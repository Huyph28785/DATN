<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>--%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</br>
<style>
    /*.card-body{*/
    /*    margin: 0;*/
    /*    padding: 0;*/
    /*    background-color: lightgrey;*/
    /*    padding-left: 20px;*/
    /*}*/

    .item-right {
        padding-top: 20px;
    }

    #but {
        background-color: #3ec2e2;
        border-radius: 20px;
        border: none;
        width: 150px;

    }


    .left {
        width: 45%;
        padding-left: 20px;
    }

    .item {
        float: left;
    }

    .inp input {
        border: 1px solid #AAAAAA;
        width: 280px;
        border-radius: 8px;
    }

    .form {
        display: flex;
        justify-content: space-between;
    }


    .img input {
        background-color: #ffffff;
    }

    .item input {
        margin-top: 20px;
    }


    .col-lg-6 select {
        height: 30px;
        margin-top: 20px;
        width: 550px;
        border: none

    }

    .form-label {
        padding-left: 30px;
    }

</style>
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
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">

    <h2 class="h3 text-gray-900 mb-4" style="text-align: center; padding-top: 40px;">Thêm/Sửa Chi Tiết Sản Phẩm</h2>
    <form:form action="${action}" modelAttribute="sanpham" enctype="multipart/form-data"
               id="sanPhamForm">
        <form:input path="id" class="form-control" type="hidden"/>
        <div class="row ">
            <div class="col-lg-6" >
                <div class="item inp">
                    <label class="form-label" style="color: black;">Tên sản phẩm <span style="color: red">*</span> : </label>
                    <form:input path="sanPham" class="form-control form-control-user" cssStyle="margin-left: 12px;"
                                type="hidden" />

                    <form:input path="sanPham.ten" value="${tensp}" cssStyle="margin-left: 12px;padding:7px" readonly="true" />
                </div>
                <div class="item inp">
                    <label class="form-label" style="color: black">Giá Bán <span style="color: red">*</span> : </label>
                    <form:input path="giaBan" type="number" min="1" cssStyle="margin-left: 50px;padding:7px" />
                    <p><form:errors path="giaBan" cssStyle="color: crimson"/></p>
                </div>

                <c:if test="${act eq 'update'}">

                    <div class="item inp">
                        <label class="form-label" style="color: black">Số lượng <span style="color: red">*</span> : </label>
                        <form:input path="soLuongTon" type="number" min="1" cssStyle="margin-left: 44px"/>
                        <p><form:errors path="soLuongTon" cssStyle="color: crimson"/></p>
                    </div>
                </c:if>
                <div class="item">
                    <label class="form-label" style="color: black">Mô tả <span style="color: red"></span> : </label>
                    <form:textarea cols="50" rows="5" path="moTaCT"
                                   cssStyle="border: 1px solid #AAAAAA; width: 280px;height: 100px;border-radius: 15px;margin-left: 65px;margin-top: 20px;padding: 10px"
                                   />
                    <p><form:errors path="moTaCT" cssStyle="color: crimson"/></p>
                </div>

                <div class="item form-check-inline">
                    <br>
                    <label class="form-label ms-5" style="color: black;margin-right: 40px">Trạng Thái <span style="color: red">*</span> :</label>
                    <form:radiobutton class="form-check-input me-5" path="trangThai" value="0"
                                      checked="true" cssStyle="color: black"/>Hoạt Động
                    <form:radiobutton class="form-check-input" path="trangThai" value="1"
                                      cssStyle="margin-left: 1cm;color: black"/>Ngưng Hoạt Động
                    <p><form:errors path="trangThai" cssStyle="color: crimson"/></p>
                </div>
            </div>
            <div class="col-lg-6 ">
                <div class="item-right">
                    <label class="form-label" style="margin-right: 15px;">Cổ Áo <span
                            style="color: red">*</span> : </label>

                    <form:select type="text" id="searchName10" path="coAo">
                        <form:option value="">Chọn Cổ Áo</form:option>
                        <form:options items="${listCoAo}" itemLabel="ten" itemValue="id"/>
                    </form:select>

                    <a data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                            class="bi bi-plus-circle-fill"></i></a>
                    <div><form:errors path="coAo" cssStyle="color: crimson"/></div>
                </div>
                <div class="item-right">
                    <label class="form-label">Chất liệu <span style="color: red">*</span>   : </label>

                    <form:select type="text" id="searchName14" path="chatLieu">
                        <form:option value="">Chọn chất liệu</form:option>
                        <form:options items="${listChatLieu}" itemLabel="ten" itemValue="id"/>
                    </form:select>

                    <a data-bs-toggle="modal" data-bs-target="#exampleModal4"><i
                            class="bi bi-plus-circle-fill"></i></a>
                    <div><form:errors path="chatLieu" cssStyle="color: crimson"/></div>
                </div>
                <div class="item-right">
                    <label class="form-label" style="margin-right: 5px;">Hãng Áo<span
                            style="color: red">*</span> : </label>

                    <form:select type="text" id="searchName13" path="thuongHieu">
                        <form:option value="">Chọn Thương Hiệu</form:option>
                        <form:options items="${listThuongHieu}" itemLabel="ten" itemValue="id"/>
                    </form:select>

                    <a data-bs-toggle="modal" data-bs-target="#exampleModal5"><i
                            class="bi bi-plus-circle-fill"></i></a>
                    <div><form:errors path="thuongHieu" cssStyle="color: crimson"/></div>
                </div>
            </div>
        </div>
        <br>
        <c:if test="${act eq 'add'}">
            <h1 class="h4 text-gray-900 mb-4" style="color: black">Biến thể của sản phẩm: </h1>
            <div class="row">
                <div class="col-6">
                    <div class="input-group">

                        <label class="form-label" style="margin-right: 10px;color: black">Kích cỡ <span style="color: red">*</span> : </label>

                        <form:select class="js-example-basic-multiple" name="states[]" multiple="multiple"
                                     id="searchName11"
                                     path="kichCo" cssClass=".searchName1" onChange="updateTable()">
                            <form:option value="">Chọn kích cỡ</form:option>
                            <form:options items="${listKichCo}" itemLabel="ten" itemValue="id"/>
                        </form:select>

                        <a data-bs-toggle="modal" data-bs-target="#exampleModal2"><i

                                class="bi bi-plus-circle-fill" style="margin-left: 10px;"></i></a>

                        <div><form:errors path="kichCo" cssStyle="color: crimson"/></div>
                    </div>
                </div>

                <div class="col-6">
                    <div class="input-group">

                        <label class="form-label" style="margin-right: 10px;color: black">Màu sắc <span style="color: red">*</span> : </label>

                        <form:select class="js-example-basic-multiple" name="states[]" multiple="multiple"
                                     id="searchName12"
                                     path="mauSac" cssClass=".searchName2" onChange="updateTable()">
                            <form:option value="">Chọn màu sắc</form:option>
                            <form:options items="${listMau}" itemLabel="ten" itemValue="id"/>
                        </form:select>

                        <a data-bs-toggle="modal" data-bs-target="#exampleModal3"><i

                                class="bi bi-plus-circle-fill" style="margin-left: 10px;"></i></a>

                        <div><form:errors path="mauSac" cssStyle="color: crimson"/></div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${act eq 'update'}">
            <div class="row">
                <div class="col-6">
                    <div class="input-group">

                        <label class="form-label" style="margin-right: 50px;">Kích cỡ <span style="color: red">*</span> : </label>

                        <form:select type="text" id="searchName11" path="kichCo" cssClass=".searchName1">
                            <form:option value="">Chọn kích cỡ</form:option>
                            <form:options items="${listKichCo}" itemLabel="ten" itemValue="id"/>
                        </form:select>

                        <a data-bs-toggle="modal" data-bs-target="#exampleModal2"><i
                                class="bi bi-plus-circle-fill" style="margin-left: 10px;"></i></a>
                        <div><form:errors path="kichCo" cssStyle="color: crimson"/></div>
                    </div>
                </div>
                <div class="col-6"style="width: 150px" >
                    <div class="input-group">
                        <label class="form-label" style="margin-right: 5px;">Màu sắc <span style="color: red">*</span> : </label>
                        <form:select type="text" id="searchName12" path="mauSac" cssClass=".searchName2">
                            <form:option value="">Chọn màu sắc</form:option>
                            <form:options items="${listMau}" itemLabel="ten" itemValue="id"/>
                        </form:select>

                        <a data-bs-toggle="modal" data-bs-target="#exampleModal3"><i
                                class="bi bi-plus-circle-fill" style="margin-left: 10px;"></i></a>

                        <div><form:errors path="mauSac" cssStyle="color: crimson"/></div>
                    </div>
                </div>
            </div>
        </c:if>
        <br>
