<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.text.*,projetJEE.models.*, java.time.format.DateTimeFormatter" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
 
<!DOCTYPE html>
<html>  
    <head>
        <%@include file="head.jsp" %>
        <title>Profile</title>
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/stores.css" />
    </head>     
    <body>
         <%@include file="header.jsp" %>
         
            <section class="container">
            <h1>User profile</h1>
            
            <%@include file="messageErreur.jsp" %>
            <div class="col-sm-8">
            
                    <%
                
            if(request.getAttribute("user")!=null) {

                UserAccount user = (UserAccount) request.getAttribute("user");

                    %>
                    <div class="store">
                        <h2><%= user.getFirstName() + " " + user.getLastName() %></h2>
                        <h5 class="role"><%= user.getType().getType() %></h5>
                        <div class="address">
                            <span class="street"><%= user.getAddress().getStreet() %></span> <br>
                            <span class="zipeCode"><%= user.getAddress().getZipCode() %></span> <span class="city"><%= user.getAddress().getCity() %></span><br>
                            <span class="state"><%= user.getAddress().getState() %></span>, <span class="city"><%= user.getAddress().getCountry().getCountry() %></span>
                        </div>
                        <div class="contact">
                            <span class="phoneNumber"><%= user.getPhoneNumber() %></span> <br>
                            <span class="email"><%= user.getEmail() %></span>
                        </div>
			<div class="dates">
                            <span class="creationDate"><%= user.getCreationDate().toString() %></span> <br>
                            <span class="lastModificationDate"><%= user.getLastModificationDate().toString() %></span>
                        </div>
                    </div>
										
			</div>
                                         <%
                } %>
         </section>
    
         <%@include file="footer.jsp" %>
    </body> 
</html>