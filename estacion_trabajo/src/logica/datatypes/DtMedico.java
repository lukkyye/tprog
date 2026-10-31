package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtMedico {

    private String nickname;
    private String nroReg;
    private TipoMes mesLic;
    private Integer porcentajeCost;
    private String link;

    public DtMedico() {
    }

    public DtMedico(String nickname, String nroReg, TipoMes mesLic,
                    Integer porcentajeCost, String link) {
        this.nickname = nickname;
        this.nroReg = nroReg;
        this.mesLic = mesLic;
        this.porcentajeCost = porcentajeCost;
        this.link = link;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNroReg() {
        return nroReg;
    }

    public void setNroReg(String nroReg) {
        this.nroReg = nroReg;
    }

    public TipoMes getMesLic() {
        return mesLic;
    }

    public void setMesLic(TipoMes mesLic) {
        this.mesLic = mesLic;
    }

    public Integer getPorcentajeCost() {
        return porcentajeCost;
    }

    public void setPorcentajeCost(Integer porcentajeCost) {
        this.porcentajeCost = porcentajeCost;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}