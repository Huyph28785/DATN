package com.example.demo.controllers;


import com.example.demo.models.CoAo;
import com.example.demo.services.CoAoService;
import com.example.demo.services.CoAoService;
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
@RequestMapping("/co-ao")
public class CoAoController {
    @Autowired
    private CoAoService coAoService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("coAo") CoAo coAo) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("coAo") CoAo coAo) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll1(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../co-ao/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("coAo") CoAo coAo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("coAo", new CoAo());
        model.addAttribute("batmodalthemcoao", 0);
        model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("coAo") CoAo coAo, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<CoAo> page = coAoService.getAll(pageable);
            model.addAttribute("listCoAo", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemcoao", 0);
            model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = coAoService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "CA0" + sl;
        } else {
            mhd = "CA" + sl;
        }
        coAo.setMa(mhd);
        coAo.setNgayTao(Date.valueOf(LocalDate.now()));
        coAo.setTrangThai(0);
        coAoService.add(coAo);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemcoao", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("coAo") CoAo coAo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("coAo", coAoService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailcoao", 0);
        model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("coAo") CoAo coAo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll1(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("coAo", coAoService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailcoao", 0);
        model.addAttribute("contentPage", "../co-ao/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("coAo") CoAo coAo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("coAo", coAoService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatecoao", 0);
        model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("coAo") CoAo coAo, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<CoAo> page = coAoService.getAll(pageable);
            model.addAttribute("listCoAo", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatecoao", 0);
            model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
            return "home/layout";
        }
        CoAo cl = coAoService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(0);
        cl.setTen(coAo.getTen());
        coAoService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatecoao", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("coAo") CoAo coAo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        CoAo cl = coAoService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        coAoService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("coAo") CoAo coAo
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        CoAo cl = coAoService.findById(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        coAoService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CoAo> page = coAoService.getAll1(pageable);
        model.addAttribute("listCoAo", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../co-ao/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("coAo") CoAo coAo, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<CoAo> page = coAoService.getAll(pageable);
            model.addAttribute("listCoAo", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
            return "home/layout";
        } else {
            List<CoAo> list = coAoService.search0(search);
            model.addAttribute("listCoAo", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../co-ao/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("coAo") CoAo coAo, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<CoAo> page = coAoService.getAll1(pageable);
            model.addAttribute("listCoAo", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../co-ao/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<CoAo> list = coAoService.search1(search);
            model.addAttribute("listCoAo", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../co-ao/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }
}
