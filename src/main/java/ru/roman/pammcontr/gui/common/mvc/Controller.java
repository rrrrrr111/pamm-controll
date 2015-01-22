package ru.roman.pammcontr.gui.common.mvc;

/** @author Roman 21.12.12 0:31 */
public abstract class Controller<V extends View, M extends Model> {

    protected V view;
    protected M currModel;

    public Controller(V view) {
        this.view = view;
    }

    public M getModel() {
        return currModel;
    }

    public void viewDataToModel() {
        view.fillModel(currModel);
    }

    public void modelDataToView() {
        view.fillWidgets(currModel);
    }

}
