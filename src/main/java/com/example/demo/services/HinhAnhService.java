package com.example.demo.services;


import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HinhAnh;
import com.example.demo.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface HinhAnhService {

    List<HinhAnh> getAll();

    List<ChiTietSanPham> getAllCTSP();

    List<ChiTietSanPham> searchCTSP(UUID keyword);

    HinhAnh findById(UUID id);

    String getImageUploadPath();

    Page<HinhAnh> listHinhAnh(Pageable pageable);

    UUID getIdHinhAnh(UUID keyword);

    HinhAnh getHinhAnh(UUID id);

    UUID getIdCTSP(UUID id);

    SanPham getSanPhamByIDCTSP(UUID idCTSP);
}
