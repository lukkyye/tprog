package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtResAsistio {
	
    private boolean asistio;
    private String motivo;
    private String diagnostico;

    public DtResAsistio() {
    }

    public DtResAsistio(boolean asistio, String motivo, String diagnostico) {
        this.asistio = asistio;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
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