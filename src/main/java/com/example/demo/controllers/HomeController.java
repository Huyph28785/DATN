package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    ThuongHieuService thuongHieuService;

    @Autowired
    KichCoService kichCoService;

    @Autowired
    MauSacService mauSacService;

    @Autowired
    ChatLieuService chatLieuService;

    @Autowired
    CoAoService coAoService;

    @Autowired
    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @Autowired
    GioHangChiTietRepository gioHangChiTietRepo;

    @Autowired
    GioHangChiTietService gioHangChiTietService;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    KhachHangService khachHangService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    HttpSession session;

    private KhachHang danngNhap=new KhachHang();

//    @Autowired
//    TaiKhoanService taiKhoanService;

    private static int maHoaDon = 1;

    @Data
    public static class SortForm {
        String key;
    }

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    HoaDonChiTietSerice hoaDonChiTietService;


    @Data
    public static class SearchFormByKichCo {
        Double key;
    }



    @RequestMapping("/windzy/product_list")
    public String product_list(Model model, @RequestParam(defaultValue = "0") int p,
                               @ModelAttribute("sortForm") SortForm sortForm,
                               @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                               HttpSession session) {
        int page = p;
        int pageSize = 15;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ChiTietSanPham> listSP = chiTietSanPhamRepo.get1CTSPByMauSac(pageable);
        List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
        List<KichCo> listKC = kichCoService.findAllFullTT();
        List<MauSac> listMS = mauSacService.findAllFullTT();
        model.addAttribute("pageSP", listSP);
        // model.addAttribute("pageSP", pageCTSP);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @Data
    public static class SearchFormByGiaBan {
        Double minPrice;
        Double maxPrice;
    }

    @RequestMapping("/windzy/product_list/searchbygiaban")
    public String getListByGiaBan(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> pageCTSPByGia = chiTietSanPhamService.getCTSPByGiaBan(searchFormByGiaBan.minPrice, searchFormByGiaBan.maxPrice, pageable);
        List<ChiTietSanPham> listSP = chiTietSanPhamService.getList();
        List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
        List<KichCo> listKC = kichCoService.findAllFullTT();
        List<MauSac> listMS = mauSacService.findAllFullTT();
        List<ChatLieu> listCL = chatLieuService.findAllFullTT();
        List<CoAo> listCA = coAoService.findAllFullTT();
        model.addAttribute("listSP", listSP);
        model.addAttribute("pageSP", pageCTSPByGia);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        model.addAttribute("listCL", listCL);
        model.addAttribute("listCA", listCA);
        model.addAttribute("view", "../ban-hang-online/trang-loc-sanpham.jsp");
        return "ban-hang-online/index";
    }

    @RequestMapping("/windzy/product_list/searchbykichco/{id}")
    public String getListByKichCo(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                  @PathVariable UUID id,
                                  HttpSession session) {
        KichCo kichCo = kichCoService.findById(id);
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> pageCTSPByKichCo = chiTietSanPhamService.getCTSPByKC(kichCo.getId(), pageable);
        List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
        List<KichCo> listKC = kichCoService.findAllFullTT();
        List<MauSac> listMS = mauSacService.findAllFullTT();
        List<SanPham> listSP = sanPhamService.findAll();
        List<ChatLieu> listCL = chatLieuService.findAllFullTT();
        List<CoAo> listCA = coAoService.findAllFullTT();
        model.addAttribute("pageSP", pageCTSPByKichCo);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        model.addAttribute("listCL", listCL);
        model.addAttribute("listCA", listCA);
//        model.addAttribute("view", "../ban-hang-online/trang-loc-sanpham.jsp");
        return "../ban-hang-online/trang-loc-sanpham.jsp";
    }

    @RequestMapping("/windzy/product_list/searchbymausac")
    public String getListByMauSac(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                  @RequestParam UUID idMS,
                                  HttpSession session
    ) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> listCTSPBYMS = chiTietSanPhamService.getCTSPByMS(idMS, pageable);
        List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
        List<KichCo> listKC = kichCoService.findAllFullTT();
        List<MauSac> listMS = mauSacService.findAllFullTT();
        List<SanPham> listSP = sanPhamService.findAll();
        List<ChatLieu> listCL = chatLieuService.findAllFullTT();
        List<CoAo> listCA = coAoService.findAllFullTT();

        model.addAttribute("pageSP", listCTSPBYMS);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        model.addAttribute("listCL", listCL);
        model.addAttribute("listCA", listCA);
        model.addAttribute("view", "../ban-hang-online/trang-loc-sanpham.jsp");
        return "home-index";
    }

    @RequestMapping("/windzy/product_list/searchbythuonghieu")
    public String getListByLoaiGiay(Model model,
                                    @RequestParam(defaultValue = "0") int p,
                                    @ModelAttribute("sortForm") SortForm sortForm,
                                    @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                    @RequestParam(name = "idThuongHieu", required = false) String idThuongHieu,
                                    HttpSession session
    ) {
        if ("all".equals(idThuongHieu)) {
            int page = p;
            int pageSize = 12;
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<ChiTietSanPham> pageSP = chiTietSanPhamRepo.get1CTSPByMauSac(pageable);
            List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
            List<KichCo> listKC = kichCoService.findAllFullTT();
            List<MauSac> listMS = mauSacService.findAllFullTT();

            model.addAttribute("pageSP", pageSP);
            model.addAttribute("listLG", listLG);
            model.addAttribute("listKC", listKC);
            model.addAttribute("listMS", listMS);
            model.addAttribute("view", "../ban-hang-online/trang-loc-sanpham.jsp");
        } else {
            List<UUID> idLoaiGiayUUIDList = Arrays.asList(idThuongHieu.split(","))
                    .stream()
                    .map(UUID::fromString)
                    .collect(Collectors.toList());

            Pageable pageable = PageRequest.of(p, 10);
            Page<ChiTietSanPham> listCTSPByLoaiGiay = chiTietSanPhamService.searchCTSPByLoaiGiayList(idLoaiGiayUUIDList,pageable);
            List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
            List<KichCo> listKC = kichCoService.findAllFullTT();
            List<MauSac> listMS = mauSacService.findAllFullTT();
            List<SanPham> listSP = sanPhamService.findAll();
            List<ChatLieu> listCL = chatLieuService.findAllFullTT();
            List<CoAo> listCA = coAoService.findAllFullTT();

            model.addAttribute("pageSP", listCTSPByLoaiGiay);
            model.addAttribute("listLG", listLG);
            model.addAttribute("listKC", listKC);
            model.addAttribute("listMS", listMS);
            model.addAttribute("listSP", listSP);
            model.addAttribute("listCL", listCL);
            model.addAttribute("listCA", listCA);
            model.addAttribute("view", "../ban-hang-online/trang-loc-sanpham.jsp");
        }

        return "home-index";
    }

    @RequestMapping("/windzy/product_list/searchbysanpham")
    public String getListBysanpham(Model model, @RequestParam(defaultValue = "0") int p,
                                   @ModelAttribute("sortForm") SortForm sortForm,
                                   @ModelAttribute("searchFormBySP") SearchFormByGiaBan searchFormByGiaBan,
                                   @RequestParam UUID idSP,
                                   HttpSession session
    ) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> listCTSPBYSP = chiTietSanPhamService.getCTSPBySP(idSP,pageable);
        List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
        List<KichCo> listKC = kichCoService.findAllFullTT();
        List<MauSac> listMS = mauSacService.findAllFullTT();
        List<SanPham> listSP = sanPhamService.findAll();
        List<ChatLieu> listCL = chatLieuService.findAllFullTT();
        List<CoAo> listCA = coAoService.findAllFullTT();
        model.addAttribute("pageSP", listCTSPBYSP);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        model.addAttribute("listCL", listCL);
        model.addAttribute("listCA", listCA);
        model.addAttribute("view", "../ban-hang-online/trang-loc-sanpham.jsp");
        return "home-index";
    }

    @RequestMapping("/windzy/product_list/searchbychatlieu")
    public String getListBychatlieu(Model model, @RequestParam(defaultValue = "0") int p,
                                    @ModelAttribute("sortForm") SortForm sortForm,
                                    @ModelAttribute("searchFormByCL") SearchFormByGiaBan searchFormByGiaBan,
                                    @RequestParam UUID idCL,
                                    HttpSession session
    ) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> listCTSPBYCL = chiTietSanPhamService.searchCL(idCL,pageable);
        List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
        List<KichCo> listKC = kichCoService.findAllFullTT();
        List<MauSac> listMS = mauSacService.findAllFullTT();
        List<SanPham> listSP = sanPhamService.findAll();
        List<ChatLieu> listCL = chatLieuService.findAllFullTT();
        List<CoAo> listCA = coAoService.findAllFullTT();
        model.addAttribute("pageSP", listCTSPBYCL);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        model.addAttribute("listCL", listCL);
        model.addAttribute("listCA", listCA);
        model.addAttribute("view", "../ban-hang-online/trang-loc-sanpham.jsp");
        return "home-index";
    }

    @RequestMapping("/windzy/product_list/searchbycoao")
    public String getListByCoAo(Model model, @RequestParam(defaultValue = "0") int p,
                                @ModelAttribute("sortForm") SortForm sortForm,
                                @ModelAttribute("searchFormByCA") SearchFormByGiaBan searchFormByGiaBan,
                                @RequestParam UUID idMS,
                                HttpSession session
    ) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> listCTSPBYMS = chiTietSanPhamService.getCTSPByMS(idMS, pageable);
        List<ThuongHieu> listLG = thuongHieuService.findAllFullTT();
        List<KichCo> listKC = kichCoService.findAllFullTT();
        List<MauSac> listMS = mauSacService.findAllFullTT();
        List<SanPham> listSP = sanPhamService.findAll();
        List<ChatLieu> listCL = chatLieuService.findAllFullTT();
        List<CoAo> listCA = coAoService.findAllFullTT();
        model.addAttribute("pageSP", listCTSPBYMS);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        model.addAttribute("listCL", listCL);
        model.addAttribute("listCA", listCA);
        model.addAttribute("view", "../ban-hang-online/trang-loc-sanpham.jsp");
        return "home-index";
    }


}
