
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtDiaConsulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtDiaConsulta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nomD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="dia" type="{http://server/}dtDiaSemana" minOccurs="0"/>
 *         <element name="horaIni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="costoD" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtDiaConsulta", propOrder = {
    "nomD",
    "descD",
    "dia",
    "horaIni",
    "costoD"
})
public class DtDiaConsulta {

    protected String nomD;
    protected String descD;
    @XmlSchemaType(name = "string")
    protected DtDiaSemana dia;
    protected String horaIni;
    protected float costoD;

    /**
     * Obtiene el valor de la propiedad nomD.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomD() {
        return nomD;
    }

    /**
     * Define el valor de la propiedad nomD.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomD(String value) {
        this.nomD = value;
    }

    /**
     * Obtiene el valor de la propiedad descD.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescD() {
        return descD;
    }

    /**
     * Define el valor de la propiedad descD.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescD(String value) {
        this.descD = value;
    }

    /**
     * Obtiene el valor de la propiedad dia.
     * 
     * @return
     *     possible object is
     *     {@link DtDiaSemana }
     *     
     */
    public DtDiaSemana getDia() {
        return dia;
    }

    /**
     * Define el valor de la propiedad dia.
     * 
     * @param value
     *     allowed object is
     *     {@link DtDiaSemana }
     *     
     */
    public void setDia(DtDiaSemana value) {
        this.dia = value;
    }

    /**
     * Obtiene el valor de la propiedad horaIni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraIni() {
        return horaIni;
    }

    /**
     * Define el valor de la propiedad horaIni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraIni(String value) {
        this.horaIni = value;
    }

    /**
     * Obtiene el valor de la propiedad costoD.
     * 
     */
    public float getCostoD() {
        return costoD;
    }

    /**
     * Define el valor de la propiedad costoD.
     * 
     */
    public void setCostoD(float value) {
        this.costoD = value;
    }

}