<%--        <input type="hidden" id="submitStatus" name="submitStatus" value="${submitStatus}">--%>

        <%--    22                onclick="return confirm('Bạn có chắc muốn thực hiện ?');"--%>
        <div class="text-right" style="padding-bottom: 20px;padding-right: 50px">
            <button type="submit" id="but" class="btn btn-outline-dark" onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                Thêm sản phẩm
            </button>
        </div>
        <c:if test="${not empty sanpham}">
            <table class="table table-bordered">
                <tr class="table-active">
                    <th>STT</th>
                    <th>Màu sắc</th>
                    <th>Kích cỡ</th>
                    <th>Số lượng</th>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">

                </tbody>
            </table>
        </c:if>
        <c:if test="${empty sanpham}">
            <td colspan="8" class="text-center">Không có sản phẩm.</td>
        </c:if>

    </form:form>

    <%--modal--%>
    <%--    modal--%>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="lg" method="post" action="${action4}">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Thêm Cổ Áo</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
<%--                        <div class="input" style="">--%>
<%--                            <p>Mã:</p>--%>
<%--                            <form:input path="ma" class="form-control"/>--%>
<%--                            <div id="ma-error4" style="color: crimson;"></div>--%>
<%--                            <div id="duplicate4-error4-ma" style="color: crimson;"></div>--%>
<%--                        </div>--%>
                        <div class="mb-3">
                            <div class="input">
                                <p>Cổ Áo :</p>
                                <form:input path="ten" class="form-control"/>
                            </div>
                            <form:errors path="ten" id="tentheloai "/>
                            <div id="ten-error4" style="color: crimson;"></div>
                            <div id="duplicate4-error4-ten" style="color: crimson;"></div>
                            <br>
                        </div>

                        <div class="item form-check-inline">
                            <label class="form-label">Trạng Thái <span style="color: red">*</span> :</label>
                            <form:radiobutton class="form-check-input" path="trangThai" value="0"
                                              checked="true"/>Hoạt Động
                            <form:radiobutton class="form-check-input" path="trangThai" value="1"
                                              cssStyle="margin-left: 1cm"/>Ngưng Hoạt Động
                            <p><form:errors path="trangThai" cssStyle="color: crimson"/></p>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button onclick="submitFormLoaiGiay()" type="button" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </form:form>
            </div>

        </div>
    </div>

    <%--    modal2--%>
    <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="kichco" action="${action2}">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel2">Thêm kích cỡ</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                        <%--                    <form:input path="id" class="form-control" type="hidden"/>--%>
