package ru.roman.pammcontr.service.file.textupload;

import java.io.IOException;
import java.util.List;

/** @author Roman 24.08.13 11:26 */
public interface TextUploadService {


    TextUploadServiceImpl.ParsedTextDto parseFile() throws IOException;

    void uploadList(List<String> list);

    void startUpload();
}
