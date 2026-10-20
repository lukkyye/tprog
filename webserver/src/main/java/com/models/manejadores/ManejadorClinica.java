package com.models.manejadores;
import com.models.clases.Clinica;
import com.models.clases.DiaConsulta;
import com.models.excepciones.NombreClinicaAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;


import com.models.excepciones.DiaConsultaAlreadyExistsException;



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

    public void agregarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		if (memberClinica(nombre)) {
			throw new NombreClinicaAlreadyExistsException("Ya existe clinica con el nombre ingresado");
		}
		Clinica clinica = new Clinica(nombre, desc, dir, costoT, costoC, link);
		clinicas.put(nombre, clinica);
	}
      
    public void agregarDiaConsulta(DiaConsulta diaConsulta) {

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
    
    
    

    
}