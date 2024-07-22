package com.example.demo.repositories;


import com.example.demo.dto.ChiTietSanPhamCustom;
import com.example.demo.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ChiTietSanPhamRepo extends JpaRepository<ChiTietSanPham, UUID> {
    boolean existsBySanPhamAndChatLieuAndCoAoAndMauSacAndThuongHieuAndKichCo(
            SanPham sanPham, ChatLieu chatLieu, CoAo coAo, MauSac mauSac, ThuongHieu thuongHieu, KichCo kichCo
    );

    @Query(value = "SELECT ctsp FROM ChiTietSanPham ctsp WHERE  (ctsp.sanPham.ten like %?1%) or (ctsp.ma like %?1%)")
    Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.mauSac.id = ?1)")
    Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.kichCo.id = ?1)")
    Page<ChiTietSanPham> searchByKichCo(UUID idKC, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.thuongHieu.id = ?1)")
    Page<ChiTietSanPham> searchByDeGiay(UUID idDe, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.chatLieu.id = ?1)")
    Page<ChiTietSanPham> searchByChatLieu(UUID idCL, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.coAo.id = ?1)")
    Page<ChiTietSanPham> searchByCA(UUID idCA, Pageable pageable);

    @Query(value = "select d from CoAo d where d.ten LIKE :keyword ")
    List<CoAo> search(@Param("keyword") String keyword);



    @Query(value = "select lg from CoAo lg")
    List<CoAo> listLoaiGiay();

    @Query(value = "select lg from CoAo lg where lg.trangThai=:trangThai")
    List<CoAo> listLG22(@Param("trangThai") Integer trangThai);

    @Query(value = "select de from ThuongHieu de where de.ten LIKE :keyword ")
    List<ThuongHieu> searchDG(@Param("keyword") String keyword);




    @Query(value = "select de from ThuongHieu de")
    List<ThuongHieu> listDeGiay();

    @Query(value = "select d from ThuongHieu d where d.trangThai=:trangThai")
    List<ThuongHieu> listDeGiay22(@Param("trangThai") Integer trangThai);


    @Query(value = "select m from MauSac m where m.ten LIKE :keyword ")
    List<MauSac> searchMS(@Param("keyword") String keyword);

    @Query(value = "select m from MauSac m where m.ten LIKE :keyword and m.trangThai=:trangThai")
    List<MauSac> search22MS(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);

    @Query(value = "select k from KichCo k where k.ten LIKE :keyword and k.trangThai=:trangThai")
    List<KichCo> search22KC(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);

    @Query(value = "select c from ChatLieu c where c.ten LIKE :keyword and c.trangThai=:trangThai")
    List<ChatLieu> search22CL(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);
    //
    @Query(value = "select ca from CoAo ca where ca.ten LIKE :keyword and ca.trangThai=:trangThai")
    List<CoAo> search22LG(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);

    @Query(value = "select de from ThuongHieu de where de.ten LIKE :keyword and de.trangThai=:trangThai")
    List<ThuongHieu> search22DG(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);


    @Query(value = "select m from MauSac m where m.trangThai=:trangThai")
    List<MauSac> listMauSac22(@Param("trangThai") Integer trangThai);

    @Query(value = "select lg from MauSac lg")
    List<MauSac> listMauSac();

    @Query(value = "select c from ChatLieu c where c.ten LIKE :keyword ")
    List<ChatLieu> searchCL(@Param("keyword") String keyword);


    @Query(value = "select lg from ChatLieu lg")
    List<ChatLieu> lítChatLieu();

    @Query(value = "select c from ChatLieu c where c.trangThai=:trangThai")
    List<ChatLieu> listChatLieu22(@Param("trangThai") Integer trangThai);

    @Query(value = "select k from KichCo k")
    List<KichCo> listKC();

    @Query(value = "select k from KichCo k where k.trangThai=:trangThai")
    List<KichCo> listKichCo22(@Param("trangThai") Integer trangThai);

    @Query(value = "select k from KichCo k where (:keyword is null or k.ten = :keyword)")
    List<KichCo> search2KC(@Param("keyword") String size);



    @Query("select ctsp from ChiTietSanPham  ctsp where ctsp.giaBan between ?1 and ?2")
    Page<ChiTietSanPham> getCTSPByGiaBan(Double minPrice, Double maxPrice, Pageable pageable);

    @Query(value = "select * from ChiTietSanPham where IdKichCo = ?1", nativeQuery = true)
    Page<ChiTietSanPham> getCTSPBYKC(UUID idKC, Pageable pageable);

    @Query("SELECT DISTINCT c FROM ChiTietSanPham c " +
            "WHERE c.id IN (SELECT MIN(c2.id) FROM ChiTietSanPham c2 " +
            "GROUP BY c2.sanPham.id, c2.mauSac.id) and c.mauSac.id IN ?1")
    Page<ChiTietSanPham> getCTSPBYMS(UUID idMS, Pageable pageable);

    @Query(value = "select * from ChiTietSanPham where IdSanPham = ?1", nativeQuery = true)
    Page<ChiTietSanPham> getCTSPBYSP(UUID idSP, Pageable pageable);

    @Query(value = "select * from ChiTietSanPham where IdChatLieu = ?1", nativeQuery = true)
    Page<ChiTietSanPham> getCTSPBYCL(UUID idCL, Pageable pageable);

    @Query(value = "select * from ChiTietSanPham where IdCoAo = ?1", nativeQuery = true)
    Page<ChiTietSanPham> getCTSPBYCA(UUID idCA, Pageable pageable);


