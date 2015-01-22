package ru.roman.pammcontr.gui.pane.choose;


import org.apache.commons.lang3.time.FastDateFormat;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Date;

public class FileChooser {

    private final JFileChooser fc = new JFileChooser();
    private static final FastDateFormat FILE_NAME_FORMAT = FastDateFormat.getInstance("yyyy.MM.dd");

    private static int reportCounter = 1;

    FileChooser(final FileChooserBuilder builder) {
        if (builder.dialogTitle != null) {
            fc.setDialogTitle(builder.dialogTitle);
        }
        FileFilter filter = null;
        if (builder.fileFilters.isEmpty()) {
            filter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return true;
                }
                @Override
                public String getDescription() {
                    return "All files (*.*)";
                }
            };
            fc.addChoosableFileFilter(filter);
        } else {
            for (final FileChooserBuilder.FilterProperties filterProp : builder.fileFilters) {
                if (filterProp.filesExtension != null) {
                    filter = new FileFilter() {
                        @Override
                        public boolean accept(File f) {
                            return !f.isFile() || (f.getName().toLowerCase().endsWith(
                                    "." + filterProp.filesExtension.toLowerCase()));
                        }
                        @Override
                        public String getDescription() {
                            return filterProp.filesName;
                        }
                    };
                } else {
                    filter = new FileFilter() {
                        @Override
                        public boolean accept(File f) {
                            return !f.isFile() || (f.getName().toLowerCase().matches(filterProp.filesRegExp));
                        }
                        @Override
                        public String getDescription() {
                            return filterProp.filesName;
                        }
                    };
                }
                fc.addChoosableFileFilter(filter);
            }
        }
        fc.setFileFilter(filter);
    }

    public File showSelectFileDialog() {
        int returnVal = fc.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == returnVal) {
            final File selectedFile = fc.getSelectedFile();
            setCurrentDirectory(selectedFile.getParentFile());
            return selectedFile;
        }
        return null;
    }

    public File saveFile() {
        String str = "Report-" + reportCounter + "_" + FILE_NAME_FORMAT.format(new Date()) + ".xls";
        fc.setSelectedFile(new File(str));

        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            reportCounter++;
            return file;
        }
        return null;
    }


    public void setDialogTitle(String dialogTitle) {
        fc.setDialogTitle(dialogTitle);
    }

    public void setCurrentDirectory(File dir) {
        fc.setCurrentDirectory(dir);
    }
}
