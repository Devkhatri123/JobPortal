<%-- 
    Document   : browseJobs
    Created on : Apr 5, 2025, 8:17:43 PM
    Author     : Dev khatri
--%>

<%@page import="Controller.indexController"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="model.Skill"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Base64"%>
<%@page import="model.Job,model.Company,model.Filter"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/browseJobs.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (request.getSession(false) == null) {
                new indexController().getJobs(request);
            }
            Set<Skill> subList = new HashSet<>();
            ArrayList<Job> allJobs = null;
            if (request.getSession(false).getAttribute("allJobs") != null) {
                allJobs = (ArrayList<Job>) request.getSession(false).getAttribute("allJobs");
            }
            Filter filter = null;
            filter = (Filter) request.getAttribute("filter");

            ArrayList<Job> filteredJobs = null;
            filteredJobs = (ArrayList<Job>) request.getAttribute("filteredJobs");

        %>

        <jsp:include page="navbar.jsp" />

        <div class="job-listings-container">
            <!-- Header Section -->
            <header class="header">
                <div class="header-content">
                    <h1 class="header-title">
                        <%if (filteredJobs != null) {%>
                        <span class="blue-text"><%=filteredJobs.size()%> Jobs
                            <%} else if (allJobs != null) {%>
                            <span class="blue-text"><%=allJobs.size()%> Jobs
                                <%}%>
                            </span> 
                            Available Now</h1>
                    <p class="header-description">
                        Lorem ipsum dolor sit amet consectetur adipiscing elit. Vitae neque eleifend magna, 
                        atque eleifend molestie ipsum
                    </p>
                </div>


            </header>

            <!-- Filter and Results -->

            <form action="browseJobs" method="POST" id="jobFiltersForm">
                <div class="filter-results-content">
                    <!-- Left Sidebar -->
                    <aside class="filters-sidebar">
                        <div class="filter-location">
                            <div class="filter-group-title">
                                <select name="country" id="countrySelect" class="form-input"  >
                                    <option value="allcountries">
                                        All Select Country
                                    </option>
                                    <option value="New York,USA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("New York,USA")) ? "selected" : ""%>>New York, USA</option>

                                    <option value="Toronto,CA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Toronto,CA")) ? "selected" : ""%>>Toronto, CA</option>

                                    <option value="London,UK" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("London,UK")) ? "selected" : ""%>>London, UK</option>

                                    <option value="San Francisco,USA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("San Francisco,USA")) ? "selected" : ""%>>San Francisco, USA</option>

                                    <option value="Los Angeles,USA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Los Angeles,USA")) ? "selected" : ""%>>Los Angeles, USA</option>

                                    <option value="Chicago,USA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Chicago,USA")) ? "selected" : ""%>>Chicago, USA</option>

                                    <option value="Vancouver,CA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Vancouver,CA")) ? "selected" : ""%>>Vancouver, CA</option>

                                    <option value="Montreal,CA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Montreal,CA")) ? "selected" : ""%>>Montreal, CA</option>

                                    <option value="Sydney,AU" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Sydney,AU")) ? "selected" : ""%>>Sydney, AU</option>

                                    <option value="Melbourne,AU" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Melbourne,AU")) ? "selected" : ""%>>Melbourne, AU</option>

                                    <option value="Berlin,DE" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Berlin,DE")) ? "selected" : ""%>>Berlin, DE</option>

                                    <option value="Paris,FR" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Paris,FR")) ? "selected" : ""%>>Paris, FR</option>

                                    <option value="Amsterdam,NL" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Amsterdam,NL")) ? "selected" : ""%>>Amsterdam, NL</option>

                                    <option value="Tokyo,JP" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Tokyo,JP")) ? "selected" : ""%>>Tokyo, JP</option>

                                    <option value="Seoul,KR" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Seoul,KR")) ? "selected" : ""%>>Seoul, KR</option>

                                    <option value="Singapore,SG" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Singapore,SG")) ? "selected" : ""%>>Singapore, SG</option>

                                    <option value="Dubai,AE" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Dubai,AE")) ? "selected" : ""%>>Dubai, AE</option>

                                    <option value="Mumbai,IN" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Mumbai,IN")) ? "selected" : ""%>>Mumbai, IN</option>

                                    <option value="Bangalore,IN" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Bangalore,IN")) ? "selected" : ""%>>Bangalore, IN</option>

                                    <option value="Delhi,IN" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Delhi,IN")) ? "selected" : ""%>>Delhi, IN</option>

                                    <option value="Cape Town,ZA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Cape Town,ZA")) ? "selected" : ""%>>Cape Town, ZA</option>

                                    <option value="Johannesburg,ZA" <%= (filter != null && filter.getCountry() != null && filter.getCountry().equals("Johannesburg,ZA")) ? "selected" : ""%>>Johannesburg, ZA</option> 

                                </select>
                            </div>
                        </div>

                        <div class="filter-group">
                            <h3 class="filter-group-title">Industry</h3>
                            <div class="filter-options">
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="industry" value="Technology" <%= (filter != null && filter.getCategories() != null && filter.getCategories().contains("Technology")) ? "checked" : ""%> > Technology
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="industry" value="Finance" <%= (filter != null && filter.getCategories() != null && filter.getCategories().contains("Finance")) ? "checked" : ""%>> Finance
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="industry" value="Sales" <%= (filter != null && filter.getCategories() != null && filter.getCategories().contains("Sales")) ? "checked" : ""%>> Sales
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="industry" value="Recruiting" <%= (filter != null && filter.getCategories() != null && filter.getCategories().contains("Recruiting")) ? "checked" : ""%> > Recruiting
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="industry" value="Management" <%= (filter != null && filter.getCategories() != null && filter.getCategories().contains("Management")) ? "checked" : ""%> > Management
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="industry" value="Advertising" <%= (filter != null && filter.getCategories() != null && filter.getCategories().contains("Advertising")) ? "checked" : ""%> > Advertising
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="industry" value="Design" <%= (filter != null && filter.getCategories() != null && filter.getCategories().contains("Design")) ? "checked" : ""%> > Design
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="industry" value="Marketing" <%= (filter != null && filter.getCategories() != null && filter.getCategories().contains("Marketing")) ? "checked" : ""%> > Marketing
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="filter-group">
                            <h3 class="filter-group-title">Salary Range</h3>
                            <div class="filter-options">
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="salary" value="$20k - $40k" <%= (filter != null && filter.getSalaries() != null && filter.getSalaries().contains("$20k - $40k")) ? "checked" : ""%> > $20k - $40k
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="salary" value="$40k - $60k" <%= (filter != null && filter.getSalaries() != null && filter.getSalaries().contains("$40k - $60k")) ? "checked" : ""%> > $40k - $60k
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="salary" value="$60k - $80k" <%= (filter != null && filter.getSalaries() != null && filter.getSalaries().contains("$60k - $80k")) ? "checked" : ""%> > $60k - $80k
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="salary" value="$80k - $100k" <%= (filter != null && filter.getSalaries() != null && filter.getSalaries().contains("$80k - $100k")) ? "checked" : ""%> > $80k - $100k
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="salary" value="$100k - $120k" <%= (filter != null && filter.getSalaries() != null && filter.getSalaries().contains("$100k - $120k")) ? "checked" : ""%> > $100k - $120k
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="filter-group">
                            <h3 class="filter-group-title">Experience Level</h3>
                            <div class="filter-options">
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="experience" value="0-1 years"  <%= (filter != null && filter.getExperiences() != null && filter.getExperiences().contains("0-1 years")) ? "checked" : ""%> > 0-1 years
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="experience" value="1-3 years" <%= (filter != null && filter.getExperiences() != null && filter.getExperiences().contains("1-3 years")) ? "checked" : ""%> > 1-3 years
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="experience" value="3-6 years" <%= (filter != null && filter.getExperiences() != null && filter.getExperiences().contains("3-6 years")) ? "checked" : ""%> > 3-6 years
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="experience" value="6-9 years" <%= (filter != null && filter.getExperiences() != null && filter.getExperiences().contains("6-9 years")) ? "checked" : ""%>  > 6-9 years
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="experience" value="10+ years" <%= (filter != null && filter.getExperiences() != null && filter.getExperiences().contains("10+ years")) ? "checked" : ""%> > 10+ years
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="filter-group">
                            <h3 class="filter-group-title">Job Type</h3>
                            <div class="filter-options">
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="jobType" value="Full time" <%= (filter != null && filter.getJobtype() != null && filter.getJobtype().contains("Full time")) ? "checked" : ""%>> Full time
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="jobType" value="Part time" <%= (filter != null && filter.getJobtype() != null && filter.getJobtype().contains("Part time")) ? "checked" : ""%> > Part time
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="jobType" value="Contract" <%= (filter != null && filter.getJobtype() != null && filter.getJobtype().contains("Contract")) ? "checked" : ""%> > Contract
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="jobType" value="Freelance" <%= (filter != null && filter.getJobtype() != null && filter.getJobtype().contains("Freelance")) ? "checked" : ""%> > Freelance
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="jobType" value="Internship" <%= (filter != null && filter.getJobtype() != null && filter.getJobtype().contains("Internship")) ? "checked" : ""%>  > Internship
                                    </label>
                                </div>

                            </div>
                        </div>

                        <div class="filter-group">
                            <h3 class="filter-group-title">Onsite/Remote</h3>
                            <div class="filter-options">
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="workLocation" value="Onsite" <%= (filter != null && filter.getWorkLocations() != null && filter.getWorkLocations().contains("Onsite")) ? "checked" : ""%> > Onsite
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="workLocation" value="Hybrid" <%= (filter != null && filter.getWorkLocations() != null && filter.getWorkLocations().contains("Hybrid")) ? "checked" : ""%> > Hybrid
                                    </label>
                                </div>
                                <div class="filter-option">
                                    <label class="filter-label">
                                        <input type="checkbox" name="workLocation" value="Remote" <%= (filter != null && filter.getWorkLocations() != null && filter.getWorkLocations().contains("Remote")) ? "checked" : ""%> > Remote
                                    </label>
                                </div>
                                <button id="filterButton">submit</button>
                            </div>
                        </div>



                    </aside>

            </form> 
            <!-- Jobs Grid -->
            <div class="jobs-grid">

                <!-- Job Card 1 -->
                <%   if (filteredJobs == null) {

                        if (allJobs != null && !allJobs.isEmpty()) {
                            for (Job Job : allJobs) { %>

                <div class="job-card">
                    <div class="job-card-header">
                        <div class="company-logo linkedin">
                            <%  String imageBase64 = null;
                                if (Job.getCompany().getC_profileImg() != null) {
                                    imageBase64 = new String(Base64.getEncoder().encode(Job.getCompany().getC_profileImg()));
                                }
                                if (Job.getJobSkills().size() > 3) {
                                    subList = Job.getJobSkills().stream().limit(3).collect(Collectors.toSet());
                                } else {
                                    subList = Job.getJobSkills();
                                }
                                if (imageBase64 != null) {%>
                            <img src="data:image/png;base64,<%=imageBase64%>" alt="Company Pic">
                            <%} else { %>
                            <img src="https://static.vecteezy.com/system/resources/previews/008/442/086/original/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg"
                                 alt="Profile picture" class="profile-pic">
                            <% }%>
                        </div>
                        <div class="company-info">
                            <span class="company-name"><%=Job.getCompany().getC_name()%></span>
                            <span class="company-location"><%=Job.getAddress()%></span>

                        </div>
                    </div>
                    <h3 class="job-title"> <%=Job.getTitle()%></h3>
                    <div class="job-meta">
                        <span class="job-type fulltime"><%=Job.getWorkLocation()%></span>
                        <span class="job-type fulltime"><%=Job.getType()%></span>

                        <span class="job-time">3 minutes ago</span>
                    </div>
                    <div class="job-description">
                        <%=Job.getMinidescription()%>
                    </div>
                    <div class="job-skills">
                        <%for (Skill en : subList) {%>
                        <span class="skill"><%=en.getSkillName()%></span>
                        <%}
                        %>
                    </div>
                    <div class="job-footer">
                        <div class="job-salary"><%=Job.getSalary()%></div>
                        <a href="getJob?id=<%=Job.getJob_id()%>" class="apply-button">Apply Now</a>
                    </div>
                </div>
                <%}
                    }
                } else {%>
                <%for (Job Job : filteredJobs) { %>
                <div class="job-card">
                    <div class="job-card-header">
                        <div class="company-logo linkedin">
                            <%  String imageBase64 = null;
                                if (Job.getCompany().getC_profileImg() != null) {
                                    imageBase64 = new String(Base64.getEncoder().encode(Job.getCompany().getC_profileImg()));
                                }
                                if (Job.getJobSkills().size() > 3) {
                                    subList = Job.getJobSkills().stream().limit(3).collect(Collectors.toSet());
                                } else {
                                    subList = Job.getJobSkills();
                                }
                                if (imageBase64 != null) {%>
                            <img src="data:image/png;base64,<%=imageBase64%>" alt="Company Pic">
                            <%} else { %>
                            <img src="https://static.vecteezy.com/system/resources/previews/008/442/086/original/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg"
                                 alt="Profile picture" class="profile-pic">
                            <% }%>
                        </div>
                        <div class="company-info">
                            <span class="company-name"><%=Job.getCompany().getC_name()%></span>
                            <span class="company-location"><%=Job.getAddress()%></span>
                        </div>
                    </div>
                    <h3 class="job-title"> <%=Job.getTitle()%></h3>
                    <div class="job-meta">
                        <span class="job-type fulltime"><%=Job.getWorkLocation()%></span>
                        <span class="job-type jobtype"><%=Job.getType()%></span>
                        <span class="job-time">3 minutes ago</span>
                    </div>
                    <div class="job-description">
                        <%=Job.getMinidescription()%>
                    </div>
                    <div class="job-skills">
                        <%for (Skill en : subList) {%>
                        <span class="skill"><%=en.getSkillName()%></span>
                        <%}
                        %>
                    </div>
                    <div class="job-footer">
                        <div class="job-salary"><%=Job.getSalary()%></div>
                        <a href="getJob?id=<%=Job.getJob_id()%>" class="apply-button">Apply Now</a>
                    </div>
                </div>
                <%}%>
                <%}%>

            </div>
        </div>
    </div>
</div>

<script src="js/navbar.js"></script>

</body>
</html>
