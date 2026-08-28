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
	private LocalDate fechaIngreso;
	private HashSet<Reserva> reservas;
	
	public Paciente(String nickname, String nombre, String apellido, String correo, LocalDate _fechaNac, int _doc, LocalDate _fechaIngreso){
			super(nickname, nombre, apellido, correo);
			this.fechaNac=_fechaNac;
			this.cantOrdenesAcum=0;
			this.cantTicketAcum=0;
			this.antSalud=new ArrayList<String>();
			this.doc=_doc;
			this.fechaIngreso=_fechaIngreso;
	}
	
	public DTPaciente get_dtpaciente( ) {
		return new DTPaciente(fechaNac, cantOrdenesAcum, cantTicketAcum, antSalud, doc, fechaIngreso);
	}
	
}
