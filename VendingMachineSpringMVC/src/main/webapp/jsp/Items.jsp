 <%-- 
  Document   : Items
  Created on : Apr 8, 2018, 11:51:03 AM
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
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Vending Machine</title>
        <style>
            hr {    border: none;    height: 1px;    width: 70%;    background-color: black;}
            #heading {  text-align: center;  font-size: 60px;  font-weight: bold;}
            .userOutput{  border: 1px;  text-align: left; border-style: solid;  width: 200px;  height: 75px; text-align:  center;}
            #eachMenuItem{  margin: 20px;  height: 140px;  width: 180px; color: black;}
            .menuNumber{text-align: left;}
        </style> 
    </head>
    <body>
        <h1 id="heading"> Vending Machine</h1>
        <div id="errorMessages"></div>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div id="menuItems" class="menu" style="text-align:center;">

                        <c:forEach var="currentItem" items="${items}">  
                            <c:if test="${currentItem.id mod 3==0}">
                                <div class="row">
                                </c:if>
                                <a href="selectItem?id=${currentItem.id}" >
                                    <div class="col-md-2" style="border: 1px solid; margin-left:16px;">    
                                        <div class="menuNumber">
                                            <c:out value="${currentItem.id}"/> <br/>
                                        </div>
                                        <c:out value="${currentItem.name}"/><br/>
                                        $<c:out value="${currentItem.price}"/><br/>
                                        Quantity Left: <c:out value="${currentItem.inventoryLevel}"/>      
                                    </div>
                                </a>
                                <c:if test="${currentItem.id mod 3==0}">
                                </div><br/><br/><br/><br/>
                            </c:if>

                        </c:forEach>

                    </div>
                </div>
                <!--right panel starts here-->
                <div class="col-md-4">
                    <div style="border: 2px dotted;">
                        <div class="row">
                            <h3 style="text-align: center"> Total $ In</h3>
                        </div>
                        <div class="row">
                            <div class="col-md-2" style="align:center;">                                   
                            </div>
                            <div class="col-md-6" style="align:center;">                                    
                                <div id="dollarsIn" class="userOutput" style="border:1px; border-style:solid;">${totalMoney} </div>
                                <br/>
                            </div>
                        </div>


                        <!--add money-->                                                   

                        <div class="row">
                            <div class="col-md-1"></div>

                            <a href="moneyAdded?amount=${1.00}">
                                <input type="button" value="Add Dollar" style="margin:6px;">
                            </a>

                            <a href="moneyAdded?amount=${0.25}">
                                <input type="button" value="Add Quarter" style="margin:6px;">
                            </a>
                        </div>
                        <br/><br/>
                        <div class="row">
                            <div class="col-md-1"></div>
                            <a href="moneyAdded?amount=${0.10}">
                                <input type="button" value="Add Nickel" style="margin:6px;">
                            </a>
                            <a href="moneyAdded?amount=${0.05}">
                                <input type="button" value="Add Dime" style="margin:6px;">
                            </a>
                        </div>
                        <!--add money ends here--> 
                        <br/><br/><hr/><br/>

                        <div class="row">
                            <label for="message" class="col-md-12 control-label" style="text-align: center;"> Messages : </label></br>
                        </div>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-6">
                                <div id="message" class="userOutput">

                                    <c:out value="${message}"/>

                                    <sf:errors path="inventoryLevel" cssclass="error"></sf:errors>
                                    </div>

                                </div>
                            </div>
                            <br/><br/><br/>

                            <div class="row">
                                <label for="item" class="col-md-12 control-label" style="text-align: center;"> Item : </label>

                            </div>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-6">
                                    <div id="item" class="userOutput">

                                    <c:forEach var="selectedItem" items="${selectedItem}">
                                        <c:out value="${selectedItem.id}"/>
                                    </c:forEach> 


                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">


                                <a href="${pageContext.request.contextPath}/makePurchase">
                                    <input type="button" value="Make Purchase"/>
                                </a>
                            </div>
                        </div><hr/><br/>

                        <div class="row">
                            <label for="change" class="col-md-12 control-label" style="text-align: center;"> Change : </label><br/>
                        </div>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-6">
                                <div id="change" class="userOutput">
                                    <c:if test="${change.quarterChange>0}">
                                        Quarters  ${change.quarterChange} <br>
                                    </c:if>
                                    <c:if test="${change.nickelsChange>0}">
                                        Nickel  ${change.nickelsChange} <br>
                                    </c:if>
                                    <c:if test="${change.dimesChange>0}">
                                        Dimes  ${change.dimesChange} <br>
                                    </c:if>
                                    <c:if test="${change.penniesChange>0}">
                                        Pennies ${change.penniesChange} <br>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        </br>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/returnChange">
                                    <input type="button" value="Return Change"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <!--right panel ends here--> 
            </div>
    </body>
</html>
