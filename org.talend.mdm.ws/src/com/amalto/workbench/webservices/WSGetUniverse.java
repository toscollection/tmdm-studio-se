
package com.amalto.workbench.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSGetUniverse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSGetUniverse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsUniversePK" type="{urn-com-amalto-xtentis-webservice}WSUniversePK"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSGetUniverse", propOrder = {
    "wsUniversePK"
})
public class WSGetUniverse {

    @XmlElement(required = true)
    protected WSUniversePK wsUniversePK;

    /**
     * Default no-arg constructor
     * 
     */
    public WSGetUniverse() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public WSGetUniverse(final WSUniversePK wsUniversePK) {
        this.wsUniversePK = wsUniversePK;
    }

    /**
     * Gets the value of the wsUniversePK property.
     * 
     * @return
     *     possible object is
     *     {@link WSUniversePK }
     *     
     */
    public WSUniversePK getWsUniversePK() {
        return wsUniversePK;
    }

    /**
     * Sets the value of the wsUniversePK property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSUniversePK }
     *     
     */
    public void setWsUniversePK(WSUniversePK value) {
        this.wsUniversePK = value;
    }

}
