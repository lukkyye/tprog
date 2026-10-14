package com.models.logica.manejadores;
import com.models.logica.clases.Clinica;
import com.models.logica.clases.DiaConsulta;
import com.models.logica.clases.Especialidad;
import com.models.logica.clases.Medico;
import com.models.logica.datatypes.DTConsulta;
import com.models.logica.excepciones.NombreClinicaAlreadyExistsException;
import com.models.logica.datatypes.TipoMes;
import com.models.logica.excepciones.NicknameAlreadyExistsException;
import com.models.logica.excepciones.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import com.models.logica.excepciones.DiaConsultaAlreadyExistsException;



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
      
    public void agregarDiaConsulta(DiaConsulta diaConsulta) {

        if (memberDiaConsulta(diaConsulta.get_nom())) {
            throw new DiaConsultaAlreadyExistsException(
                "Ya existe un día de consulta con el nombre ingresado"
            );
        }

        diasConsultas.put(
            diaConsulta.get_nom(),
            diaConsulta
        );
    }
    
    
    

    
}