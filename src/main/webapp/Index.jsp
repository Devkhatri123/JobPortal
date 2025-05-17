<%-- 
    Document   : Index
    Created on : Apr 5, 2025, 1:21:37 PM
    Author     : Dev khatri
--%>

<%@page import="model.Job"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/index.css" />
         <link rel="stylesheet" href="css/navbar.css" />
         
        <title>JSP Page</title>
    </head>
   
    <body>
        <jsp:include page="navbar.jsp" />
        
        <div class="hero-section">
        <div class="hero-content">
          <h1 class="hero-title">
            The <span class="text-highlight">#1</span> Job Board for
            <br> Hiring or Find your next job
          </h1>
          <p class="hero-description">
           Lorem ipsum dolor sit amet consectetur adipiscing elit. 
            <br> Vitae neque eleifend magna, atque eleifend molestie ipsum
          </p>
          
          <!-- Search Form -->
        
          
          <!-- Popular Searches -->
         
          
         </div>
      </div>
        
      <jsp:include page="jobs.jsp" />
        
        <script src="js/navbar.js"></script>
    </body>
</html>
