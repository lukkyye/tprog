package logica.clases;
import java.time.LocalDate;
import java.util.ArrayList;
public class Paciente extends Usuario{
	private LocalDate fechaNac;
	private Integer cantOrdenesAcum;
	private Integer cantTicketAcum;
	private ArrayList<String> antSalud;
	private Integer doc;
	private LocalDate fechaIngreso;
	
	public Paciente(String nickname, String nombre, String apellido, String correo, LocalDate _fechaNac, Integer _doc, LocalDate _fechaIngreso){
			super(nickname, nombre, apellido, correo);
			this.fechaNac=_fechaNac;
			this.cantOrdenesAcum=0;
			this.cantTicketAcum=0;
			this.antSalud=new ArrayList<String>();
			this.doc=_doc;
			this.fechaIngreso=_fechaIngreso;
	}
	
}