//    List<KichCo> search2KC(@Param("keyword") Integer keyword);

    // update ctsp modal add
    @Query(value = "select c.IdSanPham from ChiTietSanPham c join SanPham s on c.IdSanPham=s.Id where c.Id=?1", nativeQuery = true)
    UUID getOneToAddModal(UUID id);


    @Query("SELECT DISTINCT c FROM ChiTietSanPham c " +
            "WHERE c.id IN (SELECT MIN(c2.id) FROM ChiTietSanPham c2 " +
            "GROUP BY c2.sanPham.id, c2.mauSac.id) and c.thuongHieu.id IN ?1")
    Page<ChiTietSanPham> searchCTSPByLoaiGiayList(List<UUID> idLoaiGiayList, Pageable pageable);


//    @Query("select h from HinhAnh h where h.ctsp.id = ?1")
//    HinhAnh getHADetail(UUID idCTSP);


    @Query(value = "select * from mausac ms join ChiTietSanPham ctsp on ms.Id = ctsp.IdMauSac " +
            "join SanPham sp on sp.Id = ctsp.IdSP where sp.Id = ?1", nativeQuery = true)
    List<MauSac> getMauBySanPham(UUID idSP);


    // list ctsp theo id
    @Query(value = "select ct from ChiTietSanPham ct where ct.sanPham.id=?1 order by ct.ma desc ")
    Page<ChiTietSanPham> listCTSP(UUID id, Pageable pageable);

    @Query(value = "select kc.ten from kichCo kc join ChiTietSanPham ctsp on kc.Id = ctsp.IdKichCo " +
            "join SanPham sp on sp.Id = ctsp.IdSP where sp.Id = ?1", nativeQuery = true)
    List<String> getKichCoBySanPham(UUID idSP);

    @Query("select kc from KichCo kc where kc.ten = ?1")
    KichCo getKichCoBySize(String size);


    @Query(value = "select kc.ten from kichCo kc join ChiTietSanPham ctsp on ctsp.IdKichCo = kc.Id\n" +
            "where ctsp.IdMauSac = ?1 \n" +
            "and ctsp.IdSP = ?2 and ctsp.TrangThai = 1", nativeQuery = true)
    List<Integer> getKichCoByMauSacAndSanPham(UUID idMS, UUID idSP);

    @Query(value = "select ctsp.soLuongTon from ChiTietSanPham ctsp join KichCo kc on kc.Id = ctsp.IdKichCo\n" +
            "            where ctsp.IdMauSac = ?1\n" +
            "            and ctsp.IdSP = ?2 \n " +
            "\t\t\tand kc.ten = ?3 ", nativeQuery = true)
    String getSoLuongByKichCo(UUID idMS, UUID idSP, String size);

    @Query(value = "SELECT ctsp.*, kc.Id AS KichCoId FROM ChiTietSanPham ctsp " +
            "JOIN KichCo kc ON kc.Id = ctsp.IdKichCo " +
            "WHERE ctsp.IdMauSac = :idMS AND ctsp.IdSP = :idSP AND kc.ten = :size", nativeQuery = true)
    ChiTietSanPham getCTSPByKichCo(
            @Param("idMS") UUID idMS,
            @Param("idSP") UUID idSP,
            @Param("size") String size
    );

    @Query(value = "SELECT * FROM ChiTietSanPham c1 " +
            "WHERE c1.trangThai = 0 AND NOT EXISTS (" +
            "    SELECT 1 FROM ChiTietSanPham c2 " +
            "    WHERE c1.idSanPham = c2.idSanPham " +
            "        AND c1.idMauSac = c2.idMauSac " +
            "        AND c1.id > c2.id" +
            "        AND c2.trangThai = 0" +
            ")", nativeQuery = true)
    Page<ChiTietSanPham> get1CTSPByMauSac(Pageable pageable);

    @Query(value = "select * from chitietsanpham where IdSP = ?1 \n" +
            "and IdMauSac = ?2 \n" +
            "and IdKichCo = ?3", nativeQuery = true)
    ChiTietSanPham findctspAddCart(UUID idSP, UUID idMS, UUID idKC);


    @Query(value = "select count(*) from giohangchitiet join giohang on giohangchitiet.idgiohang = giohang.id\n" +
            "where giohang.IdKH = ?1", nativeQuery = true)
    Integer getSLGioHang(UUID idKH);


    @Query(value = "select ctsp from ChiTietSanPham  ctsp where  ctsp.soLuongTon > 0 and ctsp.trangThai = 1")
    List<ChiTietSanPham> getListCTSPSuDung();

    @Query(value = "select soluong from giohangchitiet" +
            " where IdChiTietSP = ?1 and IdGioHang = ?2", nativeQuery = true)
    Integer getSLGioHangBySPAndGH(UUID idCTSP, UUID idGH);


    @Query("SELECT c FROM ChiTietSanPham c " +
            "WHERE c.id IN (SELECT MIN(c2.id) FROM ChiTietSanPham c2 " +
            "GROUP BY c2.mauSac.id, c2.sanPham.id HAVING c2.sanPham.id = ?1)")
    List<ChiTietSanPham> getCTSPByIdSP(UUID idSP);

    @Query(value = "select ctsp.id, sp.TenSanPham,ctsp.GiaBan,ctsp.SoLuongTon, ms.ten, kc.ten, cl.ten, dg.ten\n" +
            "from ChiTietSanPham ctsp join SanPham sp on ctsp.IdSP = sp.Id\n" +
            "\t\t\t\t\t\t join MauSac ms on ctsp.IdMauSac = ms.Id\n" +
            "\t\t\t\t\t\t join KichCo kc on ctsp.IdKichCo= kc.Id\n" +
            "\t\t\t\t\t\t join ChatLieu cl on ctsp.IdChatLieu = cl.Id\n" +
            "\t\t\t\t\t\t join ThuongHieu dg on ctsp.ThuongHieu = dg.Id\n" +
            "where ctsp.soLuongTon > 0 and ctsp.GiaBan = ?1 and ctsp.TrangThai = 1", nativeQuery = true)
    List<ChiTietSanPhamCustom> listSanPhamCungLoai(Double giaSP);

    @Query(value = "select ctsp.id, sp.TenSanPham,ctsp.GiaBan,ctsp.SoLuongTon, ms.ten, kc.ten, cl.ten, dg.ten\n" +
            "from ChiTietSanPham ctsp join SanPham sp on ctsp.IdSP = sp.Id\n" +
            "\t\t\t\t\t\t join MauSac ms on ctsp.IdMauSac = ms.Id\n" +
            "\t\t\t\t\t\t join KichCo kc on ctsp.IdKichCo= kc.Id\n" +
            "\t\t\t\t\t\t join ChatLieu cl on ctsp.IdChatLieu = cl.Id\n" +
            "\t\t\t\t\t\t join ThuongHieu dg on ctsp.ThuongHieu = dg.Id\n" +
            "where ctsp.soLuongTon > 0 and sp.Id = ?1 and ctsp.TrangThai = 1", nativeQuery = true)
    List<ChiTietSanPhamCustom> listChiTietSanPhamKM(UUID idSanPham);

    @Query(value = "select ctsp from ChiTietSanPham ctsp where ctsp.trangThai = 1 and ctsp.soLuongTon > 0")
    List<ChiTietSanPham> getListCTSPByTrangThaiAndSoLuong();

    @Query(value = "select ctsp from ChiTietSanPham ctsp order by ctsp.ma desc ")
    Page<ChiTietSanPham> getListSP(Pageable pageable);


    boolean existsBySanPhamAndCoAoAndChatLieuAndMauSacAndThuongHieuAndKichCo(
            SanPham sanPham, CoAo coAo, ChatLieu chatLieu, MauSac mauSac, ThuongHieu thuongHieu, KichCo kichCo
    );

    @Query("SELECT cts FROM ChiTietSanPham cts " +
            "WHERE cts.sanPham = :sanPham " +
            "AND (:mauSac IS NULL OR cts.mauSac = :mauSac) " +
            "AND (:kichCo IS NULL OR cts.kichCo = :kichCo) " +
            "AND (:chatLieu IS NULL OR cts.chatLieu = :chatLieu) " +
            "AND (:deGiay IS NULL OR cts.coAo = :coAo) " +
            "AND (:loaiGiay IS NULL OR cts.thuongHieu = :thuongHieu)")
    List<ChiTietSanPham> findBySanPhamAndMauSacAndKichCoAndChatLieuAndDeGiayAndLoaiGiay(
            @Param("sanPham") SanPham sanPham,
            @Param("mauSac") MauSac mauSac,
            @Param("kichCo") KichCo kichCo,
            @Param("chatLieu") ChatLieu chatLieu,
            @Param("deGiay") ThuongHieu thuongHieu,
            @Param("coAo") CoAo coAo);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.soLuongTon > 0 and ctsp.trangThai = 1")
    List<ChiTietSanPham> ctspConHang();

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.sanPham.id = ?1 order by ctsp.ma desc")
    List<ChiTietSanPham> listCTSPByIDSP(UUID id);

    @Query(value = "select * from chitietsanpham where idMauSac = ?1 " +
            "and idSanPham = ?2\n", nativeQuery = true)
    List<ChiTietSanPham> getCTSPBYSPAndMauSac(UUID idMS, UUID idSP);

    ChiTietSanPham findFirstBySanPhamAndChatLieuAndCoAoAndMauSacAndThuongHieuAndKichCo(
            SanPham sanPham, ChatLieu chatLieu, CoAo coAo, MauSac mauSac, ThuongHieu thuongHieu, KichCo kichCo
    );

    @Query(value = "SELECT \n" +
            "    CSP.Id,\n" +
            "FROM \n" +
            "    ChiTietSanPham CSP\n" +
            "JOIN \n" +
            "    ChiTietKhuyenMai CTKM ON CSP.Id = CTKM.IdChiTietSanPham\n" +
            "JOIN \n" +
            "    KhuyenMai KM ON CTKM.IdKhuyenMai = KM.Id\n" +
            "WHERE CSP.GiaBan * 0.7 <= ?1 AND TrangThai = 0 AND CSP.SoLuongTon > 0", nativeQuery = true)
    List<ChiTietSanPham> listCTSPDuDieuKien(Integer giaTriKhuyenMai);

    @Query("select c from ChiTietSanPham c  where c.trangThai=0 order by c.ma desc")
    List<ChiTietSanPham> findAllCTSP();

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

    @Query("select i from ChiTietSanPham i where i.id=:id")
    List<ChiTietSanPham> showQR(UUID id);

}