package com.example.demo.services;

import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DiaChiService {
    public Page<DiaChi> getAll(Pageable pageable);

    public Page<DiaChi> getAll1(Pageable pageable);

    public List<DiaChi> findAll();

    public List<DiaChi> findAllFullTT();

    public DiaChi findById(UUID id);

    public DiaChi add(DiaChi diaChi);

    public DiaChi update(UUID id, DiaChi diaChi);

    public void updateTT();

    public List<DiaChi> search0(String ten);

    public List<DiaChi> search1(String ten);

    List<DiaChi> danhSachDiaChi(UUID id);
}
