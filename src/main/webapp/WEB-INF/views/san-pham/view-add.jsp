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
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" role="tabpanel"
                 aria-labelledby="description-tab">
                <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">

                    <li class="nav-item">
                        <a class="nav-link" href="/san-pham/view-ctsp/${sanPham.id}" role="tab" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông
                            tin
                            chi tiết sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                           aria-controls="description" aria-selected="true">Thêm thông tin chi tiết sản phẩm</a>
                    </li>
                </ul>
            </div>
            <br>
            <br>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <form:form action="/san-pham/chi-tiet-san-pham/add" method="post" modelAttribute="chiTiet"
                               enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-12" style="text-align: center">
                                <div class="form-group">
                                    <label class="form-label" style="font-weight: bold;color: #0b0b0b">Sản
                                        phẩm: ${sanPham.ten}</label>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="color: black">
                            <div class="col-6">
                                <div class="form-group">
                                    <form:label class="form-label" path="giaBan">Giá bán:</form:label>
                                    <form:input class="form-control" path="giaBan" type="number"/>
                                    <form:errors path="giaBan" cssStyle="color: red"></form:errors>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-group">
                                    <form:label class="form-label" path="soLuongTon">Số lượng tồn:</form:label>
                                    <form:input class="form-control" path="soLuongTon" type="number"/>
                                    <form:errors path="soLuongTon" cssStyle="color: red"></form:errors>
                                    <label style="color: red">${thongBaoSoLuong}</label>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="coAo" class="form-control" id="selectCoAo"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Cổ áo</option>
                                                <form:options items="${listCoAo}" itemValue="id" itemLabel="ten"/>
                                            </form:select>
                                            <span class="text-danger">${errorCoAo}</span>
                                            <form:errors path="coAo"/>
                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal"
                                               data-bs-target="#addCoAo">
                                                <img src="../../../images/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="thuongHieu" class="form-control" id="selectChucVu"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Thương hiệu</option>
                                                <form:options items="${listThuongHieu}" itemValue="id" itemLabel="ten"/>
                                            </form:select>
                                            <span class="text-danger">${errorThuongHieu}</span>
                                            <form:errors path="thuongHieu"/>
                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal"
                                               data-bs-target="#addThuongHieu">
                                                <img src="../../../images/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="chatLieu" class="form-control" id="selectChatLieu"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Chất liệu</option>
                                                <form:options items="${listChatLieu}" itemValue="id" itemLabel="ten"/>
                                            </form:select>
                                            <span class="text-danger">${errorChatLieu}</span>
                                            <form:errors path="chatLieu"/>
                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal"
                                               data-bs-target="#addChatLieu">
                                                <img src="../../../images/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="kichCo" class="form-control" id="selectKichCo"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Kích cỡ</option>
                                                <form:options items="${listKichCo}" itemValue="id" itemLabel="ten"/>
                                            </form:select>
                                            <span class="text-danger">${errorKichThuoc}</span>
                                            <form:errors path="kichCo"/>
                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal"
                                               data-bs-target="#addKichCo">
                                                <img src="../../../images/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-11">
                                            <form:select path="mauSac" class="form-control" id="selectMauSac"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Màu sắc</option>
                                                <form:options items="${listMauSac}" itemValue="id" itemLabel="ten"/>
                                            </form:select>
                                            <span class="text-danger">${errorMauSac}</span>
                                            <form:errors path="mauSac"/>
                                        </div>
                                        <div class="col-1">
                                            <a type="button" data-bs-toggle="modal"
                                               data-bs-target="#addMauSac">
                                                <img src="../../../images/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-3">
                                            <div align="center">
                                                <br>
                                                <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                       for="anhmoi1">
                                                    <img id="preview-anh1-2" class="preview-image" src="" alt=""
                                                         width="100%" height="100%"
                                                         style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                    <br><br>
                                                    ẢNH 1
                                                </label>
                                                <br>
                                                <div style="display: none">
                                                    <input type="file" name="anh1s" accept="image/jpeg, image/png"
                                                           id="anhmoi1">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <div align="center">
                                                <br>
                                                <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                       for="anhmoi2">
                                                    <img id="preview-anh2-2" class="preview-image" src="" alt=""
                                                         width="100%" height="100%"
                                                         style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                    <br><br>
                                                    ẢNH 2
                                                </label>
                                                <br>
                                                <div style="display: none">
                                                    <input type="file" name="anh2s" accept="image/jpeg, image/png"
                                                           id="anhmoi2">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <div align="center">
                                                <br>
                                                <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                       for="anhmoi3">
                                                    <img id="preview-anh3-2" class="preview-image" src="" alt=""
                                                         width="100%" height="100%"
                                                         style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                    <br><br>
                                                    ẢNH 3
                                                </label>
                                                <br>
                                                <div style="display: none">
                                                    <input type="file" name="anh3s" accept="image/jpeg, image/png"
                                                           id="anhmoi3">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <div align="center">
                                                <br>
                                                <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                       for="anhmoi4">
                                                    <img id="preview-anh4-2" class="preview-image" src="" alt=""
                                                         width="100%" height="100%"
                                                         style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                    <br><br>
                                                    ẢNH 4
                                                </label>
                                                <br>
                                                <div style="display: none">
                                                    <input type="file" name="anh4s" accept="image/jpeg, image/png"
                                                           id="anhmoi4">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-group">
                                    <div style="text-align: center">
                                        <button type="submit" class="btn btn-primary mr-2"
                                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            Thêm thông tin
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>

<button style="display: none" type="button" id="batmodalthemthuonghieu" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addThuongHieu">
    Open modal
