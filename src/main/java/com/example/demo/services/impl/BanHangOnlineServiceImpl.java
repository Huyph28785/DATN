package com.example.demo.services.impl;


import com.example.demo.dto.Top10SanPham;
import com.example.demo.models.*;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.services.BanHangOnlineService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.GioHangChiTietService;
import com.example.demo.viewmodels.TongtienvsTongspchon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BanHangOnlineServiceImpl implements BanHangOnlineService {
    @Autowired
    BanHangOnLinerepository banHangOnLinerepository;
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    GioHangChiTietService gioHangChiTietService;



    @Override
    public List<ChiTietSanPham> ctspbanhang() {
        return banHangOnLinerepository.ctspbanhang();
    }

    @Override
    public List<Top10SanPham> top10SanPhamBanChay() {
        return banHangOnLinerepository.top10SanPhamBanChay();
    }

    @Override
    public Integer soluongcon(String idctsp) {
        return banHangOnLinerepository.soluongcon(UUID.fromString(idctsp));
    }

    @Override
    public Integer soluongdaban(String idctsp) {
        return banHangOnLinerepository.soluongdaban(UUID.fromString(idctsp));
    }



    @Override
    public List<ChiTietSanPham> locbanhang(
                                           String idMauSac,
                                           String idKichCo,
                                           String tenSP
    ) {
        return banHangOnLinerepository.locbanhang(
                idMauSac,
                idKichCo,
                tenSP);
    }

    @Override
    public List<ChiTietSanPham> ListctspTheoidsp(String idsp) {
        return banHangOnLinerepository.ListctspTheoidsp(UUID.fromString(idsp));
    }

    @Override
    public List<GioHang> ListghTheoidkh(String idkh) {
        return banHangOnLinerepository.ListghTheoidkh(UUID.fromString(idkh));
    }

    @Override
    public Integer sl1ctsptronggh(UUID idgh, UUID idctsp) {
        return banHangOnLinerepository.sl1ctsptronggh(idgh, idctsp);
    }

    @Override
    public List<GioHangChiTiet> ListghctTheoidgh(UUID idgh) {
        return banHangOnLinerepository.ListghctTheoidgh(idgh);
    }

    @Override
    public List<GioHangChiTiet> ListghctTheoIdghvsIdctsp(UUID idgh, UUID idctsp) {
        return banHangOnLinerepository.ListghctTheoIdghvsIdctsp(idgh, idctsp);
    }

    @Override
    public TongtienvsTongspchon TongtienvsTongspchon(UUID idgh) {
        return banHangOnLinerepository.TongtienvsTongspchon(idgh);
    }

    @Override
    public void trangthaighct(Integer trangthai, UUID idgh) {
        banHangOnLinerepository.trangthaighct(trangthai, idgh);
    }

    @Override
    public List<GioHangChiTiet> ListghTheoidghvsTT1(UUID idgh) {
        return banHangOnLinerepository.ListghTheoidghvsTT1(idgh);
    }

    @Override
    public List<DiaChi> Listdiachimotkhachang(UUID idkh) {
        return banHangOnLinerepository.Listdiachimotkhachang(idkh);
    }

    @Override
    public HoaDon timhdtheomahd(String mahd) {
        return banHangOnLinerepository.timhdtheomahd(mahd);
    }

//    @Override
//    public List<IMEI> timimeitheoidctspVSttO(UUID idctsp) {
//        return banHangOnLinerepository.timimeitheoidctspVSttO(idctsp);
//    }

    @Override
    public void xoaghcttheoIDGHvsTTO(UUID idgh) {
        banHangOnLinerepository.xoaghcttheoIDGHvsTTO(idgh);
    }

    @Override
    public List<HoaDon> timhoadontheoidkh(UUID idkh) {
        return banHangOnLinerepository.timhoadontheoidkh(idkh);
    }

    @Override
    public List<HoaDonChiTiet> timhoadonchitiettheoidhd(UUID idhd) {
        return banHangOnLinerepository.timhoadonchitiettheoidhd(idhd);
    }

    @Override
    public List<HoaDonChiTiet> listIMEItheoIDHDvsIDCTSP(UUID idhd, UUID idctsp) {
        return banHangOnLinerepository.listIMEItheoIDHDvsIDCTSP(idhd, idctsp);
    }

    @Override
    public void huyhoadon(UUID idhd) {
        banHangOnLinerepository.huyhoadon(idhd);
        banHangOnLinerepository.huyhoadonchitiet(idhd);
    }

    @Override
    public void updateimeiTTveOtheoIDHDvsIDCTSP(UUID idhd, UUID idctsp) {

    }


    @Override
    public void XoahdcttheoIDHDvsIDCTSP(UUID idhd, UUID idctsp) {
        banHangOnLinerepository.XoahdcttheoIDHDvsIDCTSP(idhd, idctsp);
    }

    @Override
    public List<ChiTietSanPham> locbanhangcoGIATIEN(String idChatLieu,
                                                    String idMauSac,
                                                    String idThuongHieu,
                                                    String idKichCo,
                                                    String idCoAo,
                                                    String tenSP,
                                                    BigDecimal tienMin,
                                                    BigDecimal tienMax) {
        return banHangOnLinerepository.locbanhangcoGIATIEN(
                idChatLieu,
                idMauSac,
                idThuongHieu,
                idKichCo,
                idCoAo,
                tenSP,
                tienMin,
                tienMax);
    }


    @Override
    public Page<HoaDon> cacDonHang(UUID idkh, Pageable pageable) {
        return banHangOnLinerepository.cacDonHang(idkh, pageable);
    }


    @Override
    public List<HoaDon> search(UUID id, String ten) {
        return banHangOnLinerepository.search(id, ten);
    }

    @Override
    public List<HoaDon> search0(UUID id, String ten) {
        return banHangOnLinerepository.searchDH0(id, ten);
    }

    @Override
    public List<HoaDon> search1(UUID id, String ten) {
        return banHangOnLinerepository.searchDH1(id, ten);
    }

    @Override
    public List<HoaDon> search2(UUID id, String ten) {
        return banHangOnLinerepository.searchDH2(id, ten);
    }

    @Override
    public List<HoaDon> search3(UUID id, String ten) {
        return banHangOnLinerepository.searchDH3(id, ten);
    }

    @Override
    public List<HoaDon> search8(UUID id, String ten) {
        return banHangOnLinerepository.searchDH8(id, ten);
    }

    @Override
    public List<HoaDon> searchDangChoXuLy(UUID id, String ten) {
        return banHangOnLinerepository.searchChoXuLy(id, ten);
    }

    @Override
    public List<HoaDon> searchDangGiao(UUID id, String ten) {
        return banHangOnLinerepository.searchDangGiao(id, ten);
    }

    @Override
    public List<HoaDon> searchGiaoThanhCong(UUID id, String ten) {
        return banHangOnLinerepository.searchDHThanhCong(id, ten);
    }



    @Override
    public String dongiaVSsoluongXemHDCT(UUID idhd, UUID idctsp) {
        Long sl = Long.valueOf(String.valueOf(banHangOnLinerepository.listIMEItheoIDHDvsIDCTSP(idhd, idctsp).size()));
        Long dongia = Long.valueOf(String.valueOf(banHangOnLinerepository.listIMEItheoIDHDvsIDCTSP(idhd, idctsp).get(0).getDonGia()));
        long number = sl * dongia;
        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        // Format the number
        return decimalFormat.format(number);
    }

    @Override
    public List<ChiTietSanPham> timkiemTrangChu(String ten) {
        List<ChiTietSanPham> list = banHangOnLinerepository.timkiemTrangChu(ten);
        List<ChiTietSanPham> list1 = new ArrayList<>();
        for (ChiTietSanPham ct : list) {
            if (banHangOnLinerepository.soluongcon(ct.getId()) > 0) {
                list1.add(ct);
            }
        }
        return list1;
    }

    @Override
    public String convertgiatien(BigDecimal giatien) {
        if (giatien == null) {
            return "Chưa có";
        }
        // Input number
        long number = Long.valueOf(String.valueOf(giatien));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);

    }

    @Override
    public String convertgiatien2(BigDecimal giatien) {
        if(giatien==null){
            return "Chưa có";
        }
        // Input number
        long number = Long.valueOf(String.valueOf(giatien));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return  decimalFormat.format(number).replaceAll("[,]", ".");

    }

    @Override
    public List<HoaDonChiTiet> dsDDHtheoIDHD(UUID idhd) {
        return banHangOnLinerepository.dsDDHtheoIDHD(idhd);
    }

    @Override
    public List<HoaDonChiTiet> listHDCTtheoIDHD(UUID idhd) {
        return  banHangOnLinerepository.listHDCTtheoIDHD(idhd);
    }
}
