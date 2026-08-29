package logica.controladores;
import logica.clases.Clinica;
import logica.datatypes.DTDiaConsulta;
import logica.manejadores.ManejadorClinica;
import logica.datatypes.DTConsultaDetalle;
import logica.manejadores.ManejadorConsulta;
import logica.clases.Consulta;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

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
}

