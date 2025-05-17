<%-- 
    Document   : profile
    Created on : Apr 10, 2025, 2:43:07 PM
    Author     : Dev khatri
--%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="model.Job"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="model.Company"%>
<%@page import="java.util.Base64"%>
<%@page import="model.User,model.userJobDetail,model.Skill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:ital,wght@0,200..800;1,200..800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/profile.css">
        <link rel="stylesheet" href="css/profileNavbar.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
         <link rel="stylesheet" href="css/profileHeader.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User user = null;
            Company company = null;
            if (request.getSession(false) == null) {
                response.sendRedirect(request.getContextPath() + "/index");
            }
            

            if ((User) request.getSession(false).getAttribute("user") != null && (User)request.getAttribute("applicantProfile") == null) {
                user = (User) request.getSession(false).getAttribute("user");
            } else {
                if ((Company) request.getSession(false).getAttribute("company") != null && (User)request.getAttribute("applicantProfile") == null) {
                   company = (Company) request.getSession(false).getAttribute("company");
                }
                
            }
             if((User)request.getAttribute("applicantProfile") != null){
              user = (User) request.getAttribute("applicantProfile");
            }else if ((Company)request.getAttribute("foundCompany") != null){
              company = (Company) request.getAttribute("foundCompany");
            }
        %>
        <jsp:include page="navbar.jsp" />
        <div class="container">
            <jsp:include page="profileHeader.jsp" />
            <jsp:include page="profileNavbar.jsp" /> 

            <main class="content">
                <div class="main-content">
                    <%if (user != null) {%>  
                    <%if (user.getAboutMe() != null) {%>
                    <section class="about-me">
                        <h2>About Me</h2>
                        <p><%=user.getAboutMe()%></p>
                    </section>
                    <%}%>

                    <%} else if (company != null) {%>
                    <%if (company.getC_about() != null) {%>
                    <section class="about-me">
                        <h2>About Me</h2>
                        <p><%=company.getC_about()%></p>
                    </section>
                    <%}%>
                    <%}%>

                    <%if (user != null && user.getUserSkills() != null) {%>
                    <section class="professional-skills">
                        <h2>Professional Skills</h2>
                        <div class="skills-container">
                            <div class="skills-column">
                                <%for (Skill skill : user.getUserSkills()) {%>
                                <p><%=skill.getSkillName()%></p>
                                <%}%>
                            </div>
                        </div>
                    </section>
                    <%}%>


                    <%if (user != null) {%>
                    <%if (user.getUserJobDetail() != null && !user.getUserJobDetail().getAboutexperience().equals("")) {%>
                    <section class="work-experience">
                        <h2>Work Experience</h2>
                        <%=user.getUserJobDetail().getAboutexperience()%>
                    </section>
                    <%}%>
                    <%}%>

                    <%if (user != null) {%>
                    <%if (user.getAboutEducation() != null) {%>
                    <section class="education">
                        <h2>Education</h2>
                        <%=user.getAboutEducation()%>
                    </section>
                    <%}%>
                    <%}%>
                      
                   
                 
                </div>

                <aside class="sidebar">
                    <div class="overview-card">
                        <h3>Overview</h3>
                        <%if (user != null && user.getUserJobDetail() != null) {%>
                        <div class="overview-item">
                            <i class="fas fa-briefcase"></i>
                            <div>
                                <p class="label">Experience</p>
                                <p class="value"><%=user.getUserJobDetail().getExperience()%></p>

                            </div>
                        </div>
                        <%}%>

                        <%if (company != null && company.getC_size() != null) {%>
                        <div class="overview-item" style="height: 25px">
                            <i class="fas fa-briefcase"></i>
                            <div style="display: flex;gap: 10px">
                                <p class="label">Company size : </p>
                                <p class="value"><%=company.getC_size()%></p>

                            </div>
                        </div>
                        <%}%>

                        <%if (company != null && company.getC_websiteurl() != null) {%>
                        <div class="overview-item" style="height: 25px">
                            <i class="fa-solid fa-link"></i>
                            <div style="display: flex;gap: 10px">
                                <p class="label">Website URL : </p>
                                <p class="value"><%=company.getC_websiteurl()%></p>

                            </div>
                        </div>
                        <%}%>  



                        <%if (user != null) {%>
                         <%if (user != null && user.getLanguage()!= null) {%> 
                        <div class="overview-item">
                            
                            <i class="fas fa-language"></i>
                            <div>
                             
                                <p class="label">Language</p>
                                <p class="value"><%=user.getLanguage()%></p>
                            </div>
                        </div>
                            <%}%>
                        <%}%>

                        <%if (user != null && user.getEducation() != null) {%> 
                        <div class="overview-item">
                            <i class="fas fa-graduation-cap"></i>
                            <div>
                                <p class="label">Education Level</p>
                                <p class="value"><%=user.getEducation()%></p>
                            </div>
                        </div>
                        <%}%>
                        <div class="contact-info">
                            <%if (user != null && user.getAddress() != null) {%>
                            <p><i class="fas fa-map-marker-alt"></i> <%=user.getAddress()%></p>
                            <%} else if (company != null && company.getC_address() != null) {%>
                            <p><i class="fas fa-map-marker-alt"></i> <%=company.getC_address()%></p>
                            <%}%>
                            <%if (user != null && user.getPhoneNumber() != null) {%>
                            <p><i class="fas fa-phone"></i> Phone: <%=user.getPhoneNumber()%></p>
                            <%} else if (company != null && company.getC_phone() != null) {%>
                            <p><i class="fas fa-phone"></i> <%=company.getC_phone()%></p>
                            <%}%>
                            <%if (user != null && user.getEmail() != null) {%>
                            <p><i class="fas fa-envelope"></i> Email: <%=user.getEmail()%></p>
                            <%} else if (company != null) {%>
                            <p><i class="fas fa-envelope"></i> <%=company.getC_email()%></p>
                            <%}%>
                        </div>

                    </div>


                </aside>
            </main>
        </div> 
        <script src="js/navbar.js"></script>
         <script>
        // Get DOM elements
        const viewResumeBtn = document.getElementById('viewResumeBtn');
        const resumeModal = document.getElementById('resumeModal');
        const closeModal = document.getElementById('closeModal');
        const resumeImage = document.getElementById('resumeImage');

        // Function to open the modal
        function openModal() {
            resumeModal.classList.add('active');
            document.body.style.overflow = 'hidden'; // Prevent scrolling when modal is open
            
            // For accessibility
            resumeModal.setAttribute('aria-hidden', 'false');
            
            // Set focus to the modal for keyboard navigation
            resumeModal.focus();
        }

        // Function to close the modal
        function closeModalFunc() {
            resumeModal.classList.remove('active');
            document.body.style.overflow = ''; // Restore scrolling
            
            // For accessibility
            resumeModal.setAttribute('aria-hidden', 'true');
            
            // Return focus to the button
            viewResumeBtn.focus();
        }

        // Event listeners
        viewResumeBtn.addEventListener('click', openModal);
        closeModal.addEventListener('click', closeModalFunc);

        // Close modal when clicking outside the content
        resumeModal.addEventListener('click', function(event) {
            if (event.target === resumeModal) {
                closeModalFunc();
            }
        });

        // Close modal with Escape key
        document.addEventListener('keydown', function(event) {
            if (event.key === 'Escape' && resumeModal.classList.contains('active')) {
                closeModalFunc();
            }
        });
    </script>
    </body>
</html>
