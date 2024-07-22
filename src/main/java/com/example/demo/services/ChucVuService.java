package com.example.demo.services;

import com.example.demo.models.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChucVuService {
    public Page<ChucVu> getAll(Pageable pageable);

    public Page<ChucVu> getAll1(Pageable pageable);

    public List<ChucVu> findAll();
    public List<ChucVu> findAllFullTT();

    public ChucVu findById(UUID id);

    public ChucVu add(ChucVu chucVu);

    public ChucVu update(UUID id,ChucVu chucVu);

    public void updateTT();

    public List<ChucVu> search0(String ten);

    public List<ChucVu> search1(String ten);
}
