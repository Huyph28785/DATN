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
    <link href="css/style.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <!-- Favicon icon -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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
            <ul class="nav nav-pills mb-3" id="setting-panel" role="tablist">
                <li class="nav-item">
                    <a class="nav-link" href="/khach-hang/hien-thi" role="tab" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin
                        khách hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/khach-hang/view-add" role="tab" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm thông
                        tin khách hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                       aria-controls="description" aria-selected="true">Khách hàng đã ngừng</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách khách hàng</h4>
                    <%--            Tìm kiếm               --%>
<%--                    <a href="/khach-hang/hien-thi" class=" btn btn-secondary" style="margin-left: 10px">--%>
<%--                        <i class="bi bi-arrow-clockwise"></i></i>--%>
<%--                    </a>--%>
                    <form action="/khach-hang/search-ngung-hoat-dong" method="post">
                        <div class="input-group" style="width: 30%; float: right">
                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                   aria-label="Bạn tìm gì..." name="search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="submit">Search</button>
                            </div>
                        </div>
                    </form>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table class="table table-striped" style="color: black; width: 1500px;">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã</th>
                                <th>Tên</th>
                                <th>Ngày sinh</th>
                                <th>Giới tính</th>
                                <th>Email</th>
                                <th>SĐT</th>
                                <th>Tài khoản</th>
                                <th>Tình Trạng</th>
<%--                                <th>Ngày Tạo</th>--%>
<%--                                <th>Ngày Sửa</th>--%>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <br>
                            <br>
                            <c:forEach items="${listKhachHang}" var="khachHang" varStatus="index">
                                <tr>
                                    <td>${index.index+1}</td>
                                    <td>${khachHang.ma}</td>
                                    <td>${khachHang.hoTen}</td>
                                    <td>${khachHang.ngaySinh}</td>
                                    <td>
                                        <c:if test="${khachHang.gioiTinh == true}">Nam</c:if>
                                        <c:if test="${khachHang.gioiTinh == false}">Nữ</c:if>
                                    </td>
                                    <td>${khachHang.email}</td>
                                    <td>${khachHang.soDienThoai}</td>
                                    <td>${khachHang.taiKhoan}</td>
                                    <td>
                                        <c:if test="${khachHang.trangThai==0}">Đang hoạt động</c:if>
                                        <c:if test="${khachHang.trangThai==1}">Ngừng hoạt động</c:if>
                                    </td>
<%--                                    <td>${khachHang.ngayTao}</td>--%>
<%--                                    <td>${khachHang.ngaySua}</td>--%>
                                    <td>
                                        <a href="/khach-hang/detail/${khachHang.id}"
                                           class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Detail</a>
                                        <a href="/khach-hang/view-update/${khachHang.id}"
                                           class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Update</a>
                                        <a href="/khach-hang/delete/${khachHang.id}"
                                           class="btn btn-danger btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-reload btn-icon-prepend"></i>
                                            Status</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${not listKhachHang.isEmpty()}">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center pagination-lg">
                                    <li class="page-item"><a class="page-link"
                                                             href="/khach-hang/hien-thi?pageNum=0"><<</a>
                                    </li>
                                    <c:forEach begin="1" end="${totalPage}" varStatus="status">
                                        <li class="page-item">
                                            <a href="/khach-hang/hien-thi?pageNum=${status.index -1}"
                                               class="page-link">${status.index}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item"><a class="page-link"
                                                             href="/khach-hang/hien-thi?pageNum=${total-1}">>></a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>
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
<script>
    <c:if test="${batmodalthemchatlieu==0}">
    document.getElementById('batmodalthemchatlieu').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodaldetailchatlieu==0}">
    document.getElementById('batmodaldetailchatlieu').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalupdatechatlieu==0}">
    document.getElementById('batmodalupdatechatlieu').click();
    </c:if>
</script>
</html>
