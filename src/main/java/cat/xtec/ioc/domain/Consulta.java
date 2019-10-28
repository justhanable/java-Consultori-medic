
package cat.xtec.ioc.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


public class Consulta{

    private String codi;
    private Double visitaAcumulat;
    private Boolean visitantActual;
    private LocalDateTime dateVisitant;

    public Consulta(){
        
    }

    public Consulta(String codi, Double visitaAcumulat, Boolean visitantActual, LocalDateTime dateVisitant) {
        this.codi = codi;
        this.visitaAcumulat = visitaAcumulat;
        this.visitantActual = visitantActual;
        this.dateVisitant = dateVisitant;
    }

    public Consulta(String codi, String visitaAcumulat, String visitantActual, String dateVisitant) {
        this.codi = codi;
        this.visitaAcumulat = Double.parseDouble(visitaAcumulat);
        this.visitantActual = Boolean.parseBoolean(visitantActual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dateVisitant = LocalDateTime.parse(dateVisitant, formatter);
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public Double getVisitaAcumulat() {
        return visitaAcumulat;
    }

    public void setVisitaAcumulat(Double visitaAcumulat) {
        this.visitaAcumulat = visitaAcumulat;
    }

    public Boolean getVisitantActual() {
        return visitantActual;
    }

    public void setVisitantActual(Boolean visitantActual) {
        this.visitantActual = visitantActual;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    public LocalDateTime getDateVisitant() {
        return dateVisitant;
    }

    public void setDateVisitant(LocalDateTime dateVisitant) {
        this.dateVisitant = dateVisitant;
    }

}
