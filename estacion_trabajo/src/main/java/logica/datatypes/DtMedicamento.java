package logica.datatypes;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtMedicamento {
	private String nombre;
	private String posologia;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate indicacion;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate vencimiento;
	private boolean usado;
	private float costoMed;
	private DtSolicitudMedicamento solMed;
	private String nomC;

	
    public DtMedicamento() {
    }
	public DtMedicamento(String nombre, String posologia, LocalDate indicacion, LocalDate vencimiento, boolean usado, float costoMed, DtSolicitudMedicamento solMed, String nomC) {
		this.nombre = nombre;
		this.posologia = posologia;
		this.indicacion = indicacion;
		this.vencimiento = vencimiento;
		this.usado = usado;
		this.costoMed = costoMed;
		this.solMed = solMed;
		this.nomC = nomC;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPosologia() {
		return posologia;
	}
	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}
	public LocalDate getIndicacon() {
		return indicacion;
	}
	public void setIndicacion(LocalDate indicacion) {
		this.indicacion = indicacion;
	}
	public LocalDate getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(LocalDate vencimiento) {
		this.vencimiento = vencimiento;
	}
	public boolean getUsado() {
		return usado;
	}
	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	public float getCostoMed() {
		return costoMed;
	}
	public void setCostoMed(float costoMed) {
		this.costoMed = costoMed;
	}
	public DtSolicitudMedicamento getSolMed() {
		return solMed;
	}
	public void setSolMed(DtSolicitudMedicamento solMed) {
		this.solMed = solMed;
	}
	public String getNomC() { 
		return nomC; 
	}
	public void setNomC(String nomC) { 
		this.nomC = nomC; 
	}
	
}
