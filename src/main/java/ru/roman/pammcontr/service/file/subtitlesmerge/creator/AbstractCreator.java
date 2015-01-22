package ru.roman.pammcontr.service.file.subtitlesmerge.creator;

/** @author Roman 09.06.13 22:58 */
public abstract class AbstractCreator {



    protected static String removeExtension(String fileName) {
        if (fileName.matches("^.*\\.[\\S]{3}$")) {
            return fileName.substring(0, fileName.length() - 4);
        }
        if (fileName.matches("^.*\\.[\\S]{4}$")) {
            return fileName.substring(0, fileName.length() - 5);
        }
        return fileName;
    }
}
