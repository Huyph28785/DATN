package com.example.demo.controllers;


import com.example.demo.dto.SanPhamCustom;
import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.services.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Controller
public class SanPhamController {

    @Autowired
    ChiTietSanPhamService service;
    @Autowired
    ChiTietSanPhamRepo repo;
    @Autowired
    ThuongHieuRepository deGiayRepo;
    @Autowired
    ChatLieuRepository chatLieuRepo;
    @Autowired
    KichCoRepository kichCoRepo;
    @Autowired
    CoAoRepository loaiGiayRepo;
    @Autowired
    MauSacRepository mauSacReponsitories;
    @Autowired
    SanPhamRepository sanPhamRepo;
    @Autowired
    SanPhamService sanPhamService;
//
    @Autowired
    ThuongHieuService deGiayService;
    @Autowired
    ChatLieuService chatLieuService;
    @Autowired
    KichCoService kichCoService;
    @Autowired
    CoAoService loaiGiayService;
    @Autowired
    MauSacService mauSacService;

    @ModelAttribute("listDeGiay")
    List<ThuongHieu> listDeGiay() {
        return deGiayRepo.findAll();
    }

    @ModelAttribute("listChatLieu")
    List<ChatLieu> listChatLieu() {
        return chatLieuRepo.findAll();
    }

    @ModelAttribute("listKichCo")
    List<KichCo> listKichCo() {
        return kichCoRepo.findAll();
    }

    @ModelAttribute("listMau")
    List<MauSac> listMauSac() {
        return mauSacReponsitories.findAll();
    }

    @ModelAttribute("listLoaiGiay")
    List<CoAo> listLoaiGiay() {
        return loaiGiayRepo.findAll();
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Boolean, String> getDsGioiTinh() {
        Map<Boolean, String> dsGT = new HashMap<>();
        dsGT.put(true, "Nam");
        dsGT.put(false, "Nữ");
        return dsGT;
    }

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Còn kinh doanh");
        dsTrangThai.put(1, "Ngừng kinh doanh");
        return dsTrangThai;
    }

    @Getter
    @Setter
    public static class SearchForm {
        String keyword = "";
    }


