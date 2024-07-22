package com.example.demo.services.impl;

import com.example.demo.models.LichSuHoaDon;
import com.example.demo.repositories.KichCoRepository;
import com.example.demo.repositories.LichSuHoaDonRepository;
import com.example.demo.services.LichSuHoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LichSuHoaDonServiceimpl implements LichSuHoaDonService {
    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;
    @Override
    public List<LichSuHoaDon> getAllLichSuHoaDonByIdHoaDon(UUID id) {
        return lichSuHoaDonRepository.findByHoaDon(id);
    }

    @Override
    public LichSuHoaDon save(LichSuHoaDon lichSuHoaDon) {
        return lichSuHoaDonRepository.save(lichSuHoaDon);
    }
}
