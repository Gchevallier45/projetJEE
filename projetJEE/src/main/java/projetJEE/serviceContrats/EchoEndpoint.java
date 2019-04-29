/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

import java.time.LocalDate;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.UserAccount;
import javax.annotation.Resource;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projetJEE.bl.concrete.*;
import projetJEE.models.*;
/**
 *
 * @author redti
 */
@RestController
@RequestMapping("/api/echo")

//@Produces(TEXT_PLAIN)
public class EchoEndpoint {
 
    //@GET
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public Response echo(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }
 
    //@GET
    //@Path("jwt")
    @RequestMapping(value = "/jwt", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response echoWithJWTToken(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }
}
