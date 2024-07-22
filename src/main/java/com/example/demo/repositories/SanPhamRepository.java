package com.example.demo.repositories;


import com.example.demo.dto.SanPhamCustom;
import com.example.demo.dto.SanPhamDto;
import com.example.demo.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("SELECT s FROM SanPham s WHERE s.ten = :tensp")
    SanPham findByTenSp(@Param("tensp") String tensp);

//    @Query("select  sp from SanPham  sp where sp.trangThai=0 order by sp.ngayTao desc")
//    Page<SanPham> findALlSP(Pageable pageable);

    @Query("select  p from SanPham  p where  p.ma =?1")
    SanPham getSanPhamByMaSP(String masp);

    @Query("select p from SanPham p  where p.ma = ?1 or p.ten = ?1")
    Page<SanPham> findAllByKeWord(String keyword,Pageable pageable);

    @Query("select p from SanPham p  where p.ma = ?1 or p.ten = ?1")
    List<SanPham> searchSanPham(String keyword);

    @Query("select c from SanPham c  where c.trangThai=0 order by c.ma desc ")
    Page<SanPham> getAll(Pageable pageable);

    @Query("select c from SanPham c  where c.trangThai=1 order by c.ma desc ")
    Page<SanPham> getAll1(Pageable pageable);

    @Query("select c from SanPham c  where  c.trangThai = 0")
    List<SanPham> findAll0();

//    @Query("select c from SanPham c  where  c.trangThai = 0 and (c.ma like %:ten% or c.ten like %:ten%)")
   @Query(value = "select SanPham.Id as Id, SanPham.Ma as MaSanPham, SanPham.Ten as TenSanPham," +
        " count(IdSanPham) as SoLuong, SanPham.TrangThai as TrangThai " +
        "from SanPham left join ChiTietSanPham on ChiTietSanPham.IdSanPham= SanPham.Id " +
        "where (SanPham.Ma like %:ten% or SanPham.Ten like %:ten%) group by SanPham.Id,SanPham.Ma, SanPham.Ten, SanPham.TrangThai",nativeQuery = true)
    List<SanPhamCustom> search0(String ten);

//    @Query("select c from SanPham c  where  c.trangThai = 1 and (c.ma like %:ten% or c.ten like %:ten%)")
@Query(value = "select SanPham.Id as Id, SanPham.Ma as MaSanPham, SanPham.Ten as TenSanPham," +
        " count(IdSanPham) as SoLuong, SanPham.TrangThai as TrangThai " +
        "from SanPham left join ChiTietSanPham on ChiTietSanPham.IdSanPham= SanPham.Id " +
        "where SanPham.TrangThai=1 and (SanPham.Ma like %:ten% or SanPham.Ten like %:ten%) group by SanPham.Id,SanPham.Ma, SanPham.Ten, SanPham.TrangThai",nativeQuery = true)
    List<SanPhamCustom> search1(String ten);

//    @Query("select c from SanPham c  where  c.trangThai =:trangThai")
   @Query(value = "select SanPham.Id as Id, SanPham.Ma as MaSanPham, SanPham.Ten as TenSanPham," +
        " count(IdSanPham) as SoLuong, SanPham.TrangThai as TrangThai " +
        "from SanPham left join ChiTietSanPham on ChiTietSanPham.IdSanPham= SanPham.Id " +
        "where SanPham.TrangThai=:trangThai group by SanPham.Id,SanPham.Ma, SanPham.Ten, SanPham.TrangThai " +
        "order by SanPham.Ma desc",nativeQuery = true)
    Page<SanPhamCustom> findAllByTrangThai(int trangThai, Pageable pageable);

    boolean existsSanPhamByTen(String ten);

    @Query(value = "select SanPham.Id as Id, SanPham.Ma as MaSanPham, SanPham.Ten as TenSanPham," +
            " count(IdSanPham) as SoLuong, SanPham.TrangThai as TrangThai " +
            "from SanPham left join ChiTietSanPham on ChiTietSanPham.IdSanPham= SanPham.Id " +
            "where SanPham.TrangThai=0 group by SanPham.Id,SanPham.Ma, SanPham.Ten, SanPham.TrangThai " +
            "order by SanPham.Ma desc",nativeQuery = true)
    Page<SanPhamCustom> findALlSP(Pageable pageable);


}
