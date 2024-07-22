<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<body>

<c:forEach items="${listsp}" var="ht" varStatus="stt">
    <a href="/ban-hang-online/chi-tiet-san-pham/${ht.id}" >
        <img src="../../../uploads/${ht.hinhAnh.tenAnh}" width="30" height="30" style="border-radius:50% 50% 50% 50%;">
        <label style=" font-weight: normal;margin-left: 10px">${ht.sanPham.ten},màu:${ht.mauSac.ten},kích cỡ${ht.kichCo.ten},..</label>

            <label style=" font-weight: normal;margin-left: 10px">
                <label style="font-weight: bold">Đơn
                    giá:</label>
                <fmt:formatNumber value="${ht.giaBan}" type="number"/> VND
            </label>

    </a>
    <br>





</c:forEach>
</body>

</html>