package com.example.demo.repositories;

import com.example.demo.models.CoAo;
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
public interface CoAoRepository extends JpaRepository<CoAo, UUID> {
    @Query("select c from CoAo c  where c.trangThai=0 order by c.ma desc ")
    Page<CoAo> getAll(Pageable pageable);

    @Query("select c from CoAo c  where c.trangThai=1 order by c.ma desc ")
    Page<CoAo> getAll1(Pageable pageable);

    @Query("select c from CoAo c  where  c.trangThai = 0")
    List<CoAo> findAll0();

    @Query("select c from CoAo c  where  c.trangThai = 0 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<CoAo> search0(String ten);

    @Query("select c from CoAo c  where  c.trangThai = 1 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<CoAo> search1(String ten);

    @Transactional
    @Modifying
    @Query(value = "update CoAo set trangThai=0, ngaySua=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();

    CoAo findCoAoByMa(String ma);
    CoAo findCoAoByTen(String ten);
}
