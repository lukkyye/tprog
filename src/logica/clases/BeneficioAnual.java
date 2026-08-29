package logica.clases;

public class BeneficioAnual {
	private int anio;
	private int cantOrdenes;
	private int cantTicket;
	
	public BeneficioAnual (int anio, int cantOrdenes, int cantTicket) {
		this.anio = anio;
		this.cantOrdenes = cantOrdenes;
		this.cantTicket = cantTicket;
	}
	
	public int getAnio() {
		return anio;
	}
	public int getCantOrdenes() {
		return cantOrdenes;
	}
	public int getCantTicket() {
		return cantTicket;
	}
}
