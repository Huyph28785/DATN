package com.example.demo.utils;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.NhanVien;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeGenerator {
    //    public static void generatorQRCode(ChiTietSanPham sp, String qrCodeFolderPath) throws WriterException, IOException {
//        // gen qr code
//        File existingQRCodeFile = new File(qrCodeFolderPath + File.separator + sp.getMa() + ".png");
//        if (existingQRCodeFile.exists()) {
//            // Mã QR code đã tồn tại, tiến hành cập nhật
//            String qrCodeData = String.valueOf(sp.getMa());
//
//            // Cấu hình mã QR code
//            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
//            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
//
//            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//
//            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 400, 400, hints);
//            Path path = FileSystems.getDefault().getPath(existingQRCodeFile.getAbsolutePath());
//            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
//        } else {
////            String qrCodeData = String.valueOf(sp.getMa()()+ sp.getSanPham().getTenSanPham());
//
//            String qrCodeData = String.valueOf(sp.getMa());            // Cấu hình mã QR code
//            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
//            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
//
//            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//
//            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 400, 400, hints);
//
//            // Tạo đường dẫn và tên tệp mã QR code
//            String qrCodeFileName = sp.getMa() + ".png";
//            String qrCodeFilePath = qrCodeFolderPath + File.separator + qrCodeFileName;
//
//            // Lưu mã QR code vào thư mục đích
//            Path path = FileSystems.getDefault().getPath(qrCodeFilePath);
//            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
//        }
//    }
    public static void generatorQRCode(ChiTietSanPham sp, String outputFolderPath) throws WriterException, IOException {
        // Generate code:
        String qrCodeData = String.valueOf(sp.getMa());


        // Configure UTF-8 encoding
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        String qrCodeName = outputFolderPath + File.separator + sp.getMa() + ".png";

        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 400, 400, hints);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
