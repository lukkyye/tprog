
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtMedicamento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtMedicamento">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="posologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="indicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="vencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="usado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="costoMed" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="solMed" type="{http://server/}dtSolicitudMedicamento" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtMedicamento", propOrder = {
    "nombre",
    "posologia",
    "indicacion",
    "vencimiento",
    "usado",
    "costoMed",
    "solMed"
})
public class DtMedicamento {

    protected String nombre;
    protected String posologia;
    protected String indicacion;
    protected String vencimiento;
    protected boolean usado;
    protected float costoMed;
    protected DtSolicitudMedicamento solMed;

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
     * Obtiene el valor de la propiedad posologia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosologia() {
        return posologia;
    }

    /**
     * Define el valor de la propiedad posologia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosologia(String value) {
        this.posologia = value;
    }

    /**
     * Obtiene el valor de la propiedad indicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndicacion() {
        return indicacion;
    }

    /**
     * Define el valor de la propiedad indicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndicacion(String value) {
        this.indicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad vencimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVencimiento() {
        return vencimiento;
    }

    /**
     * Define el valor de la propiedad vencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVencimiento(String value) {
        this.vencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad usado.
     * 
     */
    public boolean isUsado() {
        return usado;
    }

    /**
     * Define el valor de la propiedad usado.
     * 
     */
    public void setUsado(boolean value) {
        this.usado = value;
    }

    /**
     * Obtiene el valor de la propiedad costoMed.
     * 
     */
    public float getCostoMed() {
        return costoMed;
    }

    /**
     * Define el valor de la propiedad costoMed.
     * 
     */
    public void setCostoMed(float value) {
        this.costoMed = value;
    }

    /**
     * Obtiene el valor de la propiedad solMed.
     * 
     * @return
     *     possible object is
     *     {@link DtSolicitudMedicamento }
     *     
     */
    public DtSolicitudMedicamento getSolMed() {
        return solMed;
    }

    /**
     * Define el valor de la propiedad solMed.
     * 
     * @param value
     *     allowed object is
     *     {@link DtSolicitudMedicamento }
     *     
     */
    public void setSolMed(DtSolicitudMedicamento value) {
        this.solMed = value;
    }

}
