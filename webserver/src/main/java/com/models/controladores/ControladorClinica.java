package com.models.controladores;
import com.models.clases.BeneficioAnual;
import com.models.clases.Clinica;
import com.models.datatypes.DTDiaConsulta;
import com.models.datatypes.DTDiaSemana;
import com.models.datatypes.DTMedico;
import com.models.manejadores.ManejadorClinica;
import com.models.datatypes.DTConsultaDetalle;
import com.models.manejadores.ManejadorConsulta;
import com.models.clases.Consulta;
import com.models.datatypes.DTClinica;
import com.models.datatypes.DTConsulta;
import com.models.clases.DiaConsulta;
import com.models.clases.Medico;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.models.datatypes.DTPaciente;
import com.models.datatypes.DTResConsulta;
import com.models.datatypes.DTResConsultaDetalle;
import com.models.datatypes.EstadoConsulta;
import com.models.manejadores.ManejadorUsuario;
import com.models.clases.Usuario;
import com.models.clases.Paciente;
import com.models.clases.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import com.models.excepciones.NoNumberAvailableException;
import com.models.excepciones.ReservaAlreadyExistsException;
import com.models.excepciones.ConsultaNombreAlreadyExistsException;
import com.models.excepciones.MedicoSinEspecialidadException;
import com.models.excepciones.FechaDiaSemanaInvalidaException;

import com.models.clases.Especialidad;
import com.models.manejadores.ManejadorEspecialidad;
import com.models.excepciones.DiaConsultaAlreadyExistsException;
import com.models.excepciones.BeneficioSinOrdenesException;



public class ControladorClinica {
	

	public Set<DTDiaConsulta> listarDatosDiaConsulta() {
	    Set<DTDiaConsulta> res = new HashSet<>();
	    ManejadorClinica manejadorClinica = ManejadorClinica.get_instance();
	    for (Clinica c : manejadorClinica.getClinicas().values()) { // esto es un for each
	        res.addAll(c.mostrarDiasConsulta());
	    }
	    return res;
	}
	
