<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
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
                                    <th>Edit</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${profileUser}" var="user">
                                    <tr>
                                        <td>${user.Id}</td>
                                        <td>${user.FirstName}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.email}</td>
                                        <td>${user.active}</td>
                                        <td>${user.role}</td>
                                        <td>
                                            <a href="profile?id=${user.id}" class="search_link">Edit</a>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
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

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<c:import url="logout-part.jsp"/>
<c:import url="loader-part.jsp"/>

</body>

</html>