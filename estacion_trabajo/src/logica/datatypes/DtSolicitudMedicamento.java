package logica.datatypes;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtSolicitudMedicamento {
	private String nicknamePaciente;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate entrega;
	private String direccion;
	private boolean usaBenef;
	private float costoMed;
	
	public DtSolicitudMedicamento(String nicknamePaciente, LocalDate entrega, String direccion, boolean usaBenef, float costoMed) {
		this.nicknamePaciente = nicknamePaciente;
		this.entrega = entrega;
		this.direccion = direccion;
		this.usaBenef = usaBenef;
		this.costoMed = costoMed;
	}
	public String getNicknamePaciente() {
		return nicknamePaciente;
	}
	public LocalDate getEntrega() {
		return entrega;
	}
	public String getDireccion() {
		return direccion;
	}
	public boolean getUsaBenef() {
		return usaBenef;
	}
	public float getCostoMed() {
		return costoMed;
	}

}
