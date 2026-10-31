package logica.datatypes;
import java.time.LocalDate;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtConsultaDetalle {

    private String nomC;
    private String medico;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaC;
    private int sala;
    private int cantNum;
    private Set<DtResConsulta> reservas;
    private Float costoC;
    private EstadoConsulta estado;

    public DtConsultaDetalle() {
    }

    public DtConsultaDetalle(String nomC, String medico, LocalDate fechaC, int sala,
                             int cantNum, Set<DtResConsulta> reservas,
                             Float costoC, EstadoConsulta estado) {
        this.nomC = nomC;
        this.medico = medico;
        this.fechaC = fechaC;
        this.sala = sala;
        this.cantNum = cantNum;
        this.reservas = reservas;
        this.costoC = costoC;
        this.estado = estado;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
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

    public Set<DtResConsulta> getReservas() {
        return reservas;
    }

    public void setReservas(Set<DtResConsulta> reservas) {
        this.reservas = reservas;
    }

    public Float getCostoC() {
        return costoC;
    }

    public void setCostoC(Float costoC) {
        this.costoC = costoC;
    }

    public EstadoConsulta getEstado() {
        return estado;
    }

    public void setEstado(EstadoConsulta estado) {
        this.estado = estado;
    }
}