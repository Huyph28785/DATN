package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LichSuHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNhanVien")
    private NhanVien nhanVien;

    @Column(name = "GhiChu")
//    @NotBlank(message = "* Không để trống")
//    @Length(max = 50, message = "* Không quá 50 kí tự")
    String ghiChu;

    @Column(name = "ThoiGian")
    Timestamp thoiGian;

    @Column(name = "TrangThaiHoaDon")
    int trangThaiHD;
}
