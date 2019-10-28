/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.domain.repository;

import cat.xtec.ioc.domain.Consulta;
import java.util.List;


public interface ConsultaRepository {
    List<Consulta> initConsultes(String codiconsultes);
    void addConsultaJSON(Consulta consulta);
    List<Consulta> getAllConsultes();
    Consulta getConsultaByCodi(String codi);
    Consulta updateVisitantConsulta(String codi, Boolean visitant);        
}
