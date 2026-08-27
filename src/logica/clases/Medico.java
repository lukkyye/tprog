package logica.clases;
import logica.datatypes.TipoMes;

public class Medico extends Usuario{
	private Integer nroReg;
	private TipoMes mesLic;
	private Integer porcentajeCosto;
	private String link;
	
	public Medico(String _nickname, String _nombre, String _apellido, String _correo, Integer _nroReg, TipoMes _mesLic, Integer _porcentajeCosto, String _link) {
		super(_nickname, _nombre, _apellido, _correo);
		this.nroReg=_nroReg;
		this.mesLic=_mesLic;
		this.porcentajeCosto=_porcentajeCosto;
		this.link=_link;
	}
	
	public Integer get_nroReg(){ return this.nroReg; }
	public TipoMes get_mesLic() {return this.mesLic; }
	public Integer get_porcentajeCosto() {return this.porcentajeCosto; }
	public String get_link() {return this.link;}
	
	public void set_link(String _link) {this.link=_link;}
	public void set_porcentajeCosto(Integer _porcentajeCosto) { this.porcentajeCosto=_porcentajeCosto;}
}