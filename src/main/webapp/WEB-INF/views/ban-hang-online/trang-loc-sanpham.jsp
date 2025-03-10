<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <%--phan trang--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Hóa đơn</title>
    <%--căn đều--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/>


    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/slick.css"/>
    <link type="text/css" rel="stylesheet" href="/cssbanhang/slick-theme.css"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/nouislider.min.css"/>

    <!-- Font Awesome Icon -->

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/style.css"/>

    <%--    select 2--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
    <%--loc theo giá--%>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.1/nouislider.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.1/nouislider.min.js"></script>
    <style>
        /* CSS cho modal */
        #myModal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 1000; /* Đặt giá trị z-index lớn */
        }

        .modal-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
        }

        /*div{*/
        /*    border: 1px solid red;*/
        /*}*/
        .input-with-button {
            display: flex; /* Sử dụng flexbox để căn chỉnh nút bên trong input */
            border: 1px solid #ccc; /* Tạo đường viền xung quanh hộp tìm kiếm */
            border-radius: 25px; /* Đặt bán kính tròn cho hộp tìm kiếm */
            overflow: hidden; /* Loại bỏ nút nếu nó bị tràn ra ngoài hộp */
        }

        .input-with-button input {
            flex: 1; /* Làm cho input mở rộng để lấp đầy hộp */
            border: none; /* Loại bỏ đường viền của input */
            padding: 10px; /* Đặt khoảng cách nội dung bên trong input */
            outline: none; /* Loại bỏ đường viền khi focus vào input */
        }

        .input-with-button button {
            background: #007bff; /* Màu nền của nút */
            color: #fff; /* Màu chữ trắng */
            border: none; /* Loại bỏ đường viền của nút */
            padding: 10px 20px; /* Đặt khoảng cách nội dung bên trong nút */
            cursor: pointer; /* Biến con trỏ thành bàn tay khi trỏ vào nút */
        }

        .carousel-prev {
            background-color: white;
            color: black;
            font-size: 16px;
            border: none;
            transition: background-color 0.3s, color 0.3s;
        }

        .carousel-prev:hover {
            background-color: #0056b3; /* Màu nền khi di chuột qua */
            color: #fff; /* Màu chữ khi di chuột qua */
        }

        .carousel-next {
            background-color: white; /* Màu nền nút */
            color: black; /* Màu chữ trên nút */
            font-size: 16px; /* Kích cỡ chữ */
            border: none; /* Loại bỏ viền nút */
            transition: background-color 0.3s, color 0.3s;
        }

        .carousel-next:hover {
            background-color: #0056b3; /* Màu nền khi di chuột qua */
            color: #fff; /* Màu chữ khi di chuột qua */
        }

        .form-control {
            background-color: #f5f5f5; /* Màu nền combobox */
            color: #333; /* Màu chữ */
            border: 1px solid #ccc; /* Viền */
            border-radius: 5px; /* Góc bo tròn */
            width: 200px; /* Chiều rộng */
            height: 40px;
        }

        .form-control:hover {
            background-color: #007bff; /* Màu nền khi di chuột vào */
            border-color: #007bff; /* Màu viền khi di chuột vào */
            color: #fff; /* Màu chữ khi di chuột vào */
        }
        .cart-dropdown {
            border-radius: 10px;
            width: 180px;
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            padding: 10px;
            margin-top: 10px;
        }

        .cart-dropdown a {
            display: block;
            width: 100%;
            padding: 10px;
            text-decoration: none;
            text-align: center;
            color: #fff;
            background-color: #007bff;
            margin-bottom: 10px;
        }

        .cart-dropdown a:hover {
            background-color: #0056b3;
        }

        header {
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 100;
        }

        .one {
            margin-top: 170px; /* Chiều cao của header */
            padding: 20px;
        }
    </style>

</head>
<body style="background-color: #ffffff">


