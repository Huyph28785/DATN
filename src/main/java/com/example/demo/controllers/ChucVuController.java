package com.example.demo.controllers;


import com.example.demo.models.ChucVu;
import com.example.demo.services.ChucVuService;
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
@RequestMapping("/chuc-vu")
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("chucVu") ChucVu chucVu) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("chucVu") ChucVu chucVu) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll1(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../chuc-vu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("chucVu", new ChucVu());
        model.addAttribute("batmodalthemchucvu", 0);
        model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("chucVu") ChucVu chucVu, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<ChucVu> page = chucVuService.getAll(pageable);
            model.addAttribute("listChucVu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemchucvu", 0);
            model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = chucVuService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "CV0" + sl;
        } else {
            mhd = "CV" + sl;
        }
        chucVu.setMa(mhd);
        chucVu.setNgayTao(Date.valueOf(LocalDate.now()));
        chucVu.setTinhTrang(0);
        chucVuService.add(chucVu);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemchucvu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("chucVu", chucVuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailchucvu", 0);
        model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll1(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("chucVu", chucVuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailchucvu", 0);
        model.addAttribute("contentPage", "../chuc-vu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("chucVu", chucVuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatechucvu", 0);
        model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("chucVu") ChucVu chucVu, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<ChucVu> page = chucVuService.getAll(pageable);
            model.addAttribute("listChucVu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatechucvu", 0);
            model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
            return "home/layout";
        }
        ChucVu cl = chucVuService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTinhTrang(chucVu.getTinhTrang());
        cl.setTen(chucVu.getTen());
        chucVuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatechucvu", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ChucVu cl = chucVuService.findById(id);
        cl.setTinhTrang(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        chucVuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("chucVu") ChucVu chucVu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ChucVu cl = chucVuService.findById(id);
        cl.setTinhTrang(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        chucVuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ChucVu> page = chucVuService.getAll1(pageable);
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../chuc-vu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("chucVu") ChucVu chucVu, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<ChucVu> page = chucVuService.getAll(pageable);
            model.addAttribute("listChucVu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
            return "home/layout";
        } else {
            List<ChucVu> list = chucVuService.search0(search);
            model.addAttribute("listChucVu", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../chuc-vu/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("chucVu") ChucVu chucVu, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
            Page<ChucVu> page = chucVuService.getAll1(pageable);
            model.addAttribute("listChucVu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../chuc-vu/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<ChucVu> list = chucVuService.search1(search);
            model.addAttribute("listChucVu", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../chuc-vu/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }
}
