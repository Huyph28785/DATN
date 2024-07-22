package com.example.demo.services.impl;

import com.example.demo.models.CoAo;
import com.example.demo.repositories.CoAoRepository;
import com.example.demo.services.CoAoService;
import com.example.demo.services.CoAoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CoAoServiceImpl implements CoAoService {

    @Autowired
    private CoAoRepository coAoRepo;

    @Override
    public Page<CoAo> getAll(Pageable pageable) {
        return coAoRepo.getAll(pageable);
    }

    @Override
    public Page<CoAo> getAll1(Pageable pageable) {
        return coAoRepo.getAll1(pageable);
    }

    @Override
    public List<CoAo> findAll() {
        return coAoRepo.findAll0();
    }

    @Override
    public List<CoAo> findAllFullTT() {
        return coAoRepo.findAll();
    }

    @Override
    public CoAo findById(UUID id) {
        return coAoRepo.findById(id).orElse(null);
    }

    @Override
    public CoAo add(CoAo coAo) {
        return coAoRepo.save(coAo);
    }

    @Override
    public CoAo update(UUID id,CoAo coAo) {
        if (id != null) {
            CoAo coAoUpdate = coAoRepo.findById(id).orElse(null);
            if (coAoUpdate != null) {
                BeanUtils.copyProperties(coAo, coAoUpdate);
                coAoRepo.save(coAoUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<CoAo> search0(String ten) {
        return coAoRepo.search0(ten);
    }

    @Override
    public List<CoAo> search1(String ten) {
        return coAoRepo.search1(ten);
    }

    @Override
    public CoAo findByMa(String ma) {
        return coAoRepo.findCoAoByMa(ma);
    }

    @Override
    public CoAo findByTen(String ten) {
        return coAoRepo.findCoAoByTen(ten);
    }
}
