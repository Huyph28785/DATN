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
                <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                           aria-controls="description" aria-selected="true">Thông tin thương hiệu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/thuong-hieu/view-add" role="tab" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm
                            thông tin
                            thương hiệu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/thuong-hieu/hien-thi-delete" role="tab" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thương hiệu ngừng sử dụng</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title" style="float: left">Danh sách thương hiệu</h4>
                                <%--            Tìm kiếm               --%>
                                <form action="/thuong-hieu/search-con-hoat-dong" method="post">
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
                                    <table class="table table-striped" style="color: black">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã</th>
                                            <th>Tên</th>
                                            <th>Tình Trạng</th>
                                            <th>Ngày Tạo</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <br>
                                        <br>
                                        <c:forEach items="${listThuongHieu}" var="thuongHieu" varStatus="index">
                                            <tr>
                                                <td>${index.index+1}</td>
                                                <td>${thuongHieu.ma}</td>
                                                <td>${thuongHieu.ten}</td>
                                                <td>
                                                    <c:if test="${thuongHieu.trangThai==0}">Còn sử dụng</c:if>
                                                    <c:if test="${thuongHieu.trangThai==1}">Ngừng sử dụng</c:if>
                                                </td>
                                                <td>${thuongHieu.ngayTao}</td>
                                                <td>
                                                    <a href="/thuong-hieu/detail/${thuongHieu.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Detail</a>
                                                    <a href="/thuong-hieu/view-update/${thuongHieu.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Update</a>
                                                    <a href="/thuong-hieu/delete/${thuongHieu.id}"
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
                                    <c:if test="${not listThuongHieu.isEmpty()}">
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination justify-content-center pagination-lg">
                                                <c:forEach begin="1" end="${totalPage}" varStatus="status">
                                                    <li class="page-item">
                                                        <a href="/thuong-hieu/hien-thi?pageNum=${status.index -1}"
                                                           class="page-link">${status.index}</a>
                                                    </li>
                                                </c:forEach>

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
<button style="display: none" type="button" id="batmodalthemthuonghieu" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addChatLieu">
    Open modal
</button>
<div class="modal" id="addChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/thuong-hieu/add" method="post" modelAttribute="thuongHieu">
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên thương hiệu:</form:label>
                        <form:input class="form-control" path="ten"/>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2"
                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                            Thêm thông tin
                        </button>
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
<button style="display: none" type="button" id="batmodaldetailthuonghieu" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#detailChatLieu">
    Open modal
</button>
<div class="modal" id="detailChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form method="post" modelAttribute="thuongHieu">
                    <div class="form-group">
                        <form:label class="form-label" path="ma">Mã thương hiệu:</form:label>
                        <form:input class="form-control" path="ma" readonly="true"/>
                        <form:errors path="ma" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên thương hiệu:</form:label>
                        <form:input class="form-control" path="ten" readonly="true"/>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayTao">Ngày Tạo:</form:label>
                        <form:input class="form-control" path="ngayTao" type="date" readonly="true"/>
                        <form:errors path="ngayTao" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngaySua">Ngày Sửa:</form:label>
                        <form:input class="form-control" path="ngaySua" type="date" readonly="true"/>
                        <form:errors path="ngaySua" cssStyle="color: red"></form:errors>
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
<button style="display: none" type="button" id="batmodalupdatethuonghieu" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#updateChatLieu">
    Open modal
</button>
<div class="modal" id="updateChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/thuong-hieu/update/${thuongHieu.id}" method="post" modelAttribute="thuongHieu">
                    <div class="form-group">
                        <form:label class="form-label" path="ma">Mã thương hiệu:</form:label>
                        <form:input class="form-control" path="ma" readonly="true"/>
                        <form:errors path="ma" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên thương hiệu:</form:label>
                        <form:input class="form-control" path="ten" />
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayTao">Ngày Tạo:</form:label>
                        <form:input class="form-control" path="ngayTao" type="date" readonly="true"/>
                        <form:errors path="ngayTao" cssStyle="color: red"></form:errors>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2"
                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                            Cập nhật thông tin
                        </button>
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
    <c:if test="${batmodalthemthuonghieu==0}">
    document.getElementById('batmodalthemthuonghieu').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodaldetailthuonghieu==0}">
    document.getElementById('batmodaldetailthuonghieu').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalupdatethuonghieu==0}">
    document.getElementById('batmodalupdatethuonghieu').click();
    </c:if>
</script>
</html>
