<%-- 
    Document   : dvds
    Created on : Apr 5, 2018, 6:19:23 PM
    Author     : Farhan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <title>DVDs </title>
    </head>
    <body>
        <div class="container">
            <h1>DVD Library Spring MVC Application from Archetype</h1>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-3">
                            <a href="${pageContext.request.contextPath}/displayNewDvdForm">
                                <input type="button" value="Create Dvd"/>
                            </a>
                        </div>
                        <div class="col-md-3">
                            <div class="col-md-3">               
                                <a href="${pageContext.request.contextPath}/displaySearchPage">
                                    <input type="button" name="search" value="Search">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <select  name="category">
                                <option value="title">Title</option>
                                <option value="releaseDate">Release Date</option>
                                <option value="director">Director</option>
                                <option value="rating">Rating</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <input type="tex" name="searchTerm" placeholder="Search Term"/></div>
                    </div>
                    <h1>My Dvds</h1>
                    <a href="index.jsp" ><h3>Home</h3></a>
                    <table id="dvdTable" class="table table-hover">
                        <tr>
                            <th width="20%">Title</th>
                            <th width="20%">Release Date</th>
                            <th width="20%">Director</th>
                            <th width="20%">Notes</th>
                            <th width="10%">Rating</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>

                        <c:forEach var="currentDvd" items="${dvdList}">
                            <tr>
                                <td>
                                    <c:out value="${currentDvd.title}"/>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.releaseDate}"/>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.director.firstName}"/>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.notes}"/>
                                </td>
                                <td>
                                    <%--<c:out value="${currentDvd.rating}"/>--%>
                                    <c:out value="${currentDvd.rating.rating}"/>


                                </td>
                                <td>
                                    <a href="displayEditeditDvdForm?id=${currentDvd.id}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteDvd?id=${currentDvd.id}">
                                        Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 
            </div> 

        </div>
    </body>
</html>
