<%-- 
    Document   : signIn
    Created on : Apr 9, 2025, 9:35:04 PM
    Author     : Dev khatri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="css/signin.css">
           <link rel="stylesheet" href="css/navbar.css">
    </head>
    <body>
        <%String msg = (String)request.getAttribute("errorMsg");%>
         <jsp:include page="navbar.jsp" />
   <div class="login-container">
<!--        <p class="welcome-text">Welcome back!</p>-->
        <h1 class="login-title">Sign In</h1>
        <p class="login-subtitle">Access to all features. No credit card required.</p>
        
       
        
        <form action="signIn" method="POST">
           
            <%if(msg != null){%>
            <p class="errorMSg"><%=msg%></p>
            <%}%>
            <div class="form-group">
                <label class="form-label">Email address <span class="required">*</span></label>
                <input type="email" name="email" class="form-input" value="" placeholder="user@gmail.com">
            </div>
            
             <div class="form-group">
                <label class="form-label">Password <span class="required">*</span></label>
                <input type="password" name="password" class="form-input" placeholder="************">
            </div>
            
            
            <button type="submit" class="login-btn">Sign In</button>
        </form>
        
        <p class="signup-text">Don't have an Account? <a href="#" class="signup-link">Sign in</a></p>
    </div>
    </body>
</html>
