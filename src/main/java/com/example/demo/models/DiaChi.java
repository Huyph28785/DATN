package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "DiaChi")
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "DiaChi")
    private String soDiaChi;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "ThanhPho")
    private String thanhPho;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "Quan")
    private String quan;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "Phuong")
    private String phuong;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "NguoiTao")
    private String nguoiTao;

    @Column(name = "NguoiSua")
    private String nguoiSua;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgaySua")
    private Date ngaySua;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai")
    private int trangThai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;
}
