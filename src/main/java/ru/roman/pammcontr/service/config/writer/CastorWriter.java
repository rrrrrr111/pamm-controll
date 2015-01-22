package ru.roman.pammcontr.service.config.writer;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.XMLContext;
import ru.roman.pammcontr.service.config.CastorConfigServiceImpl;

import javax.xml.transform.sax.TransformerHandler;

/** @author Roman 26.01.13 0:32 */
public class CastorWriter implements XmlWriter {

    @Override
    public void write(TransformerHandler handler, Object data) throws Exception {
        XMLContext context = new XMLContext();
        context.addMapping(CastorConfigServiceImpl.getMapping());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setContentHandler(handler);
        marshaller.marshal(data);

    }
}
