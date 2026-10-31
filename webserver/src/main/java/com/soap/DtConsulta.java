
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtConsulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtConsulta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nomC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="sala" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="cantNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costoC" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
@XmlType(name = "dtConsulta", propOrder = {
    "nomC",
    "fechaC",
    "sala",
    "cantNum",
    "costoC",
    "estado"
})
public class DtConsulta {

    protected String nomC;
    protected String fechaC;
    protected int sala;
    protected int cantNum;
    protected float costoC;
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
