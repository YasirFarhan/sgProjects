<%-- 
   Document   : addLocation
   Created on : May 22, 2018, 10:27:59 PM
   Author     : Farhan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        

        <title>Locations</title>
    </head>
    <body>
        <h1>Locations</h1> 
     
        <div class="container">
             <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h4>Hello : ${pageContext.request.userPrincipal.name}
                | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
            </h4>
        </c:if>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/index">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DisplayCharacterDetails">Display Character Details</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayOrganizationPage">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/DiplayLocationPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/userController/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul>   
            </div>

            <div class="row">
                <div class="col-md-8">
                    <h1>All Locations</h1>
                    <button id="characterDetailsWithLocaiton" >Show / Hide Characters</button>
                    <table id="charactersTable" class="table table-hover">
                        <tr>
                            <!--                            <th width="10%" >Character Id</th>-->
                            <th ></th>
                            <th ></th>
                            <th width="30%">Name</th>
                            <th width="30%">Description</th>
                            <th width="20%">Address</th>
                            <th width="10%">Longitude</th>
                            <th width="10%">Latitude</th>
                            <th width="10%"><p>Characters</p></th>

                        </tr>

                        <c:forEach var="currentLocation" items="${locationDetails}">
                            <tr>                            
                                <td><a href="displayLocationEditForm?locationId=${currentLocation.locationId}" class="btn btn-success">Edit</a></td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteLocation?locationId=${currentLocation.locationId}" class="btn btn-danger">Delete</a>
                                    </sec:authorize>
                                </td>
                                <td ><c:out value="${currentLocation.locationName}"/></td> 
                                <td><c:out value="${currentLocation.locationDescription}"/></td> 
                                <td><c:out value="${currentLocation.locationAddress}"/></td> 
                                <td><c:out value="${currentLocation.longitude}"/></td> 
                                <td><c:out value="${currentLocation.latitude}"/></td> 
                                <td >                                    
                                    <c:forEach var="currentCharacter" items="${currentLocation.characterDetails}">
                                        <p><c:out value="${currentCharacter.characterName}" /></p>
                                    </c:forEach>
                                </td>

                            </tr>                                                            
                        </c:forEach>                            
                    </table>
                </div>
                <!--add location-->
                <div class="col-md-4" >
                    <div style="color:red;margin-left:50px;">
                        <h1>Add Location</h1>
                        <form class="form-horizontal" role="form" method="POST"
                              action="addLocation" modelAttribute="location">
                            <div class="form-group">  <label for="locationName"/> Location Name:
                                <input type="text" class="form-control" id="inputWarning2" name="locationName" placeholder="Enter Name" required/>
                            </div>
                            <div class="form-group">  <label for="locationDescription"/> Location Description:
                                <input type="text" class="form-control" name="locationDescription" placeholder="Enter Description" required/>
                            </div>
                            <div class="form-group">  <label for="locationAddress"/> Location Address:
                                <input type="text" class="form-control" name="locationAddress" placeholder="Enter Address" required/>
                            </div>

                            <div class="form-group">  <label for="longitude"/> Longitude:
                                <input type="number" step="0.0000001" class="form-control" name="longitude" placeholder="Enter Longitude" min="-180.000000" max="180.000000" requierd />
                            </div>
                            <div class="form-group">  <label for="latitude"/> Latitude:
                                <input type="number" step="0.0000001" class="form-control" name="latitude" placeholder="Enter Latitude" min="-90.0000000" max="90.0000000" requierd/>
                            </div>
                            <div class="form-group">

                                <div style="color:black;">
                                    <input type="submit" value="Add Location"/>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>      
            </div>
        </div>  
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

        <script>
            $('p').hide();
            $('#characterDetailsWithLocaiton').on('click', function () {
                $('p').toggle('fast');
            });
        </script>
    </body>
</html>
