package com.models.clases;
import com.models.datatypes.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Medico extends Usuario{
	private String nroReg;
	private TipoMes mesLic;
	private int porcentajeCosto;
	private String link;
	private Set<String> consultasAsociadas;
	private Set<Especialidad> especialidades;
	
	public Medico(String _nickname, String _password, String _nombre, String _apellido, String _correo, String _nroReg, TipoMes _mesLic, int _porcentajeCosto, String _link) {
		super(_nickname, _password,  _nombre, _apellido, _correo);
		this.nroReg = _nroReg;
		this.mesLic = _mesLic;
		this.porcentajeCosto = _porcentajeCosto;
		this.link = _link;
		consultasAsociadas = new HashSet<String>();
		especialidades = new HashSet<Especialidad>();
	}
	
	public void agregarConsultaAsociada(String nomC) {
		consultasAsociadas.add(nomC);
	}
	
	public boolean tieneEspecialidad(String espNom) {
		for (Especialidad e : especialidades) {
			if (e.getNombre().equals(espNom)) {
				return true;
			}
		}
		return false;
	}
	
	public void agregarEspecialidad(Especialidad especialidad) {
		especialidades.add(especialidad);
	}
	
	public Set<Especialidad> get_especialidades() {
		return especialidades;
	}
	
	public DTMedico get_dtmedico() {
		return new DTMedico(nickname, nroReg, mesLic, porcentajeCosto, link);
	}
	
	public Set<String> get_consultassoc(){
		return consultasAsociadas;
	}
	
	public String get_nicknameM() {
		return this.nickname;
	}
	public String get_nroReg(){ 
		return this.nroReg; 
	}
	public TipoMes get_mesLic() {
		return this.mesLic; 
	}
	public int get_porcentajeCosto() {
		return this.porcentajeCosto; 
	}
	public String get_link() {
		return this.link;
	}
	
	public void set_link(String _link) {
		this.link=_link;
	}
	public void set_porcentajeCosto(int _porcentajeCosto) { 
		this.porcentajeCosto=_porcentajeCosto;
	}
	
	public void set_nroReg(String _nroReg) {
		this.nroReg=_nroReg;
	}
	
	public void set_mesLic(TipoMes _mesLic) {
		this.mesLic=_mesLic;
	}
	
	public void set_especialidades(Set<Especialidad> nuevas) {
		this.especialidades = nuevas;
	}
	
	public List<String> get_nombresEspecialidades() {
		return especialidades.stream().map(x->x.getNombre()).toList();
	}
	
}