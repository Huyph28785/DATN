package com.example.demo.services.impl;

import com.example.demo.models.KichCo;
import com.example.demo.repositories.KichCoRepository;
import com.example.demo.repositories.KichCoRepository;
import com.example.demo.services.KichCoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KichCoServiceImpl implements KichCoService {

    @Autowired
    private KichCoRepository kichCoRepo;

    @Override
    public Page<KichCo> getAll(Pageable pageable) {
        return kichCoRepo.getAll(pageable);
    }

    @Override
    public Page<KichCo> getAll1(Pageable pageable) {
        return kichCoRepo.getAll1(pageable);
    }

    @Override
    public List<KichCo> findAll() {
        return kichCoRepo.findAll0();
    }

    @Override
    public List<KichCo> findAllFullTT() {
        return kichCoRepo.findAll();
    }

    @Override
    public KichCo findById(UUID id) {
        return kichCoRepo.findById(id).orElse(null);
    }

    @Override
    public KichCo add(KichCo kichCo) {
        return kichCoRepo.save(kichCo);
    }

    @Override
    public KichCo update(UUID id,KichCo kichCo) {
        if (id != null) {
            KichCo kichCoUpdate = kichCoRepo.findById(id).orElse(null);
            if (kichCoUpdate != null) {
                BeanUtils.copyProperties(kichCo, kichCoUpdate);
                kichCoRepo.save(kichCoUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<KichCo> search0(String ten) {
        return kichCoRepo.search0(ten);
    }

    @Override
    public List<KichCo> search1(String ten) {
        return kichCoRepo.search1(ten);
    }

    @Override
    public KichCo findByMa(String ma) {
        return kichCoRepo.findKichCoByMa(ma);
    }

    @Override
    public KichCo findByTen(String ten) {
        return kichCoRepo.findKichCoByTen(ten);
    }
}
