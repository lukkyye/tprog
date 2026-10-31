
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtResConsulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtResConsulta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nomC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaRes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="numReserva" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="aplicaBenef" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="costoR" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtResConsulta", propOrder = {
    "nomC",
    "fechaRes",
    "numReserva",
    "aplicaBenef",
    "costoR"
})
public class DtResConsulta {

    protected String nomC;
    protected String fechaRes;
    protected int numReserva;
    protected boolean aplicaBenef;
    protected float costoR;

    /**
     * Obtiene el valor de la propiedad nomC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomC() {
        return nomC;
    }

    /**
     * Define el valor de la propiedad nomC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomC(String value) {
        this.nomC = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaRes() {
        return fechaRes;
    }

    /**
     * Define el valor de la propiedad fechaRes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaRes(String value) {
        this.fechaRes = value;
    }

    /**
     * Obtiene el valor de la propiedad numReserva.
     * 
     */
    public int getNumReserva() {
        return numReserva;
    }

    /**
     * Define el valor de la propiedad numReserva.
     * 
     */
    public void setNumReserva(int value) {
        this.numReserva = value;
    }

    /**
     * Obtiene el valor de la propiedad aplicaBenef.
     * 
     */
    public boolean isAplicaBenef() {
        return aplicaBenef;
    }

    /**
     * Define el valor de la propiedad aplicaBenef.
     * 
     */
    public void setAplicaBenef(boolean value) {
        this.aplicaBenef = value;
    }

    /**
     * Obtiene el valor de la propiedad costoR.
     * 
     */
    public float getCostoR() {
        return costoR;
    }

    /**
     * Define el valor de la propiedad costoR.
     * 
     */
    public void setCostoR(float value) {
        this.costoR = value;
    }

}
