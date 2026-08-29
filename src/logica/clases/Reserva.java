package logica.clases;
import java.time.LocalDate;

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
}

