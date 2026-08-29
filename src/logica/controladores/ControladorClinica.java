package logica.controladores;
import logica.clases.Clinica;
import logica.datatypes.DTDiaConsulta;
import logica.manejadores.ManejadorClinica;
import logica.datatypes.DTConsultaDetalle;
import logica.manejadores.ManejadorConsulta;
import logica.clases.Consulta;
import logica.datatypes.DTConsulta;
import logica.clases.DiaConsulta;
import java.util.Set;
import java.util.HashSet;
import logica.datatypes.DTPaciente;
import logica.manejadores.ManejadorUsuario;

public class ControladorClinica {

	public Set<DTDiaConsulta> listarDatosDiaConsulta() {
	    Set<DTDiaConsulta> res = new HashSet<>();
	    ManejadorClinica mc = ManejadorClinica.get_instance();
	    for (Clinica c : mc.getClinicas().values()) { // esto es un for each
	        res.addAll(c.mostrarDiasConsulta());
	    }
	    return res;
	}
	
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
		DTConsultaDetalle res;
		ManejadorConsulta mcon = ManejadorConsulta.get_instance();
		Consulta cons = mcon.get_consulta(nomC);
		res = cons.getDataConsultaDetalle();
		return res;
		
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
	
	public Set<DTPaciente> listarPacientes() {
	    ManejadorUsuario mu = ManejadorUsuario.get_instance();
	    return mu.get_dtpacientes();
	}
	
	public void reservarConsulta(String nicknamePaciente, String nomC) {
		ManejadorUsuario mu = ManejadorUsuario.g
		
	}
}

