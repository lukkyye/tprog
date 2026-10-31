
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtMedico complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtMedico">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nroReg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="mesLic" type="{http://server/}tipoMes" minOccurs="0"/>
 *         <element name="porcentajeCost" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtMedico", propOrder = {
    "nickname",
    "nroReg",
    "mesLic",
    "porcentajeCost",
    "link"
})
public class DtMedico {

    protected String nickname;
    protected String nroReg;
    @XmlSchemaType(name = "string")
    protected TipoMes mesLic;
    protected Integer porcentajeCost;
    protected String link;

    /**
     * Obtiene el valor de la propiedad nickname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Define el valor de la propiedad nickname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Obtiene el valor de la propiedad nroReg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroReg() {
        return nroReg;
    }

    /**
     * Define el valor de la propiedad nroReg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroReg(String value) {
        this.nroReg = value;
    }

    /**
     * Obtiene el valor de la propiedad mesLic.
     * 
     * @return
     *     possible object is
     *     {@link TipoMes }
     *     
     */
    public TipoMes getMesLic() {
        return mesLic;
    }

    /**
     * Define el valor de la propiedad mesLic.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMes }
     *     
     */
    public void setMesLic(TipoMes value) {
        this.mesLic = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeCost.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPorcentajeCost() {
        return porcentajeCost;
    }

    /**
     * Define el valor de la propiedad porcentajeCost.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPorcentajeCost(Integer value) {
        this.porcentajeCost = value;
    }

    /**
     * Obtiene el valor de la propiedad link.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Define el valor de la propiedad link.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

}
