package com.example.demo.models;

import com.example.demo.models.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    UUID id;

    @Column(name = "Ma")
    private String ma;


    @Column(name = "MaQR")
    private String maQR;

    @Column(name = "urlAnh")
    private String urlAnh;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSanPham")
    SanPham sanPham;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMauSac")
    @NotNull(message = "* Mời chọn màu sắc")
    MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCoAo")
    @NotNull(message = "* Mời chọn cỡ áo")
    CoAo coAo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKichCo")
    @NotNull(message = "* Mời chọn kích cỡ")
    KichCo kichCo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdChatLieu")
    @NotNull(message = "* Mời chọn chất liệu")
    ChatLieu chatLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdThuongHieu")
    @NotNull(message = "* Mời chọn thương hiệu")
    ThuongHieu thuongHieu;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "ctsp")
    HinhAnh hinhAnh;

    @Column(name = "GiaBan")
    @DecimalMin(value = "39999", inclusive = false, message = "* Giá bán không hợp lệ, nhập giá nhỏ nhất là 40000")
    @DecimalMax(value = "9999999999.99", inclusive = false, message = "* Giá bán không hợp lệ")
    @NotNull(message = "* không để trống giá bán !")
    Double giaBan;


    @Column(name = "SoLuongTon")
//    @DecimalMin(value = "1", inclusive = false,message = "* Số lượng không hợp lệ, số lượng phải lớn hơn 0")
    @DecimalMax(value = "9999999", inclusive = false,message = "* Số lượng không hợp lệ")
//    @NotNull(message = "* không để trống số lượng !")
    Integer soLuongTon;



    private String nguoiTao;
    private String nguoiSua;

    @Column(name = "MoTa")
//    @NotBlank(message = "* không để trống mô tả !")
    String moTaCT;

    @Column(name = "NgayTao")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngayTao;

    @Column(name = "NgaySua")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngaySua;


    @Column(name = "Trangthai")
    @NotNull(message = "* Mời chọn trạng thái !")
    Integer trangThai;



    public void loadFromViewModel(QLSanPham vm) {
        this.setChatLieu(vm.getChatLieu());
        this.setCoAo(vm.getCoAo());
        this.setGiaBan(vm.getGiaBan());
        this.setKichCo(vm.getKichCo());
        this.setSanPham(vm.getSanPham());
        this.setTrangThai(vm.getTrangThai());
        this.setMoTaCT(vm.getMoTaCT());
        this.setSoLuongTon(vm.getSoLuongTon());
        this.setNgayTao(vm.getNgayTao());
        this.setThuongHieu(vm.getThuongHieu());
        this.setMauSac(vm.getMauSac());
        this.setUrlAnh(vm.getUrlAnh());
    }
    public String basoOchammotlam() {
        // Input number
        long number = Long.valueOf(String.valueOf(giaBan));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Format the number
        return decimalFormat.format(number);
    }

    public String basoOchammotlam2() {
        // Input number
        long number = Long.valueOf(String.valueOf(giaBan));

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        return  decimalFormat.format(number).replaceAll("[,]", ".");



    }
}
