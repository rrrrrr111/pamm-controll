package ru.roman.pammcontr.service.config.writer;

import javax.xml.transform.sax.TransformerHandler;

/**
 * @author Roman 15.09.12 22:58
 */
public interface XmlWriter<T> {

    void write(TransformerHandler handler, T data) throws Exception;
}
