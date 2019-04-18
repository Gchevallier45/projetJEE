/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import projetJEE.bl.concrete.UserAccountManager;
import javax.annotation.Resource;
import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.core.UriInfo;
import org.json.JSONException;
//import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/userrest")

public class UserRest {
@Resource
    UserAccountManager uamanager;
@Resource
    TypeManager tymanager;
@Resource
    AddressManager admanager;
@Resource
    CountryManager comanager;
//
@Context
    private UriInfo uriInfo;

    /*@Autowired*/
    private static Logger logger;
    //private final Logger logger = (Logger) LoggerFactory.getLogger(UserRest.class);

    //@Autowired
    private KeyGenerator keyGenerator;
//
    public UserRest() {
    }
 
    @GetMapping(value = "/getTest", produces = MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello  User REST!";
    }

    @RequestMapping(value = "/putTest", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)

    public String putJson(@RequestBody String content) {
        return content + " Data user have been saved!";
    }

 

    @GetMapping(value = "/getUserInfo/{id}", produces = MediaType.APPLICATION_JSON)
    public String getUserInfo(@PathVariable("id") String id) throws Exception {
          org.json.JSONObject obj = new  org.json.JSONObject();
        try{
            UserAccount user = uamanager.getUserAccountById(Integer.parseInt(id));
            
            org.json.JSONObject objType = new  org.json.JSONObject();
            objType.put("id",id);
            objType.put("type",user.getType().getType());
            JSONObject objCountry = new JSONObject();
            //Recherche id country correspondant
            objCountry.put("id",user.getAddress().getCountry().getID());
            objCountry.put("country",user.getAddress().getCountry().getCountry());

            org.json.JSONObject objAddress = new  org.json.JSONObject();
            objAddress.put("id",user.getAddress().getID());
            objAddress.put("street",user.getAddress().getStreet());
            objAddress.put("city",user.getAddress().getCity());
            objAddress.put("state",user.getAddress().getState());
            objAddress.put("zipCode",user.getAddress().getZipCode());
            objAddress.put("country",objCountry);

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
        }catch(NumberFormatException | JSONException e){
            throw new Exception("Cet ID ne correspond à aucun compte dans la base de données");
        }        
        return obj.toString(2);
    }
  
  
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String register(@QueryParam("lastName") String lastName,@QueryParam("firstName") String firstName,@QueryParam("email") String email,@QueryParam("password") String password,
                           @QueryParam("phoneNumber") String phoneNumber, @QueryParam("type") String type,@QueryParam("address") String address,@QueryParam("city") String city,@QueryParam("zipCode") String zipCode,
                           @QueryParam("state") String state,@QueryParam("country") String country) throws Exception {
        
           org.json.JSONObject obj = new  org.json.JSONObject();
        try{
            //Construire un objet user et le mettre dans la Bd s'il n'existe pas
            System.out.println("Conversion terminee.");
            
            //Sans controle de l'existence prealable de l objet on créé l'objet user
            boolean active = true;
            LocalDate creationDate = LocalDate.now();
            LocalDate lastModificationDate = LocalDate.now();
            String resetPasswordLink = "none";
            LocalDate resetLinkValidateDate = LocalDate.now();
            boolean isRemoved = false;
            
            //Cherche si type Existe
            //Tester la case sur la recherche
            Type typeUser = tymanager.getTypeByName(type);
            if(typeUser == null){
                typeUser = new Type(type);
                tymanager.addType(typeUser);
            }
            
            
            //Cherche si country Existe
            //Tester la case sur la recherche
            Country countryUser = comanager.getCountryByName(country);
            if(countryUser == null){
                countryUser = new Country(country);
                comanager.addCountry(countryUser);
            }
            
            
            //Cherche si address Existe
             Address addressUser = new Address(address, city, state, zipCode, countryUser);
            //admanager.AddressExiste(addressUser);//regarde si l'address existe ou pas sinon on le créé
            admanager.addAddress(addressUser); // en attendant implémentation on considère que c'est toujours une nouvelle adresse
           
            UserAccount newUser = new UserAccount(firstName, lastName, email, password, phoneNumber, active, creationDate, lastModificationDate, resetPasswordLink, resetLinkValidateDate, isRemoved, typeUser, addressUser);
       
            //On ajoute le user à la BD
            uamanager.addUserAccount(newUser);
        
            //Récupérer user creer et l'afficher
            //Trouver une method qui permet de récupérer utilisateur autrment que par l'id
            /*int id =2;
            UserAccount user = uamanager.getUserAccountById(id);
            
            org.json.JSONObject objType = new  org.json.JSONObject();
            objType.put("id",id);
            objType.put("type",user.getType().getType());
            JSONObject objCountry = new JSONObject();
            //Recherche id country correspondant
            objCountry.put("id",user.getAddress().getCountry().getID());
            objCountry.put("country",user.getAddress().getCountry().getCountry());

            org.json.JSONObject objAddress = new  org.json.JSONObject();
            objAddress.put("id",user.getAddress().getID());
            objAddress.put("street",user.getAddress().getStreet());
            objAddress.put("city",user.getAddress().getCity());
            objAddress.put("state",user.getAddress().getState());
            objAddress.put("zipCode",user.getAddress().getZipCode());
            objAddress.put("country",objCountry);

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
            obj.put("adresse",objAddress);*/
        }catch(Exception e){
            throw new Exception("Le compte utilisateur n a pas pu etre cree");
        }        
        
        //obj.toString(2)
       return " Le Compte utilisateur a bien ete cree";
    }
    