<%--                    <div class="mb-3">--%>
<%--                        <label>Mã Kích Cỡ: </label>--%>

<%--                        <form:input path="ma" class="form-control"/>--%>
<%--                        <form:errors path="ma" cssStyle="color: crimson"/>--%>
<%--                        <span id="maKichCoError" class="text-danger"></span>--%>
<%--                        <div id="maKichCo-error3" style="color: crimson;"></div>--%>
<%--                        <div id="duplicate3-error3-maKichCo" style="color: crimson;"></div>--%>
<%--                    </div>--%>
                            <div class="mb-3">
                                <label>Kích Cỡ :</label>
                                <form:input path="ten" class="form-control"/>
                                <form:errors path="ten" cssStyle="color: crimson"/>
                                <div id="ten-error3" style="color: crimson;"></div>
                                <div id="duplicate3-error3-ten" style="color: crimson;"></div>
                            </div>

                            <div class="item form-check-inline">
                                <br>
                                <label class="form-label">Trạng Thái <span style="color: red">*</span> :</label>
                                <form:radiobutton class="form-check-input" path="trangThai" value="0"
                                                  checked="true"/>Hoạt Động
                                <form:radiobutton class="form-check-input" path="trangThai" value="1" cssStyle="margin-left: 1cm"
                                />Ngưng Hoạt Động
                                    <%--cssStyle="margin-right: 15px;margin-left: 35px;"--%>
                                <p><form:errors path="trangThai" cssStyle="color: crimson"/></p>
                            </div>
                    <br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="button" onclick="submitFormKichCo();" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                    </form:form>
                </div>

            </div>
        </div>
    </div>
    <div class="modal fade" id="exampleModal3" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="ms" action="${action3}">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel3">Thêm Màu Sắc</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
