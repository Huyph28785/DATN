package com.example.demo.services;

import com.example.demo.models.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface KhachHangService {
    public Page<KhachHang> getAll(Pageable pageable);

    public Page<KhachHang> getAll1(Pageable pageable);

    public List<KhachHang> findAll();

    public List<KhachHang> findAllFullTT();

    public KhachHang findById(UUID id);

    public KhachHang add(KhachHang khachHang);

    public KhachHang update(UUID id, KhachHang khachHang);

    public void updateTT();

    public List<KhachHang> search0(String ten);

    public List<KhachHang> search1(String ten);
    boolean existKhachHangByEmail(String email);
    boolean existKhachHangBySDT(String sdt);
    List<KhachHang> locTT(int trangThai);
    Page<KhachHang> locGT(Boolean gioiTinh,Pageable pageable);
    Page<KhachHang> findAllByTinhTrangAndGioiTinh(int trangThai, Boolean gioiTinh,Pageable pageable);
    public KhachHang quenMatKhau(String username, String email);
}
