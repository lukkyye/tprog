package logica.clases;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import  logica.datatypes.DtBeneficioAnual;
import  logica.datatypes.DtMedicamento;
import  logica.datatypes.DtPaciente;
import  logica.datatypes.DtSolicitudMedicamento;
import  logica.excepciones.BeneficioSinTicketsException;
import  logica.excepciones.DatosMedicamentoInvalidosException;
import  logica.excepciones.FechaEntregaInvalidaException;
import  logica.excepciones.MedicamentoAlreadyExistsException;
import  logica.excepciones.MedicamentoNotFoundException;
import  logica.excepciones.MedicamentoVencidoException;
import  logica.excepciones.MedicamentoYaSolicitadoException;

import java.util.ArrayList;

public class Paciente extends Usuario{
	private LocalDate fechaNac;
	//private int cantOrdenesAcum;
	//private int cantTicketAcum;
	private List<String> antSalud;
	private int doc;
	private LocalDate fechaIn;
	private Set<BeneficioAnual> benef;
	private Set<Reserva> reservas;
	private List<Medicamento> medicamentos;
	
	public Paciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate _fechaNac, LocalDate _fechaIn, int _doc, List<String> _antSalud){
			super(nickname, password, nombre, apellido, correo);
			this.fechaNac = _fechaNac;
			//this.cantOrdenesAcum = 0;
			//this.cantTicketAcum = 0;
			this.antSalud= (_antSalud != null) ? _antSalud : new ArrayList<String>();
			this.doc = _doc;
			this.fechaIn = _fechaIn;
			this.benef = new HashSet<BeneficioAnual>();
			this.reservas = new HashSet<Reserva>();
			this.medicamentos = new ArrayList<Medicamento>();
	}
	
	public Set<Reserva> getReservas() {
		return reservas;
	}
	
	public boolean esMayorDe60() {
	    return fechaNac.plusYears(60).isBefore(LocalDate.now());
	}
	
	public void agregarReserva(Reserva reserva) {
	    reservas.add(reserva);
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
	
	public DtPaciente getDtpaciente( ) {
		return new DtPaciente(nickname, fechaNac, cantOrdenesAcum(), cantTicketAcum(), antSalud, doc, fechaIn);
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
	
	public void agregarBeneficio(BeneficioAnual beneficio) {
		benef.add(beneficio);
	}
	
	public Set<DtBeneficioAnual> getDtbeneficios() {
		Set<DtBeneficioAnual> result = new HashSet<DtBeneficioAnual>();
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
	
	public void setAntSalud(List<String> antSalud) {
		this.antSalud = (antSalud != null) ? new ArrayList<String>(antSalud) : new ArrayList<String>();
	}
	
	public BeneficioAnual beneficioConOrdenesMasAntiguo() {
		BeneficioAnual elegido = null;
		for (BeneficioAnual b : benef) {
			boolean esMasAntiguo = elegido == null || b.getAnio() < elegido.getAnio();
			if (b.tieneOrdenesDisponibles() && esMasAntiguo) {
				elegido = b;
			}
		}
		return elegido;
	}
	
	public BeneficioAnual beneficioConTicketMasAntiguo() {
		BeneficioAnual elegido = null;
		for (BeneficioAnual b : benef) {
			boolean esMasAntiguo = elegido == null || b.getAnio() < elegido.getAnio();
			if (b.tieneTicketDisponibles() && esMasAntiguo) {
				elegido = b;
			}
		}
		return elegido;
	}

	public int getDoc() {
		return this.doc;
	}
	
	public Medicamento getMedicamento(String nombre, LocalDate indicacion) {
		for (Medicamento m : medicamentos) {
			if (m.esMismo(nombre, indicacion)) {
				return m;
			}
		}
		return null;
	}
	
	public void agregarMedicamento(Medicamento medicamento) throws MedicamentoAlreadyExistsException {
		if (getMedicamento(medicamento.getNombre(), medicamento.getIndicacion()) != null) {
			throw new MedicamentoAlreadyExistsException("Ya se recetó " + medicamento.getNombre() + " a este paciente en esa fecha");
		}
		medicamentos.add(medicamento);
	}
	
	public List<DtMedicamento> getDtMedicamentos() {
		List<DtMedicamento> result = new ArrayList<DtMedicamento>();
		for (Medicamento m : medicamentos) {
			result.add(m.getDtMedicamento());
		}
		return result;
	}
	
	public DtSolicitudMedicamento solicitarMedicamento(String nombre, LocalDate indicacion, LocalDate hoy, LocalDate entrega, String direccion, boolean usaBenef) throws MedicamentoNotFoundException, MedicamentoYaSolicitadoException, MedicamentoVencidoException, FechaEntregaInvalidaException, DatosMedicamentoInvalidosException, BeneficioSinTicketsException {
		Medicamento medicamento = getMedicamento(nombre, indicacion);
		if (medicamento == null) {
			throw new MedicamentoNotFoundException("El paciente no tiene recetado el medicamento " + nombre);
		}
		if (medicamento.getUsado()) {
			throw new MedicamentoYaSolicitadoException("El medicamento " + nombre + " ya fue solicitado");
		}
		if (medicamento.estaVencido(hoy)) {
			throw new MedicamentoVencidoException("El plazo para solicitar " + nombre + " venció el " + medicamento.getVencimiento());
		}
		if (entrega == null || !entrega.isAfter(hoy)) {
			throw new FechaEntregaInvalidaException("La fecha de entrega debe ser posterior a la fecha actual");
		}
		if (direccion == null || direccion.isBlank()) {
			throw new DatosMedicamentoInvalidosException("Debe ingresar una dirección de entrega");
		}
		BeneficioAnual beneficio = null;
		float costo = medicamento.getCostoMed();
		if (usaBenef) {
			beneficio = beneficioConTicketMasAntiguo();
			if (beneficio == null) {
				throw new BeneficioSinTicketsException("El paciente no tiene tickets disponibles en sus beneficios anuales");
			}
			costo = 0.0f;
		}
		DtSolicitudMedicamento solicitud = new DtSolicitudMedicamento(nickname, entrega, direccion.trim(), usaBenef, costo);
		medicamento.registrarSolicitud(solicitud);
		if (beneficio != null) {
			beneficio.usarTicket();
		}
		return solicitud;
	}

}
