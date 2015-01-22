package ru.roman.pammcontr.service.file.subtitlesmerge.dto;

import java.io.Serializable;

/** @author Roman 09.06.13 13:22 */
public class SrtFragmentModel implements Serializable {
    private int num;
    private long startTimeSec;
    private String startTime;
    private String stopTime;
    private String textFragment;


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getStartTimeSec() {
        return startTimeSec;
    }

    public void setStartTimeSec(long startTimeSec) {
        this.startTimeSec = startTimeSec;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getTextFragment() {
        return textFragment;
    }

    public void setTextFragment(String textFragment) {
        this.textFragment = textFragment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SrtFragmentModel)) return false;
        SrtFragmentModel that = (SrtFragmentModel) o;
        if (!startTime.equals(that.startTime)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return startTime.hashCode();
    }

    @Override
    public String toString() {
        return "SrtFragmentModel{" +
                "num=" + num +
                ", startTimeSec=" + startTimeSec +
                ", startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", textFragment='" + textFragment + '\'' +
                '}';
    }
}
