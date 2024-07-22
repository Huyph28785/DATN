package com.example.demo.controllers;


import com.example.demo.models.DiaChi;
import com.example.demo.services.DiaChiService;
import com.example.demo.services.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/dia-chi")
public class DiaChiController {
    @Autowired
    private DiaChiService DiaChiService;

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("DiaChi") DiaChi diaChi){
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll(pageable);
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../diaChi/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("DiaChi") DiaChi diaChi){
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll1(pageable);
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../diaChi/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("diaChi") DiaChi DiaChi
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll(pageable);
        model.addAttribute("listKhachHang",khachHangService.findAll());
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("DiaChi", new DiaChi());
        model.addAttribute("contentPage", "../diaChi/dia-chi-add.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("DiaChi") DiaChi DiaChi, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listKhachHang",khachHangService.findAll());
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<DiaChi> page = DiaChiService.getAll(pageable);
            model.addAttribute("listDiaChi", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../diaChi/dia-chi-add.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = DiaChiService.findAllFullTT().size() + 1;
        if (sl < 10) {
            mhd = "MDC0" + sl;
        } else {
            mhd = "MDC" + sl;
        }
        DiaChi.setMa(mhd);
        DiaChi.setNgayTao(Date.valueOf(LocalDate.now()));
        DiaChi.setTrangThai(0);
        DiaChiService.add(DiaChi);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll(pageable);
        model.addAttribute("listKhachHang",khachHangService.findAll());
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemdiachi", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../diaChi/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("DiaChi") DiaChi DiaChi
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll(pageable);
        model.addAttribute("listKhachHang",khachHangService.findAll());
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("DiaChi", DiaChiService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetaildiachi", 0);
        model.addAttribute("contentPage", "../diaChi/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("diaChi") DiaChi DiaChi
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll1(pageable);
        model.addAttribute("listKhachHang",khachHangService.findAll());
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("DiaChi", DiaChiService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetaildiachi", 0);
        model.addAttribute("contentPage", "../diaChi/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("diaChi") DiaChi DiaChi
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll(pageable);
        model.addAttribute("listKhachHang",khachHangService.findAll());
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("diaChi", DiaChiService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../diaChi/dia-chi-update.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("DiaChi") DiaChi DiaChi, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<DiaChi> page = DiaChiService.getAll(pageable);
            model.addAttribute("listKhachHang",khachHangService.findAll());
            model.addAttribute("listDiaChi", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatediachi", 0);
            model.addAttribute("contentPage", "../diaChi/hien-thi.jsp");
            return "home/layout";
        }
        DiaChi cl = DiaChiService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(DiaChi.getTrangThai());
        cl.setSoDiaChi(DiaChi.getSoDiaChi());
        cl.setMa(DiaChi.getMa());
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setThanhPho(DiaChi.getThanhPho());
        cl.setQuan(DiaChi.getQuan());
        cl.setPhuong(DiaChi.getPhuong());
//        cl.setSoDienThoai(DiaChi.getSoDienThoai());
        DiaChiService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll(pageable);
        model.addAttribute("listKhachHang",khachHangService.findAll());
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatediachi", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../diaChi/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("DiaChi") DiaChi DiaChi
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        DiaChi cl = DiaChiService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        DiaChiService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll(pageable);
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../diaChi/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("DiaChi") DiaChi DiaChi
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        DiaChi cl = DiaChiService.findById(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        DiaChiService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DiaChi> page = DiaChiService.getAll1(pageable);
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../diaChi/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("DiaChi") DiaChi DiaChi, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<DiaChi> page = DiaChiService.getAll(pageable);
            model.addAttribute("listDiaChi", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../diaChi/hien-thi.jsp");
            return "home/layout";
        } else {
            List<DiaChi> list = DiaChiService.search0(search);
            model.addAttribute("listDiaChi", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../diaChi/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("DiaChi") DiaChi DiaChi, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<DiaChi> page = DiaChiService.getAll1(pageable);
            model.addAttribute("listDiaChi", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../diaChi/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<DiaChi> list = DiaChiService.search1(search);
            model.addAttribute("listDiaChi", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../diaChi/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }
}
