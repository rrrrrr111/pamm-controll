package ru.roman.pammcontr.gui.common.mvc;

/** @author Roman 21.12.12 0:34 */
public interface View<M extends Model, V extends View, C extends Controller<V, M>> {

    C getController();

    void fillWidgets(M model);

    void fillModel(M currModel);
}
