package com.example.demo.controllers;


import com.example.demo.models.MauSac;

import com.example.demo.services.MauSacService;
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
@RequestMapping("/mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("mauSac") MauSac mauSac) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("mauSac") MauSac mauSac) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll1(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../mau-sac/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("mauSac") MauSac mauSac
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("batmodalthemmausac", 0);
        model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MauSac> page = mauSacService.getAll(pageable);
            model.addAttribute("listMauSac", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemmausac", 0);
            model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = mauSacService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "MS0" + sl;
        } else {
            mhd = "MS" + sl;
        }
        mauSac.setMa(mhd);
        mauSac.setNgayTao(Date.valueOf(LocalDate.now()));
        mauSac.setTrangThai(0);
        mauSacService.add(mauSac);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemmausac", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("mauSac") MauSac mauSac
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("mauSac", mauSacService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailmausac", 0);
        model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("mauSac") MauSac mauSac
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll1(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("mauSac", mauSacService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailmausac", 0);
        model.addAttribute("contentPage", "../mau-sac/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("mauSac") MauSac mauSac
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("mauSac", mauSacService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatemausac", 0);
        model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MauSac> page = mauSacService.getAll(pageable);
            model.addAttribute("listMauSac", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatemausac", 0);
            model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
            return "home/layout";
        }
        MauSac cl = mauSacService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(0);
        cl.setTen(mauSac.getTen());
        mauSacService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatemausac", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("mauSac") MauSac mauSac
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        MauSac cl = mauSacService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        mauSacService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("mauSac") MauSac mauSac
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        MauSac cl = mauSacService.findById(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        mauSacService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MauSac> page = mauSacService.getAll1(pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../mau-sac/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("mauSac") MauSac mauSac, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MauSac> page = mauSacService.getAll(pageable);
            model.addAttribute("listMauSac", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
            return "home/layout";
        } else {
            List<MauSac> list = mauSacService.search0(search);
            model.addAttribute("listMauSac", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../mau-sac/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("mauSac") MauSac mauSac, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<MauSac> page = mauSacService.getAll1(pageable);
            model.addAttribute("listMauSac", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../mau-sac/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<MauSac> list = mauSacService.search1(search);
            model.addAttribute("listMauSac", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../mau-sac/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }
}
