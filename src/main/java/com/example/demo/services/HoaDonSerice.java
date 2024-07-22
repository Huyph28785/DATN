package com.example.demo.services;

import com.example.demo.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface HoaDonSerice {

    List<HoaDon> hoaDonKH(UUID id);

    List<HoaDon> hoaDonAll();

    HoaDon findById(UUID id);

    List<HoaDon> hoaDonHuy();

    List<HoaDon> hoaDonChoXacNhan();

    List<HoaDon> hoaDonDaXacNhan();

    List<HoaDon> hoaDonChoGiaoHang();

    List<HoaDon> hoaDonDangVanChuyen();

    List<HoaDon> hoaDonHoanThanh();

    List<HoaDon> hoaDonDaThanhToan();

    List<HoaDon> hoaDonCho();

    HoaDon add(HoaDon hoaDon);

    HoaDon update(UUID id, HoaDon hoaDon);

    List<HoaDon> findAll();

//     Page<HoaDon> hoaDonDaGiaoHang(Pageable pageable);

//     Page<HoaDon> hoaDonDaThanhToan(Pageable pageable);

    //     Page<HoaDon> hoaDonChoThanhToan(Pageable pageable);
    ResponseEntity<byte[]> generatePdfDonTaiQuay(UUID hoaDonId);

    List<HoaDon> locTong(UUID idKH, UUID idNV, Integer loai, Integer giaoHang, Integer hoaDon, Date startDate, Date endDate, Date startSua, Date endSua);
}
