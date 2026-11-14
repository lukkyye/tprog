package logica.controladores;
import logica.clases.BeneficioAnual;
import logica.clases.Clinica;
import logica.datatypes.DtDiaConsulta;
import logica.datatypes.DtDiaSemana;
import logica.manejadores.ManejadorClinica;
import logica.datatypes.DtConsultaDetalle;
import logica.manejadores.ManejadorConsulta;
import logica.clases.Consulta;
import logica.datatypes.DtClinica;
import logica.datatypes.DtConsulta;
import logica.clases.DiaConsulta;
import logica.clases.Medico;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import logica.datatypes.DtPaciente;
import logica.datatypes.DtResConsulta;
import logica.datatypes.DtResConsultaDetalle;
import logica.datatypes.EstadoConsulta;
import logica.manejadores.ManejadorUsuario;
import logica.clases.Usuario;
import logica.clases.Paciente;
import logica.clases.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import logica.excepciones.NoNumberAvailableException;
import logica.excepciones.NombreClinicaAlreadyExistsException;
import logica.excepciones.ReservaAlreadyExistsException;
import logica.excepciones.SpecialityNotFoundException;
import logica.excepciones.UserNotFoundException;
import logica.excepciones.ConsultaNombreAlreadyExistsException;
import logica.excepciones.ConsultaNotFoundException;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.FechaDiaSemanaInvalidaException;

import logica.clases.Especialidad;
import logica.manejadores.ManejadorEspecialidad;
import logica.excepciones.DiaConsultaAlreadyExistsException;
import logica.excepciones.BeneficioSinOrdenesException;



public class ControladorClinica {
	

	public Set<DtDiaConsulta> listarDatosDiaConsulta() {
	    Set<DtDiaConsulta> res = new HashSet<>();
	    ManejadorClinica manejadorClinica = ManejadorClinica.getInstance();
	    for (Clinica c : manejadorClinica.getClinicas().values()) { // esto es un for each
	        res.addAll(c.mostrarDiasConsulta());
	    }
	    return res;
	}
	
