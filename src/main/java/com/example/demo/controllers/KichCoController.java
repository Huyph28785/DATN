package com.example.demo.controllers;


import com.example.demo.models.KichCo;
import com.example.demo.services.KichCoService;
import com.example.demo.services.KichCoService;
import jakarta.validation.Valid;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/kich-co")
public class KichCoController {
    @Autowired
    private KichCoService kichCoService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("kichCo") KichCo kichCo) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("kichCo") KichCo kichCo) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll1(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../kich-co/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("kichCo") KichCo kichCo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("kichCo", new KichCo());
        model.addAttribute("batmodalthemkichco", 0);
        model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("kichCo") KichCo kichCo, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<KichCo> page = kichCoService.getAll(pageable);
            model.addAttribute("listKichCo", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemkichco", 0);
            model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = kichCoService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "KC0" + sl;
        } else {
            mhd = "KC" + sl;
        }
        kichCo.setMa(mhd);
        kichCo.setNgayTao(Date.valueOf(LocalDate.now()));
        kichCo.setTrangThai(0);
        kichCoService.add(kichCo);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemkichco", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("kichCo") KichCo kichCo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("kichCo", kichCoService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailkichco", 0);
        model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("kichCo") KichCo kichCo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll1(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("kichCo", kichCoService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailkichco", 0);
        model.addAttribute("contentPage", "../kich-co/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("kichCo") KichCo kichCo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("kichCo", kichCoService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatekichco", 0);
        model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("kichCo") KichCo kichCo, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<KichCo> page = kichCoService.getAll(pageable);
            model.addAttribute("listKichCo", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatekichco", 0);
            model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
            return "home/layout";
        }
        KichCo cl = kichCoService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(0);
        cl.setTen(kichCo.getTen());
        kichCoService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatekichco", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("kichCo") KichCo kichCo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        KichCo cl = kichCoService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        kichCoService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("kichCo") KichCo kichCo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        KichCo cl = kichCoService.findById(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        kichCoService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<KichCo> page = kichCoService.getAll1(pageable);
        model.addAttribute("listKichCo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../kich-co/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("kichCo") KichCo kichCo, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<KichCo> page = kichCoService.getAll(pageable);
            model.addAttribute("listKichCo", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
            return "home/layout";
        } else {
            List<KichCo> list = kichCoService.search0(search);
            model.addAttribute("listKichCo", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../kich-co/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("kichCo") KichCo kichCo, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<KichCo> page = kichCoService.getAll1(pageable);
            model.addAttribute("listKichCo", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../kich-co/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<KichCo> list = kichCoService.search1(search);
            model.addAttribute("listKichCo", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../kich-co/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }
}
