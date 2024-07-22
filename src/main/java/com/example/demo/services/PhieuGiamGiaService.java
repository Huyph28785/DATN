package com.example.demo.services;

import com.example.demo.models.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface PhieuGiamGiaService {
    public List<PhieuGiamGia> getAll();

    public Page<PhieuGiamGia> getAll1(Pageable pageable);

    public List<PhieuGiamGia> findAll();

    public List<PhieuGiamGia> findAllFullTT();

    public PhieuGiamGia findById(UUID id);

    public PhieuGiamGia add(PhieuGiamGia phieuGiamGia);

    public PhieuGiamGia update(UUID id, PhieuGiamGia phieuGiamGia);

    public void updateTT();

    public List<PhieuGiamGia> search0(String ten);

    public List<PhieuGiamGia> search1(String ten);
    List<PhieuGiamGia> loc(Integer tt, Date startDate, Date endDate);

}
