
package cn.com.cslc.aw.ws.client.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Head" type="{http://www.cslc.com.cn/aw/ws}RequestHead"/>
 *         &lt;element name="UserName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MobilePhoneNumber">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0-9]{11}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OperateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UserFullName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="UmpUserId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="2000000000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CredentialNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CredentialTypeCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProvinceNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CityNo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AgentNo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="isEnable">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IsDeleted">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
@XmlRootElement(name = "SynchronizeUserRequest")
public class SynchronizeUserRequest {

    @XmlElement(name = "Head", required = true)
    protected RequestHead head;
    @XmlElement(name = "UserName", required = true)
    protected String userName;
    @XmlElement(name = "MobilePhoneNumber", required = true)
    protected String mobilePhoneNumber;
    @XmlElement(name = "OperateDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operateDate;
    @XmlElement(name = "UserFullName", required = true)
    protected String userFullName;
    @XmlElement(name = "UmpUserId")
    protected String umpUserId;
    @XmlElement(name = "CredentialNo", required = true)
    protected String credentialNo;
    @XmlElement(name = "CredentialTypeCode", required = true)
    protected String credentialTypeCode;
    @XmlElement(name = "ProvinceNo", required = true)
    protected String provinceNo;
    @XmlElement(name = "CityNo")
    protected String cityNo;
    @XmlElement(name = "AgentNo")
    protected String agentNo;
    @XmlElement(required = true)
    protected String isEnable;
    @XmlElement(name = "IsDeleted", required = true)
    protected String isDeleted;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link RequestHead }
     *     
     */
    public RequestHead getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHead }
     *     
     */
    public void setHead(RequestHead value) {
        this.head = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the mobilePhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    /**
     * Sets the value of the mobilePhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilePhoneNumber(String value) {
        this.mobilePhoneNumber = value;
    }

    /**
     * Gets the value of the operateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperateDate() {
        return operateDate;
    }

    /**
     * Sets the value of the operateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperateDate(XMLGregorianCalendar value) {
        this.operateDate = value;
    }

    /**
     * Gets the value of the userFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserFullName() {
        return userFullName;
    }

    /**
     * Sets the value of the userFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserFullName(String value) {
        this.userFullName = value;
    }

    /**
     * Gets the value of the umpUserId property.
     * 
     */
    public String getUmpUserId() {
        return umpUserId;
    }

    /**
     * Sets the value of the umpUserId property.
     * 
     */
    public void setUmpUserId(String value) {
        this.umpUserId = value;
    }

    /**
     * Gets the value of the credentialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredentialNo() {
        return credentialNo;
    }

    /**
     * Sets the value of the credentialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredentialNo(String value) {
        this.credentialNo = value;
    }

    /**
     * Gets the value of the credentialTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredentialTypeCode() {
        return credentialTypeCode;
    }

    /**
     * Sets the value of the credentialTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredentialTypeCode(String value) {
        this.credentialTypeCode = value;
    }

    /**
     * Gets the value of the provinceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceNo() {
        return provinceNo;
    }

    /**
     * Sets the value of the provinceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceNo(String value) {
        this.provinceNo = value;
    }

    /**
     * Gets the value of the cityNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityNo() {
        return cityNo;
    }

    /**
     * Sets the value of the cityNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityNo(String value) {
        this.cityNo = value;
    }

    /**
     * Gets the value of the agentNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentNo() {
        return agentNo;
    }

    /**
     * Sets the value of the agentNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentNo(String value) {
        this.agentNo = value;
    }

    /**
     * Gets the value of the isEnable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * Sets the value of the isEnable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEnable(String value) {
        this.isEnable = value;
    }

    /**
     * Gets the value of the isDeleted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the value of the isDeleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsDeleted(String value) {
        this.isDeleted = value;
    }

}
