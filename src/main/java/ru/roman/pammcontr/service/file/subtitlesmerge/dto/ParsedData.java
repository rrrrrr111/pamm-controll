package ru.roman.pammcontr.service.file.subtitlesmerge.dto;

import java.util.List;
import java.util.Map;

/** @author Roman 09.06.13 12:18 */
public class ParsedData {

    private boolean parsed;
    private String firstFileName;
    private List<SrtFragmentModel> firstList;
    private long syncDelta;
    private Map<Long, SrtFragmentModel> secondSorted;


    public String getFirstFileName() {
        return firstFileName;
    }

    public void setFirstFileName(String firstFileName) {
        this.firstFileName = firstFileName;
    }

    public List<SrtFragmentModel> getFirstList() {
        return firstList;
    }

    public void setFirstList(List<SrtFragmentModel> firstList) {
        this.firstList = firstList;
    }

    public long getSyncDelta() {
        return syncDelta;
    }

    public void setSyncDelta(long syncDelta) {
        this.syncDelta = syncDelta;
    }

    public Map<Long, SrtFragmentModel> getSecondSorted() {
        return secondSorted;
    }

    public void setSecondSorted(Map<Long, SrtFragmentModel> secondSorted) {
        this.secondSorted = secondSorted;
    }

    public boolean isParsed() {
        return parsed;
    }

    public void setParsed(boolean parsed) {
        this.parsed = parsed;
    }
}
