<%-- 
   Document   : SearchPage
   Created on : May 29, 2018, 10:37:23 PM
   Author     : Farhan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <title>Search Page</title>
    </head>
    <body>
        <h1>Search Page</h1>

        <!--nav bar Starts Here--> 
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
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/DisplayCharacterDetails">Display Character Details</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayOrganizationPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayLocationPage">Locations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/DiplaySearchPage">Search</a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation" >
                            <a href="${pageContext.request.contextPath}/userController/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul>    
            </div>
            <!--nav bar Ends Here--> 

            <!--main page starts here--> 
            <ul class="list-group" id="errorMessage"></ul>
            <div class="row"> 
                <div class="col-md-6">
                    <h2>Search Results</h2>
                    <table id="characterTable" class="table table-hover">
                        <tr>
                            <th width="20%">Character Name</th>
                            <th width="20%">Character Type</th>
                            <th width="20%">Power</th>
                            <th width="20%">Organization</th>
                            <th width="40%">Sighting Details</th>
                        </tr>
                        <tbody id="contentRows"/>
                    </table>
                </div>
                <!--search form starts here--> 
                <div class="col-md-6">    
                    <h2 style="text-align: center; font-size: 60px; font-family: red serifs;"> Search Criteria</h2>
                    <form class="form-horizontal" role="form" id="search-form">
                        <div class="form-group">
                            <label for="CharacterName" class="col-md-4 control-label">Character Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-character-name" placeholder="Character Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="CharacterType" class="col-md-4 control-label">Character Type:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-Character-Type" placeholder="Character Type"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="SuperPower" class="col-md-4 control-label">Super Power:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-Super-Power" placeholder="Super Power"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="OrganizationName" class="col-md-4 control-label">Organization Name</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-Organization-Name" placeholder="Organization Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="LocationName" class="col-md-4 control-label">Location Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-Location-Name" placeholder="Location Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="SightingTimeStamp" class="col-md-4 control-label">Sighting Date:</label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" id="search-Sighting-Date" placeholder="Sighting Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="SightingTime" class="col-md-4 control-label">Sighting Time</label>
                            <div class="col-md-8">
                                <input type="time" class="form-control" id="search-Sighting-Time" placeholder="Sighting Time"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="button" class="btn btn-default" id="search-button" value="Search"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/search.js"></script>

    </body>
</html>
