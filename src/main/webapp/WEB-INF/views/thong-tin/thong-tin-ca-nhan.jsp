<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Thông tin cá nhân</title>
</head>
<body>
<div style="width: 100%;height: auto;color: black">

    <form:form action="/login/update-thong-tin/${us.id}" method="post" modelAttribute="us"
               enctype="multipart/form-data">
        <form class="form-sample">
            <div>
                <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#personal-information"
                           role="tab"
                           aria-controls="description" aria-selected="true">Thông tin cá nhân</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/doi-mat-khau" role="tab">Đổi mật
                            khẩu</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" style="width: 80%">
                <div class="tab-pane fade show active" id="personal-information">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div style="display: none">
                                    <form:input path="ma"/>
                                    <form:input path="taiKhoan"/>
                                    <form:input path="matKhau"/>
                                    <form:input path="tinhTrang"/>
                                    <form:input path="ngayTao"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <form:label path="hoTen"
                                                    class="col-sm-1 col-form-label">Họ tên:</form:label>
                                        <div class="col-sm-5">
                                            <form:input class="form-control" id="form-control" placeholder=""
                                                        path="hoTen" disabled="true"/>
                                            <form:errors path="hoTen" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <form:label path="email"
                                                    class="col-sm-1 col-form-label">Email:</form:label>
                                        <div class="col-sm-5">
                                            <form:input class="form-control" id="form-control" placeholder=""
                                                        path="email" disabled="true"/>
                                            <form:errors path="email"
                                                         cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <form:label path="sdt" class="col-sm-1 col-form-label">SĐT:</form:label>
                                        <div class="col-sm-5">
                                            <form:input class="form-control" placeholder="" path="sdt" disabled="true"/>
                                            <form:errors path="sdt" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <form:label path="ngaySinh" class="col-sm-1 col-form-label">Ngày sinh:
                                        </form:label>
                                        <div class="col-sm-5">
                                            <form:input class="form-control" type="date"
                                                        placeholder=""
                                                        path="ngaySinh" disabled="true"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <form:label path="CCCD"
                                                    class="col-sm-1 col-form-label">CCCD:</form:label>
                                        <div class="col-sm-5">
                                            <form:input class="form-control" placeholder="" path="CCCD" disabled="true"/>
                                            <form:errors path="CCCD" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <form:label path="gioiTinh"
                                                    class="col-sm-1 col-form-label">Giới tính:</form:label>
                                        <div class="col-sm-5">
                                                <form:radiobutton path="gioiTinh" value="true"/>Nam
                                                <form:radiobutton path="gioiTinh" value="false"/> Nữ
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12">
                                    <div class="form-group row">
                                        <form:label path="diaChi"
                                                    class="col-sm-1 col-form-label">Quê quán:</form:label>
                                        <div class="col-sm-5">
                                            <form:input class="form-control" placeholder="" path="diaChi" disabled="true"/>
                                            <form:errors path="diaChi" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="text-align: center">
<%--                                <button type="submit" class="btn btn-primary mr-2"--%>
<%--                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">--%>
<%--                                    Lưu thông tin--%>
<%--                                </button>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </form:form>
</div>
</div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script>
    const imageInput1 = document.getElementById('anhmoi1');

    const previewAnh12 = document.getElementById('preview-anh1-2');

    imageInput1.addEventListener('change', function () {
        const file = imageInput1.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh12.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh12.src = '';
        }
        document.getElementById('cucheck1').value = 'moi1';
    });
</script>
</html>