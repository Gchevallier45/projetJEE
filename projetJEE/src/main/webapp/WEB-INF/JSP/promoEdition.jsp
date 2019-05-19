<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.text.*,projetJEE.models.*, java.time.format.DateTimeFormatter" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
<!DOCTYPE html>
<html>  
    <head>
        <%@include file="head.jsp" %>
        <title>${title}</title>
    </head>     
    <body>
         <%@include file="header.jsp" %>
         
         <section class="container">
            <h1>${title}</h1>
            
            <%@include file="messageErreur.jsp" %>
            
            <%
            if(request.getAttribute("stores")!=null) {
            %>
            
            <form action="${actionForm}" method="POST" enctype="multipart/form-data">
                
                 <!-- Store -->
                <label for="idStore">Store</label>
                <select id="type" name="idStore" class="custom-select mr-sm-2 col-sm-6">
                 <%
                List<Store> stores = (List<Store>) request.getAttribute("stores");
                for(Store store : stores) {
                %>

                    <option value="<%= store.getID() %>"  <% if(request.getAttribute("idStore") != null && request.getAttribute("idStore").equals(Integer.toString(store.getID()))) { out.println("selected"); }%> ><%= store.getName() %></option>
               <% } %>
                </select>
                
                
                <div class="form-row">
                    <!-- Promotion Title -->
                    <div class="form-group col-md-6">
                        <label for="promoTitle">Promotion title</label>
                        <input type="text" class="form-control" name="promoTitle" id="promoTitle" required placeholder="Ex: Get 12 cookies for $5.00" <% if(request.getAttribute("promoTitle") != null) { out.println("value='"+request.getAttribute("promoTitle")+"'"); }%> >
                    </div>
                </div>

                <!-- shortDescription -->
                <div class="form-group">
                  <label for="shortDescription">Short description</label>
                  <input type="text" class="form-control" name="shortDescription" id="shortDescription" required placeholder="Enjoy a box of mouth-watering freshly baked cookies and save." <% if(request.getAttribute("shortDescription") != null) { out.println("value='"+request.getAttribute("shortDescription")+"'"); }%> >
                </div>    
                    
                <!-- longDescription -->
                <div class="form-group">
                    <label for="longDescription">Long Description</label>
                    <textarea class="form-control" rows="5" id="longDescription" name="longDescription" required><% if(request.getAttribute("longDescription") != null) { out.println(request.getAttribute("longDescription")); }%></textarea>
                </div>
                
                <div class="form-row">
                    <!-- startDate -->
                    <div class="form-group col-md-6">
                        <label for="startDate">Start date</label>
                        <input type="date" id="startDate" class="form-control" name="startDate" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" <% if(request.getAttribute("startDate") != null) { out.println("value='"+request.getAttribute("startDate")+"'"); }%>>
                        <span class="validity"></span>
                     </div>
                    <!-- endDate -->
                    <div class="form-group col-md-6">
                        <label for="endDate">End date</label>
                        <input type="date" id="endDate" class="form-control" name="endDate" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" <% if(request.getAttribute("endDate") != null) { out.println("value='"+request.getAttribute("endDate")+"'"); }%> >
                        <span class="validity"></span>
                     </div>
                </div>
                        
                <div class="form-row">
                    <label for="endDate">Picture</label>
                    <input type="file" class="custom-file" name="file" />
                </div>

                <input type="text" style="display:none" name="idPromo" <% if(request.getAttribute("idPromo") != null) { out.println("value='"+request.getAttribute("idPromo")+"'"); }%>>
                      
                <p class="text-right"><button type="submit" class="btn btn-primary " >Save</button></p>
            </form>
           <%
            }
            else {
                if(session.getAttribute("userStatus").equals("Administrator")) {
                 out.println("There is no shop at the moment.");
                } else
                {
                 out.println("You have no shop at the moment.");
                }
            }
            %>


         </section>
         <!--<script type="text/javascript" src="${cp}/resources/js/storeEdition.js"></script>-->
         <%@include file="footer.jsp" %>
    </body> 
</html>