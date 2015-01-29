package ru.roman.pammcontr.gui.pane.main;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ru.roman.pammcontr.gui.common.grid.ListTableModel;
import ru.roman.pammcontr.gui.common.mvc.Model;

import javax.xml.datatype.XMLGregorianCalendar;

/** @author Roman 19.12.12 23:36 */
public class MainViewModel implements Model {

    private String borderInfo;
    private ListTableModel<String> tableModel = new ListTableModel<>();
    private String infoText;


    public ListTableModel<String> getTableModel() {
        return tableModel;
    }


    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException();
    }


    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getBorderInfo() {
        return borderInfo;
    }

    public void setBorderInfo(String borderInfo) {
        this.borderInfo = borderInfo;
    }
}
