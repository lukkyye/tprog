package logica.clases;
import java.time.LocalDate;
import logica.datatypes.DTResConsultaDetalle;
import logica.datatypes.DTConsultaDetalle;
import logica.manejadores.ManejadorConsulta;
import logica.datatypes.DTResConsulta;

public class Reserva {
	private String nicknamePaciente;
	private String nom_consulta_asoc;
	private LocalDate fechaRS;
    private int numReserva;
    private boolean aplicaBenef;
    private float costoR;
	
    public Reserva(String nicknamePaciente,String nom_consulta_aso, LocalDate fechaRS, int numReserva,
            boolean aplicaBenef, float costoR) {

    		this.nicknamePaciente = nicknamePaciente;
    		this.nom_consulta_asoc = nom_consulta_aso;
    		this.fechaRS = fechaRS;
    		this.numReserva = numReserva;
    		this.aplicaBenef = aplicaBenef;
    		this.costoR = costoR;
    }
	
    public String getNicknamePaciente() {
        return nicknamePaciente;
    }
    
	public String get_consultaAsociada() {
		return nom_consulta_asoc;
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
    
    public DTResConsulta getDataResConsulta() {
    	return new DTResConsulta(fechaRS, numReserva, aplicaBenef, costoR);
    }
    
    public DTResConsultaDetalle getDataResConsultaDetalle() {

        ManejadorConsulta mc = ManejadorConsulta.get_instance();
        Consulta c = mc.get_consulta(nom_consulta_asoc);

        DTConsultaDetalle dtConsulta = c.getDataConsultaDetalle();

        return new DTResConsultaDetalle(
            fechaRS,
            numReserva,
            aplicaBenef,
            dtConsulta.medico(),
            dtConsulta.nomC(),
            dtConsulta.fechaC(),
            dtConsulta.sala(),
            dtConsulta.cantNum(),
            dtConsulta.costoC(),
            costoR
        );
    }
 }

