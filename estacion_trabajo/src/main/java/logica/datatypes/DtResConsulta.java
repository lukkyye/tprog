package logica.datatypes;
import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtResConsulta {

    private String nomC;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaRes;
    private int numReserva;
    private boolean aplicaBenef;
    private float costoR;
    private String nicknamePaciente;

    public DtResConsulta() {
    }

    public DtResConsulta(String nomC, LocalDate fechaRes, int numReserva,
                         boolean aplicaBenef, float costoR, String nicknamePaciente) {
        this.nomC = nomC;
        this.fechaRes = fechaRes;
        this.numReserva = numReserva;
        this.aplicaBenef = aplicaBenef;
        this.costoR = costoR;
        this.nicknamePaciente = nicknamePaciente;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public LocalDate getFechaRes() {
        return fechaRes;
    }

    public void setFechaRes(LocalDate fechaRes) {
        this.fechaRes = fechaRes;
    }

    public int getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(int numReserva) {
        this.numReserva = numReserva;
    }

    public boolean getAplicaBenef() {
        return aplicaBenef;
    }

    public void setAplicaBenef(boolean aplicaBenef) {
        this.aplicaBenef = aplicaBenef;
    }

    public float getCostoR() {
        return costoR;
    }

    public void setCostoR(float costoR) {
        this.costoR = costoR;
    }
    
    public String getNicknamePacienteRes() {
    	return nicknamePaciente;
    }
    
    public void setNicknamePacienteRes(String nicknamePaciente) {
    	this.nicknamePaciente = nicknamePaciente;
    }
}