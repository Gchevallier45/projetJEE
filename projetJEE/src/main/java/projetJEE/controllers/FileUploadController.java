/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController
{
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submitP(HttpServletRequest request, @RequestParam("file") MultipartFile uploadedFile, ModelMap modelMap) {
        System.out.println("capaaaassseee");
        String path = request.getServletContext().getRealPath("/WEB-INF/resources/img/") + File.separator + uploadedFile.getOriginalFilename();
        System.out.print(path);
        try {
            modelMap.addAttribute("file", uploadedFile);
            File file = new File(path);
            uploadedFile.transferTo(file);
        } catch (IOException ex) {
            Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fileupload";
    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String submitG(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
        return "fileupload";
    }
}