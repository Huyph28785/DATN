package com.example.demo.repositories;

import com.example.demo.dto.*;
import com.example.demo.models.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface  ThongKeRepository extends JpaRepository<HoaDon, UUID> {

    @Query(value = "select count(h) from HoaDon h where  h.trangThaiHoaDon = 2")
    int countHD();

    @Query(value = "select avg (h.tongTien) from HoaDon h  where h.trangThaiHoaDon = 2")
    int avgHD();

    @Query(value = "SELECT SUM(tongTien) AS doanh_thu, MONTH(NgayThanhToan) AS thang FROM " +
            "HoaDon WHERE YEAR(NgayThanhToan) = ? GROUP BY thang", nativeQuery = true)
    List<DoanhThuTheoThang> getDoanhThusInYear(int year);

    @Query(value = "Select MONTH(HoaDon.NgayThanhToan) AS thang,\n" +
            "            COUNT(DISTINCT HoaDon.id) as SLHoaDon ,\n" +
            "            SUM(HoaDonChiTiet.soLuong) as soLuongSP,\n" +
            "            SUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.SoLuong)   AS DoanhThuThucTe\n" +
            "            FROM HoaDon left join HoaDonChiTiet on  HoaDonChiTiet.IdHoaDon = HoaDon.id\n" +
            "            left join ChiTietSanPham on ChiTietSanPham.id = HoaDonChiTiet.IdCTSP\n" +
            "            where YEAR(HoaDon.NgayThanhToan) = 2024 and (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "            GROUP BY MONTH(HoaDon.NgayThanhToan)\n" +
            "            ORDER BY thang ASC", nativeQuery = true)
    List<DoanhThuTheoThang> doanhThu();

    @Query(value = " SELECT DAY(HoaDon.NgayThanhToan) AS Ngay,\n" +
            "             MONTH(HoaDon.NgayThanhToan) AS Thang,\n" +
            "             YEAR(HoaDon.NgayThanhToan) AS Nam,\n" +
            "\t\t\t COUNT(DISTINCT HoaDon.Id) as TongHD,  \n" +
            "             SUM(HoaDonChiTiet.soLuong) as soLuongSP,  \n" +
            "\t\t   SUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.SoLuong) AS DoanhThuThucTe\n" +
            "            FROM HoaDon left join HoaDonChiTiet on  HoaDonChiTiet.IdHoaDon = HoaDon.id      \n" +
            "            left join ChiTietSanPham on ChiTietSanPham.id = HoaDonChiTiet.IdCTSP\n" +
            "            where YEAR(HoaDon.NgayThanhToan) = 2024 and  (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "            and DAY(HoaDon.NgayThanhToan) = DAY(GETDATE())\n" +
            "            GROUP BY DAY(HoaDon.NgayThanhToan),\n" +
            "             MONTH(HoaDon.NgayThanhToan),\n" +
            "             YEAR(HoaDon.NgayThanhToan) ", nativeQuery = true)
    List<DoanhThuTheoThang> doanhThuNgay();


    @Query(value = " SELECT DAY(HoaDon.NgayThanhToan) AS Ngay,\n" +
            "             MONTH(HoaDon.NgayThanhToan) AS Thang,\n" +
            "             YEAR(HoaDon.NgayThanhToan) AS Nam,\n" +
            "\t\t\t COUNT(DISTINCT HoaDon.Id) as TongHD,  \n" +
            "             SUM(HoaDonChiTiet.soLuong) as soLuongSP  \n" +
            "            FROM HoaDon left join HoaDonChiTiet on  HoaDonChiTiet.IdHoaDon = HoaDon.id      \n" +
            "            left join ChiTietSanPham on ChiTietSanPham.id = HoaDonChiTiet.IdCTSP\n" +
            "            where YEAR(HoaDon.NgayThanhToan) = 2024 and  (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "            and DAY(HoaDon.NgayThanhToan) = DAY(GETDATE())\n" +
            "            GROUP BY DAY(HoaDon.NgayThanhToan),\n" +
            "             MONTH(HoaDon.NgayThanhToan),\n" +
            "             YEAR(HoaDon.NgayThanhToan) ", nativeQuery = true)
    List<DoanhThuTheoThang> doanhThuHoaDon();

    @Query(value = " \t\t\t\t\t\t SELECT\n" +
            "\t\t\t\t\t\t SUM(HoaDonChiTiet.soLuong) as soLuongSP,\n" +
            "\t\t\t\t\t\t DAY(HoaDon.NgayThanhToan) AS Ngay,\n" +
            "                         MONTH(HoaDon.NgayThanhToan) AS Thang,\n" +
            "                         YEAR(HoaDon.NgayThanhToan) AS Nam\n" +
            "\t\t\t\t\t\t FROM HoaDonChiTiet LEFT JOIN HoaDon ON HoaDonChiTiet.IdHoaDon = HoaDon.Id\n" +
            "\t\t\t\t\t\t LEFT JOIN ChiTietSanPham ON ChiTietSanPham.Id = HoaDonChiTiet.IdCTSP\n" +
            "\t\t\t\t\t\t WHERE YEAR(HoaDon.NgayThanhToan) = 2024 AND (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "\t\t\t\t\t\t AND DAY(HoaDon.NgayThanhToan) = DAY(GETDATE())\n" +
            "\t\t\t\t\t\t GROUP BY  DAY(HoaDon.NgayThanhToan),\n" +
            "                         MONTH(HoaDon.NgayThanhToan),\n" +
            "                         YEAR(HoaDon.NgayThanhToan)", nativeQuery = true)
    List<DoanhThuTheoThang> doanhThuSP();

    @Query(value = "SELECT\n" +
            "\t\t\t MONTH(HoaDon.NgayThanhToan) AS thang,\n" +
            "\t\t\t COUNT(DISTINCT HoaDon.Id) as TongHD,  \n" +
            "             SUM(HoaDonChiTiet.soLuong) as soLuongSP,  \n" +
            "\t\t   SUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.SoLuong)   AS DoanhThuThucTe\n" +
            "            FROM HoaDon left join HoaDonChiTiet on  HoaDonChiTiet.IdHoaDon = HoaDon.id      \n" +
            "            left join ChiTietSanPham on ChiTietSanPham.id = HoaDonChiTiet.IdCTSP\n" +
            "            where YEAR(HoaDon.NgayThanhToan) = ?1 and  (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "            GROUP BY \n" +
            "             MONTH(HoaDon.NgayThanhToan)\n" +
            "\t\t\t ORDER BY thang ASC", nativeQuery = true)
    List<DoanhThuTheoThang> loctheonam(Integer Nam);

    @Query(value = "SELECT YEAR(NgayThanhToan) AS Nam FROM HoaDon GROUP BY YEAR(NgayThanhToan)", nativeQuery = true)
    List<DoanhThuTheoThang> selectedYear();


    @Query(value = "SELECT \n" +
            "\t\t\t\t\t\tSanPham.Ten AS TenSanPham,\n" +
            "\t\t\t\t\t\tChiTietSanPham.SoLuongTon as SoLuongTon,\n" +
            "\t\t\t\t\t\tCOUNT(ChiTietSanPham.Id) AS SoLuongSP,\n" +
            "\t\t\t\t\t\tSUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong)  AS DoanhThu\n" +
            "\t\t\t\t\t\tFROM HoaDon JOIN HoaDonChiTiet ON HoaDon.Id = HoaDonChiTiet.IdHoaDon\n" +
            "\t\t\t\t\t\tLEFT JOIN ChiTietSanPham ON ChiTietSanPham.Id = HoaDonChiTiet.IdCTSP\n" +
            "\t\t\t\t\t\tLEFT JOIN SanPham ON SanPham.Id = ChiTietSanPham.IdSanPham\n" +
            "\t\t\t\t\t\tWHERE ChiTietSanPham.TrangThai = 0 and  (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "\t\t\t\t\t\tGROUP BY SanPham.Ten,ChiTietSanPham.SoLuongTon", nativeQuery = true)
    List<DoanhThuSanPham> doanhThuSanPham();

    @Query(value = "SELECT \n" +
            "\t\t\t\t\t\tSanPham.Ten AS TenSanPham,\n" +
            "\t\t\t\t\t\tChiTietSanPham.SoLuongTon as SoLuongTon,\n" +
            "\t\t\t\t\t\tCOUNT(ChiTietSanPham.Id) AS SoLuongSP,\n" +
            "\t\t\t\t\t\tThuongHieu.Ten,\n" +
            "\t\t\t\t\t\tSUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong)  AS DoanhThu\n" +
            "\t\t\t\t\t\tFROM HoaDonChiTiet LEFT JOIN HoaDon ON HoaDonChiTiet.IdHoaDon = HoaDon.Id\n" +
            "\t\t\t\t\t\tLEFT JOIN ChiTietSanPham ON ChiTietSanPham.Id = HoaDonChiTiet.IdCTSP\n" +
            "\t\t\t\t\t\tLEFT JOIN SanPham ON SanPham.Id = ChiTietSanPham.IdSanPham\n" +
            "\t\t\t\t\t\tLEFT JOIN ThuongHieu ON ThuongHieu.Id = ChiTietSanPham.IdThuongHieu\n" +
            "\t\t\t\t\t\tWHERE ChiTietSanPham.TrangThai = 0 AND ThuongHieu.Ten LIKE %:ten%\n and  (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)" +
            "\t\t\t\t\t\tGROUP BY SanPham.Ten,ChiTietSanPham.SoLuongTon,ThuongHieu.Ten", nativeQuery = true)
    List<DoanhThuSanPham> locHang(String ten);

    @Query(value = "SELECT\n" +
            "\t\t\tThuongHieu.Ten AS TenHang\n" +
            "\t\t\tFROM ChiTietSanPham \n" +
            "\t\t\tLEFT JOIN SanPham ON ChiTietSanPham.IdSanPham = SanPham.Id\n" +
            "\t\t\tLEFT JOIN ThuongHieu ON ThuongHieu.Id = ChiTietSanPham.IdThuongHieu \n" +
            "\t\t\tLEFT JOIN HoaDonChiTiet ON ChiTietSanPham.Id = HoaDonChiTiet.IdCTSP\n" +
            "\t\t\tLEFT JOIN HoaDon ON HoaDon.Id = HoaDonChiTiet.IdHoaDon\n" +
            "\t\t\tGROUP BY ThuongHieu.Ten", nativeQuery = true)
    List<DoanhThuSanPham> selectedHang();

    @Query(value = "SELECT\n" +
            "            ThuongHieu.Ten AS TenHang,\n" +
            "            COALESCE(SUM(HoaDonChiTiet.DonGia), 0) AS DoanhThuCu,\n" +
            "            SUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.SoLuong)   AS DoanhThuThucTe\n" +
            "            FROM ChiTietSanPham\n" +
            "\t\t\tLEFT JOIN SanPham ON SanPham.Id = ChiTietSanPham.IdSanPham\n" +
            "            LEFT JOIN ThuongHieu ON ThuongHieu.Id = ChiTietSanPham.IdThuongHieu\n" +
            "            LEFT JOIN HoaDonChiTiet ON ChiTietSanPham.Id = HoaDonChiTiet.IdCTSP\n" +
            "\t\t\tLEFT JOIN HoaDon ON HoaDon.Id = HoaDonChiTiet.IdHoaDon\n" +
            "\t\t WHERE ChiTietSanPham.TrangThai = 0 and (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "            GROUP BY ThuongHieu.Ten", nativeQuery = true)
    List<DoanhThuHang> doanhThuHang();

    @Query(value = "\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\tThuongHieu.ten AS TenHang,\n" +
            "\t\t\t\t\t\tCOALESCE(SUM(HoaDonChiTiet.DonGia),0) AS DoanhThuCu,\n" +
            "\t\t\t\t\t\tSUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong) AS DoanhThuThucTe\n" +
            "\t\t\t\t\t\tFROM HoaDon \n" +
            "\t\t\t\t\t\tLEFT JOIN HoaDonChiTiet ON HoaDon.Id = HoaDonChiTiet.IdHoaDon\n" +
            "\t\t\t\t\t\tLEFT JOIN ChiTietSanPham ON ChiTietSanPham.Id = HoaDonChiTiet.IdCTSP\n" +
            "\t\t\t\t\t\tLEFT JOIN SanPham ON SanPham.Id = ChiTietSanPham.IdSanPham\n" +
            "\t\t\t\t\t\tLEFT JOIN ThuongHieu ON ThuongHieu.Id = ChiTietSanPham.IdThuongHieu\n" +
            "\t\t\t\t\t\tWHERE HoaDon.NgayThanhToan BETWEEN :startDate AND :endDate and  (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n " +
            "\t\t\t\t\t\tGROUP BY ThuongHieu.Ten", nativeQuery = true)
    List<DoanhThuHang> locdoanhThuHang(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT \n" +
            "\t\t\t\t\t\tNhanVien.Ten AS TenNhanVien,\n" +
            "\t\t\t\t\t\tSUM(HoaDonChiTiet.soLuong) AS SoLuongSP,\n" +
            "\t\t\t\t\t\tCOUNT(HoaDonChiTiet.Id) AS TongHD,\n" +
            "\t\t\t\t\t\tCOALESCE(SUM(HoaDon.PhiShip),0) AS PhiShip,\n" +
            "\t\t\t\t\t\tSUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong) AS DoanhThu,\n" +
            "\t\t\t\t\t\tSUM(HoaDon.TongTien) AS 'DoanhThuCu'\n" +
            "\t\t\t\t\t\tFROM HoaDon\n" +
            "\t\t\t\t\t\tLEFT JOIN NhanVien ON HoaDon.IdNhanVien = NhanVien.Id\n" +
            "\t\t\t\t\t\tLEFT JOIN HoaDonChiTiet ON HoaDonChiTiet.IdHoaDon = HoaDon.Id\n" +
            "\t\t\t\t\t\tWHERE (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "\t\t\t\t\t\tGROUP BY NhanVien.Ten", nativeQuery = true)
    List<DoanhThuNhanVien> doanhThuNhanVien();

    @Query(value = "SELECT \n" +
            "\t\t\t\t\t\tNhanVien.Ten AS TenNhanVien,\n" +
            "\t\t\t\t\t\tSUM(HoaDonChiTiet.soLuong) AS SoLuongSP,\n" +
            "\t\t\t\t\t\tCOUNT(HoaDonChiTiet.Id) AS TongHD,\n" +
            "\t\t\t\t\t\tCOALESCE(SUM(HoaDon.PhiShip),0) AS PhiShip,\n" +
            "\t\t\t\t\t\tSUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong) AS DoanhThu,\n" +
            "\t\t\t\t\t\tSUM(HoaDon.TongTien) AS 'DoanhThuCu'\n" +
            "\t\t\t\t\t\tFROM HoaDon\n" +
            "\t\t\t\t\t\tLEFT JOIN NhanVien ON HoaDon.IdNhanVien = NhanVien.Id\n" +
            "\t\t\t\t\t\tLEFT JOIN HoaDonChiTiet ON HoaDonChiTiet.IdHoaDon = HoaDon.Id\n" +
            "\t\t\t\t\t\tWHERE (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "\t\t\t\t\t\tAND HoaDon.NgayThanhToan BETWEEN :startDate AND :endDate\n" +
            "\t\t\t\t\t\tGROUP BY NhanVien.Ten", nativeQuery = true)
    List<DoanhThuNhanVien> locDoanhThuNhanVien(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "        \t SELECT CASE WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 25 THEN N'Dưới 25'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 25 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 35 THEN N'Từ 25 đến 35 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 35 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 45 THEN N'Từ 35 đến 45 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 45 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 55 THEN N'Từ 45 đến 55 tuổi'\n" +
            "                            ELSE N'Trên 55 tuổi'\n" +
            "                            END AS Tuoi,\n" +
            "                            COALESCE(SUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong),0) AS DoanhThu\n" +
            "                            FROM ChiTietSanPham\n" +
            "                            LEFT JOIN HoaDonChiTiet on HoaDonChiTiet.IdCTSP = ChiTietSanPham.Id\n" +
            "                            LEFT JOIN HoaDon ON HoaDon.Id = HoaDonChiTiet.IdHoaDon                     \n" +
            "                            LEFT JOIN SanPham ON ChiTietSanPham.IdSanPham = SanPham.Id\n" +
            "                            LEFT JOIN KhachHang ON HoaDon.IdKhachHang = KhachHang.Id           \n" +
            "\t\t\t\t\t\t                WHERE (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "                            GROUP BY CASE WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 25 THEN N'Dưới 25'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 25 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 35 THEN N'Từ 25 đến 35 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 35 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 45 THEN N'Từ 35 đến 45 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 45 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 55 THEN N'Từ 45 đến 55 tuổi'\n" +
            "                            ELSE N'Trên 55 tuổi'\n" +
            "                            END", nativeQuery = true)
    List<DoanhThuKhachHang> doanhThuKhachHang();

    @Query(value = "SELECT KhachHang.GioiTinh as GioiTinh,\n" +
            "                        SUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong)   AS DoanhThu\n" +
            "                        FROM HoaDon\n" +
            "                        left join HoaDonChiTiet on HoaDonChiTiet.IdHoaDon = HoaDon.Id\n" +
            "                        LEFT JOIN KhachHang ON HoaDon.IdKhachHang = KhachHang.Id                     \n" +
            "                        WHERE (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "                        Group by KhachHang.GioiTinh", nativeQuery = true)
    List<DoanhThuKhachHang> doanhThuKhachHangGioiTinh();

    @Query(value = "      SELECT CASE WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 25 THEN N'Dưới 25'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 25 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 35 THEN N'Từ 25 đến 35 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 35 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 45 THEN N'Từ 35 đến 45 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 45 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 55 THEN N'Từ 45 đến 55 tuổi'\n" +
            "                            ELSE N'Trên 55 tuổi'\n" +
            "                            END AS Tuoi,\n" +
            "                            SUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong)  AS DoanhThu\n" +
            "                            FROM ChiTietSanPham\n" +
            "                            LEFT JOIN HoaDonChiTiet on HoaDonChiTiet.IdCTSP = ChiTietSanPham.Id\n" +
            "                            LEFT JOIN HoaDon ON HoaDon.Id = HoaDonChiTiet.IdHoaDon                     \n" +
            "                            LEFT JOIN SanPham ON ChiTietSanPham.IdSanPham = SanPham.Id\n" +
            "                            LEFT JOIN KhachHang ON HoaDon.IdKhachHang = KhachHang.Id  \n" +
            "                            WHERE HoaDon.NgayThanhToan BETWEEN :startDate AND :endDate and  (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "                            GROUP BY CASE WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 25 THEN N'Dưới 25'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 25 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 35 THEN N'Từ 25 đến 35 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 35 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 45 THEN N'Từ 35 đến 45 tuổi'\n" +
            "                            WHEN YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) >= 45 AND YEAR(GETDATE()) - YEAR(KhachHang.NgaySinh) < 55 THEN N'Từ 45 đến 55 tuổi'\n" +
            "                            ELSE N'Trên 55 tuổi'\n" +
            "                            END", nativeQuery = true)
    List<DoanhThuKhachHang> locDoanhThuKhachHang(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "\tSELECT KhachHang.GioiTinh as GioiTinh,\n" +
            "                   \tSUM(HoaDonChiTiet.DonGia*HoaDonChiTiet.soLuong) AS DoanhThu\n" +
            "                        FROM HoaDon\n" +
            "                        left join HoaDonChiTiet on HoaDonChiTiet.IdHoaDon = HoaDon.Id\n" +
            "                        LEFT JOIN KhachHang ON HoaDon.IdKhachHang = KhachHang.Id                   \n" +
            "                        WHERE (HoaDon.TrangThaiHoaDon = 4 or HoaDon.TrangThaiHoaDon = 5)\n" +
            "                        and HoaDon.NgayThanhToan BETWEEN :startDate AND :endDate\n" +
            "                        Group by KhachHang.GioiTinh", nativeQuery = true)
    List<DoanhThuKhachHang> locDoanhThuKhachHangGioiTinh(@Param("startDate") Date startDate, @Param("endDate") Date endDate);



}