<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
 
<%
 request.setAttribute("activePage","registerPage"); 
 %>

<!DOCTYPE html>
<html>  
    <head>
        <%@include file="head.jsp" %>
        <title>Home Page</title>
         
        <script src="${cp}/resources/js/js.js"></script>    
    </head>     
    <body>
         <%@include file="header.jsp" %>
         
         <section class="container">
            <h1>Registration</h1>
            
            <%@include file="messageErreur.jsp" %>
            
            <form action="Register" method="POST">
                <div class="formTitle col-sm-12 col-md-12 col-lg-12">
                    <h2>General Information</h2>
                </div>
                <div class="form-row">
                    <!-- first name -->
                    <div class="form-group col-md-6">
                        <label for="firstName">First name</label>
                        <input type="text" class="form-control" required name="firstName" id="firstName" aria-describedby="emailHelp" placeholder="Ex: Louis" <% if(request.getAttribute("firstName") != null) { out.println("value='"+request.getAttribute("firstName")+"'"); }%>>
                    </div>
                    
                    <!-- last name -->
                    <div class="form-group col-md-6">
                        <label for="lastName">Last name</label>
                        <input type="text" class="form-control" required name="lastName" id="lastName" aria-describedby="emailHelp" placeholder="Ex: Durant" <% if(request.getAttribute("lastName") != null) { out.println("value='"+request.getAttribute("lastName")+"'"); }%>>
                    </div>
                </div>
                
                <!-- email -->
                <div class="form-group">
                  <label for="email">Email address</label>
                  <input type="email" class="form-control" required name="email" id="email" aria-describedby="emailHelp" placeholder="louis.durant@mail.com" <% if(request.getAttribute("email") != null) { out.println("value='"+request.getAttribute("email")+"'"); }%>>
                </div>
                
                <!-- password -->
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" required name="password" id="password" placeholder="Password">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="password2">Re-enter the password</label>
                        <input type="password" class="form-control" required name="password2" id="password2" placeholder="Password">
                    </div>
                </div>
                
                <div class="formTitle col-sm-12 col-md-12 col-lg-12">
                    <h2>Contact information</h2>
                </div>
                <!-- phone number -->
                <div class="form-group">
                    <label for="phoneNumber">Phone number</label>
                    <input type="tel" class="form-control" required name="phoneNumber" id="phoneNumber" placeholder="0622335544" maxlength="10" <% if(request.getAttribute("phoneNumber") != null) { out.println("value='"+request.getAttribute("phoneNumber")+"'"); }%>>
                </div>
                
                <!-- adress -->
                <div class="form-group">
                    <label for="inputAddress2">street</label>
                    <input type="text" class="form-control" id="street" required name="street" placeholder="1234 Main St" <% if(request.getAttribute("street") != null) { out.println("value='"+request.getAttribute("street")+"'"); }%>>
                </div>
                
                <div class="form-row">
                    
                    <!-- City -->
                    <div class="form-group col-md-4">
                      <label for="inputCity">City</label>
                      <input type="text" class="form-control" id="inputCity" required name="city" placeholder="Tours" <% if(request.getAttribute("city") != null) { out.println("value='"+request.getAttribute("city")+"'"); }%>>
                    </div>
                    
                    <!-- ZipCode -->
                    <div class="form-group col-md-2">
                      <label for="inputZip">Zip</label>
                      <input type="text" class="form-control" id="inputZip" maxlength="5" required name="zipCode" placeholder="37000" <% if(request.getAttribute("zipCode") != null) { out.println("value='"+request.getAttribute("zipCode")+"'"); }%>>
                    </div>
                    
                    <!-- State -->
                    <div class="form-group col-md-3">
                        <label for="state">State</label>
                        <input type="text" class="form-control" id="validationCustom04" required name="state"  placeholder="Région Centre" <% if(request.getAttribute("state") != null) { out.println("value='"+request.getAttribute("state")+"'"); }%>>
                    </div>

                    <!-- Country -->
                    <div class="form-group col-md-3">
                      <label for="country">Country</label>
                      <input type="text" class="form-control" id="inputZip" placeholder="France" required name="country" <% if(request.getAttribute("country") != null) { out.println("value='"+request.getAttribute("country")+"'"); }%>>
                    </div>
                </div>
                    
                <select id="type" name="type" class="custom-select mr-sm-2 col-sm-6">
                    <option value="2"  <% if(request.getAttribute("type") != null && request.getAttribute("type").equals(2)) { out.println("selected"); }%> >Owner</option>
                    <option value="1"  <% if(request.getAttribute("type") != null && request.getAttribute("type").equals(1)) { out.println("selected"); }%> >Client</option>
                </select>
                <br/>
                <p class="text-right"><button type="submit" class="btn btn-primary " >Register</button></p>
            </form>

         </section>

         <%@include file="footer.jsp" %>
    </body> 
</html>