package com.example.demo.services.impl;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.services.HoaDonChiTietSerice;
import com.example.demo.services.HoaDonSerice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietSericeImpl implements HoaDonChiTietSerice {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;


    @Override
    public List<HoaDonChiTiet> hoaDonChiTietAll(UUID pageable) {
        return hoaDonChiTietRepository.hoaDonChiTietAll(pageable);
    }

    @Override
    public HoaDonChiTiet add(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTiet update(UUID id, HoaDonChiTiet hoaDonChiTiet) {
        if (id != null) {
            HoaDonChiTiet chiTietUpdate = hoaDonChiTietRepository.findById(id).orElse(null);
            if (chiTietUpdate != null) {
                BeanUtils.copyProperties(hoaDonChiTiet, chiTietUpdate);
                hoaDonChiTietRepository.save(chiTietUpdate);
            }
        }
        return null;
    }

    @Override
    public HoaDonChiTiet findHoaDonChiTiet(UUID id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(id).orElse(null);
            if (hoaDonChiTiet != null) {
                hoaDonChiTietRepository.delete(hoaDonChiTiet);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<HoaDonChiTiet> searchBanTaiQuay(UUID id, String ten) {
        return hoaDonChiTietRepository.searchBanTaiQuay(id, ten);
    }

    @Override
    public HoaDonChiTiet findById(UUID id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public HoaDonChiTiet hdct(UUID idHDCT, UUID idCTSP) {
        return hoaDonChiTietRepository.hdct(idHDCT,idCTSP);
    }

//    @Override
//    public List<HoaDonChiTiet> hoaDonChiTietBanHang(UUID id) {
//        return hoaDonChiTietRepository.hoaDonChiTietBanHang(id);
//    }
}
