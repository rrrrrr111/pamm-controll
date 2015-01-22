package ru.roman.pammcontr.service.file.subtitlesmerge.dto;

import java.util.List;

/** @author Roman 09.06.13 14:15 */
public class MergingResultDto {

    private String firstFileName;
    private int firstCounter;
    private int secondCounter;
    private List<MergedFragmentModel> list;

    public MergingResultDto() {
    }

    public MergingResultDto(String firstFileName, int firstCounter, int secondCounter, List<MergedFragmentModel> list) {
        this.firstFileName = firstFileName;
        this.firstCounter = firstCounter;
        this.secondCounter = secondCounter;
        this.list = list;
    }

    public String getFirstFileName() {
        return firstFileName;
    }

    public void setFirstFileName(String firstFileName) {
        this.firstFileName = firstFileName;
    }

    public int getFirstCounter() {
        return firstCounter;
    }

    public void setFirstCounter(int firstCounter) {
        this.firstCounter = firstCounter;
    }

    public int getSecondCounter() {
        return secondCounter;
    }

    public void setSecondCounter(int secondCounter) {
        this.secondCounter = secondCounter;
    }

    public List<MergedFragmentModel> getList() {
        return list;
    }

    public void setList(List<MergedFragmentModel> list) {
        this.list = list;
    }
}
