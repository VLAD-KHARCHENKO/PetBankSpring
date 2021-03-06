<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html lang="en">

<head>
    <title>Pending cards</title>
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
                <h1 class="h3 mb-4 text-gray-800">User account Page</h1>-->

            </div>
            <!-- /.container-fluid -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>
                                <spring:message code="number"/>
                            </th>
                            <th>
                                <spring:message code="condition"/>
                            </th>
                            <th>
                                <spring:message code="first.name"/>
                            </th>
                            <th>
                                <spring:message code="last.name"/>
                            </th>

                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${pendingCards}" var="pendingCard">
                            <tr>
                                <td>${pendingCard.id}</td>
                                <td>${pendingCard.number}</td>
                                <td>${pendingCard.account.user.firstName}</td>
                                <td>${pendingCard.account.user.lastName}</td>
                                <td>
                                    <c:url var="activatedUrl" value="/pending-cards/activated"/>
                                    <form id="${cardFormId}" action="${activatedUrl}" method="post">
                                        <input id="cardId" name="cardId" type="hidden"
                                               value="${pendingCard.id}"/>
                                        <button class="btn btn-success" type="submit">
                                            <spring:message code="activate"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <c:import url="footer-part.jsp"/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
</div>

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<c:import url="logout-part.jsp"/>
<c:import url="loader-part.jsp"/>

</body>

        </html>