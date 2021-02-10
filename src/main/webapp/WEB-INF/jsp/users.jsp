<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">

<head>
    <title>Users</title>
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

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">All users</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th>Email</th>
                                    <th>isActive</th>
                                    <th>Role</th>
                                    <th>Change</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td><a href="cards?id=${user.id}" class="search_link">${user.firstName}</a></td>
                                        <td>${user.lastName}</td>
                                        <td>${user.login}</td>
                                        <td>${user.condition}</td>
                                        <td>${user.role}</td>
                                        <td>
                                            <a href="profile?id=${user.id}" class="search_link">-></a>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>

                        <!-- Pagination-->
                        <nav aria-label="Page navigation example">
                            <c:if test="${usersPages > 1}">
                                <ul class="pagination">
                                    <c:choose>
                                        <c:when test="${currentPage  != 0 }">
                                            <li class="page-item"><a href="users?page=${currentPage-1}&size=3"><span class="page-link">Prev</span></a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item disabled"><span class="page-link">Prev</span></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var = "numberPage" begin = "1" end = "${usersPages}">
                                        <c:choose>
                                            <c:when test="${currentPage == (numberPage-1) }">
                                                <li class="page-item active"><a href="users?page=${numberPage-1}&size=3" class="page-link">${numberPage}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a href="users?page=${numberPage-1}&size=3" class="page-link">${numberPage}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${currentPage < (usersPages-1) }">
                                            <li class="page-item"><a href="users?page=${currentPage+1}&size=3"><span class="page-link">Next</span></a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item disabled"><span class="page-link">Next</span></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </c:if>
                        </nav>

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

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<c:import url="logout-part.jsp"/>
<c:import url="loader-part.jsp"/>

</body>

</html>