package com.example.demo.services.impl;

import com.example.demo.models.ThuongHieu;
import com.example.demo.repositories.ThuongHieuRepository;
import com.example.demo.repositories.ThuongHieuRepository;
import com.example.demo.services.ThuongHieuService;
import com.example.demo.services.ThuongHieuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepo;

    @Override
    public Page<ThuongHieu> getAll(Pageable pageable) {
        return thuongHieuRepo.getAll(pageable);
    }

    @Override
    public Page<ThuongHieu> getAll1(Pageable pageable) {
        return thuongHieuRepo.getAll1(pageable);
    }

    @Override
    public List<ThuongHieu> findAll() {
        return thuongHieuRepo.findAll0();
    }

    @Override
    public List<ThuongHieu> findAllFullTT() {
        return thuongHieuRepo.findAll();
    }

    @Override
    public ThuongHieu findById(UUID id) {
        return thuongHieuRepo.findById(id).orElse(null);
    }

    @Override
    public ThuongHieu add(ThuongHieu thuongHieu) {
        return thuongHieuRepo.save(thuongHieu);
    }

    @Override
    public ThuongHieu update(UUID id, ThuongHieu thuongHieu) {
        if (id != null) {
            ThuongHieu thuongHieuUpdate = thuongHieuRepo.findById(id).orElse(null);
            if (thuongHieuUpdate != null) {
                BeanUtils.copyProperties(thuongHieu, thuongHieuUpdate);
                thuongHieuRepo.save(thuongHieuUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<ThuongHieu> search0(String ten) {
        return thuongHieuRepo.search0(ten);
    }

    @Override
    public List<ThuongHieu> search1(String ten) {
        return thuongHieuRepo.search1(ten);
    }

    @Override
    public ThuongHieu findByMa(String ma) {
        return thuongHieuRepo.findThuongHieuByMa(ma);
    }

    @Override
    public ThuongHieu findByTen(String ten) {
        return thuongHieuRepo.findThuongHieuByTen(ten);
    }
}
