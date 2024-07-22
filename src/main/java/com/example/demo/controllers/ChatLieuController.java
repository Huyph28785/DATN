package com.example.demo.controllers;


import com.example.demo.models.ChatLieu;
import com.example.demo.services.ChatLieuService;
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
@RequestMapping("/chat-lieu")
public class ChatLieuController {
    @Autowired
    private ChatLieuService chatLieuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiNgungHoatDong(Model model, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll1(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("contentPage", "../chat-lieu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("chatLieu") ChatLieu chatLieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("batmodalthemchatlieu", 0);
        model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<ChatLieu> page = chatLieuService.getAll(pageable);
            model.addAttribute("listChatLieu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalthemchatlieu", 0);
            model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
            return "home/layout";
        }
        String mhd = "";
        Integer sl = chatLieuService.findAllFullTT().size() + 1;
        if (sl < 9) {
            mhd = "CL0" + sl;
        } else {
            mhd = "CL" + sl;
        }
        chatLieu.setMa(mhd);
        chatLieu.setNgayTao(Date.valueOf(LocalDate.now()));
        chatLieu.setTrangThai(0);
        chatLieuService.add(chatLieu);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalthemchatlieu", 1);
        model.addAttribute("thongBaoThanhCong", "Thêm dữ liệu thành công");
        model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("chatLieu") ChatLieu chatLieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("chatLieu", chatLieuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailchatlieu", 0);
        model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail-ngung-hoat-dong/{id}")
    public String detailNgungHoatDong(Model model, @PathVariable("id") UUID id, @ModelAttribute("chatLieu") ChatLieu chatLieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll1(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("chatLieu", chatLieuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodaldetailchatlieu", 0);
        model.addAttribute("contentPage", "../chat-lieu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("chatLieu") ChatLieu chatLieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("chatLieu", chatLieuService.findById(id));
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatechatlieu", 0);
        model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
        return "home/layout";
    }

    @PostMapping("/update/{id}")
    public String add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult bindingResult
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (bindingResult.hasErrors()) {

            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<ChatLieu> page = chatLieuService.getAll(pageable);
            model.addAttribute("listChatLieu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("batmodalupdatechatlieu", 0);
            model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
            return "home/layout";
        }
        ChatLieu cl = chatLieuService.findById(id);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        cl.setTrangThai(0);
        cl.setTen(chatLieu.getTen());
        chatLieuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("batmodalupdatechatlieu", 1);
        model.addAttribute("thongBaoThanhCong", "Cập nhật dữ liệu thành công");
        model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/delete/{id}")
    public String updateTrangThai(Model model, @PathVariable("id") UUID id, @ModelAttribute("chatLieu") ChatLieu chatLieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        ChatLieu cl = chatLieuService.findById(id);
        cl.setTrangThai(1);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        chatLieuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String updateTrangThaiNgung(Model model, @PathVariable("id") UUID id, @ModelAttribute("chatLieu") ChatLieu chatLieu
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        ChatLieu cl = chatLieuService.findById(id);
        cl.setTrangThai(0);
        cl.setNgaySua(Date.valueOf(LocalDate.now()));
        chatLieuService.update(id, cl);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ChatLieu> page = chatLieuService.getAll1(pageable);
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("thongBaoThanhCong", "Cập nhật trạng thái thành công");
        model.addAttribute("contentPage", "../chat-lieu/hien-thi-ngung-hoat-dong.jsp");
        return "home/layout";
    }

    @PostMapping("/search-con-hoat-dong")
    public String search0(Model model, @ModelAttribute("chatLieu") ChatLieu chatLieu, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<ChatLieu> page = chatLieuService.getAll(pageable);
            model.addAttribute("listChatLieu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
            return "home/layout";
        } else {
            List<ChatLieu> list = chatLieuService.search0(search);
            model.addAttribute("listChatLieu", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../chat-lieu/hien-thi.jsp");
            return "home/layout";
        }

    }

    @PostMapping("/search-ngung-hoat-dong")
    public String search1(Model model, @ModelAttribute("chatLieu") ChatLieu chatLieu, @RequestParam("search") String search
            , @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<ChatLieu> page = chatLieuService.getAll1(pageable);
            model.addAttribute("listChatLieu", page.getContent());
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("contentPage", "../chat-lieu/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        } else {
            List<ChatLieu> list = chatLieuService.search1(search);
            model.addAttribute("listChatLieu", list);
            model.addAttribute("thongBaoThanhCong", "Tìm kiếm dữ liệu thành công");
            model.addAttribute("contentPage", "../chat-lieu/hien-thi-ngung-hoat-dong.jsp");
            return "home/layout";
        }

    }
}
