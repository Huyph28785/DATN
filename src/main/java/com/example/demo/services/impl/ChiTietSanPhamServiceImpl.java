package com.example.demo.services.impl;


import com.example.demo.dto.ChiTietSanPhamCustom;
import com.example.demo.models.*;
import com.example.demo.repositories.ChiTietSanPhamRepo;
import com.example.demo.services.ChiTietSanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    ChiTietSanPhamRepo repo;

    @Autowired
    HttpServletRequest request;


    @Override
    public List<ChiTietSanPham> findAll() {
        return repo.findAll();
    }

    @Override
    public ChiTietSanPham findById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Integer getSLGioHang(UUID idKH) {
        return repo.getSLGioHang(idKH);
    }

    @Override
    public String getSoLuongByKichCo(UUID idMS, UUID idSP, String size) {
        return repo.getSoLuongByKichCo(idMS, idSP, size);
    }

    @Override
    public ChiTietSanPham findCTSPAddCart(UUID idSP, UUID idMS, UUID idKC) {
        return repo.findctspAddCart(idSP, idMS, idKC);
    }

    @Override
    public List<MauSac> getMauSacBySP(UUID idSP) {
        return repo.getMauBySanPham(idSP);
    }

    @Override
    public List<String> getKichCoSacBySP(UUID idSP) {
        return repo.getKichCoBySanPham(idSP);
    }

    @Override
    public List<Integer> getKichCoByMauSacAndSanPham(UUID idMS, UUID idSP) {
        return repo.getKichCoByMauSacAndSanPham(idMS, idSP);
    }

    @Override
    public Page<ChiTietSanPham> searchCTSPByLoaiGiayList(List<UUID> idLoaiGiayList, Pageable pageable) {
        return repo.searchCTSPByLoaiGiayList(idLoaiGiayList, pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByKC(UUID idKC, Pageable pageable) {
        return repo.getCTSPBYKC(idKC, pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByMS(UUID idMS, Pageable pageable) {
        return repo.getCTSPBYMS(idMS, pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByGiaBan(Double minPrice, Double maxPrice, Pageable pageable) {
        return repo.getCTSPByGiaBan(minPrice, maxPrice, pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPBySP(UUID idSP, Pageable pageable) {
        return repo.getCTSPBYSP(idSP,pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByCL(UUID idSP, Pageable pageable) {
        return repo.getCTSPBYCL(idSP,pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByCA(UUID idSP, Pageable pageable) {
        return repo.getCTSPBYCA(idSP,pageable);
    }

    @Override
    public List<ChiTietSanPham> getList() {
        return repo.findAll();
    }

    @Override
    public Page<ChiTietSanPham> getListSP(Pageable pageable) {
        return repo.getListSP(pageable);
    }


    @Override
    public void addKC(ChiTietSanPham qlSanPham) {
        repo.save(qlSanPham);
    }

    @Override
    public void deleteSP(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public boolean isChiTietSanPhamExists(ChiTietSanPham sp) {
        return repo.existsBySanPhamAndChatLieuAndCoAoAndMauSacAndThuongHieuAndKichCo(
                sp.getSanPham(), sp.getChatLieu(), sp.getCoAo(), sp.getMauSac(), sp.getThuongHieu(), sp.getKichCo()
        );
    }


    @Override
    public Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable) {
        return repo.searchByMau(idMau, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchKichCo(UUID idKC, Pageable pageable) {
        return repo.searchByKichCo(idKC, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchDeGiay(UUID idDe, Pageable pageable) {
        return repo.searchByDeGiay(idDe, pageable);
    }

    @Override
    public ChiTietSanPham updateSoLuongTon(UUID id, int soLuong) {
        ChiTietSanPham chiTietSanPham = repo.findById(id).orElse(null);
        if (chiTietSanPham != null) {
            chiTietSanPham.setSoLuongTon(soLuong);
            repo.save(chiTietSanPham);
            return chiTietSanPham;
        } else {
            return null;
        }
    }

    @Override
    public ChiTietSanPham updateDelete(UUID id, int soLuong) {
        ChiTietSanPham chiTietSanPham = repo.findById(id).orElse(null);
        if (chiTietSanPham != null) {
            chiTietSanPham.setSoLuongTon(soLuong + chiTietSanPham.getSoLuongTon());
            repo.save(chiTietSanPham);
            return chiTietSanPham;
        } else {
            return null;
        }

    }

    @Override
    public Page<ChiTietSanPham> listCTSP(UUID id, Pageable pageable) {
        return repo.listCTSP(id, pageable);
    }

    @Override

    public List<ChiTietSanPham> listCTSPSuDung() {
        return repo.getListCTSPSuDung();
    }

    public List<CoAo> listLG22(Integer trangThai) {
        return repo.listLG22(trangThai);
    }

    @Override
    public List<ThuongHieu> listDeGiay22(Integer trangThai) {
        return repo.listDeGiay22(trangThai);
    }

    @Override
    public List<MauSac> listMauSac22(Integer trangThai) {
        return repo.listMauSac22(trangThai);
    }

    @Override
    public List<ChatLieu> listChatLieu22(Integer trangThai) {
        return repo.listChatLieu22(trangThai);
    }

    @Override
    public List<KichCo> listKichCo22(Integer trangThai) {
        return repo.listKichCo22(trangThai);
    }

    @Override
    public List<CoAo> search22LG(String keyword, Integer trangThai) {
        return repo.search22LG(keyword, trangThai);
    }

    @Override
    public List<ThuongHieu> search22DG(String keyword, Integer trangThai) {
        return repo.search22DG(keyword, trangThai);
    }

    @Override
    public List<MauSac> search22MS(String keyword, Integer trangThai) {
        return repo.search22MS(keyword, trangThai);
    }

    @Override
    public List<ChatLieu> search22CL(String keyword, Integer trangThai) {
        return repo.search22CL(keyword, trangThai);
    }

    @Override
    public List<KichCo> search22KC(String size, Integer trangThai) {
        return repo.search22KC(size, trangThai);

    }

    @Override
    public List<ChiTietSanPhamCustom> listSPCungLoai(Double giaSP) {
        return repo.listSanPhamCungLoai(giaSP);
    }

    @Override

    public List<ChiTietSanPham> listCTSPByIDSP(UUID id) {
        return repo.listCTSPByIDSP(id);
    }


    public List<ChiTietSanPhamCustom> listCTSPKhuyenMai(UUID idSanPham) {
        return repo.listChiTietSanPhamKM(idSanPham);

    }

    @Override
    public ChiTietSanPham findFirstBySanPhamAndChatLieuAndCoAoAndMauSacAndThuongHieuAndKichCo(ChiTietSanPham sp) {
        return repo.findFirstBySanPhamAndChatLieuAndCoAoAndMauSacAndThuongHieuAndKichCo(
                sp.getSanPham(), sp.getChatLieu(), sp.getCoAo(), sp.getMauSac(), sp.getThuongHieu(), sp.getKichCo()
        );
    }

    @Override
    public Page<ChiTietSanPham> searchCL(UUID idCL, Pageable pageable) {
        return repo.searchByChatLieu(idCL, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchCA(UUID idCA, Pageable pageable) {
        return repo.searchByCA(idCA, pageable);
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable) {
        return repo.searchCTSP(keyword, pageable);
    }

    @Override
    public List<CoAo> search2CA(String keyword) {
        return repo.search(keyword);
    }

    @Override
    public List<KichCo> search2KC(String size) {
        return repo.search2KC(size);
    }

    @Override
    public List<KichCo> getListKC() {
        return repo.listKC();
    }

    @Override
    public UUID getOneToAddModal(UUID id) {
        return repo.getOneToAddModal(id);
    }

}
