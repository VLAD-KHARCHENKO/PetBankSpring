<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">

<head>
     <title>Statements</title>
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
                    <h1 class="h3 mb-4 text-gray-800">Statements Page</h1>

                    ${card.cardName}
                    ${card.number}
                    <div class="card shadow mb-4">
                        <!-- Card Header - Accordion -->
                        <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse"
                           role="button" aria-expanded="true" aria-controls="collapseCardExample">
                            <h6 class="m-0 font-weight-bold text-primary">Collapsable Card Example</h6>
                        </a>
                        <!-- Card Content - Collapse -->
                        <div class="collapse show" id="collapseCardExample">
                            <div class="card-body">

                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>date</th>
                                                <th>debit.number</th>
                                                <th>credit.number</th>
                                                <th>amount</th>
                                                <th>description</th>
                                                <th>Status</th>

                                            </tr>
                                            </thead>

                                            <tbody>
                                            <c:forEach items="${savedPayments}" var="savedPayment">
                                                <tr>
                                                    <td>${savedPayment.id}</td>
                                                    <td>${savedPayment.date}</td>
                                                    <td>${savedPayment.debit.number}</td>
                                                    <td>${savedPayment.credit.number}</td>
                                                    <td>${savedPayment.amount}</td>
                                                    <td>${savedPayment.description}</td>
                                                    <td>${savedPayment.status}</td>


                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>
                            </div>
                        </div>
                    </div>

                    <div class="card shadow mb-4">
                        <!-- Card Header - Accordion -->
                        <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse"
                           role="button" aria-expanded="true" aria-controls="collapseCardExample1">
                            <h6 class="m-0 font-weight-bold text-primary">Collapsable Card Example</h6>
                        </a>
                        <!-- Card Content - Collapse -->
                        <div class="collapse show" id="collapseCardExample1">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable1 " width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>date</th>
                                            <th>debit.number</th>
                                            <th>credit.number</th>
                                            <th>amount</th>
                                            <th>description</th>
                                            <th>Status</th>

                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach items="${paidPayments}" var="paidPayment">
                                            <tr>
                                                <td>${paidPayment.id}</td>
                                                <td>${paidPayment.date}</td>
                                                <td>${paidPayment.debit.number}</td>
                                                <td>${paidPayment.credit.number}</td>
                                                <td>${paidPayment.amount}</td>
                                                <td>${paidPayment.description}</td>
                                                <td>${paidPayment.status}</td>


                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <c:import url="footer-part.jsp"/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <c:import url="logout-part.jsp"/>
    <c:import url="loader-part.jsp"/>

</body>

</html>