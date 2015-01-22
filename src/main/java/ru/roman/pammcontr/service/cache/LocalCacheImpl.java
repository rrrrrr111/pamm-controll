package ru.roman.pammcontr.service.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.roman.pammcontr.gui.common.cbchain.CallBackChain;
import ru.roman.pammcontr.gui.pane.main.MainViewModel;
import ru.roman.pammcontr.gui.pane.settings.Settings;
import ru.roman.pammcontr.gui.pane.settings.SettingsViewModel;
import ru.roman.pammcontr.service.ServiceFactory;

import ru.roman.pammcontr.model.BimItemModel;

import ru.roman.pammcontr.util.BimException;
import ru.roman.pammcontr.util.WsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** @author Roman 22.12.12 16:44 */
public class LocalCacheImpl implements LocalCache {
    private static final Log log = LogFactory.getLog(LocalCacheImpl.class);


    /* ��� */
    private final List<MainViewModel> cache;
    /** ������� ������������ �������� ���� ������� ���� � ��, ���������� � ���� */
    private volatile Integer currentNum;
    /** ����� ����������� ������ �� ��� �������� ������ � ���� */
    private volatile Integer currentOffset;
    /** ����� ���-�� ������� */
    private volatile Integer recordsCount;

    protected LocalCacheImpl() {
        super();
        this.cache = new ArrayList<MainViewModel>(Settings.get().getCacheMaxSize().intValue());
    }

    protected LocalCacheImpl(LocalCache cache) {
        super();
        this.cache = cache.getCacheData();
        initCache(cache.getCurrentNum(), cache.getRecordsCount(), cache.getCurrentOffset());
    }

    @Override
    public synchronized void initCache(Integer currentNum, Integer recordsCount, Integer currentOffset) {
        this.currentNum = currentNum;
        this.recordsCount = recordsCount;
        this.currentOffset = currentOffset;
    }

    @Override
    public synchronized MainViewModel getCurrentSync() {
        final MainViewModel[] modelArr = new MainViewModel[1];
        final CountDownLatch signal = new CountDownLatch(1);
        checkCacheState(new CallBackChain<MainViewModel>() {
            @Override
            public void onSuccess(MainViewModel model) {
                modelArr[0] = model;
                signal.countDown();
            }
        });
        try {
            boolean result = signal.await(5, TimeUnit.MINUTES);
            if (modelArr[0] == null) {
                throw new BimException("Illegal state of synchronously invocation, model is null");
            } else if (!result) {
                throw new BimException("Illegal state of synchronously invocation, CountDownLatch" +
                        " waits more then 5 minutes");
            }
            return modelArr[0];
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void getCurrent(CallBackChain callBack) {
        checkCacheState(callBack);
    }

    @Override
    public synchronized void getNext(CallBackChain callBack) {
        ++currentNum;
        checkCacheState(callBack);
    }

    @Override
    public synchronized void getPrev(CallBackChain callBack) {
        --currentNum;
        checkCacheState(callBack);
    }

    private MainViewModel getFromCache(Integer num) {
        num = num - currentOffset;
        if (num < cache.size()) {
            final MainViewModel model = cache.get(num);
            model.setModelNum(currentNum.longValue());
            return model;
        }
        throw new BimException(String.format("Illegal cache state, wrong value wordNum %s", num));
    }


    private void checkCacheState(final CallBackChain<MainViewModel> callBack) {
        final SettingsViewModel sett = Settings.get();
        // ������ ��������
        final int portion = sett.getPortion().intValue();
        // ����� ������ � �������
        final boolean isPortionWorking = sett.isWorkWithPortion();
        final int volume;               // ���������� ����� ���-�� ������������ ����
        if ((portion > recordsCount) || !isPortionWorking) {
            volume = recordsCount;
        } else {
            volume = portion;
        }

        if (currentNum >= volume) {
            currentNum = 0;
        }
        if (currentNum < 0 ) {
            currentNum = volume - 1;
        }
        final int[] newOffset = new int[] {0};
        final int cacheMaxSize = sett.getCacheMaxSize().intValue();
        if (currentNum >= cacheMaxSize) {
            newOffset[0] = currentNum - currentNum % cacheMaxSize;
        } else {
            newOffset[0] = 0;
        }

        if (currentOffset != newOffset[0] || cache.isEmpty() || currentNum == 0) {
            // ��� ������������� � ������
            //  - �������� �� ����� �������� ������
            //  - ��� ������ ������� (��� ����)
            //  - ��� ����������� ������� ����� currentNum == 0




        } else {
            callBack.run(getFromCache(currentNum));
        }
    }

    private Collection<MainViewModel> toModels(List<BimItemModel> list) {
        Collection<MainViewModel> res = new ArrayList<MainViewModel>();
        for (BimItemModel model : list) {
            res.add(new MainViewModel(model));
        }
        return res;
    }

    @Override
    public synchronized Integer getCurrentNum() {
        return currentNum;
    }

    @Override
    public synchronized Integer getCurrentOffset() {
        return currentOffset;
    }

    @Override
    public synchronized List<MainViewModel> getCacheData() {
        return new ArrayList<MainViewModel>(cache);
    }

    @Override
    public synchronized Integer getRecordsCount() {
        return recordsCount;
    }

    @Override
    public synchronized void clearCache() {
        int size = cache.size();
        cache.clear();
        log.info(String.format("Cache cleaned, %s models released", size));
    }

    @Override
    public synchronized void addOrRenewModel(MainViewModel model) {
        int idx = cache.indexOf(model);
        if (idx >=0) {
            cache.remove(idx);
            cache.add(idx, model);
        } else {
            ++currentNum;
            cache.add(currentNum - currentOffset, model);
        }
    }
}
