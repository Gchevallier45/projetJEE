<%-- 
    Document   : index
    Created on : 5 mars 2019, 17:30:15
    Author     : redti
--%>
<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>  
    <head>
        <!-- feuilles de style -->
        <link rel="stylesheet"
          href="/projetJEE/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
        
        <!-- scripts js -->
        <script src="/projetJEE/webjars/jquery/3.3.1/jquery.min.js"></script>
        <script src="/projetJEE/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Spring 5 Web MVC via Annotations</title>      
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/site.css" />    
        <script src="${cp}/resources/js/js.js"></script>    
    </head>     
    <body>      
        <h1>${cp}</h1>
        <h4>Spring 5 Web MVC via Annotations</h4>
        Spring says: <span class="blue">${msg}</span>
        <br /> 
        <br />   
        <a href="javascript:void(0)" onclick="test()">Click to test JS</a> 
        
        <!-- test bootstrap -->
        <div class="container"><br/>
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert"
                  aria-label="close">×</a>
                <strong>Success!</strong> Webjars is working as we expected.
            </div>
        </div>
    </body> 
</html>