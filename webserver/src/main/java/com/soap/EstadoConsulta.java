
package com.soap;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoConsulta.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="estadoConsulta">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Ingresada"/>
 *     <enumeration value="Aceptada"/>
 *     <enumeration value="Rechazada"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estadoConsulta")
@XmlEnum
public enum EstadoConsulta {

    @XmlEnumValue("Ingresada")
    INGRESADA("Ingresada"),
    @XmlEnumValue("Aceptada")
    ACEPTADA("Aceptada"),
    @XmlEnumValue("Rechazada")
    RECHAZADA("Rechazada");
    private final String value;

    EstadoConsulta(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EstadoConsulta fromValue(String v) {
        for (EstadoConsulta c: EstadoConsulta.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
