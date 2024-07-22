package com.example.demo.repositories;

import com.example.demo.models.ChucVu;
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
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {
    @Query("select c from ChucVu c  where c.tinhTrang=0 order by c.ngayTao desc ")
    Page<ChucVu> getAll(Pageable pageable);

    @Query("select c from ChucVu c  where c.tinhTrang=1 order by c.ngayTao desc ")
    Page<ChucVu> getAll1(Pageable pageable);

    @Query("select c from ChucVu c  where  c.tinhTrang = 0")
    List<ChucVu> findAll0();

    @Query("select c from ChucVu c  where  c.tinhTrang = 0 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<ChucVu> search0(String ten);

    @Query("select c from ChucVu c  where  c.tinhTrang = 1 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<ChucVu> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update ChucVu set tinhTrang=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
}
