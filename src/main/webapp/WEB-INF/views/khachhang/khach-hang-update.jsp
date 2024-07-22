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
<div class="col-md-10">
    <div class="card">
        <div class="card-body">
            <div>
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <h1 style="text-align: center">Sửa khách hàng</h1>
                    <form:form action="/khach-hang/update/${khachHang.id}" method="post" modelAttribute="khachHang">
                        <div class="form-group">
                            <form:label class="form-label" path="hoTen">Họ và tên:</form:label>
                            <form:input class="form-control" path="hoTen" placeholder=""/>
                            <form:errors path="hoTen" cssStyle="color: red"></form:errors>
                        </div>
                        <div class="form-group">
                            <div>
                                <form:label class="form-label" path="gioiTinh">Giới tính:</form:label>
                                <form:radiobutton path="gioiTinh" value="true" checked="true"/> Nam
                                <form:radiobutton path="gioiTinh" value="false" cssStyle="margin-left: 1cm"/> Nữ
                                <form:errors path="gioiTinh" cssStyle="color: red"></form:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="form-label" path="ngaySinh">Ngày sinh:</form:label>
                            <form:input class="form-control" path="ngaySinh" type="date" required="true"/>
                            <form:errors path="ngaySinh" cssStyle="color: red"></form:errors>
                            <span style="color: red">${tbt14tuoi}</span>
                        </div>
                        <div class="form-group">
                            <form:label class="form-label" path="soDienThoai">Số điện thoại:</form:label>
                            <form:input class="form-control" path="soDienThoai"/>
                            <form:errors path="soDienThoai" cssStyle="color: red"></form:errors>
                            <span style="color: red">${tbtrungsdt}</span>
                        </div>
                        <div class="form-group">
                            <form:label class="form-label" path="email">Email:</form:label>
                            <form:input class="form-control" path="email"/>
                            <form:errors path="email" cssStyle="color: red"></form:errors>
                            <span style="color: red">${tbtrungemail}</span>
                        </div>
                        <div class="form-group">
                            <form:label class="form-label" path="trangThai">Trạng Thái:</form:label>
                            <div>
                                <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="radioButton" cssStyle="margin-left: 20px;margin-right: 8px"/>
                            </div>
                        </div>
                        <div class="col-12">
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    Cập nhật thông tin
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