	public DtClinica seleccionarClinica(String nombre) {
		ManejadorClinica manejadorClinica = ManejadorClinica.getInstance();
		Clinica clinica = manejadorClinica.findClinica(nombre);
		if (clinica == null) {
			return null;
		}
		return clinica.getDt();
	}
	
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) throws NombreClinicaAlreadyExistsException {
		ManejadorClinica manejadorClinica = ManejadorClinica.getInstance();
		manejadorClinica.agregarClinica(nombre, desc, dir, costoT, costoC, link);
		return true;
	}
	
	
	public DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException {
		DtConsultaDetalle res;
		ManejadorConsulta mcon = ManejadorConsulta.getInstance();
		Consulta cons = mcon.getConsulta(nomC);
		res = cons.getDataConsultaDetalle();
		return res;
		
	}
	
	public DtResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) throws ConsultaNotFoundException {
	    ManejadorConsulta manejadorConsulta = ManejadorConsulta.getInstance();
	    Consulta consulta = manejadorConsulta.getConsulta(nomC);
	    if (consulta != null) {
	        return consulta.listarDatosResConsultaC(numReserva);
	    } else {
	    	throw new ConsultaNotFoundException();
	    }
	}
	
	public Set<DtResConsulta> getReservasConsulta(String nomC) throws ConsultaNotFoundException{
		ManejadorConsulta manejadorConsulta = ManejadorConsulta.getInstance();
		Consulta consulta = manejadorConsulta.getConsulta(nomC);
		return consulta.getResConsultas();
	}
	
	//nombre de consultas que satisfacen que son de especialidad dada
	public List<String> getConsultasPorEspecialidad(String especialidad){
		if (!ManejadorEspecialidad.getInstance().existeEsp(especialidad)) {
			return null;
		}
		List<String> toreturn = new ArrayList<String>();
		for (DiaConsulta dc : ManejadorClinica.getInstance().getDiaConsulta().values()) {
			if (dc.getEspecialidad().getNombre().equals(especialidad)) {
				dc.getDtconsultas().stream().forEach(dtc-> {toreturn.add(dtc.getNomC()); });
			}
		}
		return toreturn;
	}
	
	public Set<DtConsulta> selecListaConsultaDelDia(String nomD) {// si nomD no existe puede dar problemas hay que ver 
		Set<DtConsulta> res;
		ManejadorClinica manejadorClinica = ManejadorClinica.getInstance();
		DiaConsulta day = manejadorClinica.findDiasConsultas(nomD);
		res = day.getDtconsultas();
		return res;
		
	}
	
	public DtConsulta seleccionarConsulta(String nomC) throws ConsultaNotFoundException {
		DtConsulta res;
		ManejadorConsulta mcon = ManejadorConsulta.getInstance();
		Consulta consulta = mcon.getConsulta(nomC);
		res = consulta.getDataConsulta();
		return res;
		
	}
	
	
	public Set<DtPaciente> listarPacientes() {
	    ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
	    return manejadorUsuario.getDtpacientes();
	}
	
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef) throws ReservaAlreadyExistsException, NoNumberAvailableException, UserNotFoundException, BeneficioSinOrdenesException, ConsultaNotFoundException {
		 ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		 ManejadorConsulta manejadorConsulta = ManejadorConsulta.getInstance();
		 Usuario usuario = manejadorUsuario.getUsuario(nicknamePaciente);
		 Paciente paciente = (Paciente) usuario;
		 Consulta consulta = manejadorConsulta.getConsulta(nomC);
		 if (consulta.tieneReserva(nicknamePaciente)) {// ya tiene una reserva
			 throw new ReservaAlreadyExistsException("El paciente ya tiene una reserva para esta consulta");
		    }
		 int numero = consulta.obtenerNumeroDisponible();
		 if (numero == -1) {// no hay numeros disp
			 throw new NoNumberAvailableException("No hay números disponibles para esta consulta");
		    }
		 float costo = consulta.getCostoC();
		 //boolean aplicaBenef = false;
		 if (paciente.esMayorDe60()) {
		        costo = costo * 0.8f;
		        //aplicaBenef = true;
		    }
		 BeneficioAnual beneficio = null;
		 if (aplicaBenef) {
			 beneficio = paciente.beneficioConOrdenesMasAntiguo();
			 if (beneficio == null) {
				 throw new BeneficioSinOrdenesException("El paciente no tiene ordenes gratuitas disponibles");
			 }
			 costo = 0.0f;
		 }
		 Reserva reserva = new Reserva(nicknamePaciente, nomC, fechaReserva, numero, aplicaBenef, costo);
		 paciente.agregarReserva(reserva);
		 consulta.agregarReserva(reserva);
		 if (beneficio != null) {
			 beneficio.usarOrden();
		 }
	}
	
	//*public void altaDetalleReserva(String nomConsulta, int numRes, boolean asistio, String motivo, String diagnostico) {
	//*	ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
	//*	ManejadorConsulta manejadorConsulta = ManejadorConsulta.getInstance();
	//*}
	
	public Set<DtConsulta> listarConsultas() {
	    ManejadorConsulta manejadorConsulta = ManejadorConsulta.getInstance();
	    Set<DtConsulta> result = new HashSet<>();
	    for (Consulta c : manejadorConsulta.getConsulta().values()) {
	        result.add(c.getDataConsulta());
	    }
	    return result;
	}
	
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) throws  
	MedicoSinEspecialidadException, ConsultaNombreAlreadyExistsException, FechaDiaSemanaInvalidaException, UserNotFoundException
	{
		ManejadorConsulta manejadorConsulta = ManejadorConsulta.getInstance();
		ManejadorClinica manejadorClinica = ManejadorClinica.getInstance();
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		
		if (manejadorConsulta.getConsulta().containsKey(nomC)) {
			throw new ConsultaNombreAlreadyExistsException("Ya existe una consulta con el nombre ingresado");
		}
		DiaConsulta diaConsulta = manejadorClinica.findDiasConsultas(nomD);
		Usuario usuario = manejadorUsuario.getUsuario(med);
		Medico medico = (Medico) usuario;
		if (!medico.tieneEspecialidad(diaConsulta.getEspecialidad().getNombre())) {
			throw new MedicoSinEspecialidadException("El medico no tiene la espcialidad requerida para este dia de consulta");
		}
		if (diaConsulta.getDia() != DtDiaSemana.values()[fecha.getDayOfWeek().getValue() - 1]) {
			throw new FechaDiaSemanaInvalidaException("La fecha ingresada no corresponde al dia de la semana del dia de consulta");
		}
		float costoC = diaConsulta.getCostoD()* (1 + medico.getPorcentajeCosto()/100f);
		EstadoConsulta estado = EstadoConsulta.Ingresada;
		Consulta consulta = new Consulta(nomD, med, nomC, fecha, sala, cantNum, costoC, estado);
		diaConsulta.agregarConsulta(consulta);
		medico.agregarConsultaAsociada(nomC);
		manejadorConsulta.agregarConsulta(consulta);
	}
	
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) throws ConsultaNotFoundException {
		ManejadorConsulta mco = ManejadorConsulta.getInstance();
		mco.getConsulta(nombreConsulta).setEstado(state);
	}
	
	public Set<String> listarClinicas() {

	    ManejadorClinica manejadorClinica = ManejadorClinica.getInstance();

	    return new HashSet<String>(
	        manejadorClinica.getClinicas().keySet()
	    );
	}
	
	public Set<String> listarEspecialidades() {

	    ManejadorEspecialidad manejadorEspecialidad =
	            ManejadorEspecialidad.getInstance();

	    return new HashSet<String>(
	            manejadorEspecialidad.getEspecialidades()
	    );
	}
	

	public void ingresarDiaConsulta(
	        String nombre,
	        String descripcion,
	        DtDiaSemana dia,
	        LocalTime horaInicio,
	        String nombreEspecialidad,
	        String nombreClinica) throws DiaConsultaAlreadyExistsException, SpecialityNotFoundException {

	    ManejadorClinica manejadorClinica =
	            ManejadorClinica.getInstance();

	    ManejadorEspecialidad manejadorEspecialidad =
	            ManejadorEspecialidad.getInstance();


	    // Verificar que el nombre sea único
	    if (manejadorClinica.memberDiaConsulta(nombre)) {

	        throw new DiaConsultaAlreadyExistsException(
	                "Ya existe un día de consulta con ese nombre"
	        );
	    }


	    // Buscar clínica
	    Clinica clinica =
	            manejadorClinica.findClinica(nombreClinica);


	    // Buscar especialidad
	    Especialidad especialidad =
	            manejadorEspecialidad.getEspecialidad(nombreEspecialidad);


	    // Calcular costo del día de consulta
	    float costoBase =
	            clinica.getCostoConsulta();

	    float costoDia =
	            costoBase *
	            (1 + especialidad.getPorcentajeAdicional() / 100f);


	    // Crear el día
	    DiaConsulta nuevoDia =
	            new DiaConsulta(
	                    nombre,
	                    costoDia,
	                    horaInicio,
	                    dia,
	                    descripcion,
	                    especialidad
	            );


	    // Guardarlo en el manejador
	    manejadorClinica.agregarDiaConsulta(nuevoDia);


	    // Asociarlo a la clínica
	    clinica.agregarDiaConsulta(nuevoDia);
	}

	public List<DtDiaConsulta> listarDiaConsultas() {
		List<DtDiaConsulta> prelist = new ArrayList<DtDiaConsulta>();
		ManejadorClinica manejadorClinica = ManejadorClinica.getInstance();
		for (DiaConsulta dc : manejadorClinica.getDiaConsulta().values()) {
			prelist.add(dc.getDTDiaConsulta());
		}
		return prelist;
	}
}

