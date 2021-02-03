<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">

<head>
    <title>Profile</title>
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
                <div class="container">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                                <div class="col-lg-7">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Change account!</h1>
                                        </div>
                                        <form:form class="user" action="profile" method="post">
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <label for="exampleFirstName" class="form-label-outside">
                                                        First Name
                                                    </label>
                                                    <form:input path="firstName" type="text" name="firstName"
                                                                value="${profileUser.firstName}"
                                                                class="form-control form-control-user"
                                                                id="exampleFirstName"
                                                                placeholder="First Name">
                                                        <form:errors path="firstName"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label for="exampleLastName" class="form-label-outside">
                                                        Last Name
                                                    </label>
                                                    <form:input path="lastName" type="text" name="lastName"
                                                                value="${profileUser.lastName}"
                                                                class="form-control form-control-user"
                                                                id="exampleLastName"
                                                                placeholder="Last Name">
                                                        <form:errors path="lastName"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="exampleInputEmail" class="form-label-outside">
                                                    Email
                                                </label>
                                                <form:input path="login" type="email" name="login"
                                                            value="${profileUser.login}"
                                                            class="form-control form-control-user"
                                                            id="exampleInputEmail"
                                                            placeholder="Email Address">
                                                    <form:errors path="login"/>
                                            </div>

                                            <c:choose>
                                                <c:when test="${profileUser.id != user.id}">

                                                    <label class="form-label-outside">
                                                        Condition
                                                    </label>
                                                    <div class="switch-wrap d-flex justify-content-between">
                                                        <div class="confirm-radio">

                                                            <c:choose>
                                                                <c:when test="${profileUser.active=='true'}">
                                                                    <form:input path="active" type="radio"
                                                                                id="confirm-radio"
                                                                                name="active" value="true" checked>
                                                                        <form:errors path="active"/>
                                                                        <label for="confirm-radio">Active</label>
                                                                        <form:input path="active" type="radio"
                                                                                    id="confirm-radio2"
                                                                                    name="active" value="false">
                                                                            <form:errors path="active"/>
                                                                            <label for="confirm-radio">Blocked</label>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <form:input path="active" type="radio" id="confirm-radio"
                                                                           name="active" value="true">

                                                                    <label for="confirm-radio">Active</label>
                                                                        <form:errors path="active"/>
                                                                        <form:input path="active" type="radio" id="confirm-radio2"
                                                                           name="active" value="false" checked>
                                                                    <label for="confirm-radio">Blocked</label>
                                                                            <form:errors path="active"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                    </div>


                                                    <div class="form-group">
                                                        <label for="role" class="form-label-outside">
                                                            Role
                                                        </label>
                                                        <select id="role" name="role" class="form-control">
                                                            <security:authorize access="hasRole('ROLE_ADMIN')">
                                                                <option value="ADMIN">ADMIN</option>
                                                                <option value="CUSTOMER">CUSTOMER</option>
                                                            </security:authorize>
                                                            <security:authorize access="hasRole('ROLE_CUSTOMER')">
                                                                <option value="CUSTOMER">CUSTOMER</option>
                                                                <option value="ADMIN">ADMIN</option>
                                                            </security:authorize>
                                                        </select>

                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <hr>
                                                </c:otherwise>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${profileUser.id == user.id}">

                                                    <div class="form-group row">
                                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                                            <label for="exampleInputPassword"
                                                                   class="form-label-outside">
                                                                Password
                                                            </label>
                                                            <form:input path="password" type="password" name="password"
                                                                        value="${profileUser.password}"
                                                                        class="form-control form-control-user"
                                                                        id="exampleInputPassword"
                                                                        placeholder="Password">
                                                                <form:errors path="password"/>
                                                        </div>

                                                        <div class="col-sm-6">
                                                            <label for="exampleRepeatPassword"
                                                                   class="form-label-outside">
                                                                Password
                                                            </label>
                                                            <form:input path="password_confirm" type="password"
                                                                        name="confirmPassword"
                                                                        value="${profileUser.password}"
                                                                        class="form-control form-control-user"
                                                                        id="exampleRepeatPassword"
                                                                        placeholder="Repeat Password">
                                                                <form:errors path="password_confirm"/>
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <hr>
                                                </c:otherwise>
                                            </c:choose>

                                            <input type="hidden" name="userId" value="${profileUser.id}"/>

                                            <button type="submit" class="btn btn-primary btn-user btn-block">Change
                                            </button>


                                        </form:form>


                                    </div>
                                </div>
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