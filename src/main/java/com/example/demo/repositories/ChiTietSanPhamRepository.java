package com.example.demo.repositories;

import com.example.demo.models.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("select c from ChiTietSanPham c  where c.trangThai=0 order by c.ngayTao desc ")
    Page<ChiTietSanPham> getAll(Pageable pageable);

    @Query("select c from ChiTietSanPham c  where c.trangThai=1 order by c.ngayTao desc ")
    Page<ChiTietSanPham> getAll1(Pageable pageable);

    @Query("select c from ChiTietSanPham c  where c.trangThai=0")
    List<ChiTietSanPham> findAllCTSP();

    List<ChiTietSanPham> findChiTietSanPhamBySanPham(SanPham sanPham);

    @Transactional
    @Modifying
    @Query(value = "update ChiTietSanPham set trangThai=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();

    @Query("select i from ChiTietSanPham i where i.id=:id")
    List<ChiTietSanPham> showQR(UUID id);

    boolean existsByChatLieuAndCoAoAndKichCoAndMauSacAndThuongHieuAndSanPham(ChatLieu chatLieu, CoAo coAo, KichCo kichCo, MauSac mauSac, ThuongHieu thuongHieu, SanPham sanPham);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join ChatLieu cl on ct.chatLieu.id=cl.id left join CoAo ca on ct.coAo.id=ca.id " +
            "left join KichCo kc on ct.kichCo.id=kc.id left join MauSac ms on ct.mauSac.id=ms.id " +
            "left join ThuongHieu th on ct.thuongHieu.id=th.id " +
            "where (:idChatLieu is null or cl.id =:idChatLieu) " +
            "and (:idSanPham is null or sp.id=: idSanPham) " +
            "and (:idCoAo is null or ca.id=: idCoAo) " +
            "and (:idKichCo is null or kc.id=: idKichCo) " +
            "and (:idMauSac is null or ms.id=: idMauSac) " +
            "and (:idThuongHieu is null or th.id=: idThuongHieu) "
    )
    List<ChiTietSanPham> loc(UUID idSanPham, UUID idChatLieu, UUID idCoAo, UUID idKichCo, UUID idMauSac, UUID idThuongHieu);

    @Query("select ct from ChiTietSanPham ct where ct.ma like %:ma%")
    ChiTietSanPham scan(@Param("ma") String ma);
}
