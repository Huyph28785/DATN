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
                           aria-controls="description" aria-selected="true">Thông tin màu sắc</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/mau-sac/view-add" role="tab" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm
                            thông tin
                            màu sắc</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/mau-sac/hien-thi-delete" role="tab" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                            Màu sắc ngừng sử dụng</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title" style="float: left">Danh sách màu sắc</h4>
                                <%--            Tìm kiếm               --%>
                                <form action="/mau-sac/search-con-hoat-dong" method="post">
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
                                        <c:forEach items="${listMauSac}" var="mauSac" varStatus="index">
                                            <tr>
                                                <td>${index.index+1}</td>
                                                <td>${mauSac.ma}</td>
                                                <td>${mauSac.ten}</td>
                                                <td>
                                                    <c:if test="${mauSac.trangThai==0}">Còn sử dụng</c:if>
                                                    <c:if test="${mauSac.trangThai==1}">Ngừng sử dụng</c:if>
                                                </td>
                                                <td>${mauSac.ngayTao}</td>
                                                <td>
                                                    <a href="/mau-sac/detail/${mauSac.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Detail</a>
                                                    <a href="/mau-sac/view-update/${mauSac.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Update</a>
                                                    <a href="/mau-sac/delete/${mauSac.id}"
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
                                    <c:if test="${not listMauSac.isEmpty()}">
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination justify-content-center pagination-lg">
                                                <c:forEach begin="1" end="${totalPage}" varStatus="status">
                                                    <li class="page-item">
                                                        <a href="/mau-sac/hien-thi?pageNum=${status.index -1}"
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
<button style="display: none" type="button" id="batmodalthemmausac" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addCoAo">
    Open modal
</button>
<div class="modal" id="addCoAo">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/mau-sac/add" method="post" modelAttribute="mauSac">
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên màu sắc:</form:label>
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
<button style="display: none" type="button" id="batmodaldetailmausac" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#detailCoAo">
    Open modal
</button>
<div class="modal" id="detailCoAo">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form method="post" modelAttribute="mauSac">
                    <div class="form-group">
                        <form:label class="form-label" path="ma">Mã màu sắc:</form:label>
                        <form:input class="form-control" path="ma" readonly="true"/>
                        <form:errors path="ma" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên màu sắc:</form:label>
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
<button style="display: none" type="button" id="batmodalupdatemausac" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#updateCoAo">
    Open modal
</button>
<div class="modal" id="updateCoAo">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/mau-sac/update/${mauSac.id}" method="post" modelAttribute="mauSac">
                    <div class="form-group">
                        <form:label class="form-label" path="ma">Mã màu sắc:</form:label>
                        <form:input class="form-control" path="ma" readonly="true"/>
                        <form:errors path="ma" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên màu sắc:</form:label>
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
    <c:if test="${batmodalthemmausac==0}">
    document.getElementById('batmodalthemmausac').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodaldetailmausac==0}">
    document.getElementById('batmodaldetailmausac').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalupdatemausac==0}">
    document.getElementById('batmodalupdatemausac').click();
    </c:if>
</script>
</html>
