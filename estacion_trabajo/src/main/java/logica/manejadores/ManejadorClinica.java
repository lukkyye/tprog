package logica.manejadores;
import  logica.clases.Clinica;
import  logica.clases.DiaConsulta;
import  logica.excepciones.NombreClinicaAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;


import  logica.excepciones.DiaConsultaAlreadyExistsException;



public class ManejadorClinica {

    private static ManejadorClinica instancia;

    private Map<String, Clinica> clinicas;
    private Map<String, DiaConsulta> diasConsultas;
    private ManejadorClinica() {
        clinicas = new HashMap<>();
        diasConsultas = new HashMap<>();
    }

    public static ManejadorClinica getInstance() {

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

    public void agregarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) throws NombreClinicaAlreadyExistsException{
		if (memberClinica(nombre)) {
			throw new NombreClinicaAlreadyExistsException("Ya existe clinica con el nombre ingresado");
		}
		Clinica clinica = new Clinica(nombre, desc, dir, costoT, costoC, link);
		clinicas.put(nombre, clinica);
	}
      
    public void agregarDiaConsulta(DiaConsulta diaConsulta) throws DiaConsultaAlreadyExistsException {

        if (memberDiaConsulta(diaConsulta.getNom())) {
            throw new DiaConsultaAlreadyExistsException(
                "Ya existe un día de consulta con el nombre ingresado"
            );
        }

        diasConsultas.put(
            diaConsulta.getNom(),
            diaConsulta
        );
    }
    
    public Clinica clinicaDeDiaConsulta(String nomD) {
    	for (Clinica c : getClinicas().values()) {
    		if (c.tieneDiaConsulta(nomD)) {
    			return c;
    		}
    	}
    		return null;
    }
    

    
}