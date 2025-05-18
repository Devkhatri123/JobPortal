<%-- 
    Document   : CompanyPostedJobs
    Created on : May 11, 2025, 3:54:45 PM
    Author     : Dev khatri
--%>

<%@page import="Servlet.postJob"%>
<%@page import="Controller.companyController"%>
<%@page import="java.util.List"%>
<%@page import="model.Job"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Base64"%>
<%@page import="model.Company"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="model.Skill"%>
<%@page import="model.User"%>
<%@page import="java.util.stream.Collectors"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:ital,wght@0,200..800;1,200..800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/CompanyPostedJobs.css">
        <link rel="stylesheet" href="css/profileHeader.css">
        <link rel="stylesheet" href="css/profileNavbar.css">
        <title>JSP Page</title>
    </head>
    <body>


        <%
            User user = null;
            Set<Skill> subList = new HashSet<>();
            Company company = null;
            if (request.getSession(false) == null) {
                RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
                rd.forward(request, response);
                return;
            }
            user = (User) request.getSession(false).getAttribute("user");
            List<Job> Jobs = null;
            String imageBase64 = null;
            company = (Company) request.getSession(false).getAttribute("company");
                if (company != null) {
                    if (company.getC_profileImg() != null) {
                        imageBase64 = new String(Base64.getEncoder().encode(company.getC_profileImg()));
                    }
                    Jobs = company.getJobs();
                } 
            
             Jobs = (List<Job>) request.getAttribute("companyPostedJobs");
            //Set<Job> companyJobs = new HashSet<>(Jobs);
        %>
        <jsp:include page="navbar.jsp" />
        <jsp:include page="profileHeader.jsp" />
        <jsp:include page="profileNavbar.jsp" /> 
        <div class="posted-job-container">
        <h1 style="text-align: left;margin: 20px">Posted Jobs</h1>
        <div class="job-listings">
            <!-- Row 1 -->
            <%
        if (Jobs != null) {

            for (Job job : Jobs) {%>
            <%String imageBase642 = null;
                if (job.getCompany().getC_profileImg() != null) {
                    imageBase64 = new String(Base64.getEncoder().encode(job.getCompany().getC_profileImg()));
                }
                if (job.getJobSkills().size() > 3) {
                    subList = job.getJobSkills().stream().limit(3).collect(Collectors.toSet());
                } else {
                    subList = job.getJobSkills();
                }
            %>
            <div class="job-card">
                <div class="job-card-header">
                    <div class="company-logo linkedin">
                        <%if (imageBase64 != null) {%>
                        <img src="data:image/png;base64,<%=imageBase64%>" alt="Company Pic">
                        <%} else { %>
                        <img src="https://static.vecteezy.com/system/resources/previews/008/442/086/original/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg"
                             alt="Profile picture" class="profile-pic">
                        <% }%>
                    </div>

                    <div class="company-info">
                        <h3 class="company-name"><%=job.getCompany().getC_name()%></h3>
                        <div class="company-location">
                            <span class="location-icon"><i style='font-size:12px' class='fas'>&#xf3c5;</i></span>
                            <%=job.getAddress()%>
                        </div>
                    </div>
                   <div class="featured-tag" style="<%=job.IsOpened() == true ? "background-color:#22c55e;":"background-color:red;"%>"></div>
                </div>
                <div class="job-title">  <%=job.getTitle()%></div>
                <div class="job-meta">
                    <span class="job-type fulltime"><%=job.getWorkLocation()%></span>
                    <!--                <span class="job-posted">4 minutes ago</span>-->
                </div>
                <div class="job-description">
                    <%=job.getMinidescription()%>
                </div>
                <div class="job-skills">
                    <%for (Skill en : subList) {%>
                    <span class="skill"><%=en.getSkillName()%></span>
                    <%}
                    %>
                </div>
                <div class="job-footer">
                    <div class="job-salary"><%=job.getSalary()%></div>
                    <a href="getJob?id=<%=job.getJob_id()%>" class="apply-btn">View Job</a>
                    <a href="getJobApplicants?jobId=<%=job.getJob_id()%>" class="apply-btn">View Applicants</a>
                </div>
            </div>
            <%}
        }%>
            
        </div>
        </div>
    </body>
</html>