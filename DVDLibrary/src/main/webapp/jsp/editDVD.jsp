<%-- 
    Document   : editDVD
    Created on : Apr 4, 2018, 9:12:10 PM
    Author     : Farhan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <title>Edit DVD</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit DVD</h1>
            <sf:form class="form-horizontal" role="form" modelAttribute="dvd" 
                     action="editDvd" method="POST">
                <div class="form-group">
                    <label for="dvd-title" class="col-md-4 control-label">Title:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="title"/>
                        <sf:errors path="title" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="director" class="col-md-4 control-label">Director:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="director.firstName" placeholder="Enter Director"/>
                        <sf:errors path="director.firstName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="notes" class="col-md-4 control-label">Notes:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="notes" placeholder="Enter Note"/>
                        <sf:errors path="notes" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <!--<label for="release-Date" class="col-md-4 control-label">Release Year:</label>-->
                        <div class="col-md-8">
                        <%--<sf:input type="date" pattern="yyyy/mm/dd" class="form-control" path="releaseDate" placeholder="Enter Release Date"/>--%>
                        <%--<sf:input type="text" pattern="yyyy/mm/dd" class="form-control" path="releaseDate" placeholder="Enter Release Date"/>--%>
                        <c:out value="${releaseDate}"/>
                        <%--<sf:errors path="releaseDate" cssclass="error"></sf:errors>--%>
                        <sf:hidden path="id"/>
                        <sf:hidden path="rating.ratingId"/>
                        <sf:hidden path="id"/>
                    </div>
                </div>



                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Save Changes"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            <input type="button" value="Cancel"/></a>
                    </div>
                </div>
            </sf:form>
        </div>
        <hr/>
        <a href="${pageContext.request.contextPath}/displayDvds"><h3>Display All Dvds</h3></a>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