<%--                        <div class="mb-3">--%>
<%--                            <label> Mã</label>--%>
<%--                            <form:input class="form-control" path="ma"/>--%>
<%--                            <form:errors path="ma"/>--%>
<%--                            <div id="ma-error2" style="color: crimson;"></div>--%>
<%--                            <div id="duplicate2" style="color: crimson;"></div>--%>
<%--                        </div>--%>
                        <br>
                        <div class="mb-3">
                            <label>Màu :</label>
                            <form:input class="form-control" path="ten"/>
                            <form:errors path="ten"/>
                            <div id="ten-error2" style="color: crimson;"></div>
                            <div id="duplicate2-error2-ten" style="color: crimson;"></div>
                        </div>
                        <div class="item form-check-inline">
                            <br>
                            <label class="form-label">Trạng Thái <span style="color: red">*</span> :</label>
                            <form:radiobutton class="form-check-input" path="trangThai" value="0"
                                              checked="true"/>Hoạt Động
                            <form:radiobutton class="form-check-input" path="trangThai" value="1" cssStyle="margin-left: 1cm"
                            />Ngưng Hoạt Động
                                <%--                    cssStyle="margin-right: 15px;margin-left: 35px;"--%>
                            <p><form:errors path="trangThai" cssStyle="color: crimson"/></p>
                        </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="button" onclick="submitFormMauSac()" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal4" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="vm" action="${action6}">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel4">Thêm chất liệu</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
<%--                        <div id="ma">--%>
<%--                            <label>Mã</label>--%>
<%--                            <form:input path="ma" class="form-control"/> <br/>--%>
<%--                            <form:errors path="ma" cssStyle="color: red"/>--%>
<%--                            <div id="ma-error1" style="color: crimson;"></div>--%>
<%--                            <div id="duplicate1" style="color: crimson;"></div>--%>

<%--                        </div>--%>
                        <div id="ten">
                            <label>Chất Liệu :</label>
                            <form:input path="ten" class="form-control"/> <br/>
                            <form:errors path="ten" cssStyle="color: red"/>
                            <div id="ten-error1" style="color: crimson;"></div>
                            <div id="duplicate1-error-ten" style="color: crimson;"></div>

                        </div>
                        <div class="item form-check-inline">
                            <br>
                            <label class="form-label">Trạng Thái <span style="color: red">*</span> :</label>
                            <form:radiobutton class="form-check-input" path="trangThai" value="0"
                                              checked="true"/> Hoạt Động
                            <form:radiobutton class="form-check-input" path="trangThai" value="1" cssStyle="margin-left: 1cm"
                            /> Ngưng Hoạt Động
                                <%--                    cssStyle="margin-right: 15px;margin-left: 35px;"--%>
                            <p><form:errors path="trangThai" cssStyle="color: crimson"/></p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="button" onclick="submitFormChatLieu()" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </form:form>
            </div>

        </div>
    </div>
    <%--    modal4--%>
    <div class="modal fade" id="exampleModal5" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="degiay" action="${action5}" id="deGiayForm">

                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel5">Thêm Thương Hiệu</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
