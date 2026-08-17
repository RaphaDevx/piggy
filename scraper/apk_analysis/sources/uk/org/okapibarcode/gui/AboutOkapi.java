package uk.org.okapibarcode.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/* loaded from: classes4.dex */
public class AboutOkapi extends JFrame {
    private JButton okButton;
    private JLabel titleLabel;

    public AboutOkapi() {
        initComponents();
        this.titleLabel.setText("<html><b>Okapi Barcode</b><br><br>A free barcode generator<br>&copy; 2006-2016 Robin Stuart &amp; Robert Elliott<br>Licensed under the Apache License, Version 2.0<br><br>&quot;QR Code&quot; is a registerered trademark of Denso Corp.<br>&quot;Telepen&quot; is a registered trademark of SB Electronics.<br></html>");
    }

    private void initComponents() {
        this.titleLabel = new JLabel();
        this.okButton = new JButton();
        setDefaultCloseOperation(2);
        setTitle("About Okapi");
        setResizable(false);
        this.titleLabel.setFont(new Font("Tahoma", 0, 12));
        this.titleLabel.setText("Okapi Barcode");
        this.okButton.setText("OK");
        this.okButton.setName("btnOK");
        this.okButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.AboutOkapi.1
            public void actionPerformed(ActionEvent actionEvent) {
                AboutOkapi.this.okButtonActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(336, 336, 336).addComponent(this.okButton).addContainerGap(-1, 32767)).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(this.titleLabel, -1, -1, 32767).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(this.titleLabel, -2, 254, -2).addGap(18, 18, 18).addComponent(this.okButton).addContainerGap(-1, 32767)));
        pack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void okButtonActionPerformed(ActionEvent actionEvent) {
        dispose();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
    
        javax.swing.UIManager.setLookAndFeel(r4.getClassName());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r7) {
        /*
            java.lang.Class<uk.org.okapibarcode.gui.AboutOkapi> r7 = uk.org.okapibarcode.gui.AboutOkapi.class
            r0 = 0
            javax.swing.UIManager$LookAndFeelInfo[] r1 = javax.swing.UIManager.getInstalledLookAndFeels()     // Catch: javax.swing.UnsupportedLookAndFeelException -> L24 java.lang.Throwable -> L33
            int r2 = r1.length     // Catch: javax.swing.UnsupportedLookAndFeelException -> L24 java.lang.Throwable -> L33
            r3 = 0
        L9:
            if (r3 >= r2) goto L41
            r4 = r1[r3]     // Catch: javax.swing.UnsupportedLookAndFeelException -> L24 java.lang.Throwable -> L33
            java.lang.String r5 = "Nimbus"
            java.lang.String r6 = r4.getName()     // Catch: javax.swing.UnsupportedLookAndFeelException -> L24 java.lang.Throwable -> L33
            boolean r5 = r5.equals(r6)     // Catch: javax.swing.UnsupportedLookAndFeelException -> L24 java.lang.Throwable -> L33
            if (r5 == 0) goto L21
            java.lang.String r1 = r4.getClassName()     // Catch: javax.swing.UnsupportedLookAndFeelException -> L24 java.lang.Throwable -> L33
            javax.swing.UIManager.setLookAndFeel(r1)     // Catch: javax.swing.UnsupportedLookAndFeelException -> L24 java.lang.Throwable -> L33
            goto L41
        L21:
            int r3 = r3 + 1
            goto L9
        L24:
            r1 = move-exception
            java.lang.String r7 = r7.getName()
            java.util.logging.Logger r7 = java.util.logging.Logger.getLogger(r7)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r7.log(r2, r0, r1)
            goto L41
        L33:
            r1 = move-exception
            java.lang.String r7 = r7.getName()
            java.util.logging.Logger r7 = java.util.logging.Logger.getLogger(r7)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            r7.log(r2, r0, r1)
        L41:
            uk.org.okapibarcode.gui.AboutOkapi$2 r7 = new uk.org.okapibarcode.gui.AboutOkapi$2
            r7.<init>()
            java.awt.EventQueue.invokeLater(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.gui.AboutOkapi.main(java.lang.String[]):void");
    }
}
