<%-- 
    Document   : jobs
    Created on : Apr 5, 2025, 3:42:13 PM
    Author     : Dev khatri
--%>

<%@page import="Controller.indexController"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="model.Skill"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Base64"%>
<%@page import="model.Job,model.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" ref="css/index.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           Set<Skill> subList = new HashSet<>();
            List<Job> allJobs = null;
            if (request.getSession(false).getAttribute("allJobs") != null) {
               allJobs = indexController.checkedJobsClosingDate((List<Job>)request.getSession(false).getAttribute("allJobs"),request);
               // allJobs = (List<Job>) request.getSession(false).getAttribute("allJobs");
                allJobs = allJobs.stream().filter(job -> job.IsOpened() == true).collect(Collectors.toList());
                if(allJobs.size() > 8){
                allJobs = allJobs.stream().limit(8).collect(Collectors.toList());
            }
               
            }

        %>
        <div class="jobs-section">
            <div class="jobs-container">
                <h2 class="jobs-title">Jobs of the day</h2>
                <p class="jobs-subtitle">Search and connect with the right candidates faster.</p>

                

                <!-- Job Listings -->
                <div class="job-listings">
                    <!-- Row 1 -->

                    <% if (allJobs != null) {

                    for (Job job : allJobs) {%>
                                        <%String imageBase64 = null;
                        if (job.getCompany().getC_profileImg() != null) {
                            imageBase64 = new String(Base64.getEncoder().encode(job.getCompany().getC_profileImg()));
                        }
                        if(job.getJobSkills().size() > 3){
                       subList = job.getJobSkills().stream().limit(3).collect(Collectors.toSet());
                       }else subList = job.getJobSkills();
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
                            <a href="getJob?id=<%=job.getJob_id()%>" class="apply-btn">Apply Now</a>
                        </div>
                    </div>
                    <%}
                }%>


                </div>
            </div>
        </div>
    </body>
</html>
