package logica.clases;
import logica.datatypes.TipoMes;

public class Medico extends Usuario{
	private int nroReg;
	private TipoMes mesLic;
	private int porcentajeCosto;
	private String link;
	
	public Medico(String _nickname, String _nombre, String _apellido, String _correo, int _nroReg, TipoMes _mesLic, int _porcentajeCosto, String _link) {
		super(_nickname, _nombre, _apellido, _correo);
		this.nroReg=_nroReg;
		this.mesLic=_mesLic;
		this.porcentajeCosto=_porcentajeCosto;
		this.link=_link;
	}
	
	public int get_nroReg(){ return this.nroReg; }
	public TipoMes get_mesLic() {return this.mesLic; }
	public int get_porcentajeCosto() {return this.porcentajeCosto; }
	public String get_link() {return this.link;}
	
	public void set_link(String _link) {this.link=_link;}
	public void set_porcentajeCosto(int _porcentajeCosto) { this.porcentajeCosto=_porcentajeCosto;}
}