package logica.clases;
import java.time.LocalDate;

import logica.datatypes.DtConsulta;
import logica.datatypes.DtConsultaDetalle;
import logica.datatypes.DtResConsulta;
import logica.datatypes.DtResConsultaDetalle;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.EstadoConsulta;
import logica.excepciones.ConsultaNotFoundException;

public class Consulta {
	private String diaConsulta;
	private String medico;
	private float costoC;
	private int cantNum;
	private int sala;
	private LocalDate fecha;
	private String nomC;
	private EstadoConsulta estado;
	private Set<Reserva> reservas;
	
	public Consulta(String diaConsulta, String medico, String nomC, LocalDate fecha, int sala, int cantNum, float costoC, EstadoConsulta estado) { //Ver costoC
		this.nomC = nomC;
		this.fecha = fecha;
		this.sala = sala;
		this.cantNum = cantNum;
		this.costoC = costoC; //verificar esto
		this.diaConsulta = diaConsulta;
		this.medico = medico;
		this.estado = estado;
		this.reservas = new HashSet<Reserva>();
	}
	
	public String getMedicoC() {
        return medico;
    }
	
	public String getNomC() {
        return nomC;
    }
	
	public LocalDate getFechaC() {
        return fecha;
    }
	public int getSalaC() {
        return sala;
    }
	public int getCantNumC() {
        return cantNum;
    }
	
	public void agregarReserva(Reserva reserva) {
	    reservas.add(reserva);
	}
	
	public Set<Reserva> getReservas() {
	    return reservas;
	}
	
	public float getCostoC() { 
	    return costoC;
	}
	
	public EstadoConsulta getEstado() {
		return estado;
	}
	
	public Set<DtResConsulta> getResConsultas(){
		Set<DtResConsulta> dtReservas = new HashSet<DtResConsulta>();
		for (Reserva r : reservas) {
			
			dtReservas.add(r.getDataResConsulta()); 
		}
		return dtReservas;
	}
	
	public DtConsultaDetalle getDataConsultaDetalle() { 
		Set<DtResConsulta> arg = new HashSet<DtResConsulta>();
		for (Reserva reserva : reservas) {
			DtResConsulta dtconsulta = new DtResConsulta(nomC, reserva.getFechaRS(), reserva.getNumReserva(), reserva.getAplicaBenef(), reserva.getCostoR());
			arg.add(dtconsulta);
		}
		DtConsultaDetalle consultaDetalle = new DtConsultaDetalle(nomC, medico, fecha, sala, cantNum, arg, costoC, estado);
		return consultaDetalle;
	}
	
	public DtConsulta getDataConsulta() {
		DtConsulta consulta = new DtConsulta(nomC, fecha, sala, cantNum, costoC, estado);
		return consulta;
	}
	
	public int obtenerNumeroDisponible() {
	    for (int i = 1; i <= cantNum; i++) {
	        boolean ocupado = false;
	        for (Reserva r : reservas) {
	            if (r.getNumReserva() == i) {
	                ocupado = true;
	                break;
	            }
	        }
	        if (!ocupado) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	public boolean tieneReserva(String nicknamePaciente) {
	    for (Reserva r : reservas) {
	        if (r.getNicknamePaciente().equals(nicknamePaciente)) {
	            return true;
	        }
	    }
	    return false;
	}

	public String getDiaConsulta() {
	    return diaConsulta;
	}	

	
	public DtResConsultaDetalle listarDatosResConsultaC(int numReserva) throws ConsultaNotFoundException {
	    for (Reserva r : reservas) {
	        if (r.getNumReserva() == numReserva) {
	            return r.getDataResConsultaDetalle();
	        }
	    }
	    return null;
	}

	public void setEstado(EstadoConsulta estado) {
		this.estado=estado;
	}

	
}
