<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <meta http-equiv="refresh" content="5; url=${pageContext.request.contextPath}/displayDvds">

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>
        <div class="container">
            <h1>DVD Library Spring MVC Application from Archetype</h1>
            <hr/>

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
            <hr/>  
            <hr/>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/displayDvds"><h3>Display All Dvds</h3></a>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

