
package ru.roman.pammcontr.service.gae.wsclient;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for saveStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="saveStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CREATED_NEW"/>
 *     &lt;enumeration value="ALREADY_EXIST_SKIPPED"/>
 *     &lt;enumeration value="ALREADY_EXIST_MERGED"/>
 *     &lt;enumeration value="EDITED"/>
 *     &lt;enumeration value="EDITED_OLD_MERGED_AND_DELETED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "saveStatus")
@XmlEnum
public enum SaveStatus {

    CREATED_NEW,
    ALREADY_EXIST_SKIPPED,
    ALREADY_EXIST_MERGED,
    EDITED,
    EDITED_OLD_MERGED_AND_DELETED;

    public String value() {
        return name();
    }

    public static SaveStatus fromValue(String v) {
        return valueOf(v);
    }

}
