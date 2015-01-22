
package ru.roman.pammcontr.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;


public class UserSettingsModel {

    protected Double checkFastPammInterval;
    protected String fastPammUrl;
    protected Double minimalPercentLevelToControl;
    protected Date lastCheckDate;
    protected XMLGregorianCalendar settingsEditDate;
    protected String lookAndFeel;
    protected Double opacity;
    protected Double previewDuration;
    protected Double disabilityDuration;
    protected List<PammInfo> pammInfoList;

    public Double getCheckFastPammInterval() {
        return checkFastPammInterval;
    }

    public void setCheckFastPammInterval(Double checkFastPammInterval) {
        this.checkFastPammInterval = checkFastPammInterval;
    }

    public String getFastPammUrl() {
        return fastPammUrl;
    }

    public void setFastPammUrl(String fastPammUrl) {
        this.fastPammUrl = fastPammUrl;
    }

    public Double getMinimalPercentLevelToControl() {
        return minimalPercentLevelToControl;
    }

    public void setMinimalPercentLevelToControl(Double minimalPercentLevelToControl) {
        this.minimalPercentLevelToControl = minimalPercentLevelToControl;
    }

    public Date getLastCheckDate() {
        return lastCheckDate;
    }

    public void setLastCheckDate(Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    public XMLGregorianCalendar getSettingsEditDate() {
        return settingsEditDate;
    }

    public void setSettingsEditDate(XMLGregorianCalendar settingsEditDate) {
        this.settingsEditDate = settingsEditDate;
    }

    public List<PammInfo> getPammInfoList() {
        if (pammInfoList == null) {
            pammInfoList = new ArrayList<>();
        }
        return pammInfoList;
    }

    public void setPammInfoList(List<PammInfo> pammInfoList) {
        this.pammInfoList = pammInfoList;
    }

    public String getLookAndFeel() {
        return lookAndFeel;
    }

    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }

    public Double getOpacity() {
        return opacity;
    }

    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }

    public Double getPreviewDuration() {
        return previewDuration;
    }

    public void setPreviewDuration(Double previewDuration) {
        this.previewDuration = previewDuration;
    }

    public Double getDisabilityDuration() {
        return disabilityDuration;
    }

    public void setDisabilityDuration(Double disabilityDuration) {
        this.disabilityDuration = disabilityDuration;
    }
}
