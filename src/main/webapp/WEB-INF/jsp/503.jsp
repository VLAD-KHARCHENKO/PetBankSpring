<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">

<head>
     <title>503</title>
    <c:import url="../templ/head-part.jsp"/>

   </head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <c:import url="../templ/header-part.jsp"/>
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <c:import url="../templ/topbar-part.jsp"/>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- 503 Error Text -->
                    <div class="text-center">
                        <div class="error mx-auto" data-text="503">503</div>
                        <p class="lead text-gray-800 mb-5">Page Not Found</p>
                        <p class="text-gray-500 mb-0">It looks like you found a glitch in the matrix...</p>
                        <a href="index">&larr; Back to Dashboard</a>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <c:import url="../templ/footer-part.jsp"/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <c:import url="../templ/logout-part.jsp"/>
    <c:import url="../templ/loader-part.jsp"/>

</body>

</html>