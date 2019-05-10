<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.text.*,projetJEE.models.*, java.time.format.DateTimeFormatter" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" /> 
 
<!DOCTYPE html>
<html>  
    <head>
        <%@include file="head.jsp" %>
        <title>Promotions</title>
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/promotions.css" />
    </head>     
    <body>
         <%@include file="header.jsp" %>
         
         <section class="container">
            <h1>Promotions</h1>
            <input class="form-control" id="myInput" type="text" placeholder="Enter criterias">
            <%@include file="messageErreur.jsp" %>
            
            <div class="col-sm-8">
            <%
                
            if(request.getAttribute("promotions")!=null) {

                List<Promotion> promotionList = (List<Promotion>) request.getAttribute("promotions");
                for(Promotion promotion : promotionList) {
                    %>
                    
                    <div class="promotion" >
                        <h2><%= promotion.getTitle() %></h2>
                        <div class="shortDescription">
                            <span><%= promotion.getShortDescription() %></span>
                        </div>
                        <div class="longDescription">
                            <span><%= promotion.getLongDescription() %></span>
                        </div>
                        <div class="dates">
                            <span><%= promotion.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE) %></span> - <span><%= promotion.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE) %></span> 
                        </div>
                        <% 
                            
                         if(session.getAttribute("userStatus") != null && (session.getAttribute("userStatus").equals("Owner") || session.getAttribute("userStatus").equals("Administrator"))){ %>
                        <div class="actionsOnPromotion">
                            <button type="button" class="btn btn-info" onclick="document.location.href='${cp}/UpdatePromotion?promotionId=<%= promotion.getID() %>'">Modifier</button> <button type="button" class="btn btn-warning"   onclick="removePromotion('<%= promotion.getID() %>', '<%= promotion.getTitle() %>')">Supprimer</button>
                        </div>
                        <% } %>
                    </div>
                                

                    <%
                }  
            } 


            else { out.println("No promotion displayed."); }

            %>
            </div>
            

         </section>
         
         
         <%@include file="footer.jsp" %>
         <script type="text/javascript" src="${cp}/resources/js/promotions.js"></script>
        <script>
            $(document).ready(function(){
            $("#myInput").on("keyup", function() {
              var value = $(this).val().toLowerCase();
                  var valueSplitted = value.split(" ");
              $(".promotion").filter(function() {
                    var acceptStore = true;
                    for(var i=0; i<valueSplitted.length; i++){
                          if(($(this).text().toLowerCase().indexOf(valueSplitted[i]) > -1) == false){
                                  acceptStore = false;
                          };
                    }
                $(this).toggle(acceptStore);
              });
            });
          });
          
          function removePromotion(promotionId, title) {
                if(confirm("Do you want to delete the promotion '"+title+"'?"))
                    document.location.href="${cp}/DeletePromotion?promotionId="+promotionId;
            }


        </script>
    </body> 
</html>