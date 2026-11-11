
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtResAsistio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtResAsistio">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nomConsulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="numRes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="nicknamePaciente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="asistio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="diagnostico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtResAsistio", propOrder = {
    "nomConsulta",
    "numRes",
    "nicknamePaciente",
    "asistio",
    "motivo",
    "diagnostico"
})
public class DtResAsistio {

    protected String nomConsulta;
    protected int numRes;
    protected String nicknamePaciente;
    protected boolean asistio;
    protected String motivo;
    protected String diagnostico;

    /**
     * Obtiene el valor de la propiedad nomConsulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomConsulta() {
        return nomConsulta;
    }

    /**
     * Define el valor de la propiedad nomConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomConsulta(String value) {
        this.nomConsulta = value;
    }

    /**
     * Obtiene el valor de la propiedad numRes.
     * 
     */
    public int getNumRes() {
        return numRes;
    }

    /**
     * Define el valor de la propiedad numRes.
     * 
     */
    public void setNumRes(int value) {
        this.numRes = value;
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

    /**
     * Obtiene el valor de la propiedad asistio.
     * 
     */
    public boolean isAsistio() {
        return asistio;
    }

    /**
     * Define el valor de la propiedad asistio.
     * 
     */
    public void setAsistio(boolean value) {
        this.asistio = value;
    }

    /**
     * Obtiene el valor de la propiedad motivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Define el valor de la propiedad motivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Obtiene el valor de la propiedad diagnostico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Define el valor de la propiedad diagnostico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnostico(String value) {
        this.diagnostico = value;
    }

}
