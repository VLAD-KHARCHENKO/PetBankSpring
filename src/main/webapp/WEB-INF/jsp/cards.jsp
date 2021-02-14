<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">

<head>
    <title>
        <spring:message code="cards"/>
    </title>
    <c:import url="head-part.jsp"/>

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <c:import url="header-part.jsp"/>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <c:import url="topbar-part.jsp"/>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading
                <h1 class="h3 mb-4 text-gray-800">Card Page</h1>-->

            </div>
            <!-- /.container-fluid -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>
                                <spring:message code="card.name"/>
                            </th>
                            <th>
                                <spring:message code="number"/>
                            </th>
                            <th>
                                <spring:message code="balance"/>
                            </th>
                            <th>
                                <spring:message code="condition"/>
                            </th>
                            <th>
                                <spring:message code="change"/>
                            </th>

                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${cards}" var="card">
                            <tr>
                                <td><a href="statements/${card.account.id}?page=0&size=3" class="search_link">${card.cardName}</a>
                                </td>
                                <td>${card.number}</td>
                                <td>${card.account.balance}</td>
                                <td>${card.condition}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${card.condition == 'ACTIVE'}">
                                            <c:url var="blockedUrl" value="/cards/blocked"/>
                                            <form id="${cardFormId}" action="${blockedUrl}" method="post">
                                                <input id="cardId" name="cardId" type="hidden"
                                                       value="${card.id}"/>
                                                <c:choose>
                                                    <c:when test="${user.role == 'ADMIN'}">
                                                        <input id="userId" name="userId" type="hidden"
                                                               value="${card.account.user.id}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input id="userId" name="userId" type="hidden"
                                                               value="${user.id}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <button class="btn btn-danger" type="submit">
                                                    <spring:message code="blocked.card"/>
                                                </button>
                                            </form>


                                        </c:when>

                                        <c:when test="${card.condition == 'PENDING'}">
                                            <security:authorize access="hasRole('ROLE_ADMIN')">
                                                <c:url var="activatedUrl" value="/cards/activated"/>
                                                <form id="${cardFormId}" action="${activatedUrl}" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${card.account.user.id}"/>
                                                    <button class="btn btn-success" type="submit">
                                                        <spring:message code="activate"/>
                                                    </button>
                                                </form>
                                            </security:authorize>
                                        </c:when>


                                        <c:when test="${card.condition == 'BLOCKED'}">
                                            <security:authorize access="hasRole('ROLE_CUSTOMER')">
                                                <c:url var="pendingUrl" value="/cards/pending"/>
                                                <form id="${cardFormId}" action="${pendingUrl}" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${user.id}"/>
                                                    <button class="btn btn-warning" type="submit">
                                                        <spring:message code="activating.request"/>
                                                    </button>
                                                </form>
                                            </security:authorize>
                                            <security:authorize access="hasRole('ROLE_ADMIN')">
                                                <c:url var="activatedUrl" value="/cards/activated"/>
                                                <form id="${cardFormId}" action="${activatedUrl}" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${card.account.user.id}"/>
                                                    <button class="btn btn-success" type="submit">
                                                        <spring:message code="activate"/>
                                                    </button>
                                                </form>
                                            </security:authorize>
                                        </c:when>


                                    </c:choose>

                                </a>
                            </td>

                            <!--  <td>${card.account.user.firstName} ${card.account.user.lastName}</td>-->

                        </tr>
                        </c:forEach>


                    </tbody>
                </table>

                <!--
                                   <a class="btn btn-light btn-icon-split" href="#" data-toggle="collapse"
                                      data-target="#collapseUtilities"
                                      aria-expanded="true" aria-controls="collapseUtilities">
                                          <span class="icon text-gray-600">
                                                           <i class="far fa-credit-card"></i>
                                                       </span>
                                       <span class="text">Create new card</span>
                                   </a>

                                   <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                                        data-parent="#accordionSidebar">
                                       <div class="bg-white py-2 collapse-inner rounded">
                                           <h6 class="collapse-header">Select card:</h6>
                                           <c:forEach items="${cardName}" var="value">
                                               <a class="dropdown-item" href="createCard?cardName=${value}">${value}</a>
                                           </c:forEach>

                                       </div>
                                   </div>
                                   -->

                <div class="mb-4"></div>


            </div>
            <form:form class="row g-3" modelAttribute="cardForm" action="cards" method="post">
                <div class="col-auto">
                    <div class="input-group">
                        <span class="input-group-text"><spring:message code="create.new"/></span>
                        <form:select path="cardName" class="form-control">
                            <c:forEach items="${cardName}" var="value">
                                <form:option value="${value}">${value}</form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="cardName"/>
                    </div>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">
                        <spring:message code="card"/>
                        <i class="fas fa-fw  fa-credit-card"></i>
                    </button>
                </div>
            </form:form>

        </div>
    </div>
</div>

</div>
<!-- End of Main Content -->


<!-- Footer -->
<c:import url="footer-part.jsp"/>
<!-- End of Footer -->


<!-- End of Content Wrapper -->

</div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
<c:import url="logout-part.jsp"/>
<c:import url="loader-part.jsp"/>

        </body>

        </html>