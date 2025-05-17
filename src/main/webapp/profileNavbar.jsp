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

    user = (User) request.getSession(false).getAttribute("user");
    if (user == null) {
        company = (Company) request.getSession(false).getAttribute("company");
    }
%>
<nav class="profile-nav">
    <ul>
        <li><a href="profile.jsp"><i class="fas fa-user"></i>Bio</a></li>
            <%
                    if (company != null && (User) request.getAttribute("applicantProfile") == null) {%>
        <li><a href="CompanyPostedJobs.jsp"><i class="fas fa-chart-bar"></i>Posted Jobs</a></li>
            <%} else if (user != null) {%>
        <li><a href="saveJob.jsp"><i class="fas fa-chart-bar"></i> Saved Jobs</a></li>
        <li><a href="appliedJobs.jsp"><i class="fas fa-briefcase"></i> Applied Jobs</a></li>
            <%}
            %>
    </ul>
</nav>   