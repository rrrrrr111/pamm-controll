package ru.roman.pammcontr.dev.dummy;

import ru.roman.pammcontr.gui.pane.main.MainViewModel;
import ru.roman.pammcontr.model.WordType;

/** @author Roman 22.12.12 13:41 */
public class TestHelper {



    public static MainViewModel createMainViewModel() {
        MainViewModel model = new MainViewModel();
        model.setRating(1L);
        model.setTextFaced("hello it's a question... sd fs sdfasdfas sd fsdfssd safsdf sdfds s fasd sd f sdfsdf sdfsdf sfsfsdf sf sf  sdfsd sdf sdf s dfsf");
        model.setTextShadowed("         ��� ���� �������...");
        model.setType(WordType.WORD.getOrdinal());
        return model;
    }
}
