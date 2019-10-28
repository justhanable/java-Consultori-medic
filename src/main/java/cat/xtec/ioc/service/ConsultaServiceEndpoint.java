/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Consulta;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface ConsultaServiceEndpoint {
    @WebMethod List<Consulta> initConsultes(String codiconsultes);
    @WebMethod List<Consulta> getAllConsultes();
    @WebMethod Consulta getConsultaByCodi(String codi);
    @WebMethod Consulta updateVisitantConsulta(String codi, Boolean visitant);       
}
