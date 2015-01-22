package ru.roman.pammcontr.gui.common.cbchain;

import ru.roman.pammcontr.util.ExceptionHandler;

/**
 * ��� ���������� ������� ������� �������. ���������� �������� � ������������ ���� �����
 * previous  - ����� �������� ����� ����������� ��������
 * next  - ����� �������� ����� ������������ �������
 * �������� ������ ����������� ������� ������ ������� ����� run(..), � ��� ������ exception(..)
 * �������� ������ ����� ������� ������ ������ �� ������� �������, ��������� ����� ����������
 * � ������������������ �������� ��� ��������. ��� ������� RuntimeException ������ �� �������, ���������
 * ������ � ������� �� �����������, ������ �������������� � ������ onInnerException(..) �� ���� �������,
 * ��� ������������� ����� ��������������.
 *
 *
 * @author Roman 30.03.13 16:46 */
public abstract class CallBackChain<T> {

    private CallBackChain<T> previous = null;
    private CallBackChain<T> next = null;
    private static ThreadLocal<Boolean> defaultFailureActionExecuted = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };


    /**
     *
     * @param previous  - ����� �������� ����� ������ ��������
     * @param next  - ����� �������� ����� ������� �������
     */
    public CallBackChain(CallBackChain<T> previous, CallBackChain<T> next) {
       if (previous != null) {
            if (previous.next == null) {
                previous.next = this;
                this.previous = previous;
            } else {
                throw new ChaneCreationException("Previous nexus already set to : " + this.previous.next);
            }
        }
        if (next != null) {
            if (next.previous == null) {
                next.previous = this;
                this.next = next;
            } else {
                throw new ChaneCreationException("Next nexus already set to : " + this.next.next);
            }
        }
    }
    /**
     *
     * @param previous  - ����� �������� ����� ������ ��������
     */
    public CallBackChain(CallBackChain<T> previous) {
        this(previous, null);
    }

    protected CallBackChain() {
        this(null, null);
    }



    public void run(T result){
        if (getPrevious() == null) {
            runSuccess(result);
        } else {
            getPrevious().run(result);
        }
    }

    private void runSuccess(T result) {
        try {
            onSuccess(result);
        } catch (RuntimeException ex) {
            onInner(ex, result, null);
            return;
        }
        if (getNext() != null) {
            getNext().runSuccess(result);
        }
    }

    protected abstract void onSuccess(T result);

    public void exception(Exception e) {
        if (getPrevious() == null) {
            exceptionFailure(e);
        } else {
            getPrevious().exception(e);
        }
    }

    private void exceptionFailure(Exception e) {
        try {
            onFailure(e);
        } catch (RuntimeException ex) {
            onInner(ex, null, e);
            return;
        }
        if (getNext() != null) {
            getNext().exceptionFailure(e);
        }
    }

    protected void onFailure(Exception e) {
        if (!defaultFailureActionExecuted.get()) {
            defaultFailureActionExecuted.set(true);
            ExceptionHandler.showErrorMessage(e);
        }
    }



    private void onInner(RuntimeException nexusEx, T result, Exception failureEx) {
        if (getPrevious() == null) {
            onInnerException(nexusEx, result, failureEx);
        } else {
            getPrevious().onInner(nexusEx, result, failureEx);
        }
    }

    /**
     * ����������� ��� ������ � ������ ������ �� ��������� onFailure(..) ��� onSuccess(..)
     * ���� ����� ����������������, �� ������ ������� ����� ����������� onInnerException(..)
     * ��� ������� ��������� ���������� ��������.
     *
     * @param nexusEx - ������ � ���������� ��������
     * @param result - ���� �� null �� ������ � onSuccess(..)
     * @param failureEx - ���� �� null �� ������ � onFailure(..)
     */
    protected void onInnerException(RuntimeException nexusEx, T result, Exception failureEx) {
        if (getNext() == null) {
            throw nexusEx;
        } else {
            getNext().onInnerException(nexusEx, result, failureEx);
        }
    }

    public CallBackChain<T> getPrevious() {
        return previous;
    }

    public CallBackChain<T> getNext() {
        return next;
    }

}
