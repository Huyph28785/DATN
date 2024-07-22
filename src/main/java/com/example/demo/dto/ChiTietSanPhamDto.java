package com.example.demo.dto;


import com.example.demo.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChiTietSanPhamDto {

    private UUID id;
    private String maQr;
    private SanPham sanPham;
    private MauSac mauSac;
    private CoAo coAo;
    private KichCo kichCo;
    private ChatLieu chatLieu;
    private ThuongHieu thuongHieu;
    private Double giaBan;
    private Integer soLuong;
    private String moTaCT;
    private Integer trangThai;
    private String hinhAnh;
}
