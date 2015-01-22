
package ru.roman.pammcontr.service.gae.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for bimItemModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bimItemModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="editDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="facedLangId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="modelNum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="shadowedLangId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="textFaced" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textShadowed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bimItemModel", propOrder = {
    "category",
    "editDate",
    "facedLangId",
    "id",
    "modelNum",
    "owner",
    "rating",
    "shadowedLangId",
    "textFaced",
    "textShadowed",
    "type"
})
public class BimItemModel {

    protected Long category;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar editDate;
    protected Long facedLangId;
    protected Long id;
    protected Long modelNum;
    protected Long owner;
    protected Long rating;
    protected Long shadowedLangId;
    protected String textFaced;
    protected String textShadowed;
    protected Long type;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCategory(Long value) {
        this.category = value;
    }

    /**
     * Gets the value of the editDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEditDate() {
        return editDate;
    }

    /**
     * Sets the value of the editDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEditDate(XMLGregorianCalendar value) {
        this.editDate = value;
    }

    /**
     * Gets the value of the facedLangId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFacedLangId() {
        return facedLangId;
    }

    /**
     * Sets the value of the facedLangId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFacedLangId(Long value) {
        this.facedLangId = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the modelNum property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getModelNum() {
        return modelNum;
    }

    /**
     * Sets the value of the modelNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setModelNum(Long value) {
        this.modelNum = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOwner(Long value) {
        this.owner = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRating(Long value) {
        this.rating = value;
    }

    /**
     * Gets the value of the shadowedLangId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getShadowedLangId() {
        return shadowedLangId;
    }

    /**
     * Sets the value of the shadowedLangId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setShadowedLangId(Long value) {
        this.shadowedLangId = value;
    }

    /**
     * Gets the value of the textFaced property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextFaced() {
        return textFaced;
    }

    /**
     * Sets the value of the textFaced property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextFaced(String value) {
        this.textFaced = value;
    }

    /**
     * Gets the value of the textShadowed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextShadowed() {
        return textShadowed;
    }

    /**
     * Sets the value of the textShadowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextShadowed(String value) {
        this.textShadowed = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setType(Long value) {
        this.type = value;
    }

}
