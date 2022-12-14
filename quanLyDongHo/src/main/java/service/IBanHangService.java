/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import model.hoadon.HoaDon;
import viewmodel.BanHang_HDCustom;

/**
 *
 * @author Nguyen
 */
public interface IBanHangService {
    void insert(HoaDon hd);
    List<BanHang_HDCustom> getHDCho();
    Object[] getSPCustom();
    Object[] getHDCT(int idHD);
    void insertSPToHDCT(int idHD, int sl, int idCTSP);
    void loadSLTon(int sl, int idCTSP);
    void huyHD(int idHD);
    void updateSLTon(int idCTSP, int sl);
    void thanhToanHD(int idHD, String ngayTT, BigDecimal tongTien, BigDecimal tienTraLai, String ghiChu, int pttt, int htgh);
    void deleteSPOnHDCT(int idCTSP, int sl);
    Object[] loadCboDongSP();
    Object[] getBH_SPCustomByDongSP(int idDSP);
    Object[] getAllHTGH();
    Object[] getAllPTTT();
    Object[] searchSP(String search);
    void updateKhachHang(int idKH, int idHD);
    HoaDon getHDById(int id);
}
