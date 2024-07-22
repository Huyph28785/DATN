package com.example.demo.controllers;


import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.ChucVu;
import com.example.demo.models.HoaDon;
import com.example.demo.models.NhanVien;
import com.example.demo.services.ChucVuService;
import com.example.demo.services.MailerService;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.NhanVienService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private MailerService mailerService;



    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue ="0" ) Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }
    @GetMapping("/loc")
    public String hienThiLoc(Model model,@RequestParam("ten1") String chucVu,@RequestParam("trangThai1") int tinhTrang,
                             @RequestParam("gioiTinh1") String gioiTinh,@RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page=nhanVienService.searchByTenChucVuAndGioiTinh(chucVu,Boolean.valueOf(gioiTinh),tinhTrang,pageable);
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";


    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll1(pageable);
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("chucVu", new ChucVu());
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("batmodalthemnv", 0);
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
//        model.addAttribute("contentPage", "../nhan-vien/view-add.jsp");

        return "home/layout";
    }
    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult bindingResult, @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemnv", 0);
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        }

        Calendar calSinh = Calendar.getInstance();
        calSinh.setTime(nhanVien.getNgaySinh());
        int namSinh = calSinh.get(Calendar.YEAR);
        Calendar cal = Calendar.getInstance();
        int age = cal.get(Calendar.YEAR) - namSinh;
        System.out.println("tuoi-" +age);
        if (age < 18) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("tbt14tuoi", "Nhân viên không đủ tuổi < 18 . Vui lòng nhập lại!");
            model.addAttribute("batmodalthemnv", 0);
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";

        }

        if (nhanVienService.existNhanVienBySDT(nhanVien.getSdt())) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("tbtrungsdt","Số điện thoại trùng!");
            model.addAttribute("batmodalthemnv", 0);
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        }
        if (nhanVienService.existNhanVienByCCCD(nhanVien.getCCCD())) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("tbtrungcccd","Số CCCD trùng!");
            model.addAttribute("batmodalthemnv", 0);
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        }
        if (nhanVienService.existNhanVienByEmail(nhanVien.getEmail())) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("tbtrungemail","Tai khoan Email trùng!");
            model.addAttribute("batmodalthemnv", 0);
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        }


        NhanVien nvien = new NhanVien();
        String randomPassword = generateRandomPassword(8);
        nvien.setMatKhau(randomPassword);

        String hashedPassword = BCrypt.hashpw(randomPassword, BCrypt.gensalt());
        nhanVien.setMatKhau(hashedPassword);
        String rawPassword = randomPassword;
        String hashedPasswords = hashedPassword; // Mật khẩu đã được mã hóa
        boolean matches = BCrypt.checkpw(rawPassword, hashedPassword);
        System.out.println(matches);
        System.out.println("MK Nè " + randomPassword.toString());
        String mhd = "";
        Integer sl = nhanVienService.findAllFullTT().size() + 1;
        if (sl < 10) {
            mhd = "NV0" + sl;
        } else {
            mhd = "NV" + sl;
        }
        nhanVien.setMa(mhd);
        nhanVien.setNgayTao(Date.valueOf(LocalDate.now()));
        nhanVien.setTinhTrang(0);
        nhanVien.setTaiKhoan(nhanVien.getEmail());
        nhanVienService.add(nhanVien);
        mailerService.queue(nhanVien.getEmail(),"Chúc mừng bạn đã đăng kí thành công!" ,"Tài khoản : " +nhanVien.getTaiKhoan() +"\n Mật khẩu: " +randomPassword);
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemnhanvien", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }



    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailnhanvien", 0);
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll1(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailnhanvien", 0);
        model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../nhan-vien/view-update.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../nhan-vien/view-update.jsp");
            return "home/layout";
        }
        Calendar calSinh = Calendar.getInstance();
        calSinh.setTime(nhanVien.getNgaySinh());
        int namSinh = calSinh.get(Calendar.YEAR);
        Calendar cal = Calendar.getInstance();
        int age = cal.get(Calendar.YEAR) - namSinh;
        System.out.println("tuoi-" +age);
        if (age < 18) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("tbt14tuoi", "Nhân viên không đủ tuổi < 18 . Vui lòng nhập lại!");
            model.addAttribute("contentPage", "../nhan-vien/view-update.jsp");
            return "home/layout";

        }

        NhanVien cl = nhanVienService.findById(id);
        nhanVien.setNgaySua(Date.valueOf(LocalDate.now()));
        nhanVien.setMa(cl.getMa());
        nhanVien.setNgayTao(cl.getNgayTao());
        nhanVien.setTaiKhoan(nhanVien.getEmail());
        nhanVien.setMatKhau(cl.getMatKhau());
        nhanVienService.update(id, nhanVien);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatenhanvien", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        NhanVien cl = nhanVienService.findById(id);
        cl.setTinhTrang(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        nhanVienService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("nhanVien") NhanVien nhanVien
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        NhanVien cl = nhanVienService.findById(id);
        cl.setTinhTrang(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        nhanVienService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll1(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll(pageable);
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        } else {
            List<NhanVien> list = nhanVienService.search0(search.trim());
            model.addAttribute("listNhanVien", list);
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("nhanVien") NhanVien nhanVien, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<NhanVien> page = nhanVienService.getAll1(pageable);
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("listNhanVien", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<NhanVien> list = nhanVienService.search1(search);
            model.addAttribute("listNhanVien", list);
            model.addAttribute("listChucVu", chucVuService.findAll());
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../nhan-vien/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/quet-qr/{ma}")
    public String qrCode(Model model, @PathVariable("ma") String scan,
                         @ModelAttribute("nhanVien") NhanVien nhanVien,@ModelAttribute("chucVu") ChucVu chucVu,
                         @RequestParam(name = "pageNum", required = false, defaultValue ="0" ) Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize
    ) {
          nhanVien = new NhanVien();

        String[] s2 = scan.split("\\|");
        if (scan.contains("|")) {
            String cccd = s2[0];
            String hoTen = s2[2];
            String ngaySinh = s2[3];
            String gioiTinh = s2[4];
            String diaChi = s2[5];
            String ngay = ngaySinh.substring(0, 2);
            String thang = ngaySinh.substring(2, 4);
            String nam = ngaySinh.substring(4, ngaySinh.length());
            String ngayThangNam = nam + "-" + thang + "-" + ngay;



            nhanVien.setHoTen(hoTen);
            nhanVien.setCCCD(cccd);
            nhanVien.setNgaySinh(Date.valueOf(ngayThangNam));
            nhanVien.setGioiTinh(gioiTinh=="Nam"?false:true);
            nhanVien.setDiaChi(diaChi);



        } else {
            System.out.println(s2);
            model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
            return "home/layout";
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<NhanVien> page = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("listChucVu", chucVuService.findAll());
        model.addAttribute("nhanVien",nhanVien);
        model.addAttribute("batmodalthemnv", 0);
        model.addAttribute("contentPage", "../nhan-vien/hien-thi.jsp");
//        model.addAttribute("contentPage", "../nhan-vien/view-add.jsp");
        return "home/layout";
        }
    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hoạt động");
        dsTrangThai.put(1, "Ngừng hoạt động");
        return dsTrangThai;
    }



}
