
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtValoracionConsulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtValoracionConsulta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombreConsulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaConsulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="promedioConsulta" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         <element name="valoracionPaciente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtValoracionConsulta", propOrder = {
    "nombreConsulta",
    "fechaConsulta",
    "promedioConsulta",
    "valoracionPaciente"
})
public class DtValoracionConsulta {

    protected String nombreConsulta;
    protected String fechaConsulta;
    protected Float promedioConsulta;
    protected int valoracionPaciente;

    /**
     * Obtiene el valor de la propiedad nombreConsulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreConsulta() {
        return nombreConsulta;
    }

    /**
     * Define el valor de la propiedad nombreConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreConsulta(String value) {
        this.nombreConsulta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaConsulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * Define el valor de la propiedad fechaConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaConsulta(String value) {
        this.fechaConsulta = value;
    }

    /**
     * Obtiene el valor de la propiedad promedioConsulta.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPromedioConsulta() {
        return promedioConsulta;
    }

    /**
     * Define el valor de la propiedad promedioConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPromedioConsulta(Float value) {
        this.promedioConsulta = value;
    }

    /**
     * Obtiene el valor de la propiedad valoracionPaciente.
     * 
     */
    public int getValoracionPaciente() {
        return valoracionPaciente;
    }

    /**
     * Define el valor de la propiedad valoracionPaciente.
     * 
     */
    public void setValoracionPaciente(int value) {
        this.valoracionPaciente = value;
    }

}
