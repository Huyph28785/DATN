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

    <style>

    </style>
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
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h1 class="card-title" style="text-align:center;margin-left: 70px;font-size: 30px;font-family: Arial, Baskerville, monospace">Danh sách Chi Tiết Sản Phẩm</h1>

                             <form>
                                 <div class="row mt-3 ms-5 mb-3">
                                     <div class="col-3">
                                             <form:form modelAttribute="lg" action="/chi-tiet-san-pham/search-by-coao">
                                             </form:form>
                                         <div class="form-group" >
                                             <form:form modelAttribute="lg" action="/chi-tiet-san-pham/search-by-coao">
                                                 <label class="form-label">Cổ áo: </label>
                                                 <form:select type="text" path="idCA" onchange="submit()" class="form-control" style="border-radius: 10px;border:0.5px solid #AAAAAA">
                                                     <form:option value="">Tất cả</form:option>
                                                     <form:options items="${listCoAo}" itemLabel="ten" itemValue="id"/>
                                                 </form:select>
                                             </form:form>
                                         </div>
                                     </div>
                                         <div class="col-2">
                                             <div class="form-group" >
                                             <form:form modelAttribute="searchChatLieu" action="/chi-tiet-san-pham/search-by-chatlieu">
                                                 <label class="form-label">Chất liệu: </label>
                                                 <form:select type="text" id="searchName4" path="idChatLieu" onchange="submit()" class="form-control" style="border-radius: 10px;border:0.5px solid #AAAAAA">
                                                     <form:option value="">Tất cả</form:option>
                                                     <form:options items="${listChatLieu}" itemLabel="ten" itemValue="id"/>
                                                 </form:select>

                                             </form:form>
                                                 </div>
                                         </div>

                                         <div class="col-2">
                                         <div class="form-group">
                                             <form:form action="/chi-tiet-san-pham/search-by-thuonghieu" modelAttribute="searchDG">
                                                 <label class="form-label">Thương hiệu: </label>
                                                 <form:select type="text" id="searchName3" path="idTH" onchange="submit()" class="form-control" style="border-radius: 10px;border:0.5px solid #AAAAAA">
                                                     <form:option value="">Tất cả</form:option>
                                                     <form:options items="${listThuongHieu}" itemLabel="ten" itemValue="id"/>
                                                 </form:select>
                                             </form:form>
                                     </div>
                                         </div>


                                         <div class="col-2" >
                                             <div class="form-group" >
                                             <form:form action="/chi-tiet-san-pham/search-by-kichco" modelAttribute="searchKC">
                                                 <label class="form-label">Kích cỡ: </label>
                                                 <form:select type="text" id="searchName1" path="idKC" onchange="submit()" class="form-control" style="border-radius: 10px;border:0.5px solid #AAAAAA">
                                                     <form:option value="">Tất cả</form:option>
                                                     <form:options items="${listKichCo}" itemLabel="ten" itemValue="id"/>
                                                 </form:select>

                                             </form:form>
                                             </div>
                                         </div>

                                         <div class="col-3" >
                                             <div class="form-group">
                                             <form:form action="/chi-tiet-san-pham/search-by-mausac" modelAttribute="searchFormByMau">
                                                 <label class="form-label">Màu sắc:</label>
                                                 <form:select type="text" id="searchName2" path="idMau" onchange="submit()" class="form-control" style="border-radius: 10px;border:0.5px solid #AAAAAA">
                                                     <form:option value="">Tất cả</form:option>
                                                     <form:options items="${listMau}" itemLabel="ten" itemValue="id"/>
                                                 </form:select>
                                             </form:form>
                                             </div>
                                         </div>



                                    </div>
                             </form>

                                <form:form action="/chi-tiet-san-pham/search" modelAttribute="searchForm">
                                    <div class="input-group" style="width: 40%; float: right">
                                        <form:input path="keyword" class="form-control" placeholder="Nhập mã hoặc tên sản phẩm"/>
                                        <div class="input-group-append">
                                            <button class="btn btn-sm btn-primary" type="submit">Search</button>
                                        </div>
                                    </div>
                               </form:form>


                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                     </div>
                </div>
<%--            </div>--%>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="row align-items-center">
                        <div class=" col-xl-12">
                            <div class="card">
                                <div class="card-body">
                                <h4 class="card-title" style="float: left">Danh sách chi tiết sản phẩm</h4>
                                <div class="table-responsive">
                                    <c:if test="${not empty page.content}">

                                        <table class="table table-hover "
                                               style="color: black;width: 1500px">
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã sản phẩm</th>
                                                <th>Ảnh Sản Phẩm</th>
                                                <th>Tên sản phẩm</th>
                                                <th>Loại Cổ Áo</th>
                                                <th>Màu sắc</th>
                                                <th>Thương Hiệu</th>
                                                <th>Chất liệu</th>
                                                <th>Kích cỡ</th>
                                                <th>Giá Bán</th>
                                                <th>Số lượng</th>
                                                <th>Trạng Thái</th>

                                                <td>Action</td>
                                            </tr>

                                            <tbody id="myTable">
                                            <c:forEach items="${page.content}" var="sp" varStatus="i">
                                                <tr>
                                                    <td>${i.index+1}</td>
                                                    <td>${sp.ma}</td>
                                                    <td>
                                                        <img src="../../../uploads/${sp.hinhAnh.duongDan1}" width="40"
                                                             height="40"
                                                             style="border-radius:50% 50% 50% 50%">
                                                    </td>
                                                    <td>${sp.sanPham.ten}</td>
                                                    <td>${sp.coAo.ten}</td>
                                                    <td>${sp.mauSac.ten}</td>
                                                    <td>${sp.thuongHieu.ten}</td>
                                                    <td>${sp.chatLieu.ten}</td>
                                                    <td>${sp.kichCo.ten}</td>
                                                    <td>
                                                        <script>
                                                            var donGia = ${sp.giaBan};
                                                            document.write(donGia.toLocaleString('vi-VN'));
                                                        </script>
