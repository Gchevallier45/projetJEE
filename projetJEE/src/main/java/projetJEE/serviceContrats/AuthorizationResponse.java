/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author redti
 */
public class AuthorizationResponse extends BaseResponse{
      private String privateKey;

    public AuthorizationResponse() {
    }

    public AuthorizationResponse(int status, String message, String privateKey) {
        super(message, status);
        this.privateKey = privateKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        return "AuthorizationResponse{" +
                super.toString() +
                "privateKey='" + privateKey + '\'' +
                '}';
    }
}
