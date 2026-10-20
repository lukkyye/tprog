package logica.controladores;
import logica.clases.BeneficioAnual;
import logica.clases.Clinica;
import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTDiaSemana;
import logica.manejadores.ManejadorClinica;
import logica.datatypes.DTConsultaDetalle;
import logica.manejadores.ManejadorConsulta;
import logica.clases.Consulta;
import logica.datatypes.DTConsulta;
import logica.clases.DiaConsulta;
import logica.clases.Medico;

import java.util.Set;
import java.util.HashSet;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTResConsultaDetalle;
import logica.datatypes.EstadoConsulta;
import logica.manejadores.ManejadorUsuario;
import logica.clases.Usuario;
import logica.clases.Paciente;
import logica.clases.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import logica.excepciones.NoNumberAvailableException;
import logica.excepciones.ReservaAlreadyExistsException;
import logica.excepciones.ConsultaNombreAlreadyExistsException;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.FechaDiaSemanaInvalidaException;

import logica.clases.Especialidad;
import logica.manejadores.ManejadorEspecialidad;
import logica.excepciones.DiaConsultaAlreadyExistsException;
import logica.excepciones.BeneficioSinOrdenesException;



public class ControladorClinica {

	public Set<DTDiaConsulta> listarDatosDiaConsulta() {
	    Set<DTDiaConsulta> res = new HashSet<>();
	    ManejadorClinica mc = ManejadorClinica.get_instance();
	    for (Clinica c : mc.getClinicas().values()) { // esto es un for each
	        res.addAll(c.mostrarDiasConsulta());
	    }
	    return res;
	}
	
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		ManejadorClinica mcl = ManejadorClinica.get_instance();
		mcl.agregar_clinica(nombre, desc, dir, costoT, costoC, link);
		return true;
	}
	
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
		DTConsultaDetalle res;
		ManejadorConsulta mcon = ManejadorConsulta.get_instance();
		Consulta cons = mcon.get_consulta(nomC);
		res = cons.getDataConsultaDetalle();
		return res;
		
	}
	
	public DTResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) {
	    ManejadorConsulta mc = ManejadorConsulta.get_instance();
	    Consulta c = mc.get_consulta(nomC);
	    if (c != null) {
	        return c.listarDatosResConsultaC(numReserva);
	    }
	    return null;
	}
	
	public Set<DTConsulta> selecListaConsultaDelDia(String nomD) {// si nomD no existe puede dar problemas hay que ver 
		Set<DTConsulta> res;
		ManejadorClinica mc = ManejadorClinica.get_instance();
		DiaConsulta day = mc.findDiasConsultas(nomD);
		res = day.get_dtconsultas();
		return res;
		
	}
	
	public DTConsulta seleccionarConsulta(String nomC) {
		DTConsulta res;
		ManejadorConsulta mcon = ManejadorConsulta.get_instance();
		Consulta c = mcon.get_consulta(nomC);
		res = c.getDataConsulta();
		return res;
		
	}
	
	
	public HashSet<DTPaciente> listarPacientes() {
	    ManejadorUsuario mu = ManejadorUsuario.get_instance();
	    return mu.get_dtpacientes();
	}
	
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef) {
		 ManejadorUsuario mu = ManejadorUsuario.get_instance();
		 ManejadorConsulta mc = ManejadorConsulta.get_instance();
		 Usuario u = mu.get_usuario(nicknamePaciente);
		 Paciente p = (Paciente) u;
		 Consulta c = mc.get_consulta(nomC);
		 if (c.tieneReserva(nicknamePaciente)) {// ya tiene una reserva
			 throw new ReservaAlreadyExistsException("El paciente ya tiene una reserva para esta consulta");
		    }
		 int numero = c.obtenerNumeroDisponible();
		 if (numero == -1) {// no hay numeros disp
			 throw new NoNumberAvailableException("No hay números disponibles para esta consulta");
		    }
		 float costo = c.getCostoC();
		 //boolean aplicaBenef = false;
		 if (p.esMayorDe60()) {
		        costo = costo * 0.8f;
		        //aplicaBenef = true;
		    }
		 BeneficioAnual beneficio = null;
		 if (aplicaBenef) {
			 beneficio = p.beneficioConOrdenesMasAntiguo();
			 if (beneficio == null) {
				 throw new BeneficioSinOrdenesException("El paciente no tiene ordenes gratuitas disponibles");
			 }
			 costo = 0.0f;
		 }
		 Reserva r = new Reserva(nicknamePaciente, nomC, fechaReserva, numero, aplicaBenef, costo);
		 p.agregarReserva(r);
		 c.agregarReserva(r);
		 if (beneficio != null) {
			 beneficio.usarOrden();
		 }
	}
	public Set<DTConsulta> listarConsultas() {
	    ManejadorConsulta mc = ManejadorConsulta.get_instance();
	    Set<DTConsulta> result = new HashSet<>();
	    for (Consulta c : mc.get_consultas().values()) {
	        result.add(c.getDataConsulta());
	    }
	    return result;
	}
	
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) {
		ManejadorConsulta mco = ManejadorConsulta.get_instance();
		mco.get_consulta(nombreConsulta).setEstado(state);
	}
	
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) {
		ManejadorConsulta mco = ManejadorConsulta.get_instance();
		ManejadorClinica mcl = ManejadorClinica.get_instance();
		ManejadorUsuario mu = ManejadorUsuario.get_instance();
		
		if (mco.get_consultas().containsKey(nomC)) {
			throw new ConsultaNombreAlreadyExistsException("Ya existe una consulta con el nombre ingresado");
		}
		DiaConsulta diaConsulta = mcl.findDiasConsultas(nomD);
		Usuario u = mu.get_usuario(med);
		Medico medico = (Medico) u;
		if (!medico.tieneEspecialidad(diaConsulta.get_especialidad().getNombre())) {
			throw new MedicoSinEspecialidadException("El medico no tiene la espcialidad requerida para este dia de consulta");
		}
		if (diaConsulta.get_dia() != DTDiaSemana.values()[fecha.getDayOfWeek().getValue() - 1]) {
			throw new FechaDiaSemanaInvalidaException("La fecha ingresada no corresponde al dia de la semana del dia de consulta");
		}
		float costoC = diaConsulta.get_costoD()* (1 + medico.get_porcentajeCosto()/100f);
		EstadoConsulta estado = EstadoConsulta.Ingresada;
		Consulta c = new Consulta(nomD, med, nomC, fecha, sala, cantNum, costoC, estado);
		diaConsulta.agregarConsulta(c);
		medico.agregarConsultaAsociada(nomC);
		mco.agregarConsulta(c);
	}
	
	public Set<String> listarClinicas() {

	    ManejadorClinica mc = ManejadorClinica.get_instance();

	    return new HashSet<String>(
	        mc.getClinicas().keySet()
	    );
	}
	
	public Set<String> listarEspecialidades() {

	    ManejadorEspecialidad me =
	            ManejadorEspecialidad.get_instance();

	    return new HashSet<String>(
	            me.get_especialidades()
	    );
	}


	public void ingresarDiaConsulta(
	        String nombre,
	        String descripcion,
	        DTDiaSemana dia,
	        LocalTime horaInicio,
	        String nombreEspecialidad,
	        String nombreClinica) {

	    ManejadorClinica mc =
	            ManejadorClinica.get_instance();

	    ManejadorEspecialidad me =
	            ManejadorEspecialidad.get_instance();


	    // Verificar que el nombre sea único
	    if (mc.memberDiaConsulta(nombre)) {

	        throw new DiaConsultaAlreadyExistsException(
	                "Ya existe un día de consulta con ese nombre"
	        );
	    }


	    // Buscar clínica
	    Clinica clinica =
	            mc.findClinica(nombreClinica);


	    // Buscar especialidad
	    Especialidad especialidad =
	            me.getEspecialidad(nombreEspecialidad);


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
	    mc.agregarDiaConsulta(nuevoDia);


	    // Asociarlo a la clínica
	    clinica.agregarDiaConsulta(nuevoDia);
	}
}

