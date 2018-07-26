<%-- 
   Document   : EditOrganizationForm
   Created on : Jun 10, 2018, 11:17:51 AM
   Author     : Farhan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <title>Edit Organization</title>
    </head>
    <body>        
        <h1>Edit Organization</h1>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h4>Hello : ${pageContext.request.userPrincipal.name}
                | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
            </h4>
        </c:if>
        <div class="container">
            <!--nav bar starts here--> 
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/index">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DisplayCharacterDetails">Display Character Details</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/DiplayOrganizationPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayLocationPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
                </ul>   
            </div>

            <div class="col-md-4" >
                <div style="color:red;margin-left:50px;">
                    <h1>Edit Organization</h1>
                    <sf:form class="form-horizontal" role="form" method="POST"
                             action="editOrganization" modelAttribute="organization">
                        <div class="form-group">  
                            <label for="orgName"/> Organization Name:
                            <sf:input type="text" class="form-control" id="inputWarning2" path="orgName" placeholder="Enter Name" />
                            <sf:errors path="orgName" cssclass="error" style="color:red;"></sf:errors>
                            </div>
                            <div class="form-group">
                                <label for="orgDescription"/> Organization Description:
                            <sf:input type="text" class="form-control" path="orgDescription" placeholder="Enter Description" />
                            <sf:errors path="orgDescription" cssclass="error" style="color:red;"></sf:errors>

                            </div>
                            <div class="form-group"> 
                                <label for="orgAddress"/> Address:
                            <sf:input type="text" class="form-control" path="orgAddress" placeholder="Enter Address" />
                            <sf:errors path="orgAddress" cssclass="error" style="color:red;"></sf:errors>

                            </div>

                            <div class="form-group"> 
                                <label for="email"/> Email Address:
                            <sf:input type="email" class="form-control" path="email" placeholder="Enter Email" />
                            <sf:errors path="email" cssclass="error" style="color:red;"></sf:errors>
                            </div>
                            <div class="form-group"> 
                                <label for="phone"/> Phone:
                            <sf:input type="number" class="form-control" path="phone" placeholder="Enter Phone"/>
                            <sf:errors path="phone" cssclass="error" style="color:red;"></sf:errors>
                            <sf:hidden path="orgId"/>

                        </div>
                        <div class="form-group">
                            <div style="color:black;">
                                <input type="submit" value="Save Changes"/>
                            </div>

                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