	public DTClinica seleccionarClinica(String nombre) {
		ManejadorClinica manejadorClinica = ManejadorClinica.get_instance();
		Clinica clinica = manejadorClinica.findClinica(nombre);
		if (clinica == null) {
			return null;
		}
		return clinica.get_dt();
	}
	
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		ManejadorClinica manejadorClinica = ManejadorClinica.get_instance();
		manejadorClinica.agregar_clinica(nombre, desc, dir, costoT, costoC, link);
		return true;
	}
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link, String img) {
		ManejadorClinica manejadorClinica= ManejadorClinica.get_instance();
		manejadorClinica.agregar_clinica(nombre, desc, dir, costoT, costoC, link);
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
	    ManejadorConsulta manejadorConsulta = ManejadorConsulta.get_instance();
	    Consulta consulta = manejadorConsulta.get_consulta(nomC);
	    if (consulta != null) {
	        return consulta.listarDatosResConsultaC(numReserva);
	    }
	    return null;
	}
	
	public Set<DTResConsulta> getReservasConsulta(String nomC){
		ManejadorConsulta manejadorConsulta = ManejadorConsulta.get_instance();
		Consulta consulta = manejadorConsulta.get_consulta(nomC);
		return consulta.getResConsultas();
	}
	
	//nombre de consultas que satisfacen que son de especialidad dada
	public List<String> getConsultasPorEspecialidad(String especialidad){
		if (!ManejadorEspecialidad.get_instance().existeEsp(especialidad)) {
			return null;
		}
		List<String> toreturn = new ArrayList<String>();
		for (DiaConsulta dc : ManejadorClinica.get_instance().getDiaConsulta().values()) {
			if (dc.get_especialidad().getNombre().equals(especialidad)) {
				dc.get_dtconsultas().stream().forEach(dtc->{toreturn.add(dtc.nomC());});
			}
		}
		return toreturn;
	}
	
	public Set<DTConsulta> selecListaConsultaDelDia(String nomD) {// si nomD no existe puede dar problemas hay que ver 
		Set<DTConsulta> res;
		ManejadorClinica manejadorClinica = ManejadorClinica.get_instance();
		DiaConsulta day = manejadorClinica.findDiasConsultas(nomD);
		res = day.get_dtconsultas();
		return res;
		
	}
	
	public DTConsulta seleccionarConsulta(String nomC) {
		DTConsulta res;
		ManejadorConsulta mcon = ManejadorConsulta.get_instance();
		Consulta consulta = mcon.get_consulta(nomC);
		res = consulta.getDataConsulta();
		return res;
		
	}
	
	
	public Set<DTPaciente> listarPacientes() {
	    ManejadorUsuario manejadorUsuario = ManejadorUsuario.get_instance();
	    return manejadorUsuario.get_dtpacientes();
	}
	
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef) {
		 ManejadorUsuario manejadorUsuario = ManejadorUsuario.get_instance();
		 ManejadorConsulta manejadorConsulta = ManejadorConsulta.get_instance();
		 Usuario usuario = manejadorUsuario.get_usuario(nicknamePaciente);
		 Paciente paciente = (Paciente) usuario;
		 Consulta consulta = manejadorConsulta.get_consulta(nomC);
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
	public Set<DTConsulta> listarConsultas() {
	    ManejadorConsulta manejadorConsulta = ManejadorConsulta.get_instance();
	    Set<DTConsulta> result = new HashSet<>();
	    for (Consulta c : manejadorConsulta.get_consultas().values()) {
	        result.add(c.getDataConsulta());
	    }
	    return result;
	}
	
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) {
		ManejadorConsulta manejadorConsulta = ManejadorConsulta.get_instance();
		ManejadorClinica manejadorClinica = ManejadorClinica.get_instance();
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.get_instance();
		
		if (manejadorConsulta.get_consultas().containsKey(nomC)) {
			throw new ConsultaNombreAlreadyExistsException("Ya existe una consulta con el nombre ingresado");
		}
		DiaConsulta diaConsulta = manejadorClinica.findDiasConsultas(nomD);
		Usuario usuario = manejadorUsuario.get_usuario(med);
		Medico medico = (Medico) usuario;
		if (!medico.tieneEspecialidad(diaConsulta.get_especialidad().getNombre())) {
			throw new MedicoSinEspecialidadException("El medico no tiene la espcialidad requerida para este dia de consulta");
		}
		if (diaConsulta.get_dia() != DTDiaSemana.values()[fecha.getDayOfWeek().getValue() - 1]) {
			throw new FechaDiaSemanaInvalidaException("La fecha ingresada no corresponde al dia de la semana del dia de consulta");
		}
		float costoC = diaConsulta.get_costoD()* (1 + medico.get_porcentajeCosto()/100f);
		EstadoConsulta estado = EstadoConsulta.Ingresada;
		Consulta consulta = new Consulta(nomD, med, nomC, fecha, sala, cantNum, costoC, estado);
		diaConsulta.agregarConsulta(consulta);
		medico.agregarConsultaAsociada(nomC);
		manejadorConsulta.agregarConsulta(consulta);
	}
	
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) {
		ManejadorConsulta mco = ManejadorConsulta.get_instance();
		mco.get_consulta(nombreConsulta).setEstado(state);
	}
	
	public Set<String> listarClinicas() {

	    ManejadorClinica manejadorClinica = ManejadorClinica.get_instance();

	    return new HashSet<String>(
	        manejadorClinica.getClinicas().keySet()
	    );
	}
	
	public Set<String> listarEspecialidades() {

	    ManejadorEspecialidad manejadorEspecialidad =
	            ManejadorEspecialidad.get_instance();

	    return new HashSet<String>(
	            manejadorEspecialidad.get_especialidades()
	    );
	}
	

	public void ingresarDiaConsulta(
	        String nombre,
	        String descripcion,
	        DTDiaSemana dia,
	        LocalTime horaInicio,
	        String nombreEspecialidad,
	        String nombreClinica) {

	    ManejadorClinica manejadorClinica =
	            ManejadorClinica.get_instance();

	    ManejadorEspecialidad manejadorEspecialidad =
	            ManejadorEspecialidad.get_instance();


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

	public List<DTDiaConsulta> listarDiaConsultas() {
		List<DTDiaConsulta> prelist = new ArrayList<DTDiaConsulta>();
		ManejadorClinica manejadorClinica = ManejadorClinica.get_instance();
		for (DiaConsulta dc : manejadorClinica.getDiaConsulta().values()) {
			prelist.add(dc.getDTDiaConsulta());
		}
		return prelist;
	}
}

