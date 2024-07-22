package com.example.demo.services.impl;

import com.example.demo.models.PhieuGiamGia;
import com.example.demo.repositories.PhieuGiamGiaRepository;
import com.example.demo.services.PhieuGiamGiaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PhieuGiamGiaServiceImpl implements PhieuGiamGiaService {

    @Autowired
    private PhieuGiamGiaRepository phieuRepo;


    @Override
    public List<PhieuGiamGia> getAll() {
        return phieuRepo.getAll();
    }

    @Override
    public Page<PhieuGiamGia> getAll1(Pageable pageable) {
        return phieuRepo.getAll1(pageable);
    }

    @Override
    public List<PhieuGiamGia> findAll() {
        return phieuRepo.findAll0();
    }

    @Override
    public List<PhieuGiamGia> findAllFullTT() {
        return phieuRepo.findAll();
    }

    @Override
    public PhieuGiamGia findById(UUID id) {
        return phieuRepo.findById(id).orElse(null);
    }

    @Override
    public PhieuGiamGia add(PhieuGiamGia phieu) {
        return phieuRepo.save(phieu);
    }

    @Override
    public PhieuGiamGia update(UUID id,PhieuGiamGia phieu) {
        if (id != null) {
            PhieuGiamGia phieuUpdate = phieuRepo.findById(id).orElse(null);
            if (phieuUpdate != null) {
                BeanUtils.copyProperties(phieu, phieuUpdate);
                phieuRepo.save(phieuUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<PhieuGiamGia> search0(String ten) {
        return phieuRepo.search0(ten);
    }

    @Override
    public List<PhieuGiamGia> search1(String ten) {
        return phieuRepo.search1(ten);
    }

    @Override
    public List<PhieuGiamGia> loc(Integer tt, Date startDate, Date endDate) {
        return phieuRepo.loc(tt, startDate, endDate);
    }
}
