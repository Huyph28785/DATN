package com.example.demo.controllers;


import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.models.PhieuGiamGia;
import com.example.demo.repositories.PhieuGiamGiaRepository;
import com.example.demo.services.HoaDonChiTietSerice;
import com.example.demo.services.HoaDonSerice;
import com.example.demo.services.PhieuGiamGiaService;
import com.example.demo.services.PhieuGiamGiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/phieu-giam-gia")
public class PhieuGiamGiaController {
    @Autowired
    private PhieuGiamGiaService phieuService;
    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;
    @Autowired
    private HoaDonSerice hoaDonSerice;
    @Autowired
    private HoaDonChiTietSerice hoaDonChiTietSerice;

    @Scheduled(fixedRate = 1000)
    public void kiemtrangayhientaiVSkhoangtimegiamgia() {
        for (PhieuGiamGia km : phieuGiamGiaRepository.findAll()) {
            String batdau = km.getNgayBatDau();
            String ketthuc = km.getNgayketThuc();

// Chuyển đổi chuỗi abc thành đối tượng LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime batdauDateTime = LocalDateTime.parse(batdau, formatter);
            LocalDateTime ketthucDateTime = LocalDateTime.parse(ketthuc, formatter);

// Lấy thời gian hiện tại và định dạng nó
            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);


            // Kiểm tra xem currentDateTime có nằm trong khoảng [batdauDateTime, ketthucDateTime] không
            if ((currentDateTime.isAfter(batdauDateTime) && currentDateTime.isBefore(ketthucDateTime))) {
                km.setTrangThai(0);
                km.setNgaySua(Date.valueOf(LocalDate.now()));
                phieuService.update(km.getId(), km);
            }
            else if (batdauDateTime.isAfter(currentDateTime)) {
                km.setTrangThai(3);
                km.setNgaySua(Date.valueOf(LocalDate.now()));
                phieuService.update(km.getId(), km);
            }else {
//                km.setSoLuong(km.getSoLuong() + 1);
                km.setNgaySua(Date.valueOf(LocalDate.now()));
                km.setTrangThai(1);
                phieuService.update(km.getId(), km);
                List<HoaDon> lhd = hoaDonSerice.hoaDonCho();
                for (HoaDon hd : lhd
                ) {
                    if (hd.getPhieuGiamGia() != null) {
                        hd.setPhieuGiamGia(null);
                        hd.setNgaySua(Date.valueOf(LocalDate.now()));
                        BigDecimal giaGoc = BigDecimal.ZERO;
                        for (HoaDonChiTiet hdctt : hoaDonChiTietSerice.hoaDonChiTietAll(hd.getId())
                        ) {
                            giaGoc = giaGoc.add(BigDecimal.valueOf(hdctt.getDonGia().intValue() * hdctt.getSoLuong()));

                        }
                        hd.setTongTien(giaGoc);
                        hoaDonSerice.update(hd.getId(), hd);
                    }
                }
            }
        }
    }

    public String dinhangCHUOIsangJSP(String ngaycan) {
        // Input date and time string
//        String inputDateTime = "29-11-2023 17:09:00";
        String inputDateTime = ngaycan;

        // Define the input format
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Parse the input string
        LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);

        // Define the output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Format the LocalDateTime to the desired output format
        return dateTime.format(outputFormatter);

    }

    public String dinhdangngaytujsp(String inputString) {


        // Tạo đối tượng LocalDateTime từ chuỗi
        LocalDateTime dateTime = LocalDateTime.parse(inputString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Định dạng lại theo định dạng mong muốn
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));


    }

    public boolean isNgayKetThucAfterNgayBatDau(String ngayBatDau, String ngayKetThuc) {
        // Tạo đối tượng LocalDateTime từ chuỗi
        LocalDateTime ngayBT = LocalDateTime.parse(ngayBatDau, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime ngayKT = LocalDateTime.parse(ngayKetThuc, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Định dạng lại theo định dạng mong muốn
        String ngayBT1 = ngayBT.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        String ngayKT1 = ngayKT.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime batDauDateTime = LocalDateTime.parse(ngayBT1, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayKT1, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(batDauDateTime);
    }

    public boolean isNgayKetThucAfterNgayBatDauupdate(String ngayBatDau, String ngayKetThuc) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime batDauDateTime = LocalDateTime.parse(ngayBatDau, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayKetThuc, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(batDauDateTime);
    }

    public boolean isNgayKetThucAfterNgayHienTai(String ngayKetThuc) {
        // Tạo đối tượng LocalDateTime từ chuỗi

        LocalDateTime ngayKT = LocalDateTime.parse(ngayKetThuc, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Định dạng lại theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String ngayHT1 = currentDateTime.format(formatter);
        String ngayKT1 = ngayKT.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));


        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime hienTaiDateTime = LocalDateTime.parse(ngayHT1, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayKT1, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(hienTaiDateTime);
    }

    public boolean isNgayKetThucAfterNgayHienTaiupdate(String ngayKetThuc) {
        // Tạo đối tượng LocalDateTime từ chuỗi


        // Định dạng lại theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String ngayHT1 = currentDateTime.format(formatter);


        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime hienTaiDateTime = LocalDateTime.parse(ngayHT1, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayKetThuc, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(hienTaiDateTime);
    }
    public boolean isBatDauAfterNgayHienTai(String ngayBatDau) {
        // Tạo đối tượng LocalDateTime từ chuỗi


        // Định dạng lại theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String ngayHT1 = currentDateTime.format(formatter);


        // Chuyển đổi chuỗi ngayBatDau và ngayKetThuc thành đối tượng LocalDateTime
        LocalDateTime hienTaiDateTime = LocalDateTime.parse(ngayHT1, formatter);
        LocalDateTime ketThucDateTime = LocalDateTime.parse(ngayBatDau, formatter);

        // Kiểm tra xem ngayKetThuc có lớn hơn ngayBatDau không
        return ketThucDateTime.isAfter(hienTaiDateTime);
    }

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia) {
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia) {
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia) {
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("phieuGiamGia", new PhieuGiamGia());
        model.addAttribute("batmodalthemphieu", 0);
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia,
                      BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("listPhieuGiamGia", phieuService.getAll());
            model.addAttribute("batmodalthemphieu", 0);
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }
        if (isNgayKetThucAfterNgayBatDau(phieuGiamGia.getNgayBatDau(), phieuGiamGia.getNgayketThuc()) == false) {

            model.addAttribute("listPhieuGiamGia", phieuService.getAll());
            model.addAttribute("thongBaoPhieu", "Ngày kết thúc phải sau ngày bắt đầu");
            model.addAttribute("batmodalthemphieu", 0);
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }
        if (isNgayKetThucAfterNgayHienTai(phieuGiamGia.getNgayketThuc()) == false) {

            model.addAttribute("listPhieuGiamGia", phieuService.getAll());
            model.addAttribute("thongBaoPhieu", "Ngày kết thúc phải sau ngày hiện tại");
            model.addAttribute("batmodalthemphieu", 0);
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }

        String mhd = "";
        Integer sl = phieuService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "PGG0" + sl;
        } else {
            mhd = "PGG" + sl;
        }
        phieuGiamGia.setMa(mhd);
        phieuGiamGia.setHinhThucGiam(true);
        phieuGiamGia.setNgayTao(Date.valueOf(LocalDate.now()));
        phieuGiamGia.setNgayBatDau(dinhdangngaytujsp(phieuGiamGia.getNgayBatDau()));
        phieuGiamGia.setNgayketThuc(dinhdangngaytujsp(phieuGiamGia.getNgayketThuc()));

        if(isBatDauAfterNgayHienTai(phieuGiamGia.getNgayBatDau()) == true){
            phieuGiamGia.setTrangThai(3);
        }else{
            phieuGiamGia.setTrangThai(0);
        }
        phieuService.add(phieuGiamGia);
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("batmodalthemphieu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia) {
        model.addAttribute("phieuGiamGia", phieuService.findById(id));
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("batmodaldetailphieu", 0);
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia
    ) {
        String NBTCC = phieuService.findById(id).getNgayBatDau();
        String NKTCC = phieuService.findById(id).getNgayketThuc();

        model.addAttribute("NBTCC", dinhangCHUOIsangJSP(NBTCC));
        model.addAttribute("NKTCC", dinhangCHUOIsangJSP(NKTCC));
        model.addAttribute("phieuGiamGia", phieuService.findById(id));
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("batmodalupdatephieu", 0);
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            if (phieuGiamGia.getNgayBatDau().isEmpty() == true && phieuGiamGia.getNgayketThuc().isEmpty() == false) {
                model.addAttribute("NKTCC", dinhangCHUOIsangJSP(phieuGiamGia.getNgayketThuc()));
            } else if (phieuGiamGia.getNgayBatDau().isEmpty() == false && phieuGiamGia.getNgayketThuc().isEmpty() == true) {
                model.addAttribute("NBTCC", dinhangCHUOIsangJSP(phieuGiamGia.getNgayBatDau()));
            } else if (phieuGiamGia.getNgayBatDau().isEmpty() == false && phieuGiamGia.getNgayketThuc().isEmpty() == false) {
                model.addAttribute("NBTCC", dinhangCHUOIsangJSP(phieuGiamGia.getNgayBatDau()));
                model.addAttribute("NKTCC", dinhangCHUOIsangJSP(phieuGiamGia.getNgayketThuc()));
            } else {
            }
            model.addAttribute("listPhieuGiamGia", phieuService.getAll());
            model.addAttribute("batmodalupdatephieu", 0);
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }
        if (isNgayKetThucAfterNgayBatDauupdate(phieuGiamGia.getNgayBatDau(), phieuGiamGia.getNgayketThuc()) == false) {

            model.addAttribute("listPhieuGiamGia", phieuService.getAll());
            model.addAttribute("thongBaoPhieu", "Ngày kết thúc phải sau ngày bắt đầu");
            model.addAttribute("batmodalupdatephieu", 0);
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }
        if (isNgayKetThucAfterNgayHienTaiupdate(phieuGiamGia.getNgayketThuc()) == false) {

            model.addAttribute("listPhieuGiamGia", phieuService.getAll());
            model.addAttribute("batmodalupdatephieu", 0);
            model.addAttribute("thongBaoPhieu", "Ngày kết thúc phải sau ngày hiện tại");
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime batdauDateTime = LocalDateTime.parse(phieuGiamGia.getNgayBatDau(), formatter);
        LocalDateTime ketthucDateTime = LocalDateTime.parse(phieuGiamGia.getNgayketThuc(), formatter);
        phieuGiamGia.setNgaySua(Date.valueOf(LocalDate.now()));
        if (batdauDateTime.isAfter(currentDateTime)) {
            phieuGiamGia.setTrangThai(3);
        }
        else if(currentDateTime.isBefore(ketthucDateTime)){
            phieuGiamGia.setTrangThai(0);
        }
        else {
            phieuGiamGia.setTrangThai(1);
        }
        phieuService.update(id, phieuGiamGia);
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("batmodalupdatephieu", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia
    ) {
        PhieuGiamGia cl = phieuService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        phieuService.update(id, cl);
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia
    ) {
        PhieuGiamGia cl = phieuService.findById(id);
        if(isBatDauAfterNgayHienTai(cl.getNgayBatDau()) == true){
            phieuGiamGia.setTrangThai(3);
        }else{
            cl.setTrangThai(0);
        }

        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        phieuService.update(id, cl);
        model.addAttribute("listPhieuGiamGia", phieuService.getAll());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia, @RequestParam("search") String search) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            model.addAttribute("listPhieuGiamGia", phieuService.getAll());
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        } else {
            List<PhieuGiamGia> list = phieuService.search0(search);
            model.addAttribute("listPhieuGiamGia", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia, @RequestParam("search") String search) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            model.addAttribute("listPhieuGiamGia", phieuService.getAll());
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<PhieuGiamGia> list = phieuService.search1(search);
            model.addAttribute("listPhieuGiamGia", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/loc")
    public String loc(Model model, @ModelAttribute("phieuGiamGia") PhieuGiamGia phieuGiamGia
                    , @RequestParam(value = "startDate", required = false) String startDate,
                      @RequestParam(value = "endDate", required = false) String endDate,
                      @RequestParam(value = "trangThaiPhieu", required = false) Integer trangThaiPhieu){
        List<PhieuGiamGia> list=new ArrayList<>();
        if(startDate.isEmpty() && endDate.isEmpty()){
            list = phieuService.loc(trangThaiPhieu,null,null);
            model.addAttribute("listPhieuGiamGia", list);
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }else if(endDate.isEmpty()){
            list = phieuService.loc(trangThaiPhieu,Date.valueOf(startDate),Date.valueOf(LocalDate.now()));
            model.addAttribute("listPhieuGiamGia", list);
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }else if(startDate.isEmpty()){
            list = phieuService.loc(trangThaiPhieu,Date.valueOf(LocalDate.now()),Date.valueOf(endDate));
            model.addAttribute("listPhieuGiamGia", list);
            model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
            model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
            return "home/layout";
        }else{
            LocalDate start = Date.valueOf(startDate).toLocalDate();
            LocalDate end = Date.valueOf(endDate).toLocalDate();
            int check = start.compareTo(end);
            System.out.println(check);
            if (check > 0) {
                model.addAttribute("listPhieuGiamGia", phieuService.getAll());
                model.addAttribute("thongBao", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
                return "home/layout";
            } else {
                list = phieuService.loc(trangThaiPhieu,Date.valueOf(startDate),Date.valueOf(endDate));
                model.addAttribute("listPhieuGiamGia", list);
                model.addAttribute("thongBaoThanhCong", "Lọc dữ liệu thành công");
                model.addAttribute("contentPage", "../phieu-giam-gia/hien-thi.jsp");
                return "home/layout";
            }
        }
    }
}
