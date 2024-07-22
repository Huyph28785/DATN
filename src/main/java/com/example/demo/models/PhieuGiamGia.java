package com.example.demo.models;

import com.google.api.client.util.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "PhieuGiamGia")
public class PhieuGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "Ten")
    private String ten;

    @Column(name = "HinhThucGiam")
    private Boolean hinhThucGiam;

    @Column(name = "NgayBatDau")
    private String ngayBatDau;

    @Column(name = "NgayketThuc")
    private String ngayketThuc;

    @NotNull(message = "Không để trống thông tin")
    @DecimalMin(value = "1", message = "% giảm phải lớn hơn 0")
    @DecimalMax(value = "20", message = "% giảm tối đa 20%")
    @Column(name = "TienGiam")
    private int tienGiam;

    @CreationTimestamp
    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgaySua")
    private Date ngaySua;

    @Column(name = "NguoiTao")
    private String nguoiTao;

    @Column(name = "NguoiSua")
    private String nguoiSua;

    @DecimalMin(value = "0.01", message = "Tổng tiền hóa đơn giảm lớn hơn 0")
    @NotNull(message = "Không để trống dữ liệu")
    @Column(name = "GiamToiDa")
    private BigDecimal giamToiDa;

    @DecimalMin(value = "0.01", message = "Số tiền giảm tối đa lớn hơn 0")
    @NotNull(message = "Không để trống dữ liệu")
    @Column(name = "GiamToiThieu")
    private BigDecimal giamToiThieu;

    @NotNull(message = "Không để trống thông tin")
    @DecimalMin(value = "1", message = "Số lượng phải lớn hơn 0")
    @DecimalMax(value = "100", message = "Số lượng tối đa 100")
    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "TrangThai")
    private int trangThai;

}
