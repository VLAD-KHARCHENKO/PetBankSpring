<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html lang="en">

<head>
    <title>Cards</title>
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

                <!-- Page Heading -->
                <h1 class="h3 mb-4 text-gray-800">Card Page</h1>

            </div>
            <!-- /.container-fluid -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Statements</th>
                            <th>Card name</th>
                            <th>Number</th>
                            <th>Balance</th>
                            <th>Condition</th>
                            <th>Change</th>

                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${cards}" var="card">
                            <tr>
                                <td><a href="statements/${card.account.id}" class="search_link">-></a></td>
                                <td>${card.cardName}</td>
                                <td>${card.number}</td>
                                <td>${card.account.balance}</td>
                                <td>${card.condition}</td>
                                <td><a href="profile?id=${user.id}" class="search_link">
                                    <c:choose>
                                        <c:when test="${card.condition == 'ACTIVE'}">
                                            Blocked the card
                                        </c:when>
                                        <c:when test="${card.condition == 'PENDING'}">

                                        </c:when>
                                    </c:choose>

                                </a></td>

                                <!--  <td>${card.account.user.firstName} ${card.account.user.lastName}</td>-->

                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>


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


                    <div class="mb-4"></div>


                </div>
                <form class="row g-3">
                    <div class="col-auto">
                        <div class="input-group">
                            <span class="input-group-text">Create new</span>
                            <select class="form-control" >
                                <c:forEach items="${cardName}" var="value">
                                    <option value="${value}">${value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-3">CARD
                            <i class="fas fa-fw  fa-credit-card"></i>
                        </button>
                    </div>
                </form>
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