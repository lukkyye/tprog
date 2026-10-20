package logica.clases;
import java.time.LocalDate;
import logica.datatypes.*;
import logica.manejadores.ManejadorConsulta;
import logica.manejadores.ManejadorUsuario;

import java.util.HashSet;

public class Consulta {
	private String diaConsulta;
	private String medico;
	private float costoC;
	private int cantNum;
	private int sala;
	private LocalDate fecha;
	private String nomC;
	private EstadoConsulta estado;
	private HashSet<Reserva> reservas;
	
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
	
	public void agregarReserva(Reserva r) {
	    reservas.add(r);
	}
	
	public HashSet<Reserva> getReservas() {
	    return reservas;
	}
	
	public float getCostoC() {
	    return costoC;
	}
	
	public EstadoConsulta getEstado() {
		return estado;
	}
	
	public DTConsultaDetalle getDataConsultaDetalle() { 
		HashSet<DTResConsulta> arg = new HashSet<DTResConsulta>();
		for(Reserva r : reservas) {
			DTResConsulta dt = new DTResConsulta(nomC, r.getFechaRS(),r.getNumReserva(),r.getAplicaBenef(),r.getCostoR());
			arg.add(dt);
		}
		DTConsultaDetalle consultaDetalle = new DTConsultaDetalle(nomC,medico,fecha,sala,cantNum,arg, costoC, estado);
		return consultaDetalle;
	}
	
	public DTConsulta getDataConsulta() {
		DTConsulta consulta = new DTConsulta(nomC, fecha, sala, cantNum, costoC, estado);
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

	
	public DTResConsultaDetalle listarDatosResConsultaC(int numReserva) {
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
