/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.domain.repository.impl;

import cat.xtec.ioc.domain.Consulta;
import cat.xtec.ioc.domain.repository.ConsultaRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;


public class InMemoryConsultaRepository implements ConsultaRepository {

    private List<Consulta> consultes = new ArrayList<>();

    @Override
    public void addConsultaJSON(Consulta consulta) {
        this.consultes.add(consulta);
    }

    @Override
    public List<Consulta> initConsultes(String codiconsultes) {
        consultes.clear();
        for (String codiconsulta : codiconsultes.split("##")) {
            Consulta consulta = new Consulta(codiconsulta, 0.0, false, LocalDateTime.now());
            consultes.add(consulta);
        }
        return getAllConsultes();
    }

    @Override
    public List<Consulta> getAllConsultes() {
        return consultes;
    }

    @Override
    public Consulta getConsultaByCodi(String codi) {
        Consulta consultaReturn = null;
        for (Consulta consulta : consultes) {
            if (consulta.getCodi().equals(codi)) {
                consultaReturn = consulta;
            }
        }
        return consultaReturn;
    }

    @Override
    public Consulta updateVisitantConsulta(String codi, Boolean visitant) {
        Consulta consultaReturn = getConsultaByCodi(codi);
        if (consultaReturn != null) {
            Duration duracio = Duration.between(consultaReturn.getDateVisitant(), LocalDateTime.now());
            if (!visitant) {
                consultaReturn.setVisitaAcumulat(consultaReturn.getVisitaAcumulat() + duracio.getSeconds());
            }
            consultaReturn.setVisitantActual(visitant);
            consultaReturn.setDateVisitant(LocalDateTime.now());
        }
        return consultaReturn;
    }

}
