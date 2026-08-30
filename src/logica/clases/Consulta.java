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
	private HashSet<Reserva> reservas;
	
	public Consulta(String diaConsulta, String medico, String nomC, LocalDate fecha, int sala, int cantNum, float costoC) { //Ver costoC
		this.nomC = nomC;
		this.fecha = fecha;
		this.sala = sala;
		this.cantNum = cantNum;
		this.costoC = costoC; //verificar esto
		this.diaConsulta = diaConsulta;
		this.medico = medico;
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
	
	public DTConsultaDetalle getDataConsultaDetalle() { 
		HashSet<DTResConsulta> arg = new HashSet<DTResConsulta>();
		for(Reserva r : reservas) {
			DTResConsulta dt = new DTResConsulta(r.getFechaRS(),r.getNumReserva(),r.getAplicaBenef(),r.getCostoR());
			arg.add(dt);
		}
		DTConsultaDetalle consultaDetalle = new DTConsultaDetalle(nomC,medico,fecha,sala,cantNum,arg, costoC);
		return consultaDetalle;
	}
	
	public DTConsulta getDataConsulta() {
		DTConsulta consulta = new DTConsulta(nomC, fecha, sala, cantNum, costoC);
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
	public String getNomC() {
	    return nomC;
	}

	public String getDiaConsulta() {
	    return diaConsulta;
	}
	
}
