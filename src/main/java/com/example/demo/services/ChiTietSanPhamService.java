package com.example.demo.services;

import com.example.demo.dto.ChiTietSanPhamCustom;
import com.example.demo.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChiTietSanPhamService {
    List<ChiTietSanPham> findAll();

    ChiTietSanPham findById(UUID id);

    Integer getSLGioHang(UUID idKH);

    String getSoLuongByKichCo(UUID idMS, UUID idSP, String size);

    ChiTietSanPham findCTSPAddCart(UUID idSP, UUID idMS, UUID idKC);

    List<MauSac> getMauSacBySP(UUID idSP);

    List<String> getKichCoSacBySP(UUID idSP);

    List<Integer> getKichCoByMauSacAndSanPham(UUID idMS, UUID idSP);

    Page<ChiTietSanPham> searchCTSPByLoaiGiayList(List<UUID> idLoaiGiayList, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByKC(UUID idKC, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByMS(UUID idMS, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByGiaBan(Double minPrice, Double maxPrice, Pageable pageable);

    Page<ChiTietSanPham> getCTSPBySP(UUID idSP, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByCL(UUID idSP, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByCA(UUID idSP, Pageable pageable);

    List<ChiTietSanPham> getList();

    Page<ChiTietSanPham> getListSP(Pageable pageable);

    void addKC(ChiTietSanPham qlSanPham);

    void deleteSP(UUID id);

    boolean isChiTietSanPhamExists(ChiTietSanPham sp);

    //seacrchByMau
    Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable);

    Page<ChiTietSanPham> searchKichCo(UUID idKC, Pageable pageable);

    Page<ChiTietSanPham> searchDeGiay(UUID idDe, Pageable pageable);

    Page<ChiTietSanPham> searchCL(UUID idCL, Pageable pageable);

    Page<ChiTietSanPham> searchCA(UUID idLG, Pageable pageable);


    ChiTietSanPham getOne(UUID id);

    Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable);

    List<CoAo> search2CA(String keyword);

    List<KichCo> search2KC(String size);

    List<KichCo> getListKC();
// v3 updatectsp modal
    UUID getOneToAddModal(UUID id);

    ChiTietSanPham updateSoLuongTon(UUID id,int soLuong);

    ChiTietSanPham updateDelete(UUID id,int soLuong);


    Page<ChiTietSanPham> listCTSP(UUID id, Pageable pageable);


    List<ChiTietSanPham> listCTSPSuDung();

    //13.11.2023
    List<CoAo> listLG22(Integer trangThai);
    List<ThuongHieu> listDeGiay22(Integer trangThai);
    List<MauSac> listMauSac22(Integer trangThai);
    List<ChatLieu> listChatLieu22(Integer trangThai);
    List<KichCo> listKichCo22(Integer trangThai);
    List<CoAo> search22LG(String keyword, Integer trangThai);
    List<ThuongHieu> search22DG(String keyword, Integer trangThai);
    List<MauSac> search22MS(String keyword, Integer trangThai);
    List<ChatLieu> search22CL(String keyword, Integer trangThai);
    List<KichCo> search22KC(String size, Integer trangThai);

    List<ChiTietSanPhamCustom> listSPCungLoai (Double giaSP);


    List<ChiTietSanPham> listCTSPByIDSP(UUID id);

    List<ChiTietSanPhamCustom> listCTSPKhuyenMai (UUID idSanPham);

    ChiTietSanPham findFirstBySanPhamAndChatLieuAndCoAoAndMauSacAndThuongHieuAndKichCo(
            ChiTietSanPham ctsp);
}
