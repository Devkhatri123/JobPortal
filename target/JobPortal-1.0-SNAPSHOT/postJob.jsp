<%-- 
    Document   : postJob
    Created on : Apr 26, 2025, 11:59:51 AM
    Author     : Dev khatri
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Skill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/postJob.css">
        <!--         <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/45.0.0/ckeditor5.css">-->
        <script src="https://cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>

        <%ArrayList<Skill> skills_list = (ArrayList<Skill>) request.getAttribute("skills");%>

        <jsp:include page="navbar.jsp" />
        <div class="page-wrapper">
            <div class="container">
                <header>
                    <div class="header-content">
                        <div class="header-icon">
                            <i class="fas fa-briefcase"></i>
                        </div>
                        <h1>Post a New Job</h1>
                        <p>Fill out the form below to list your job opening</p>
                    </div>
                    <div class="header-wave">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
                        <path fill="#ffffff" fill-opacity="1" d="M0,96L48,112C96,128,192,160,288,160C384,160,480,128,576,122.7C672,117,768,139,864,149.3C960,160,1056,160,1152,138.7C1248,117,1344,75,1392,53.3L1440,32L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path>
                        </svg>
                    </div>
                </header>

                <form action="${pageContext.request.contextPath}/postJob" method="post">
                    <div class="form-section">
                        <div class="section-header">
                            <div class="section-icon">
                                <i class="fas fa-file-alt"></i>
                            </div>
                            <h2>Job Details</h2>
                        </div>

                        <div class="form-group">
                            <label for="jobtitle">Job Title <span class="required">*</span></label>
                            <div class="input-wrapper">
                                <i class="fas fa-briefcase input-icon"></i>
                                <input type="text" id="job-title" name="jobtitle" required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group" style="display: flex;justify-content: space-between">
                                <div style="width: 49%">
                                    <label for="location">Location <span class="required">*</span></label>
                                    <div class="input-wrapper">
                                        <select id="job-type" name="location" required>
                                            <option value="New York,USA">New York, USA</option>
                                            <option value="Toronto,CA">Toronto, CA</option>
                                            <option value="London,UK">London, UK</option>
                                            <option value="San Francisco,USA">San Francisco, USA</option>
                                            <option value="Los Angeles,USA">Los Angeles, USA</option>
                                            <option value="Chicago,USA">Chicago, USA</option>
                                            <option value="Vancouver,CA">Vancouver, CA</option>
                                            <option value="Montreal,CA">Montreal, CA</option>
                                            <option value="Sydney,AU">Sydney, AU</option>
                                            <option value="Melbourne,AU">Melbourne, AU</option>
                                            <option value="Berlin,DE">Berlin, DE</option>
                                            <option value="Paris,FR">Paris, FR</option>
                                            <option value="Amsterdam,NL">Amsterdam, NL</option>
                                            <option value="Tokyo,JP">Tokyo, JP</option>
                                            <option value="Seoul,KR">Seoul, KR</option>
                                            <option value="Singapore,SG">Singapore, SG</option>
                                            <option value="Dubai,AE">Dubai, AE</option>
                                            <option value="Mumbai,IN">Mumbai, IN</option>
                                            <option value="Bangalore,IN">Bangalore, IN</option>
                                            <option value="Delhi,IN">Delhi, IN</option>
                                            <option value="Cape Town,ZA">Cape Town, ZA</option>
                                            <option value="Johannesburg,ZA">Johannesburg, ZA</option>

                                        </select>
                                        <!--                                    <input type="text" id="location" name="location" placeholder="City, State or Remote" required>-->
                                    </div>
                                </div>
                                <div style="width: 49%">
                                    <label for="workLocation">Work Location <span class="required">*</span></label>
                                    <div class="select-wrapper">
                                        <select id="job-type" name="workLocation" required>
                                            <option value="">Select Work Location</option>
                                            <option value="Onsite">Onsite</option>
                                            <option value="Remote">Remote</option>
                                            <option value="Hybrid">Hybrid</option>

                                        </select>
                                        <i class="fas fa-chevron-down select-icon"></i>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="job-type">Job Type <span class="required">*</span></label>
                                <div class="select-wrapper">
                                    <select id="job-type" name="jobtype" required>
                                        <option value="">Select job type</option>
                                        <option value="Full time">Full time</option>
                                        <option value="Part time">Part time</option>
                                        <option value="Contract">Contract</option>
                                        <option value="Freelance">Freelance</option>
                                        <option value="Internship">Internship</option>
                                    </select>
                                    <i class="fas fa-chevron-down select-icon"></i>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="category">Category <span class="required">*</span></label>
                                <div class="select-wrapper">
                                    <select id="category" name="category" required>
                                        <option value="">Select category</option>
                                        <option value="Technology">Technology</option>
                                        <option value="Design">Design</option>
                                        <option value="Marketing">Marketing</option>
                                        <option value="Sales">Sales</option>
                                        <option value="Customer Service">Customer Service</option>
                                        <option value="Finance">Finance</option>
                                        <option value="Healthcare">Healthcare</option>
                                        <option value="Education">Education</option>
                                        <option value="Other">Other</option>
                                    </select>
                                    <i class="fas fa-chevron-down select-icon"></i>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="salary-min">Salary Range (Annual)</label>
                                <div class="salary-inputs">
                                    <div class="input-wrapper">
                                        <select name="salary" id="salary-min"" class="bg-light form-control">

                                            <option>$20k - $40k</option>
                                            <option>$40k - $60k</option>
                                            <option>$60k - $80k</option>
                                            <option >$80k - $100k</option>
                                            <option >$100k - $120k</option>

                                        </select>




                                    </div>

                                </div>
                            </div>

                            <div class="form-group">
                                <label for="experience">Experience Level <span class="required">*</span></label>
                                <div class="select-wrapper">
                                    <select id="experience" name="experience" required>
                                        <option value="">Select experience level</option>
                                        <option>0-1 years</option>
                                        <option >1-3 years</option>
                                        <option  >3-6 years</option>
                                        <option >6-9 years</option>
                                        <option>10+ years</option>
                                    </select>
                                    <i class="fas fa-chevron-down select-icon"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-section">
                        <div class="section-header">
                            <div class="section-icon">
                                <i class="fas fa-align-left"></i>
                            </div>
                            <h2>Job Description</h2>
                        </div>

                        <div class="form-group">
                            <label for="description">Job Description <span class="required">*</span></label>
                            <textarea id="description" name="description" rows="6" required></textarea>
                            <p class="help-text"><i class="fas fa-info-circle"></i> Provide a detailed description of the role, responsibilities, and what a typical day looks like.</p>
                        </div>

                        <div class="form-group">
                            <label for="minidescription"> Mini Job Description <span class="required">*</span></label>
                            <textarea id="description" name="minidescription" rows="6" required></textarea>
                            <p class="help-text"><i class="fas fa-info-circle"></i> Provide a mini description of the role</p>
                        </div>


                        <div class="form-group">
                            <label for="skills">Skills <span class="required">*</span></label>
                            <div class="input-wrapper">
                                <i class="fas fa-map-marker-alt input-icon"></i>
                                <input type="text" id="skillInput" name="skillInput" placeholder="Search Skills" >
                            </div>
                            <div class="defined_skills">
                            </div>
                            <div class="selectedkilss">
                                <p>Selected skills :</p> 
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="deadline">Application Deadline <span class="required">*</span></label>
                                <div class="input-wrapper">
                                    <i class="fas fa-calendar-alt input-icon"></i>
                                    <input type="date" id="deadline" name="deadline" required>
                                </div>
                                <p class="help-text"><i class="fas fa-info-circle"></i> Last date for candidates to apply for this position.</p>
                            </div>
                        </div>

                        <div class="form-section terms-section">
                            <div class="form-group checkbox-group">
                                <div class="custom-checkbox">
                                    <input type="checkbox" id="terms" name="terms" required>
                                    <label for="terms" class="checkbox-label"></label>
                                </div>
                                <label for="terms" class="terms-label">I agree to the <a href="#">terms and conditions</a> <span class="required">*</span></label>
                            </div>
                        </div>




                        <div class="form-actions">
                            <button type="submit" class="btn-primary">
                                <i class="fas fa-paper-plane"></i> Post Job
                            </button>
                        </div>
                </form>
            </div>
        </div>




    </div>
