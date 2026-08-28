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
		HashSet<DTResConsulta> arg = new HashSet<DTResConsulta>();
		DTConsultaDetalle consultaDetalle = new DTConsultaDetalle(nomC,medico,fecha,sala,cantNum,arg, costoC);
		return consultaDetalle;
	}
	public DTConsulta getDataConsulta() {
		DTConsulta consulta = new DTConsulta(nomC, fecha, sala, cantNum, costoC);
		return consulta;
	}
	
}
