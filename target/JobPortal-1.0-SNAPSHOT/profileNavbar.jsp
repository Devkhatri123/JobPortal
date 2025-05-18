<%-- 
    Document   : profileNavbar
    Created on : May 11, 2025, 4:05:32 PM
    Author     : Dev khatri
--%>

<%@page import="model.User"%>
<%@page import="model.Company"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = null;
    Company company = null;

    if ((User) request.getSession(false).getAttribute("user") != null && (User) request.getAttribute("applicantProfile") == null && (Company) request.getAttribute("foundCompany") == null) {
        user = (User) request.getSession(false).getAttribute("user");
    } else if ((Company) request.getSession(false).getAttribute("company") != null && (User) request.getAttribute("applicantProfile") == null && (Company) request.getAttribute("foundCompany") == null) {
        company = (Company) request.getSession(false).getAttribute("company");

    } else if ((User) request.getAttribute("applicantProfile") != null) {
        user = (User) request.getAttribute("applicantProfile");
    } else if ((Company) request.getAttribute("foundCompany") != null) {
        company = (Company) request.getAttribute("foundCompany");
    }
   // int companyId = Integer.parseInt(request.getParameter("companyId"));
%>
<nav class="profile-nav">
    <ul>
        <%if (user != null) {%>
        <li><a href="getProfile?userId=<%=user.getId()%>"><i class="fas fa-user"></i>Bio</a></li>
            <%} else {%>
        <li><a href="getProfile?companyId=<%=company.getC_id()%>"><i class="fas fa-user"></i>Bio</a></li>
            <%}%>   
            <%
                if (company != null && (User) request.getAttribute("applicantProfile") == null) {%>
        <li><a href="CompanyPostedJobs?companyId=<%=company.getC_id()%>"><i class="fas fa-chart-bar"></i>Posted Jobs</a></li>
            <%} else if (user != null && (Company) request.getAttribute("foundCompany") == null) {%>
        <li><a href="saveJob.jsp"><i class="fas fa-chart-bar"></i> Saved Jobs</a></li>
        <li><a href="appliedJobs.jsp"><i class="fas fa-briefcase"></i> Applied Jobs</a></li>
            <%}
            %>
    </ul>
</nav>   