package ru.roman.pammcontr.service.file.subtitlesmerge;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.pane.choose.FileChooser;
import ru.roman.pammcontr.gui.pane.choose.FileChooserBuilder;
import ru.roman.pammcontr.service.file.subtitlesmerge.creator.HtmlCreator;
import ru.roman.pammcontr.service.file.subtitlesmerge.creator.SrtCreator;
import ru.roman.pammcontr.service.file.subtitlesmerge.dto.*;
import ru.roman.pammcontr.util.GuiUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author Roman 02.06.13 10:51 */
public class SubtitlesMergeServiceImpl implements SubtitlesMergeService {
    private static final Log log = LogFactory.getLog(SubtitlesMergeServiceImpl.class);

    private SrtCreator srtCreator = new SrtCreator();
    private HtmlCreator htmlCreator = new HtmlCreator();
    private FileChooser fc;


    @Override
    public void startMerge(List<String> formatsList) {

        if (formatsList.isEmpty()) {
            GuiUtil.showInfoMessage("Output format have to be selected");
            return;
        }

        final ParsedData data = parseFiles();
        if (!data.isParsed())  {
            return;
        }
        final MergingResultDto merRes = merge(data);


        String filesNames = "";
        if (formatsList.contains(SRT_FORMAT)) {
            filesNames += "\n" + srtCreator.createSrt(data, merRes);
        }
        if (formatsList.contains(HTML_FORMAT)) {
            filesNames += "\n" + htmlCreator.createHtml(data, merRes);
        }

        final String mess = String.format("Merging complete, %s rows processed from first file, %s from second. \n" +
                "Result stored to : %s", merRes.getFirstCounter(), merRes.getSecondCounter(), filesNames);
        log.info(mess);
        GuiUtil.showInfoMessage(mess);

    }

    private MergingResultDto merge(ParsedData data) {
        List<MergedFragmentModel> list = new ArrayList<MergedFragmentModel>();

        final List<SrtFragmentModel> firstList = data.getFirstList();
        final long syncDelta = data.getSyncDelta();
        final Map<Long, SrtFragmentModel> secondSorted = data.getSecondSorted();
        final String firstFileName = data.getFirstFileName();

        String secondText;
        String stopTime;
        SrtFragmentModel firstModel;
        SrtFragmentModel nextModel;
        long startTime = 0;
        long endTime;
        int firstCounter = 0;
        int secondCounter = 0;
        final int size = firstList.size();
        int nextI;

        for (int i = 0; i < size; i++) {
            firstModel = firstList.get(i);
            nextI = i + 1;
            if (nextI < size) {
                nextModel = firstList.get(nextI);
                endTime = firstModel.getStartTimeSec() +
                        ((nextModel.getStartTimeSec() - firstModel.getStartTimeSec()) / 2) - syncDelta;
                stopTime = SrtUtil.calcStopTime(nextModel.getStartTime());
            } else {
                endTime = Long.MAX_VALUE;
                stopTime = firstModel.getStopTime();
            }
            secondText = findNearest(secondSorted, startTime, endTime);

            final MergedFragmentModel model = new MergedFragmentModel();
            list.add(model);
            model.setNum(++firstCounter);
            model.setFirstTextFragment(firstModel.getTextFragment());
            if (secondText != null) {
                model.setSecondTextFragment(secondText);
                ++secondCounter;
            }
            model.setStartTime(firstModel.getStartTime());
            model.setStartTimeSec(firstModel.getStartTimeSec());
            model.setStopTime(stopTime);
            startTime = endTime;
        }

        return new MergingResultDto(firstFileName, firstCounter, secondCounter, list);
    }


    private List<SrtFragmentModel> parseFile(File file) throws IOException {

        final List<SrtFragmentModel> res = new ArrayList<SrtFragmentModel>();
        final String nl = "\\r?\\n";
        final Pattern pattern = Pattern.compile("(?s)(\\d+)[ \\t]*" + nl +
                "(\\d{1,2}:\\d\\d:\\d\\d,\\d\\d\\d)" +
                "[ \\t]*-->[ \\t]*" +
                "(\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d)" +
                "[ \\t]*(X1:\\d.*?)??" + nl + "(.*?)" + nl + nl);
        //group 2 is start time group 3 is finish time group 4 is subtitle text
        final Matcher matcher = pattern.matcher(FileUtils.readFileToString(file));
        SrtFragmentModel model;
        while (matcher.find()) {
            final int num = Integer.valueOf(matcher.group(1));
            final String start = matcher.group(2);
            final String stop = matcher.group(3);
            final String text1 = matcher.group(4);
            final String text2 = matcher.group(5);
            //log.info(String.format("Parsing: %s %s %s", num, start, stop));

            model = new SrtFragmentModel();
            model.setNum(num);
            model.setStartTime(start);
            model.setStopTime(stop);
            model.setTextFragment((StringUtils.trimToEmpty(StringUtils.normalizeSpace(text1))
                    + (text2 == null ? "" : " " + StringUtils.trimToEmpty(StringUtils.normalizeSpace(text2)))
            ).trim());
            model.setStartTimeSec(SrtUtil.calcTime(start));
            res.add(model);
        }
        return res;

    }

    public static String findNearest(Map<Long, SrtFragmentModel> secondSorted, long startTime, long endTime) {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Long, SrtFragmentModel> secondEntry : secondSorted.entrySet()) {
            if (secondEntry.getKey() >= startTime && secondEntry.getKey() < endTime) {
                str.append(secondEntry.getValue().getTextFragment()).append(" ");
            }
        }
        return StringUtils.trimToNull(str.toString());
    }



    public ParsedData parseFiles() {
        try {
            ParsedData data = new ParsedData();
            if (fc == null) {
                fc = new FileChooserBuilder("Subtitles files (*.srt)", "srt", "Choose first subtitles file").toChooser();
            }
            final File firstFile = fc.showSelectFileDialog();
            if (firstFile == null) {
                return data;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            fc.setDialogTitle("Choose another one");
            final File secondFile = fc.showSelectFileDialog();
            if (secondFile == null) {
                GuiUtil.showInfoMessage("Second file for merging hasn't selected");
                return data;
            }

            final String firstFileName = firstFile.getCanonicalPath();
            data.setFirstFileName(firstFileName);
            if (firstFileName.equals(secondFile.getCanonicalPath())) {
                GuiUtil.showInfoMessage("Different files should be selected");
                return data;
            }

            log.info(String.format("Merging files:\n%s\n%s", firstFile, secondFile));

            final List<SrtFragmentModel> firstList = parseFile(firstFile);
            data.setFirstList(firstList);
            if (firstList.isEmpty()) {
                GuiUtil.showInfoMessage("Incorrect format of the first file");
                return data;
            }

            final List<SrtFragmentModel> secondList = parseFile(secondFile);
            if (secondList.isEmpty()) {
                GuiUtil.showInfoMessage("Incorrect format of the second file");
                return data;
            }

            // ����������� �� �������, � ������ ������ ���������� ��� ������ ������
            data.setSyncDelta(data.getFirstList().get(0).getStartTimeSec() - secondList.get(0).getStartTimeSec());

            final HashMap<Long, SrtFragmentModel> secondSorted = new HashMap<Long, SrtFragmentModel>();
            data.setSecondSorted(secondSorted);
            for (SrtFragmentModel secondModel : secondList) {
                secondSorted.put(secondModel.getStartTimeSec(), secondModel);
            }
            data.setParsed(true);
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
