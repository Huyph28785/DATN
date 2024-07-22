package com.example.demo.repositories;

import com.example.demo.models.KichCo;
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
public interface KichCoRepository extends JpaRepository<KichCo, UUID> {
    @Query("select c from KichCo c  where c.trangThai=0 order by c.ma desc ")
    Page<KichCo> getAll(Pageable pageable);

    @Query("select c from KichCo c  where c.trangThai=1 order by c.ma desc ")
    Page<KichCo> getAll1(Pageable pageable);

    @Query("select c from KichCo c  where  c.trangThai = 0")
    List<KichCo> findAll0();

    @Query("select c from KichCo c  where  c.trangThai = 0 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<KichCo> search0(String ten);

    @Query("select c from KichCo c  where  c.trangThai = 1 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<KichCo> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update KichCo set trangThai=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();

    KichCo findKichCoByMa(String ma);
    KichCo findKichCoByTen(String ten);
}
