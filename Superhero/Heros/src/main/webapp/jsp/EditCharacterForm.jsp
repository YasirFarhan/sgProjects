<%-- 
   Document   : EditForm
   Created on : Jun 9, 2018, 9:53:18 PM
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

        <title>Edit Form</title>
    </head>
    <body>
        <h1>Edit Character </h1>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/DisplayCharacterDetails">Display Character Details</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayOrganizationPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayLocationPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>

                </ul>    
            </div>

            <!--edit Character form starts here-->
            <div class="row" id="editCharacterForm">
                <h1>Character Edit Form</h1>
                <sf:form class="form-horizontal" role="form" modelAttribute="character"
                         action="editCharacter" method="POST">
                    <div class="form-group">                       
                        <label for="name" >Character Name</label>
                        <sf:input type="text" class="form-control" id="character-Name" path="characterName" placeholder="Enter Name"/>
                        <sf:errors path="characterName" cssclass="error" style="color:red;"></sf:errors>
                        </div>
                        <div class="form-group">                       
                            <label for="details" >Character Details</label>
                        <sf:input type="text" class="form-control" id="character-Description" path="characterDescription" placeholder="Enter Description" />                        
                        <sf:errors path="characterDescription" cssClass="error" style="color:red;"></sf:errors>
                        </div>
                        <div class="form-group">                       
                            <label for="superPower" >Super Power</label>
                        <sf:input type="text" class="form-control" id="super-Power" path="superPower" placeholder="Enter Character Super Power"/>                                                
                        <sf:errors path="superPower" cssClass="error" style="color:red;"></sf:errors>
                        </div>                  
                        <div class="form-group">                       
                            <label for="characterType" >Character Type</label>
                        <sf:select name="character-Type" path="characterType">
                            <sf:option value="HERO"> He is a HERO</sf:option>
                            <sf:option value="VILLAIN"> He is a VILLAIN</sf:option>
                        </sf:select>
                        <sf:hidden path="characterId"/>
                    </div>                    
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input  type="submit" value="Save Changes"/>
                        </div>
                    </div>
                </sf:form>
            </div>

            <!--edit Location Form Ends here-->                     
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
