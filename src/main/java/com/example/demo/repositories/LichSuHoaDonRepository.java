package com.example.demo.repositories;

import com.example.demo.models.LichSuHoaDon;
import com.example.demo.models.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, UUID> {
    @Query("select ls from LichSuHoaDon ls where ls.hoaDon.id =:id order by ls.thoiGian desc ")
    List<LichSuHoaDon> findByHoaDon(UUID id);
}
