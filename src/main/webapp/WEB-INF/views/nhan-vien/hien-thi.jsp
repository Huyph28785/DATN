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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
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
            <div>
<%--                <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"--%>
<%--                           aria-controls="description" aria-selected="true">Thông tin nhân viên</a>--%>
<%--                    </li>--%>
<%--                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="/nhan-vien/view-add" role="tab">--%>
<%--                            Thêm thông tin nhân viên--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="/nhan-vien/hien-thi-delete" role="tab">--%>
<%--                            Nhân viên ngừng hoạt động</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <a class="btn btn-outline-primary"  href="/nhan-vien/view-add"
                                        style="float: right;margin-bottom: 10px">
                                    + Thêm thông tin nhân viên
                                </a>
                                <h1 style="text-align: center">Danh sách nhân viên</h1>
                                <br>


                                <%--            Tìm kiếm               --%>
                                <form method="get" action="/nhan-vien/loc" style="color: black; float:left; ">
                                    <label for="chucVu">Chức vụ:</label>
                                    <select id="chucVu" name="ten1">
                                        <c:forEach items="${listChucVu}" var="chucVu">
                                            <option value="${chucVu.ten}">${chucVu.ten}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="gioiTinh">Giới tính:</label>
                                    <select id="gioiTinh" name="gioiTinh1">
                                        <%--                            <option value="">Tất cả</option>--%>
                                        <option value="true">Nam</option>
                                        <option value="false">Nữ</option>
                                        <!-- Thêm các option cho giới tính khác -->
                                    </select>
                                    <label for="trangThai">Trạng Thái:</label>
                                    <select id="trangThai" name="trangThai1">
                                        <option value=0>Còn hoạt động</option>
                                        <option value=1>Ngưng hoạt động</option>
                                    </select>
                                    <button type="submit" class="btn btn-primary">Lọc</button>
                                </form>

                                <a href="/nhan-vien/hien-thi" class=" btn btn-secondary" style="margin-left: 10px">
                                    <i class="bi bi-arrow-clockwise"></i></i>
                                </a>
                                <form action="/nhan-vien/search-con-hoat-dong" method="post">
                                    <div class="input-group" style="width: 30%; float: right">
                                        <input type="text" class="form-control" placeholder="Nhập mã hoặc tên..."
                                               aria-label="Bạn tìm gì..." name="search">
                                        <div class="input-group-append">
                                            <button class="btn btn-sm btn-primary" type="submit">Search</button>
                                        </div>
                                    </div>
                                </form>
                                <%--           kết thúc tìm kiếm         --%>
                                <div class="table-responsive">
                                    <table class="table table-striped" style="color: black; width: 1300px">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã</th>
                                            <th>Tên</th>
                                            <th>Số Điện Thoại</th>
                                            <th>Email</th>
                                            <th>Ngày Sinh</th>
                                            <th>Giới Tính</th>
                                            <th>Chức Vụ</th>
<%--                                            <th>Trạng Thái</th>--%>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <br>
                                        <br>
                                        <c:forEach items="${listNhanVien}" var="nhanVien" varStatus="index">
                                            <tr>
                                                <td>${index.index+1}</td>
                                                <td>${nhanVien.ma}</td>
                                                <td>${nhanVien.hoTen}</td>
                                                <td>${nhanVien.sdt}</td>
                                                <td>${nhanVien.email}</td>
                                                <td>${nhanVien.ngaySinh}</td>

                                                <td>
                                                    <c:if test="${nhanVien.gioiTinh==true}">Nam</c:if>
                                                    <c:if test="${nhanVien.gioiTinh==false}">Nữ</c:if>
                                                </td>
                                                <td>${nhanVien.chucVu.ten}</td>
<%--                                                <td>--%>
<%--                                                    <c:if test="${nhanVien.tinhTrang==0}">Còn hoạt động</c:if>--%>
<%--                                                    <c:if test="${nhanVien.tinhTrang==1}">Ngừng hoạt động</c:if>--%>
<%--                                                </td>--%>
                                                <td>
                                                    <a href="/nhan-vien/detail/${nhanVien.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button">
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Detail</a>
                                                    <a href="/nhan-vien/view-update/${nhanVien.id}"
                                                       class="btn btn-primary btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                      >
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Update</a>
<%--                                                    <a href="/nhan-vien/delete/${nhanVien.id}"--%>
<%--                                                       class="btn btn-danger btn-icon-text"--%>
<%--                                                       tabindex="-1"--%>
<%--                                                       role="button"--%>
<%--                                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">--%>
<%--                                                        <i class="ti-reload btn-icon-prepend"></i>--%>
<%--                                                        Status</a>--%>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <c:if test="${not listNhanVien.isEmpty()}">
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination justify-content-center pagination-lg">
                                                <c:forEach begin="1" end="${totalPage}" varStatus="status">
                                                    <li class="page-item">
                                                        <a href="/nhan-vien/hien-thi?pageNum=${status.index-1}"
                                                           class="page-link">${status.index}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </nav>
                                    </c:if>
                                    <c:if test="${listNhanVien.isEmpty()}">
                                        <td colspan="8" class="text-center">Không có thông tin người dùng này!</td>
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
<button style="display: none" type="button" id="batmodalthemnv" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addChatLieu">
    Open modal
