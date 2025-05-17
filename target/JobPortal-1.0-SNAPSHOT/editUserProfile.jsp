<%-- 
    Document   : editUserProfile
    Created on : Apr 7, 2025, 11:37:10 PM
    Author     : Dev khatri
--%>

<%@page import="model.Company"%>
<%@page import="java.util.Base64"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Skill,java.util.ArrayList,model.User,model.category,model.userJobDetail" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/editprofile.css">
        <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/45.0.0/ckeditor5.css">
        <script src="https://cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <% User user = (User) request.getSession(false).getAttribute("user");
        
        if(user == null){
        
        response.sendRedirect("Index.jsp");
        return;
        }
            ArrayList<Skill> skills_list = (ArrayList<Skill>)request.getAttribute("skills");
            ArrayList<category> categories_List = (ArrayList<category>)request.getAttribute("catogries");
            String imageBase64 = null;
            if(user.getProfileImg() != null){
             imageBase64 = new String(Base64.getEncoder().encode(user.getProfileImg()));
             }
            %>
          <jsp:include page="navbar.jsp" />
          <form action="editUserProfile" method="POST" enctype="multipart/form-data" id="myForm">
       <div class="wrapper bg-white mt-sm-5">
           <% if (request.getSession(false).getAttribute("errorMsg") != null) { %>
           <p class="errorMSg"><%=request.getSession(false).getAttribute("errorMsg")%></p>
      <% }%>
    <h4 class="pb-4 border-bottom">Account settings</h4>
    <div class="d-flex align-items-start py-3 border-bottom">
         <% if (imageBase64 != null) { %>
          <img src="data:image/png;base64,<%=imageBase64%>"
            class="img" alt="">
      <% } else { %>
          <img src="https://static.vecteezy.com/system/resources/previews/008/442/086/original/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg"
            class="img" alt="">
      <% } %>
        <div class="pl-sm-4 pl-2" id="img-section">
            <b>Profile Photo</b>
            <p>Accepted file type .png. Less than 1MB</p>
            <input type="file" name="imgPath" class="btn button border fileUploadBtn" value="<%=user.getProfileImg() != null ? user.getProfileImg() : ""%>"/>
        </div>
    </div>
    <div class="py-2">
        <div class="row py-2">
            <div class="col-md-6">
                <label for="firstname">First Name</label>
                <input type="text" name="fullname" class="bg-light form-control" value="<%=user.getFullName()%>" placeholder="Steve">
            </div>
        </div>
        <div class="row py-2">
            <div class="col-md-6">
                <label for="email">Email Address</label>
                <input type="text" name="email" class="bg-light form-control" value="<%=user.getEmail()%>" placeholder="steve_@email.com">
            </div>
            <div class="col-md-6 pt-md-0 pt-3">
                <label for="phone">Phone Number</label>
                <input type="tel" name="phone" class="bg-light form-control" value="<%=user.getPhoneNumber() !=null ? user.getPhoneNumber() : ""%>" placeholder="+1 213-548-6015">
            </div>
              <div class="col-md-6 pt-md-0 pt-3 genderSelect">
                <label for="gender">Gender</label>
                <div>
                <input type="radio" name="gender" class="bg-light form-control" value="male" <%= (user.getGender() != null && user.getGender().equalsIgnoreCase("male")) ? "checked" : "" %> >Male
                <input type="radio" name="gender" class="bg-light form-control" value="female" <%= (user.getGender() != null && user.getGender().equalsIgnoreCase("female")) ? "checked" : "" %> >Female
                <input type="radio" name="gender" class="bg-light form-control" value="other" <%= (user.getGender() != null && user.getGender().equalsIgnoreCase("other")) ? "checked" : "" %>> Other
                </div>
            </div>
        </div>
                <div class="col-md-6 pt-md-0 pt-3">
                <label for="Address">Address</label>
                <input type="text" name="Address" class="bg-light form-control" value="<%=user.getAddress() != null ? user.getAddress() : ""%>" placeholder="+1 213-548-6015">
            </div>
         <div class="col-md-6 pt-md-0 pt-3">
                <label for="City">City</label>
                <input type="text" name="city" class="bg-light form-control" value="<%=user.getCity() != null ? user.getCity() : ""%>" placeholder="+1 213-548-6015">
            </div>
        <div class="row py-2">
            <div class="col-md-6">
                <label for="country">Education</label>
                <select name="education" id="country" class="bg-light">
                    <option value="BS" selected>BS</option>
                    <option value="MS">MS</option>
                    <option value="Phd">Phd</option>
                    <option value="Diploma">Diploma</option>
                </select>
            </div>
            <div class="col-md-6 pt-md-0 pt-3" id="lang">
                <label for="language">Language</label>
                <div class="arrow">
                    <select name="language" id="language" class="bg-light">
                        <option value="english" selected>English</option>
                        <option value="english_us">English (United States)</option>
                        <option value="enguk">English UK</option>
                        <option value="arab">Arabic</option>
                    </select>
                </div>
            </div>
             <div class="col-md-6 pt-md-0 pt-3 aboutme">
                <label for="">About me</label>
                <textarea  name="aboutme" class="bg-light form-control textArea" placeholder="About me"><%=user.getAboutMe() != null ? user.getAboutMe() : "" %></textarea>
            </div>
            
              <div class="col-md-6 pt-md-0 pt-3 educationdetail">
                <label for="about education">About Education</label>
                <textarea name="abouteducation" class="bg-light form-control textArea" placeholder="About education"><%= user.getAboutEducation() != null ? user.getAboutEducation() : "" %></textarea>
            </div>
             <div class="col-md-6 pt-md-0 pt-3 educationdetail">
                <label for="about experience">About Experience</label>
               <textarea name="aboutexperience" class="bg-light form-control textArea" placeholder="About experience"><%=user.getUserJobDetail() != null && user.getUserJobDetail().getAboutexperience() != null ? user.getUserJobDetail().getAboutexperience() : "" %></textarea>
            </div>
           <div class="col-md-6">
    <label for="Experience">Experience</label>
    <select name="Experience" id="experience" class="bg-light form-control">
        <%
            String selectedExperience = null;
            if (user.getUserJobDetail() != null && user.getUserJobDetail().getExperience() != null) {
                selectedExperience = user.getUserJobDetail().getExperience();
            }
        %>

        <option  <%= selectedExperience != null && "0-1".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>0-1 years</option>
        <option  <%= selectedExperience != null && "1-3".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>1-3 years</option>
        <option  <%= selectedExperience != null && "3-6".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>3-6 years</option>
        <option  <%= selectedExperience != null && "6-9".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>6-9 years</option>
        <option  <%= selectedExperience != null && "10+".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>10+ years</option>
   
    </select>
