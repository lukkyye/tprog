package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPaciente {

    private String nickname;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaNac;
    private int cantOrdenesAcum;
    private int cantTicketAcum;
    private List<String> antSalud;
    private int doc;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaIngreso;

    public DtPaciente() {
    }

    public DtPaciente(String nickname, LocalDate fechaNac, int cantOrdenesAcum,
                      int cantTicketAcum, List<String> antSalud, int doc,
                      LocalDate fechaIngreso) {
        this.nickname = nickname;
        this.fechaNac = fechaNac;
        this.cantOrdenesAcum = cantOrdenesAcum;
        this.cantTicketAcum = cantTicketAcum;
        this.antSalud = antSalud;
        this.doc = doc;
        this.fechaIngreso = fechaIngreso;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getCantOrdenesAcum() {
        return cantOrdenesAcum;
    }

    public void setCantOrdenesAcum(int cantOrdenesAcum) {
        this.cantOrdenesAcum = cantOrdenesAcum;
    }

    public int getCantTicketAcum() {
        return cantTicketAcum;
    }

    public void setCantTicketAcum(int cantTicketAcum) {
        this.cantTicketAcum = cantTicketAcum;
    }

    public List<String> getAntSalud() {
        return antSalud;
    }

    public void setAntSalud(List<String> antSalud) {
        this.antSalud = antSalud;
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}