</button>
<div class="modal" id="addChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <h1 style="text-align: center">Thêm nhân viên</h1>
                <form:form action="/nhan-vien/add" method="post" modelAttribute="nhanVien">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <form:label class="form-label" path="hoTen">Họ tên:</form:label>
                                <form:input class="form-control" path="hoTen"/>
                                <form:errors path="hoTen" cssStyle="color: red"></form:errors>

                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="diaChi">Địa chỉ:</form:label>
                                <form:input class="form-control" path="diaChi"/>
                                <form:errors path="diaChi" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="sdt">SĐT:</form:label>
                                <form:input class="form-control" path="sdt"/>
                                <form:errors path="sdt" cssStyle="color: red"></form:errors>
                                <span style="color: red">${tbtrungsdt}</span>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="CCCD">CCCD:</form:label>
                                <form:input class="form-control" path="CCCD"/>
                                <form:errors path="CCCD" cssStyle="color: red"></form:errors>
                                <span style="color: red">${tbtrungcccd}</span>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group">
                                <form:label class="form-label" path="gioiTinh">Giới tính:</form:label>
                                <div >
                                    <form:radiobutton path="gioiTinh" value="true" checked="true"/>Nam
                                    <form:radiobutton path="gioiTinh" value="false"
                                                      cssStyle="margin-left: 1cm"/>
                                    Nữ
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="email">Email:</form:label>
                                <form:input class="form-control" path="email"/>
                                <form:errors path="email" cssStyle="color: red"></form:errors>
                                <span style="color: red">${tbtrungemail}</span>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="ngaySinh">Ngày sinh:</form:label>
                                <form:input class="form-control" path="ngaySinh" type="date" required="true"/>
                                <form:errors path="ngaySinh" cssStyle="color: red"></form:errors>
                                <span style="color: red">${tbt14tuoi}</span>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">

                                        <form:label class="form-label" path="chucVu">Chức vụ:</form:label>
                                        <form:select path="chucVu" class="form-select" id="selectChucVu" type="text"
                                                     cssStyle="font-weight: bold; width: 100%;">
                                            <option selected disabled>Chức vụ</option>
                                            <form:options items="${listChucVu}" itemValue="id" itemLabel="ten"/>
                                        </form:select>
                                        <form:errors path="chucVu" cssStyle="color: red"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12">
                        <div style="text-align: center">
                            <button type="submit" class="btn btn-primary mr-2"
                                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                Thêm thông tin

                            </button>
                        </div>
                    </div>
                </form:form>
                <button class="btn btn-secondary"
                        data-bs-toggle="modal"
                        data-bs-target="#QRScan"
                        style="float: right; height: 45px;color: black">Scan QR</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="QRScan" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black;text-align: center">
                <div class="container">
                    <h1>Scan QR Codes</h1>
                    <div class="section">
                        <div id="my-qr-reader">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>
</div>
<button style="display: none" type="button" id="batmodaldetailnhanvien" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#detailChatLieu">
    Open modal
</button>
<div class="modal" id="detailChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form method="post" modelAttribute="nhanVien">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <form:label class="form-label" path="hoTen">Họ tên:</form:label>
                                <form:input class="form-control" path="hoTen" readonly="true"/>
                                <form:errors path="hoTen" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="diaChi">Địa chỉ:</form:label>
                                <form:input class="form-control" path="diaChi" readonly="true"/>
                                <form:errors path="diaChi" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="sdt">SĐT:</form:label>
                                <form:input class="form-control" path="sdt" readonly="true"/>
                                <form:errors path="sdt" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="CCCD">CCCD:</form:label>
                                <form:input class="form-control" path="CCCD" readonly="true"/>
                                <form:errors path="CCCD" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="ngaySinh">Ngày sinh:</form:label>
                                <form:input class="form-control" path="ngaySinh" type="date" required="true"
                                            readonly="true"/>
                                <form:errors path="ngaySinh" cssStyle="color: red"></form:errors>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group">
                                <form:label class="form-label" path="gioiTinh">Giới tính:</form:label>
                                <div readonly="true">
                                    <form:radiobutton path="gioiTinh" value="true" checked="true"/> Nam
                                    <form:radiobutton path="gioiTinh" value="false"
                                                      cssStyle="margin-left: 1cm"/>
                                    Nữ
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="email">Email:</form:label>
                                <form:input class="form-control" path="email" readonly="true"/>
                                <form:errors path="email" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="chucVu">Chức vụ:</form:label>
                                <form:select path="chucVu" class="form-control" id="selectChucVu"
                                             cssStyle="font-weight: bold; width: 100%">
                                    <option selected disabled>Chức vụ</option>
                                    <form:options items="${listChucVu}" itemValue="id" itemLabel="ten"/>
                                </form:select>
                                <form:errors path="chucVu"/>
                            </div>

<%--                                <div class="form-group">--%>
<%--                                    <form:label class="form-label" path="tinhTrang">Trạng Thái:</form:label>--%>
<%--                                    <div>--%>
<%--                                        <form:radiobuttons items="${dsTrangThai}" path="tinhTrang" class="radioButton" cssStyle="margin-left: 20px;margin-right: 8px"/>--%>
<%--                                    </div>--%>
<%--                                </div>--%>

                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>
</div>

</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
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
    <c:if test="${batmodalthemnhanvien==0}">
    document.getElementById('batmodalthemnhanvien').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodaldetailnhanvien==0}">
    document.getElementById('batmodaldetailnhanvien').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalupdatenhanvien==0}">
    document.getElementById('batmodalupdatenhanvien').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalthemnv==0}">
    document.getElementById('batmodalthemnv').click();
    </c:if>
</script>
<script type="text/javascript" src="https://unpkg.com/html5-qrcode"></script>
<script src="../../js/scan-qr-4.js"></script>
</html>
