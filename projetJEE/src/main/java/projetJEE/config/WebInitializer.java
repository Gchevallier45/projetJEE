/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.config; 
 
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic; 
 
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet; 
 
public class WebInitializer implements WebApplicationInitializer { 
 
    private String TMP_FOLDER = "/tmp"; 
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;
    
    @Override 
    public void onStartup(ServletContext servletContext) throws ServletException {         
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);        
        ctx.setServletContext(servletContext);
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);    
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, 
          MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
        servlet.setMultipartConfig(multipartConfigElement);
    } 
 
}
