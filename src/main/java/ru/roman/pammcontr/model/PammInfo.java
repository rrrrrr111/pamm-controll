package ru.roman.pammcontr.model;

/**
 * Created by Roman on 22.01.2015.
 */
public class PammInfo {

    protected String name;
    protected Double profitLossPercent;
    protected boolean flagToIgnoreDropDown;

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

    public boolean isFlagToIgnoreDropDown() {
        return flagToIgnoreDropDown;
    }

    public void setFlagToIgnoreDropDown(boolean flagToIgnoreDropDown) {
        this.flagToIgnoreDropDown = flagToIgnoreDropDown;
    }
}
