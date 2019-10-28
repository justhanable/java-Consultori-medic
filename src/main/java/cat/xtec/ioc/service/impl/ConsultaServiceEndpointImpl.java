/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Consulta;
import cat.xtec.ioc.domain.repository.ConsultaRepository;
import cat.xtec.ioc.domain.repository.impl.InMemoryConsultaRepository;
import cat.xtec.ioc.service.ConsultaServiceEndpoint;
import java.util.List;
import javax.jws.WebService;

@WebService(serviceName = "ConsultaService",
        endpointInterface = "cat.xtec.ioc.service.ConsultaServiceEndpoint")

public class ConsultaServiceEndpointImpl implements ConsultaServiceEndpoint{
    private final ConsultaRepository consultaRepository = new InMemoryConsultaRepository();

    @Override
    public List<Consulta> initConsultes(String telefons) {
        return consultaRepository.initConsultes(telefons);        
    }

    @Override
    public List<Consulta> getAllConsultes() {
        return consultaRepository.getAllConsultes();
    }

    @Override
    public Consulta getConsultaByCodi(String codi) {
        return consultaRepository.getConsultaByCodi(codi);
    }

    @Override
    public Consulta updateVisitantConsulta(String codi, Boolean visitant) {
        return consultaRepository.updateVisitantConsulta(codi, visitant);
    }

}
