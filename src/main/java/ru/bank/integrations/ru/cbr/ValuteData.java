package ru.bank.integrations.ru.cbr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ValuteData {

    private List<ValuteCursOnDate> valuteCursOnDateList = new ArrayList<>();

    @JacksonXmlProperty(localName = "ValuteCursOnDate")
    public List<ValuteCursOnDate> getValuteCursOnDateList() {
        return valuteCursOnDateList;
    }

    public void setValuteCursOnDateList(ValuteCursOnDate valuteCursOnDateList) {
        valuteCursOnDateList.setVname(valuteCursOnDateList.getVname().stripTrailing());
        this.valuteCursOnDateList.add(valuteCursOnDateList);
    }

}
