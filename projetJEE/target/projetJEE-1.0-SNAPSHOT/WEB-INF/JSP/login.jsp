<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
 
<%
 request.setAttribute("activePage","login"); 
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
            <h1>Login</h1>
           
            <form>
                <!-- email -->
                <div class="form-group">
                  <label for="email">Email address</label>
                  <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email">
                </div>
                
                <!-- password -->
                <div class="form-group">
                  <label for="password2">Re-enter the password</label>
                        <input type="password" class="form-control" name="password2" id="password2" placeholder="Password">
                </div>
                
                <p class="text-right"><button type="submit" class="btn btn-primary " >Login</button></p>
            </form>

         </section>

         <%@include file="footer.jsp" %>
    </body> 
</html>