<%--                    <label for="inputEmail3" class="col-sm-2 col-form-label">Mã</label>--%>
<%--                    <div class="mb-3">--%>
<%--                        <form:input type="text" class="form-control" id="inputEmail3" path="ma"/>--%>
<%--                        <form:errors path="ma" cssStyle="color: crimson"></form:errors>--%>
<%--&lt;%&ndash;                            ${mess_Ma}&ndash;%&gt;--%>
<%--                        <div id="ma-error" style="color: crimson;"></div>--%>
<%--                        <div id="duplicate-error" style="color: crimson;"></div>--%>

<%--                    </div>--%>

                    <label for="inputEmail3" >Thương Hiệu :</label>
                    <div class="mb-3">
                        <form:input type="text" class="form-control" id="inputEmail3" path="ten"/>
                        <form:errors path="ten" cssStyle="color: crimson"></form:errors>
                        <div id="ten-error" style="color: crimson;"></div>
                        <div id="duplicate-error-ten" style="color: crimson;"></div>

                    </div>

                    <div class="item form-check-inline">
                        <br>
                        <label class="form-label">Trạng Thái <span style="color: red">*</span> :</label>
                        <form:radiobutton class="form-check-input" path="trangThai" value="0"
                                          checked="true"/>Hoạt Động
                        <form:radiobutton class="form-check-input" path="trangThai" value="1" cssStyle="margin-left: 1cm"
                        />Ngưng Hoạt Động
                            <%--                    cssStyle="margin-right: 15px;margin-left: 35px;"--%>
                        <p><form:errors path="trangThai" cssStyle="color: crimson"/></p>
                    </div>
                     <br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="submitFormDeGiay()">Submit</button>
                    </div>
                    </form:form>
                </div>

            </div>
        </div>
    </div>
</div>
            </div>
        </div>
    </div>
</div>




            <script src="https://cdn.jsdelivr.net/npm/toastr@2.1.4/dist/toastr.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastr@2.1.4/dist/toastr.min.css"/>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    function updateTable() {
        var selectedSizes = $("#searchName11").select2("data");
        var selectedColors = $("#searchName12").select2("data");

        var table = $('#myTable');
        table.empty();

        var stt = 1;

        selectedSizes.forEach(function (selectedSize) {
            selectedColors.forEach(function (selectedColor) {
                var row = '<tr>';
                row += '<td>' + stt + '</td>';
                row += '<td>' + selectedColor.text + '</td>';
                row += '<td>' + selectedSize.text + '</td>';
                // row += '<td><input type="number" name="giaBan" required class="form-control"></td>';
                row += '<td><input type="number" name="soLuong" required class="form-control" min="1"></td>';
                row += '<td><a href="#" class="btn btn-danger btn-circle delete-row" onclick="deleteRow(this)">Xóa</a></td>';
                row += '</tr>';
                table.append(row);
                stt++;
            });
        });
    }

    function deleteRow(button) {
        var row = button.closest('tr');
        row.remove();
    }

    $(document).ready(function () {
        updateTable();

        $('#searchName11, #searchName12').on('change', function () {
            updateTable();
        });
    });