</button>
<div class="modal" id="addThuongHieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/san-pham/thuong-hieu/add" method="post" modelAttribute="thuongHieu">
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên thương hiệu:</form:label>
                        <form:input class="form-control" path="ten" id="tenTH"/>
                        <label id="tenTH1" style="color: red"></label>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2" id="btTH" onclick="return checkTH()">
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

<button style="display: none" type="button" id="batmodalthemchatlieu" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addChatLieu">
    Open modal
</button>
<div class="modal" id="addChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/san-pham/chat-lieu/add" method="post" modelAttribute="chatLieu">
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên chất liệu:</form:label>
                        <form:input class="form-control" path="ten" id="tenCL"/>
                        <label id="tenCL1" style="color: red"></label>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2" id="btCL" onclick="return checkCL()">
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

<button style="display: none" type="button" id="batmodalthemcoao" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addCoAo">
    Open modal
</button>
<div class="modal" id="addCoAo">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/san-pham/co-ao/add" method="post" modelAttribute="coAo">
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên cổ áo:</form:label>
                        <form:input class="form-control" path="ten" id="tenCA"/>
                        <label id="tenCA1" style="color: red"></label>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2" id="btCA" onclick="return checkCA()">
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

<button style="display: none" type="button" id="batmodalthemkichco" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addKichCo">
    Open modal
</button>
<div class="modal" id="addKichCo">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/san-pham/kich-co/add" method="post" modelAttribute="kichCo">
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên kích cỡ:</form:label>
                        <form:input class="form-control" path="ten" id="tenKC"/>
                        <label id="tenKC1" style="color: red"></label>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2" id="btKC" onclick="return checkKC()">
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

<button style="display: none" type="button" id="batmodalthemmausac" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addMauSac">
    Open modal
</button>
<div class="modal" id="addMauSac">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/san-pham/mau-sac/add" method="post" modelAttribute="mauSac">
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên màu sắc:</form:label>
                        <form:input class="form-control" path="ten" id="tenMS"/>
                        <label id="tenMS1" style="color: red"></label>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2" id="btMS" onclick="return checkMS()">
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

</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
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
    });
    const imageInput2 = document.getElementById('anhmoi2');

    const previewAnh22 = document.getElementById('preview-anh2-2');

    imageInput2.addEventListener('change', function () {
        const file = imageInput2.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh22.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh22.src = '';
        }
    });
    const imageInput3 = document.getElementById('anhmoi3');

    const previewAnh32 = document.getElementById('preview-anh3-2');

    imageInput3.addEventListener('change', function () {
        const file = imageInput3.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh32.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh32.src = '';
        }
    });
    const imageInput4 = document.getElementById('anhmoi4');

    const previewAnh42 = document.getElementById('preview-anh4-2');

    imageInput4.addEventListener('change', function () {
        const file = imageInput4.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh42.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh42.src = '';
        }
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectChucVu').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectKichCo').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectCoAo').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectMauSac').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectChatLieu').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectKichCo').select2();
    });
</script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    function checkCL() {
        var tenCL = document.getElementById("tenCL").value;

        if (tenCL.trim() === '') {
            document.getElementById("tenCL1").innerHTML = "Không để trống thông tin";
            document.getElementById("btCL").type = "button";
            return false;
        } else {
            document.getElementById('tenCL1').innerHTML = '';
            document.getElementById('btCL').type = 'summit';
            return true;
        }
    }

    function checkCA() {
        var tenCA = document.getElementById("tenCA").value;

        if (tenCA.trim() === '') {
            document.getElementById("tenCA1").innerHTML = "Không để trống thông tin";
            document.getElementById("btCA").type = "button";
            return false;
        } else {
            document.getElementById('tenCA1').innerHTML = '';
            document.getElementById('btCA').type = 'summit';
            return true;
        }
    }

    function checkKC() {
        var tenKC = document.getElementById("tenKC").value;

        if (tenKC.trim() === '') {
            document.getElementById("tenKC1").innerHTML = "Không để trống thông tin";
            document.getElementById("btKC").type = "button";
            return false;
        } else {
            document.getElementById('tenKC1').innerHTML = '';
            document.getElementById('btKC').type = 'summit';
            return true;
        }
    }

    function checkMS() {
        var tenMS = document.getElementById("tenMS").value;

        if (tenMS.trim() === '') {
            document.getElementById("tenMS1").innerHTML = "Không để trống thông tin";
            document.getElementById("btMS").type = "button";
            return false;
        } else {
            document.getElementById('tenMS1').innerHTML = '';
            document.getElementById('btMS').type = 'summit';
            return true;
        }
    }

    function checkTH() {
        var tenTH = document.getElementById("tenTH").value;

        if (tenTH.trim() === '') {
            document.getElementById("tenTH1").innerHTML = "Không để trống thông tin";
            document.getElementById("btTH").type = "button";
            return false;
        } else {
            document.getElementById('tenTH1').innerHTML = '';
            document.getElementById('btTH').type = 'summit';
            return true;
        }
    }
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
<script>
    <c:if test="${batmodalthemnthuonghieu==0}">
    document.getElementById('batmodalthemnthuonghieu').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalthemnkichco==0}">
    document.getElementById('batmodalthemnkichco').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalthemnmausac==0}">
    document.getElementById('batmodalthemnmausac').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalthemncoao==0}">
    document.getElementById('batmodalthemncoao').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalthemnchatlieu==0}">
    document.getElementById('batmodalthemnchatlieu').click();
    </c:if>
</script>
</html>
