
package ru.roman.pammcontr.service.gae.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerNewAndLoadSettingsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registerNewAndLoadSettingsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://dataws.service.server.bim.roman.ru/}registerNewAndLoadSettingsResp" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerNewAndLoadSettingsResponse", propOrder = {
    "_return"
})
public class RegisterNewAndLoadSettingsResponse {

    @XmlElement(name = "return")
    protected RegisterNewAndLoadSettingsResp _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterNewAndLoadSettingsResp }
     *     
     */
    public RegisterNewAndLoadSettingsResp getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterNewAndLoadSettingsResp }
     *     
     */
    public void setReturn(RegisterNewAndLoadSettingsResp value) {
        this._return = value;
    }

}
