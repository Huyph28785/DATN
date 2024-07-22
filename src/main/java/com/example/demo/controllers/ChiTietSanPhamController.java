package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.repositories.ChiTietSanPhamRepo;
import com.example.demo.repositories.HinhAnhRepository;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.services.*;
import com.example.demo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Controller
public class ChiTietSanPhamController<MauSacReponsitory> {
    @Autowired
    ChiTietSanPhamService service;
    @Autowired
    ThuongHieuService deGiayRepo;
//    @Autowired
//    ExcelServiceImpl excelService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private KichCoService kichCoService;
    @Autowired
    private CoAoService loaiGiayRepo;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    SanPhamRepository sanPhamRepo;
    @Autowired
    ChiTietSanPhamRepo chiTietSanPhamRepo;
    @Autowired
    MauSacRepository mauSacReponsitories;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hoạt động");
        dsTrangThai.put(1, "Ngưng Hoạt động");
        return dsTrangThai;
    }

    @ModelAttribute("listThuongHieu")
    List<ThuongHieu> listThuongHieu() {
        return deGiayRepo.findAll();
    }

    @ModelAttribute("listChatLieu")
    List<ChatLieu> listChatLieu() {
        return chatLieuService.findAll();
    }

    @ModelAttribute("listKichCo")
    List<KichCo> listKichCo() {
        return kichCoService.findAll();
    }

    @ModelAttribute("listMau")
    List<MauSac> listMauSac() {
        return mauSacService.findAll();
    }

    @ModelAttribute("listCoAo")
    List<CoAo> listCoAo() {
        return loaiGiayRepo.findAll();
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Boolean, String> getDsGioiTinh() {
        Map<Boolean, String> dsGT = new HashMap<>();
        dsGT.put(true, "Nam");
        dsGT.put(false, "Nữ");
        return dsGT;
    }

    @Data
    public static class SearchFormSP {
        String keyword;
    }

    @Data
    public static class SearchFormSPByMau {
        UUID idMau;
    }

    @Data
    public static class SearchThuongHieu {
        UUID idTH;
    }

    @Data
    public static class SearchKC {
        UUID idKC;
    }

    @Data
    public static class SearchCoAo {
        UUID idCA;
    }

    @Data
    public static class SearchChatlieu {
        UUID idChatLieu;
    }

    @Data
    public static class SortFormSP {
        String key = "";
    }

    @ModelAttribute("listSP")
    List<SanPham> listSP() {
        return sanPhamRepo.findAll();
    }

    @RequestMapping("/chi-tiet-san-pham/hien-thi")

    public String hienThiSanPham(@ModelAttribute("sortForm") SortFormSP sortFormSP, @ModelAttribute("sanpham") QLSanPham sp, @RequestParam(defaultValue = "0") int p, Model model) throws IOException, WriterException {

        if (p < 0) {
            p = 0;
        }

        Pageable pageable = PageRequest.of(p, 5);
        Page<ChiTietSanPham> qlSanPhamPage = service.getListSP(pageable);
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("SP", new SanPham());

        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("lg", new SearchCoAo());
//
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        return "home/layout";

    }
    @RequestMapping("/chi-tiet-san-pham/view-add/{id}")
    public String viewAdd(Model model, @ModelAttribute("sanpham") QLSanPham sp, @PathVariable("id") UUID id
            ,@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {


        model.addAttribute("lg", new CoAo());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("action2", "/san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/san-pham/chat-lieu/add/" + id);
        model.addAttribute("act", "add");
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("tensp", sanPham1.getTen());
        model.addAttribute("action", "/chi-tiet-san-pham/add/" + sanPham1.getId());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
        return "home/layout";
    }

    // add ctsp
    @PostMapping("/chi-tiet-san-pham/add/{id}")
    public String AddSanPham(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("sanpham") ChiTietSanPham sp
            ,
                             BindingResult result, RedirectAttributes redirectAttributes, @RequestParam(value = "kichCo", required = false) List<String> kichCoList,
                             @RequestParam(value = "mauSac", required = false) List<String> mauSacList,
                             @RequestParam(name = "soLuong", required = false) List<String> listSoLuong) throws WriterException, IOException {
        model.addAttribute("lg", new CoAo());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("act", "add");

        if (result.hasErrors()) {
            model.addAttribute("submitStatus", "error");
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("contentPage",  "../chi-tiet-san-pham/add_update.jsp");
            return "home/layout";
        }

        SanPham sanPham1 = sanPhamService.getOne(id);
        String mhd = "";
        sp.setSanPham(sanPham1);
        sp.setNgayTao(Date.valueOf(LocalDate.now()));
        ChiTietSanPham ctsp = new ChiTietSanPham();
        if (kichCoList != null && mauSacList != null && listSoLuong != null) {
            for (int i = 0; i < kichCoList.size(); i++) {
                if (i >= kichCoList.size()) {
                    System.out.println("Chỉ số vượt quá kích thước của kichCoList");
                    // hoặc thực hiện hành động cụ thể tùy thuộc vào logic ứng dụng của bạn
                    continue;
                }
                String kichCoID =kichCoList.get(i);
                for (int j = 0; j < mauSacList.size(); j++) {
                    if (j >= mauSacList.size()) {
                        System.out.println("Chỉ số vượt quá kích thước của mauSacList");
                        // hoặc thực hiện hành động cụ thể tùy thuộc vào logic ứng dụng của bạn
                        continue;
                    }
                    String mauSacID = mauSacList.get(j);
                    //
                    int index = i * mauSacList.size() + j;
                    if (index >= listSoLuong.size()) {
                        System.out.println("Chỉ số vượt quá kích thước của listSoLuong");
                        // hoặc thực hiện hành động cụ thể tùy thuộc vào logic ứng dụng của bạn
                        continue;
                    }
                    //
                    String soLuong = listSoLuong.get(index);
                    sp.setSoLuongTon(Integer.valueOf(soLuong));
                    sp.setKichCo(kichCoService.findById(UUID.fromString(kichCoID)));
                    sp.setMauSac(mauSacReponsitories.getOne(UUID.fromString(mauSacID)));
                    Integer sl = (sanPhamService.findAllCTSP().size() + 1);
                    if (sl < 10) {
                        mhd = "CTSP0" + sl;
                    } else {
                        mhd = "CTSP" + sl;
                    }
                    sp.setMa(mhd);
                    String projectRootPath = System.getProperty("user.dir");
                    String outputFolderPath = projectRootPath + "/src/main/webapp/maqr";
                    QRCodeGenerator.generatorQRCode(sp, outputFolderPath);
                    sp.setMaQR(sp.getMa()+ ".png");
                    if (service.isChiTietSanPhamExists(sp)) {
                        ChiTietSanPham ctspExit = service.findFirstBySanPhamAndChatLieuAndCoAoAndMauSacAndThuongHieuAndKichCo(sp);
//
                        ctspExit.setSoLuongTon(Integer.valueOf(soLuong) + ctspExit.getSoLuongTon());
                        sp.setGiaBan(ctspExit.getGiaBan());

                        service.addKC(ctspExit);

                    } else {
                        service.addKC(sp);
                    }
                }
            }
        }

        model.addAttribute("tensp", sanPham1.getTen());
        redirectAttributes.addFlashAttribute("redirectUrl", "/chi-tiet-san-pham/list-san-pham/" + id);
        model.addAttribute("thongBaoThanhCong", "Thêm chi tiết sản phẩm mới thành công");
        System.out.println("Thêm chi tiết sản phẩm mới thành công");
        SanPham sanPhamm = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPhamm.getId());
        model.addAttribute("tensp", sanPhamm.getTen());
        Pageable pageable = PageRequest.of(0, 5);
        Page<ChiTietSanPham> qlSanPhamPage = service.listCTSP(id, pageable);
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("lg", new SearchCoAo());
        model.addAttribute("SP", new SanPham());

        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        return "home/layout";
    }

    //list ctsp theo id
    @RequestMapping("chi-tiet-san-pham/list-san-pham/{id}")

    public String hienListSanPham(@ModelAttribute("sortForm") SortFormSP sortFormSP,
                                  @ModelAttribute("sanpham") QLSanPham sp, @RequestParam(defaultValue = "0") int p,
                                  @PathVariable("id") UUID id, Model model) throws IOException, WriterException {

        if (p < 0) {
            p = 0;
        }
        SanPham sanPham1 = sanPhamService.getOne(id);
        model.addAttribute("idsp", sanPham1.getId());
        model.addAttribute("tensp", sanPham1.getTen());
        Pageable pageable = PageRequest.of(p, 5);
        Page<ChiTietSanPham> qlSanPhamPage = service.listCTSP(id, pageable);
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("lg", new SearchCoAo());
        model.addAttribute("SP", new SanPham());

        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham1.jsp");
        return "home/layout";
    }
    @Autowired
    HinhAnhService hinhAnhservice;

    @Autowired
    HinhAnhRepository hinhAnhrepository;

    @GetMapping("/chi-tiet-san-pham/hinh-anh-sp/view-add/{id}")
    public String viewAddHinhAnh(Model model, @ModelAttribute("hinhAnh") HinhAnh hinhAnh, @PathVariable("id") UUID id,
                                 @RequestParam UUID idSP,
                                 @RequestParam UUID idMS
    ) {
        ChiTietSanPham ctsp = service.getOne(id);
        model.addAttribute("ctsp",ctsp);
        UUID idHinhANh = hinhAnhrepository.getIdHinhAnh(id);
        SanPham sp = hinhAnhservice.getSanPhamByIDCTSP(id);
        if (idHinhANh != null) {
            HinhAnh hinhAnh2 = hinhAnhservice.getHinhAnh(id);
            model.addAttribute("listHinhAnh", hinhAnh2);

            model.addAttribute("action4", "/hinh-anh-spct/update/" + ctsp.getId());
            model.addAttribute("contentPage", "../hinh-anh/add_update.jsp");
            return "home/layout";

        } else {
            // Các xử lý khác nếu không tìm thấy idHinhAnh
            model.addAttribute("ctsp", ctsp);
            model.addAttribute("action4", "/chi-tiet-san-pham/hinh-anh/add/" + ctsp.getId());
            model.addAttribute("contentPage", "../hinh-anh/add_update.jsp");
            return "home/layout";
        }
    }

    @Autowired
    HinhAnhService hinhAnhService;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @PostMapping("/chi-tiet-san-pham/hinh-anh/add/{id}")
    public String save(Model model,
                       @RequestParam(name = "tenAnh") MultipartFile tenanh,
                       @RequestParam(name = "duongDan1") MultipartFile duongdan1,
                       @RequestParam(name = "duongDan2") MultipartFile duongdan2,
                       @RequestParam(name = "duongDan3") MultipartFile duongdan3,
                       @PathVariable UUID id,
                       @RequestParam(name = "ctsp") ChiTietSanPham ctsp
    ) {
        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setCtsp(ctsp);

        try {
            String uploadPath = hinhAnhService.getImageUploadPath();
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            MultipartFile[] imageFiles = {tenanh, duongdan1, duongdan2, duongdan3};
            for (int i = 0; i < imageFiles.length; i++) {
                MultipartFile file = imageFiles[i];
                if (file != null && !file.isEmpty()) {
                    String fileName = file.getOriginalFilename().toLowerCase();
                    Path filePath = uploadDir.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    switch (i) {
                        case 0:
                            hinhAnh.setTenAnh(fileName);
                            break;
                        case 1:
                            hinhAnh.setDuongDan1(fileName);
                            break;
                        case 2:
                            hinhAnh.setDuongDan2(fileName);
                            break;
                        case 3:
                            hinhAnh.setDuongDan3(fileName);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("contentPage", "../hinh-anh/add_update.jsp");
            return "home/layout";
        }
        ChiTietSanPham ct =  service.findById(id);
        ct.setUrlAnh(hinhAnh.getDuongDan1());
        chiTietSanPhamRepo.save(ct);
        hinhAnhRepository.save(hinhAnh);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @RequestMapping("/hinh-anh-spct/update/{id}")
    public String updateHinhAnh(@PathVariable(name = "id") UUID id,
                                @RequestParam(name = "tenAnh") MultipartFile tenanh,
                                @RequestParam(name = "duongDan1") MultipartFile duongdan1,
                                @RequestParam(name = "duongDan2") MultipartFile duongdan2,
                                @RequestParam(name = "duongDan3") MultipartFile duongdan3,
                                @RequestParam(name = "ctsp") ChiTietSanPham ctsp, Model model) {
        HinhAnh hinhAnh = hinhAnhservice.findById(ctsp.getHinhAnh().getId());
        model.addAttribute("hinhANh", hinhAnh);
        UUID idctsp = hinhAnhrepository.getIdCTSP(id);
        model.addAttribute("idctsp", idctsp);
        // Cập nhật ảnh "tenanh" nếu người dùng đã chọn
        if (!tenanh.isEmpty()) {
            String newTenAnh = saveImage(tenanh);
            hinhAnh.setTenAnh(newTenAnh);
        }
        if (!duongdan1.isEmpty()) {
            String newDuongdan1 = saveImage(duongdan1);
            hinhAnh.setDuongDan1(newDuongdan1);
        }
        if (!duongdan2.isEmpty()) {
            String newDuongdan2 = saveImage(duongdan2);
            hinhAnh.setDuongDan2(newDuongdan2);
        }
        if (!duongdan3.isEmpty()) {
            String newDuongdan3 = saveImage(duongdan3);
            hinhAnh.setDuongDan3(newDuongdan3);
        }
        ChiTietSanPham ct =  service.findById(id);
        ct.setUrlAnh(hinhAnh.getDuongDan1());
        chiTietSanPhamRepo.save(ct);
        hinhAnhrepository.save(hinhAnh);

        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    private String saveImage(MultipartFile file) {

        String newImageFileName = UUID.randomUUID().toString() + ".jpg";
        String imagePath = hinhAnhservice.getImageUploadPath() + File.separator + newImageFileName;

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imagePath);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newImageFileName;
    }
    @ResponseBody
    @GetMapping("/chi-tiet-san-pham/show-qr/{id}")
    public List<ChiTietSanPham> showQR(Model model, @PathVariable("id") UUID id){
        List<ChiTietSanPham> list = sanPhamService.showQR(id);
        System.out.println(list);
        return list;
    }
    // search 2 loại giầy
    @GetMapping("/chi-tiet-san-pham/search2-co-ao")
    public ResponseEntity<?> search2LoaiGiay(@RequestParam(name = "keyword") String keyword) {

        if (keyword == null || keyword == "") {
            return ResponseEntity.ok(chiTietSanPhamRepo.listLoaiGiay());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.search("%" + keyword + "%"));
        }
    }

    @GetMapping("/chi-tiet-san-pham/search2-kich-co")
    public ResponseEntity<List<KichCo>> search2KichCo(@RequestParam(name = "size", required = false) String size) {
        if (size == null || size == "") {
            return ResponseEntity.ok(chiTietSanPhamRepo.listKC());
        } else {
            return ResponseEntity.ok(service.search2KC("%" + size + "%"));
        }
    }

    // search 2 màu sắc
    @GetMapping("/chi-tiet-san-pham/search2-mau-sac")
    public ResponseEntity<?> search2MS(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(mauSacService.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchMS("%" + ten + "%"));
        }
    }

    // search 2 đế giầy
    @GetMapping("/chi-tiet-san-pham/search2-thuong-hieu")
    public ResponseEntity<?> search2DG(@RequestParam(name = "keyword") String loaiDe) {

        if (loaiDe == null || loaiDe.equals("")) {
            return ResponseEntity.ok(deGiayRepo.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchDG("%" + loaiDe + "%"));
        }
    }

    // search 2 chất liệu
    @GetMapping("/chi-tiet-san-pham/search2-chat-lieu")
    public ResponseEntity<?> search2CL(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(chatLieuService.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchCL("%" + ten + "%"));
        }
    }


    // 12/11/2023
// search 2 loại giầy
    @GetMapping("/chi-tiet-san-pham/search22-loai-giay")
    public ResponseEntity<?> search22LoaiGiay(@RequestParam(name = "keyword") String keyword) {

        if (keyword == null || keyword == "") {
            return ResponseEntity.ok(service.listLG22(0));
        } else {
            return ResponseEntity.ok(service.search22LG("%" + keyword + "%", 0));
        }
    }
    // search 2 đế giầy
    @GetMapping("/chi-tiet-san-pham/search22-de-giay")
    public ResponseEntity<?> search22DG(@RequestParam(name = "keyword") String loaiDe) {

        if (loaiDe == null || loaiDe.equals("")) {
            return ResponseEntity.ok(service.listDeGiay22(0));
        } else {
            return ResponseEntity.ok(service.search22DG("%" + loaiDe + "%", 0));
        }
    }
    @GetMapping("/chi-tiet-san-pham/search22-kich-co")
    public ResponseEntity<List<KichCo>> search22KichCo(@RequestParam(name = "keyword", required = false) String size) {
        if (size != null || size.equals("")) {
            // Xử lý khi 'size' có giá trị
            return ResponseEntity.ok(service.listKichCo22(0));
        } else {
            // Xử lý khi 'size' là null (không được truyền vào)
            return ResponseEntity.ok(chiTietSanPhamRepo.search22KC("%" + size + "%", 0));
        }
    }

    // search 2 màu sắc
    @GetMapping("/chi-tiet-san-pham/search22-mau-sac")
    public ResponseEntity<?> search22MS(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(service.listMauSac22(0));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.search22MS("%" + ten + "%", 0));
        }
    }



    // search 2 chất liệu
    @GetMapping("/chi-tiet-san-pham/search22-chat-lieu")
    public ResponseEntity<?> search22CL(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(service.listChatLieu22(0));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.search22CL("%" + ten + "%", 0));
        }
    }

    //

    @RequestMapping("/chi-tiet-san-pham/search")
    public String searchSP(@ModelAttribute("searchForm") SearchFormSP searchFormSP, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 10);
        if (searchFormSP.keyword != null && !searchFormSP.keyword.equals("")) {
            qlSanPhamPage = service.searchCTSP(searchFormSP.keyword, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("lg", new SearchCoAo());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);

        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // filer with combobox mau-sac
    @RequestMapping("/chi-tiet-san-pham/search-by-mausac")
    public String searchByMau(@ModelAttribute("searchFormByMau") SearchFormSPByMau searchFormSPByMau, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchFormSPByMau.idMau != null && !searchFormSPByMau.idMau.equals("")) {
            qlSanPhamPage = service.searchByMau(searchFormSPByMau.idMau, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("lg", new SearchCoAo());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchKC", new SearchKC());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // filer with combobox kich co
    @RequestMapping("/chi-tiet-san-pham/search-by-kichco")
    public String searchByKC(@ModelAttribute("searchKC") SearchKC searchKC, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchKC.idKC != null && !searchKC.idKC.equals("")) {
            qlSanPhamPage = service.searchKichCo(searchKC.idKC, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("lg", new SearchCoAo());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // filer with combobox de giay
    @RequestMapping("/chi-tiet-san-pham/search-by-thuonghieu")
    public String searchByDeGiay(@ModelAttribute("searchDG") SearchThuongHieu searchThuongHieu, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchThuongHieu.idTH != null && !searchThuongHieu.idTH.equals("")) {
            qlSanPhamPage = service.searchDeGiay(searchThuongHieu.idTH, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }

        model.addAttribute("lg", new SearchCoAo());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }


    // filer with combobox chat lieu
    @RequestMapping("/chi-tiet-san-pham/search-by-chatlieu")
    public String searchByChatLieu(@ModelAttribute("searchChatLieu") SearchChatlieu searchChatlieu, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchChatlieu.idChatLieu != null && !searchChatlieu.idChatLieu.equals("")) {
            qlSanPhamPage = service.searchCL(searchChatlieu.idChatLieu, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("lg", new SearchCoAo());

        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    // filer with combobox loai giay
    @RequestMapping("/chi-tiet-san-pham/search-by-coao")
    public String searchByLoaiGiay(@ModelAttribute("lg") SearchCoAo searchCoAo, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchCoAo.idCA != null && !searchCoAo.idCA.equals("")) {
            qlSanPhamPage = service.searchCA(searchCoAo.idCA,pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "home/layout";
    }

    @RequestMapping("/chi-tiet-san-pham/sort")
    public String sort(@ModelAttribute("sortForm") SortFormSP sortFormSP, @ModelAttribute("searchForm") SearchFormSP searchFormSP, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Sort sort;
        model.addAttribute("searchDG", new SearchThuongHieu());
        model.addAttribute("lg", new SearchCoAo());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        sort = sortFormSP.key.equals("giaBan") ? Sort.by(Sort.Direction.DESC, "giaBan") : Sort.by(Sort.Direction.DESC, "giaBan");
        Pageable pageable = PageRequest.of(p, 5, sort);
        Page<ChiTietSanPham> qlSanPhamPage = service.getListSP(pageable);
        model.addAttribute("page", qlSanPhamPage);

        System.out.println(sortFormSP.key);
//        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("listSanPham", qlSanPhamPage.getContent());
        model.addAttribute("totalPage", qlSanPhamPage.getTotalPages());
        model.addAttribute("contentPage", "../san-pham/hien-thi-chi-tiet-san-pham.jsp");
        return "home/layout";
    }


    @RequestMapping("/chi-tiet-san-pham/update/{id}")
    public String updateKC(Model model, @Valid @ModelAttribute("sanpham") QLSanPham qlSanPham, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, WriterException {
        model.addAttribute("lg", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("act", "update");
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
//            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
            return "home/layout";
        }


        UUID idSP = service.getOneToAddModal(qlSanPham.getId());
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTen());

        ChiTietSanPham ctsp = service.getOne(qlSanPham.getId());
        qlSanPham.setNgayTao(ctsp.getNgayTao());
        ctsp.loadFromViewModel(qlSanPham);

        service.addKC(ctsp);
        //generate code qr

        String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
        String qrCodeFolderPath = documentsPath + File.separator + "QRCode";
        new File(qrCodeFolderPath).mkdirs(); // Tạo thư mục "QRCode" nếu chưa tồn tại

        // Lưu QR code vào thư mục "QRCode" trong "Documents"
        QRCodeGenerator.generatorQRCode(ctsp, qrCodeFolderPath);

        //
//        redirectAttributes.addFlashAttribute("redirectUrl","/chi-tiet-san-pham/hien-thi");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list.jsp");
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @RequestMapping("/chi-tiet-san-pham/view-update/{id}")
    public String viewUpdate(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham sp = service.getOne(id);

        model.addAttribute("lg", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());

        model.addAttribute("act", "update");
        UUID idSP = service.getOneToAddModal(id);
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTen());

        model.addAttribute("action2", "/chi-tiet-san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/chi-tiet-san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/chi-tiet-san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/chi-tiet-san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/chi-tiet-san-pham/chat-lieu/add/" + id);
        model.addAttribute("action", "/chi-tiet-san-pham/update/" + sp.getId());
        model.addAttribute("sanpham", sp);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
        return "home/layout";
    }

//update list-spct

    @RequestMapping("/chi-tiet-san-pham/update-sp/{id}")
    public String updateSPCT(Model model, @Valid @ModelAttribute("sanpham") QLSanPham qlSanPham, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, WriterException {
        model.addAttribute("lg", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        model.addAttribute("act", "update");
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
            return "home/layout";
        }

        UUID idSP = service.getOneToAddModal(qlSanPham.getId());
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTen());
        model.addAttribute("searchForm", new SearchFormSP());

        ChiTietSanPham ctsp = service.getOne(qlSanPham.getId());
        qlSanPham.setNgayTao(ctsp.getNgayTao());
        ctsp.loadFromViewModel(qlSanPham);

        service.addKC(ctsp);
        //generate code qr
        String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
        String qrCodeFolderPath = documentsPath + File.separator + "QRCode";
        new File(qrCodeFolderPath).mkdirs(); // Tạo thư mục "QRCode" nếu chưa tồn tại

        // Lưu QR code vào thư mục "QRCode" trong "Documents"
        QRCodeGenerator.generatorQRCode(ctsp, qrCodeFolderPath);

        //
//        redirectAttributes.addFlashAttribute("redirectUrl","/chi-tiet-san-pham/list-san-pham/" + idSP);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/list-spct.jsp");
        return "redirect:/chi-tiet-san-pham/list-san-pham/" + idSP;
    }

    @RequestMapping("/chi-tiet-san-pham/view-update-ctsp/{id}")
    public String viewUpdateCTSP(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham sp = service.getOne(id);
        model.addAttribute("act", "update");
        model.addAttribute("lg", new CoAo());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new ThuongHieu());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());


        UUID idSP = service.getOneToAddModal(id);
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTen());

        model.addAttribute("action2", "/chi-tiet-san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/chi-tiet-san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/chi-tiet-san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/chi-tiet-san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/chi-tiet-san-pham/chat-lieu/add/" + id);
        model.addAttribute("action", "/chi-tiet-san-pham/update-sp/" + sp.getId());
        model.addAttribute("sanpham", sp);
//        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add_update.jsp");
        return "home/layout";
    }

    //    // modal
    @Autowired
    SanPhamService sanPhamService;

    @RequestMapping("/chi-tiet-san-pham/loai-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> save(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("lg") CoAo coAo, BindingResult result) {
        Boolean hasE = result.hasErrors();
        Map<String, Object> response = new HashMap<>();
        List<CoAo> list = loaiGiayRepo.findAll();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
        if (result.hasErrors()) {
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            return response;
        }

        if (loaiGiayRepo.findByMa(coAo.getMa()) != null) {
            result.rejectValue("ma", "duplicate4", "Lỗi! Mã không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (loaiGiayRepo.findByTen(coAo.getTen()) != null) {
            result.rejectValue("ten", "duplicate4", "Lỗi! Tên không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }

        loaiGiayRepo.add(coAo);
        response.put("status", "success");
        return response;

    }

    @RequestMapping("/chi-tiet-san-pham/kich-co/add/{id}")
    @ResponseBody
    public Map<String, Object> addKC(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("kichco") KichCo kichCo, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
        if (result.hasErrors()) {
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            return response;
        }

        if (kichCoService.findByMa(kichCo.getMa()) != null) {
            result.rejectValue("ma", "duplicate3", "Lỗi! Mã không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (kichCoService.findByTen(kichCo.getTen()) != null) {
            result.rejectValue("ten", "duplicate3", "Lỗi! Size không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "ten");
            return response;
        }

        kichCoService.add(kichCo);
        response.put("status", "success");
        return response;
    }

    @PostMapping("/chi-tiet-san-pham/mau-sac/add/{id}")
    @ResponseBody
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("ms") MauSac ms, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());

        if (result.hasErrors()) {
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            return response;
        }

        if (mauSacService.findByMa(ms.getMa()) != null) {
            result.rejectValue("ma", "duplicate2", "Lỗi! Mã không được trùng");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (mauSacService.findByTen(ms.getTen()) != null) {
            result.rejectValue("ten", "duplicate2", "Lỗi! Tên màu không được trùng hoho");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ten"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        mauSacService.add(ms);
        response.put("status", "success");
        return response;
    }



    @RequestMapping("/chi-tiet-san-pham/chat-lieu/add/{id}")
    @ResponseBody
    public Map<String, Object> store(Model model, @PathVariable("id") UUID id,
                                     @Valid @ModelAttribute("vm") ChatLieu cl,
                                     BindingResult result
    ) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());
//        model.addAttribute("ms", new MauSac());
//        model.addAttribute("degiay", new DeGiay());
//        model.addAttribute("kichco", new KichCo());
//        model.addAttribute("lg", new LoaiGiay());
        if (result.hasErrors()) {
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            return response;
        }

        if (chatLieuService.findByMa(cl.getMa()) != null) {
            result.rejectValue("ma", "duplicate1", "Lỗi! Mã không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (chatLieuService.findByTen(cl.getTen()) != null){
            result.rejectValue("ten", "duplicate1", "Lỗi! Tên chất liệu không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ten"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        chatLieuService.add(cl);
        response.put("status", "success");
        return response;
    }

    //
    private List<String> getErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        return errors;
    }

    @PostMapping("/chi-tiet-san-pham/de-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("degiay") ThuongHieu degiay, BindingResult result) {
        UUID idSP = service.getOneToAddModal(id);
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTen());

        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("errors", getErrors(result));
            return response;
        }

        if (deGiayRepo.findByMa(degiay.getMa()) != null) {
            result.rejectValue("ma", "duplicate", "Lỗi! Mã không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (deGiayRepo.findByTen(degiay.getTen()) != null) {
            result.rejectValue("ten", "duplicate", "Lỗi! Loại đế không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "ten"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        deGiayRepo.add(degiay);
        response.put("status", "success");
        return response;
    }

}
