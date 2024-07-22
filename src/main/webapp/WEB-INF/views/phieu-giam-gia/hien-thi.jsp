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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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
                           aria-controls="description" aria-selected="true">Thông tin phiếu giảm giá</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/phieu-giam-gia/view-add" role="tab">Thêm
                            thông tin
                            phiếu giảm giá</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">

                            <div class="card-body">
                                <div>
                                    <form:form action="/phieu-giam-gia/loc" method="post" modelAttribute="phieuGiamGia">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group row">
                                                    <label class="col-sm-4 col-form-label">Từ ngày tạo:</label>
                                                    <div class="col-sm-8">
                                                        <input type="date" id="ngayTao" name="startDate" class="form-control"
                                                               placeholder="Từ ngày">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="form-group row">
                                                    <label class="col-sm-5 col-form-label">Đến ngày tạo:</label>
                                                    <div class="col-sm-7">
                                                        <input type="date" id="ngayNhan" name="endDate"
                                                               class="form-control"
                                                               placeholder="Đến ngày">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="form-group row">
                                                    <div class="col-sm-12">
                                                        <select name="trangThaiPhieu" class="form-control select2"
                                                                style="font-weight: bold; width: 100%" id="selectPhieu">
                                                            <option selected disabled>Trạng thái phiếu</option>
                                                            <option value="0">Còn hạn sử dụng</option>
                                                            <option value="1">Hết hạn sử dụng</option>
                                                            <option value="2">Hết phiếu</option>
                                                            <option value="3">Chưa khả dụng</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12" align="center">
                                                <BUTTON type="submit" class="btn btn-warning" style="" id="bt">
                                                    Lọc phiếu
                                                </BUTTON>
                                                <a href="/phieu-giam-gia/hien-thi" class=" btn btn-secondary" style="margin-left: 10px">
                                                    <i class="bi bi-arrow-clockwise"></i></i>
                                                </a>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                                <%--                                <h4 class="card-title" style="float: left">Danh sách phiếu giảm giá</h4>--%>
                                <%--                                &lt;%&ndash;            Tìm kiếm               &ndash;%&gt;--%>
                                <%--                                <form action="/phieu-giam-gia/search-con-hoat-dong" method="post">--%>
                                <%--                                    <div class="input-group" style="width: 30%; float: right">--%>
                                <%--                                        <input type="text" class="form-control" placeholder="Bạn tìm gì..."--%>
                                <%--                                               aria-label="Bạn tìm gì..." name="search">--%>
                                <%--                                        <div class="input-group-append">--%>
                                <%--                                            <button class="btn btn-sm btn-primary" type="submit">Search</button>--%>
                                <%--                                        </div>--%>
                                <%--                                    </div>--%>
                                <%--                                </form>--%>
                                <%--           kết thúc tìm kiếm         --%>
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered zero-configuration"
                                           style="color: black;width: 1600px">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã</th>
                                            <th>Tên</th>
                                            <th>Hình thức giảm giá</th>
                                            <th>Giá trị giảm</th>
                                            <th>Hóa đơn tối thiểu</th>
                                            <th>Số tiền giảm tối đa</th>
                                            <th>Ngày Tạo</th>
<%--                                            <th>Ngày Sửa</th>--%>
                                            <th>Ngày bắt đầu</th>
                                            <th>Ngày kết thúc</th>
                                            <th>Số lượng</th>
                                            <th>Tình Trạng</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <br>
                                        <br>
                                        <c:forEach items="${listPhieuGiamGia}" var="phieuGiamGia" varStatus="i">
                                            <tr>
                                                <td>${i.index+1}</td>
                                                <td>${phieuGiamGia.ma}</td>
                                                <td>${phieuGiamGia.ten}</td>
                                                <td><c:if
                                                        test="${phieuGiamGia.hinhThucGiam== true}"> Giảm theo %</c:if></td>
                                                <td>${phieuGiamGia.tienGiam}%</td>
                                                <td>
                                                    <script>
                                                        var donGia = ${phieuGiamGia.giamToiThieu};
                                                        document.write(donGia.toLocaleString('vi-VN'));
                                                    </script>
                                                    VND
                                                </td>
                                                <td>
                                                    <script>
                                                        var donGia1 = ${phieuGiamGia.giamToiDa};
                                                        document.write(donGia1.toLocaleString('vi-VN'));
                                                    </script>
                                                    VND
                                                </td>
                                                <td>${phieuGiamGia.ngayTao}</td>
