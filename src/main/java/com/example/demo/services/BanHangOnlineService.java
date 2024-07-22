package com.example.demo.services;


import com.example.demo.dto.Top10SanPham;
import com.example.demo.models.*;
import com.example.demo.viewmodels.TongtienvsTongspchon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BanHangOnlineService {

    List<ChiTietSanPham> ctspbanhang();

    List<Top10SanPham> top10SanPhamBanChay();

    Integer soluongcon(String idctsp);

    Integer soluongdaban(String idctsp);

    List<ChiTietSanPham> locbanhang(
                                    String idMauSac,
                                    String idKichCo,
                                    String tenSP);

    List<ChiTietSanPham> ListctspTheoidsp(String idsp);

    List<GioHang> ListghTheoidkh(String idkh);

    Integer sl1ctsptronggh(UUID idgh, UUID idctsp);

    List<GioHangChiTiet> ListghctTheoidgh(UUID idgh);

    List<GioHangChiTiet> ListghctTheoIdghvsIdctsp(UUID idgh, UUID idctsp);

    TongtienvsTongspchon TongtienvsTongspchon(UUID idgh);

    void trangthaighct(Integer trangthai, UUID idgh);

    List<GioHangChiTiet> ListghTheoidghvsTT1(UUID idgh);

    List<DiaChi> Listdiachimotkhachang(UUID idkh);

    HoaDon timhdtheomahd(String mahd);

//    List<IMEI> timimeitheoidctspVSttO(UUID idctsp);

    void xoaghcttheoIDGHvsTTO(UUID idgh);

    List<HoaDon> timhoadontheoidkh(UUID idkh);

    List<HoaDonChiTiet> timhoadonchitiettheoidhd(UUID idhd);

    List<HoaDonChiTiet> listIMEItheoIDHDvsIDCTSP(UUID idhd, UUID idctsp);

    void huyhoadon(UUID idhd);

    void updateimeiTTveOtheoIDHDvsIDCTSP(UUID idhd, UUID idctsp);

    void XoahdcttheoIDHDvsIDCTSP(UUID idhd, UUID idctsp);
//    Boolean ThemSPvaoGHCT(String idkh, String idctsp);

    List<ChiTietSanPham> locbanhangcoGIATIEN(String idChatLieu,
                                             String idMauSac,
                                             String idThuongHieu,
                                             String idKichCo,
                                             String idCoAo,
                                             String tenSP,
                                             BigDecimal tienMin,
                                             BigDecimal tienMax);

    Page<HoaDon> cacDonHang(@Param("idkh") UUID idkh, Pageable pageable);


    List<HoaDon> search(UUID id, String ten);

    List<HoaDon> search0(UUID id, String ten);

    List<HoaDon> search1(UUID id, String ten);

    List<HoaDon> search2(UUID id, String ten);

    List<HoaDon> search3(UUID id, String ten);

    List<HoaDon> search8(UUID id, String ten);

    List<HoaDon> searchDangChoXuLy(UUID id, String ten);

    List<HoaDon> searchDangGiao(UUID id, String ten);

    List<HoaDon> searchGiaoThanhCong(UUID id, String ten);

//    String sotienkhidagiam(UUID idctsp);
//    String sotienkhidagiam2(UUID idctsp);
//    String sotienkhidagiam3(UUID idctsp);


    String dongiaVSsoluongXemHDCT(UUID idhd, UUID idctsp);

    List<ChiTietSanPham> timkiemTrangChu(String ten);

    String convertgiatien(BigDecimal giatien);
    String convertgiatien2(BigDecimal giatien);

    List<HoaDonChiTiet> dsDDHtheoIDHD(UUID idhd);

    List<HoaDonChiTiet> listHDCTtheoIDHD(UUID idhd);
}
