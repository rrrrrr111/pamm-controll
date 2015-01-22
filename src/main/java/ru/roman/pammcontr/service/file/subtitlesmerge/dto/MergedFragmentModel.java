package ru.roman.pammcontr.service.file.subtitlesmerge.dto;

/** @author Roman 09.06.13 13:46 */
public class MergedFragmentModel extends SrtFragmentModel {


    private String firstTextFragment;
    private String secondTextFragment;


    public String getFirstTextFragment() {
        return firstTextFragment;
    }

    public void setFirstTextFragment(String firstTextFragment) {
        this.firstTextFragment = firstTextFragment;
    }

    public String getSecondTextFragment() {
        return secondTextFragment;
    }

    public void setSecondTextFragment(String secondTextFragment) {
        this.secondTextFragment = secondTextFragment;
    }
}
