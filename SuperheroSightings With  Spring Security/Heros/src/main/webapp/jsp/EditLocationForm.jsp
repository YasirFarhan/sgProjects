<%-- 
   Document   : EditLocation
   Created on : Jun 9, 2018, 10:44:39 PM
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
        <title>Edit Location</title>
    </head>
    <body>
        <h1>Edit Location</h1>
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
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/DisplayCharacterDetails">Display Character Details</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayOrganizationPage">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/DiplayLocationPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>

                </ul>    
            </div>
            <div class="col-md-4" >
                <div style="color:red;margin-left:50px;">
                    <h1>Edit Location</h1>
                    <sf:form class="form-horizontal" role="form" method="POST"
                             action="editLocation" modelAttribute="location">
                        <div class="form-group">  <label for="locationName"/> Location Name:
                            <sf:input type="text" class="form-control" id="inputWarning2" path="locationName" placeholder="Enter Name" />
                            <sf:errors path="locationName" cssclass="error" style="color:red;"></sf:errors>

                            </div>
                            <div class="form-group">  <label for="locationDescription"/> Location Description:
                            <sf:input type="text" class="form-control" path="locationDescription" placeholder="Enter Description" />
                            <sf:errors path="locationDescription" cssclass="error" style="color:red;"></sf:errors>
                            </div>
                            <div class="form-group">  <label for="locationAddress"/> Location Address:
                            <sf:input type="text" class="form-control" path="locationAddress" placeholder="Enter Address" />
                            <sf:errors path="locationAddress" cssclass="error" style="color:red;"></sf:errors>
                            </div>

                            <div class="form-group">  <label for="longitude"/> Longitude:
                            <sf:input type="number" step="0.0000001" class="form-control" path="longitude" placeholder="Enter Longitude"/>
                            <sf:errors path="longitude" cssclass="error" style="color:red;"></sf:errors>
                            </div>
                            <div class="form-group">  <label for="latitude"/> Latitude:
                            <sf:input type="number" step="0.0000001" class="form-control" path="latitude" placeholder="Enter Latitude" />
                            <sf:errors path="latitude" cssclass="error" style="color:red;"></sf:errors>
                            <sf:hidden path="locationId"/>
                        </div>
                        <div class="form-group">
                            <div style="color:black;">
                                <input type="submit" value="Save Changes"/>
                            </div>

                        </div>
                    </sf:form>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
