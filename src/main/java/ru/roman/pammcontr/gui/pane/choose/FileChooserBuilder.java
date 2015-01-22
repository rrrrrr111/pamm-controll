package ru.roman.pammcontr.gui.pane.choose;

import java.util.ArrayList;
import java.util.List;

/** @author Roman 02.06.13 10:55 */
public class FileChooserBuilder {

    List<FilterProperties> fileFilters = new ArrayList<FilterProperties>(1);
    String dialogTitle;

    public FileChooserBuilder(String filterFilesName, String filterFilesExtension, String dialogTitle) {
        if (filterFilesName != null && filterFilesExtension != null) {
            this.fileFilters.add(new FilterProperties(filterFilesName, filterFilesExtension));
        }
        this.dialogTitle = dialogTitle;
    }

    public FileChooserBuilder(String dialogTitle) {
        this(null, null, dialogTitle);
    }

    public FileChooserBuilder appendDialogTitle(String dialogTitle) {
        if (this.dialogTitle != null) {
            throw new RuntimeException("Dialog title already have set");
        }
        if (dialogTitle == null) {
            throw new RuntimeException("Dialog title can't be null");
        }
        this.dialogTitle = dialogTitle;
        return this;
    }

    public FileChooserBuilder appendExtensionFilter(String filterFilesName, String filterFilesExtension) {
        if (filterFilesName != null && filterFilesExtension != null) {
            this.fileFilters.add(new FilterProperties(filterFilesName, filterFilesExtension));
            return this;
        }
        throw new RuntimeException("Parameters filterFilesName and filterFilesExtension can't be null");
    }

    public FileChooserBuilder appendRegExpFilter(String filterFilesName, String filesRegExp) {
        if (filterFilesName != null && filesRegExp != null) {
            final FilterProperties properties = new FilterProperties(filterFilesName);
            properties.filesRegExp = filesRegExp;
            this.fileFilters.add(properties);
            return this;
        }
        throw new RuntimeException("Parameters filterFilesName and filesRegExp can't be null");
    }


    public FileChooser toChooser() {
        return new FileChooser(this);
    }

    static class FilterProperties {
        String filesName;
        String filesExtension;
        String filesRegExp;

        FilterProperties(String filesName, String filesExtension) {
            this.filesName = filesName;
            this.filesExtension = filesExtension;
        }

        FilterProperties(String filesName) {
            this.filesName = filesName;
        }
    }
}
