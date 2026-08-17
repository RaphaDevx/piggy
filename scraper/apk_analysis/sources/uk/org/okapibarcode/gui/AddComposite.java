package uk.org.okapibarcode.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

/* loaded from: classes4.dex */
public class AddComposite extends JFrame {
    private JButton cancelButton;
    private JLabel compositeLabel;
    private JTextArea compositeText;
    private JScrollPane compsitePane;
    private JButton okButton;

    public AddComposite() {
        initComponents();
        pullData();
    }

    private void initComponents() {
        this.compositeLabel = new JLabel();
        this.compsitePane = new JScrollPane();
        this.compositeText = new JTextArea();
        this.cancelButton = new JButton();
        this.okButton = new JButton();
        setDefaultCloseOperation(3);
        this.compositeLabel.setText("Composite Data");
        this.compositeLabel.setName("");
        this.compositeText.setColumns(20);
        this.compositeText.setRows(5);
        this.compsitePane.setViewportView(this.compositeText);
        this.cancelButton.setText("Cancel");
        this.cancelButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.AddComposite.1
            public void actionPerformed(ActionEvent actionEvent) {
                AddComposite.this.cancelButtonActionPerformed(actionEvent);
            }
        });
        this.okButton.setText("OK");
        this.okButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.AddComposite.2
            public void actionPerformed(ActionEvent actionEvent) {
                AddComposite.this.okButtonActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.compsitePane, -1, 491, 32767).addGroup(groupLayout.createSequentialGroup().addComponent(this.compositeLabel).addGap(0, 0, 32767)).addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.okButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cancelButton))).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(this.compositeLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.compsitePane, -2, 259, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, 32767).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cancelButton).addComponent(this.okButton)).addGap(11, 11, 11)));
        pack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelButtonActionPerformed(ActionEvent actionEvent) {
        dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void okButtonActionPerformed(ActionEvent actionEvent) {
        OkapiUI.compositeInputField.setText(this.compositeText.getText());
        dispose();
    }

    private void pullData() {
        this.compositeText.setText(OkapiUI.compositeInputField.getText());
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
            java.lang.Class<uk.org.okapibarcode.gui.AddComposite> r7 = uk.org.okapibarcode.gui.AddComposite.class
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
            uk.org.okapibarcode.gui.AddComposite$3 r7 = new uk.org.okapibarcode.gui.AddComposite$3
            r7.<init>()
            java.awt.EventQueue.invokeLater(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.gui.AddComposite.main(java.lang.String[]):void");
    }
}
