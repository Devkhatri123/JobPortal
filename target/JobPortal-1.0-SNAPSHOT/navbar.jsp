<%@ page import="model.User,model.Company" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>JobBox</title>
  </head>
  <body>
      <%Company company = null;
          User user = (User)request.getSession(false).getAttribute("user");
      if(user == null){
         company = (Company) request.getSession(false).getAttribute("company");
      }
      %>
    <div class="site-container">
      <!-- Desktop Navigation -->
      <nav class="desktop-nav">
        <div class="nav-container">
          <div class="nav-left">
            <a href="Index.jsp" class="logo">
              <div class="logo-box">
                <div class="logo-inner"></div>
              </div>
              <span class="logo-text">JobBox</span>
            </a>
            
            <div class="nav-items">
              <div class="nav-item active">
                <span>Home</span>
                <div class="active-line"></div>
              </div>
              <div class="nav-item dropdown">
                   <a href="browseJobs.jsp" class="logo">
                <span>Browse Jobs</span>
                   </a>
               </div>
               <% if (user != null) { %>
             <div class="nav-item dropdown">
                 <a href="appliedJobs.jsp" class="logo">
                <span>View applied jobs</span>
                 </a>
               </div>
                
                 <div class="nav-item dropdown profile">
                <span>Profile</span>
                <ul class="sub-menu">
                    <li><a href="profile.jsp">View profile</a></li>
                     <li><a href="editUserProfile">Update profile</a></li>
                    <li><a href="signOut">SignOut</a></li>
                  </ul>
               </div>
      <% } else if(company != null) { %>
        <div class="nav-item dropdown">
            <a href="postJob">Post jobs</a>
               </div>
                
                 <div class="nav-item dropdown profile">
                <span>Profile</span>
                <ul class="sub-menu">
                    <li><a href="getProfile?companyId=<%=company.getC_id()%>">View profile</a></li>
                     <li><a href="editCompanyProfile.jsp">Update profile</a></li>
                    <li><a href="signOut">SignOut</a></li>
                  </ul>
               </div>
      <% } %>
                
                
            </div>
          </div>
           <% if (user != null || company != null ) { %>
           <a href="signOut" class="signin-btn">Sign Out</a>
           <% }else {%>
          <div class="nav-right">
            <a href="${pageContext.request.contextPath}/signUp" class="register-link">Register</a>
            <a href="signIn" class="signin-btn">Sign in</a>
          </div>
            <%}%>
        </div>
      </nav>

      <!-- Mobile Navigation -->
      <nav class="mobile-nav">
        <a href="/" class="logo">
          <div class="logo-box">
            <div class="logo-inner"></div>
          </div>
          <span class="logo-text">JobBox</span>
        </a>
        
        <button id="menu-toggle" class="menu-toggle">
          <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none"><line x1="3" y1="12" x2="21" y2="12"></line><line x1="3" y1="6" x2="21" y2="6"></line><line x1="3" y1="18" x2="21" y2="18"></line></svg>
        </button>
      </nav>

      <!-- Mobile Menu -->
      <div id="mobile-menu" class="mobile-menu">
        <div class="mobile-menu-header">
          <div class="search-container">
            <svg class="search-icon" viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
            <input type="text" placeholder="Search..." class="search-input">
          </div>
          <button id="menu-close" class="menu-close">
            <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
          </button>
        </div>

        <div class="mobile-menu-content">
          <div class="mobile-menu-item">
            <span>Home</span>
          </div>
          <div class="mobile-menu-item">
            <span>Browse Jobs</span>
           </div>
         
          
          <% if (user != null) { %>
            <div class="account-section">
            <h3>Your Account</h3>
            <div class="mobile-menu-item">
              <span>View applied jobs</span>
            </div>
             <div class="mobile-menu-item">
              <span>Profile</span>
            </div>
             <div class="mobile-menu-item">
              <a href="signOut">Sign Out</a>
            </div>
          </div
      <% } %>
            
            
         
        </div>
      </div>

    </div>

  </body>
</html>