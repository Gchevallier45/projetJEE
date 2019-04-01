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
@XmlRootElement
public class BaseResponse {
    
    public static final int FAILURE = 0;
    public static final int SUCCESS = 1;

    private String message;
    private int status;

    public BaseResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public BaseResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
