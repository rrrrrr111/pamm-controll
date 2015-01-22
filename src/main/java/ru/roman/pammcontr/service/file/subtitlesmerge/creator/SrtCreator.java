package ru.roman.pammcontr.service.file.subtitlesmerge.creator;

import org.apache.commons.io.FileUtils;
import ru.roman.pammcontr.service.file.subtitlesmerge.dto.MergedFragmentModel;
import ru.roman.pammcontr.service.file.subtitlesmerge.dto.MergingResultDto;
import ru.roman.pammcontr.service.file.subtitlesmerge.dto.ParsedData;

import java.io.File;

/** @author Roman 09.06.13 12:37 */
public class SrtCreator extends AbstractCreator {



    public String createSrt(ParsedData parsedData, MergingResultDto data) {
        try {

            String fileName = data.getFirstFileName();
            final StringBuilder resultStr = new StringBuilder();

            for (MergedFragmentModel model : data.getList()) {
                resultStr.append(model.getNum())
                        .append("\n")
                        .append(model.getStartTime())
                        .append(" --> ")
                        .append(model.getStopTime())
                        .append("\n")
                        .append(model.getFirstTextFragment());
                if (model.getSecondTextFragment() != null) {
                    resultStr.append("\n")
                            .append("<i>")
                            .append(model.getSecondTextFragment())
                            .append("</i>");
                }
                resultStr.append("\n\n");
            }


            fileName = removeExtension(fileName) + "_merged.srt";
            final File resultFile = FileUtils.getFile(fileName);
            FileUtils.write(resultFile, resultStr, "Windows-1251", false);
            return fileName;

        } catch (Exception e) {
            throw new RuntimeException("Error while creation srt file", e);
        }
    }


}
