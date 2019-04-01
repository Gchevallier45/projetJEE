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
           
            <form>
                <div class="formTitle col-sm-12 col-md-12 col-lg-12">
                    <h2>General Information</h2>
                </div>
                <div class="form-row">
                    <!-- first name -->
                    <div class="form-group col-md-6">
                        <label for="firstName">First name</label>
                        <input type="text" class="form-control" name="firstName" id="firstName" aria-describedby="emailHelp" placeholder="Ex: Louis">
                    </div>
                    
                    <!-- last name -->
                    <div class="form-group col-md-6">
                        <label for="lastName">Last name</label>
                        <input type="text" class="form-control" name="lastName" id="lastName" aria-describedby="emailHelp" placeholder="Ex: Durant">
                    </div>
                </div>
                
                <!-- email -->
                <div class="form-group">
                  <label for="email">Email address</label>
                  <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="louis.durant@mail.com">
                  <small id="email" class="form-text text-muted">We'll never share your email with anyone else.</small>
                </div>
                
                <!-- password -->
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="password2">Re-enter the password</label>
                        <input type="password" class="form-control" name="password2" id="password2" placeholder="Password">
                    </div>
                </div>
                
                <div class="formTitle col-sm-12 col-md-12 col-lg-12">
                    <h2>Contact information</h2>
                </div>
                <!-- phone number -->
                <div class="form-group">
                    <label for="phoneNumber">Phone number</label>
                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="0622335544" maxlength="10">
                </div>
                
                <!-- adress -->
                <div class="form-group">
                    <label for="inputAddress2">Address</label>
                    <input type="text" class="form-control" id="inputAddress2" name="address" placeholder="1234 Main St">
                </div>
                
                <div class="form-row">
                    
                    <!-- City -->
                    <div class="form-group col-md-4">
                      <label for="inputCity">City</label>
                      <input type="text" class="form-control" id="inputCity" name="city" placeholder="Tours">
                    </div>
                    
                    <!-- ZipCode -->
                    <div class="form-group col-md-2">
                      <label for="inputZip">Zip</label>
                      <input type="text" class="form-control" id="inputZip" name="zipCode" placeholder="37000">
                    </div>
                    
                    <!-- State -->
                    <div class="form-group col-md-3">
                        <label for="validationCustom04">State</label>
                        <input type="text" class="form-control" id="validationCustom04" name=state"  placeholder="Région Centre">
                    </div>

                    <!-- Country -->
                    <div class="form-group col-md-3">
                      <label for="inputZip">Country</label>
                      <input type="text" class="form-control" id="inputZip" placeholder="France" name="contry">
                    </div>
                </div>

                <p class="text-right"><button type="submit" class="btn btn-primary " >Register</button></p>
            </form>

         </section>

         <%@include file="footer.jsp" %>
    </body> 
</html>