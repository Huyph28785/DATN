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
    <title>Focus - Bootstrap Admin Dashboard </title>
    <!-- Favicon icon -->
</head>

<body>


<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="row align-items-center">
            <div class=" col-xl-12">
                <div class="card">
                    <div class="card-body">
                        <ul class="nav nav-pills mb-3" id="setting-panel" role="tablist">
                                                            <li class="nav-item">
                                                                <a class="nav-link active" id="description-tab" data-toggle="tab" href="/thong-ke/hien-thi"
                                                                   role="tab"
                                                                   aria-controls="description" aria-selected="false">Doanh thu theo tháng</a>
                                                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/thong-ke/hien-thi-hang" role="tab"
                                <%--                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"--%>
                                >Doanh thu theo hãng</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/thong-ke/hien-thi-khach-hang" role="tab"
                                <%--                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"--%>
                                >Doanh thu theo khách hàng</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/thong-ke/hien-thi-nhan-vien" role="tab"
                                <%--                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"--%>
                                >Doanh thu theo nhân viên</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
<div class="col-4">
    <div class="card">
        <div class="stat-widget-two card-body">

            <div class="stat-content">
                <c:forEach items="${listDoanhThuNgay}" var="dtNgay">
                    <div class="stat-text" style="color: black">Doanh thu ngày ${dtNgay.getNgay()}/${dtNgay.getThang()}/${dtNgay.getNam()} </div>

                    <div class="stat-digit"> <script>
                        var tongTien = ${dtNgay.getDoanhThuThucTe()};
                        document.write(tongTien.toLocaleString('vi-VN'));
                    </script> VNĐ</div>
                </c:forEach>
            </div>
        </div>
    </div>

</div>

<div class="col-4">
    <div class="card">
        <div class="stat-widget-two card-body">

            <div class="stat-content">
                <c:forEach items="${listDoanhThuSP}" var="dtSP">
                    <div class="stat-text" style="color: black">Doanh thu Sản phẩm ngày ${dtSP.getNgay()}/${dtSP.getThang()}/${dtSP.getNam()} </div>

                    <div class="stat-digit"> <script>
                        var tongTien = ${dtSP.getSoLuongSP()};
                         document.write(tongTien.toLocaleString('vi-VN'));
                    </script> Sản Phẩm</div>
                </c:forEach>
            </div>
        </div>
    </div>

</div>

    <div class="col-4">
        <div class="card">
            <div class="stat-widget-two card-body">

                <div class="stat-content">
                    <c:forEach items="${listDoanhThuHoaDon}" var="dtHD">
                        <div class="stat-text" style="color: black">Doanh thu tổng hóa đơn ngày ${dtHD.getNgay()}/${dtHD.getThang()}/${dtHD.getNam()} </div>

                        <div class="stat-digit"> <script>
                            var tongTien = ${dtHD.getTongHD()};
                            document.write(tongTien.toLocaleString('vi-VN'));
                        </script> Hóa đơn</div>
                    </c:forEach>
                </div>
            </div>
        </div>
        </div>
</div>
<div class="card">
    <h4 class="card-title">Biểu đồ doanh thu các tháng trong năm </h4>
<%--    <li>--%>
<%--                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">--%>
<%--                            <i class="icon-grid menu-icon"></i><span class="nav-text">Thống Kê</span>--%>
<%--                        </a>--%>

<%--                            <li><a href="/thong-ke/hien-thi">Doanh Thu theo tháng</a></li>--%>
<%--        <ul aria-expanded="false">--%>

<%--        </ul>--%>

<%--    </li>--%>
    <form action="/thong-ke/loc-nam" method="post" style="text-align: right">
        <select name="namSelect" style="height: 34.78px">
            <option value="" disabled selected>Chọn năm</option>
            <c:forEach items="${listYear}" var="nam">
                <option value="${nam.getNam()}">${nam.getNam()}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary mr-2"
                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
            Lọc
        </button>
    </form>
    <div class="card-body">
        <canvas id="myChart" ></canvas>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
<script>
    const data = [];

    <c:forEach items="${listDoanhThu}" var="DT" varStatus="index">
    data.push({
        thang: ${DT.getThang()},
        soLuong: ${DT.getSoLuongSP()},
        doanhThuThucTe: ${DT.getDoanhThuThucTe()},
    });
    </c:forEach>

    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.map(item => item.thang),
            datasets: [{
                label: 'Doanh thu thực tế',
                data: data.map(item => item.doanhThuThucTe),
                borderWidth: 1,
                yAxisID: 'y',
                borderColor: '#ff6384',
                backgroundColor: '#ff6384',
            },
                {
                    label: 'Số lượng sản phẩm đã bán',
                    data: data.map(item => item.soLuong),
                    borderWidth: 1,
                    yAxisID: 'y1',
                    borderColor: '#36a2eb',
                    backgroundColor: '#36a2eb',
                },
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    id: 'y'
                },
                y1: {
                    beginAtZero: true,
                    id: 'y1',
                    position: 'right',

                    // grid line settings
                    grid: {
                        drawOnChartArea: false, // only want the grid lines for one axis to show up
                    },
                },
            }
        }
    });
</script>
</html>
