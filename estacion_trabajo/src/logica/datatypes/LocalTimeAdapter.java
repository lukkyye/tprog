package logica.datatypes;

import java.time.LocalTime;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

    @Override
    public LocalTime unmarshal(String value) {
        return value == null ? null : LocalTime.parse(value);
    }

    @Override
    public String marshal(LocalTime value) {
        return value == null ? null : value.toString();
    }
}