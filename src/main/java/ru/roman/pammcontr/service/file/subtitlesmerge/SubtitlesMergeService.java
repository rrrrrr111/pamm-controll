package ru.roman.pammcontr.service.file.subtitlesmerge;

import java.util.List;

/** @author Roman 02.06.13 10:51 */
public interface SubtitlesMergeService {

    String SRT_FORMAT = "SRT";
    String HTML_FORMAT = "HTML";

    void startMerge(List<String> formatsList);
}
