package com.example.demo.services.impl;

import com.example.demo.models.ChucVu;
import com.example.demo.repositories.ChucVuRepository;
import com.example.demo.repositories.ChucVuRepository;
import com.example.demo.services.ChucVuService;
import com.example.demo.services.ChucVuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepo;

    @Override
    public Page<ChucVu> getAll(Pageable pageable) {
        return chucVuRepo.getAll(pageable);
    }

    @Override
    public Page<ChucVu> getAll1(Pageable pageable) {
        return chucVuRepo.getAll1(pageable);
    }

    @Override
    public List<ChucVu> findAll() {
        return chucVuRepo.findAll0();
    }

    @Override
    public List<ChucVu> findAllFullTT() {
        return chucVuRepo.findAll();
    }

    @Override
    public ChucVu findById(UUID id) {
        return chucVuRepo.findById(id).orElse(null);
    }

    @Override
    public ChucVu add(ChucVu chucVu) {
        return chucVuRepo.save(chucVu);
    }

    @Override
    public ChucVu update(UUID id,ChucVu chucVu) {
        if (id != null) {
            ChucVu chucVuUpdate = chucVuRepo.findById(id).orElse(null);
            if (chucVuUpdate != null) {
                BeanUtils.copyProperties(chucVu, chucVuUpdate);
                chucVuRepo.save(chucVuUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<ChucVu> search0(String ten) {
        return chucVuRepo.search0(ten);
    }

    @Override
    public List<ChucVu> search1(String ten) {
        return chucVuRepo.search1(ten);
    }
}
