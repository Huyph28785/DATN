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
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <div>
                <ul class="nav nav-pills mb-3" id="setting-panel" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link" href="/dia-chi/hien-thi" role="tab">Thêm
                            thông tin
                            địa chỉ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                           aria-controls="description" aria-selected="true">Thêm thông tin địa chỉ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/dia-chi/hien-thi-delete" role="tab" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Địa chỉ
                            đã ngừng</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <form:form action="/dia-chi/add" method="post" modelAttribute="diaChi">
                        <div class="form-group">
                            <form:label class="form-label" path="soDiaChi">Địa Chỉ:</form:label>
                            <form:input class="form-control" path="soDiaChi"/>
                            <form:errors path="soDiaChi" cssStyle="color: red"></form:errors>
                        </div>
                        <div class="form-group">
                            <form:label class="form-label" path="thanhPho">Thành phố:</form:label>
                            <form:input class="form-control" path="thanhPho"/>
                            <form:errors path="thanhPho" cssStyle="color: red"></form:errors>
                        </div>
                        <div class="form-group">
                            <form:label class="form-label" path="quan">Quận:</form:label>
                            <form:input class="form-control" path="quan"/>
                            <form:errors path="quan" cssStyle="color: red"></form:errors>
                        </div>
                        <div class="form-group">
                            <form:label class="form-label" path="phuong">Phường:</form:label>
                            <form:input class="form-control" path="phuong"/>
                            <form:errors path="phuong" cssStyle="color: red"></form:errors>
                        </div>
                        <div class="form-group">
                            <form:label class="form-label" path="khachHang">Khách hàng:</form:label>
                            <form:select path="khachHang" class="form-control"
                                         cssStyle="font-weight: bold; width: 100%">
                                <option selected disabled>Khách hàng</option>
                                <form:options items="${listKhachHang}" itemValue="id" itemLabel="hoTen"/>
                            </form:select>
                            <form:errors path="khachHang"/>
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
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
