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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Length(min = 1,max = 50, message = "* Không dưới 1 kí tự và quá 50 kí tự")
    @Column(name = "Ten")
    private String hoTen;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @NotBlank(message = "* không để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ!")
    @Length(min = 10,max = 10, message = "* Không dưới 10 kí tự và quá 10 kí tự")
    @Column(name = "SdtNhanVien")
    private String sdt;

    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @NotBlank(message = "Không để trống thông tin")
    @Email(message = "Email không hợp lệ!")
    @Column(name = "Email")

    private String email;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "TaiKhoan")
    private String taiKhoan;

    @NotBlank(message = "Không để trống thông tin")
    @Length(min = 12,max = 12, message = "* Không dưới 12 kí tự và quá 12 kí tự")
    @Column(name = "CCCD")
    private String CCCD;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "TrangThai")
    private int tinhTrang;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgaySua")
    private Date ngaySua;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdChucVu")
    private ChucVu chucVu;



}
