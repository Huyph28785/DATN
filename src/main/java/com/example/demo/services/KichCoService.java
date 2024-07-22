package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface KichCoService {
    public Page<KichCo> getAll(Pageable pageable);

    public Page<KichCo> getAll1(Pageable pageable);

    public List<KichCo> findAll();

    public List<KichCo> findAllFullTT();

    public KichCo findById(UUID id);

    public KichCo add(KichCo kichCo);

    public KichCo update(UUID id, KichCo kichCo);

    public void updateTT();

    public List<KichCo> search0(String ten);

    public List<KichCo> search1(String ten);

    KichCo findByMa(String ma);

    KichCo findByTen(String ten);
}
