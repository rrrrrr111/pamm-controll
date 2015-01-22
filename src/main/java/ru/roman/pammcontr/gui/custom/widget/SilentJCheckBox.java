package ru.roman.pammcontr.gui.custom.widget;

import javax.swing.*;

/** @author Roman 20.12.12 23:34 */
public class SilentJCheckBox extends JCheckBox {


    public SilentJCheckBox(){
        super();
        setModel(new SilentJCheckBoxModel());
    }


    public void setSelectedSilent(boolean b) {
        boolean oldValue = isSelected();
        ((SilentJCheckBoxModel)model).setSelectedSilent(b);

    }

    private static class SilentJCheckBoxModel extends ToggleButtonModel {
        public void setSelectedSilent(boolean b) {
            ButtonGroup group = getGroup();
            if (group != null) {
                // use the group model instead
                group.setSelected(this, b);
                b = group.isSelected(this);
            }

            if (isSelected() == b) {
                return;
            }

            if (b) {
                stateMask |= SELECTED;
            } else {
                stateMask &= ~SELECTED;
            }

            // Send ChangeEvent
            fireStateChanged();

            // Send ItemEvent
//            fireItemStateChanged(
//                    new ItemEvent(this,
//                            ItemEvent.ITEM_STATE_CHANGED,
//                            this,
//                            this.isSelected() ?  ItemEvent.SELECTED : ItemEvent.DESELECTED));

        }
    }

}
