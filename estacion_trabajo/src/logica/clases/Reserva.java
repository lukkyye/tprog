package logica.clases;
import java.time.LocalDate;

import logica.datatypes.DtResConsultaDetalle;
import logica.excepciones.ConsultaNotFoundException;
import logica.datatypes.DtConsultaDetalle;
import logica.manejadores.ManejadorConsulta;
import logica.datatypes.DtResConsulta;
import logica.datatypes.DtResAsistio;

public class Reserva {
	private String nicknamePaciente;
	private String nomConsultaAsoc;
	private LocalDate fechaRS;
    private int numReserva;
    private boolean aplicaBenef;
    private float costoR;
    private boolean asistio;
    private String motivo;
    private String diagnostico;
	
    public Reserva(String nicknamePaciente, String nom_consulta_aso, LocalDate fechaRS, int numReserva,
            boolean aplicaBenef, float costoR) {

    		this.nicknamePaciente = nicknamePaciente;
    		this.nomConsultaAsoc = nom_consulta_aso;
    		this.fechaRS = fechaRS;
    		this.numReserva = numReserva;
    		this.aplicaBenef = aplicaBenef;
    		this.costoR = costoR;
    		this.asistio = false;
    		this.motivo = "";
    		this.diagnostico = "";
    }
	
    public String getNicknamePaciente() {
        return nicknamePaciente;
    }
    
	public String getConsultasAsoc() {
		return nomConsultaAsoc;
	}
	
	public LocalDate getFechaRS() {
        return fechaRS;
    }

    public int getNumReserva() {
        return numReserva;
    }

    public boolean getAplicaBenef() {
        return aplicaBenef;
    }

    public float getCostoR() {
        return costoR;
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
    
    public String getDiagnostico() {
    	return diagnostico;
    }
    
    public void setDiagnostico(String diag) {
    	this.diagnostico = diag;
    }
    
    public boolean tieneDetalleReserva() {
    	if (motivo == "") {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    public DtResConsulta getDataResConsulta() {
    	return new DtResConsulta(nomConsultaAsoc, fechaRS, numReserva, aplicaBenef, costoR, nicknamePaciente);
    }
    
    public DtResAsistio getDataResAsistio() throws ConsultaNotFoundException {
    	return new DtResAsistio(nomConsultaAsoc, numReserva, nicknamePaciente, asistio, motivo, diagnostico);
    }
    
    
    public DtResConsultaDetalle getDataResConsultaDetalle() throws ConsultaNotFoundException {

        ManejadorConsulta manejadorConsulta = ManejadorConsulta.getInstance();
        Consulta consulta = manejadorConsulta.getConsulta(nomConsultaAsoc);

        DtConsultaDetalle dtConsulta = consulta.getDataConsultaDetalle();

        return new DtResConsultaDetalle(
            fechaRS,
            numReserva,
            aplicaBenef,
            dtConsulta.getMedico(),
            dtConsulta.getNomC(),
            dtConsulta.getFechaC(),
            dtConsulta.getSala(),
            dtConsulta.getCantNum(),
            dtConsulta.getCostoC(),
            costoR,
            nicknamePaciente
        );
    }
 }

