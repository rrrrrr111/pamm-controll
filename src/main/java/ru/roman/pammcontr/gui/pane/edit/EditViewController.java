package ru.roman.pammcontr.gui.pane.edit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.cbchain.CallBackChain;
import ru.roman.pammcontr.gui.common.mvc.Controller;
import ru.roman.pammcontr.gui.pane.main.MainViewModel;
import ru.roman.pammcontr.gui.pane.settings.Settings;
import ru.roman.pammcontr.model.WordCategory;
import ru.roman.pammcontr.model.WordType;
import ru.roman.pammcontr.service.ServiceFactory;
import ru.roman.pammcontr.service.cache.LocalCache;
import ru.roman.pammcontr.service.cache.LocalCacheFactory;
import ru.roman.pammcontr.service.gae.GaeConnector;
import ru.roman.pammcontr.service.gae.wsclient.SaveResp;
import ru.roman.pammcontr.service.translate.TranslationService;
import ru.roman.pammcontr.util.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

/** @author Roman 21.12.12 0:24 */
public class EditViewController extends Controller<EditView, EditViewModel> {
    private static final Log log = LogFactory.getLog(EditViewController.class);

    private State state;
    private final GaeConnector gaeConnector = ServiceFactory.getGaeConnector();
    private final TranslationService yaTranslator = ServiceFactory.getYandexService();
    private final TranslationService gooTranslator = ServiceFactory.getGoogleService();
    private LocalCache localCache;
    private EditViewModel originalModel;

    public EditViewController(EditView view) {
        super(view);
    }

    protected void onInit() {

    }

    protected synchronized void onPrev() {
        switch (state) {
            case FILL_NEW:
                state = State.LOADING;
                localCache.getCurrent(
                        new CallBackChain<MainViewModel>() {
                            @Override
                            public void onSuccess(MainViewModel model) {
                                showModel(model);
                            }
                        }
                );
                break;
            default:
                state = State.LOADING;
                localCache.getPrev(
                        new CallBackChain<MainViewModel>() {
                            @Override
                            public void onSuccess(MainViewModel model) {
                                showModel(model);
                            }
                        }
                );
        }
    }

    private synchronized void showModel(MainViewModel model) {
        if (state == State.LOADING) {
            currModel = new EditViewModel(model);
            view.fillWidgets(currModel);
            originalModel = currModel.clone();
            state = State.EDIT;
        }
    }

    protected synchronized void onNext() {
        state = State.LOADING;
        localCache.getNext(new CallBackChain<MainViewModel>() {
            @Override
            public void onSuccess(MainViewModel model) {
                showModel(model);
            }
        });
    }

    public synchronized void onNew() {
        final EditViewModel old = currModel;
        currModel = new EditViewModel();
        currModel.setFacedLangId(old.getFacedLangId());
        currModel.setShadowedLangId(old.getShadowedLangId());
        currModel.setRating(3L);
        currModel.setType(old.getType());
        currModel.setCategory(old.getCategory());
        currModel.setOwner(Settings.get().getId());
        view.fillWidgets(currModel);
        state = State.FILL_NEW;
    }

    protected synchronized void onSave() {

        view.fillModel(currModel);
        if (StringUtils.isBlank(currModel.getTextFaced()) ||
                StringUtils.isBlank(currModel.getTextShadowed())) {
            GuiUtil.showInfoMessage("Cue word and the translation can not be empty");
        } else {

            WordUtils.checkIdiom(currModel);

            if (currModel.getId() != null &&
                    !currModel.getTextFaced().equals(originalModel.getTextFaced()) &&
                    !currModel.getTextShadowed().equals(originalModel.getTextShadowed())) {
                //Custom button text
                Object[] options = {"Yes, override", "No, create new", "Cancel"};
                int n = JOptionPane.showOptionDialog(view,
                        "Would you like to override old value \"" + originalModel.getTextFaced() + "\"?",
                        "Sorry, one question",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                switch (n) {
                    case 1:
                        currModel.setId(null);
                        break;
                    case 2:
                        return;
                }
            }
            state = State.LOADING;
            gaeConnector.save(currModel, new GaeConnector.GaeCallBack<SaveResp>() {
                @Override
                protected void onSuccess(SaveResp resp) {
                    currModel.setId(resp.getId());
                    localCache.addOrRenewModel(currModel);
                    showModel(currModel);
                }
            });
        }
    }

    protected void onClose() {
        view.setVisible(false);
    }

    public synchronized void show(LocalCache cache) {
        state = State.LOADING;
        this.localCache = LocalCacheFactory.createLocalCacheInstance(cache);
        localCache.getCurrent(new CallBackChain<MainViewModel>() {
            @Override
            public void onSuccess(MainViewModel model) {
                currModel = new EditViewModel(model);
                view.fillWidgets(currModel);
                originalModel = currModel.clone();
                view.setVisible(true);
                view.setState(Frame.NORMAL);
                state = State.EDIT;
            }
        });
    }

    public Collection<WordType> getTypes() {
        return Arrays.asList(WordType.values());
    }

    public void onTranslateFacedYandex() {
        translateFaced(yaTranslator);
    }

    public void onTranslateFacedGoogle() {
        translateFaced(gooTranslator);
    }

    private void translateFaced(TranslationService translator) {
        view.fillTexts(currModel);
        String tr = translator.translate(currModel.getTextFaced(),
                currModel.getFacedLangId(), currModel.getShadowedLangId());
        currModel.setTextShadowed(tr);
        view.setTexts(currModel);
    }

    public void onTranslateTranslationYandex() {
        translateTranslation(yaTranslator);
    }

    public void onTranslateTranslationGoogle() {
        translateTranslation(gooTranslator);
    }

    private void translateTranslation(TranslationService translator) {
        view.fillTexts(currModel);
        String tr = translator.translate(currModel.getTextShadowed(),
                currModel.getShadowedLangId(), currModel.getFacedLangId());
        currModel.setTextFaced(tr);
        view.setTexts(currModel);
    }

    public Collection<WordCategory> getCategories() {
        return Arrays.asList(WordCategory.values());
    }
}
