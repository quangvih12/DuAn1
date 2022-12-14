/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.login.gui;

import Util.OTPGenerator;
import Util.SendMailUtil;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.nhanvien.NhanVien;
import service.IForgotPasswordService;
import service.impl.ForgotPasswordServiceImpl;

/**
 *
 * @author RAVEN
 */
public class ForgotPassword extends javax.swing.JPanel {

    private static int idNV;
    private static String otp;
    private IForgotPasswordService fpService = new ForgotPasswordServiceImpl();

    /**
     * Creates new form Login
     */
    public ForgotPassword() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        txtUsername.setText("nv01");
        txtEmail.setText("nguyenxom6@gmail.com");
    }

    public static int getIdNV() {
        return idNV;
    }

    public static String getGeneratedOTP() {
        return otp;
    }

    public void forgotPassword() {
        txtUsername.grabFocus();
    }

    public void addEventBackLogin(ActionListener event) {
        btnQuayLai.addActionListener(event);
    }

    private boolean isNhanVienExist() {
        String username = txtUsername.getText().trim();
        if (username.length() != 0) {
            idNV = Integer.parseInt(username.substring(2));
            if (fpService.getNV(idNV) != null) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y nh??n vi??n c?? m?? v???a nh???p");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "T??n ????ng nh???p kh??ng ???????c ????? tr???ng");
            return false;
        }
    }

    private boolean isEmailCorrect() {
        if (txtEmail.getText().trim().length() != 0) {
            if (fpService.getEmailById(idNV).equalsIgnoreCase(txtEmail.getText().trim())) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Email kh??ng ????ng!");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "B???n ch??a nh???p email");
            return false;
        }
    }

    private NhanVien getNhanVien(int idNV) {
        return fpService.getNV(idNV);
    }

    private void sendOTP() throws NoSuchAlgorithmException {
        NhanVien nv = getNhanVien(idNV);
        otp = new OTPGenerator().generate(6);
//        System.out.println(otp);
        String content = "<!DOCTYPE html>\n"
                + "<html lang=\"vi\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "    <p>Xin ch??o " + nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen() + ",</p>\n"
                + "    <p style=\"color: red; font-weight: bold\">????y l?? email ch???a m?? x??c th???c, vui l??ng kh??ng chia s??? cho b???t k?? ai!</p>\n"
                + "    <p>M?? x??c th???c c???a b???n l??:</p>\n"
                + "    <p style=\"font-weight: bold; font-size: larger; padding-left: 10%;\">" + otp + "</p>\n"
                + "    <p>L??u ??: M?? x??c th???c ch??? t???n t???i trong v??ng 3 ph??t.</p>\n"
                + "    <p>Tr??n tr???ng,</p>\n"
                + "    <p>\n"
                + "        <em>C???a h??ng ?????ng H??? ?????u.</em> <br />\n"
                + "    </p>\n"
                + "</body>\n"
                + "</html>";
        SendMailUtil.sendMail(txtEmail.getText().trim(), "[WatchStore] - M?? x??c th???c t??i kho???n", content);
    }

    private void autoCloseMessageDialog() {
        final JLabel label = new JLabel();
        int timerDelay = 1000;
        new Timer(timerDelay, new ActionListener() {
            int timeLeft = 5;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    label.setText("M?? x??c th???c ???? ???????c g???i ?????n email c???a b???n! (" + timeLeft + ")");
                    timeLeft--;
                } else {
                    ((Timer) e.getSource()).stop();
                    Window win = SwingUtilities.getWindowAncestor(label);
                    if (win != null) {
                        win.dispose();
                    }
                }
            }
        }) {
            {
                setInitialDelay(0);
            }
        }.start();
        label.setPreferredSize(new Dimension(280, 30));
        JOptionPane.showMessageDialog(this, label);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEmail = new View.login.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnXacNhan = new View.login.MyButton();
        btnQuayLai = new javax.swing.JButton();
        txtUsername = new View.login.MyTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("T??n ????ng nh???p");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(69, 68, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Qu??n m???t kh???u");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Email");

        btnXacNhan.setBackground(new java.awt.Color(125, 229, 251));
        btnXacNhan.setForeground(new java.awt.Color(40, 40, 40));
        btnXacNhan.setText("X??c nh???n");
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnQuayLai.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(30, 122, 236));
        btnQuayLai.setText("Quay l???i ????ng nh???p");
        btnQuayLai.setContentAreaFilled(false);
        btnQuayLai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuayLai.setPreferredSize(new java.awt.Dimension(109, 18));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (isNhanVienExist()) {
            if (isEmailCorrect()) {
                try {
                    sendOTP();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            autoCloseMessageDialog();
                        }
                    });
                    new OTPVerificationJD(frame, true).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private View.login.MyButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private View.login.MyTextField txtEmail;
    private static View.login.MyTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
