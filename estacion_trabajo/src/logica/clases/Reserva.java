package logica.clases;
import java.time.LocalDate;

import logica.datatypes.DtResConsultaDetalle;
import logica.datatypes.DtConsultaDetalle;
import logica.manejadores.ManejadorConsulta;
import logica.datatypes.DtResConsulta;

public class Reserva {
	private String nicknamePaciente;
	private String nomConsultaAsoc;
	private LocalDate fechaRS;
    private int numReserva;
    private boolean aplicaBenef;
    private float costoR;
	
    public Reserva(String nicknamePaciente, String nom_consulta_aso, LocalDate fechaRS, int numReserva,
            boolean aplicaBenef, float costoR) {

    		this.nicknamePaciente = nicknamePaciente;
    		this.nomConsultaAsoc = nom_consulta_aso;
    		this.fechaRS = fechaRS;
    		this.numReserva = numReserva;
    		this.aplicaBenef = aplicaBenef;
    		this.costoR = costoR;
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
    
    public DtResConsulta getDataResConsulta() {
    	return new DtResConsulta(nomConsultaAsoc, fechaRS, numReserva, aplicaBenef, costoR);
    }
    
    public DtResConsultaDetalle getDataResConsultaDetalle() {

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
            costoR
        );
    }
 }

