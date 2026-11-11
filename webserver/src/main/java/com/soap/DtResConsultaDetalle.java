
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtResConsultaDetalle complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtResConsultaDetalle">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaRes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="numReserva" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="aplicaBenef" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="medicoC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nomC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="salaC" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="cantNumC" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costoC" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="costoR" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="nicknamePaciente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtResConsultaDetalle", propOrder = {
    "fechaRes",
    "numReserva",
    "aplicaBenef",
    "medicoC",
    "nomC",
    "fechaC",
    "salaC",
    "cantNumC",
    "costoC",
    "costoR",
    "nicknamePaciente"
})
public class DtResConsultaDetalle {

    protected String fechaRes;
    protected int numReserva;
    protected boolean aplicaBenef;
    protected String medicoC;
    protected String nomC;
    protected String fechaC;
    protected int salaC;
    protected int cantNumC;
    protected float costoC;
    protected float costoR;
    protected String nicknamePaciente;

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
     * Obtiene el valor de la propiedad medicoC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicoC() {
        return medicoC;
    }

    /**
     * Define el valor de la propiedad medicoC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicoC(String value) {
        this.medicoC = value;
    }

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
     * Obtiene el valor de la propiedad fechaC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaC() {
        return fechaC;
    }

    /**
     * Define el valor de la propiedad fechaC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaC(String value) {
        this.fechaC = value;
    }

    /**
     * Obtiene el valor de la propiedad salaC.
     * 
     */
    public int getSalaC() {
        return salaC;
    }

    /**
     * Define el valor de la propiedad salaC.
     * 
     */
    public void setSalaC(int value) {
        this.salaC = value;
    }

    /**
     * Obtiene el valor de la propiedad cantNumC.
     * 
     */
    public int getCantNumC() {
        return cantNumC;
    }

    /**
     * Define el valor de la propiedad cantNumC.
     * 
     */
    public void setCantNumC(int value) {
        this.cantNumC = value;
    }

    /**
     * Obtiene el valor de la propiedad costoC.
     * 
     */
    public float getCostoC() {
        return costoC;
    }

    /**
     * Define el valor de la propiedad costoC.
     * 
     */
    public void setCostoC(float value) {
        this.costoC = value;
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

    /**
     * Obtiene el valor de la propiedad nicknamePaciente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknamePaciente() {
        return nicknamePaciente;
    }

    /**
     * Define el valor de la propiedad nicknamePaciente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknamePaciente(String value) {
        this.nicknamePaciente = value;
    }

}
