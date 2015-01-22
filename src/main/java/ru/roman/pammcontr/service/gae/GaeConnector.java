package ru.roman.pammcontr.service.gae;

import ru.roman.pammcontr.gui.common.cbchain.CallBackChain;
import ru.roman.pammcontr.gui.custom.widget.LoadingPanel;
import ru.roman.pammcontr.gui.pane.main.MainViewModel;
import ru.roman.pammcontr.service.gae.wsclient.GetListRequest;
import ru.roman.pammcontr.service.gae.wsclient.GetListResp;
import ru.roman.pammcontr.service.gae.wsclient.SaveResp;
import ru.roman.pammcontr.service.gae.wsclient.UserSettingsModel;


/** @author Roman 22.12.12 15:35 */
public interface GaeConnector {


    void save(MainViewModel model, GaeConnector.GaeCallBack<SaveResp> callBack);
    /* ������ �� ����� ���������� ������ ������, ������ ������ ���� �������� */
    void getList(GetListRequest request, GaeConnector.GaeCallBack<GetListResp> callBack);

    void renewRating(Long id, Integer rating);

    void storeSettings(UserSettingsModel model);

    void registerNewAndLoadSettings(UserSettingsModel model, GaeConnector.GaeCallBack<UserSettingsModel> callBack);

    void systemTask(int num);



    public static abstract class GaeCallBack<T> extends CallBackChain<T> {

        /**
         *
         * @param showLoading
         * @param previous  - ����� �������� ����� ������ ��������
         * @param next  - ����� �������� ����� ������� �������
         */
        protected GaeCallBack(boolean showLoading, CallBackChain<T> previous, CallBackChain<T> next) {
            super(previous, next);
            if (showLoading) {
                showLoading();
            }
        }

        protected GaeCallBack(CallBackChain<T> previous, CallBackChain<T> next) {
            this(true, previous, next);
        }

        public GaeCallBack(boolean showLoading, CallBackChain previous) {
            this(showLoading, previous, null);
        }

        public GaeCallBack(boolean showLoading) {
            this(showLoading, null);
        }

        protected GaeCallBack(CallBackChain previous) {
            this(true ,previous);
        }

        public GaeCallBack() {
            this(true, null);
        }

        public final void run(T result){
            stopLoading();
            super.run(result);
        }

        public final void exception(Exception e) {
            stopLoading();
            super.exception(e);
        }


        public final void showLoading() {
            LoadingPanel.activateSharedLoading();
        }

        public final void stopLoading() {
            LoadingPanel.stopSharedLoading();
        }

    }
}