<!-- HEADER -->
<header>
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-envelope-o"></i> gwindzy@gmail.com</a></li>
            </ul>
            <ul class="header-links pull-right">
                <c:if test="${idkhachhang=='1'}">
                    <li><a href="/login"><i class="fa fa-user-o"></i> Chưa đăng nhập:<input id="tkmkidkhachhang"
                                                                                            type="text"
                                                                                            style="display: none"
                                                                                            value="${idkhachhang}"></a>
                    </li>

                </c:if>
                <c:if test="${idkhachhang !='1'}">
                    <!-- Cart -->
                    <li>
                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">
                                    <span>
                                    <i class="fa fa-user-o"></i>
                                            ${khachhangdangnhap.hoTen}
                                        <input id="tkmkidkhachhang" type="text" style="display: none"
                                               value="${idkhachhang}">
                                    </span>
                            </a>
                            <div class="cart-dropdown"
                                 style="border-radius: 10px;width: 3.5cm;margin-top: 10px;width: 180px">
                                <div>
                                    <div>
                                        <form action="/thong-tin-ca-nhan-khach-hang" method="post" style="display: none">
                                            <input value="${idkhachhang}" name="idKhachHang" style="display: none">
                                            <button style="" class="btn btn-primary" type="submit" id="taikhoancuatoi">Tài khoản của tôi</button>
                                        </form>
                                        <a  class="btn btn-primary" type="submit" onclick="anbt()">Tài khoản của tôi</a>

                                    </div>
                                    <div>
                                        <a href="/ban-hang-online/hoa-don-online/${idkhachhang}/full/xem"
                                           class="btn btn-primary">Đơn hàng</a>
                                    </div>
                                    <div>
                                        <a href="/logout" class="btn btn-primary" style="" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                        >Đăng xuất</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- /Cart -->
                </c:if>
            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <h2 class="logo" style="margin: 20px;color: black;font-family: 'Arial'">Windzy Store</h2>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form action="/ban-hang-online/trang-chu/tim-kiem" method="post">
                            <div class="input-with-button">

                                <input type="text" id="searchInput" name="trangchutimkiem" placeholder="Tìm kiếm sản phẩm...">
                                <%--                                    <button class="search-btn" type="submit">Search</button>--%>



                            </div>
                        </form>

                        <div  style="  position: absolute;
                        background-color: white;margin-top: 5px;
                        width: 95%; overflow: hidden;z-index: 11;
                        box-shadow: 0 1px 4px 0 rgba(0,0,0,.26);" id="searchResults" class="list-group">
                            <div id="saochep"></div>
                            <div id="danhsachloctimkiemTT" style="">

                            </div>
                        </div>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <!-- Wishlist -->
                        <%--                        <div>--%>
                        <%--                            <a href="#">--%>
                        <%--                                <i class="fa fa-heart-o"></i>--%>
                        <%--                                <span>Your Wishlist</span>--%>
                        <%--                                <div class="qty">2</div>--%>
                        <%--                            </a>--%>
                        <%--                        </div>--%>
                        <!-- /Wishlist -->

                        <!-- Cart -->


                        <div class="dropdown" id="giohangtrangchu">
                            <c:if test="${idkhachhang!='1'}">
                                <c:if test="${listghct.size()>0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">${banhangonline.ListghctTheoidgh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId()).size()}</div>
                                    </a>
                                    <div class="cart-dropdown" style="width:  13cm">
                                        <div class="cart-list">

                                            <c:forEach items="${listghct}" var="ht" varStatus="stt">
                                                <br><hr>
                                                <div style="border: 1px ;height: 2cm">
                                                    <div style="width: 80%;float: right">
                                                        <label style="font-weight: bold">Sản
                                                            phẩm:</label>${ht.chiTietSanPham.sanPham.ten}-
                                                            ${ht.chiTietSanPham.mauSac.ten}-${ht.chiTietSanPham.kichCo.ten}.

                                                        <br>
                                                        <label style="font-weight: bold">Số lượng:</label> ${ht.soLuong}<br>

                                                        <label style="font-weight: bold">Đơn
                                                            giá:</label>
                                                        <script>
                                                            var giaBan = ${ht.donGia};
                                                            document.write(giaBan.toLocaleString('vi-VN'));
                                                        </script>
                                                        VND

                                                    </div>
                                                    <div style="width: 18%;">
                                                        <input type="checkbox" name="checkidghTT" value="${ht.id}"
                                                               onclick="chonsanphamgiohangTT('${stt.index}','${ht.id}','${ht.gioHang.id}');"  ${ht.tinhTrang==0 ?"checked":""}>


                                                        <img src="../../../uploads/${ht.chiTietSanPham.hinhAnh.tenAnh}"
                                                             width="50" height="50"
                                                             style="border-radius:50% 50% 50% 50%;border: 1px solid black">
                                                    </div>

                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="cart-summary">
                                            <small> ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongsanphamchon()}
                                                Sản phẩm được chọn</small>
                                            <br><label>Tổng:</label><label id="tongtienghtt">${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}</label><label>đ</label>

                                        </div>
                                        <div class="cart-btns">
                                            <a href="/ban-hang-online/xem-gio-hang">Xem giỏ hàng</a>
                                            <a href="#">Chọn hết
                                                <input type="checkbox" name="checktongTT"
                                                       onclick="chonhetgiohangtongTRANGCHU('${listghct.get(0).gioHang.id}');"  ${tttong==0 ?"checked":""}>
                                            </a>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${listghct.size()<=0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">0</div>
                                    </a>
                                    <div class="cart-dropdown" style="width: 500px;">
                                        <div class="cart-summary">
                                            <small> 0 Sản phẩm được chọn</small>
                                            <h5>Tổng:0 đ</h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="/ban-hang-online/xem-gio-hang">Xem giỏ hàng</a>
                                            <a href="#">Chọn hết
                                            </a>
                                        </div>
                                    </div>
                                </c:if>
                            </c:if>
                            <c:if test="${idkhachhang=='1'}">

                                <a class="dropdown-toggle" aria-expanded="true" href="/login">
                                    <i class="fa fa-shopping-cart"></i>
                                    <span>Giỏ hàng</span>
                                    <div class="qty">0</div>
                                </a>


                            </c:if>

                        </div>

                        <!-- /Cart -->


                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
    <!-- NAVIGATION -->
    <nav id="navigation">
        <!-- container -->
        <div class="container">
            <!-- responsive-nav -->
            <div id="responsive-nav">
                <!-- NAV -->
                <%--                main-nav nav  navbar-nav--%>
                <ul class=" main-nav nav ">

                    <c:if test="${idkhachhang=='1'}">
                        <li><a href="/ban-hang-online/hien-thi">TRANG CHỦ</a></li>
                    </c:if>
                    <c:if test="${idkhachhang !='1'}">
                        <li><a href="/ban-hang-online/home">TRANG CHỦ</a></li>
                    </c:if>


                    <li><a href="/ban-hang-online/ao-phong">SẢN PHẨM</a></li>
                </ul>
                <!-- /NAV -->
            </div>
            <!-- /responsive-nav -->
        </div>
        <!-- /container -->
    </nav>
    <!-- /NAVIGATION -->
</header>
<!-- /HEADER -->




<%--code--%>
<br>
<div class="one">
<div class="container shadow p-3 mb-5 bg-body-tertiary rounded">
    <div>
        <div class="container px-0 px-lg-5 mt-0">
            <div class="row gx-0 gx-lg-5 row-cols-0 row-cols-md-0 row-cols-xl-5 justify-content-center"
                 style="width: 100%">
                <div style="height: 1.5cm">
                    <select class="form-control" id="hangds1" onchange="clickcombobox()">
                        <option selected value="null">Thương Hiệu</option>
                        <c:forEach items="${thds}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="camds1" onchange="clickcombobox()">
                        <option selected value="null">Kích Cỡ</option>
                        <c:forEach items="${kcds}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <select class="form-control" id="mands1" onchange="clickcombobox()">
                        <option selected value="null">Cổ Áo</option>
                        <c:forEach items="${cods}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div style="height: 1.5cm">
                    <select class="form-control" id="mauds1" onchange="clickcombobox()">
                        <option selected value="null">Màu Sắc</option>
                        <c:forEach items="${mauds}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="ramds1" onchange="clickcombobox()">
                        <option selected value="null">Chất Liệu</option>
                        <c:forEach items="${clds}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="sands1" onchange="clickcombobox()">
                        <option selected value="null">Sản Phẩm</option>
                        <c:forEach items="${sands}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>


           <div id="vt"></div>
            </div>
            <div style="width: 6cm;float: right">
                <label class="range-label">Khoảng tiền:</label>
                <div id="slider" class="slider"></div>
                <br>
                <label id="thongbaokhoang" style="color: red"></label>
                <div>
                    Từ: <input id="value1" value="0" style="width: 2cm">
                    đến <input id="value2" value="${max}" style="width: 2cm">
                </div>
                <div id="max" style="display: none">${max}</div>
            </div>
    </div>


    <%--loc kết thúc--%>
    <br><br>
    <div style="margin-left: -1%;margin-top: 2cm" id="dssanphamloc">
        <!-- Carousel -->
        <div id="demo11" class="carousel slide" data-bs-ride="false">
            <!-- Indicators/dots -->

            </button>

            <!-- The slideshow/carousel -->
            <div class="carousel-inner">
                <c:set var="vitri" scope="session" value="${-1}"/>
                <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
                <c:set var="salary" scope="session" value="${1}"/>
                <c:if test="${trang.index <2}">
                <div class="carousel-item active">
                    </c:if>
                    <c:if test="${trang.index >=2}">
                    <div class="carousel-item ">
                        </c:if>


                        <div class="container px-0 px-lg-4 mt-0">
                            <div class="row gx-0 gx-lg-0 row-cols-0 row-cols-md-0 row-cols-xl-4 justify-content-center"
                                 style="width: 100%">
                                <c:forEach items="${listsp}" var="ht" varStatus="stt">
                                    <c:if test="${banhangonline.soluongcon(ht.id)>0}">
                                        <c:if test="${stt.index > vitri }">
                                            <%--                            phân trang số 9 là 8 dữ liệu--%>
                                            <c:if test="${salary <9}">
                                                <!-- product -->

                                                <div class="product" style="margin-left: 1%;width: 24%;">
                                                        <a href="/ban-hang-online/chi-tiet-san-pham/${ht.id}">
                                                            <div class="product-img">
                                                                <img src="/uploads/${ht.hinhAnh.tenAnh}" style="width: 100%;height: 6cm"
                                                                     alt="">
                                                            </div>
                                                            <div class="product-body"
                                                                 style="text-align: left;word-wrap: break-word;">
                                                                <h3 class="product-name"><a>${ht.sanPham.ten}</a></h3>
                                                                <p >${ht.mauSac.ten} - ${ht.kichCo.ten}</p>
                                                                <h3 class="product-price">
                                                                    <script>
                                                                        var giaBan2 = ${ht.giaBan};
                                                                        document.write(giaBan2.toLocaleString('vi-VN'));
                                                                    </script>
                                                                    VND
                                                                </h3>
                                                            </div>
                                                        </a>
                                                    </div>
                                                <!-- /product -->
                                                <c:set var="vitri" scope="session" value="${stt.index}"/>
                                                <c:set var="salary" scope="session" value="${salary+1}"/>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <br><br><br><br>
                    </div>
                    </c:forEach>
                </div>
                <!-- Indicators/dots -->

                <div class="carousel text-center">
                    <button class="carousel-prev" type="button" data-bs-target="#demo11" data-bs-slide="prev">
                        <<
                    </button>
                    <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
                        <button class="carousel-prev" type="button" data-bs-target="#demo11"
                                data-bs-slide-to="${trang.index-1}">${trang.index}</button>
                    </c:forEach>
                    <button class="carousel-next" type="button" data-bs-target="#demo11" data-bs-slide="next">
                        >>
                    </button>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
<%--het code--%>

<!-- FOOTER -->
<footer id="footer">
    <!-- top footer -->
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">About Us</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt
                            ut.</p>
                        <ul class="footer-links">
                            <li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
                            <li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
                            <li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Categories</h3>
                        <ul class="footer-links">
                            <li><a href="#">Hot deals</a></li>
                            <li><a href="#">T-shirt</a></li>
                            <li><a href="#">Bannet</a></li>
                            <li><a href="#">Ei-raiden</a></li>
                            <li><a href="#">Accessories</a></li>
                        </ul>
                    </div>
                </div>

                <div class="clearfix visible-xs"></div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Information</h3>
                        <ul class="footer-links">
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">Contact Us</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Orders and Returns</a></li>
                            <li><a href="#">Terms & Conditions</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Service</h3>
                        <ul class="footer-links">
                            <li><a href="#">My Account</a></li>
                            <li><a href="#">View Cart</a></li>
                            <li><a href="#">Wishlist</a></li>
                            <li><a href="#">Track My Order</a></li>
                            <li><a href="#">Help</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /top footer -->

    <!-- bottom footer -->
    <div id="bottom-footer" class="section">
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-12 text-center">
                    <ul class="footer-payments">
                        <li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
                        <li><a href="#"><i class="fa fa-credit-card"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
                    </ul>
                    <span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i
                            class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com"
                                                                                target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /bottom footer -->
</footer>
<!-- /FOOTER -->

<div style="position: fixed;
top: 50%;left: 50%;transform: translate(-50%,-50%);
display: none;z-index: 2;width: 7cm;height: 3cm;
background-color: #0b3564;text-align: center;
color: white;border-radius: 5% 5% 5% 5%"
     id="thongbaothemgiohang">
    <img style="border-radius: 50% 50% 50% 50%;width: 1.2cm;height: 1.2cm;margin-top: 20px" src="data:image/png;base64,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">
    <h2 style="color: white;font-size: 20px;margin-top: 20px">Đã Thêm vào Giỏ hàng</h2>
</div>

<script>
    function clickcombobox() {
        var a1 = encodeURIComponent(document.getElementById("hangds1").value);
        var a2 = encodeURIComponent(document.getElementById("camds1").value);
        var a3 = encodeURIComponent(document.getElementById("mands1").value);
        var a4 = encodeURIComponent(document.getElementById("mauds1").value);
        var a5 = encodeURIComponent(document.getElementById("ramds1").value);
        var a6 = encodeURIComponent(document.getElementById("sands1").value);
        var a7 = encodeURIComponent(document.getElementById("value1").value);
        var a8 = encodeURIComponent(document.getElementById("value2").value);
        if(document.getElementById("value1").value.trim()==='' || document.getElementById("value2").value.trim()===''){
            document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";
        }else {
            if (parseFloat(a7) > parseFloat(a8)) {
                document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";
            } else {
                document.getElementById("thongbaokhoang").innerHTML = "";

                var link = '/ban-hang-online/loc-ban-hang/' + a5 + '/' + a4 + '/' + a1 + '/' + a2 + '/' + a3 + '/' + a6 + '/' + a7 + '/' + a8;
                loadbenloc(link);
            }
        }
    }
</script>
<script>
    function chonhetgiohangtongTRANGCHU(idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        if (document.getElementsByName('checktongTT')[0].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        }
    }

    function chonsanphamgiohangTT(vt, idctgh, idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        var vt1 = parseInt(vt);
        // var id1=encodeURIComponent(id);
        if (document.getElementsByName('checkidghTT')[vt1].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        }
    }

    function thongbaothemvaogiohang(idctsp) {
        //chạy 2 lần mới ấn dc
        for (var stt = 0; stt < 2; stt = stt + 1) {
            loadgiaodienghctbanhangTrangChu('/ban-hang-online/them-san-pham-vao-gio-hang/' + idctsp);
        }

        // loadgiaodienghctbanhangTrangChu('/ban-hang-online/them-san-pham-vao-gio-hang/'+idctsp);
        // alert("123")
        document.getElementById('thongbaothemgiohang').style.display = '';
        setTimeout(function () {
            document.getElementById('thongbaothemgiohang').style.display = 'none';
        }, 2000); // 2000 milliseconds tương đương với 2 giây

    }


    function loadgiaodienghctbanhangTrangChu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giohangtrangchu');
                content.innerHTML = data;
                // thanhtienbenghct();
                formatAndDisplayValue("tongtienghtt");
                loadScripts();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });

        // document.getElementById('thanhlocctsp').style.display='none';

    }

    function loadbenloc(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('dssanphamloc');
                content.innerHTML = data;
                loadScripts();
            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
        // document.getElementById('thanhlocctsp').style.display='none';
    }

    function loadScripts() {
        const scriptsToLoad = [
            // 'https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js',


        ];
        const head = document.head || document.getElementsByTagName('head')[0];

        function loadScript(index) {
            if (index < scriptsToLoad.length) {
                const script = document.createElement('script');
                script.src = scriptsToLoad[index];
                script.onload = function () {
                    loadScript(index + 1);
                };
                head.appendChild(script);

            }
        }

        // Bắt đầu quá trình tải script
        loadScript(0);
    }

    loadSelect2();

    function loadSelect2() {
        // Gọi .select2() cho các phần tử sau khi tất cả các tệp script đã được nạp
        $('#hangds1').select2({
            theme: 'bootstrap-5'
        });
        $('#camds1').select2({
            theme: 'bootstrap-5'
        });
        $('#mands1').select2({
            theme: 'bootstrap-5'
        });

        $('#ramds1').select2({
            theme: 'bootstrap-5'
        });
        $('#romds1').select2({
            theme: 'bootstrap-5'
        });

        $('#pinds1').select2({
            theme: 'bootstrap-5'
        });
        $('#dungds1').select2({
            theme: 'bootstrap-5'
        });

        $('#chipds1').select2({
            theme: 'bootstrap-5'
        });

        $('#diachids1').select2({
            theme: 'bootstrap-5'
        });
        $('#sands1').select2({
            theme: 'bootstrap-5'
        });
        $('#mauds1').select2({
            theme: 'bootstrap-5'
        });
        $('#thds1').select2({
            theme: 'bootstrap-5'
        });
        $('#kcds1').select2({
            theme: 'bootstrap-5'
        });
        $('#cods1').select2({
            theme: 'bootstrap-5'
        });
        $('#clds1').select2({
            theme: 'bootstrap-5'
        });
        // Gọi .select2() cho các phần tử khác ở đây (tương tự)
    }
