package ru.roman.pammcontr.service.cache;

/** @author Roman 22.12.12 19:34 */
public class LocalCacheFactory {



    public static LocalCache createLocalCacheInstance(Long currentNum, Long recordsCount) {

        LocalCache instance = new LocalCacheImpl();
        instance.initCache(currentNum.intValue(), recordsCount.intValue(), 0);
        return instance;
    }

    public static LocalCache createLocalCacheInstance(LocalCache cache) {

        LocalCache instance = new LocalCacheImpl(cache);
        return instance;
    }


}
