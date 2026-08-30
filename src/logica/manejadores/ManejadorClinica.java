package logica.manejadores;
import logica.clases.Clinica;
import logica.clases.DiaConsulta;
import logica.clases.Especialidad;
import logica.clases.Medico;
import logica.datatypes.DTConsulta;
import logica.datatypes.TipoMes;
import logica.excepciones.NicknameAlreadyExistsException;
import logica.excepciones.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;



public class ManejadorClinica {

    private static ManejadorClinica instancia;

    private Map<String, Clinica> clinicas;
    private Map<String, DiaConsulta> diasConsultas;
    private ManejadorClinica() {
        clinicas = new HashMap<>();
        diasConsultas = new HashMap<>();
    }

    public static ManejadorClinica get_instance() {

        if (instancia == null) {
            instancia = new ManejadorClinica();
        }

        return instancia;
    }

    public Map<String, Clinica> getClinicas() {
        return clinicas;
    }

    public boolean memberClinica(String nombre) {
        return clinicas.containsKey(nombre);
    }

    public Clinica findClinica(String nombre) {

        if (memberClinica(nombre)) {
            return clinicas.get(nombre);
        }

        return null;
    }

    public Map<String, DiaConsulta> getDiaConsulta() {
        return diasConsultas;
    }

    public boolean memberDiaConsulta(String nombre) {
        return diasConsultas.containsKey(nombre);
    }

    public DiaConsulta findDiasConsultas(String nombre) {

        if (memberDiaConsulta(nombre)) {
            return diasConsultas.get(nombre);
        }

        return null;
    }

    public void agregar_clinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		if (memberClinica(nombre)) {
			throw new NombreClinicaAlreadyExistsException("Ya existe clinica con el nombre ingresado");
		}
		Clinica cl = new Clinica(nombre, desc, dir, costoT, costoC, link);
		clinicas.put(nombre, cl);
	}
    public void add(Clinica c) {

        if (!clinicas.containsKey(c.getNombre())) {
            clinicas.put(c.getNombre(), c);
        }
    }
    public Set<DTConsulta> getConsultasd(String nomD){ //REVISAR ESTA d AL FINAL DEL NOMBRE, ESTA BIEN? DEBERIA DE ESTAR?
    	Set<DTConsulta> res = new HashSet<>();
    	DiaConsulta aux = findDiasConsultas(nomD);
    	
    		res = aux.get_dtconsultas();
    	
    	return res;
    	
    }
    
}
