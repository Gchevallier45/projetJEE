<!-- Bandeau -->
<header class="container">
  <div class="row">
    <!-- Logo -->
    <div class="logo col-sm-12 col-md-2 col-lg-2 align-self-center text-center">
      <a href="<%=request.getContextPath()%>"><img src="${cp}/resources/img/logo.png" alt="Logo" class="imgLogo"></a>
    </div>
    <!-- titre site -->
    <div class="titre col-sm-12 offset-md-1 col-md-5 offset-lg-2 col-lg-4 text-center" >
        <h1>Our website<br/>in J2EE</h1>
    </div>
    <!-- option connexion, deconnexion, profil -->
    <div class="option col-sm-12  offset-md-0  col-md-4 offset-lg-1  col-lg-3 align-self-center text-center">
      <% if(session.getAttribute("userId") != null)
      {
       %>

          <!-- Profil utilisateur -->
          <div class="dropdown show dropDownProfil">
            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="oi" data-glyph="person"></span> Profil</button>

            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink d-flex justify-content-center">
              <span class="elementDrepdown-menu"><%=session.getAttribute("userFirstName")%> <%=session.getAttribute("userLastName")%></span>
              <a class="dropdown-item lien" href="<%=request.getContextPath()%>/MonProfil">Mon profil</a>

              
              <div class="dropdown-divider"></div>
              <button type="button" class="btn btn-light elementDrepdown-menu" onclick="self.location.href='${cp}/Logout'">Déconnexion</button>
            </div>
          </div>
          <%
      }
      else
      {
         %>
              <button type="button" class="btn btn-light" onclick="self.location.href='${cp}/Login';">Login</button>
              <button type="button" class="btn btn-light" onclick="self.location.href='${cp}/Register';">Register</button>
         <%
      }
      %>
    </div>
  </div>
</header>
<!-- Menu de navigation -->
<nav class="container" >
<ul class="nav nav-tabs">
  <li class="nav-item">
    <a <% if(request.getAttribute("activePage") != null && request.getAttribute("activePage").equals("HomePage")) { %> <%="class='nav-link active'"%><% } else { %> class="nav-link" <% } %> href="<%=request.getContextPath()%>">Home Page</a>
  </li>
  <li class="nav-item">
    <a <% if(request.getAttribute("activePage") != null && request.getAttribute("activePage").equals("Stores")) { %> <%="class='nav-link active'"%><% } else { %> class="nav-link" <% } %> href="<%=request.getContextPath()%>/Stores">Stores</a>
  </li>
  <% if( session.getAttribute("userStatus") != null && session.getAttribute("userStatus").equals("Owner")) { %>
  <li class="nav-item">
    <a <% if(request.getAttribute("activePage") != null && request.getAttribute("activePage").equals("AddStore")) { %> <%="class='nav-link active'"%><% } else { %> class="nav-link" <% } %> href="<%=request.getContextPath()%>/AddStore">Add Store</a>
  </li>
  <% } %>
</ul>
</nav>