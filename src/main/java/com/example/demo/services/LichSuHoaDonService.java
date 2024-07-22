package com.example.demo.services;

import com.example.demo.models.LichSuHoaDon;

import java.util.List;
import java.util.UUID;

public interface LichSuHoaDonService {
    List<LichSuHoaDon> getAllLichSuHoaDonByIdHoaDon(UUID id);

    LichSuHoaDon save(LichSuHoaDon lichSuHoaDon);
}
