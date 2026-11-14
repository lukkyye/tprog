package com.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtValoracionConsulta", propOrder = {
    "nombreConsulta",
    "fechaConsulta",
    "promedioConsulta",
    "valoracionPaciente"
})
public class DtValoracionConsulta {
    protected String nombreConsulta;
    protected String fechaConsulta;
    protected Float promedioConsulta;
    protected Float valoracionPaciente;

    public String getNombreConsulta() {
        return nombreConsulta;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public Float getPromedioConsulta() {
        return promedioConsulta;
    }

    public Float getValoracionPaciente() {
        return valoracionPaciente;
    }
}