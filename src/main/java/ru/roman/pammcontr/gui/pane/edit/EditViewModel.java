package ru.roman.pammcontr.gui.pane.edit;

import ru.roman.pammcontr.gui.pane.main.MainViewModel;

/** @author Roman 19.12.12 23:36 */
public class EditViewModel extends MainViewModel {

    /** ���������� ������������� */
    private boolean automaticTranslation;

    public EditViewModel() {
        super();
    }

    public EditViewModel(MainViewModel model) {
        super(model);
    }

    public boolean isAutomaticTranslation() {
        return automaticTranslation;
    }

    public void setAutomaticTranslation(boolean automaticTranslation) {
        this.automaticTranslation = automaticTranslation;
    }

    @Override
    protected EditViewModel clone(){
        return new EditViewModel(this);
    }
}
