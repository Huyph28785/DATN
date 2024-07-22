package com.example.demo.repositories;

import com.example.demo.models.PhieuGiamGia;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, UUID> {
    @Query("select c from PhieuGiamGia c order by c.ngayTao desc ")
    List<PhieuGiamGia> getAll();

    @Query("select c from PhieuGiamGia c  where c.trangThai=1 order by c.ngayTao desc ")
    Page<PhieuGiamGia> getAll1(Pageable pageable);

    @Query("select c from PhieuGiamGia c  where  c.trangThai = 0")
    List<PhieuGiamGia> findAll0();

    @Query("select c from PhieuGiamGia c  where c.ma like %:ten% or c.ten like %:ten%")
    List<PhieuGiamGia> search0(String ten);

    @Query("select c from PhieuGiamGia c  where  c.trangThai = 1 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<PhieuGiamGia> search1(String ten);

    @Query("select c from PhieuGiamGia c  where " +
            "( :tt is null or c.trangThai =:tt)" +
            "and ((:startDate IS NULL OR :endDate IS NULL) OR c.ngayTao >= COALESCE(:startDate, c.ngayTao)" +
            "and c.ngayTao <= COALESCE(:endDate, c.ngayTao))")
    List<PhieuGiamGia> loc(Integer tt,
                           @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                           @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate);

    @Transactional
    @Modifying
    @Query(value = "update PhieuGiamGia set trangThai=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
}
