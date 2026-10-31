
package com.soap;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoMes.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="tipoMes">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Enero"/>
 *     <enumeration value="Febrero"/>
 *     <enumeration value="Marzo"/>
 *     <enumeration value="Abril"/>
 *     <enumeration value="Mayo"/>
 *     <enumeration value="Junio"/>
 *     <enumeration value="Julio"/>
 *     <enumeration value="Agosto"/>
 *     <enumeration value="Septiembre"/>
 *     <enumeration value="Octubre"/>
 *     <enumeration value="Noviembre"/>
 *     <enumeration value="Diciembre"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "tipoMes")
@XmlEnum
public enum TipoMes {

    @XmlEnumValue("Enero")
    ENERO("Enero"),
    @XmlEnumValue("Febrero")
    FEBRERO("Febrero"),
    @XmlEnumValue("Marzo")
    MARZO("Marzo"),
    @XmlEnumValue("Abril")
    ABRIL("Abril"),
    @XmlEnumValue("Mayo")
    MAYO("Mayo"),
    @XmlEnumValue("Junio")
    JUNIO("Junio"),
    @XmlEnumValue("Julio")
    JULIO("Julio"),
    @XmlEnumValue("Agosto")
    AGOSTO("Agosto"),
    @XmlEnumValue("Septiembre")
    SEPTIEMBRE("Septiembre"),
    @XmlEnumValue("Octubre")
    OCTUBRE("Octubre"),
    @XmlEnumValue("Noviembre")
    NOVIEMBRE("Noviembre"),
    @XmlEnumValue("Diciembre")
    DICIEMBRE("Diciembre");
    private final String value;

    TipoMes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoMes fromValue(String v) {
        for (TipoMes c: TipoMes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
