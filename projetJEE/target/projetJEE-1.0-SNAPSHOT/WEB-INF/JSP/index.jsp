<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
 
<%
 request.setAttribute("activePage","HomePage"); 
 %>

<!DOCTYPE html>
<html>  
    <head>
        <%@include file="head.jsp" %>
        <title>Home Page</title>
    </head>     
    <body>
         <%@include file="header.jsp" %>
         
         <section class="container">
            <h1>Home Page</h1>
            
            <div class="col-sm-12 col-md-5">
                <img src="${cp}/resources/img/welecome.jpg" alt="Logo" class="img-fluid">
          </div>
         </section>

         <%@include file="footer.jsp" %>
    </body> 
</html>