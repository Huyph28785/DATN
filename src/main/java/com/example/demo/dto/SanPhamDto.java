package com.example.demo.dto;

import com.example.demo.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SanPhamDto {
    private UUID id;
    private String ma;
    private String ten;
    private Integer soLuong;
    private Integer trangThai;
}
