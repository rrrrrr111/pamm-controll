
package ru.roman.pammcontr.service.gae.wsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for userSettingsModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userSettingsModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cacheMaxSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="categories" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="currentNum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="disabilityDuration" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="editDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="facedLangId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="login" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lookAndFeel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opacity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="previewDuration" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="previewInterval" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ratings" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="recordsCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="shadowedLangId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="sortingDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortingField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscribed" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="types" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="workWithPortion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userSettingsModel", propOrder = {
    "cacheMaxSize",
    "categories",
    "currentNum",
    "disabilityDuration",
    "editDate",
    "facedLangId",
    "id",
    "login",
    "lookAndFeel",
    "opacity",
    "password",
    "portion",
    "previewDuration",
    "previewInterval",
    "ratings",
    "recordsCount",
    "shadowedLangId",
    "sortingDirection",
    "sortingField",
    "subscribed",
    "types",
    "workWithPortion"
})
public class UserSettingsModel {

    protected Long cacheMaxSize;
    @XmlElement(nillable = true)
    protected List<Long> categories;
    protected Long currentNum;
    protected Double disabilityDuration;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar editDate;
    protected Long facedLangId;
    protected Long id;
    protected String login;
    protected String lookAndFeel;
    protected Double opacity;
    protected String password;
    protected Long portion;
    protected Double previewDuration;
    protected Double previewInterval;
    @XmlElement(nillable = true)
    protected List<Integer> ratings;
    protected Long recordsCount;
    protected Long shadowedLangId;
    protected String sortingDirection;
    protected String sortingField;
    @XmlElement(nillable = true)
    protected List<Long> subscribed;
    @XmlElement(nillable = true)
    protected List<Long> types;
    protected boolean workWithPortion;

    /**
     * Gets the value of the cacheMaxSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCacheMaxSize() {
        return cacheMaxSize;
    }

    /**
     * Sets the value of the cacheMaxSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCacheMaxSize(Long value) {
        this.cacheMaxSize = value;
    }

    /**
     * Gets the value of the categories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCategories() {
        if (categories == null) {
            categories = new ArrayList<Long>();
        }
        return this.categories;
    }

    /**
     * Gets the value of the currentNum property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCurrentNum() {
        return currentNum;
    }

    /**
     * Sets the value of the currentNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCurrentNum(Long value) {
        this.currentNum = value;
    }

    /**
     * Gets the value of the disabilityDuration property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDisabilityDuration() {
        return disabilityDuration;
    }

    /**
     * Sets the value of the disabilityDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDisabilityDuration(Double value) {
        this.disabilityDuration = value;
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
     * Gets the value of the login property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the lookAndFeel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLookAndFeel() {
        return lookAndFeel;
    }

    /**
     * Sets the value of the lookAndFeel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLookAndFeel(String value) {
        this.lookAndFeel = value;
    }

    /**
     * Gets the value of the opacity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOpacity() {
        return opacity;
    }

    /**
     * Sets the value of the opacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOpacity(Double value) {
        this.opacity = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the portion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPortion() {
        return portion;
    }

    /**
     * Sets the value of the portion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPortion(Long value) {
        this.portion = value;
    }

    /**
     * Gets the value of the previewDuration property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPreviewDuration() {
        return previewDuration;
    }

    /**
     * Sets the value of the previewDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPreviewDuration(Double value) {
        this.previewDuration = value;
    }

    /**
     * Gets the value of the previewInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPreviewInterval() {
        return previewInterval;
    }

    /**
     * Sets the value of the previewInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPreviewInterval(Double value) {
        this.previewInterval = value;
    }

    /**
     * Gets the value of the ratings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ratings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRatings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getRatings() {
        if (ratings == null) {
            ratings = new ArrayList<Integer>();
        }
        return this.ratings;
    }

    /**
     * Gets the value of the recordsCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecordsCount() {
        return recordsCount;
    }

    /**
     * Sets the value of the recordsCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecordsCount(Long value) {
        this.recordsCount = value;
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
     * Gets the value of the sortingDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortingDirection() {
        return sortingDirection;
    }

    /**
     * Sets the value of the sortingDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortingDirection(String value) {
        this.sortingDirection = value;
    }

    /**
     * Gets the value of the sortingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortingField() {
        return sortingField;
    }

    /**
     * Sets the value of the sortingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortingField(String value) {
        this.sortingField = value;
    }

    /**
     * Gets the value of the subscribed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscribed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscribed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSubscribed() {
        if (subscribed == null) {
            subscribed = new ArrayList<Long>();
        }
        return this.subscribed;
    }

    /**
     * Gets the value of the types property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the types property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getTypes() {
        if (types == null) {
            types = new ArrayList<Long>();
        }
        return this.types;
    }

    /**
     * Gets the value of the workWithPortion property.
     * 
     */
    public boolean isWorkWithPortion() {
        return workWithPortion;
    }

    /**
     * Sets the value of the workWithPortion property.
     * 
     */
    public void setWorkWithPortion(boolean value) {
        this.workWithPortion = value;
    }

}