</div>
        
 <div class="col-md-6">
    <label for="salary">Salray Range</label>
    <select name="salary" id="experience" class="bg-light form-control">
        <%
            String selectedSalrayRange = null;
            if (user.getUserJobDetail() != null && user.getUserJobDetail().getSalary_range()!= null) {
                selectedSalrayRange = user.getUserJobDetail().getSalary_range();
            }
        %>

        <option  <%= selectedSalrayRange != null && "$20k - $40k".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>$20k - $40k</option>
        <option  <%= selectedSalrayRange != null && "$40k - $60k".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>$40k - $60k</option>
        <option  <%= selectedSalrayRange != null && "$60k - $80k".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>$60k - $80k</option>
        <option  <%= selectedSalrayRange != null && "$80k - $100k".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>$80k - $100k</option>
        <option  <%= selectedSalrayRange != null && "$100k - $120k".equalsIgnoreCase(selectedExperience) ? "selected" : "" %>>$100k - $120k</option>
   
    </select>
</div>
             <div class="col-md-6">
                 <div class="skillInputArea">
                <label for="firstname">Skills</label>
                <input type="text" class="bg-light form-control " id="skillInput" placeholder="Enter your skills">
                
                <div class="defined_skills">
                   
                </div>
                 <div class="selectedkilss">
                    <p>Selected skills :</p> 
                    <%if(user.getUserSkills() != null){%>
                     <%for(Skill skill : user.getUserSkills()){%>
                     <input class="skill" value="<%=skill.getSkillName()%>" disabled>
                     <input value="<%=skill.getId()%>" name="skill" style="display:  none">
                        <%}%>
                      <%}%>
                </div>
                 </div>
             </div>
             <div class="col-md-6">
                <label for="Job Categories">Job Categories</label>
                 <select name="category" id="country" class="bg-light">
                  <% for (category list : categories_List) {%>
                   <option value="<%= list.getCategory_name()%>" <%= (user.getUserJobDetail() != null && user.getUserJobDetail().getWorkType()!= null && user.getUserJobDetail().getCategory().equalsIgnoreCase(list.getCategory_name())) ? "selected" : "" %>  ><%= list.getCategory_name()%></option>
                     <%}%>
                 </select>
            </div>
             <div class="col-md-6 pt-md-0 pt-3 genderSelect">
                <label for="Work Type">Work Type</label>
                <div>
                <input type="radio" name="WorkType" class="bg-light form-control" value="Fulltime" <%= (user.getUserJobDetail() != null && user.getUserJobDetail().getWorkType()!= null && user.getUserJobDetail().getWorkType().equalsIgnoreCase("Fulltime")) ? "checked" : "" %> >Full time
                <input type="radio" name="WorkType" class="bg-light form-control" value="Parttime" <%= (user.getUserJobDetail() != null && user.getUserJobDetail().getWorkType()!= null && user.getUserJobDetail().getWorkType().equalsIgnoreCase("Parttime")) ? "checked" : "" %> >Part time
                <input type="radio" name="WorkType" class="bg-light form-control" value="Internship" <%= (user.getUserJobDetail() != null && user.getUserJobDetail().getWorkType()!= null && user.getUserJobDetail().getWorkType().equalsIgnoreCase("Internship")) ? "checked" : "" %> > Internship
                </div>
            </div>
            <div class="col-md-6 pt-md-0 pt-3 genderSelect">
                <label for="WorkLocation">Work Location</label>
                <div>
                <input type="radio" name="WorkLocation" class="bg-light form-control" value="Onsite" <%= (user.getUserJobDetail() != null && user.getUserJobDetail().getWorkType()!= null && user.getUserJobDetail().getWorkLocation().equalsIgnoreCase("Onsite")) ? "checked" : "" %> >Onsite
                <input type="radio" name="WorkLocation" class="bg-light form-control" value="Remote" <%= (user.getUserJobDetail() != null && user.getUserJobDetail().getWorkType()!= null && user.getUserJobDetail().getWorkLocation().equalsIgnoreCase("Remote")) ? "checked" : "" %>  >Remote
                <input type="radio" name="WorkLocation" class="bg-light form-control" value="Hybrid" <%= (user.getUserJobDetail() != null && user.getUserJobDetail().getWorkType()!= null && user.getUserJobDetail().getWorkLocation().equalsIgnoreCase("Hybrid")) ? "checked" : "" %> >Hybrid
                </div>
            </div>
        </div>
            <div class="pl-sm-4 pl-2" id="img-section">
            <b>Resume Photo</b>
            <p>Accepted file type .png. Less than 1MB</p>
            <input type="file" name="resumePath" class="btn button border fileUploadBtn" value="<%=user.getUserJobDetail() != null && user.getUserJobDetail().getResume()!= null ? user.getUserJobDetail().getResume() : ""%>"/>
            <p style="color: black;font-size: 15px">Selected Resume : <%=user.getUserJobDetail() != null && user.getUserJobDetail().getResumeName() != null ? user.getUserJobDetail().getResumeName() : "" %></p>
            </div
        <div class="py-3 pb-4 border-bottom">
            <button class="btn btn-primary mr-3" id="save"> Save Changes</button>
            <button class="btn border button">Cancel</button>
        </div>
        <div class="d-sm-flex align-items-center pt-3" id="deactivate">
            <div>
                <b>Deactivate your account</b>
                <p>Details about your company account and password</p>
            </div>
            <div class="ml-auto">
                <button class="btn danger">Deactivate</button>
            </div>
            
        </div>
    </div>
