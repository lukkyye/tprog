
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtSolicitudMedicamento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtSolicitudMedicamento">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nicknamePaciente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="entrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="usaBenef" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="costoMed" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtSolicitudMedicamento", propOrder = {
    "nicknamePaciente",
    "entrega",
    "direccion",
    "usaBenef",
    "costoMed"
})
public class DtSolicitudMedicamento {

    protected String nicknamePaciente;
    protected String entrega;
    protected String direccion;
    protected boolean usaBenef;
    protected float costoMed;

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

    /**
     * Obtiene el valor de la propiedad entrega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntrega() {
        return entrega;
    }

    /**
     * Define el valor de la propiedad entrega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntrega(String value) {
        this.entrega = value;
    }

    /**
     * Obtiene el valor de la propiedad direccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Define el valor de la propiedad direccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Obtiene el valor de la propiedad usaBenef.
     * 
     */
    public boolean isUsaBenef() {
        return usaBenef;
    }

    /**
     * Define el valor de la propiedad usaBenef.
     * 
     */
    public void setUsaBenef(boolean value) {
        this.usaBenef = value;
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

}
