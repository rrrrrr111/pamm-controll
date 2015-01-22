
package ru.roman.pammcontr.service.gae.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for saveRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="saveRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://dataws.service.server.bim.roman.ru/}abstractRequest">
 *       &lt;sequence>
 *         &lt;element name="model" type="{http://dataws.service.server.bim.roman.ru/}bimItemModel" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveRequest", propOrder = {
    "model"
})
public class SaveRequest
    extends AbstractRequest
{

    protected BimItemModel model;

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link BimItemModel }
     *     
     */
    public BimItemModel getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link BimItemModel }
     *     
     */
    public void setModel(BimItemModel value) {
        this.model = value;
    }

}
