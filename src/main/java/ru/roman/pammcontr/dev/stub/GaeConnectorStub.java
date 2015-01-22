package ru.roman.pammcontr.dev.stub;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.pane.main.MainViewModel;
import ru.roman.pammcontr.model.WordCategory;
import ru.roman.pammcontr.model.WordType;
import ru.roman.pammcontr.service.gae.GaeConnector;
import ru.roman.pammcontr.service.gae.GaeConnectorImpl;
import ru.roman.pammcontr.service.gae.wsclient.*;
import ru.roman.pammcontr.util.WsUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/** @author Roman 22.12.12 15:43 */
public class GaeConnectorStub implements GaeConnector {
    private static final Log log = LogFactory.getLog(GaeConnectorStub.class);

    public static TreeSet<MainViewModel> store = new TreeSet<MainViewModel>();
    public static List<UserSettingsModel> settings = new ArrayList<UserSettingsModel>();
    static long counter;
    public static final int SERVICE_TIMEOUT = 1000;

    static {
        Date currDate = new Date();


        store.add(new MainViewModel(counter++, "1 Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor incididunt ut labore","transl1",1l,2l,1l, WordType.EXPRESSION.getOrdinal()
                , WordCategory.COMMON.getOrdinal(), null, 1l, WsUtil.asXMLGregorianCalendar(currDate)));
        store.add(new MainViewModel(counter++, "2 Lorem ipsum dolor sit amet, " +
                "consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod " +
                "tempor incididunt ut labore Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod " +
                "tempor incididunt ut labore",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                1l,2l,1l, WordType.EXPRESSION.getOrdinal(),
                WordCategory.COMMON.getOrdinal(), null, 1l, WsUtil.asXMLGregorianCalendar(currDate)));
        store.add(new MainViewModel(counter++, "3 word3","transl3",1l,2l,4l, WordType.EXPRESSION.getOrdinal()
                , WordCategory.COMMON.getOrdinal(), null, 1l, WsUtil.asXMLGregorianCalendar(currDate)));
        store.add(new MainViewModel(counter++, "4 word4","transl4",1l,2l,3l, WordType.WORD.getOrdinal()
                , WordCategory.COMMON.getOrdinal(), null, 1l, WsUtil.asXMLGregorianCalendar(currDate)));
        store.add(new MainViewModel(counter++, "5 word5","transl5",1l,2l,4l, WordType.IDIOM.getOrdinal()
                , WordCategory.COMMON.getOrdinal(), null, 1l, WsUtil.asXMLGregorianCalendar(currDate)));
        store.add(new MainViewModel(counter++, "6 word6","transl6",1l,2l,2l, WordType.WORD.getOrdinal()
                , WordCategory.COMMON.getOrdinal(), null, 1l, WsUtil.asXMLGregorianCalendar(currDate)));
        store.add(new MainViewModel(counter++, "7 word7","transl7",1l,2l,5l, WordType.WORD.getOrdinal()
                , WordCategory.COMMON.getOrdinal(), null, 1l, WsUtil.asXMLGregorianCalendar(currDate)));


        UserSettingsModel settingsModel = new UserSettingsModel();
        settingsModel.setCacheMaxSize(100L);
        settingsModel.setCurrentNum(0L);
        settingsModel.setEditDate(WsUtil.getCurrGregorian());
        settingsModel.setFacedLangId(1L);
        settingsModel.setId(81001L);
        settingsModel.setLogin("curdes@gmail.com");
        settingsModel.setLookAndFeel("");
        settingsModel.setOpacity(0.75);
        settingsModel.setPassword("355869f9511e558c3fbdde6779dcc0");     //tratra11
        settingsModel.setPortion(100L);
        settingsModel.setPreviewDuration(0.1);
        settingsModel.setPreviewInterval(1d);
        settingsModel.setRecordsCount(1000L);
        settingsModel.setShadowedLangId(2L);
        settingsModel.setSortingDirection("DESCENDING");
        settingsModel.setSortingField("editDate");
        settingsModel.setWorkWithPortion(true);
        settingsModel.setDisabilityDuration(3d);
        settings.add(settingsModel);
    }


    @Override
    public void save(MainViewModel model, GaeConnector.GaeCallBack<SaveResp> callBack) {
        if (model.getId() == null) {
            model.setId(++counter);
        }
        if (store.contains(model)) {
            store.remove(model);
        }
        store.add(model);
        log.info("Stub save : " + ToStringBuilder.reflectionToString(model));
        sleep();
        final SaveResp result = new SaveResp();
        result.setId(model.getId());
        result.setStatus(SaveStatus.CREATED_NEW);
        callBack.run(result);

    }

    @Override
    public void getList(GetListRequest request, GaeConnector.GaeCallBack<GetListResp> callBack) {
        List<MainViewModel> list = new ArrayList<MainViewModel>(store);
        final MainViewModel from = list.get(request.getOffset());
        int toIdx = request.getOffset() + request.getCount() - 1;
        if (toIdx >= list.size()) {
            toIdx = list.size() - 1;
        }
        final MainViewModel to = list.get(toIdx);

        GetListResp resp = new GetListResp();
        resp.getList().addAll(store.subSet(from, true, to, true));
        resp.setRecordsCount(store.size());
        log.info(String.format("Stub getList : %s, return : %s models",
                ToStringBuilder.reflectionToString(request), resp.getList().size()));
        sleep();
        callBack.run(resp);
    }

    @Override
    public void renewRating(Long id, Integer rating) {
        GaeConnectorImpl.CALL_BACK_STUB.showLoading();
        for (MainViewModel mainViewModel : store) {
            if (mainViewModel.getId().equals(id)) {
                mainViewModel.setRating(rating.longValue());
            }
        }
        log.info(String.format("Stub renewRating id=%s, rating=%s", id, rating));
        sleep();
    }

    @Override
    public void storeSettings(UserSettingsModel model) {
        registerNewAndLoadSettings(model, new GaeCallBack<UserSettingsModel>() {
            @Override
            protected void onSuccess(UserSettingsModel result) {
            }
        });
    }

    @Override
    public void registerNewAndLoadSettings(UserSettingsModel model, GaeConnector.GaeCallBack<UserSettingsModel> callBack) {
        final UserSettingsModel[] stored = new UserSettingsModel[]{null};
        for (UserSettingsModel setting : settings) {
            if (setting.getLogin().equalsIgnoreCase(model.getLogin())) {
                stored[0] = setting;
                break;
            }
        }
        if (stored[0] != null) {
            if (!stored[0].getPassword().equals(model.getPassword())) {
                throw new RuntimeException("password or login wrong");
            }
            sleep();
            callBack.run(stored[0]);
        } else {
            settings.add(model);
            callBack.run(stored[0]);
        }


    }

    @Override
    public void systemTask(int num) {
        GaeConnectorImpl.CALL_BACK_STUB.showLoading();
        sleep();
        GaeConnectorImpl.CALL_BACK_STUB.stopLoading();
    }


    private void sleep() {
        try {
            Thread.sleep(SERVICE_TIMEOUT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
