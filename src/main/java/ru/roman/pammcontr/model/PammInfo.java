package ru.roman.pammcontr.model;

/**
 * Created by Roman on 22.01.2015.
 */
public class PammInfo {

    protected String name;
    protected Long num;
    protected Double profitLossPercent;
    protected Boolean flagToIgnoreDropDown;


    public PammInfo() {
    }

    public PammInfo(String name, Long num, Double profitLossPercent, boolean flagToIgnoreDropDown) {
        this.name = name;
        this.num = num;
        this.profitLossPercent = profitLossPercent;
        this.flagToIgnoreDropDown = flagToIgnoreDropDown;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getProfitLossPercent() {
        return profitLossPercent;
    }

    public void setProfitLossPercent(Double profitLossPercent) {
        this.profitLossPercent = profitLossPercent;
    }

    public Boolean isFlagToIgnoreDropDown() {
        return flagToIgnoreDropDown;
    }

    public void setFlagToIgnoreDropDown(Boolean flagToIgnoreDropDown) {
        this.flagToIgnoreDropDown = flagToIgnoreDropDown;
    }
}
