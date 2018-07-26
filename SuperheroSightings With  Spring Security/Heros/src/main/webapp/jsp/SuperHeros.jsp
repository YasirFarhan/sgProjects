<%-- 
   Document   : SuperHeros
   Created on : May 24, 2018, 10:35:33 PM
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

        <title>Super Hero</title>
    </head>
    <body>
        <h1>Super Hero</h1>


        <div class="container">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h4>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </h4>
            </c:if>
            <!--nav bar starts here--> 
            <div class="navbar">
                <ul class="nav nav-tabs">

                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/index">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/DisplayCharacterDetails">Display Character Details</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayOrganizationPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayLocationPage">Locations</a></li>
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
            <!--report sighting Character Starts here-->
            <div class="row">
                <sec:authorize access="hasRole('ROLE_USER')">
                    <h4>Report Character Sighting & / OR Join Organization</h4>
                    <form class="form-horizontal" role="form" method="POST"
                          action="addCharacterSightingOrganization" >

                        <label for="location" >Select Character</label>
                        <select name="characterId">
                            <option value="null">Pick a character</option>
                            <c:forEach var="currentCharacter" items="${characterDetails}">
                                <option value="${currentCharacter.characterId}"/>
                                <c:out value="${currentCharacter.characterName}" />                                
                            </c:forEach>
                        </select>
                        </br>
                        <label for="organization" >Add organization </label>
                        <select name="organizationId">
                            <option value="null">Select Organization</option>
                            <c:forEach var="currentOrganization" items="${organization}">
                                <option value="${currentOrganization.orgId}"/>
                                <c:out value="${currentOrganization.orgName}" />                                                                                        
                            </c:forEach>
                        </select>
                        <div class="row">
                            <label for="location" >Add Sighting </label>
                            <select name="locationId">
                                <option value="null">Select Location</option>
                                <c:forEach var="currentLocation" items="${locations}">
                                    <option value="${currentLocation.locationId}"/>
                                    <c:out value="${currentLocation.locationName}" />                                
                                    |Long:<c:out value="${currentLocation.longitude}" />                                
                                    |Lat: <c:out value="${currentLocation.latitude}"/>                                

                                </c:forEach>
                            </select>
                            <label for="sightingDate">Sighting Date</label>
                            <input type="date" name="sightingDate" placeholder="USE Format yyyy/mm/dd"/>
                            <input type="time" name="sightingTime" />
                        </div>
                        <div class="row">
                            <input type="submit" value="Submit"/>
                        </div>
                    </form>
                </sec:authorize>
            </div>
            <!--display Character Starts here-->

            <div class="row">
                <div class="col-md-8">
                    <h1> Character Details</h1>            
                    <hr/>
                    <button id="showHideSightings" >Show / Hide Details</button>
                    <table id="charactersTable" class="table table-hover">
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Description</th>
                            <th width="20%">SuperPower</th>
                            <th width="10%">Type</th>
                            <th width="10%"></th>
                            <th width="10%"></th> 
                        </tr>
                        <c:forEach var="currentCharacter" items="${characterDetails}">
                            <tr style="background-color: gray;">
                                <td> <c:out value="${currentCharacter.characterName}"/></td> 
                                <td> <c:out value="${currentCharacter.characterDescription}"/></td> 
                                <td><c:out value="${currentCharacter.superPower}"/></td> 
                                <td> <c:out value="${currentCharacter.characterType}"/></td> 
                                <td>
                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <a href="displayCharacterEditForm?characterId=${currentCharacter.characterId}" class="btn btn-success">Edit</a>
                                    </sec:authorize>
                                </td>

                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteCharacter?characterId=${currentCharacter.characterId}" class="btn btn-danger">Delete</a>
                                    </sec:authorize>      
                                </td>

                            </tr>
                            <tr>

                                <td style="color:red;" colspan="2"><b><p>SightingLocation:</b>  </br><c:forEach var="currentLocation" items="${currentCharacter.sightingLocations}">
                                        <sec:authorize access="hasRole('ROLE_USER')">
                                            <a href="deleteLocationFromCharacter?locationId=${currentLocation.locationId}&characterId=${currentCharacter.characterId}">delete:</a>
                                        </sec:authorize>
                                        <c:out value="${currentLocation.locationName},|Long:${currentLocation.longitude},|Lat${currentLocation.latitude},"/> 
                                        </br>
                                    </c:forEach></p></td>
                                <td style="color:blue;" colspan="4" id="organization"><b><p>Organization:</b>  </br> <c:forEach var="currentorganization" items="${currentCharacter.characterOrganization}">
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <a href="deleteOrgFromCharacter?orgId=${currentorganization.orgId}&characterId=${currentCharacter.characterId}">delete:</a>
                                        </sec:authorize>
                                        <c:out value="${currentorganization.orgName},|Description:${currentorganization.orgDescription}"/> 
                                        </br>
                                    </c:forEach></p></td>
                            </tr>




                        </c:forEach>
                    </table>

                </div>

                <div class="col-md-1"></div>

                <!--add Character Starts here-->     
                <sec:authorize access="hasRole('ROLE_USER')">
                    <div class="col-md-2" id="addCharacter">
                        <div class="row">
                            <h2> Add New Character </h2>

                            <form class="form-horizontal" role="form" method="POST"
                                  action="createCharacter">
                                <div class="form-group">                       
                                    <label for="name" >Character Name</label>
                                    <input type="text" class="form-control" name="name" placeholder="Enter Name" required/>                                       
                                </div>
                                <div class="form-group">                       
                                    <label for="details" >Character Details</label>
                                    <input type="text" class="form-control" name="description" placeholder="Enter Description" required/>                        
                                </div>
                                <div class="form-group">                       
                                    <label for="superPower" >Super Power</label>
                                    <input type="text" class="form-control" name="superPower" placeholder="Enter Character Super Power" required/>                                                
                                </div>                  


                                <div class="form-group">                       
                                    <label for="characterType" >Character Type</label>
                                    <select name="characterType">
                                        <option value="HERO"> He is a HERO</option>
                                        <option value="VILLAIN"> He is a VILLAIN</option>
                                    </select>
                                </div>                    

                                <div class="form-group">

                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" value="Add Character"/>
                                    </div>
                                </div>
                            </form>   
                        </div>
                    </div>        
                    <!--add Character ends here-->
                </sec:authorize>
            </div>



            <!--Recent Sightings Starts here--> 

            <div class="row">
                <h1 style="color: red; text-align: center;">Latest 10 Sightings </h1>
                <table id="charactersTable" class="table table-hover">
                    <tr>
                        <th>Character Name</th>
                        <th>Sighting Location</th>                                
                        <th>Latitude</th>
                        <th>Longitude</th>
                        <th>Sighting Date</th>
                        <th>Sighting Time</th>
                    </tr>                           
                    <c:forEach var="currentRecentSightings" items="${recentSightings}">
                        <tr>
                            <td> <c:out value="${currentRecentSightings.characterName}"/></td> 
                            <td> <c:out value="${currentRecentSightings.locationName}"/></td> 
                            <td> <c:out value="${currentRecentSightings.latitude}"/></td> 
                            <td> <c:out value="${currentRecentSightings.longitude}"/></td> 
                            <td> <c:out value="${currentRecentSightings.sightingDate}"/></td> 
                            <td> <c:out value="${currentRecentSightings.sightingTime}"/></td> 
                        </tr>
                    </c:forEach>
                </table>

            </div>

            <!--Recent Sightings Ends here--> 
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script>
            $('p').hide();
            $('#showHideSightings').on('click', function () {
                $('p').toggle('fast');

            });

            $('#showHideSightings').on('click', function () {
                $('p2').toggle('fast');

            });
        </script>
    </body>

</html>
