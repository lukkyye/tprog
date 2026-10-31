
package com.soap;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtConsultaDetalle complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtConsultaDetalle">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nomC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="medico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="sala" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="cantNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="reservas" type="{http://server/}dtResConsulta" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="costoC" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         <element name="estado" type="{http://server/}estadoConsulta" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtConsultaDetalle", propOrder = {
    "nomC",
    "medico",
    "fechaC",
    "sala",
    "cantNum",
    "reservas",
    "costoC",
    "estado"
})
public class DtConsultaDetalle {

    protected String nomC;
    protected String medico;
    protected String fechaC;
    protected int sala;
    protected int cantNum;
    @XmlElement(nillable = true)
    protected List<DtResConsulta> reservas;
    protected Float costoC;
    @XmlSchemaType(name = "string")
    protected EstadoConsulta estado;

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
     * Obtiene el valor de la propiedad medico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedico() {
        return medico;
    }

    /**
     * Define el valor de la propiedad medico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedico(String value) {
        this.medico = value;
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
     * Obtiene el valor de la propiedad sala.
     * 
     */
    public int getSala() {
        return sala;
    }

    /**
     * Define el valor de la propiedad sala.
     * 
     */
    public void setSala(int value) {
        this.sala = value;
    }

    /**
     * Obtiene el valor de la propiedad cantNum.
     * 
     */
    public int getCantNum() {
        return cantNum;
    }

    /**
     * Define el valor de la propiedad cantNum.
     * 
     */
    public void setCantNum(int value) {
        this.cantNum = value;
    }

    /**
     * Gets the value of the reservas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the reservas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtResConsulta }
     * 
     * 
     * @return
     *     The value of the reservas property.
     */
    public List<DtResConsulta> getReservas() {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }
        return this.reservas;
    }

    /**
     * Obtiene el valor de la propiedad costoC.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getCostoC() {
        return costoC;
    }

    /**
     * Define el valor de la propiedad costoC.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setCostoC(Float value) {
        this.costoC = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoConsulta }
     *     
     */
    public EstadoConsulta getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoConsulta }
     *     
     */
    public void setEstado(EstadoConsulta value) {
        this.estado = value;
    }

}
