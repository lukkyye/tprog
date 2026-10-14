package logica.clases;
import java.time.LocalDate;
import logica.datatypes.*;
import java.util.HashSet;

import java.util.ArrayList;
public class Paciente extends Usuario{
	private LocalDate fechaNac;
	//private int cantOrdenesAcum;
	//private int cantTicketAcum;
	private ArrayList<String> antSalud;
	private int doc;
	private LocalDate fechaIn;
	private HashSet<BeneficioAnual> benef;
	private HashSet<Reserva> reservas;
	
	public Paciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate _fechaNac, LocalDate _fechaIn, int _doc, ArrayList<String> _antSalud){
			super(nickname, password,nombre, apellido, correo);
			this.fechaNac = _fechaNac;
			//this.cantOrdenesAcum = 0;
			//this.cantTicketAcum = 0;
			this.antSalud= (_antSalud != null) ? _antSalud : new ArrayList<String>();
			this.doc = _doc;
			this.fechaIn = _fechaIn;
			this.benef = new HashSet<BeneficioAnual>();
			this.reservas = new HashSet<Reserva>();
	}
	
	public HashSet<Reserva> get_reservas() {
		return reservas;
	}
	
	public boolean esMayorDe60() {
	    return fechaNac.plusYears(60).isBefore(LocalDate.now());
	}
	
	public void agregarReserva(Reserva r) {
	    reservas.add(r);
	}
	
	public int cantOrdenesAcum() {
		int total = 0;
		for (BeneficioAnual b : benef) {
			total += b.getCantOrdenes();
		}
		return total;
	}
	
	public int cantTicketAcum() {
		int total = 0;
		for (BeneficioAnual b : benef) {
			total += b.getCantTicket();
		}
		return total;
	}
	
	public DTPaciente get_dtpaciente( ) {
		return new DTPaciente(nickname,fechaNac, cantOrdenesAcum(), cantTicketAcum(), antSalud, doc, fechaIn);
	}
	
	public BeneficioAnual getBeneficio(int anio) {
		for (BeneficioAnual b : benef) {
			if (b.getAnio() == anio) {
				return b;
			}
		}
		return null;
	}
	
	public boolean tieneBeneficio(int anio) {
		return getBeneficio(anio) != null;
	}
	
	public void agregarBeneficio(BeneficioAnual b) {
		benef.add(b);
	}
	
	public HashSet<DTBeneficioAnual> get_dtbeneficios() {
		HashSet<DTBeneficioAnual> result = new HashSet<DTBeneficioAnual>();
		for (BeneficioAnual b : benef) {
			result.add(b.getDataBeneficioAnual());
		}
		return result;
	}
	
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	public void setFechaIn(LocalDate fechaIn) {
		this.fechaIn = fechaIn;
	}
	
	public void setDoc(int doc) {
		this.doc = doc;
	}
	
	public void setAntSalud(ArrayList<String> antSalud) {
		this.antSalud = (antSalud != null) ? new ArrayList<String>(antSalud) : new ArrayList<String>();
	}
}
