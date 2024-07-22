package com.example.demo.controllers;


import com.example.demo.models.ThuongHieu;
import com.example.demo.services.ThuongHieuService;
import com.example.demo.services.ThuongHieuService;
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
@RequestMapping("/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll1(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thuongHieu", new ThuongHieu());
        model.addAttribute("batmodalthemthuonghieu", 0);
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("thuongHieu") ThuongHieu thuongHieu, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
            model.addAttribute("listThuongHieu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemthuonghieu", 0);
            model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = thuongHieuService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "TH0" + sl;
        } else {
            mhd = "TH" + sl;
        }
        thuongHieu.setMa(mhd);
        thuongHieu.setNgayTao(Date.valueOf(LocalDate.now()));
        thuongHieu.setTrangThai(0);
        thuongHieuService.add(thuongHieu);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemthuonghieu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("thuongHieu", thuongHieuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailthuonghieu", 0);
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll1(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("thuongHieu", thuongHieuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailthuonghieu", 0);
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("thuongHieu", thuongHieuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatethuonghieu", 0);
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("thuongHieu") ThuongHieu thuongHieu, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
            model.addAttribute("listThuongHieu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatethuonghieu", 0);
            model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
            return "home/layout";
        }
        ThuongHieu cl = thuongHieuService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(0);
        cl.setTen(thuongHieu.getTen());
        thuongHieuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatethuonghieu", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        ThuongHieu cl = thuongHieuService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        thuongHieuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        ThuongHieu cl = thuongHieuService.findById(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        thuongHieuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ThuongHieu> page = thuongHieuService.getAll1(pageable);
        model.addAttribute("listThuongHieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../thuong-hieu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<ThuongHieu> page = thuongHieuService.getAll(pageable);
            model.addAttribute("listThuongHieu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
            return "home/layout";
        } else {
            List<ThuongHieu> list = thuongHieuService.search0(search);
            model.addAttribute("listThuongHieu", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../thuong-hieu/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("thuongHieu") ThuongHieu thuongHieu, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<ThuongHieu> page = thuongHieuService.getAll1(pageable);
            model.addAttribute("listThuongHieu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../thuong-hieu/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<ThuongHieu> list = thuongHieuService.search1(search);
            model.addAttribute("listThuongHieu", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../thuong-hieu/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }
}