<%--                                                <td>${phieuGiamGia.ngaySua}</td>--%>
                                                <td>${phieuGiamGia.ngayBatDau}</td>
                                                <td>${phieuGiamGia.ngayketThuc}</td>
                                                <td>${phieuGiamGia.soLuong}</td>
                                                <td>
                                                    <c:if test="${phieuGiamGia.trangThai==0}">Còn hạn sử dụng</c:if>
                                                    <c:if test="${phieuGiamGia.trangThai==1}">Hết hạn sử dụng</c:if>
                                                    <c:if test="${phieuGiamGia.trangThai==2}">Hết phiếu giảm giá</c:if>
                                                    <c:if test="${phieuGiamGia.trangThai==3}">Phiểu giảm giá chưa khả dụng</c:if>
                                                </td>
                                                <td>
                                                    <a href="/phieu-giam-gia/detail/${phieuGiamGia.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                    >
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Detail</a>
                                                    <a href="/phieu-giam-gia/view-update/${phieuGiamGia.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                       >
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Update</a>
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
<button style="display: none" type="button" id="batmodalthemphieu" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addChatLieu">
    Open modal
</button>
<div class="modal" id="addChatLieu">
    <div class="modal-di                                                                                                                                                                                                                                                     alog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/phieu-giam-gia/add" method="post" modelAttribute="phieuGiamGia">
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên phiếu giảm giá:</form:label>
                        <form:input class="form-control" path="ten"/>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="tienGiam">% giảm( tối đa 20% ):</form:label>
                        <form:input class="form-control" path="tienGiam" type="number"/>
                        <form:errors path="tienGiam" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="giamToiThieu">Hóa đơn tối thiểu:</form:label>
                        <form:input class="form-control" path="giamToiThieu" type="number"/>
                        <form:errors path="giamToiThieu" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="giamToiDa">Giảm tối đa:</form:label>
                        <form:input class="form-control" path="giamToiDa" type="number"/>
                        <form:errors path="giamToiDa" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayBatDau">Ngày bắt đầu:</form:label>
                        <form:input class="form-control" path="ngayBatDau" type="datetime-local" required="true"/>
                        <form:errors path="ngayBatDau" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayketThuc">Ngày kết thúc:</form:label>
                        <form:input class="form-control" path="ngayketThuc" type="datetime-local" required="true"/>
                        <form:errors path="ngayketThuc" cssStyle="color: red"></form:errors>
                        <label style="color: red">${thongBaoPhieu}</label>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="soLuong">Số lượng:</form:label>
                        <form:input class="form-control" path="soLuong"/>
                        <form:errors path="soLuong" cssStyle="color: red"></form:errors>
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
<button style=" display: none" type="button" id="batmodaldetailphieu" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#detailChatLieu">
    Open modal
</button>
<div class="modal" id="detailChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form method="post" modelAttribute="phieuGiamGia">
                    <div class="form-group">
                        <form:label class="form-label" path="ma">Mã phiếu giảm giá:</form:label>
                        <form:input class="form-control" path="ma" readonly="true"/>
                        <form:errors path="ma" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên phiếu giảm giá:</form:label>
                        <form:input class="form-control" path="ten" readonly="true"/>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="tienGiam">% giảm( tối đa 20% ):</form:label>
                        <form:input class="form-control" path="tienGiam" type="number" readonly="true"/>
                        <form:errors path="tienGiam" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="giamToiThieu">Hóa đơn tối thiểu:</form:label>
                        <form:input class="form-control" path="giamToiThieu" type="number"/>
                        <form:errors path="giamToiThieu" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="giamToiDa">Giảm tối đa:</form:label>
                        <form:input class="form-control" path="giamToiDa" type="number"/>
                        <form:errors path="giamToiDa" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayBatDau">Ngày bắt đầu:</form:label>
                        <form:input class="form-control" path="ngayBatDau"  readonly="true"/>
                        <form:errors path="ngayBatDau" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayketThuc">Ngày kết thúc:</form:label>
                        <form:input class="form-control" path="ngayketThuc" readonly="true"/>
                        <form:errors path="ngayketThuc" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="soLuong">Số lượng:</form:label>
                        <form:input class="form-control" path="soLuong" readonly="true"/>
                        <form:errors path="soLuong" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayTao">Ngày Tạo:</form:label>
                        <form:input class="form-control" path="ngayTao" type="date" readonly="true"/>
                        <form:errors path="ngayTao" cssStyle="color: red"></form:errors>
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
<button style="display: none" type="button" id="batmodalupdatephieu" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#updateChatLieu">
    Open modal
