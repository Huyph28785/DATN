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
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Length(min = 1,max = 50, message = "* Không dưới 1 kí tự và quá 50 kí tự")
    @Column(name = "hoTen")
    private String hoTen;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @NotBlank(message = "Không để trống thông tin")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại không hợp lệ!")
    @Length(min = 10,max = 10, message = "* Không dưới 10 kí tự và quá 10 kí tự")
    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @NotBlank(message = "Không để trống thông tin")
    @Email(message = "Email không hợp lệ!")
    @Column(name = "Email")
    private String email;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "TaiKhoan")
    private String taiKhoan;

//    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgaySua")
    private Date ngaySua;

    @Column(name = "NguoiTao")
    private String nguoiTao;

    @Column(name = "NguoiSua")
    private String nguoiSua;

    @Column(name = "TrangThai")
    private int trangThai;


}
