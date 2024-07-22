package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "GioHang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "Ma")
    private String ma;

    @CreationTimestamp
    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "TrangThai")
    private int tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idKH")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idNV")
    private NhanVien nhanVien;
}
