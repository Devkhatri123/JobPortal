<%-- 
    Document   : profileHeader
    Created on : May 11, 2025, 3:47:25 PM
    Author     : Dev khatri
--%>

<%@page import="java.util.Base64"%>
<%@page import="model.Company"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="model.Skill"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = null;
    Company company = null;
    String resumeBase64 = null;
    if (request.getSession(false) == null) {
        RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
        rd.forward(request, response);
        return;
    }

    String imageBase64 = null;
    if ((User) request.getSession(false).getAttribute("user") != null && (User) request.getAttribute("applicantProfile") == null) {
        user = (User) request.getSession(false).getAttribute("user");
       
    } else {

        if ((Company) request.getSession(false).getAttribute("company") != null && (User) request.getAttribute("applicantProfile") == null) {
            company = (Company) request.getSession(false).getAttribute("company");
            if (company.getC_profileImg() != null) {
                imageBase64 = new String(Base64.getEncoder().encode(company.getC_profileImg()));
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
    if ((User) request.getAttribute("applicantProfile") != null) {
        user = (User) request.getAttribute("applicantProfile");
            
     }else if ((Company)request.getAttribute("foundCompany") != null){
              company = (Company) request.getAttribute("foundCompany");
      }
    if(user != null){
    if(user.getProfileImg() != null) imageBase64 = new String(Base64.getEncoder().encode(user.getProfileImg()));
    if(user.getUserJobDetail() != null && user.getUserJobDetail().getResume() != null)  resumeBase64 = new String(Base64.getEncoder().encode(user.getUserJobDetail().getResume()));
    }else{
    if(company.getC_profileImg()!= null) imageBase64 = new String(Base64.getEncoder().encode(company.getC_profileImg()));
    }
%>
<header class="profile-header">
    <div class="header-image">
        <img src="https://jobbox-html-frontend.vercel.app/assets/imgs/page/candidates/img.png" alt="Office workspace with desk and computer" class="background-image">
    </div>
    <div class="profile-info">
        <div class="profile-left">
            <% if (imageBase64 != null) {%>
            <img src="data:image/png;base64,<%=imageBase64%>"
                 alt="Profile picture" class="profile-pic">
            <% } else { %>
            <img src="https://static.vecteezy.com/system/resources/previews/008/442/086/original/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg"
                 alt="Profile picture" class="profile-pic">
            <% } %>
            <div class="profile-details">
                <%if (user != null) {%>
                <h1><%=user.getFullName()%></h1>
                <%if (user.getCity() != null) {%>
                <p class="location"><i class="fas fa-map-marker-alt"></i> <%=user.getCity()%></p>
                <%}%>

                <%if (user.getUserJobDetail() != null && user.getUserJobDetail().getCategory() != null) {%>                   
                <p class="job-title"><%= user.getUserJobDetail().getCategory()%></p>
                <%}%>
                <%} else if (company != null) {%>
                <h1><%=company.getC_name()%></h1>
                <%if (company.getC_city() != null) {%>
                <p class="location"><i class="fas fa-map-marker-alt"></i> <%=company.getC_city()%></p>
                <%}%>
                <%}%>
            </div>
        </div>
        <%if (user != null) {%>
        <div class="profile-right">
            <button class="download-btn" id="viewResumeBtn">View CV</button>
        </div>
        <div class="modal" id="resumeModal">
            <div class="modal-content">
                <button class="close-btn" id="closeModal">&times;</button>
                <img src="data:image/png;base64,<%=resumeBase64%>" alt="Denice Harris Resume" class="resume-image" id="resumeImage">
            </div>
        </div>
        <%}%>
    </div>
</header>


