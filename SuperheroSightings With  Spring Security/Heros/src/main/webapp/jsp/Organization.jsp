<%-- 
   Document   : addOrganization
   Created on : May 22, 2018, 10:27:45 PM
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
        <title>Organizations</title>


    </head>
    <body>
        
   
        <div class="container"> 
            <h1>Organization</h1>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/DisplayCharacterDetails">Display Character Details</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/DiplayOrganizationPage">Organizations</a></li>
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
            <div class="row">
                <div class="col-md-8">
                    <h1>Organizations List</h1>
                    <button id="characterDetailsWithLocaiton" >Show / Hide Members</button>

                    <table id="organizationTable" class="table table-hover">
                        <tr>
                            <th></th>
                            <th></th>
                            <th>Name</th>
                            <th>Desctiption</th>
                            <th>Address </th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th><p>Members</p></th>
                        </tr>
                        <c:forEach var="currentOrg" items="${organizationDetails}">
                            <tr>

                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="displayOrganizationEditForm?orgId=${currentOrg.orgId}" class="btn btn-success">Edit</a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteOrganization?orgId=${currentOrg.orgId}" class="btn btn-danger">Delete</a>
                                    </sec:authorize>
                                </td>

                                <td><c:out value="${currentOrg.orgName}"/></td>
                                <td><c:out value="${currentOrg.orgDescription}"/></td>
                                <td><c:out value="${currentOrg.orgAddress}"/></td>
                                <td><c:out value="${currentOrg.email}"/></td>
                                <td><c:out value="${currentOrg.phone}"/></td>
                                <td>
                                    <c:forEach var="currentCharacter" items="${currentOrg.characterDetails}">
                                        <p><c:out value="*${currentCharacter.characterName}  "/> </p>
                                    </c:forEach>
                                </td>                                                                
                            </tr>
                        </c:forEach>
                    </table>

                </div>
                <!--add organization form--> 
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-md-4" >
                        <div style="color:red;margin-left:50px;">
                            <h1>Add Organization</h1>
                            <form class="form-horizontal" role="form" method="POST"
                                  action="addOrganization" modelAttribute="organization">
                                <div class="form-group">  <label for="orgName"/> Organization Name:
                                    <input type="text" class="form-control" id="inputWarning2" name="orgName" placeholder="Enter Name" requir/>
                                </div>
                                <div class="form-group">  <label for="orgDescription"/> Organization Description:
                                    <input type="text" class="form-control" name="orgDescription" placeholder="Enter Description" required/>
                                </div>
                                <div class="form-group">  <label for="orgAddress"/> Address:
                                    <input type="text" class="form-control" name="orgAddress" placeholder="Enter Address" required/>
                                </div>

                                <div class="form-group">  <label for="email"/> Email Address:
                                    <input type="email" class="form-control" name="email" placeholder="Enter Email" />
                                </div>
                                <div class="form-group">  <label for="phone"/> Phone:
                                    <input type="number" class="form-control" name="phone" placeholder="Enter Phone"/>
                                </div>
                                <div class="form-group">

                                    <div style="color:black;">
                                        <input type="submit" value="Add Organization"/>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>       
                </sec:authorize>
            </div>               
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script>
        $('p').hide();
        $('#characterDetailsWithLocaiton').on('click', function () {
            $('p').toggle('fast');

        });
    </script>
</html>
