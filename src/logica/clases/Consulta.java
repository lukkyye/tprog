package logica.clases;
import java.time.LocalDate;
import logica.datatypes.*;
import java.util.HashSet;

public class Consulta {
	private String diaConsulta;
	private String medico;
	private float costoC;
	private int cantNum;
	private int sala;
	private LocalDate fecha;
	private String nomC;
	private HashSet<Reserva> reservas;
	
	public Consulta(String diaConsulta, String medico, String nomC, LocalDate fecha, int sala, int cantNum, float costoC) { //Ver costoC
		this.nomC = nomC;
		this.fecha = fecha;
		this.sala = sala;
		this.cantNum = cantNum;
		this.costoC = costoC; //verificar esto
		this.diaConsulta = diaConsulta;
		this.medico = medico;
		this.reservas = new HashSet<Reserva>();
	}
	
	public void agregarReserva(Reserva r) {
	    reservas.add(r);
	}
	
	public DTConsultaDetalle getDataConsultaDetalle() { 
		HashSet<DTResConsulta> arg = new HashSet<DTResConsulta>();
		for(Reserva r : reservas) {
			DTResConsulta dt = new DTResConsulta(r.getFechaRS(),r.getNumReserva(),r.getAplicaBenef(),r.getCostoR());
			arg.add(dt);
		}
		DTConsultaDetalle consultaDetalle = new DTConsultaDetalle(nomC,medico,fecha,sala,cantNum,arg, costoC);
		return consultaDetalle;
	}
	
	public DTConsulta getDataConsulta() {
		DTConsulta consulta = new DTConsulta(nomC, fecha, sala, cantNum, costoC);
		return consulta;
	}
	
}
