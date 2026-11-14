package com.soap;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtValoracionConsultaArray", propOrder = {"item"})
public class DtValoracionConsultaArray {
    @XmlElement(nillable = true)
    protected List<DtValoracionConsulta> item;

    public List<DtValoracionConsulta> getItem() {
        if (item == null) {
            item = new ArrayList<>();
        }
        return item;
    }
}