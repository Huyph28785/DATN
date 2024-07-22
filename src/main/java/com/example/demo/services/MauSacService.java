package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MauSacService {
    public Page<MauSac> getAll(Pageable pageable);

    public Page<MauSac> getAll1(Pageable pageable);

    public List<MauSac> findAll();

    public List<MauSac> findAllFullTT();

    public MauSac findById(UUID id);

    public MauSac add(MauSac mauSac);

    public MauSac update(UUID id, MauSac mauSac);

    public void updateTT();

    public List<MauSac> search0(String ten);

    public List<MauSac> search1(String ten);

    MauSac findByMa(String ma);

    MauSac findByTen(String ten);
}
