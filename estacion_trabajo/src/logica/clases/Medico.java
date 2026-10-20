package logica.clases;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logica.datatypes.DTMedico;
import logica.datatypes.TipoMes;

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
	
	public Set<Especialidad> getEspecialidades() {
		return especialidades;
	}
	
	public DTMedico getDtmedico() {
		return new DTMedico(nickname, nroReg, mesLic, porcentajeCosto, link);
	}
	
	public Set<String> getConsultasAsoc(){
		return consultasAsociadas;
	}
	
	public String getNicknameM() {
		return this.nickname;
	}
	public String getNroReg(){ 
		return this.nroReg; 
	}
	public TipoMes getMesLic() {
		return this.mesLic; 
	}
	public int getPorcentajeCosto() {
		return this.porcentajeCosto; 
	}
	public String getLink() {
		return this.link;
	}
	
	public void setLink(String _link) {
		this.link=_link;
	}
	public void setPorcentajeCosto(int _porcentajeCosto) { 
		this.porcentajeCosto=_porcentajeCosto;
	}
	
	public void setNroReg(String _nroReg) {
		this.nroReg=_nroReg;
	}
	
	public void setMesLic(TipoMes _mesLic) {
		this.mesLic=_mesLic;
	}
	
	public void setEspecialidades(Set<Especialidad> nuevas) {
		this.especialidades = nuevas;
	}
	
	public List<String> getNombreEspecialidades() {
		return especialidades.stream().map(x-> x.getNombre()).toList();
	}
	
}