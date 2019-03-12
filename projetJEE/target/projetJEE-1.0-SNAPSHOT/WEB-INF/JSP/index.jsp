<%-- 
    Document   : index
    Created on : 5 mars 2019, 17:30:15
    Author     : redti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
 
<!DOCTYPE html>
<html>  
    <head> 
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
    </body> 
</html>