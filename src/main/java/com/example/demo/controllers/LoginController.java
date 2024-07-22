package com.example.demo.controllers;

import com.example.demo.config.UserInfoUserDetails;
import com.example.demo.models.GioHang;
import com.example.demo.models.KhachHang;
import com.example.demo.models.NhanVien;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.services.GioHangService;
import com.example.demo.services.KhachHangService;
import com.example.demo.services.MailerService;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.impl.DataIntermediateService;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    DataIntermediateService service;

    @Autowired
    private MailerService mailer;
    @Autowired
    private GioHangService gioHangService;

    private String un;
    private NhanVien nhanVien2;//du lieu khi dang nhap la nhan vien
    private KhachHang khachHang2;//du lieu khi dang nhap la khach hang

    @GetMapping("/login")
    public String login(@ModelAttribute("khachHang") KhachHang khachHang) {
        return "login/login";

    }

    @GetMapping("/thong-tin-ca-nhan")
    public String thongTinND(Model model, @ModelAttribute("us") NhanVien nhanVien) {
        NhanVien nv = nhanVienService.findById(SecurityUtil.getId().getId());
        model.addAttribute("us", nv);

        model.addAttribute("contentPage", "../thong-tin/thong-tin-ca-nhan.jsp");
        return "home/layout";
    }

    @GetMapping("/403")
    private String accessDenied(Model model) {
        model.addAttribute("contentPage", "../home/error.jsp");
        return "home/layout";
    }

    @GetMapping("/doi-mat-khau")
    public String doiMatKhau(Model model) {
        NhanVien nv = nhanVienService.findById(SecurityUtil.getId().getId());
        model.addAttribute("us", nv);
        model.addAttribute("contentPage", "../thong-tin/doi-mat-khau.jsp");
        return "home/layout";
    }

    @GetMapping("/dang-ky-tai-khoan")
    public String dangKyTaiKhoan(Model model, @ModelAttribute("khachHang") KhachHang khachHang) {
        return "login/dang-ki";
    }

    @GetMapping("/quen-mat-khau")
    public String quenMatKhau(Model model, @ModelAttribute("khachHang") KhachHang khachHang) {
        return "login/quen-mat-khau";
    }

    @PostMapping("/login/dang-ky-tai-khoan")
    public String dangKy(Model model,@Valid @ModelAttribute("khachHang") KhachHang KhachHang,
                         BindingResult bindingResult) {

        Calendar calSinh = Calendar.getInstance();
        calSinh.setTime(KhachHang.getNgaySinh());
        int namSinh = calSinh.get(Calendar.YEAR);
        Calendar cal = Calendar.getInstance();
        int age = cal.get(Calendar.YEAR) - namSinh;
        System.out.println("tuoi-" +age);

        if (khachHangService.existKhachHangByEmail(KhachHang.getEmail())) {
            model.addAttribute("tbtrungemail", "Email trùng!");
            return "login/dang-ki";
        }
        if (khachHangService.existKhachHangBySDT(KhachHang.getSoDienThoai())) {
            model.addAttribute("tbtrungsdt", "Số điện thoại trùng!");
            return "login/dang-ki";

        }
        if (age < 15) {
            model.addAttribute("tbt14tuoi", "Khách hàng không đủ tuổi < 15 . Vui lòng nhập lại!");
            return "login/dang-ki";
        }

        KhachHang khh=new KhachHang();
        String randomPassword=generateRandomPassword(8);
        khh.setMatKhau(randomPassword);
        String hashedPassword = BCrypt.hashpw(randomPassword, BCrypt.gensalt());
        KhachHang.setMatKhau(hashedPassword);
        System.out.println("MK Nè " + randomPassword.toString());

        String mhd = "";
        Integer sl = khachHangService.findAllFullTT().size() + 1;
        if (sl < 10) {
            mhd = "KH0" + sl;
        } else {
            mhd = "KH" + sl;
        }
        KhachHang.setMa(mhd);
        KhachHang.setNgayTao(Date.valueOf(LocalDate.now()));
        KhachHang.setTrangThai(0);
        KhachHang.setTaiKhoan(KhachHang.getEmail());
        KhachHang.setMatKhau(hashedPassword);
        khachHangService.add(KhachHang);

        //      them gh
        khachHangService.findAll();
        String mghkh="";
        Integer slgh = (gioHangService.findAll().size() + 1);
        if(slgh<10){
            mghkh = "MGH0" + slgh;
        }else {
            mghkh = "MGH" + slgh;
        }
        GioHang ghkh=new GioHang();
        ghkh.setMa(mghkh);
        for (KhachHang kh11: khachHangService.findAll()){
            if(kh11.getMa().equals(mhd)){
                ghkh.setKhachHang(kh11);
                break;
            }
        }
        gioHangService.add(ghkh);
// het them gh
        KhachHang khachHang1 = khachHangService.findById(KhachHang.getId());
        model.addAttribute("thongBaoThanhCong", "Bạn đã đăng kí tài khoản thành công");
        mailer.queue(khachHang1.getEmail(), "Bạn đã đăng kí tài khoản thành công", "TK: " + khachHang1.getTaiKhoan() + "\n Mật khẩu: " +khh.getMatKhau());
        return "login/login";
    }

    @PostMapping("/login/quen-mat-khau")
    public String khoiPhuc(Model model, @RequestParam("taiKhoan-quen") String taiKhoan, @RequestParam("email-quen") String email) {
        KhachHang khachHang = khachHangService.quenMatKhau(taiKhoan, email);
        if (khachHang == null) {
            model.addAttribute("thongBao", "Không có thông tin tài khoản này");
            return "login/quen-mat-khau";
        } else {
            String randomPW = generateRandomPassword(8);
            String matKhau = randomPW;
            String hashedPassword = BCrypt.hashpw(matKhau, BCrypt.gensalt());
            khachHang.setMatKhau(hashedPassword);
            khachHang.setNgaySua(Date.valueOf(LocalDate.now()));
            khachHangService.update(khachHang.getId(), khachHang);
            KhachHang kh = khachHangService.findById(khachHang.getId());
            mailer.queue(kh.getEmail(), "Bạn đã khôi phục tài khoản thành công", "Tài khoản: " + kh.getTaiKhoan() + "\nMật khẩu mới: " + matKhau);
            model.addAttribute("thongBao", "Mật khẩu mới đã được gửi vào email của bạn");
            return "login/quen-mat-khau";
        }
    }

    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

    @GetMapping("/infor")
    public String ggetin() {

        UserInfoUserDetails userDetails = SecurityUtil.getId();
        System.out.println("Ho Ten: " + userDetails.getHoTen());

        return null;


    }


    @PostMapping("/login/update-thong-tin/{id}")
    public String updateThongTin(Model model, @ModelAttribute("us") @Valid NhanVien nhanVien,
                                 BindingResult bindingResult, @PathVariable("id") UUID id
            , @RequestParam("checkanh1") String checkanh1, @RequestParam("anh1s") MultipartFile anh1) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../thong-tin/thong-tin-ca-nhan.jsp");
            return "home/layout";
        }


        NhanVien nv = nhanVienService.findById(id);
        System.out.println(nv);
        nhanVien.setChucVu(nv.getChucVu());
        nhanVien.setNgaySua(Date.valueOf(LocalDate.now()));
        nhanVienService.update(id, nhanVien);
        return "redirect:/thong-tin-ca-nhan";
    }

    @PostMapping("/login/doi-mat-khau/{id}")
    public String doiMatKhau(Model model, @ModelAttribute("us") @Valid NhanVien nhanVien, @PathVariable("id") UUID id,
                             @RequestParam("mat-khau-cu") String oldp,
                             @RequestParam("mat-khau-moi") String newp,
                             @RequestParam("xac-nhan-mat-khau") String conp,
                             @RequestParam("checkanh1") String checkanh1, @RequestParam("anh1s") MultipartFile anh1) throws IOException {
        NhanVien nv = nhanVienService.findById(id);
        boolean matches = BCrypt.checkpw(oldp, nv.getMatKhau());
        if (matches == false) {
            model.addAttribute("thongBao1", "Sai mật khẩu");
            model.addAttribute("contentPage", "../thong-tin/doi-mat-khau.jsp");
            return "home/layout";
        } else {
            if (!newp.equalsIgnoreCase(conp)) {
                model.addAttribute("thongBao3", "Mật khẩu mới chưa khớp với mật khẩu xác nhận");
                model.addAttribute("contentPage", "../thong-tin/doi-mat-khau.jsp");
                return "home/layout";
            } else {
                nhanVien.setChucVu(nv.getChucVu());
                nhanVien.setNgaySua(Date.valueOf(LocalDate.now()));
                String matKhau = newp;
                String hashedPassword = BCrypt.hashpw(matKhau, BCrypt.gensalt());
                nhanVien.setMatKhau(hashedPassword);
                nhanVienService.update(id, nhanVien);
                NhanVien newnv = nhanVienService.findById(id);
                mailer.queue(newnv.getEmail(), "Bạn đã thay đổi mật khẩu thành công", "Tài khoản: " + newnv.getTaiKhoan() + "\nMật khẩu mới: " + matKhau);
                return "redirect:/login";
            }
        }
    }

    @GetMapping("/in4")
    public String in4() {
        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("id: " + userDetails.getId());
        return null;
    }

