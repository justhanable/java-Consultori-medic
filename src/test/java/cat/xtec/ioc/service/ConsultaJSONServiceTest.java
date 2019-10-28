/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Consulta;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ConsultaJSONServiceTest {

    private static final Client client = ClientBuilder.newClient();
    private Consulta consulta1;
    private Consulta consulta2;

    @Before
    public void testBefore() {
        consulta1 = new Consulta("A1", 0.0d, false, LocalDateTime.now());
        URI uri = UriBuilder.fromUri("http://localhost/dawm07eac5consulta/consultajson/consultes").port(8080).build();
        WebTarget target = client.target(uri);
        Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildPost(Entity.entity(consulta1, MediaType.APPLICATION_JSON));
        Response res = invocation.invoke();
        consulta2 = new Consulta("A2", 0.0d, true, LocalDateTime.now());
        invocation = target.request(MediaType.APPLICATION_JSON).buildPost(Entity.entity(consulta2, MediaType.APPLICATION_JSON));
        res = invocation.invoke();
    }

    @Test
    public void getAllConsultes() {

        //Arrange
        URI uri = UriBuilder.fromUri("http://localhost/dawm07eac5consulta/consultajson/consultes").port(8080).build();
        WebTarget target = client.target(uri);
        Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();
        // Act
        Response res = invocation.invoke();
        List<Consulta> consultes = res.readEntity(new GenericType<List<Consulta>>() {
        });
        // Assert 
        //Si hem creat consultes des de curl (exterior a aquest test) llavors s'ha de fer aquest assert                
        assertTrue(consultes.size() > 0);
        //Si no hem creat consultes des de curl (exterior a aquest test) llavors s'ha de fer aquest assert                

        //for (Consulta consulta : consultes) {            
        //    System.out.println("Consulta: "+consulta.getCodi());
        //    assertTrue((consulta.getCodi().equals(consulta1.getCodi())) || (consulta.getCodi().equals(consulta2.getCodi())));
        //}
    }

    @Test
    public void getConsultaByCodi() {
       
        //Arrange
        URI uri = UriBuilder.fromUri("http://localhost/dawm07eac5consulta/consultajson/consultes").port(8080).path("A2").build();
        WebTarget target = client.target(uri);
        Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();
        // Act
        Response res = invocation.invoke();
        Consulta consulta = res.readEntity(Consulta.class);
        // Assert   
        assertTrue(consulta.getCodi().equals(consulta2.getCodi()));

    }

    @Test
    public void updateVisitantConsulta() {

        //Arrange1
        URI uri = UriBuilder.fromUri("http://localhost/dawm07eac5consulta/consultajson/consultes").port(8080).path("A1").build();
        WebTarget target = client.target(uri);
        Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();
        // Act1
        Response res = invocation.invoke();
        Consulta consulta = res.readEntity(Consulta.class);
        consulta.setVisitantActual(false);
        //Arrange 2      
        uri = UriBuilder.fromUri("http://localhost/dawm07eac5consulta/consultajson/consultes").port(8080).build();
        target = client.target(uri);
        invocation = target.request(MediaType.APPLICATION_JSON).buildPut(Entity.entity(consulta, MediaType.APPLICATION_JSON));
        // Act2
        res = invocation.invoke();
        //Arrange3
        uri = UriBuilder.fromUri("http://localhost/dawm07eac5consulta/consultajson/consultes").port(8080).path("A1").build();
        target = client.target(uri);
        invocation = target.request(MediaType.APPLICATION_JSON).buildGet();
        // Act 3
        res = invocation.invoke();
        consulta = res.readEntity(Consulta.class);
        // Assert    
        assertTrue(consulta.getVisitantActual().equals(false));

    }

}