</button>
<div class="modal" id="updateChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/phieu-giam-gia/update/${phieuGiamGia.id}" method="post"
                           modelAttribute="phieuGiamGia">
                    <div style="display: none">
                        <form:input class="form-control" path="ngayBatDau" type="text" id="input2"/>
                        <form:input class="form-control" path="ngayketThuc" type="text" id="input4"/>
                        <form:input class="form-control" path="trangThai"/>
                        <form:input class="form-control" path="nguoiTao"/>
                        <form:input class="form-control" path="ngayTao"/>
                        <form:input class="form-control" path="hinhThucGiam"/>
                        <form:input class="form-control" path="ma"/>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên phiếu giảm giá:</form:label>
                        <form:input class="form-control" path="ten"/>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="tienGiam">% giảm( tối đa 20% ):</form:label>
                        <form:input class="form-control" path="tienGiam" type="number"/>
                        <form:errors path="tienGiam" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="giamToiThieu">Hóa đơn tối thiểu:</form:label>
                        <form:input class="form-control" path="giamToiThieu" type="number"/>
                        <form:errors path="giamToiThieu" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="giamToiDa">Giảm tối đa:</form:label>
                        <form:input class="form-control" path="giamToiDa" type="number"/>
                        <form:errors path="giamToiDa" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayBatDau">Ngày bắt đầu:</form:label>
                        <input value="${NBTCC}" name="NBDupdate" type="datetime-local" style="width: 100%" id="input1"
                               oninput="updateInput2(this.value)" class="form-control">
                        <form:errors path="ngayBatDau" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="ngayketThuc">Ngày kết thúc:</form:label>
                        <input value="${NKTCC}" name="NKTupdate" type="datetime-local" style="width: 100%" id="input3"
                               oninput="updateInput4(this.value)" class="form-control">
                        <form:errors path="ngayketThuc" cssStyle="color: red"></form:errors>
                        <label style="color: red">${thongBaoPhieu}</label>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="soLuong">Số lượng:</form:label>
                        <form:input class="form-control" path="soLuong"/>
                        <form:errors path="soLuong" cssStyle="color: red"></form:errors>
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
    <c:if test="${batmodalthemphieu==0}">
    document.getElementById('batmodalthemphieu').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodaldetailphieu==0}">
    document.getElementById('batmodaldetailphieu').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalupdatephieu==0}">
    document.getElementById('batmodalupdatephieu').click();
    </c:if>
</script>
<script>
    // Hàm chuyển đổi định dạng thời gian
    function convertDateFormat(inputDateTime1) {


        // Chuỗi thời gian đầu vào
        var inputDateTime = inputDateTime1.replace("T", " ");


        // Chuyển đổi chuỗi thành đối tượng Date
        var parsedDate = new Date(inputDateTime);


        // Kiểm tra nếu parsedDate không hợp lệ
        if (isNaN(parsedDate.getTime())) {
            return "";
        }

        // Lấy thông tin ngày, tháng, năm, giờ, phút và giây
        var day = parsedDate.getDate();
        var month = parsedDate.getMonth() + 1; // Tháng trong JavaScript bắt đầu từ 0
        var year = parsedDate.getFullYear();
        var hours = parsedDate.getHours();
        var minutes = parsedDate.getMinutes();
        var seconds = parsedDate.getSeconds();

        // Định dạng lại chuỗi theo định dạng mong muốn
        var formattedMonth = (month < 10 ? '0' : '') + month;

        var formattedDay = (day < 10 ? '0' : '') + day;
        var formattedHours = (hours < 10 ? '0' : '') + hours;
        var formattedMinutes = (minutes < 10 ? '0' : '') + minutes;
        var formattedSeconds = (seconds < 10 ? '0' : '') + seconds;

        var outputDateTime = formattedDay + "-" + formattedMonth + "-" + year + " " + formattedHours + ":" + formattedMinutes + ":" + formattedSeconds;


        return outputDateTime;
    }


    // Hàm được gọi khi giá trị của input3 thay đổi
    function updateInput4(value) {


        document.getElementById('input4').value = convertDateFormat(value);
        ;
    }

    // Hàm được gọi khi giá trị của input1 thay đổi
    function updateInput2(value) {
        // Lấy thẻ input thứ hai và cập nhật giá trị của nó
        document.getElementById('input2').value = convertDateFormat(value);
    }
</script>
</html>
