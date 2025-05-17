<%-- 
    Document   : job
    Created on : May 5, 2025, 2:57:17 PM
    Author     : Dev khatri
--%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="Controller.userController"%>
<%@page import="model.User"%>
<%@page import="java.util.Base64"%>
<%@page import="model.Skill"%>
<%@page import="model.Job"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/job.css">
    <title>Job Details - Senior UI Designer</title>
    <link rel="stylesheet" href="css/navbar.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
   <%
      
      Job currentJob = (Job) request.getAttribute("currentJob");
      String imageBase64 = null;
         if (currentJob != null && currentJob.getCompany().getC_profileImg() != null) {
          imageBase64 = new String(Base64.getEncoder().encode(currentJob.getCompany().getC_profileImg()));
          }
          User currentUser = (User)request.getSession(false).getAttribute("user");
//          List<Job> appliedJobs = new userController().getAppliedJobs(currentUser.getId());
//          Set<Job> A = new HashSet<Job>(appliedJobs);
//          List<Job> savedJobs = new userController().getSavedJobs(currentUser.getId());
//          Set<Job> S = new HashSet<Job>(savedJobs);
   %> 
 <jsp:include page="navbar.jsp" />
    <main class="main-content">
        <div class="container">
            <div class="job-header">
                <div class="company-logo">
                   <% if (imageBase64 != null) {%>
                     <img src="data:image/png;base64,<%=imageBase64%>" alt="Company Pic">
                    <%} else { %>
                      <img src="https://static.vecteezy.com/system/resources/previews/008/442/086/original/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg"
                       alt="Profile picture" class="profile-pic">
                   <% }%>
                </div>
                <div class="job-info">
                    <h1><%=currentJob.getTitle()%></h1>
                    <div class="company-details">
                        <span class="company-name"><%=currentJob.getCompany().getC_name()%></span>
                        <span class="location"><%=currentJob.getAddress()%></span>
                        <span class="job-type"><%=currentJob.getType()%></span>
                    </div>
                    <div class="job-tags">
                        <%
                            if(currentJob.getJobSkills() != null ){%>
                            <%for(Skill skill : currentJob.getJobSkills()) {%>
                             <span class="tag"><%=skill.getSkillName()%></span>
                             <%}
                            }
                        %>
                     </div>
                </div>
                <div class="action-buttons">
                    <%
                    if (currentJob.IsOpened() == false) {%>
                    <a href="#" style="<%=currentJob.IsOpened() == false  ? "background-color: #7b88f5;color:black;":""%>" class="btn-outline">
                           Closed
                        </a>
                        <%}
                   if((User)request.getSession(false).getAttribute("user") != null ){%>
                    
                    <%  if(currentJob.IsOpened() == true){%>
                    <a href="applyJob?id=<%=currentJob.getJob_id()%>" class="btn-primary" id="apply" style="<%=currentUser.getAppliedJobs() != null && currentUser.getAppliedJobs().stream().anyMatch(job -> job.getJob_id() == currentJob.getJob_id()) == true ? "background-color: #7b88f5;color:white;":"background-color: #3b82f6 !important"%>">
                       <%=currentUser.getAppliedJobs() != null &&currentUser.getAppliedJobs().stream().anyMatch(job -> job.getJob_id() == currentJob.getJob_id()) == true ? "Applied " : "Apply "%>
                       </a>
                   <%}%>
                       <a href="saveJob?id=<%=currentJob.getJob_id()%>" style="<%=currentUser.getSavedJobs() != null && currentUser.getSavedJobs().stream().anyMatch(job -> job.getJob_id() == currentJob.getJob_id()) == true ? "background-color: #7b88f5;color:white;margin-left:10px;":"transparent !important;margin-left:10px;"%>" class="btn-outline">
                           <%=currentUser.getSavedJobs() != null && currentUser.getSavedJobs().stream().anyMatch(job -> job.getJob_id() == currentJob.getJob_id()) == true ? "Saved Job " : "save Job "%>
                        </a>
                 
                      <%}%>
                </div>
            </div>

            <div class="job-content">
                <div class="job-description">
                    <section>
                        <h2>Job Description</h2>
                        <p><%=currentJob.getMinidescription()%></p>
                    </section>

                    <section>
                        <%=currentJob.getDescription()%>
                    </section>
                </div>

                <div class="job-sidebar">
                    <div class="info-card">
                        <h3>Job Overview</h3>
                        <div class="info-item">
                            <span class="label">Industry:</span>
                            <span class="value"><%=currentJob.getCategory()%></span>
                        </div>
                        <div class="info-item">
                            <span class="label">Experience:</span>
                            <span class="value"><%=currentJob.getExperience()%></span>
                        </div>
                        <div class="info-item">
                            <span class="label">Salary:</span>
                            <span class="value"><%=currentJob.getSalary()%></span>
                        </div>
                        <div class="info-item">
                            <span class="label">Location:</span>
                            <span class="value"><%=currentJob.getAddress()%></span>
                        </div>
                         <div class="info-item">
                            <span class="label">Work Location:</span>
                            <span class="value"><%=currentJob.getWorkLocation()%></span>
                        </div
                    </div>

                    <div class="company-card">
                        <h3>About Company</h3>
                        <div class="company-info">
                             <% if (imageBase64 != null) {%>
                     <img src="data:image/png;base64,<%=imageBase64%>" alt="Company Pic">
                    <%} else { %>
                      <img src="https://static.vecteezy.com/system/resources/previews/008/442/086/original/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg"
                       alt="Profile picture" class="profile-pic">
                   <% }%>
                            <div>
                                <h4><%=currentJob.getCompany().getC_name()%></h4>
                                <p><%=currentJob.getCompany().getC_about()%></p>
                            </div>
                        </div>
                        <a href="getProfile?companyId=<%=currentJob.getCompany().getC_id()%>" class="btn-outline">View Company Profile</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
                           
</body>
</html>
