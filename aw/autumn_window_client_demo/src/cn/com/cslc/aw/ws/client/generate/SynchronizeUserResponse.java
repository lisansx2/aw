
package cn.com.cslc.aw.ws.client.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Head" type="{http://www.cslc.com.cn/aw/ws}ResponseHead"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "SynchronizeUserResponse")
public class SynchronizeUserResponse {

    @XmlElement(name = "Head", required = true)
    protected ResponseHead head;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseHead }
     *     
     */
    public ResponseHead getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseHead }
     *     
     */
    public void setHead(ResponseHead value) {
        this.head = value;
    }

}
