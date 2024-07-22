package com.example.demo.services.impl;

import com.example.demo.models.MauSac;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.services.MauSacService;
import com.example.demo.services.MauSacService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    private MauSacRepository mauSacRepo;

    @Override
    public Page<MauSac> getAll(Pageable pageable) {
        return mauSacRepo.getAll(pageable);
    }

    @Override
    public Page<MauSac> getAll1(Pageable pageable) {
        return mauSacRepo.getAll1(pageable);
    }

    @Override
    public List<MauSac> findAll() {
        return mauSacRepo.findAll0();
    }

    @Override
    public List<MauSac> findAllFullTT() {
        return mauSacRepo.findAll();
    }

    @Override
    public MauSac findById(UUID id) {
        return mauSacRepo.findById(id).orElse(null);
    }

    @Override
    public MauSac add(MauSac mauSac) {
        return mauSacRepo.save(mauSac);
    }

    @Override
    public MauSac update(UUID id,MauSac mauSac) {
        if (id != null) {
            MauSac mauSacUpdate = mauSacRepo.findById(id).orElse(null);
            if (mauSacUpdate != null) {
                BeanUtils.copyProperties(mauSac, mauSacUpdate);
                mauSacRepo.save(mauSacUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<MauSac> search0(String ten) {
        return mauSacRepo.search0(ten);
    }

    @Override
    public List<MauSac> search1(String ten) {
        return mauSacRepo.search1(ten);
    }

    @Override
    public MauSac findByMa(String ma) {
        return mauSacRepo.findMauSacByMa(ma);
    }

    @Override
    public MauSac findByTen(String ten) {
        return mauSacRepo.findMauSacByTen(ten);
    }
}