</script>
<script>
    function clearErrors() {
        $("#error-message").empty();
        $("div[id$='-error']").empty();
        $("#duplicate-error").empty();
        $("#ma-error").empty();
        $("#trangThai-error").empty();
        $("#ten-error").empty();
        $("#ten").empty();
        $("#duplicate-error-ten").empty();
        $("#error-message").empty();
        $("div[id$='-error1']").empty();
        $("#duplicate1").empty();
        $("#ma-error1").empty();
        $("#ten-error1").empty();
        $("#trangThai-error1").empty();
        $("#duplicate1-error-ten").empty();
        $("#error-message").empty();
        $("div[id$='-error2']").empty();
        $("#duplicate2").empty();
        $("#ma-error2").empty();
        $("#ten-error2").empty();
        $("#tt-error2").empty();
        $("#duplicate2-error-ten").empty();
        $("#error-message").empty();
        $("div[id$='-error3']").empty();
        $("#duplicate3").empty();
        $("#trangThai-error3").empty();
        $("#maKichCo-error3").empty();
        $("#ten-error3").empty();
        $("#duplicate3-error3-ten").empty();
        $("#duplicate3-error3-maKichCo").empty();
        $("#error-message").empty();
        $("div[id$='-error4']").empty();
        $("#ma-error4").empty();
        $("#ten-error4").empty();
        $("#duplicate4-error4-ten").empty
    }

    //de giay
    function submitFormDeGiay() {
        var formData = $("#deGiayForm").serialize();
        $.ajax({
            type: "POST",
            url: "${action5}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal5").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal5').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#deGiayForm')[0].reset();
                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Thêm dữ liệu thành công!",
                        showConfirmButton: false,
                        timer: 2500
                    });
                } else {
                    displayErrors(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrors(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error']").empty();
        $("#duplicate-error").empty();
        $("#ma-error").empty();
        $("#trangThai-error").empty();
        $("#ten-error").empty();
        $("#ten").empty();
        $("#duplicate-error-ten").empty();

        errors.forEach(function (error) {
            $("#error-message").append('<div style="color: crimson;">' + error + '</div>');
        });


        errors.forEach(function (error) {
            var fieldName = error.split(":")[0].trim();
            var errorMessage = error.split(":")[1].trim();
            $("#" + fieldName + "-error").append('<div style="color: crimson;">' + errorMessage + '</div>');
        });


        if (response.status === "error" && response.field === "ma") {
            $("#duplicate-error").append('<div style="color: crimson;">' + response.message + '</div>');
        }
        if (response.status === "error" && response.field === "ten") {
            $("#duplicate-error-ten").append('<div style="color: crimson;">' + response.message + '</div>');
        }
    }

    // chat lieu
    function submitFormChatLieu() {
        var formData = $("#vm").serialize();
        $.ajax({
            type: "POST",
            url: "${action6}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal4").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal4').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#vm')[0].reset();

                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Thêm dữ liệu thành công!",
                        showConfirmButton: false,
                        timer: 2500
                    });
                } else {
                    displayErrorsChatLieu(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrorsChatLieu(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error1']").empty();
        $("#duplicate1").empty();
        $("#ma-error1").empty();
        $("#ten-error1").empty();
        $("#trangThai-error1").empty();
        $("#duplicate1-error-ten").empty();

        errors.forEach(function (error1) {
            $("#error-message").append('<div style="color: crimson;">' + error1 + '</div>');
        });

        errors.forEach(function (error) {
            var fieldName1 = error.split(":")[0].trim();
            var errorMessage1 = error.split(":")[1].trim();
            $("#" + fieldName1 + "-error1").append('<div style="color: crimson;">' + errorMessage1 + '</div>');
        });

        if (errors.length > 0) {
            var response = errors[0]; // Lấy thông báo lỗi đầu tiên để kiểm tra trường và giá trị
            if (response.status === "error1" && response.field === "ma") {
                $("#duplicate1").append('<div style="color: crimson;">' + response.message + '</div>');
            }
            if (response.status === "error1" && response.field === "ten") {
                $("#duplicate1-error-ten").append('<div style="color: crimson;">' + response.message + '</div>');
            }
        }
    }

    //mau sac
    function submitFormMauSac() {
        var formData = $("#ms").serialize();
        $.ajax({
            type: "POST",
            url: "${action3}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal3").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal3').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#ms')[0].reset();

                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Thêm dữ liệu thành công!",
                        showConfirmButton: false,
                        timer: 2500
                    });
                } else {
                    displayErrorsMauSac(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrorsMauSac(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error2']").empty();
        $("#duplicate2").empty();
        $("#ma-error2").empty();
        $("#ten-error2").empty();
        $("#tt-error2").empty();
        $("#duplicate2-error-ten").empty();

        errors.forEach(function (error2) {
            $("#error-message").append('<div style="color: crimson;">' + error2 + '</div>');
        });

        errors.forEach(function (error) {
            var fieldName2 = error.split(":")[0].trim();
            var errorMessage2 = error.split(":")[1].trim();
            $("#" + fieldName2 + "-error2").append('<div style="color: crimson;">' + errorMessage2 + '</div>');
        });

        if (errors.length > 0) {
            var response = errors[0]; // Lấy thông báo lỗi đầu tiên để kiểm tra trường và giá trị
            if (response.status === "error2" && response.field === "ma") {
                $("#duplicate2-error-ma").append('<div style="color: crimson;">' + response.message + '</div>');
            }
            if (response.status === "error2" && response.field === "ten") {
                $("#duplicate2-error-ten").append('<div style="color: crimson;">' + response.message + '</div>');
            }
        }
    }

    //kich co
    function submitFormKichCo() {
        var formData = $("#kichco").serialize();
        $.ajax({
            type: "POST",
            url: "${action2}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal2").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal2').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#kichco')[0].reset();

                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Thêm dữ liệu thành công!",
                        showConfirmButton: false,
                        timer: 2500
                    });
                } else {
                    displayErrorsKichCo(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrorsKichCo(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error3']").empty();
        $("#duplicate3").empty();
        $("#trangThai-error3").empty();
        $("#maKichCo-error3").empty();
        $("#ten-error3").empty();
        $("#duplicate3-error3-ten").empty();
        $("#duplicate3-error3-maKichCo").empty();

        errors.forEach(function (error3) {
            $("#error-message").append('<div style="color: crimson;">' + error3 + '</div>');
        });

        errors.forEach(function (error3) {
            var fieldName3 = error3.split(":")[0].trim();
            var errorMessage3 = error3.split(":")[1].trim();
            $("#" + fieldName3 + "-error3").append('<div style="color: crimson;">' + errorMessage3 + '</div>');
        });

        if (errors.length > 0) {
            var response = errors[0]; // Lấy thông báo lỗi đầu tiên để kiểm tra trường và giá trị
            if (response.status === "error3" && response.field === "ma") {
                $("#duplicate3-error3-makichCo").append('<div style="color: crimson;">' + response.message + '</div>');
            }
            if (response.status === "error3" && response.field === "ten") {
                $("#duplicate3-error3-ten").append('<div style="color: crimson;">' + response.message + '</div>');
            }
        }
    }

    //loai giay
    function submitFormLoaiGiay() {
        var formData = $("#lg").serialize();
        $.ajax({
            type: "POST",
            url: "${action4}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#lg')[0].reset();

                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Thêm dữ liệu thành công!",
                        showConfirmButton: false,
                        timer: 2500
                    });
                } else {
                    displayErrorsLoaiGiay(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrorsLoaiGiay(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error4']").empty();
        $("#ma-error4").empty();
        $("#ten-error4").empty();
        $("#duplicate4-error4-ten").empty();
        $("#duplicate4-error4-ma").empty();

        errors.forEach(function (error4) {
            $("#error-message").append('<div style="color: crimson;">' + error4 + '</div>');
        });

        errors.forEach(function (error4) {
            var fieldName4 = error4.split(":")[0].trim();
            var errorMessage4 = error4.split(":")[1].trim();
            $("#" + fieldName4 + "-error4").append('<div style="color: crimson;">' + errorMessage4 + '</div>');
        });

        if (errors.length > 0) {
            var response = errors[0]; // Lấy thông báo lỗi đầu tiên để kiểm tra trường và giá trị
            if (response.status === "error4" && response.field === "ma") {
                $("#duplicate4-error4-ma").append('<div style="color: crimson;">' + response.message + '</div>');
            }
            if (response.status === "error4" && response.field === "ten") {
                $("#duplicate4-error4-ten").append('<div style="color: crimson;">' + response.message + '</div>');
            }
        }
    }

</script>

<div class="text-center" style="color: crimson">${mess}</div>
<script src="../../../js/chi_tiet_san_pham/chi_tiet_san_pham.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous"></script>

<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<!-- Include SweetAlert2 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<script>

    $(document).ready(function () {
        $('.js-example-basic-multiple').select2();
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


