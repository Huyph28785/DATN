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
</head>
<body>
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <div>
            </div>
            <h1 style="text-align: center">Sửa thông tin Nhân Vien</h1>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <form:form action="/nhan-vien/update/${nhanVien.id}" method="post" modelAttribute="nhanVien">
                    <div class="row">
                        <div style="display: none">
                            <form:input class="form-control" path="taiKhoan"/>
                            <form:input class="form-control" path="matKhau"/>
                        </div>
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
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group">
                                <form:label class="form-label" path="gioiTinh">Giới tính:</form:label>
                                <div >
                                    <form:radiobutton path="gioiTinh" value="true" checked="true"/> Nam
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
                            <div class="form-group">
                                <form:label class="form-label" path="chucVu">Chức vụ:</form:label>
                                <form:select path="chucVu" class="form-control" id="selectChucVu"
                                             cssStyle="font-weight: bold; width: 100%">
                                    <option selected disabled>Chức vụ</option>
                                    <form:options items="${listChucVu}" itemValue="id" itemLabel="ten"/>
                                </form:select>
                                <form:errors path="chucVu"/>
                            </div>
                                <div class="form-group">
                                    <form:label class="form-label" path="tinhTrang">Trạng Thái:</form:label>
                                    <div>
                                        <form:radiobuttons items="${dsTrangThai}" path="tinhTrang" class="radioButton" cssStyle="margin-left: 20px;margin-right: 8px"/>
                                    </div>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#selectChucVu').select2();
    });
</script>
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
</html>
