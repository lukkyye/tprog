package logica.datatypes;
import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTResConsultaDetalle {

    private LocalDate fechaRes;
    private int numReserva;
    private boolean aplicaBenef;
    private String medicoC;
    private String nomC;
    private LocalDate fechaC;
    private int salaC;
    private int cantNumC;
    private float costoC;
    private float costoR;

    public DTResConsultaDetalle() {
    }

    public DTResConsultaDetalle(LocalDate fechaRes, int numReserva, boolean aplicaBenef,
                                String medicoC, String nomC, LocalDate fechaC,
                                int salaC, int cantNumC, float costoC, float costoR) {
        this.fechaRes = fechaRes;
        this.numReserva = numReserva;
        this.aplicaBenef = aplicaBenef;
        this.medicoC = medicoC;
        this.nomC = nomC;
        this.fechaC = fechaC;
        this.salaC = salaC;
        this.cantNumC = cantNumC;
        this.costoC = costoC;
        this.costoR = costoR;
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

    public boolean isAplicaBenef() {
        return aplicaBenef;
    }

    public void setAplicaBenef(boolean aplicaBenef) {
        this.aplicaBenef = aplicaBenef;
    }

    public String getMedicoC() {
        return medicoC;
    }

    public void setMedicoC(String medicoC) {
        this.medicoC = medicoC;
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

    public int getSalaC() {
        return salaC;
    }

    public void setSalaC(int salaC) {
        this.salaC = salaC;
    }

    public int getCantNumC() {
        return cantNumC;
    }

    public void setCantNumC(int cantNumC) {
        this.cantNumC = cantNumC;
    }

    public float getCostoC() {
        return costoC;
    }

    public void setCostoC(float costoC) {
        this.costoC = costoC;
    }

    public float getCostoR() {
        return costoR;
    }

    public void setCostoR(float costoR) {
        this.costoR = costoR;
    }
}