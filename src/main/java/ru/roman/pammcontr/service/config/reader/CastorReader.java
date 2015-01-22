package ru.roman.pammcontr.service.config.reader;

import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLContext;
import org.w3c.dom.Document;
import ru.roman.pammcontr.service.config.CastorConfigServiceImpl;

/** @author Roman 26.01.13 0:36 */
public class CastorReader implements XmlReader {

    private Class clazz;

    public CastorReader(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object read(Document doc) throws Exception {
        XMLContext context = new XMLContext();
        context.addMapping(CastorConfigServiceImpl.getMapping());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setClass(clazz);

        return unmarshaller.unmarshal(doc);
    }
}
