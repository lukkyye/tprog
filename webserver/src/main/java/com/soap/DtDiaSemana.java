
package com.soap;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtDiaSemana.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="dtDiaSemana">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Lunes"/>
 *     <enumeration value="Martes"/>
 *     <enumeration value="Miercoles"/>
 *     <enumeration value="Jueves"/>
 *     <enumeration value="Viernes"/>
 *     <enumeration value="Sabado"/>
 *     <enumeration value="Domingo"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "dtDiaSemana")
@XmlEnum
public enum DtDiaSemana {

    @XmlEnumValue("Lunes")
    LUNES("Lunes"),
    @XmlEnumValue("Martes")
    MARTES("Martes"),
    @XmlEnumValue("Miercoles")
    MIERCOLES("Miercoles"),
    @XmlEnumValue("Jueves")
    JUEVES("Jueves"),
    @XmlEnumValue("Viernes")
    VIERNES("Viernes"),
    @XmlEnumValue("Sabado")
    SABADO("Sabado"),
    @XmlEnumValue("Domingo")
    DOMINGO("Domingo");
    private final String value;

    DtDiaSemana(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DtDiaSemana fromValue(String v) {
        for (DtDiaSemana c: DtDiaSemana.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
