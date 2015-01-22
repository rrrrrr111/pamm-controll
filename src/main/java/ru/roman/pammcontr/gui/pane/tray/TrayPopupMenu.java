package ru.roman.pammcontr.gui.pane.tray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/** @author Roman 23.03.13 10:39 */
public class TrayPopupMenu extends PopupMenu {

    private final TrayController controller;

    public TrayPopupMenu(TrayController contr) throws HeadlessException {
        super();
        this.controller = contr;


        // Create a pop-up menu components
        final MenuItem aboutItem = new MenuItem("About");
        final MenuItem infoItem = new MenuItem();
        infoItem.setEnabled(false);
        final CheckboxMenuItem cbDisabled = new CheckboxMenuItem("Disabled");
        final MenuItem exitItem = new MenuItem("Exit");

        //Add components to pop-up menu
        add(aboutItem);
        addSeparator();
        add(cbDisabled);
        addSeparator();
        add(exitItem);

        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.onShowInfo();
            }
        });

        cbDisabled.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                controller.onStateChanged(e);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.onExit();
            }
        });

    }
}
