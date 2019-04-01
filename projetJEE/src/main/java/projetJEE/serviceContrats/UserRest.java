/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import javax.ws.rs.POST;
import java.util.Date;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.JSONObject;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import projetJEE.models.Address;
import projetJEE.models.Type;
import projetJEE.models.UserAccount;
import projetJEE.parser.ParserUserInfo;

import projetJEE.serviceContrats.*;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.domain.repository.UserAccountRepository;

//@RestController
//@RequestMapping("userrest/")
@Path("userrest/")
public class UserRest{

    @Context
    private UriInfo context;
    
    @Resource
    UserAccountManager uamanager;
    

    public UserRest() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello REST!";
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
        return obj.toString(2);
    }
    @GET
    //@RestController
    @Path("getUserInfo/{id}")
    //@GetMapping("getUserInfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserAccount getUserInfo(@PathVariable int id) {
        //getUser de Id sur la base de donnée et le stocker dans la variable user
        //UserAccount user = new UserAccount();
        String name= "name";
        LocalDate date1 = LocalDate.of(1999,Month.MARCH,1);
        //int ID = Integer.parseInt(id);
        String tel= "0103030102";
        String email= "email@gmail.com";
        String addresshtml ="resetpasswordlink";
        /*UserAccount user = new UserAccount(name, name, email, name, tel, true, date1, 
             date1, addresshtml, date1, false, new Type(), new Address());*/
        UserAccount user = uamanager.getUserAccountById(id);
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
       
        return user;
    }
    
    @POST
    @Path(value="register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String register(@QueryParam("lastName") String lastName,@QueryParam("firstName") String firstName,@QueryParam("email") String email,@QueryParam("password") String password,
                           @QueryParam("phoneNumber") String phoneNumber, @QueryParam("type") String type,@QueryParam("address") String address,@QueryParam("city") String city,@QueryParam("zipCode") String zipCode,
                           @QueryParam("state") String state,@QueryParam("country") String country) {
        
        //Construire un objet user et le mettre dans la Bd s'il n'existe pas
	System.out.println("Conversion terminee.");
        
        JSONObject objType = new JSONObject();
        //Recherche de id type lié au label en entrée
        objType.put("id","adeterminer");
        objType.put("labelType",type);
        
        JSONObject objCountry = new JSONObject();
        //Recherche id country correspondant
        objCountry.put("id","adeterminer");
        objCountry.put("country",country);
        
        JSONObject objAddress = new JSONObject();
        //Recherche de id adress à ensemble de donnée en entrée d'adress
        objAddress.put("id","adeterminer");
        objAddress.put("street",address);
        objAddress.put("city",city);
        objAddress.put("zipCode",zipCode);
        objAddress.put("state",state);
        objAddress.put("Country",objCountry);
        
        LocalDate today = LocalDate.now();
        JSONObject obj = new JSONObject();
        obj.put("id","adeterminer");
        obj.put("firstName",firstName);
        obj.put("lastName",lastName);
        obj.put("email",email);
        obj.put("password",password);
        obj.put("phoneNumber",phoneNumber);
        obj.put("active",true);
        obj.put("CreationDate",today);
        obj.put("lastModificationDate",today);
        obj.put("resetPasswordLink","none");
        obj.put("resetLinkValidateDate","none");
        obj.put("isRemoved",true);
        obj.put("type",objType);
        obj.put("adresse",objAddress);
        return obj.toString(2);
    }
    
    @POST
    @Path(value = "authorization_service")
    //@JwtCustomToken
    @Produces(MediaType.APPLICATION_JSON)
    public String authorizationService(@QueryParam("username") String userName, @QueryParam("password") String password) {
        
        if (userName.isEmpty()){
            System.out.println("username field cannot be empty!");
            return "username field cannot be empty!";
        }else if (password.isEmpty()){
            System.out.println("password field cannot be empty!");
            return "password field cannot be empty!";
        }else{
        //Mettre en place un controle username password avent de fournir la clé de connexion
        //et mettre en place isactive à 1 pour email correspondant dans la base de donnée 
        String privateKey = JwTokenHelper.getInstance().generatePrivateKey(userName, password);
        return "You're authenticated successfully. Private key will be valid for 30 mins : \n" + privateKey;
        }
    }
    
    /*@POST
    @Path(value = "authorization_service")
    //@JwtCustomToken
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorizationService(@QueryParam("username") String userName, @QueryParam("password") String password) {
        if (userName.isEmpty())
            return getResponse(new BaseResponse("username field cannot be empty!",BaseResponse.FAILURE));
        if (password.isEmpty())
            return getResponse(new BaseResponse("password field cannot be empty!",BaseResponse.FAILURE));
        String privateKey = JwTokenHelper.getInstance().generatePrivateKey(userName, password);
        return getResponse(new AuthorizationResponse(BaseResponse.SUCCESS, "You're authenticated successfully. Private key will be valid for 30 mins", privateKey));
    }*/
    
    /*@GET
    @Path("allDevices")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevices() {
         return getResponse(new DeviceCollection(Arrays.asList(new Device("Electric Kettle", 1, true), new Device("Computer", 1, true), new Device("Motorcycle", 3, false), new Device("Sandwich Maker", 4, true))));
    }*/
    
    /*@GET
    @Path("allDevices")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevices() {
         return getResponse(new DeviceCollection(Arrays.asList(new Device("Electric Kettle", 1, true), new Device("Computer", 1, true), new Device("Motorcycle", 3, false), new Device("Sandwich Maker", 4, true))));
    }*/
    //identificationpost
    //id=?&
    /* public UserAccount(String firstName, String lastName, String email, String password, String phoneNumber,  boolean active, LocalDate creationDate, 
             LocalDate lastModificationDate, String resetPasswordLink, LocalDate resetLinkValidateDate, boolean isRemoved, Type type, Address address);*/
   
}
