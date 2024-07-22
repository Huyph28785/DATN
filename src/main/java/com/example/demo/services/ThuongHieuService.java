package com.example.demo.services;

import com.example.demo.models.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ThuongHieuService {
    public Page<ThuongHieu> getAll(Pageable pageable);

    public Page<ThuongHieu> getAll1(Pageable pageable);

    public List<ThuongHieu> findAll();

    public List<ThuongHieu> findAllFullTT();

    public ThuongHieu findById(UUID id);

    public ThuongHieu add(ThuongHieu thuongHieu);

    public ThuongHieu update(UUID id, ThuongHieu thuongHieu);

    public void updateTT();

    public List<ThuongHieu> search0(String ten);

    public List<ThuongHieu> search1(String ten);

    ThuongHieu findByMa(String ma);

    ThuongHieu findByTen(String ten);

}
