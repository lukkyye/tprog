package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtConsulta {

    private String nomC;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaC;
    
    private int sala;
    private int cantNum;
    private float costoC;
    private EstadoConsulta estado;

    public DtConsulta() {
    }

    public DtConsulta(String nomC, LocalDate fechaC, int sala, int cantNum,
                      float costoC, EstadoConsulta estado) {
        this.nomC = nomC;
        this.fechaC = fechaC;
        this.sala = sala;
        this.cantNum = cantNum;
        this.costoC = costoC;
        this.estado = estado;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public LocalDate getFechaC() {
        return fechaC;
    }

    public void setFechaC(LocalDate fechaC) {
        this.fechaC = fechaC;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public int getCantNum() {
        return cantNum;
    }

    public void setCantNum(int cantNum) {
        this.cantNum = cantNum;
    }

    public float getCostoC() {
        return costoC;
    }

    public void setCostoC(float costoC) {
        this.costoC = costoC;
    }

    public EstadoConsulta getEstado() {
        return estado;
    }

    public void setEstado(EstadoConsulta estado) {
        this.estado = estado;
    }
}