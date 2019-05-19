<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
<!DOCTYPE html>
<html>  
    <head>
        <%@include file="head.jsp" %>
        <title>${title}</title>
        
        <link rel="stylesheet" type="text/css" href="${cp}/resources/js/leaflet/leaflet.css" />
	<link rel="stylesheet" type="text/css" href="${cp}/resources/css/map.css"/>

	<script type="text/javascript" src="${cp}/resources/js/leaflet/leaflet.js"></script>
	<link rel="stylesheet" href="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.css" />
	<script src="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.js"></script>
    </head>     
    <body>
         <%@include file="header.jsp" %>
         
         <section class="container">
            <h1>${title}</h1>
            
            <%@include file="messageErreur.jsp" %>
            
            <form action="${actionForm}" method="POST">
                <div class="formTitle col-sm-12 col-md-12 col-lg-12">
                    <h2>General Information</h2>
                </div>
                <div class="form-row">
                    <!-- first name -->
                    <div class="form-group col-md-6">
                        <label for="name">Store name</label>
                        <input type="text" class="form-control" name="name" required id="name" aria-describedby="emailHelp" placeholder="Ex: Boutique de Tours" <% if(request.getAttribute("name") != null) { out.println("value='"+request.getAttribute("name")+"'"); }%> >
                    </div>
                   
                </div>
                    
                <div class="formTitle col-sm-12 col-md-12 col-lg-12">
                    <h2>Contact information</h2>
                </div>
                    
                <!-- email -->
                <div class="form-group">
                  <label for="email">Email address</label>
                  <input type="email" class="form-control" name="email"  required id="email" aria-describedby="emailHelp" placeholder="louis.durant@mail.com" <% if(request.getAttribute("email") != null) { out.println("value='"+request.getAttribute("email")+"'"); }%> >
                </div>    
                    
                <!-- phone number -->
                <div class="form-group">
                    <label for="phoneNumber">Phone number</label>
                    <input type="tel" class="form-control" name="phoneNumber"  required id="phoneNumber" placeholder="0622335544" maxlength="10" <% if(request.getAttribute("phoneNumber") != null) { out.println("value='"+request.getAttribute("phoneNumber")+"'"); }%> >
                </div>
                
                <!-- adress -->
                <div class="form-group">
                    <label for="inputAddress2">street</label>
                    <input type="text" class="form-control" id="street" name="street" required placeholder="1234 Main St" <% if(request.getAttribute("street") != null) { out.println("value='"+request.getAttribute("street")+"'"); }%> >
                </div>
                
                <div class="form-row">
                    
                    <!-- City -->
                    <div class="form-group col-md-4">
                      <label for="inputCity">City</label>
                      <input type="text" class="form-control" id="inputCity" name="city" required placeholder="Tours" <% if(request.getAttribute("city") != null) { out.println("value='"+request.getAttribute("city")+"'"); }%> >
                    </div>
                    
                    <!-- ZipCode -->
                    <div class="form-group col-md-2">
                      <label for="inputZip">Zip code</label>
                      <input type="text" class="form-control" id="inputZip" maxlength="5"  required name="zipCode" placeholder="37000" <% if(request.getAttribute("zipCode") != null) { out.println("value='"+request.getAttribute("zipCode")+"'"); }%> >
                    </div>
                    
                    <!-- State -->
                    <div class="form-group col-md-3">
                        <label for="state">State</label>
                        <input type="text" class="form-control" id="validationCustom04" name="state" required placeholder="Région Centre" <% if(request.getAttribute("state") != null) { out.println("value='"+request.getAttribute("state")+"'"); }%> >
                    </div>

                    <!-- Country -->
                    <div class="form-group col-md-3">
                      <label for="country">Country</label>
                      <input type="text" class="form-control" id="inputZip" placeholder="France" required name="country" <% if(request.getAttribute("country") != null) { out.println("value='"+request.getAttribute("country")+"'"); } %> >
                    
                    <!-- Lattitude  -->
                    <input style="display:none" type="text" name="latitude" <% if(request.getAttribute("latitude") != null) { out.println("value='"+request.getAttribute("latitude")+"'"); } %> >
                    
                    <!-- Longitude  -->
                    <input style="display:none" type="text" name="longitude" <% if(request.getAttribute("longitude") != null) { out.println("value='"+request.getAttribute("longitude")+"'"); } %> >
                </div>
                    
                <label for="localisation">Put the localisation of store on the map</label>
                <div id="map"></div>
   
                <div class="formTitle col-sm-12 col-md-12 col-lg-12">
                    <h2>Opening hours</h2>
                </div>
                    
                <table class="table">
                    
                    <tr>
                        <th></th>
                        <th>Monday</th>
                        <th>Tuesday</th>
                        <th>Wednesday</th>
                        <th>Thursday</th>
                        <th>Friday</th>
                        <th>Saturday</th>
                        <th>Sunday</th>
                    </tr>
                    <tr>
                        <th>From</th>
                        <td><input type="time" class="form-control" name="fromMon" <% if(request.getAttribute("fromMon") != null) { out.println("value='"+request.getAttribute("fromMon")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="fromTues" <% if(request.getAttribute("fromTues") != null) { out.println("value='"+request.getAttribute("fromTues")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="fromWed" <% if(request.getAttribute("fromWed") != null) { out.println("value='"+request.getAttribute("fromWed")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="fromThu" <% if(request.getAttribute("fromThu") != null) { out.println("value='"+request.getAttribute("fromThu")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="fromFri" <% if(request.getAttribute("fromFri") != null) { out.println("value='"+request.getAttribute("fromFri")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="fromSat" <% if(request.getAttribute("fromSat") != null) { out.println("value='"+request.getAttribute("fromSat")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="fromSun" <% if(request.getAttribute("fromSun") != null) { out.println("value='"+request.getAttribute("fromSun")+"'"); } %> /></td>
                    </tr>
                    <tr>
                        <th>To</th>
                        <td><input type="time" class="form-control" name="toMon" <% if(request.getAttribute("toMon") != null) { out.println("value='"+request.getAttribute("toMon")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="toTues" <% if(request.getAttribute("toTues") != null) { out.println("value='"+request.getAttribute("toTues")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="toWed" <% if(request.getAttribute("toWed") != null) { out.println("value='"+request.getAttribute("toWed")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="toThu" <% if(request.getAttribute("toThu") != null) { out.println("value='"+request.getAttribute("toThu")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="toFri" <% if(request.getAttribute("toFri") != null) { out.println("value='"+request.getAttribute("toFri")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="toSat" <% if(request.getAttribute("toSat") != null) { out.println("value='"+request.getAttribute("toSat")+"'"); } %> /></td>
                        <td><input type="time" class="form-control" name="toSun" <% if(request.getAttribute("toSun") != null) { out.println("value='"+request.getAttribute("toSun")+"'"); } %> /></td>
                    </tr>
                    <tr>
                        <th>Closed</th>
                        <td><div class="checkbox"><label><input onChange="closed(0)" type="checkbox" id="c1" name="closedMon" <% if(request.getAttribute("closedMon") != null && (request.getAttribute("closedMon").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="closed(1)" type="checkbox" id="c2" name="closedTues" <% if(request.getAttribute("closedTues") != null && (request.getAttribute("closedTues").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="closed(2)" type="checkbox" id="c3" name="closedWed"  <% if(request.getAttribute("closedWed") != null && (request.getAttribute("closedWed").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="closed(3)" type="checkbox" id="c4" name="closedThu" <% if(request.getAttribute("closedThu") != null && (request.getAttribute("closedThu").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="closed(4)" type="checkbox" id="c5" name="closedFri" <% if(request.getAttribute("closedFri") != null && (request.getAttribute("closedFri").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="closed(5)" type="checkbox" id="c6" name="closedSat" <% if(request.getAttribute("closedSat") != null && (request.getAttribute("closedSat").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="closed(6)" type="checkbox" id="c7" name="closedSun" <% if(request.getAttribute("closedSun") != null && (request.getAttribute("closedSun").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                    </tr>
                    <tr>
                        <th>24 hrs</th>
                        <td><div class="checkbox"><label><input onChange="is24hrsDay(0)" type="checkbox" id="h1" name="24hrsMon" <% if(request.getAttribute("24hrsMon") != null && (request.getAttribute("24hrsMon").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="is24hrsDay(1)" type="checkbox" id="h2" name="24hrsTues" <% if(request.getAttribute("24hrsTues") != null && (request.getAttribute("24hrsTues").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="is24hrsDay(2)"type="checkbox" id="h3" name="24hrsWed" <% if(request.getAttribute("24hrsWed") != null && (request.getAttribute("24hrsWed").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="is24hrsDay(3)" type="checkbox" id="h4" name="24hrsThu" <% if(request.getAttribute("24hrsThu") != null && (request.getAttribute("24hrsThu").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="is24hrsDay(4)" type="checkbox" id="h5" name="24hrsFri" <% if(request.getAttribute("24hrsFri") != null && (request.getAttribute("24hrsFri").equals("true"))) { out.println(" checked '"); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="is24hrsDay(5)" type="checkbox" id="h6" name="24hrsSat" <% if(request.getAttribute("24hrsSat") != null && (request.getAttribute("24hrsSat").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                        <td><div class="checkbox"><label><input onChange="is24hrsDay(6)" type="checkbox" id="h7" name="24hrsSun" <% if(request.getAttribute("24hrsSun") != null && (request.getAttribute("24hrsSun").equals("true"))) { out.println(" checked "); } %> ></label></div></td>
                    </tr>
                </table>
                
                 <!-- Longitude  -->
                <input type="text" style="display:none" name="idStore" <% if(request.getAttribute("idStore") != null) { out.println("value='"+request.getAttribute("idStore")+"'"); }%>>
                      
                <p class="text-right"><button type="submit" class="btn btn-primary " >Save</button></p>
            </form>

         </section>
         <script type="text/javascript" src="${cp}/resources/js/storeEdition.js"></script>
         <%@include file="footer.jsp" %>
    </body> 
</html>