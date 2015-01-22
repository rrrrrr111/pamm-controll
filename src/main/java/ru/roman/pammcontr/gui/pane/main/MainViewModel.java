package ru.roman.pammcontr.gui.pane.main;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ru.roman.pammcontr.gui.common.mvc.Model;
import ru.roman.pammcontr.model.BimItemModel;

import javax.xml.datatype.XMLGregorianCalendar;

/** @author Roman 19.12.12 23:36 */
public class MainViewModel extends BimItemModel implements Model {

    public MainViewModel() {
        super();
    }

    public MainViewModel(Long id, String textFaced, String textShadowed,
                         Long facedLangId, Long shadowedLangId, Long rating,
                         Long type, Long category, Long modelNum, Long owner, XMLGregorianCalendar editDate) {
        this.id = id;
        this.textFaced = textFaced;
        this.textShadowed = textShadowed;
        this.facedLangId = facedLangId;
        this.shadowedLangId = shadowedLangId;
        this.rating = rating;
        this.type = type;
        this.category = category;
        this.modelNum = modelNum;
        this.owner = owner;
        this.editDate = editDate;
    }

    public MainViewModel(BimItemModel model) {
        this(
                model.getId(),
                model.getTextFaced(),
                model.getTextShadowed(),
                model.getFacedLangId(),
                model.getShadowedLangId() ,
                model.getRating(),
                model.getType(),
                model.getCategory(),
                model.getModelNum(),
                model.getOwner(),
                model.getEditDate()
        );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o instanceof MainViewModel)){
            MainViewModel that = (MainViewModel) o;
            if (id != null) {
                return id.equals(that.id);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int compareTo(Object o) {
        if (id != null && (o instanceof MainViewModel)) {
            MainViewModel that = (MainViewModel) o;
            return id.compareTo(that.id);
        }
        return 0;
    }
}
