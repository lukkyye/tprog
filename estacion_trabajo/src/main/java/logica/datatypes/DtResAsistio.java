package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtResAsistio {
	private String nomConsulta;
	private int numRes;
	private String nicknamePaciente;
    private boolean asistio;
    private String motivo;
    private String diagnostico;

    public DtResAsistio() {
    }

    public DtResAsistio(String nomC, int numRes, String nicknamePac, boolean asistio, String motivo, String diagnostico) {
    	this.nomConsulta = nomC;
    	this.numRes = numRes;
    	this.nicknamePaciente = nicknamePac;
        this.asistio = asistio;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
    }

    public String getNomConsulta() {
        return nomConsulta;
    }
    
    public void setNomConsulta(String nomC) {
        this.nomConsulta = nomC;
    }
    
    public String getNicknamePaciente() {
        return nicknamePaciente;
    }
    
    public void setNicknamePaciente(String nicknamePac) {
        this.nicknamePaciente = nicknamePac;
    }
    
    public int getNumRes() {
        return numRes;
    }
    
    public void setNumRes(int numR) {
    	this.numRes = numR;
    }
    
    public boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiag() {
        return diagnostico;
    }

    public void setDiag(String diag) {
        this.diagnostico = diag;
    }

    
}