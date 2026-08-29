package logica.clases;
import java.time.LocalDate;
import logica.datatypes.*;
import java.util.HashSet;

import java.util.ArrayList;
public class Paciente extends Usuario{
	private LocalDate fechaNac;
	private int cantOrdenesAcum;
	private int cantTicketAcum;
	private ArrayList<String> antSalud;
	private int doc;
	private LocalDate fechaIn;
	private HashSet<BeneficioAnual> benef;
	private HashSet<Reserva> reservas;
	
	public Paciente(String nickname, String nombre, String apellido, String correo, LocalDate _fechaNac, LocalDate _fechaIn, int _doc, ArrayList<String> _antSalud){
			super(nickname, nombre, apellido, correo);
			this.fechaNac = _fechaNac;
			this.cantOrdenesAcum = 0;
			this.cantTicketAcum = 0;
			this.antSalud= (_antSalud != null) ? _antSalud : new ArrayList<String>();
			this.doc = _doc;
			this.fechaIn = _fechaIn;
			this.benef = new HashSet<BeneficioAnual>();
			this.reservas = new HashSet<Reserva>();
	}
	
	public HashSet<Reserva> get_reservas() {
		return reservas;
	}
	
	public DTPaciente get_dtpaciente( ) {
		return new DTPaciente(fechaNac, cantOrdenesAcum, cantTicketAcum, antSalud, doc, fechaIn);
	}
	
}//cambios
