package com.example.demo.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "HinhAnh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HinhAnh {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String tenAnh;
    private String duongDan1;
    private String duongDan2;
    private String duongDan3;
    private String duongDan4;
    private String duongDan5;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCTSP", referencedColumnName = "id")
    ChiTietSanPham ctsp;
}
