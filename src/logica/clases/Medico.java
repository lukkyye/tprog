package logica.clases;
import logica.datatypes.*;
import java.util.HashSet;

public class Medico extends Usuario{
	private int nroReg;
	private TipoMes mesLic;
	private int porcentajeCosto;
	private String link;
	private HashSet<String> consultasAsociadas;
	private HashSet<Especialidad> especialidades;
	
	public Medico(String _nickname, String _nombre, String _apellido, String _correo, int _nroReg, TipoMes _mesLic, int _porcentajeCosto, String _link) {
		super(_nickname, _nombre, _apellido, _correo);
		this.nroReg=_nroReg;
		this.mesLic=_mesLic;
		this.porcentajeCosto=_porcentajeCosto;
		this.link=_link;
		especialidades=new HashSet<Especialidad>();
	}
	
	public void agregarEspecialidad(Especialidad e) {
		especialidades.add(e);
	}
	
	public HashSet<Especialidad> get_especialidades() {
		return especialidades;
	}
	
	public DTMedico get_dtmedico() {
		return new DTMedico(nroReg, mesLic, porcentajeCosto, link);
	}
	
	public HashSet<String> get_consultassoc(){
		return consultasAsociadas;
	}
	
	
	public int get_nroReg(){ 
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
	
}