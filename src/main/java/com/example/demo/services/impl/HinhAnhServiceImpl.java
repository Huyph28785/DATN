package com.example.demo.services.impl;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HinhAnh;
import com.example.demo.models.SanPham;
import com.example.demo.repositories.ChiTietSanPhamRepo;
import com.example.demo.repositories.HinhAnhRepository;
import com.example.demo.services.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {

    @Autowired
    HinhAnhRepository repository;

    @Autowired
    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @Value("src/main/webapp/uploads/")
    private String imageUploadPath;

    // Các phương thức khác trong dịch vụ
    @Override
    public String getImageUploadPath() {
        return imageUploadPath;
    }

    @Override
    public Page<HinhAnh> listHinhAnh(Pageable pageable) {
        return repository.listHinhAnh(pageable);
    }

    @Override
    public UUID getIdHinhAnh(UUID keyword) {
        return repository.getIdHinhAnh(keyword);
    }

    @Override
    public HinhAnh getHinhAnh(UUID id) {
        return repository.getHinhAnh(id);
    }

    @Override
    public UUID getIdCTSP(UUID id) {
        return repository.getIdCTSP(id);
    }

    @Override
    public SanPham getSanPhamByIDCTSP(UUID idCTSP) {
        return repository.getSanPhamByIDCTSP(idCTSP);
    }

    @Override
    public List<HinhAnh> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ChiTietSanPham> getAllCTSP() {
        return chiTietSanPhamRepo.findAll();
    }

    @Override
    public List<ChiTietSanPham> searchCTSP(UUID keyword) {
        return repository.searchCTSP(keyword);
    }

    @Override
    public HinhAnh findById(UUID id) {
        return repository.findId(id);
    }

}
