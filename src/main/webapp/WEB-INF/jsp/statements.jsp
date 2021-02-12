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
                <h1 class="h3 mb-4 text-gray-800">${card.cardName} ${card.number}</h1>


                <div class="card shadow mb-4">

                    <a href="#collapseCardExample" class="d-block card-header py-3 collapsed" data-toggle="collapse"
                       role="button" aria-expanded="false" aria-controls="collapseCardExample">
                        <h6 class="m-0 font-weight-bold text-primary">
                            <spring:message code="saved.payments"/>
                        </h6>
                    </a>

                    <div class="collapse" id="collapseCardExample">
                        <div class="card-body">

                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>
                                            <spring:message code="date"/>
                                        </th>
                                        <th>
                                            <spring:message code="credit.number"/>
                                        </th>
                                        <th>
                                            <spring:message code="debit.number"/>
                                        </th>
                                        <th>
                                            <spring:message code="amount"/>
                                        </th>
                                        <th>
                                            <spring:message code="description"/>
                                        </th>
                                        <th>
                                            <spring:message code="pay"/>
                                        </th>
                                        <th>
                                            <spring:message code="delete"/>
                                        </th>

                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${savedPayments}" var="savedPayment">
                                        <tr>
                                            <td>${savedPayment.id}</td>
                                            <td>${savedPayment.date}</td>
                                            <td>${savedPayment.credit.number}</td>
                                            <td>${savedPayment.debit.number}</td>
                                            <td>${savedPayment.amount}</td>
                                            <td>${savedPayment.description}</td>

                                            <td>
                                                <div class="d-grid gap-2 col-10 mx-auto">
                                                    <c:url var="payUrl" value="/statements/pay"/>
                                                    <form id="${paymentId}" action="${payUrl}" method="post">
                                                        <input id="payId" name="payId" type="hidden"
                                                               value="${savedPayment.id}"/>
                                                        <input id="cardId" name="cardId" type="hidden"
                                                               value="${card.id}"/>
                                                        <button class="btn btn-success" type="submit">
                                                            <spring:message code="pay"/>
                                                        </button>
                                                    </form>
                                                </div>


                                            </td>
                                            <td>
                                                <div class="d-grid gap-2 col-10 mx-auto ">
                                                    <c:url var="deleteUrl" value="/statements/remove"/>
                                                    <form id="${paymentFormId}" action="${deleteUrl}" method="post">
                                                        <input id="paymentId" name="paymentId" type="hidden"
                                                               value="${savedPayment.id}"/>
                                                        <input id="cardId" name="cardId" type="hidden"
                                                               value="${card.id}"/>
                                                        <button class="btn btn-warning" type="submit">
                                                            <spring:message code="delete"/>
                                                        </button>
                                                    </form>

                                                </div>

                                            </td>


                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card shadow mb-4">

                    <a href="#collapseCardExample1" class="d-block card-header py-3" data-toggle="collapse"
                       role="button" aria-expanded="true" aria-controls="collapseCardExample1">
                        <h6 class="m-0 font-weight-bold text-primary">
                            <spring:message code="paid.payments"/>
                        </h6>
                    </a>

                    <div class="collapse show" id="collapseCardExample1">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable1 " width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>
                                            <spring:message code="date"/>
                                        </th>
                                        <th>
                                            <spring:message code="credit.number"/>
                                        </th>
                                        <th>
                                            <spring:message code="debit.number"/>
                                        </th>
                                        <th>
                                            <spring:message code="amount"/>
                                        </th>
                                        <th>
                                            <spring:message code="description"/>
                                        </th>
                                        <th>
                                            <spring:message code="status"/>
                                        </th>

                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${paidPayments}" var="paidPayment">
                                        <tr>
                                            <td>${paidPayment.id}</td>
                                            <td>${paidPayment.date}</td>
                                            <td>${paidPayment.credit.number}</td>
                                            <td>${paidPayment.debit.number}</td>
                                            <td>${paidPayment.amount}</td>
                                            <td>${paidPayment.description}</td>
                                            <td>${paidPayment.status}</td>


                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <!-- Pagination-->
                            <nav aria-label="Page navigation example">
                                <c:if test="${paidPaymentsPages > 1}">
                                    <ul class="pagination">
                                        <c:choose>
                                            <c:when test="${currentPage  != 0 }">
                                                <li class="page-item"><a href="statements/${card.id}?page=${currentPage-1}&size=3"><span
                                                        class="page-link">Prev</span></a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item disabled"><span class="page-link"><spring:message
                                                        code="prev"/></span></li>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach var="numberPage" begin="1" end="${paidPaymentsPages}">
                                            <c:choose>
                                                <c:when test="${currentPage == (numberPage-1) }">
                                                    <li class="page-item active"><a
                                                            href="statements/${card.id}?page=${numberPage-1}&size=3"
                                                            class="page-link">${numberPage}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a
                                                            href="statements/${card.id}?page=${numberPage-1}&size=3"
                                                            class="page-link">${numberPage}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${currentPage < (paidPaymentsPages-1) }">
                                                <li class="page-item"><a href="statements/${card.id}?page=${currentPage+1}&size=3"><span
                                                        class="page-link"><spring:message code="next"/></span></a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item disabled"><span class="page-link"><spring:message
                                                        code="next"/></span></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </c:if>
                            </nav>

                        </div>
                    </div>
                </div>


            </div>


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