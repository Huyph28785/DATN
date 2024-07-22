package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.CoAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CoAoService {
    public Page<CoAo> getAll(Pageable pageable);

    public Page<CoAo> getAll1(Pageable pageable);

    public List<CoAo> findAll();

    public List<CoAo> findAllFullTT();

    public CoAo findById(UUID id);

    public CoAo add(CoAo coAo);

    public CoAo update(UUID id, CoAo coAo);

    public void updateTT();

    public List<CoAo> search0(String ten);

    public List<CoAo> search1(String ten);

    CoAo findByMa(String ma);

    CoAo findByTen(String ten);
}
