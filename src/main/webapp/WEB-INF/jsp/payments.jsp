<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">

<head>
    <title>Payments</title>
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
            <form:form class="row g-3" action="payments" method="post"
                       modelAttribute="paymentForm">
                <div class="row">

                    <div class="col-xl-4 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xl font-weight-bold text-primary text-uppercase mb-2">
                                            <spring:message code="from.the.card"/>
                                        </div>

                                        <form:select path="credit" class="form-control mb-3">

                                            <c:forEach items="${cards}" var="card">
                                                <form:option value="${card.number}">${card.cardName} - ${card.number} -
                                                    ${card.account.balance}$
                                                </form:option>

                                            </c:forEach>
                                        </form:select>
                                        <div class="input-group">
                                            <form:input path="amount" class="form-control"
                                                   aria-label="Dollar amount (with dot and two decimal places)"/>
                                            <span class="input-group-text">$</span>
                                            <span class="input-group-text">0.00</span>
                                            <form:errors path="amount"/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlTextarea1"
                                                   class="form-label"><spring:message code="description"/></label>
                                            <form:textarea path="description" class="form-control" id="exampleFormControlTextarea1"
                                                      rows="3"></form:textarea>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>

                    <!-- Earnings (Annual) Card Example -->
                    <div class="col-xl-4 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xl font-weight-bold text-success text-uppercase mb-2">
                                            <spring:message code="recipient.card"/>
                                        </div>

                                        <div class="input-group">
                                            <form:input path="debit" type="text" class="form-control"
                                                   aria-label="Dollar amount (with dot and two decimal places)"/>
                                            <span class="input-group-text"> <spring:message code="card.number"/></span>
                                            <form:errors path="debit"/>
                                        </div>
                                        <br/>
                                        <h5 class="card-title">Special title treatment</h5>
                                        <p class="card-text">With supporting text below as a natural lead-in to
                                            additional content.</p>
                                        <div class="d-grid gap-2 col-6 mx-auto">
                                            <button class="btn btn-success" type="submit"> <spring:message code="submit"/></button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </form:form>
        </div>
        <!-- /.container-fluid -->

    </div>


    <!-- Footer -->
    <c:import url="footer-part.jsp"/>
    <!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->


<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<c:import url="logout-part.jsp"/>
<c:import url="loader-part.jsp"/>

</body>

</html>