//    @GetMapping("/oauth2/callback")
//    public String handleOAuth2Callback(@AuthenticationPrincipal OAuth2User oauth2User, Principal principal) {
//        // Trích xuất thông tin người dùng từ OAuth2
//
//        String email = oauth2User.getAttribute("email");
//        String name = oauth2User.getAttribute("name");
//        String idToken = oauth2User.getAttribute("id_token");
//        System.out.println(principal.getName());
//
//        System.out.println(email);
//        System.out.println(name);
//
//        // Kiểm tra xem người dùng đã tồn tại trong cơ sở dữ liệu hay chưa
//
//
//        if (khachHangRepository.getKhachHangByTaiKhoan(email).isEmpty()) {
//            // Người dùng chưa tồn tại, tạo người dùng mới
////            GoogleId token= oauth2User.get;
//
//            List<KhachHang> khachHangList = khachHangRepository.findAll();
//            String email1 = oauth2User.getAttribute("email");
//            String name1 = oauth2User.getAttribute("name");
//            KhachHang newUser = new KhachHang();
//            newUser.setMa("KH" + khachHangList.size() + 1);
//            newUser.setEmail(email1);
//            newUser.setTaiKhoan(email1);
//            newUser.setHoTen(name1);
//            newUser.setSdt(" ");
//            newUser.setDiem(0);
//            newUser.setGioiTinh(true);
//            newUser.setTaiKhoan(" ");
//            newUser.setNgaySinh(Date.valueOf("1999-1-1"));
//            newUser.setTinhTrang(0);
//            newUser.setNgayTao(Date.valueOf(LocalDate.now()));
//            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//
//
//            String randomPassword = generateRandomPassword(8);
//
//            String hashedPassword = BCrypt.hashpw(randomPassword, BCrypt.gensalt());
//            newUser.setMatKhau(hashedPassword);
//
//            // Lưu người dùng mới vào cơ sở dữ liệu
//            khachHangRepository.save(newUser);
//        }
//
//        return "redirect:/ban-hang-online/hien-thi"; // Chuyển hướng sau khi đăng nhập thành công
//    }
}
