<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.text.*,projetJEE.models.*, java.time.format.DateTimeFormatter" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
 
<!DOCTYPE html>
<html>  
    <head>
        <%@include file="head.jsp" %>
        <title>Home Page</title>
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/stores.css" />  
    </head>     
    <body>
         <%@include file="header.jsp" %>
         
         <section class="container">
            <h1>Stores</h1>
            
            <%@include file="messageErreur.jsp" %>
            
            <div class="col-sm-8">
            <%
                
            if(request.getAttribute("stores")!=null) {

                List<Store> storesList = (List<Store>) request.getAttribute("stores");
                for(Store store : storesList) {
                    %>
                    
                    <div class="store" >
                        <h2><%= store.getName() %></h2>
                        <div class="address">
                            <span class="street"><%= store.getAddress().getStreet() %></span> <br/>
                            <span class="zipeCode"><%= store.getAddress().getStreet() %></span> <span class="city"><%= store.getAddress().getCity() %></span><br/>
                            <span class="state"><%= store.getAddress().getState() %></span>, <span class="city"><%= store.getAddress().getCountry().getCountry() %></span>
                        </div>
                        <div class="contact">
                            <span class="phoneNumber"><%= store.getPhoneNumber() %></span> <br/>
                            <span class="email"><%= store.getEmail() %></span>
                        </div>
                        <div class="openingHours">
                            <table>
                                <tr>
                                    <th>Monday</th>
                                    <th>Tuesday</th>
                                    <th>Wednesday</th>
                                    <th>Thursday</th>
                                    <th>Friday</th>
                                    <th>Saturday</th>
                                    <th>Sunday</th>
                                </tr>
                                <tr>
                                    <%
                                    String[] days = { "Mon", "Tues", "Wed", "Thu", "Fri", "Sat", "Sun"};
                                    DateTimeFormatter FOMATTER_HOUR = DateTimeFormatter.ofPattern("HH:mm");
                                    Map<String, String> hourliesFrom = new HashMap(); 
                                    Map<String, String> hourliesTo = new HashMap();
                                    Map<String, Boolean> isClosed = new HashMap();
                                    Map<String, Boolean> id24h = new HashMap();
                                    
                                    OpeningHour openingHours = store.getOpeningHours();

                                    hourliesFrom.put(days[0], openingHours.getMonOpen().format(FOMATTER_HOUR));
                                    hourliesTo.put(days[0], openingHours.getMonClose().format(FOMATTER_HOUR));

                                    hourliesFrom.put(days[1], openingHours.getTuesOpen().format(FOMATTER_HOUR));
                                    hourliesTo.put(days[1], openingHours.getTuesClose().format(FOMATTER_HOUR));

                                    hourliesFrom.put(days[2], openingHours.getWedOpen().format(FOMATTER_HOUR));
                                    hourliesTo.put(days[2], openingHours.getWedClose().format(FOMATTER_HOUR));

                                    hourliesFrom.put(days[3], openingHours.getThuOpen().format(FOMATTER_HOUR));
                                    hourliesTo.put(days[3], openingHours.getThuClose().format(FOMATTER_HOUR));

                                    hourliesFrom.put(days[4], openingHours.getFriOpen().format(FOMATTER_HOUR));
                                    hourliesTo.put(days[4], openingHours.getFriClose().format(FOMATTER_HOUR));

                                    hourliesFrom.put(days[5], openingHours.getSatOpen().format(FOMATTER_HOUR));
                                    hourliesTo.put(days[5], openingHours.getSatClose().format(FOMATTER_HOUR));

                                    hourliesFrom.put(days[6], openingHours.getSunOpen().format(FOMATTER_HOUR));
                                    hourliesTo.put(days[6], openingHours.getSunClose().format(FOMATTER_HOUR));


                                    // opening hours
                                     for(int indiceDay = 0; indiceDay < 7; indiceDay++) {
                                        if(hourliesFrom.get(days[indiceDay]).equals("00:00") && hourliesTo.get(days[indiceDay]).equals("23:59")) 
                                        {
                                             out.println("<td>24/24</td>");
                                        }
                                        else {
                                            if(hourliesFrom.get(days[indiceDay]).equals("00:00") && hourliesTo.get(days[indiceDay]).equals("00:00")) 
                                            {
                                                 out.println("<td>Closed</td>");
                                            } else {
                                                out.println("<td>"+hourliesFrom.get(days[indiceDay])+" - "+hourliesTo.get(days[indiceDay])+"</td>");
                                            }
                                        }
                                     }
                                    %>
                                </tr>
                            </table>
                        </div>
                        <% 
                            
                         if( (session.getAttribute("userStatus") != null && session.getAttribute("userStatus").equals("Owner")) && 
                            (session.getAttribute("userId") != null && (session.getAttribute("userId")).equals(store.getLastModifiedBy().getID()))){ %>
                        <div class="actionsOnStore">
                            <button type="button" class="btn btn-info" onclick="document.location.href='${cp}/UpdateStore?storeId=<%= store.getID() %>'">Modifier</button> <button type="button" class="btn btn-warning"   onclick="removeStore(<%= store.getID() %>, <%= store.getName() %>);">Supprimer</button>
                        </div>
                        <% } %>
                    </div>
                                

                    <%
                }  
            } 


            else { out.println("No shop displayed."); }

            %>
            </div>
            

         </section>

         <%@include file="footer.jsp" %>
    </body> 
</html>

<script>
    
    function removeStore(storeId, storeName) {
        if(confirm("Do you want to delete the store '"+storeName+"'?");)
            document.location.href="${cp}/DeleteStore?storeId=storeId)"";
    }
    
</script>