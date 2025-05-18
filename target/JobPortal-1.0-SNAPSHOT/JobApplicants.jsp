<%-- 
    Document   : JobApplicants
    Created on : May 15, 2025, 9:07:29 PM
    Author     : Dev khatri
--%>

<%@page import="model.Skill"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Base64,java.util.stream.Collectors"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/CompanyPostedJobs.css">
        <link rel="stylesheet" href="css/profileHeader.css">
        <link rel="stylesheet" href="css/profileNavbar.css">
        <link rel="stylesheet" href="css/JobApplicants.css">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            
            if (request.getSession(false).getAttribute("company") == null) {
                response.sendRedirect(request.getContextPath() + "/index");
            }
            Set<Skill> subList = new HashSet<>();
            String imageBase64 = null;
            List<User> applicants =  (List<User>)request.getAttribute("applicants");
         %>
         
        <jsp:include page="navbar.jsp" />
        <jsp:include page="profileHeader.jsp" />
        <jsp:include page="profileNavbar.jsp" /> 
        <h1 style="text-align: left;margin: 20px">Job Applicants</h1>

       <div class="container">
    <div class="team-section">
      <div class="team-grid">
        
         <% 
    if (applicants != null) {
        for (User applicant : applicants) {
            if (applicant.getProfileImg() != null) {
                imageBase64 = new String(Base64.getEncoder().encode(applicant.getProfileImg()));
            }

            if (applicant.getUserSkills().size() > 3) {
                subList = applicant.getUserSkills().stream().limit(3).collect(Collectors.toSet());
            } else {
                subList = applicant.getUserSkills();
            }
%>
    <div class="card">
        <div class="card-header">
            <div class="avatar-container">
                <img src="data:image/jpeg;base64,<%= imageBase64 %>" alt="User Image" class="avatar">
              
            </div>
            <div class="user-info">
                <h3 class="user-name"><%= applicant.getFullName() %></h3>
                <p class="user-title"><%= applicant.getUserJobDetail().getCategory()%></p>
              
            </div>
        </div>
                <div class="card-description"">
       <%= applicant.getAboutMe()%>
                </div>

        <div class="skills">
            <% for (Skill skill : subList) { %>
                <span class="skill-tag"><%= skill.getSkillName() %></span>
            <% } %>
        </div>

        <div class="card-footer">
            <div class="location">
                <!-- SVG omitted for brevity -->
                <i class="fas fa-map-marker-alt"></i>
                <%= applicant.getCity()%>
            </div>
            <div>
                <a href="getProfile?userId=<%=applicant.getId()%>" class="apply-btn">View Profile</a>
            </div>
           
        </div>
    </div>
<% 
        } 
    } 
%>

       
        
        
      </div>
    </div>
  </div>
    </body>
</html>
