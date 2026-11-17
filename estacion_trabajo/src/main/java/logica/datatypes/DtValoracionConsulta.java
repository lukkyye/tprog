package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtValoracionConsulta {
    private String nombreConsulta;
    private String fechaConsulta;
    private Float promedioConsulta;
    private int valoracionPaciente;

    public DtValoracionConsulta() {
    }

    public DtValoracionConsulta(String nombreConsulta, String fechaConsulta,
            Float promedioConsulta, int valoracionPaciente) {
        this.nombreConsulta = nombreConsulta;
        this.fechaConsulta = fechaConsulta;
        this.promedioConsulta = promedioConsulta;
        this.valoracionPaciente = valoracionPaciente;
    }

    public String getNombreConsulta() {
        return nombreConsulta;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public Float getPromedioConsulta() {
        return promedioConsulta;
    }

    public int getValoracionPaciente() {
        return valoracionPaciente;
    }
}