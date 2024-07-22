package com.example.demo.services.impl;

import com.example.demo.models.NhanVien;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.NhanVienService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepo;

    @Override
    public Page<NhanVien> getAll(Pageable pageable) {
        return nhanVienRepo.getAll(pageable);
    }

    @Override
    public Page<NhanVien> getAll1(Pageable pageable) {
        return nhanVienRepo.getAll1(pageable);
    }

    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepo.findAll0();
    }

    @Override
    public List<NhanVien> findAllFullTT() {
        return nhanVienRepo.findAll();
    }

    @Override
    public NhanVien findById(UUID id) {
        return nhanVienRepo.findById(id).orElse(null);
    }

    @Override
    public NhanVien add(NhanVien nhanVien) {
        return nhanVienRepo.save(nhanVien);
    }

    @Override
    public NhanVien update(UUID id,NhanVien nhanVien) {
        if (id != null) {
            NhanVien nhanVienUpdate = nhanVienRepo.findById(id).orElse(null);
            if (nhanVienUpdate != null) {
                BeanUtils.copyProperties(nhanVien, nhanVienUpdate);
                nhanVienRepo.save(nhanVienUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<NhanVien> search0(String ten) {
        return nhanVienRepo.search0(ten);
    }

    @Override
    public List<NhanVien> search1(String ten) {
        return nhanVienRepo.search1(ten);
    }

    @Override
    public boolean existNhanVienByEmail(String email) {
        return nhanVienRepo.existsNhanVienByEmail(email);
    }

    @Override
    public boolean existNhanVienBySDT(String sdt) {
        return nhanVienRepo.existsNhanVienBySdt(sdt);
    }

    @Override
    public boolean existNhanVienByCCCD(String cccd) {
        return nhanVienRepo.existsNhanVienByCCCD(cccd);
    }

    @Override
    public List<NhanVien> locTT(int tinhTrang) {
        return nhanVienRepo.findAllByTinhTrang(tinhTrang);
    }

    @Override
    public Page<NhanVien> locGT(Boolean gioiTinh,Pageable pageable) {
        return nhanVienRepo.findAllByGioiTinh(gioiTinh,pageable);
    }

    @Override
    public List<NhanVien> findAllByTinhTrangAndGioiTinh(int tinhTrang, Boolean gioiTinh) {
        return nhanVienRepo.findAllByTinhTrangAndGioiTinh(tinhTrang, gioiTinh);
    }

    @Override
    public Page<NhanVien> searchByTenChucVuAndGioiTinh(String tenChucVu, Boolean gioiTinh, int tinhTrang,Pageable pageable) {
        return nhanVienRepo.searchByTenChucVuAndGioiTinh(tenChucVu, gioiTinh,tinhTrang,pageable);
    }

    @Override
    public NhanVien getByTaiKhoan(String gmail) {
        return nhanVienRepo.findByTaiKhoan(gmail).get();
    }
}
