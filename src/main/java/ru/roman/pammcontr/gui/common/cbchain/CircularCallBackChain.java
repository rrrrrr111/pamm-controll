package ru.roman.pammcontr.gui.common.cbchain;

/** @author Roman 07.09.13 15:20 */
public abstract class CircularCallBackChain<T> extends CallBackChain<T> {


    private final int num;

    protected CircularCallBackChain() {
        super(null, null);
        num = 1;
    }

    public CircularCallBackChain(CircularCallBackChain<T> previous) {
        super(previous, null);
        num = getPrevious().getNum() + 1;
    }



    public void closeChain() {
        if (getPrevious() != null) {
            getPrevious().closeChain();
        } else {

        }
    }

    public CircularCallBackChain<T> getPrevious() {
        return (CircularCallBackChain<T>) super.getPrevious();
    }

    public CircularCallBackChain<T> getNext() {
        return (CircularCallBackChain<T>) super.getNext();
    }

    public int getNum() {
        return num;
    }
}
