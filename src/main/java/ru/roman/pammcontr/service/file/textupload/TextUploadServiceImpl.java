package ru.roman.pammcontr.service.file.textupload;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.pane.choose.FileChooser;
import ru.roman.pammcontr.gui.pane.choose.FileChooserBuilder;
import ru.roman.pammcontr.util.GuiUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

/** @author Roman 24.08.13 11:26 */
public class TextUploadServiceImpl implements TextUploadService{
    private static final Log log = LogFactory.getLog(TextUploadServiceImpl.class);

    private static final char[] BAD_WORD_CHARS = new char[]{
            '@','#','$','%','^','&','*','_','=','+','\\','~','/','|','!','?','�',
            '�','`','"','.',',',':',';','1','2','3','4','5','6','7','8','9','0',
            '<','>','{','}','[',']','(',')'
    };

    private FileChooser fc;

    @Override
    public ParsedTextDto parseFile() throws IOException {
        if (fc == null) {
            fc = new FileChooserBuilder("Choose text file")
                    .appendRegExpFilter("Flat files (txt, srt, htm, html)", ".*\\.(srt|txt|htm|html)$")
                    .toChooser();
        }
        final File file = fc.showSelectFileDialog();
        if (file == null) {
            return null;
        }
        log.info("Selected flat file to upload : " + file);

        final Set<String> res = new LinkedHashSet<String>();
        final Set<String> toRemove = new LinkedHashSet<String>();
        final StringTokenizer st = new StringTokenizer(FileUtils.readFileToString(file));
        String word;
        while (st.hasMoreTokens()) {
            word = StringUtils.strip(st.nextToken().toLowerCase(), "<>[]{}()�`?!.,;:\"'-");
            res.add(word);
        }

        for (Iterator<String> it = res.iterator(); it.hasNext();) {
            word = it.next();
            if (StringUtils.strip(word, "'-").length() < 2
                    || StringUtils.containsAny(word, BAD_WORD_CHARS)) {
                toRemove.add(word);
            }
        }
        res.removeAll(toRemove);
        log.info("Removed words : " + toRemove + ", count :" + toRemove.size());
        log.info("Result word list to upload : " + res + ", count :" + res.size());

        return new ParsedTextDto(res, toRemove, file);
    }

    @Override
    public void uploadList(List<String> list) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void startUpload() {
        ParsedTextDto res;
        try {
            res = parseFile();
            if (res == null) {
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception while file parsing", e);
        }

        final StringBuilder sb = new StringBuilder();
        sb.append("Bad words count : ").append(res.wordToRemoveList.size()).append("\r\n")
          .append(res.wordToRemoveList).append("\r\n\r\n")
          .append("Good words count : ").append(res.wordList.size()).append("\r\n");
        for (String word : res.wordList) {
            sb.append(word).append("\r\n");
        }

        try {
            FileUtils.write(FileUtils.getFile(res.file.getParent() ,"parsed_words.txt"), sb.toString(), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Exception while file writing", e);
        }
        GuiUtil.showInfoMessage("Parsing complete");
    }


    public class ParsedTextDto {
        Collection<String> wordList;
        Collection<String> wordToRemoveList;
        File file;

        public ParsedTextDto(Collection<String> wordList, Collection<String> wordToRemoveList, File file) {
            this.wordList = wordList;
            this.wordToRemoveList = wordToRemoveList;
            this.file = file;
        }
    }
}
