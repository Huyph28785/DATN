package com.example.demo.services.impl;


import com.example.demo.dto.SanPhamCustom;
import com.example.demo.dto.SanPhamDto;
import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.SanPham;
import com.example.demo.repositories.ChiTietSanPhamRepo;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.services.SanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    private ChiTietSanPhamRepo chiTietSanPhamRepository;
    @Override
    public List<SanPham> getList() {
        return sanPhamRepository.findAll();
    }

    @Override
    public List<SanPham> findAll() {
        return sanPhamRepository.findAll0();
    }

    @Override
    public Page<SanPhamCustom> findAllSP(Pageable pageable) {
        return sanPhamRepository.findALlSP(pageable);
    }

    @Override
    public Page<SanPham> findByKeyword(String key, Pageable pageable) {
        return sanPhamRepository.findAllByKeWord(key,pageable);
    }

    @Override
    public SanPham addSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham) ;
    }

    @Override
    public SanPham udpateSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham getOne(UUID id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public SanPham getByMa(String ma) {
        return sanPhamRepository.getSanPhamByMaSP(ma);
    }

    @Override
    public List<SanPham> searchSanPham(String keyword) {
        return sanPhamRepository.searchSanPham(keyword);
    }

    @Override
    public List<ChiTietSanPham> findAllCTSP() {
        return chiTietSanPhamRepository.findAllCTSP();
    }

    @Override
    public ChiTietSanPham findCTSPById(UUID id) {
        return chiTietSanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public ChiTietSanPham updateCTSP(UUID id, ChiTietSanPham chiTietSanPham) {
        if (id != null) {
            ChiTietSanPham chiTietUpdate = chiTietSanPhamRepository.findById(id).orElse(null);
            if (chiTietUpdate != null) {
                BeanUtils.copyProperties(chiTietSanPham, chiTietUpdate);
                chiTietSanPhamRepository.save(chiTietUpdate);
            }
        }
        return null;
    }

    @Override
    public List<ChiTietSanPham> loc(UUID idSanPham, UUID idChatLieu, UUID idCoAo, UUID idKichCo, UUID idMauSac, UUID idThuongHieu) {
        return chiTietSanPhamRepository.loc(idSanPham, idChatLieu, idCoAo, idKichCo, idMauSac, idThuongHieu);
    }

    @Override
    public ChiTietSanPham scan(String ma) {
        return chiTietSanPhamRepository.scan(ma);
    }

    @Override
    public List<ChiTietSanPham> showQR(UUID id) {
        return chiTietSanPhamRepository.showQR(id);
    }

    @Override
    public List<SanPhamCustom> search0(String ten) {
       return sanPhamRepository.search0(ten);
    }

    @Override
    public List<SanPhamCustom> search1(String ten) {
        return sanPhamRepository.search1(ten);
    }

//    @Override
//    public Page<SanPhamCustom> soLuong(Pageable pageable) {
//        return sanPhamRepository.soLuong(pageable);
//    }


    @Override
    public Page<SanPhamCustom> locTT(int trangThai, Pageable pageable) {
        return sanPhamRepository.findAllByTrangThai(trangThai,pageable);
    }

    @Override
    public boolean existSanPhamByTen(String ten) {
        return sanPhamRepository.existsSanPhamByTen(ten);
    }

    @Override
    public Page<SanPham> getAll1(Pageable pageable) {
        return sanPhamRepository.getAll1(pageable);
    }


}
