<%-- 
    Document   : signIn
    Created on : Apr 5, 2025, 4:47:41 PM
    Author     : Dev khatri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="css/signin.css">
           <link rel="stylesheet" href="css/navbar.css">
        <title>JSP Page</title>
       
    </head>
    <body>
        <% User user = (User)request.getSession(false).getAttribute("user");
        if(user != null){
          RequestDispatcher rd = request.getRequestDispatcher("/index");
          rd.forward(request, response);
        }
        %>
         <%String msg = (String)request.getAttribute("errorMsg");%>
         <jsp:include page="navbar.jsp" />
   <div class="login-container">
<!--        <p class="welcome-text">Welcome back!</p>-->
        <h1 class="login-title">Sign Up</h1>
        <p class="login-subtitle">Access to all features. No credit card required.</p>
        
       
        
        <form action="${pageContext.request.contextPath}/signUp" method="POST">
             <%if(msg != null){%>
            <p class="errorMSg"><%=msg%></p>
            <%}%>
            <div class="form-group">
                <label class="form-label">Full name <span class="required">*</span></label>
                <input type="text" name="fullname" class="form-input" value="" placeholder="Steven Job">
            </div>
            
            <div class="form-group">
                <label class="form-label">Email address <span class="required">*</span></label>
                <input type="email" name="email" class="form-input" value="" placeholder="user@gmail.com">
            </div>
            
             <div class="form-group">
                <label class="form-label">Password <span class="required">*</span></label>
                <input type="password" name="password" class="form-input" placeholder="************">
            </div>
            
              <div class="form-group">
                <label class="form-label">SignUp as <span class="required">*</span></label>
               <select name="userType" id="cars" class="form-input">
                <option value="Company">Company</option>
                <option  value="Applicant">Applicant</option>
               </select>
            </div>
            
            <button type="submit" class="login-btn">Sign Up</button>
        </form>
        
        <p class="signup-text">Already have an Account? <a href="#" class="signup-link">Sign in</a></p>
    </div>
    
   
    
     <script src="js/navbar.js"></script>
    </body>
</html>
