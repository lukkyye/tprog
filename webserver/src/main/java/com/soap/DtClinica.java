
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtClinica complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtClinica">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="dir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="web" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cTicket" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="cConsulta" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtClinica", propOrder = {
    "nombre",
    "desc",
    "dir",
    "web",
    "cTicket",
    "cConsulta"
})
public class DtClinica {

    protected String nombre;
    protected String desc;
    protected String dir;
    protected String web;
    protected float cTicket;
    protected float cConsulta;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad desc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Define el valor de la propiedad desc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Obtiene el valor de la propiedad dir.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDir() {
        return dir;
    }

    /**
     * Define el valor de la propiedad dir.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDir(String value) {
        this.dir = value;
    }

    /**
     * Obtiene el valor de la propiedad web.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeb() {
        return web;
    }

    /**
     * Define el valor de la propiedad web.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeb(String value) {
        this.web = value;
    }

    /**
     * Obtiene el valor de la propiedad cTicket.
     * 
     */
    public float getCTicket() {
        return cTicket;
    }

    /**
     * Define el valor de la propiedad cTicket.
     * 
     */
    public void setCTicket(float value) {
        this.cTicket = value;
    }

    /**
     * Obtiene el valor de la propiedad cConsulta.
     * 
     */
    public float getCConsulta() {
        return cConsulta;
    }

    /**
     * Define el valor de la propiedad cConsulta.
     * 
     */
    public void setCConsulta(float value) {
        this.cConsulta = value;
    }

}
