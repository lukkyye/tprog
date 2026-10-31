
package com.soap;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPaciente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPaciente">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaNac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantOrdenesAcum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="cantTicketAcum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="antSalud" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="doc" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaIngreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPaciente", propOrder = {
    "nickname",
    "fechaNac",
    "cantOrdenesAcum",
    "cantTicketAcum",
    "antSalud",
    "doc",
    "fechaIngreso"
})
public class DtPaciente {

    protected String nickname;
    protected String fechaNac;
    protected int cantOrdenesAcum;
    protected int cantTicketAcum;
    @XmlElement(nillable = true)
    protected List<String> antSalud;
    protected int doc;
    protected String fechaIngreso;

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
     * Obtiene el valor de la propiedad fechaNac.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNac() {
        return fechaNac;
    }

    /**
     * Define el valor de la propiedad fechaNac.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNac(String value) {
        this.fechaNac = value;
    }

    /**
     * Obtiene el valor de la propiedad cantOrdenesAcum.
     * 
     */
    public int getCantOrdenesAcum() {
        return cantOrdenesAcum;
    }

    /**
     * Define el valor de la propiedad cantOrdenesAcum.
     * 
     */
    public void setCantOrdenesAcum(int value) {
        this.cantOrdenesAcum = value;
    }

    /**
     * Obtiene el valor de la propiedad cantTicketAcum.
     * 
     */
    public int getCantTicketAcum() {
        return cantTicketAcum;
    }

    /**
     * Define el valor de la propiedad cantTicketAcum.
     * 
     */
    public void setCantTicketAcum(int value) {
        this.cantTicketAcum = value;
    }

    /**
     * Gets the value of the antSalud property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the antSalud property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAntSalud().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the antSalud property.
     */
    public List<String> getAntSalud() {
        if (antSalud == null) {
            antSalud = new ArrayList<>();
        }
        return this.antSalud;
    }

    /**
     * Obtiene el valor de la propiedad doc.
     * 
     */
    public int getDoc() {
        return doc;
    }

    /**
     * Define el valor de la propiedad doc.
     * 
     */
    public void setDoc(int value) {
        this.doc = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaIngreso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Define el valor de la propiedad fechaIngreso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaIngreso(String value) {
        this.fechaIngreso = value;
    }

}
