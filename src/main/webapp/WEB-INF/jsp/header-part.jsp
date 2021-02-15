<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-paw"></i>
        </div>
        <div class="sidebar-brand-text mx-3">PETBANK</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="index">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span><spring:message code="dashboard"/></span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">




    <!-- Nav Item - Tables -->
    <security:authorize access="hasRole('ROLE_CUSTOMER')">

            <li class="nav-item">
                <a class="nav-link" href="cards/${user.id}">
                    <i class="fas fa-fw fa-table"></i>
                    <span><spring:message code="cards"/></span></a>

            </li>
        <!--
                    <li class="nav-item">
                        <a class="nav-link" href="statements">
                            <i class="fas fa-fw fa-table"></i>
                            <span>Statements</span></a>
                    </li>
                     -->
            <li class="nav-item">
                <a class="nav-link" href="payments/${user.id}">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span><spring:message code="payments"/></span></a>
            </li>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="users?page=0&size=3&sort=id">
                    <i class="fas fa-users"></i>
                    <span><spring:message code="users"/></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="pending-cards">
                    <i class="fas fa-money-bill-wave"></i>
                    <span><spring:message code="pending.cards"/></span></a>
            </li>
    </security:authorize>
    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

    <!-- Sidebar Message -->


</ul>
<!-- End of Sidebar -->
