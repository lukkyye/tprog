
package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtBeneficioAnual complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtBeneficioAnual">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="anio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="cantOrdenes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="cantTicket" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="ordenesUsadas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="ticketUsados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtBeneficioAnual", propOrder = {
    "anio",
    "cantOrdenes",
    "cantTicket",
    "ordenesUsadas",
    "ticketUsados"
})
public class DtBeneficioAnual {

    protected int anio;
    protected int cantOrdenes;
    protected int cantTicket;
    protected int ordenesUsadas;
    protected int ticketUsados;

    /**
     * Obtiene el valor de la propiedad anio.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Define el valor de la propiedad anio.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

    /**
     * Obtiene el valor de la propiedad cantOrdenes.
     * 
     */
    public int getCantOrdenes() {
        return cantOrdenes;
    }

    /**
     * Define el valor de la propiedad cantOrdenes.
     * 
     */
    public void setCantOrdenes(int value) {
        this.cantOrdenes = value;
    }

    /**
     * Obtiene el valor de la propiedad cantTicket.
     * 
     */
    public int getCantTicket() {
        return cantTicket;
    }

    /**
     * Define el valor de la propiedad cantTicket.
     * 
     */
    public void setCantTicket(int value) {
        this.cantTicket = value;
    }

    /**
     * Obtiene el valor de la propiedad ordenesUsadas.
     * 
     */
    public int getOrdenesUsadas() {
        return ordenesUsadas;
    }

    /**
     * Define el valor de la propiedad ordenesUsadas.
     * 
     */
    public void setOrdenesUsadas(int value) {
        this.ordenesUsadas = value;
    }

    /**
     * Obtiene el valor de la propiedad ticketUsados.
     * 
     */
    public int getTicketUsados() {
        return ticketUsados;
    }

    /**
     * Define el valor de la propiedad ticketUsados.
     * 
     */
    public void setTicketUsados(int value) {
        this.ticketUsados = value;
    }

}
