package com.example.demo.repositories;

import com.example.demo.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {

    @Query("select hd from HoaDon hd order by hd.ma desc")
    List<HoaDon> hoaDonAll();

    @Query("select hd from HoaDon hd left join KhachHang kh on hd.khachHang.id=kh.id where kh.id=:id")
    List<HoaDon> hoaDonKH(UUID id);

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 1 order by hd.ngayTao desc")
    List<HoaDon> hoaDonChoXacNhan();

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 2 order by hd.ngayTao desc")
    List<HoaDon> hoaDonDaXacNhan();

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 3 order by hd.ngayTao desc")
    List<HoaDon> hoaDonChoGiaoHang();

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 4 order by hd.ngayTao desc")
    List<HoaDon> hoaDonDangVanChuyen();

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 5 order by hd.ngayTao desc")
    List<HoaDon> hoaDonHuy();

    @Query("select hd from HoaDon hd where hd.trangThaiGiaoHang = 6 order by hd.ngayTao desc")
    List<HoaDon> hoaDonHoanThanh();

    @Query("select hd from HoaDon hd where hd.trangThaiHoaDon = 4 order by hd.ngayTao desc")
    List<HoaDon> hoaDonDaThanhToan();



    @Query("select hd from HoaDon hd where hd.trangThaiHoaDon=0 and hd.loaiHoaDon=0")
    List<HoaDon> hoaDonCho();

    @Query("select hd from HoaDon hd")
    List<HoaDon> findAllHoaDon();

    @Query("select hd from HoaDon hd left join NhanVien nv on hd.nhanVien.id=nv.id " +
            "left join KhachHang kh on hd.khachHang.id=kh.id " +
            "where (:idKH is null or kh.id=:idKH)" +
            "and (:idNV is null or nv.id=:idNV) " +
            "and (:loai is null or hd.loaiHoaDon= :loai) " +
            "and (:giaoHang is null or hd.trangThaiGiaoHang= :giaoHang) " +
            "and (:hoaDon is null or hd.trangThaiHoaDon= :hoaDon) " +
            "and ((:startDate is null or :endDate is null) or hd.ngayTao >= coalesce(:startDate, hd.ngayTao) " +
            "and hd.ngayTao <= coalesce(hd.ngayTao,:endDate))" +
            "and ((:startSua is null or :endSua is null) or hd.ngaySua>= coalesce(:startSua,hd.ngaySua)" +
            "and hd.ngaySua <= coalesce(hd.ngaySua,:endSua))")
    List<HoaDon> locTong(UUID idKH, UUID idNV, Integer loai, Integer giaoHang, Integer hoaDon,
                         @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                         @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                         @RequestParam(value = "startSua", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startSua,
                         @RequestParam(value = "endSua", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endSua);
}
