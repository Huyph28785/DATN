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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "GioHangChiTiet")
public class GioHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "SoLuong")
    private int soLuong;


//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "DonGia")
    private Double donGia;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "DonGiaKhiGiam")
    private Double donGiaKhiGiam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdGioHang")
    private GioHang gioHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdChiTietSP")
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "tinhTrang")
    private int tinhTrang;

    public String basoOchammotlamGHDG() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGia));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }

    public String basoOchammotlamGHDGKG() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGiaKhiGiam));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }
    public String tichDONGIAvsSL() {
        // Input number

        long number = Long.valueOf(String.valueOf(donGia))*Long.valueOf(String.valueOf(soLuong));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }

    public String basoOchammotlamGHDG2() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGia));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
//        return decimalFormat.format(number);
        return  decimalFormat.format(number).replaceAll("[,]", ".");
    }

    public String basoOchammotlamGHDGKG2() {
        // Input number
        long number = Long.valueOf(String.valueOf(donGia));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
//        return decimalFormat.format(number);
        return  decimalFormat.format(number).replaceAll("[,]", ".");
    }
    public String tichDONGIAvsSL2() {
        // Input number

        long number = Long.valueOf(String.valueOf(donGia))*Long.valueOf(String.valueOf(soLuong));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
//        return decimalFormat.format(number);
        return  decimalFormat.format(number).replaceAll("[,]", ".");
    }
}