<%--                                                        VND--%>
                                                    </td>
<%--                                                    <td>${sp.giaBan}</td>--%>
                                                    <td>${sp.soLuongTon}</td>
                                                    <td>${sp.trangThai==1 ?"Không hoạt động":"Hoạt động"}</td>

                                                    <td>
                                                        <a href="/chi-tiet-san-pham/view-update/${sp.id}" class="btn btn-warning"><i
                                                                class="bi bi-pencil-square"></i></a>
                                                            <%--                             Nut Add Hinh Anh--%>
                                                        <a href="/chi-tiet-san-pham/hinh-anh-sp/view-add/${sp.id}?idSP=${sp.sanPham.id}&idMS=${sp.mauSac.id}" class="btn btn-warning"><i
                                                                class="bi bi-file-earmark-image"></i></a>
<%--                                                        QR--%>
                                                        <a href="/chi-tiet-san-pham/show-qr/${sp.id}"
                                                           class="btn btn-info btn-icon-text"
                                                           data-bs-toggle="modal"
                                                           data-bs-target="#showQR_${sp.id}">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                                 fill="currentColor" class="bi bi-qr-code-scan"
                                                                 viewBox="0 0 16 16">
                                                                <path d="M0 .5A.5.5 0 0 1 .5 0h3a.5.5 0 0 1 0 1H1v2.5a.5.5 0 0 1-1 0v-3Zm12 0a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0V1h-2.5a.5.5 0 0 1-.5-.5ZM.5 12a.5.5 0 0 1 .5.5V15h2.5a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5v-3a.5.5 0 0 1 .5-.5Zm15 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1 0-1H15v-2.5a.5.5 0 0 1 .5-.5ZM4 4h1v1H4V4Z"/>
                                                                <path d="M7 2H2v5h5V2ZM3 3h3v3H3V3Zm2 8H4v1h1v-1Z"/>
                                                                <path d="M7 9H2v5h5V9Zm-4 1h3v3H3v-3Zm8-6h1v1h-1V4Z"/>
                                                                <path d="M9 2h5v5H9V2Zm1 1v3h3V3h-3ZM8 8v2h1v1H8v1h2v-2h1v2h1v-1h2v-1h-3V8H8Zm2 2H9V9h1v1Zm4 2h-1v1h-2v1h3v-2Zm-4 2v-1H8v1h2Z"/>
                                                                <path d="M12 9h2V8h-2v1Z"/>
                                                            </svg>
                                                            QR Code</a>
                                                        <div class="modal fade" id="showQR_${sp.id}" tabindex="-1"
                                                             aria-labelledby="exampleModalLabelQR"
                                                             aria-hidden="true" data-backdrop="static">
                                                            <div class="modal-dialog modal-dialog-centered">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h2 class="modal-title" id="exampleModalLabelQR">QR
                                                                            Code</h2>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <table class="table" id="table_id">
                                                                            <tbody id="listCTSP_${sp.id}"
                                                                                   class="ctsp_search">
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary"
                                                                                data-bs-dismiss="modal">Close
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                    <c:if test="${empty page.content}">
                                        <td colspan="8" class="text-center">Không có sản phẩm.</td>
                                    </c:if>
                                    <div class="text-center">
                                        <nav aria-label="Page navigation text-center">
                                            <ul class="pagination justify-content-center">
                                                <li class="page-item"><a class="page-link" href="/chi-tiet-san-pham/hien-thi?p=0">Previous</a></li>
                                                <li class="page-item"><a class="page-link"
                                                                         href="/chi-tiet-san-pham/hien-thi?p=${page.number-1}"><<</a></li>
                                                <li class="page-item"><a class="page-link"
                                                                         href="/chi-tiet-san-pham/hien-thi?p=${page.number+1}">>></a></li>
                                                <li class="page-item"><a class="page-link"
                                                                         href="/chi-tiet-san-pham/hien-thi?p=${page.totalPages-1}">Next</a></li>
                                            </ul>
                                        </nav>
                                    </div>
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
<script src="../../../../../webapp/js/chi-tiet-san-pham.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#selectThuongHieu').select2();
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
        document.getElementById('cucheck2').value = 'moi2';
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
        document.getElementById('cucheck3').value = 'moi3';
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
        document.getElementById('cucheck4').value = 'moi4';
    });
</script>
<script>
    <c:if test="${batmodaldetailchitiet==0}">
    document.getElementById('batmodaldetailchitiet').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalupdatechitiet==0}">
    document.getElementById('batmodalupdatechitiet').click();
    </c:if>
</script>
<script>
    $(document).ready(function () {
        $('div[id^="showQR_"]').on('show.bs.modal', async function (e) {
            const id = e.currentTarget.id.split("_")[1];
            var url = '/chi-tiet-san-pham/show-qr/'+id;
            console.log(id, url);
            try {
                const resp = await fetch(url);
                const data = await resp.json();
                console.log(data)
                let html = '';
                for (let i = 0; i < data.length; i++) {
                    const ctsp = data[i];
                    const tr = `
               <tr>
                    <td align="center"><img src="../../../maqr/` + ctsp.maQR + `" width="300" height="300"></td>
                </tr>
            `;
                    html += tr;
                }

                $("#listCTSP_" + id).html(html);
            } catch (err) {
                console.error(err)
            }
        });
    });
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
</html>