</script>
<script>
    var slider = document.getElementById('slider');
    var value1 = document.getElementById('value1');
    var value2 = document.getElementById('value2');
    var max = parseFloat(document.getElementById('max').innerHTML);
    var isSliding = false;

    noUiSlider.create(slider, {
        start: [0, max], // Giá trị mặc định cho hai chấm chòn
        connect: true,    // Kết nối giữa hai chấm chòn
        range: {
            'min': 0,
            'max': max
        }
    });


    value1.addEventListener('input', function () {

        this.value = this.value.replace(/[^0-9]/g, '');
        isSliding = true;
        slider.noUiSlider.set([parseInt(value1.value), null]);
        isSliding = false;
        if (
            this.value.trim() === '' || parseFloat(this.value) > parseFloat(value2.value)
        ) {

            document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

        } else {
            document.getElementById("thongbaokhoang").innerHTML = "";
            clickcombobox();
        }
    });

    value2.addEventListener('input', function () {
        this.value = this.value.replace(/[^0-9]/g, '');
        isSliding = true;
        slider.noUiSlider.set([null, parseInt(value2.value)]);
        isSliding = false;
        if (
            this.value.trim() === '' || parseFloat(this.value) < parseFloat(value1.value)
        ) {

            document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

        } else {
            document.getElementById("thongbaokhoang").innerHTML = " ";
            clickcombobox();
        }
    });


    slider.noUiSlider.on('update', function (values, handle) {
        if (!isSliding) {
            if (handle === 0) {
                value1.value = Math.round(values[0]); // Làm tròn giá trị xuống số nguyên
            } else {
                value2.value = Math.round(values[1]); // Làm tròn giá trị xuống số nguyên
            }
            clickcombobox();
        }
    });
