<%-- 
    Document   : editCompanyProfile
    Created on : Apr 22, 2025, 4:55:55 PM
    Author     : Dev khatri
--%>

<%@page import="java.util.Base64"%>
<%@page import="model.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/editprofile.css">
         <script src="https://cdn.ckeditor.com/4.19.1/standard/ckeditor.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Company company = null;
            String imageBase64 = null;
            if(request.getSession(false) == null || request.getSession(false).getAttribute("company") == null){
            RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
            rd.forward(request, response);
            return;
            }else{
             company = (Company)request.getSession(false).getAttribute("company");
             
             if(company.getC_profileImg()!= null){
             imageBase64 = new String(Base64.getEncoder().encode(company.getC_profileImg()));
             }
            }
        %>
       <jsp:include page="navbar.jsp" />
          <form action="editCompanyProfile" method="POST" enctype="multipart/form-data" id="myForm">
       <div class="wrapper bg-white mt-sm-5">
           <% if (request.getAttribute("errorMsg") != null) { %>
           <p class="errorMSg"><%=request.getAttribute("errorMsg")%></p>
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
            <b>Company Photo</b>
            <p>Accepted file type .png. Less than 1MB</p>
            <input type="file" name="imgPath" class="btn button border fileUploadBtn"/>
        </div>
    </div>
    <div class="py-2">
        <div class="row py-2">
            <div class="col-md-6">
                <label for="companyName">Company Name</label>
                <input type="text" name="fullname" value="<%=company.getC_name() != null ? company.getC_name() : "" %>" class="bg-light form-control"  placeholder="Apple">
            </div>
        </div>
        <div class="row py-2">
            <div class="col-md-6">
                <label for="email">Email Address</label>
                <input type="text" name="email" value="<%=company.getC_email()!= null ? company.getC_email() : "" %>" class="bg-light form-control"  placeholder="steve_@email.com">
            </div>
            <div class="col-md-6 pt-md-0 pt-3">
                <label for="phone">Phone Number</label>
                <input type="tel" name="phone" value="<%=company.getC_phone()!= null ? company.getC_phone() : "" %>" class="bg-light form-control"  placeholder="+1 213-548-6015">
            </div>
               <div class="col-md-6 pt-md-0 pt-3">
                <label for="phone">Website url</label>
                <input type="text" name="websiteurl" value="<%=company.getC_websiteurl()!= null ? company.getC_websiteurl() : "" %>" class="bg-light form-control"  placeholder="google.com">
            </div>
        
             <div class="col-md-6 pt-md-0 pt-3">
                <label for="address">Address</label>
                <input type="text" name="Address" value="<%=company.getC_address()!= null ? company.getC_address() : "" %>" class="bg-light form-control"  placeholder="Address">
            </div>
            
             <label for="City">City</label>
                <input type="text" name="city" class="bg-light form-control" value="<%=company.getC_city()!= null ? company.getC_city() : ""%>" placeholder="+1 213-548-6015">
            
            
             <div class="col-md-6 pt-md-0 pt-3 aboutme">
                <label for="">About Company</label>
                <textarea  name="aboutcompany" class="bg-light form-control textArea" placeholder="About me"><%=company.getC_about()!= null ? company.getC_about() : "" %></textarea>
            </div>
          
            <div class="col-md-6 pt-md-0 pt-3">
                <label for="Company Size">Company Size</label>
                <input type="text" name="companySize" value="<%=company.getC_size()!= null ? company.getC_size() : "" %>"  class="bg-light form-control"  placeholder="50">
            </div>
            
        </div>
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
            <Script>  
         const fileUploadBtn = document.getElementsByClassName("fileUploadBtn")[0];
         const img = document.getElementsByClassName("img")[0];
         fileUploadBtn.addEventListener("change",()=>{
         const [file] = fileUploadBtn.files;
         if(file){
         img.src = URL.createObjectURL(file);
         console.log(URL.createObjectURL(file));
     }
  })
   CKEDITOR.replace( 'aboutcompany' );
            </script>
    </body>
</html>
