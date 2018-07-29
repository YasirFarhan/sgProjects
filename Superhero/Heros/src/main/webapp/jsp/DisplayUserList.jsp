<%-- 
  Document   : DisplayUserList
  Created on : Jun 23, 2018, 11:30:32 PM
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
        <title>Heros Admin Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Super Hero's Users</h1>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">

                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/index">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/DisplayCharacterDetails">Display Character Details</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayOrganizationPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DiplayLocationPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search</a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation" class="active">
                            <a href="${pageContext.request.contextPath}/userController/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul>    
            </div>


            <h1>Users</h1>
            <a href="displayUserForm">Add a User</a><br/>
            <hr/>
            <table id="userTable" class="table table-hover">

                <tr>
                    <th width="20%">User Status </th>
                    <th width="30%">User Name</th>
                    <th width="20%"> </th>
                    <th width="20%"></th>
                </tr>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>
                            <c:if test="${user.enabled==true}">                                
                                <p>Enabled</p>
                            </c:if>
                            <c:if test="${user.enabled==false}">                                
                                <p>Disabled</p> 
                            </c:if>
                        </td> 

                        <td><c:out value="${user.username}"/> </td>
                        <c:if test="${user.username != 'admin'}">
                            <td><a href="deleteUser?username=${user.username}">DeleteUser  </a> </td>
                            <td><a href="enableOrDisableUser?username=${user.username}">Enable/Disable User</a></td>
                        </c:if>                        
                    </tr>                    
                </c:forEach>
            </table>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