</div>
<Script>

    const skillInput = document.getElementById("skillInput");
    const definedSkills = document.querySelector(".defined_skills");
    const selectedSkills = document.querySelector(".selectedkilss");
    const selectedSkillsArray = [];

    <% String jsonString = new Gson().toJson(skills_list);%>
    const allSkills = JSON.parse('<%= jsonString%>');

    // Show skills on focus
    skillInput.addEventListener("focus", () => {
        definedSkills.style.display = "flex";
        renderSkills(allSkills);
    });

    // Hide skills on blur with slight delay
    skillInput.addEventListener("blur", () => {
        setTimeout(() => {
            definedSkills.style.display = "none";
            clearSkillsList();
        }, 200);
    });

    // Handle typing and enter selection
    skillInput.addEventListener("keydown", (e) => {
        const inputValue = skillInput.value.trim();

        // Add skill on Enter
        if (e.key === "Enter" && inputValue !== "") {
            e.preventDefault();
            const matchedSkill = allSkills.find(skill =>
                skill.skillName.toLowerCase() === inputValue.toLowerCase()
            );
            if (matchedSkill) {
                addSkill(matchedSkill.skillName, matchedSkill.id);
                skillInput.value = "";
                clearSkillsList();
            } else {
                alert("Skill not found");
            }
        }

        // Filter suggestions on typing
        if (e.key !== "Enter" && e.key !== "Backspace") {
            const filtered = allSkills.filter(skill =>
                skill.skillName.toLowerCase().includes(inputValue.toLowerCase())
            );
            renderSkills(filtered);
        }

        // Show all skills on backspace
        if (e.key === "Backspace") {
            renderSkills(allSkills);
        }
    });

    // Render skill suggestion buttons
    function renderSkills(skillsArray) {
        clearSkillsList();
        skillsArray.forEach(skill => {
            const a = document.createElement("input");
            a.className = "defined_skill";
            a.value = skill.skillName;
            a.disable = true;
            // a.name = skill.id;
            a.addEventListener("click", () => {
                addSkill(skill.skillName, skill.id);
                skillInput.value = "";
                clearSkillsList();
            });
            definedSkills.appendChild(a);
        });
    }

    // Add skill to selected area
    function addSkill(skillName, id) {
        if (selectedSkillsArray.includes(skillName)) {
            alert("You have already selected this skill");
            return;
        }
        const skill_obj = {
            id: id,
            skillName: skillName,
        }
        selectedSkillsArray.push(skill_obj);
        const input = document.createElement("input");
        const input2 = document.createElement("input");
        input.className = "skill";
        input.value = skillName;
        input.disabled = true;


        input2.name = "skill";
        input2.value = id;

        selectedSkills.appendChild(input);
        selectedSkills.appendChild(input2);
        input2.style.display = "none";
    }

    // Utility: Clear skill suggestion list
    function clearSkillsList() {
        while (definedSkills.firstChild) {
            definedSkills.removeChild(definedSkills.firstChild);
        }
    }


    CKEDITOR.replace('description');
</script>
</body>
</html>