    //san pham ngung hd
    @GetMapping("/san-pham/loc")
    public String hienThiLoc(Model model,@RequestParam("trangThai1") int trangThai,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("sanPham") SanPham sanPham) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPhamCustom> page= sanPhamService.locTT(trangThai,pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";


    }
    @GetMapping("/san-pham/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("sanPham") SanPham sanPham) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPham> page = sanPhamService.getAll1(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }
    @GetMapping("/san-pham/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        SanPham cl = sanPhamService.getOne(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        sanPhamService.udpateSanPham(cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPham> page = sanPhamService.getAll1(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }
    @PostMapping("/san-pham/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("sanPham") SanPham sanPham, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<SanPham> page = sanPhamService.getAll1(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<SanPhamCustom> list = sanPhamService.search1(search);
            model.addAttribute("listSanPham", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../san-pham/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }
    }
    //san pham
    @GetMapping("/san-pham/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("sanPham") SanPham sanPham) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }


    @GetMapping("/san-pham/view-add")
    public String viewAdd(Model model, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("batmodalthemsanpham", 0);
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/san-pham/add")
    public String add(Model model, @Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemsanpham", 0);
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        }
        if (sanPhamService.existSanPhamByTen(sanPham.getTen())) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("tbtrungten","Tên sản phẩm trùng!");
            model.addAttribute("batmodalthemsanpham", 0);
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = sanPhamService.findAll().size() + 1;
        if (sl < 9) {
            mhd = "SP0" + sl;
        } else {
            mhd = "SP" + sl;
        }
        sanPham.setMa(mhd);
        sanPham.setNgayTao(Date.valueOf(LocalDate.now()));
        sanPham.setTrangThai(0);
        sanPhamService.addSanPham(sanPham);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemsanpham", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/san-pham/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("sanPham", sanPhamService.getOne(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailsanpham", 0);
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }


    @GetMapping("/san-pham/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanPham") SanPham sanPham
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("sanPham", sanPhamService.getOne(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatesanpham", 0);
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/san-pham/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatesanpham", 0);
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        }
        SanPham cl = sanPhamService.getOne(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(sanPham.getTrangThai());
        cl.setTen(sanPham.getTen());
        sanPhamService.udpateSanPham(cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatesanpham", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/san-pham/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("sanPham") SanPham sanPham, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<SanPhamCustom> page = sanPhamService.findAllSP(pageable);
            model.addAttribute("listSanPham", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        } else {
            List<SanPhamCustom> list = sanPhamService.search0(search);
            model.addAttribute("listSanPham", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../san-pham/hien-thi.jsp");
            return "home/layout";
        }

    }



    //add modal co ao
    @RequestMapping("/san-pham/loai-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> save(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("lg") CoAo coAo, BindingResult result) {
        Boolean hasE = result.hasErrors();
        List<CoAo> list = loaiGiayRepo.findAll();
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());
        if (result.hasErrors()) {
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            return response;
        }
//        if (loaiGiayService.findByMa(coAo.getMa()) != null) {
//            result.rejectValue("ma", "duplicate4", "Lỗi! Mã không được trùng");
//            response.put("status", "error4");
//            response.put("errors", getErrors(result));
//            response.put("field", "ma");
//            return response;
//        }
        if (loaiGiayService.findByTen(coAo.getTen()) != null) {
            result.rejectValue("ten", "duplicate4", "Lỗi! Tên không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }
        String mhd = "";
        Integer sl = loaiGiayService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "TH0" + sl;
        } else {
            mhd = "TH" + sl;
        }
        coAo.setMa(mhd);
        loaiGiayRepo.save(coAo);
        response.put("status", "success");
        return response;

    }

    @RequestMapping("/san-pham/kich-co/add/{id}")
    @ResponseBody
    public Map<String, Object> addKC(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("kichco") KichCo kichCo, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());

        if (result.hasErrors()) {
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            return response;
        }

//        if (kichCoService.findByMa(kichCo.getMa()) != null) {
//            result.rejectValue("ma", "duplicate3", "Lỗi! Mã không được trùng");
//            response.put("status", "error3");
//            response.put("errors", getErrors(result));
//            response.put("field", "ma");
//            return response;
//        }
        if (kichCoService.findByTen(kichCo.getTen()) != null) {
            result.rejectValue("ten", "duplicate3", "Lỗi! Size không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }
        String mhd = "";
        Integer sl = kichCoService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "KC0" + sl;
        } else {
            mhd = "KC" + sl;
        }
        kichCo.setMa(mhd);
        kichCoService.add(kichCo);
        response.put("status", "success");
        return response;
    }

    @PostMapping("/san-pham/mau-sac/add/{id}")
    @ResponseBody
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("ms") MauSac ms, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());
        if (result.hasErrors()) {
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            return response;
        }

//        if (mauSacReponsitories.findMauSacByMa(ms.getMa()) != null) {
//            result.rejectValue("ma", "duplicate2", "Lỗi! Mã không được trùng");
//            response.put("status", "error2");
//            response.put("errors", getErrors(result));
//            response.put("field", "ma");
//            return response;
//        }
        if (mauSacReponsitories.findMauSacByTen(ms.getTen()) != null) {
            result.rejectValue("ten", "duplicate2", "Lỗi! Tên màu không được trùng");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }
        String mhd = "";
        Integer sl = mauSacService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "MS0" + sl;
        } else {
            mhd = "MS" + sl;
        }
        ms.setMa(mhd);
        mauSacReponsitories.save(ms);
        response.put("status", "success");
        return response;
    }


    @RequestMapping("/san-pham/chat-lieu/add/{id}")
    @ResponseBody
    public Map<String, Object> store(Model model, @PathVariable("id") UUID id,
                                     @Valid @ModelAttribute("vm") ChatLieu cl,
                                     BindingResult result
    ) {
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());
        ChatLieu chatLieu = chatLieuService.findByMa(cl.getMa());

        if (result.hasErrors()) {
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            return response;
        }

//        if (chatLieuService.findByMa(cl.getMa()) != null) {
//            result.rejectValue("ma", "duplicate1", "Lỗi! Mã không được trùng");
//            response.put("status", "error1");
//            response.put("errors", getErrors(result));
//            response.put("field", "ma");
//            return response;
//        }
        if (chatLieuService.findByTen(cl.getTen()) != null) {
            result.rejectValue("ten", "duplicate1", "Lỗi! Tên chất liệu không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }
        String mhd = "";
        Integer sl = chatLieuService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "CL0" + sl;
        } else {
            mhd = "CL" + sl;
        }
        cl.setMa(mhd);
        chatLieuRepo.save(cl);
        response.put("status", "success");
        return response;
    }

// thuong hieu
    @PostMapping("/san-pham/de-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> add(@PathVariable("id") UUID id, @Valid @ModelAttribute("degiay") ThuongHieu thuongHieu, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham = sanPhamService.getOne(id);

        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("errors", getErrors(result));
            return response;
        }
//        if (deGiayService.findByMa(thuongHieu.getMa()) != null) {
//            result.rejectValue("ma", "duplicate", "Lỗi! Mã không được trùng");
//            response.put("status", "error");
//            response.put("errors", getErrors(result));
//            response.put("field", "ma");
//            return response;
//        }
        if (deGiayService.findByTen(thuongHieu.getTen()) != null) {
            result.rejectValue("ten", "duplicate", "Lỗi! Loại đế không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }
        String mhd = "";
        Integer sl = deGiayService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "TH0" + sl;
        } else {
            mhd = "TH" + sl;
        }
        thuongHieu.setMa(mhd);
        deGiayRepo.save(thuongHieu);
        response.put("status", "success");
        return response;
    }

    private List<String> getErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        return errors;
    }

}
