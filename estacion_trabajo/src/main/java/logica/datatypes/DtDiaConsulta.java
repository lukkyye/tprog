package logica.datatypes;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.time.LocalTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtDiaConsulta {

    private String nomD;
    private String descD;
    private DtDiaSemana dia;
    
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime horaIni;
    private float costoD;

    public DtDiaConsulta() {
    }

    public DtDiaConsulta(String nomD, String descD, DtDiaSemana dia,
                         LocalTime horaIni, float costoD) {
        this.nomD = nomD;
        this.descD = descD;
        this.dia = dia;
        this.horaIni = horaIni;
        this.costoD = costoD;
    }

    public String getNomD() {
        return nomD;
    }

    public void setNomD(String nomD) {
        this.nomD = nomD;
    }

    public String getDescD() {
        return descD;
    }

    public void setDescD(String descD) {
        this.descD = descD;
    }

    public DtDiaSemana getDia() {
        return dia;
    }

    public void setDia(DtDiaSemana dia) {
        this.dia = dia;
    }

    public LocalTime getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(LocalTime horaIni) {
        this.horaIni = horaIni;
    }

    public float getCostoD() {
        return costoD;
    }

    public void setCostoD(float costoD) {
        this.costoD = costoD;
    }
}