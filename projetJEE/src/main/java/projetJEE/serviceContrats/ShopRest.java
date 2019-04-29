/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

import java.time.LocalDate;
import java.time.Month;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.JSONObject;
import projetJEE.models.Address;
import projetJEE.models.Type;
import projetJEE.models.UserAccount;

/**
 *
 * @author redti
 */

@Path("shoprest/")
public class ShopRest {
    
    @Context
    private UriInfo context;
    

    public ShopRest() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello SHOPREST!";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        return "Data have been saved!"; 
   }
   
    @GET
    @Path("mymethod/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String myMethod(@PathParam("id") String id) {
        JSONObject obj = new JSONObject();
        obj.put("msg", "mymethod id = " + id);
        return obj.toString();
    }
    
    @GET
    @Path("getStoreInfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStoreInfo(@PathParam("id") String id) {
        String name= "name";
        LocalDate date1 = LocalDate.of(1999,Month.MARCH,1);
        //int ID = Integer.parseInt(id);
        String tel= "0103030102";
        String email= "email@gmail.com";
        String addresshtml ="resetpasswordlink";
        UserAccount user = new UserAccount(name, name, email, name, tel, true, date1, 
             date1, addresshtml, date1, false, "", new Type(), new Address());
 
        //return user.toString();
        /*String lastName= "name";
        String tel= "0103030102";
        String email= "email@gmail.com";
        String addresshtml ="resetpasswordlink";
        LocalDate date1 = LocalDate.of(1999,Month.MARCH,1);*/
        JSONObject objType = new JSONObject();
        objType.put("id",id);
        objType.put("type",user.getType().getType());
        JSONObject objCountry = new JSONObject();
        //Recherche id country correspondant
        objCountry.put("id",user.getAddress().getCountry().getID());
        objCountry.put("country",user.getAddress().getCountry().getCountry());
        JSONObject objAddress = new JSONObject();
        objAddress.put("id",user.getAddress().getID());
        objAddress.put("street",user.getAddress().getStreet());
        objAddress.put("city",user.getAddress().getCity());
        objAddress.put("state",user.getAddress().getState());
        objAddress.put("zipCode",user.getAddress().getZipCode());
        objAddress.put("country",objCountry);
        JSONObject obj = new JSONObject();
        obj.put("id",user.getID());
        obj.put("lastName",user.getLastName());
        obj.put("firstName",user.getFirstName());
        obj.put("email",user.getEmail());
        obj.put("password",user.getPassword());
        obj.put("phoneNumber",user.getPhoneNumber());
        obj.put("active",user.getActive());
        obj.put("CreationDate",user.getCreationDate());
        obj.put("lastModificationDate",user.getLastModificationDate());
        obj.put("resetPasswordLink",user.getResetPasswordLink());
        obj.put("resetLinkValidateDate",user.getResetLinkValidateDate());
        obj.put("isRemoved",user.getIsRemoved());
        obj.put("type",objType);
        obj.put("adresse",objAddress);
       
        return obj.toString();
    }
    
    @GET
    @Path("removeStore/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeStore(@PathParam("id") String id) {
        JSONObject obj = new JSONObject();
        obj.put("msg", "mymethod id = " + id);
        return obj.toString();
    }
    
     @POST
    @Path(value="addStore")
    @Produces(MediaType.APPLICATION_JSON)
    public String addStore(@QueryParam("Key") String key,@QueryParam("openingHours") String openingHours,@QueryParam("name") String name,@QueryParam("phoneNumber") String phoneNumber,
                           @QueryParam("email") String email,@QueryParam("lattitude") String lattitude,@QueryParam("longitude") String longitude,@QueryParam("lastModifiedDate") String lastModifiedDate,
                           @QueryParam("lastModifiedBy") String lastModifiedBy,@QueryParam("strret") String street,@QueryParam("city") String city,@QueryParam("state") String state,@QueryParam("zipCode") String zipCode,
                           @QueryParam("country") String country) {
        
        //Construire un objet user et le mettre dans la Bd s'il n'existe pas
	System.out.println("Conversion terminee.");
        
              
        JSONObject objCountry = new JSONObject();
        //Recherche id country correspondant
        objCountry.put("id","adeterminer");
        objCountry.put("country",country);
        
        JSONObject objAddress = new JSONObject();
        //Recherche de id adress à ensemble de donnée en entrée d'adress
        objAddress.put("id","adeterminer");
        objAddress.put("street",street);
        objAddress.put("city",city);
        objAddress.put("zipCode",zipCode);
        objAddress.put("state",state);
        objAddress.put("Country",objCountry);
        
        //LocalDate today = LocalDate.now();
        JSONObject obj = new JSONObject();
        obj.put("id","adeterminer");
        obj.put("key",key);
        obj.put("OpeningHours",openingHours);
        obj.put("name",name);
        obj.put("phoneNumber",phoneNumber);
        obj.put("email",email);
        obj.put("lattitude",lattitude);
        obj.put("longitude",longitude);
        obj.put("lastModifiedDate",lastModifiedDate);
        obj.put("lastModifiedBy",lastModifiedBy);
        obj.put("adresse",objAddress);
        return obj.toString();
    }
}
