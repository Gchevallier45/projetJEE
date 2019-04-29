/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

 import org.apache.commons.codec.binary.Base64;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.UserAccount;

/**
 *
 * @author redti
 */
public class TokenManagement {
 
    private static final Logger logger = Logger.getLogger(TokenManagement.class);
    
    public static String generateToken(int userID){
        
        //Generation of a random UUID
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        
        //Stocke UUID dans la base de données pour l'utilisateur correspondant à l'ID     // à implémenter
        
        //Generation of the Expiration Date (here localdatetime + 15 min)
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime expirationDate = today.plusMonths(6);
        
        //Creation de l'objet JSON
        org.json.JSONObject obj = new  org.json.JSONObject();
        obj.put("userID",userID);
        obj.put("uuid",randomUUIDString);
        obj.put("dateExp",expirationDate);
        
        //Encrypte le String de l'objet en BASE64
        byte[] bytesEncoded = Base64.encodeBase64(obj.toString(2).getBytes());
        System.out.println("encoded value is " + new String(bytesEncoded));
        String token = new String(bytesEncoded);
   
        //Renvoie les infos de connection ainsi que le token généré
        /*org.json.JSONObject objToken = new  org.json.JSONObject();
        objToken.put("userID",userID);
        objToken.put("token",token);
        objToken.put("ExpirationDate",expirationDate);*/
        
        return token;

    }
    
    /**
     * Decrypte Le Token pour verifier ci celui correspond à la string stocké dans la BD
     * @param token 
     * @return  
     */
    public static boolean verifyToken(String token, UserAccountManager uamanager) throws Exception{
        
        boolean verifie = true;
        
        //Decrypte le Token
        String decodedString = new String(DatatypeConverter.parseBase64Binary(token)); 
        System.out.println("Decoded value is " + decodedString);
        logger.info(decodedString);
        
        //Parse la String Décryptée //Parse nfo D'un JSSOn contenu dans une string
        org.json.JSONObject objToken = new  org.json.JSONObject();
        int userIDMember = 0;
        String UUIDMember = "";
        LocalDateTime expirationDate = LocalDateTime.now();
        
        String UUID = "";
        try{
            UserAccount userBD = uamanager.getUserAccountById(userIDMember);
            //UUID = userBD.getID();
        }catch(Exception e){
            throw new Exception("Le userIDMember du token ne correspond à aucun utilisateur");
        }
                
        //Verification pour voir si toujours valide(ExpirationDate) et correspond à la personne qui se connecte
        if(!UUID.equals(UUIDMember)){//Controle UUID attribuer à userIDMember doit correspndre à celui donné dans le Token
              throw new Exception("L'UUID du token ne correspond pas à l'UUID de l'utilisateur");
        }else if(LocalDateTime.now().isAfter(expirationDate) || LocalDateTime.now().isEqual(expirationDate)){   // Controle Date Expiration
              throw new Exception("La Date d'expiration est depasse veuillez vous reconnecter");  
        }
        
        return verifie;
    }
}
