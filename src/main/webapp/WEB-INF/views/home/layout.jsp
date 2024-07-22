<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>King Shop</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
    <!-- Favicon icon -->
<%--    <link rel="icon" type="image/png" sizes="16x16" href="../../../images/favicon.png">--%>
    <!-- Pignose Calender -->
    <link href="../../../plugins/pg-calendar/css/pignose.calendar.min.css" rel="stylesheet">
    <!-- Chartist -->
    <link rel="stylesheet" href="../../../plugins/chartist/css/chartist.min.css">
    <link rel="stylesheet" href="../../../plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
    <!-- Custom Stylesheet -->
    <link href="../../../css/style.css" rel="stylesheet">
    <link href="../../../plugins/tables/css/datatable/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>

<body>

<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10"/>
        </svg>
    </div>
</div>
<!--*******************
    Preloader end
********************-->


<!--**********************************
    Main wrapper start
***********************************-->
<div id="main-wrapper">

    <!--**********************************
        Nav header start
    ***********************************-->
    <div class="nav-header">
        <div class="brand-logo">
            <a href="/hoa-don/hien-thi">
                <b class="logo-abbr"><img style="height: 30px;" src="../../../images/logoKg.png" alt=""> </b>
                <span class="logo-compact"><img src="../../../images/logo-compact.png" alt=""></span>
                <span class="brand-title" style="color:white;font-family:verdana">
<%--                        <img src="../../../images/logo-text.png" width="45" height="45" alt="">--%>
                    <img src="../../../images/logochinh.jpg" width="45" height="45" alt="">
<%--                            <img src="../../../images/Win.png" width="60" height="60" alt="">--%>
                 KING SHOP
                    </span>
            </a>
        </div>
    </div>
    <!--**********************************
        Nav header end
    ***********************************-->

    <!--**********************************
        Header start
    ***********************************-->
    <div class="header">
        <div class="header-content clearfix">

            <div class="nav-control">
                <div class="hamburger">
                    <span class="toggle-icon"><i class="icon-menu"></i></span>
                </div>
            </div>
            <div class="header-right">
                <ul class="clearfix">
                    <li class="icons dropdown">
                        <div class="user-img c-pointer position-relative" data-toggle="dropdown">
                            <span class="activity active"></span>
                            <img src="../../../images/user/1.png" height="40" width="40" alt="">
                        </div>
                        <div class="drop-down dropdown-profile animated fadeIn dropdown-menu">
                            <div class="dropdown-content-body">
                                <ul>
                                    <li>
                                        <a href="/thong-tin-ca-nhan"><i class="icon-user"></i> <span>Thông tin cá nhân</span></a>
                                    </li>
                                    <li>
                                        <a href="/doi-mat-khau"><i class="icon-key"></i> <span>Đổi mật khẩu</span></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void()">
                                            <i class="icon-envelope-open"></i> <span>Inbox</span>
                                            <div class="badge gradient-3 badge-pill gradient-1">1</div>
                                        </a>
                                    </li>

                                    <hr class="my-2">
<%--                                                                        <li>--%>
<%--                                                                            <a href="page-lock.html"><i class="icon-lock"></i> <span>Lock Screen</span></a>--%>
<%--                                                                        </li>--%>
                                    <li><a href="/logout"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                    ><i class="icon-key"></i> <span>Đăng xuất</span></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--**********************************
        Header end ti-comment-alt
    ***********************************-->

    <!--**********************************
        Sidebar start
    ***********************************-->
    <div class="nk-sidebar">
        <div class="nk-nav-scroll">
            <ul class="metismenu" id="menu">
                <li>
                    <a href="/ban-hang/hien-thi" aria-expanded="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-bag" viewBox="0 0 16 16">
                            <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1m3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1z"/>
                        </svg>
                        <span class="nav-text">Bán hàng</span>
                    </a>
                </li>
                <li>
                    <a href="/hoa-don/hien-thi" aria-expanded="false">
                        <i class="icon-screen-tablet menu-icon"></i><span class="nav-text">Hoá đơn</span>
                    </a>
                </li>
                <li>
                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                        <i class="icon-grid menu-icon"></i><span class="nav-text">Quản lý sản phẩm</span>
                    </a>
                    <ul aria-expanded="false">
                        <li><a href="/chi-tiet-san-pham/hien-thi">Chi Tiết Sản Phẩm</a></li>
                        <li><a href="/san-pham/hien-thi">Sản Phẩm</a></li>
                        <li><a href="/chat-lieu/hien-thi">Chất Liệu</a></li>
<%--                        <li><a href="/co-ao/hien-thi">Cổ Áo</a></li>--%>
                        <li><a href="/kich-co/hien-thi">Kích Cỡ</a></li>
                        <li><a href="/mau-sac/hien-thi">Màu Sắc</a></li>
                        <li><a href="/thuong-hieu/hien-thi">Thương Hiệu</a></li>
                    </ul>
                </li>
                <li>
                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                        <i class="icon-grid menu-icon"></i><span class="nav-text">Quản lý tài khoản</span>
                    </a>
                    <ul aria-expanded="false">
                        <li><a href="/nhan-vien/hien-thi">Nhân Viên</a></li>
                        <li><a href="/khach-hang/hien-thi">Khách Hàng</a></li>
                        <li><a href="/chuc-vu/hien-thi">Chức Vụ</a></li>
                    </ul>
                </li>
                <a href="/phieu-giam-gia/hien-thi" aria-expanded="false">
                    <i class="icon-grid menu-icon"></i><span class="nav-text">  Phiếu Giảm Giá</span>
                </a>
                <a  href="/thong-ke/hien-thi" aria-expanded="false">
                    <i class="icon-grid menu-icon"></i><span class="nav-text">  Thống Kê</span>
                </a>

            </ul>
        </div>
    </div>

    <!--**********************************
        Sidebar end
    ***********************************-->

    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">
        <div class="container-fluid mt-3">
            <jsp:include page="${contentPage}"/>
        </div>
    </div>
    <!--**********************************
        Content body end
    ***********************************-->


    <!--**********************************
        Footer start
    ***********************************-->

    <!--**********************************
        Footer end
    ***********************************-->
</div>
<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->

<script src="../../../plugins/common/common.min.js"></script>
<script src="../../../js/custom.min.js"></script>
<script src="../../../js/settings.js"></script>
<script src="../../../js/gleek.js"></script>
<script src="../../../js/styleSwitcher.js"></script>

<!-- Chartjs -->
<script src="../../../plugins/chart.js/Chart.bundle.min.js"></script>
<!-- Circle progress -->
<script src="../../../plugins/circle-progress/circle-progress.min.js"></script>
<!-- Datamap -->
<script src="../../../plugins/d3v3/index.js"></script>
<script src="../../../plugins/topojson/topojson.min.js"></script>
<script src="../../../plugins/datamaps/datamaps.world.min.js"></script>
<!-- Morrisjs -->
<script src="../../../plugins/raphael/raphael.min.js"></script>
<script src="../../../plugins/morris/morris.min.js"></script>
<!-- Pignose Calender -->
<script src="../../../plugins/moment/moment.min.js"></script>
<script src="../../../plugins/pg-calendar/js/pignose.calendar.min.js"></script>
<!-- ChartistJS -->
<script src="../../../plugins/chartist/js/chartist.min.js"></script>
<script src="../../../plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>


<script src="../../../js/dashboard/dashboard-1.js"></script>
<script src="../../../plugins/tables/js/jquery.dataTables.min.js"></script>
<script src="../../../plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
<script src="../../../plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
</body>

</html>