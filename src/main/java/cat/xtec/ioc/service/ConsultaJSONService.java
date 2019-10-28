package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Consulta;
import cat.xtec.ioc.domain.repository.ConsultaRepository;
import cat.xtec.ioc.domain.repository.impl.InMemoryConsultaRepository;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/consultes")
@Singleton
public class ConsultaJSONService {

    private ConsultaRepository consultaRepository = new InMemoryConsultaRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addConsultaJSON(Consulta consulta) {
        this.consultaRepository.addConsultaJSON(consulta);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Consulta> getAllConsultes() {
        return this.consultaRepository.getAllConsultes();
    }

    @GET
    @Path("{codi}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_HTML})
    public Consulta getConsultaByCodi(@PathParam("codi") String codi) {
        return this.consultaRepository.getConsultaByCodi(codi);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateVisitantConsulta(Consulta consulta) {
        this.consultaRepository.updateVisitantConsulta(consulta.getCodi(), consulta.getVisitantActual());      
    }

}
