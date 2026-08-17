package uk.org.okapibarcode.gui;

import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;

/* loaded from: classes4.dex */
public class MoreData extends JFrame {
    private JButton cancelButton;
    private TextArea dataInputArea;
    private Label dataLabel;
    private JButton fromFileButton;
    private JButton okButton;
    private JButton resetButton;

    public MoreData() {
        initComponents();
        pullData();
    }

    private void initComponents() {
        this.dataLabel = new Label();
        this.dataInputArea = new TextArea();
        this.fromFileButton = new JButton();
        this.resetButton = new JButton();
        this.cancelButton = new JButton();
        this.okButton = new JButton();
        setDefaultCloseOperation(2);
        setTitle("Input Data");
        setResizable(false);
        this.dataLabel.setName("dataLabel");
        this.dataLabel.setText("Data");
        this.dataInputArea.setName("dataField");
        this.fromFileButton.setText("From File...");
        this.fromFileButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.MoreData.1
            public void actionPerformed(ActionEvent actionEvent) {
                MoreData.this.fromFileButtonActionPerformed(actionEvent);
            }
        });
        this.resetButton.setText("Reset");
        this.resetButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.MoreData.2
            public void actionPerformed(ActionEvent actionEvent) {
                MoreData.this.resetButtonActionPerformed(actionEvent);
            }
        });
        this.cancelButton.setText("Cancel");
        this.cancelButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.MoreData.3
            public void actionPerformed(ActionEvent actionEvent) {
                MoreData.this.cancelButtonActionPerformed(actionEvent);
            }
        });
        this.okButton.setText("OK");
        this.okButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.MoreData.4
            public void actionPerformed(ActionEvent actionEvent) {
                MoreData.this.okButtonActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(this.dataLabel, -2, -1, -2).addGap(0, 0, 32767)).addGroup(groupLayout.createSequentialGroup().addComponent(this.resetButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.fromFileButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.okButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cancelButton)).addComponent(this.dataInputArea, -1, 468, 32767)).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(this.dataLabel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.dataInputArea, -2, 283, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.resetButton).addComponent(this.okButton).addComponent(this.cancelButton).addComponent(this.fromFileButton)).addContainerGap(-1, 32767)));
        pack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetButtonActionPerformed(ActionEvent actionEvent) {
        this.dataInputArea.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fromFileButtonActionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(this) == 0) {
            try {
                this.dataInputArea.setText(OpenFile.ReadFile(jFileChooser.getSelectedFile(), false));
            } catch (Exception unused) {
                System.out.println("Cannot read from file" + jFileChooser.getSelectedFile().toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelButtonActionPerformed(ActionEvent actionEvent) {
        dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void okButtonActionPerformed(ActionEvent actionEvent) {
        OkapiUI.dataInputField.setText(this.dataInputArea.getText());
        dispose();
    }

    private void pullData() {
        this.dataInputArea.setText(OkapiUI.dataInputField.getText());
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
            java.lang.Class<uk.org.okapibarcode.gui.MoreData> r7 = uk.org.okapibarcode.gui.MoreData.class
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
            uk.org.okapibarcode.gui.MoreData$5 r7 = new uk.org.okapibarcode.gui.MoreData$5
            r7.<init>()
            java.awt.EventQueue.invokeLater(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.gui.MoreData.main(java.lang.String[]):void");
    }
}