</script>
<script>
    function anbt() {
        document.getElementById('taikhoancuatoi').click();
    }
    function formatAndDisplayValue(elementId) {
        // Lấy giá trị từ thẻ div
        var originalValue = document.getElementById(elementId).textContent;

        // Chuyển đổi giá trị sang dạng có dấu chấm phân cách hàng nghìn
        var formattedValue = Number(originalValue).toLocaleString('vi-VN');

        // Gán giá trị đã định dạng lại vào thẻ div
        document.getElementById(elementId).textContent = formattedValue;
    }
    formatAndDisplayValue("tongtienghtt");
</script>
<script>
    var searchResultsContainer = document.getElementById("searchResults");

    document.getElementById("searchInput").addEventListener("input", function() {
        var searchValue = this.value.trim().toLowerCase();
        showSearchResults(searchValue);
    });

    function showSearchResults(searchValue) {
        if (searchValue.length > 0) {
            // document.getElementById('saochep').innerHTML=   document.getElementById('searchInput').value
            loadgiaodientimkiemTrangchu("/ban-hang-online/trang-chu/tim-kiem-loc/"+document.getElementById('searchInput').value);
            searchResultsContainer.style.display = "";
        } else {
            // Nếu không có ký tự nào, ẩn kết quả
            searchResultsContainer.style.display = "none";
        }
    }


</script>
<script>
    function loadgiaodientimkiemTrangchu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('danhsachloctimkiemTT');
                content.innerHTML = data;

            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });



    }
</script>
<!-- jQuery Plugins -->
<script src="/jsbanhang/jquery.min.js"></script>
<script src="/jsbanhang/bootstrap.min.js"></script>
<script src="/jsbanhang/slick.min.js"></script>
<script src="/jsbanhang/nouislider.min.js"></script>
<script src="/jsbanhang/jquery.zoom.min.js"></script>
<script src="/jsbanhang/main.js"></script>
<%--    select 2--%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<%--loc khoang tien--%>

</body>

</html>




