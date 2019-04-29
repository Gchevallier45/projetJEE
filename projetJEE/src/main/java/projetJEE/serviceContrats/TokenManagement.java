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
        LocalDateTime expirationDate = today.plusMinutes(15);
        
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
        org.json.JSONObject objToken = new  org.json.JSONObject();
        objToken.put("userID",userID);
        objToken.put("token",token);
        objToken.put("ExpirationDate",expirationDate);
        
        return objToken.toString(2);

    }
    
    /**
     * Decrypte Le Token pour verifier ci celui correspond à la string stocké dans la BD
     * @param token 
     * @return  
     */
    public static boolean verifyToken(String token){
        
        boolean verifie = true;
        
        //Decrypte le Token
        String decodedString = new String(DatatypeConverter.parseBase64Binary(token)); 
        System.out.println("Decoded value is " + decodedString);
        logger.info(decodedString);
        
        //Parse la String Décryptée
        
        //Verification pour voir si toujours valide(ExpirationDate) et correspond à la personne qui se connecte
        
        return verifie;
    }
}
