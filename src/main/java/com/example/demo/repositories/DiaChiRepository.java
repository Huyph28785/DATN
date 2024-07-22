package com.example.demo.repositories;

import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, UUID> {
    @Query("select c from DiaChi c  where c.trangThai=0 order by c.ma desc ")
    Page<DiaChi> getAll(Pageable pageable);

    @Query("select c from DiaChi c  where c.trangThai=1 order by c.ma desc ")
    Page<DiaChi> getAll1(Pageable pageable);

    @Query("select c from DiaChi c  where  c.trangThai = 0")
    List<DiaChi> findAll0();

    @Query("select c from DiaChi c  where  c.trangThai = 0 and (c.ma like %:ten% or c.thanhPho like %:ten%)")
    List<DiaChi> search0(String ten);

    @Query("select c from DiaChi c  where  c.trangThai = 1 and (c.ma like %:ten% or c.thanhPho like %:ten%)")
    List<DiaChi> search1(String ten);

    @Query("select c from DiaChi c  where  c.trangThai = 0 and c.khachHang.id=:id")
    List<DiaChi> danhSachDiaChi(UUID id);

    @Transactional
    @Modifying
    @Query(value = "update DiaChi set trangThai=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
}
