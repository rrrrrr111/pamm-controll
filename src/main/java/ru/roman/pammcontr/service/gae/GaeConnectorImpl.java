package ru.roman.pammcontr.service.gae;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.pane.main.MainViewModel;
import ru.roman.pammcontr.service.gae.wsclient.*;
import ru.roman.pammcontr.util.Const;
import ru.roman.pammcontr.util.ExceptionHandler;
import ru.roman.pammcontr.util.WsUtil;

import javax.xml.ws.BindingProvider;
import java.net.URL;

/** @author Roman 22.12.12 15:36 */
public class GaeConnectorImpl implements GaeConnector {
    private static final Log log = LogFactory.getLog(GaeConnectorImpl.class);

    public static final GaeCallBack<Void> CALL_BACK_STUB = new GaeCallBack<Void>(false) {
        @Override
        protected void onSuccess(Void result) {
        }
    };

    private static DataProvider provider;

    public GaeConnectorImpl() {
        try {
            provider = new DataProvider_Service(new URL(Const.DEFAULT_ENDPOINT_WSDL)).getDataProviderPort();
            BindingProvider bp = (BindingProvider)provider;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, Const.DEFAULT_ENDPOINT);

            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "bim");
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "dfg$5Uf%e6.fF/Ghfds(gghsnm,.er");
        } catch (Exception e) {
            ExceptionHandler.showErrorMessageAndExit(e);
        }
    }

    @Override
    public void save(final MainViewModel model, final GaeConnector.GaeCallBack<SaveResp> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final SaveRequest req = WsUtil.prepareRequest(new SaveRequest());
                    req.setModel(model);
                    SaveResp resp = provider.save(req);
                    log.info(String.format("Word saved id=%s, state=%s", resp.getId(), resp.getStatus()));
                    callBack.run(resp);
                } catch (Exception e) {
                    callBack.exception(e);
                }
            }
        }).start();
    }

    @Override
    public void getList(final GetListRequest request, final GaeConnector.GaeCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final GetListResp list = provider.getList(request);
                    log.info(String.format("List received count : %s", list.getList().size()));
                    callBack.run(list);
                } catch (Exception e) {
                    callBack.exception(e);
                }
            }
        }).start();
    }

    @Override
    public void renewRating(final Long id, final Integer rating) {
        CALL_BACK_STUB.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final RenewRatingRequest req = WsUtil.prepareRequest(new RenewRatingRequest());
                    req.setId(id);
                    req.setRating(rating);
                    provider.renewRating(req);
                    log.info(String.format("rating %s renew for id=%s complete", rating, id));
                    CALL_BACK_STUB.stopLoading();
                } catch (Exception e) {
                    CALL_BACK_STUB.exception(e);
                }
            }
        }).start();
    }

    @Override
    public void storeSettings(final UserSettingsModel model) {
        CALL_BACK_STUB.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final StoreSettingsRequest req = WsUtil.prepareRequest(new StoreSettingsRequest());
                    req.setUserSettingsModel(model);
                    final StoreSettingsResp resp = provider.storeSettings(req);
                    log.info(String.format("Settings stored %s", ToStringBuilder.reflectionToString(model)));
                    CALL_BACK_STUB.stopLoading();
                } catch (Exception e) {
                    CALL_BACK_STUB.exception(e);
                }
            }
        }).start();
    }

    @Override
    public void registerNewAndLoadSettings(final UserSettingsModel model, final GaeConnector.GaeCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final RegisterNewAndLoadSettingsRequest req = WsUtil.prepareRequest(new RegisterNewAndLoadSettingsRequest());
                    req.setUserSettingsModel(model);
                    final RegisterNewAndLoadSettingsResp resp = provider.registerNewAndLoadSettings(req);
                    log.info(String.format("GAE registered or loaded settings %s", ToStringBuilder.reflectionToString(resp.getUserSettingsModel())));
                    callBack.run(resp.getUserSettingsModel());
                } catch (Exception e) {
                    callBack.exception(e);
                }
            }
        }).start();
    }

    public void systemTask(final int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CALL_BACK_STUB.showLoading();
                    final SystemTaskRequest req = WsUtil.prepareRequest(new SystemTaskRequest());
                    req.setTaskNum(num);
                    final SystemTaskResp resp = provider.systemTask(req);
                    log.info(String.format("SystemTask executed %s", ToStringBuilder.reflectionToString(resp)));
                    CALL_BACK_STUB.stopLoading();
                } catch (Exception e) {
                    CALL_BACK_STUB.exception(e);
                }
            }
        }).start();
    }
}
