package com.example.demo.repositories;


import com.example.demo.dto.Top10SanPham;
import com.example.demo.models.*;
import com.example.demo.viewmodels.DSfullSanPhamDatHangOnline;
import com.example.demo.viewmodels.TongtienvsTongspchon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface BanHangOnLinerepository extends JpaRepository<KhachHang, UUID> {


    @PersistenceContext
    EntityManager entityManager = null;


    @Query("select ct from SanPham sp left join ChiTietSanPham ct on sp.id=ct.sanPham.id where sp.trangThai=0 and ct.trangThai=0 order by sp.ngayTao desc")
    List<ChiTietSanPham> ctspbanhang();

    @Query(value = "SELECT TOP 10\n" +
            "    SanPham.ten,\n" +
            "    COUNT(SanPham.id) AS so_luong_ban\n" +
            "FROM HoaDon\n" +
            "LEFT JOIN HoaDonChiTiet ON HoaDon.id = HoaDonChiTiet.idHoaDon\n" +
            "LEFT JOIN ChiTietSanPham ON ChiTietSanPham.id = HoaDonChiTiet.idCTSP\n" +
            "LEFT JOIN SanPham ON SanPham.id = ChiTietSanPham.idSanPham\n" +
            "WHERE HoaDon.TrangThaiHoaDon = 5\n" +
            "GROUP BY SanPham.id, SanPham.ten\n" +
            "ORDER BY so_luong_ban DESC;", nativeQuery = true)
    List<Top10SanPham> top10SanPhamBanChay();


//    @Query("select ct from SanPham sp left join ChiTietSanPham ct on sp.id=ct.sanPham.id where sp.tinhTrang=0 and ct.tinhTrang=0 ")
//    List<ChiTietSanPham> ctspbanhangBanChay();

    @Query("select hdct from HoaDonChiTiet hdct left join HoaDon hd on hdct.idHoaDon.id=hd.id where hd.id=:id")
    List<HoaDonChiTiet> getHoaDonChiTiet(UUID id);

    @Transactional
    @Query(value = "" +
            "DECLARE @biena int;\n" +
            "DECLARE @bienb int;\n" +
            "DECLARE @bienc int;\n" +

            " SET @biena = (select sum(soLuongTon) from \n" +
            " ChiTietSanPham where TrangThai=0 and Id=:idctsp) ;\n" +

            "SET @bienb = (select sum(a.SoLuong) \n" +
            "from HoaDonChiTiet a group by a.IdCTSP,a.TrangThai\n" +
            "having TrangThai=0 and IdCTSP=:idctsp) ;\n" +

            "if @bienb is null\n" +
            "            begin\n" +
            "            set @bienb=0;\n" +
            "            end\n" +

            "SET @bienc=(@biena+@bienb)-@bienb;\n" +

            "if (@bienc < 0 OR @bienc IS NULL)\n" +
            "            begin\n" +
            "            set @bienc=0;\n" +
            "            end\n" +

            "            SELECT CAST(@bienc AS int);", nativeQuery = true)
    Integer soluongcon(UUID idctsp);


    @Transactional
    @Query(value = "DECLARE @bienb int; " +
            "SET @bienb = (select COUNT(Id) from " +
            "ChiTietsanPham where TrangThai=0 and Id=:idctsp) ;" +
            "SELECT CAST(@bienb AS int);", nativeQuery = true)
    Integer soluongdaban(UUID idctsp);

    @Query("SELECT ct FROM ChiTietSanPham ct LEFT JOIN SanPham sp ON ct.sanPham.id = sp.id " +
            "LEFT JOIN KichCo kc ON ct.kichCo.id = kc.id " +
            "LEFT JOIN MauSac ms ON ct.mauSac.id = ms.id " +

            "WHERE " +
            "(:idMauSac = 'null' OR ms.ten = :idMauSac) " +
            "AND (:idKichCo = 'null' OR kc.ten = :idKichCo) " +
            "AND (:tenSP = 'null' OR sp.ten = :tenSP) " +
            "AND (ct.trangThai=0) "
    )
    List<ChiTietSanPham> locbanhang(
                                    @Param("idMauSac") String idMauSac,
                                    @Param("idKichCo") String idKichCo,
                                    @Param("tenSP") String tenSP);


    @Query("SELECT ct FROM ChiTietSanPham ct LEFT JOIN SanPham sp ON ct.sanPham.id = sp.id " +
            "LEFT JOIN ChatLieu cl ON ct.chatLieu.id = cl.id " +
            "LEFT JOIN KichCo kc ON ct.kichCo.id = kc.id " +
            "LEFT JOIN ThuongHieu th ON ct.thuongHieu.id = th.id " +
            "LEFT JOIN CoAo ca ON ct.coAo.id = ca.id " +
            "LEFT JOIN MauSac ms ON ct.mauSac.id = ms.id " +

            "WHERE " +
            "(:idChatLieu = 'null' OR cl.ten= :idChatLieu) " +
            "AND (:idMauSac = 'null' OR ms.ten = :idMauSac) " +
            "AND (:idThuongHieu = 'null' OR th.ten = :idThuongHieu) " +
            "AND (:idKichCo = 'null' OR kc.ten = :idKichCo) " +
            "AND (:idCoAo = 'null' OR ca.ten = :idCoAo) " +
            "AND (:tenSP = 'null' OR sp.ten = :tenSP) " +
            "AND (ct.giaBan >= :tienMin AND ct.giaBan <= :tienMax)" +
            "AND (ct.trangThai=0) "
    )
    List<ChiTietSanPham> locbanhangcoGIATIEN(@Param("idChatLieu") String idChatLieu,
                                             @Param("idMauSac") String idMauSac,
                                             @Param("idThuongHieu") String idThuongHieu,
                                             @Param("idKichCo") String dKichCo,
                                             @Param("idCoAo") String idCoAo,
                                             @Param("tenSP") String tenSP,
                                             @Param("tienMin") BigDecimal tienMin,
                                             @Param("tienMax") BigDecimal tienMax);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.sanPham.id=:idsp ")
    List<ChiTietSanPham> ListctspTheoidsp(@Param("idsp") UUID idsp);

    @Query("select gh from  GioHang gh where gh.khachHang.id=:idkh ")
    List<GioHang> ListghTheoidkh(@Param("idkh") UUID idkh);


    @Transactional
    @Query(value = "DECLARE @bienb int;\n" +
            "set @bienb=(\n" +
            "select sum (SoLuong) from GioHangChiTiet \n" +
            "where IdGioHang=:idgh and IdChiTietSP=:idctsp \n" +
            ")\n" +
            "if @bienb is null\n" +
            "begin\n" +
            "set @bienb=0;\n" +
            "end\n" +
            "SELECT CAST(@bienb AS int); ", nativeQuery = true)
    Integer sl1ctsptronggh(@Param("idgh") UUID idgh, @Param("idctsp") UUID idctsp);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh ")
    List<GioHangChiTiet> ListghctTheoidgh(@Param("idgh") UUID idgh);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh and ghct.chiTietSanPham.id=:idctsp ")
    List<GioHangChiTiet> ListghctTheoIdghvsIdctsp(@Param("idgh") UUID idgh, @Param("idctsp") UUID idctsp);

    @Query(value = "select sum(SoLuong*DonGiaKhiGiam) as tongtien,\n" +
            "COUNT(id) as tongsanphamchon  from\n" +
            "GioHangChiTiet \n" +
            "where tinhTrang=0 and IdGioHang=:idgh", nativeQuery = true)
    TongtienvsTongspchon TongtienvsTongspchon(@Param("idgh") UUID idgh);

    @Transactional
    @Modifying
    @Query(value = "update GioHangChiTiet set tinhTrang=:trangthai where IdGioHang=:idgh", nativeQuery = true)
    void trangthaighct(@Param("trangthai") Integer trangthai, @Param("idgh") UUID idgh);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh and  ghct.tinhTrang=0")
    List<GioHangChiTiet> ListghTheoidghvsTT1(@Param("idgh") UUID idgh);

    @Query("select dc from  DiaChi dc where dc.khachHang.id=:idkh and  dc.trangThai=0")
    List<DiaChi> Listdiachimotkhachang(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.ma=:mahd")
    HoaDon timhdtheomahd(@Param("mahd") String mahd);


    @Transactional
    @Modifying
    @Query("delete from GioHangChiTiet ghct where ghct.gioHang.id=:idgh and ghct.tinhTrang=0")
    void xoaghcttheoIDGHvsTTO(@Param("idgh") UUID idgh);

    @Transactional
    @Modifying
    @Query("delete from HoaDonChiTiet ghct where ghct.idHoaDon.id=:idhd ")
    void xoaDDHtheoIDHD(@Param("idhd") UUID idhd);

    @Query("select hd from  HoaDon hd where hd.khachHang.id=:idkh order by hd.ma desc")
    List<HoaDon> timhoadontheoidkh(@Param("idkh") UUID idkh);

    @Query("SELECT hd FROM HoaDon hd WHERE hd.khachHang.id = :idkh order by hd.ma desc")
    Page<HoaDon> cacDonHang(@Param("idkh") UUID idkh, Pageable pageable);


    @Query("select hd from  HoaDon hd where hd.trangThaiHoaDon=8 and hd.khachHang.id=:idkh order by hd.ma desc")
    List<HoaDon> donHang8(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.trangThaiHoaDon=0 and hd.khachHang.id=:idkh order by hd.ma desc")
    List<HoaDon> donHang0(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.trangThaiHoaDon=1 and hd.khachHang.id=:idkh order by hd.ma desc")
    List<HoaDon> donHang1(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.trangThaiHoaDon=2 and hd.khachHang.id=:idkh order by hd.ma desc")
    List<HoaDon> donHang2(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.trangThaiHoaDon=3 and hd.khachHang.id=:idkh order by hd.ma desc")
    List<HoaDon> donHang3(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.trangThaiHoaDon=4 and hd.khachHang.id=:idkh order by hd.ma desc")
    List<HoaDon> donHang4(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.trangThaiHoaDon=5 and hd.khachHang.id=:idkh order by hd.ma desc")
    List<HoaDon> donHang5(@Param("idkh") UUID idkh);





    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.trangThaiHoaDon=0 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten%  or hdct.idCTSP.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDH0(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.trangThaiHoaDon=1 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten%  or hdct.idCTSP.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDH1(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.trangThaiHoaDon=2 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten%  or hdct.idCTSP.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%)  order by hd.ma DESC")
    List<HoaDon> searchDH2(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.trangThaiHoaDon=8 and hd.khachHang.id=:idkh  and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten%  or hdct.idCTSP.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDH8(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.trangThaiHoaDon=3 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten%  or hdct.idCTSP.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDH3(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten% or hd.ngayTao like %:ten% or hd.ngayThanhToan like %:ten%) order by hd.ma DESC")
    List<HoaDon> search(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.trangThaiGiaoHang=0 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten%  or hdct.idCTSP.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchChoXuLy(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.trangThaiGiaoHang=3 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten%  or hdct.idCTSP.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDangGiao(@Param("idkh") UUID idkh, String ten);

    @Query("select hd from  HoaDon hd LEFT JOIN HoaDonChiTiet hdct on hd.id=hdct.idHoaDon.id where hd.trangThaiHoaDon=5 and hd.khachHang.id=:idkh and (hd.ma LIKE %:ten% or hd.khachHang.soDienThoai like %:ten%  or hdct.idCTSP.sanPham.ten like  %:ten% or hd.ngayTao like %:ten%) order by hd.ma DESC")
    List<HoaDon> searchDHThanhCong(@Param("idkh") UUID idkh, String ten);

    @Query("select hdct from  HoaDonChiTiet hdct where hdct.idHoaDon.id=:idhd   ")
    List<HoaDonChiTiet> timhoadonchitiettheoidhd(@Param("idhd") UUID idhd);

    @Query("select hdct from  HoaDonChiTiet hdct where hdct.idHoaDon.id=:idhd and hdct.idCTSP.id=:idctsp ")
    List<HoaDonChiTiet> listIMEItheoIDHDvsIDCTSP(@Param("idhd") UUID idhd, @Param("idctsp") UUID idctsp);

    @Transactional
    @Modifying
    @Query(value = "update HoaDon  hd set  hd.trangThaiHoaDon=8 where  hd.id=:idhd")
    void huyhoadon(@Param("idhd") UUID idhd);

    @Transactional
    @Modifying
    @Query(value = "update HoaDonChiTiet  hd set  hd.trangThai=8 where  hd.idHoaDon.id=:idhd")
    void huyhoadonchitiet(@Param("idhd") UUID idhd);

    @Query("select hdct from  HoaDonChiTiet hdct where hdct.idHoaDon.id=:idhd")
    List<HoaDonChiTiet> listHDCTtheoIDHD(@Param("idhd") UUID idhd);



    @Transactional
    @Modifying
    @Query(value = "delete from  HoaDonChiTiet hdct where hdct.id in (select hdct.id from  HoaDonChiTiet hdct where hdct.idHoaDon.id=:idhd and hdct.idCTSP.id=:idctsp)")
    void XoahdcttheoIDHDvsIDCTSP(@Param("idhd") UUID idhd, @Param("idctsp") UUID idctsp);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "LEFT JOIN ChatLieu cl ON ct.chatLieu.id = cl.id " +
            "LEFT JOIN KichCo kc ON ct.kichCo.id = kc.id " +
            "LEFT JOIN ThuongHieu th ON ct.thuongHieu.id = th.id " +
            "LEFT JOIN CoAo ca ON ct.coAo.id = ca.id " +
            "LEFT JOIN MauSac ms ON ct.mauSac.id = ms.id " +
            "where ct.trangThai=0  and" +
            " (sp.ten like %:ten% or cl.ten like %:ten% or kc.ten like %:ten% or th.ten like %:ten% " +
            "or ca.ten like %:ten% or ms.ten like %:ten%)")
    List<ChiTietSanPham> timkiemTrangChu(@Param("ten") String ten);


    @Query("select hd from  HoaDon hd where hd.loaiHoaDon=1 ")
    List<HoaDon> dsHDonlineloai1();


    @Query("select hd from HoaDonChiTiet hd where hd.idHoaDon.id=:idhd ")
    List<HoaDonChiTiet> dsDDHtheoIDHD(@Param("idhd") UUID idhd);


    @Transactional
    @Query(value = "SELECT SUM(sl1ctspTRONGhdctTHEOidhdVSisctsp) AS total_count\n" +
            "FROM (\n" +
            "    SELECT COUNT(a.Id) AS sl1ctspTRONGhdctTHEOidhdVSisctsp\n" +
            "    FROM HoaDonChiTiet a\n" +
            "    JOIN ChiTietSanPham b ON a.IdCTSP = b.Id\n" +
            "    WHERE a.idHoaDon = :idhd\n" +
            "      AND b.idCTSP = :idctsp\n" +
            "    GROUP BY a.Id, a.idHoaDon, b.idCTSP\n" +
            ") AS counts", nativeQuery = true)
    Integer sl1ctspTRONGhdctTHEOidhdVSisctsp(@Param("idhd") UUID idhd, @Param("idctsp") UUID idctsp);

//    @Query("select hd from  IMEI hd where hd.soImei=:soimei ")
//    IMEI timIMEItheosoImei(@Param("soimei") String soimei);


    @Query(value = "select a.IdCTSP as idctsp,sum(a.soLuong) as slctsp" +
            " from HoaDonChiTiet a group by a.soLuong,a.TrangThai" +
            " having TrangThai=0", nativeQuery = true)
    List<DSfullSanPhamDatHangOnline> dsfullDDHcoTT0ladoixacnhan();


    @Transactional
    @Modifying
    @Query(value = "update  HoaDonChiTiet dh set  dh.trangThai=1 where dh.idHoaDon.id=:idhd")
    void updateTTdonDatHangkhiDASULytheoIDHD(@Param("idhd") UUID idhd);


//    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
//            "LEFT JOIN ChatLieu cl ON ct.chatLieu.id = cl.id " +
//            "LEFT JOIN KichCo kc ON ct.kichCo.id = kc.id " +
//            "LEFT JOIN ThuongHieu th ON ct.thuongHieu.id = th.id " +
//            "LEFT JOIN CoAo ca ON ct.coAo.id = ca.id " +
//            "LEFT JOIN MauSac ms ON ct.mauSac.id = ms.id " +
//            "where ct.trangThai=1 and" +
//            " ((:idSanPham is null or sp.id=: idSanPham) " +
//            "AND (:idChatLieu = 'null' OR cl.ten = :idChatLieu) " +
//            "AND (:idMauSac = 'null' OR ms.ten = :idMauSac) " +
//            "AND (:idThuongHieu = 'null' OR th.ten = :idThuongHieu) " +
//            "AND (:idKichCo = 'null' OR kc.ten = :idKichCo) " +
//            "AND (:idCoAo = 'null' OR ca.ten = :idCoAo))"
//
////            +"and (:giaBanMin is null and :giaBanMax is null or ct.giaBan between :giaBanMin and :giaBanMax)"
//    )
//    List<ChiTietSanPham> locctspngungban(UUID idSanPham, UUID idChatLieu, UUID idMauSac, UUID idThuongHieu, UUID idKichco, UUID idCoAo);


    @Transactional
    @Modifying
    @Query(value = "update GioHangChiTiet ghct set ghct.tinhTrang=1 where ghct.id not in  (select ghct.id from GioHangChiTiet where ghct.id=:idghct)")
    void updatelaighctve1trumotIDGH(@Param("idghct") UUID idghct);


}
