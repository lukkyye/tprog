package logica.clases;
import java.time.LocalDate

public class Consulta {
	private String diaConsulta;
	private String medico;
	private float costoC;
	private int cantNum;
	private int sala;
	private LocalDate fecha;
	private String nomC;
	
	public Consulta(String diaConsulta, String medico, String nomC, LocalDate fecha, int sala, int cantNum, float costoC) { //Ver costoC
		this.nomC = nomC;
		this.fecha = fecha;
		this.sala = sala;
		this.cantNum = cantNum;
		this.costoC = costoC; //verificar esto
		this.diaConsulta = diaConsulta;
		this.medico = medico;
	}
	public DTConsultaDetalle getDataConsultaDetalle() { 
		DTConsultaDetalle consultaDetalle = new DTConsultaDetalle();
		consultaDetalle.nomC = this.nomC;
		consultaDetalle.medico = this.medico;
		consultaDetalle.fecha = fecha;
		consultaDetalle.sala = sala;
		consultaDetalle.cantNum = cantNum;
		consultaDetalle.reservas = 0; //ver bien despues, porque devolveria un set de DTResConsulta
		consultaDetalle.costoC = this.costoC; //revisar
		return consultaDetalle;
	}
	public DTConsulta getDataConsulta() {
		DTConsulta consulta = new DTConsulta();
		consulta.nomC = this.nomC;
		consulta.fecha = this.fecha;
		consulta.sala = this.sala;
		consulta.cantNum = this.cantNum;
		consulta.costoC = this.costoC;
		return consulta;
		
	}
	public DTConsultaDetalle obtenerDataConsultaDetalle()
}