    @RequestMapping(value = "/testp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String testp(@QueryParam("email") String email) {
        return "hello "+email;
    }
    
    @RequestMapping(value = "/authorizationService", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String authorizationService(@QueryParam("email") String email, @QueryParam("password") String password) throws Exception {
       
        //try{
            if (email.isEmpty()){
                System.out.println("email field cannot be empty!");
                return "email field cannot be empty!";
            }else if (password.isEmpty()){
                System.out.println("password field cannot be empty!");
                return "password field cannot be empty!";
            }else{
                //Mettre en place un controle username password avent de fournir la clé de connexion
                //et mettre en place isactive à 1 pour email correspondant dans la base de donnée
               /* if(uamanager.getUserAccountByLoginPassword(email, password) != null){
                    return "hello "+ uamanager.getUserAccountByLoginPassword(email, password).toString();
                }else{
                    return "hello ";
                }*/
                
                try{
                    if(uamanager.getUserAccountByLoginPassword(email, password) != null){
                        String privateKey = JwTokenHelper.getInstance().generatePrivateKey(email, password);
                        return "You're authenticated successfully. Private key will be valid for "+ JwTokenHelper.getInstance().getExpiration_limit() +" mins : \n" + privateKey;
                    }else{
                        return "You're not authenticated, change email or password field";
                    }
                }catch(Exception e){
                    throw new Exception("L acces a la base de donnee n est pas possible");
                } 
            }
        /*}catch(Exception e){
            throw new Exception("L authentification n a pa pu se faire");
        } */
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("email") String login,@QueryParam("password") String password) throws Exception {
        try {

            logger.log(Level.INFO, "#### login/password : {0}/{1}", new Object[]{login, password});

            // Authenticate the user using the credentials provided
            authenticate(login, password);

            // Issue a token for the user
            String token = issueToken(login);

            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
        
        //return " Vous etes bien connecte en tant que "+ email;
    }
    
    private void authenticate(String login, String password) throws Exception {
        
        /*TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_LOGIN_PASSWORD, User.class);
        query.setParameter("login", login);
        query.setParameter("password", PasswordUtils.digestPassword(password));
        User user = query.getSingleResult();*/
        //Get utilisateur correspondant  au login et password
        UserAccount user = uamanager.getUserAccountByLoginPassword(login, password);
        if (user == null)
            throw new SecurityException("Invalid user/password");
    }
    
     private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken;
    jwtToken = Jwts.builder()
            .setSubject(login)
            .setIssuer(uriInfo.getAbsolutePath().toString())
            .setIssuedAt(new Date())
            .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();
        logger.log(Level.INFO, "#### generating token for a key : {0} - {1}", new Object[]{jwtToken, key});
        return jwtToken;

    }
     private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
