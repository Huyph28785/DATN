<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="css/style.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <!-- Favicon icon -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
    <title>Document</title>
</head>
<body>

<c:if test="${thongBao != null}">
    <div id="modalError" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="swal2-icon swal2-error swal2-animate-error-icon" style="display: block;">
                                    <span class="swal2-x-mark swal2-animate-x-mark"><span
                                            class="swal2-x-mark-line-left"></span><span
                                            class="swal2-x-mark-line-right"></span></span></div>
                            <h4 style="color: red;margin: 10px;text-align: center">${thongBao}</h4>
                        </div>
                        <div class="col-12" style="text-align: center;margin-top: 20px">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${thongBaoThanhCong != null}">
    <div id="modalSuccess" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="swal2-icon swal2-success swal2-animate-success-icon"
                                 style="display: block;">
                                <div class="swal2-success-circular-line-left"
                                     style="background: rgb(255, 255, 255);"></div>
                                <span class="swal2-success-line-tip swal2-animate-success-line-tip"></span> <span
                                    class="swal2-success-line-long swal2-animate-success-line-long"></span>
                                <div class="swal2-success-ring"></div>
                                <div class="swal2-success-fix" style="background: rgb(255, 255, 255);"></div>
                                <div class="swal2-success-circular-line-right"
                                     style="background: rgb(255, 255, 255);"></div>
                            </div>
                            <h4 style="color: #10ae05;margin: 10px;text-align: center">${thongBaoThanhCong}</h4>
                        </div>
                        <div class="col-12" style="text-align: center;margin-top: 20px">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <h1 style="text-align: center">Quản Lý Hóa Đơn</h1>
            <ul  class="nav nav-pills mb-3" id="setting-panel" role="tablist">
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/hien-thi" role="tab"
                       >Tất cả</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/cho-xac-nhan" role="tab"
                       >Chờ xác nhận</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/da-xac-nhan" role="tab"
                      >Đã xác nhận</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/cho-giao-hang" role="tab"
                       >Hoàn thành</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/dang-van-chuyen" role="tab"
                      >Đang vận chuyển</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/huy" role="tab"
                      >Huỷ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" id="description-tab" data-toggle="tab" href="/hoa-don/hoan-thanh"
                       role="tab" aria-controls="description" aria-selected="false">Hoàn thành</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="row align-items-center">
                        <div class=" col-xl-12">
                            <div class="card">
                                <div class="card-body">
                                    <form:form action="/hoa-don/loc-hoan-thanh" method="post" modelAttribute="hoaDon">
                                    <div class="row" style="margin-top: 10px">
                                        <div class="col-md-3">
                                            <div class="form-group row">
                                                <div class="col-sm-12">
                                                    <select id="selectKhachHang1" name="khachHang"
                                                            class="form-control select2"
                                                            style="font-weight: bold; width: 100%">
                                                        <option selected disabled>Khách hàng</option>
                                                        <c:forEach items="${listKhachHang}" var="khachHang">
                                                            <option value="${khachHang.id}">${khachHang.hoTen}</option>
                                                        </c:forEach>
                                                    </select>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group row">
                                                <div class="col-sm-12">
                                                    <select id="selectNhanVien1" name="nhanVien"
                                                            class="form-control select2"
                                                            style="font-weight: bold; width: 100%">
                                                        <option selected disabled>Nhân viên</option>
                                                        <c:forEach items="${listNhanVien}" var="nhanVien">
                                                            <option value="${nhanVien.id}">${nhanVien.hoTen}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group row">
                                                <div class="col-sm-12">
                                                    <select name="loaiHoaDon" class="form-control select2"
                                                            style="font-weight: bold; width: 100%" id="selectLoai1">
                                                        <option selected disabled>Loại hóa đơn</option>
                                                        <option value="0">Tại quầy</option>
                                                        <option value="1">Online</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group row">
                                                <div class="col-sm-12">
                                                    <select name="ttHoaDon" class="form-control select2"
                                                            style="font-weight: bold; width: 100%" id="selectHoaDn">
                                                        <option selected disabled>Trạng thái hóa đơn</option>
                                                        <option value="0">Chờ Xác Nhận</option>
                                                        <option value="1">Xác Nhận</option>
                                                        <option value="2">Chờ Giao Hàng</option>
                                                        <option value="3">Đang Giao Hàng</option>
                                                        <option value="4">Thanh Toán</option>
                                                        <option value="5">Thành Công</option>
                                                        <option value="8">Đã Hủy</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-4 col-form-label">Từ ngày tạo:</label>
                                                <div class="col-sm-8">
                                                    <input type="date" id="ngayTao" name="startDate"
                                                           class="form-control"
                                                           placeholder="Từ ngày">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-5 col-form-label">Từ ngày sửa:</label>
                                                <div class="col-sm-7">
                                                    <input type="date" id="ngayNhan" name="startSua"
                                                           class="form-control"
                                                           placeholder="Từ ngày">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-4 col-form-label">Đến ngày tạo:</label>
                                                <div class="col-sm-8">
                                                    <input type="date" id="ngayTao1" name="endDate"
                                                           class="form-control"
                                                           placeholder="Đến ngày">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-5 col-form-label">Đến ngày sửa:</label>
                                                <div class="col-sm-7">
                                                    <input type="date" id="ngayNhan1" name="endSua"
                                                           class="form-control"
                                                           placeholder="Đến ngày">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div align="center">
                                    <BUTTON type="submit" class="btn btn-warning" style="" id="bt">
                                        Lọc hóa đơn
                                    </BUTTON>
                                </div>
                                </form:form>
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered zero-configuration">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã</th>
                                                <th>Tên người nhận</th>
                                                <th>Tổng tiền</th>
                                                <th>Ngày tạo</th>
                                                <th>Loại hoá đơn</th>
                                                <th>Trạng thái</th>
                                                <th>Hành động</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${listHoaDonHoanThanh}" var="HT" varStatus="i">
                                                <tr>
                                                    <td>${i.index+1}</td>
                                                    <td>${HT.ma}</td>
                                                    <td>${HT.tenNguoiNhan}</td>
                                                    <td>${HT.tongTien}</td>
                                                    <td>${HT.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${HT.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${HT.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${HT.trangThaiGiaoHang == 6}">Hoàn thành</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1  btn-outline-primary">
                                                            <a href="/hoa-don/detail/${HT.id}">Xem</a></button>
                                                        <a href="/hoa-don/xuat-pdf-hoan-tat/${HT.id}"
                                                           id="toastr-success-top-right-hoa-don-hoan-tat"
                                                           class="btn btn-outline-success btn-icon-text"
                                                           tabindex="-1"
                                                           role="button"
                                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                            <i class="ti-file btn-icon-prepend"></i>
                                                            Xuất PDF</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#selectHoaDn').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectGiaoHang').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectKhachHang1').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectLoai1').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectNhanVien1').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalError').modal('show');
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalSuccess').modal('show');
    });
</script>

</html>