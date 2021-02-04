<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html lang="en">

<head>
    <title>Registration</title>
    <c:import url="head-part.jsp"/>

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                             <form:form class="user" action="registration" method="post" modelAttribute="registrationForm">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input  path="firstName" type="text" name="firstName" class="form-control form-control-user" id="exampleFirstName"
                                            placeholder="First Name"/>
                                        <form:errors path="firstName"/>
                                    </div>
                                    <div class="col-sm-6">
                                        <form:input  path="lastName" type="text" name="lastName" class="form-control form-control-user" id="exampleLastName"
                                            placeholder="Last Name"/>
                                        <form:errors path="lastName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:input  path="login" type="email" name="login" class="form-control form-control-user" id="exampleInputEmail"
                                        placeholder="Email Address"/>
                                    <form:errors path="login"/>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="password" type="password" name="password" class="form-control form-control-user"
                                            id="exampleInputPassword" placeholder="Password"/>
                                        <form:errors path="password"/>
                                    </div>
                                    <div class="col-sm-6">
                                        <form:input path="password_confirm" type="password" name="password" class="form-control form-control-user"
                                            id="exampleRepeatPassword" placeholder="Repeat Password"/>
                                        <form:errors path="password_confirm"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-user btn-block">Register Account</button>

                                <hr>

                        </form:form>


                            <hr>

                            <div class="text-center">
                                <a class="small" href="login">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <c:import url="loader-part.jsp"/>
</body>

</html>