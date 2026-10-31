package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtClinica {

    private String nombre;
    private String desc;
    private String dir;
    private String web;
    private float cTicket;
    private float cConsulta;

    public DtClinica() {
    }

    public DtClinica(String nombre, String desc, String dir, String web,
                     float cTicket, float cConsulta) {
        this.nombre = nombre;
        this.desc = desc;
        this.dir = dir;
        this.web = web;
        this.cTicket = cTicket;
        this.cConsulta = cConsulta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public float getCTicket() {
        return cTicket;
    }

    public void setCTicket(float cTicket) {
        this.cTicket = cTicket;
    }

    public float getCConsulta() {
        return cConsulta;
    }

    public void setCConsulta(float cConsulta) {
        this.cConsulta = cConsulta;
    }
}