</div>
       </form>
   <script>
    const skillInput = document.getElementById("skillInput");
    const selectedSkills = document.querySelector(".selectedkilss");
    const definedSkills = document.querySelector(".defined_skills");
    const imgUploadForm = document.getElementById("imgUploadForm");
    
    const selectedSkillsArray = [];

    <% String jsonString = new Gson().toJson(skills_list); %>
    const allSkills = JSON.parse('<%= jsonString %>');
    
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
                addSkill(matchedSkill.skillName,matchedSkill.id);
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
                addSkill(skill.skillName,skill.id);
                skillInput.value = "";
                clearSkillsList();
            });
            definedSkills.appendChild(a);
        });
    }

    // Add skill to selected area
    function addSkill(skillName,id) {
        if (selectedSkillsArray.includes(skillName)) {
            alert("You have already selected this skill");
            return;
        }
        const skill_obj = {
            id:id,
            skillName:skillName,
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
    
    const btn = document.getElementById("save");

  const fileUploadBtn = document.getElementsByClassName("fileUploadBtn")[0];
  const img = document.getElementsByClassName("img")[0];
  fileUploadBtn.addEventListener("change",(event)=>{
       const [file] = fileUploadBtn.files
        if (file) {
       img.src = URL.createObjectURL(file);
   }
  })
  
 CKEDITOR.replace( 'aboutme' );
 CKEDITOR.replace( 'abouteducation' );
 CKEDITOR.replace( 'aboutexperience' );
 //aboutexperience
</script>

    </body>
</html>
