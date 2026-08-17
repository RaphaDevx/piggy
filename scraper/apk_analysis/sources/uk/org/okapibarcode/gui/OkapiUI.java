package uk.org.okapibarcode.gui;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import ch.coop.auth.AuthBrowserView;
import com.tealium.library.BuildConfig;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import uk.org.okapibarcode.backend.AustraliaPost;
import uk.org.okapibarcode.backend.AztecCode;
import uk.org.okapibarcode.backend.AztecRune;
import uk.org.okapibarcode.backend.ChannelCode;
import uk.org.okapibarcode.backend.Codabar;
import uk.org.okapibarcode.backend.CodablockF;
import uk.org.okapibarcode.backend.Code11;
import uk.org.okapibarcode.backend.Code128;
import uk.org.okapibarcode.backend.Code16k;
import uk.org.okapibarcode.backend.Code2Of5;
import uk.org.okapibarcode.backend.Code32;
import uk.org.okapibarcode.backend.Code3Of9;
import uk.org.okapibarcode.backend.Code3Of9Extended;
import uk.org.okapibarcode.backend.Code49;
import uk.org.okapibarcode.backend.Code93;
import uk.org.okapibarcode.backend.CodeOne;
import uk.org.okapibarcode.backend.Composite;
import uk.org.okapibarcode.backend.DataBar14;
import uk.org.okapibarcode.backend.DataBarExpanded;
import uk.org.okapibarcode.backend.DataBarLimited;
import uk.org.okapibarcode.backend.DataMatrix;
import uk.org.okapibarcode.backend.Ean;
import uk.org.okapibarcode.backend.GridMatrix;
import uk.org.okapibarcode.backend.HumanReadableLocation;
import uk.org.okapibarcode.backend.JapanPost;
import uk.org.okapibarcode.backend.KixCode;
import uk.org.okapibarcode.backend.KoreaPost;
import uk.org.okapibarcode.backend.Logmars;
import uk.org.okapibarcode.backend.MaxiCode;
import uk.org.okapibarcode.backend.MicroQrCode;
import uk.org.okapibarcode.backend.MsiPlessey;
import uk.org.okapibarcode.backend.Nve18;
import uk.org.okapibarcode.backend.OkapiException;
import uk.org.okapibarcode.backend.Pdf417;
import uk.org.okapibarcode.backend.Pharmacode;
import uk.org.okapibarcode.backend.Pharmacode2Track;
import uk.org.okapibarcode.backend.Pharmazentralnummer;
import uk.org.okapibarcode.backend.Postnet;
import uk.org.okapibarcode.backend.QrCode;
import uk.org.okapibarcode.backend.RoyalMail4State;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.backend.Telepen;
import uk.org.okapibarcode.backend.Upc;
import uk.org.okapibarcode.backend.UspsOneCode;
import uk.org.okapibarcode.backend.UspsPackage;
import uk.org.okapibarcode.gui.SymbolType;

/* loaded from: classes4.dex */
public class OkapiUI extends JFrame implements TreeSelectionListener {
    public static int barHeight = 0;
    public static Object[] bc = null;
    public static String compositeInput = null;
    public static JTextField compositeInputField = null;
    public static String dataInput = null;
    public static JTextField dataInputField = null;
    public static boolean debug = true;
    public static int factor = 1;
    public static String outputf = null;
    private static final long serialVersionUID = -681156299104876221L;
    public static Symbol symbol;
    private static JPanel topPanel;
    private JButton aboutButton;
    private JButton addCompositeButton;
    private JPanel attributePanel;
    private JScrollPane attributeScrollPane;
    private JPanel attributeScrollPanel;
    private JRadioButton aztecAutoSize;
    private ButtonGroup aztecButtonGroup;
    private JPanel aztecPanel;
    private JRadioButton aztecUserEcc;
    private JComboBox aztecUserEccCombo;
    private JRadioButton aztecUserSize;
    private JComboBox aztecUserSizeCombo;
    private JButton batchFileButton;
    private TextArea batchOutputArea;
    private JPanel batchPanel;
    private JComboBox channelChannelsCombo;
    private JLabel channelNoOfChannels;
    private JPanel channelPanel;
    private JCheckBox chkReaderInit;
    private JComboBox cmbHrtPosition;
    private JComboBox code39CheckCombo;
    private JLabel code39CheckLabel;
    private JPanel code39Panel;
    private JPanel codeOnePanel;
    private JComboBox codeOneSizeCombo;
    private JLabel codeOneSizeLabel;
    private JLabel compositeLabel;
    private JLabel compositeModeLabel;
    private JPanel compositePanel;
    private JComboBox compositeUserMode;
    private JButton createButton;
    private JComboBox dataMatrixSizeCombo;
    private JLabel dataMatrixSizeLabel;
    private JCheckBox dataMatrixSquareOnlyCheck;
    private JComboBox databarColumnsCombo;
    private JLabel databarColumnsLabel;
    private JPanel databarPanel;
    private JPanel datamatrixPanel;
    private JLabel destinationLabel;
    private JButton directoryButton;
    private TextArea encodeInfoArea;
    private JLabel errorLabel;
    private JButton exitButton;
    private JTextField folderField;
    private JTextField formatField;
    private JLabel formatLabel;
    private JRadioButton gridmatrixAutoSize;
    private ButtonGroup gridmatrixButtonGroup;
    private JPanel gridmatrixPanel;
    private JRadioButton gridmatrixUserEcc;
    private JComboBox gridmatrixUserEccCombo;
    private JRadioButton gridmatrixUserSize;
    private JComboBox gridmatrixUserSizeCombo;
    private JTextField incrementField;
    private JLabel incrementLabel;
    private JButton inkButton;
    private JLabel inputLabel;
    private JLabel lblBorderWidth;
    private JLabel lblWhitespaceWidth;
    private JLabel lblXDimension;
    private JLabel lblXDimensionPixels;
    private JButton loadDataButton;
    private JTabbedPane mainTabs;
    private JLabel maxiEncodeModeLabel;
    private JComboBox maxiEncodingModeCombo;
    private JTextField maxiPrimaryData;
    private JLabel maxiPrimaryDataLabel;
    private JPanel maxicodePanel;
    private JComboBox microPdfColumnsCombo;
    private JLabel microPdfColumnsLabel;
    private JPanel microPdfPanel;
    private JRadioButton microQrAutoSize;
    private ButtonGroup microQrButtonGroup;
    private JPanel microQrPanel;
    private JRadioButton microQrUserEcc;
    private JComboBox microQrUserEccCombo;
    private JRadioButton microQrUserSize;
    private JComboBox microQrUserSizeCombo;
    private JComboBox msiCheckDigitCombo;
    private JLabel msiCheckDigitLabel;
    private JPanel msiPanel;
    private JLabel outFileFormatLabel;
    private JLabel outFileNameLabel;
    private JComboBox outFilenameCombo;
    private JComboBox outFormatCombo;
    private JButton paperButton;
    private JComboBox pdfColumnsCombo;
    private JLabel pdfDataColumnsLabel;
    private JComboBox pdfEccCombo;
    private JLabel pdfEccLabel;
    private JPanel pdfPanel;
    private JTextField prefixField;
    private JLabel prefixLabel;
    private JRadioButton qrAutoSize;
    private ButtonGroup qrButtonGroup;
    private JPanel qrPanel;
    private JRadioButton qrUserEcc;
    private JComboBox qrUserEccCombo;
    private JRadioButton qrUserSize;
    private JComboBox qrUserSizeCombo;
    private JButton resetButton;
    private JButton resetColourButton;
    private JButton runBatchButton;
    private JButton saveButton;
    private SymbolType selectedSymbol;
    private TextArea sequenceArea;
    private JPanel singlePanel;
    private JTextField startField;
    private JLabel startLabel;
    private JTextField stopField;
    private JLabel stopLabel;
    private JScrollPane symbolPane;
    private JTree symbolTree;
    DefaultMutableTreeNode treeTop = new DefaultMutableTreeNode("Symbologies");
    private JTextField txtBorderWidth;
    private JLabel txtShowHrt;
    private JTextField txtWhitespaceWidth;
    private JTextField txtXDimension;
    private JCheckBox useCompositeCheck;
    private JCheckBox useGS1Check;
    public static Color inkColour = new Color(0, 0, 0);
    public static Color paperColour = new Color(255, 255, 255);
    public static int moduleWidth = 4;
    public static int quietZoneHorizontal = 5;
    public static int quietZoneVertical = 5;

    public OkapiUI() {
        initComponents();
        createNodes(this.treeTop);
        this.symbolTree.getSelectionModel().setSelectionMode(1);
        this.symbolTree.addTreeSelectionListener(this);
        this.symbolTree.expandRow(0);
        this.symbolTree.expandRow(1);
        this.symbolTree.expandRow(7);
        this.symbolTree.setSelectionRow(8);
        this.sequenceArea.addTextListener(new TextListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.1
            public void textValueChanged(TextEvent textEvent) {
                if (OkapiUI.this.sequenceArea.getText().isEmpty()) {
                    OkapiUI.this.runBatchButton.setEnabled(false);
                } else {
                    OkapiUI.this.runBatchButton.setEnabled(true);
                }
            }
        });
        DocumentListener documentListener = new DocumentListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.2
            public void insertUpdate(DocumentEvent documentEvent) {
                updateMe();
            }

            public void removeUpdate(DocumentEvent documentEvent) {
                updateMe();
            }

            public void changedUpdate(DocumentEvent documentEvent) {
                updateMe();
            }

            public void updateMe() {
                OkapiUI.dataInput = OkapiUI.dataInputField.getText();
                OkapiUI.compositeInput = OkapiUI.compositeInputField.getText();
                OkapiUI.this.encodeData();
            }
        };
        dataInputField.getDocument().addDocumentListener(documentListener);
        compositeInputField.getDocument().addDocumentListener(documentListener);
        this.folderField.setText(System.getProperty("user.home") + File.separator);
    }

    private void initComponents() {
        this.aztecButtonGroup = new ButtonGroup();
        this.gridmatrixButtonGroup = new ButtonGroup();
        this.microQrButtonGroup = new ButtonGroup();
        this.qrButtonGroup = new ButtonGroup();
        this.symbolPane = new JScrollPane();
        this.symbolTree = new JTree(this.treeTop);
        this.mainTabs = new JTabbedPane();
        this.singlePanel = new JPanel();
        compositeInputField = new JTextField();
        this.compositeLabel = new JLabel();
        dataInputField = new JTextField();
        this.inputLabel = new JLabel();
        this.loadDataButton = new JButton();
        topPanel = new JPanel();
        this.errorLabel = new JLabel();
        this.addCompositeButton = new JButton();
        this.useGS1Check = new JCheckBox();
        this.useCompositeCheck = new JCheckBox();
        this.batchPanel = new JPanel();
        this.startField = new JTextField();
        this.stopField = new JTextField();
        this.incrementField = new JTextField();
        this.startLabel = new JLabel();
        this.stopLabel = new JLabel();
        this.incrementLabel = new JLabel();
        this.formatLabel = new JLabel();
        this.batchFileButton = new JButton();
        this.createButton = new JButton();
        this.folderField = new JTextField();
        this.prefixField = new JTextField();
        this.destinationLabel = new JLabel();
        this.prefixLabel = new JLabel();
        this.outFileNameLabel = new JLabel();
        this.outFileFormatLabel = new JLabel();
        this.outFilenameCombo = new JComboBox();
        this.outFormatCombo = new JComboBox();
        this.runBatchButton = new JButton();
        this.formatField = new JTextField();
        this.directoryButton = new JButton();
        this.resetButton = new JButton();
        this.sequenceArea = new TextArea();
        this.batchOutputArea = new TextArea();
        this.attributePanel = new JPanel();
        this.inkButton = new JButton();
        this.paperButton = new JButton();
        this.resetColourButton = new JButton();
        this.encodeInfoArea = new TextArea();
        this.attributeScrollPane = new JScrollPane();
        this.attributeScrollPanel = new JPanel();
        this.aztecPanel = new JPanel();
        this.aztecAutoSize = new JRadioButton();
        this.aztecUserSize = new JRadioButton();
        this.aztecUserEcc = new JRadioButton();
        this.aztecUserSizeCombo = new JComboBox();
        this.aztecUserEccCombo = new JComboBox();
        this.channelPanel = new JPanel();
        this.channelNoOfChannels = new JLabel();
        this.channelChannelsCombo = new JComboBox();
        this.code39Panel = new JPanel();
        this.code39CheckLabel = new JLabel();
        this.code39CheckCombo = new JComboBox();
        this.codeOnePanel = new JPanel();
        this.codeOneSizeLabel = new JLabel();
        this.codeOneSizeCombo = new JComboBox();
        this.databarPanel = new JPanel();
        this.databarColumnsLabel = new JLabel();
        this.databarColumnsCombo = new JComboBox();
        this.datamatrixPanel = new JPanel();
        this.dataMatrixSizeLabel = new JLabel();
        this.dataMatrixSizeCombo = new JComboBox();
        this.dataMatrixSquareOnlyCheck = new JCheckBox();
        this.gridmatrixPanel = new JPanel();
        this.gridmatrixAutoSize = new JRadioButton();
        this.gridmatrixUserSize = new JRadioButton();
        this.gridmatrixUserEcc = new JRadioButton();
        this.gridmatrixUserSizeCombo = new JComboBox();
        this.gridmatrixUserEccCombo = new JComboBox();
        this.maxicodePanel = new JPanel();
        this.maxiEncodeModeLabel = new JLabel();
        this.maxiPrimaryDataLabel = new JLabel();
        this.maxiEncodingModeCombo = new JComboBox();
        this.maxiPrimaryData = new JTextField();
        this.microPdfPanel = new JPanel();
        this.microPdfColumnsLabel = new JLabel();
        this.microPdfColumnsCombo = new JComboBox();
        this.microQrPanel = new JPanel();
        this.microQrAutoSize = new JRadioButton();
        this.microQrUserSize = new JRadioButton();
        this.microQrUserEcc = new JRadioButton();
        this.microQrUserSizeCombo = new JComboBox();
        this.microQrUserEccCombo = new JComboBox();
        this.msiPanel = new JPanel();
        this.msiCheckDigitLabel = new JLabel();
        this.msiCheckDigitCombo = new JComboBox();
        this.pdfPanel = new JPanel();
        this.pdfDataColumnsLabel = new JLabel();
        this.pdfEccLabel = new JLabel();
        this.pdfColumnsCombo = new JComboBox();
        this.pdfEccCombo = new JComboBox();
        this.qrPanel = new JPanel();
        this.qrAutoSize = new JRadioButton();
        this.qrUserSize = new JRadioButton();
        this.qrUserEcc = new JRadioButton();
        this.qrUserSizeCombo = new JComboBox();
        this.qrUserEccCombo = new JComboBox();
        this.compositePanel = new JPanel();
        this.compositeModeLabel = new JLabel();
        this.compositeUserMode = new JComboBox();
        this.chkReaderInit = new JCheckBox();
        this.cmbHrtPosition = new JComboBox();
        this.txtShowHrt = new JLabel();
        this.lblXDimension = new JLabel();
        this.lblXDimensionPixels = new JLabel();
        this.lblBorderWidth = new JLabel();
        this.lblWhitespaceWidth = new JLabel();
        this.txtXDimension = new JTextField();
        this.txtBorderWidth = new JTextField();
        this.txtWhitespaceWidth = new JTextField();
        this.exitButton = new JButton();
        this.saveButton = new JButton();
        this.aboutButton = new JButton();
        setDefaultCloseOperation(3);
        setTitle("Okapi Barcode");
        setResizable(false);
        this.symbolPane.setViewportView(this.symbolTree);
        this.mainTabs.setName("");
        compositeInputField.setEnabled(false);
        this.compositeLabel.setText("Composite:");
        this.compositeLabel.setEnabled(false);
        dataInputField.setText("Your Data Here!");
        this.inputLabel.setText("Data:");
        this.loadDataButton.setText("...");
        this.loadDataButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.3
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.loadDataButtonActionPerformed(actionEvent);
            }
        });
        topPanel.setBackground(paperColour);
        topPanel.setPreferredSize(new Dimension(480, 480));
        this.errorLabel.setFont(new Font("Tahoma", 1, 18));
        this.errorLabel.setHorizontalAlignment(0);
        this.errorLabel.setText("Error");
        GroupLayout groupLayout = new GroupLayout(topPanel);
        topPanel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.errorLabel, -2, 322, -2).addGap(164, 164, 164)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(237, 32767).addComponent(this.errorLabel, -2, 28, -2).addGap(194, 194, 194)));
        this.addCompositeButton.setText("...");
        this.addCompositeButton.setEnabled(false);
        this.addCompositeButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.4
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.addCompositeButtonActionPerformed(actionEvent);
            }
        });
        this.useGS1Check.setText("Use GS1 Data Encodation");
        this.useGS1Check.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.5
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.useGS1CheckActionPerformed(actionEvent);
            }
        });
        this.useCompositeCheck.setText("Add Composite Component");
        this.useCompositeCheck.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.6
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.useCompositeCheckActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout2 = new GroupLayout(this.singlePanel);
        this.singlePanel.setLayout(groupLayout2);
        groupLayout2.setHorizontalGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, groupLayout2.createSequentialGroup().addContainerGap().addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.compositeLabel, -1, 216, 32767).addGroup(groupLayout2.createSequentialGroup().addComponent(this.inputLabel).addGap(0, 0, 32767))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout2.createSequentialGroup().addComponent(this.useGS1Check, -2, 245, -2).addGap(18, 18, 18).addComponent(this.useCompositeCheck, -2, 236, -2)).addGroup(groupLayout2.createSequentialGroup().addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(dataInputField, -1, 520, 32767).addComponent(compositeInputField)).addGap(8, 8, 8).addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.loadDataButton, -1, 60, 32767).addComponent(this.addCompositeButton, -1, -1, 32767)))).addContainerGap()).addComponent(topPanel, -1, 846, 32767));
        groupLayout2.setVerticalGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, groupLayout2.createSequentialGroup().addComponent(topPanel, -2, 459, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 56, 32767).addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.useGS1Check).addComponent(this.useCompositeCheck)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(dataInputField, -2, -1, -2).addComponent(this.inputLabel).addComponent(this.loadDataButton)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(compositeInputField, -2, -1, -2).addComponent(this.compositeLabel).addComponent(this.addCompositeButton)).addContainerGap()));
        this.mainTabs.addTab("Single", this.singlePanel);
        this.startField.setText("1");
        this.stopField.setText("10");
        this.incrementField.setText("1");
        this.startLabel.setText("Start Value:");
        this.stopLabel.setText("End Value:");
        this.incrementLabel.setText("Increment By:");
        this.formatLabel.setText("Format:");
        this.batchFileButton.setText("Import");
        this.batchFileButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.7
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.batchFileButtonActionPerformed(actionEvent);
            }
        });
        this.createButton.setText("Create");
        this.createButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.8
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.createButtonActionPerformed(actionEvent);
            }
        });
        this.prefixField.setText("bcs_");
        this.destinationLabel.setText("Destination Path:");
        this.prefixLabel.setText("File Prefix:");
        this.outFileNameLabel.setText("File Name:");
        this.outFileFormatLabel.setText("File Format:");
        this.outFilenameCombo.setModel(new DefaultComboBoxModel(new String[]{"Same as Data", "Line Number"}));
        this.outFormatCombo.setModel(new DefaultComboBoxModel(new String[]{"Portable Network Graphic (*.png)", "Joint Photographic Expert Group Image (*.jpg)", "Graphics Interchange Format (*.gif)", "Windows Bitmap (*.bmp)", "Scalable Vector Graphic (*.svg)", "Encapsulated Post Script (*.eps)"}));
        this.runBatchButton.setText("Run Batch");
        this.runBatchButton.setEnabled(false);
        this.runBatchButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.9
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.runBatchButtonActionPerformed(actionEvent);
            }
        });
        this.formatField.setText("$$$$$$");
        this.directoryButton.setText("Select Directory");
        this.directoryButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.10
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.directoryButtonActionPerformed(actionEvent);
            }
        });
        this.resetButton.setText("Reset");
        this.resetButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.11
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.resetButtonActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout3 = new GroupLayout(this.batchPanel);
        this.batchPanel.setLayout(groupLayout3);
        groupLayout3.setHorizontalGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout3.createSequentialGroup().addContainerGap().addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.batchOutputArea, -1, -1, 32767).addComponent(this.sequenceArea, -1, -1, 32767).addGroup(groupLayout3.createSequentialGroup().addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.incrementLabel, -1, 98, 32767).addComponent(this.stopLabel, -1, -1, 32767).addComponent(this.startLabel, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.stopField, GroupLayout.Alignment.LEADING, -1, 182, 32767).addComponent(this.incrementField).addComponent(this.startField)).addGap(18, 18, 18).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout3.createSequentialGroup().addComponent(this.formatLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.formatField, -2, 182, -2)).addGroup(GroupLayout.Alignment.TRAILING, groupLayout3.createSequentialGroup().addGap(0, 0, 32767).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.batchFileButton, GroupLayout.Alignment.TRAILING, -2, 130, -2).addGroup(GroupLayout.Alignment.TRAILING, groupLayout3.createSequentialGroup().addComponent(this.resetButton, -2, 130, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.createButton, -2, 130, -2)))))).addGroup(groupLayout3.createSequentialGroup().addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.destinationLabel).addComponent(this.prefixLabel).addComponent(this.outFileNameLabel).addComponent(this.outFileFormatLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.outFilenameCombo, 0, -1, 32767).addComponent(this.prefixField, GroupLayout.Alignment.LEADING).addComponent(this.folderField, GroupLayout.Alignment.LEADING).addComponent(this.outFormatCombo, -2, 365, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.directoryButton, -2, 131, 32767).addComponent(this.runBatchButton, -1, -1, 32767)))).addContainerGap()));
        groupLayout3.setVerticalGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout3.createSequentialGroup().addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.startField, -2, -1, -2).addComponent(this.startLabel).addComponent(this.formatLabel).addComponent(this.formatField, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.stopField, -2, -1, -2).addComponent(this.stopLabel).addComponent(this.resetButton).addComponent(this.createButton)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.incrementField, -2, -1, -2).addComponent(this.incrementLabel).addComponent(this.batchFileButton)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sequenceArea, -2, 163, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.folderField, -2, -1, -2).addComponent(this.destinationLabel).addComponent(this.directoryButton)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.prefixField, -2, -1, -2).addComponent(this.prefixLabel)).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout3.createSequentialGroup().addGap(11, 11, 11).addComponent(this.outFileNameLabel).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.outFileFormatLabel)).addGroup(groupLayout3.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.outFilenameCombo, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.outFormatCombo, -2, -1, -2).addComponent(this.runBatchButton)))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.batchOutputArea, -1, -1, 32767).addContainerGap()));
        this.mainTabs.addTab("Batch", this.batchPanel);
        this.inkButton.setText("Select Ink Colour");
        this.inkButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.12
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.inkButtonActionPerformed(actionEvent);
            }
        });
        this.paperButton.setText("Select Paper Colour");
        this.paperButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.13
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.paperButtonActionPerformed(actionEvent);
            }
        });
        this.resetColourButton.setText("Reset Colours");
        this.resetColourButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.14
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.resetColourButtonActionPerformed(actionEvent);
            }
        });
        this.encodeInfoArea.setEditable(false);
        this.aztecPanel.setBorder(BorderFactory.createTitledBorder("Aztec Code"));
        this.aztecButtonGroup.add(this.aztecAutoSize);
        this.aztecAutoSize.setSelected(true);
        this.aztecAutoSize.setText("Automatic Resizing");
        this.aztecAutoSize.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.15
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.aztecAutoSizeActionPerformed(actionEvent);
            }
        });
        this.aztecButtonGroup.add(this.aztecUserSize);
        this.aztecUserSize.setText("Adjust Size To:");
        this.aztecUserSize.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.16
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.aztecUserSizeActionPerformed(actionEvent);
            }
        });
        this.aztecButtonGroup.add(this.aztecUserEcc);
        this.aztecUserEcc.setText("Add Minimum Error Correction:");
        this.aztecUserEcc.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.17
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.aztecUserEccActionPerformed(actionEvent);
            }
        });
        this.aztecUserSizeCombo.setModel(new DefaultComboBoxModel(new String[]{"15 X 15 Compact", "19 X 19 Compact", "23 X 23 Compact", "27 X 27 Compact", "19 X 19", "23 X 23", "27 X 27", "31 X 31", "37 X 37", "41 X 41", "45 X 45", "49 X 49", "53 X 53", "57 X 57", "61 X 61", "67 X 67", "71 X 71", "75 X 75", "79 X 79", "83 X 83", "87 X 87", "91 X 91", "95 X 95", "101 X 101", "105 X 105", "109 X 109", "113 X 113", "117 X 117", "121 X 121", "125 X 125", "131 X 131", "135 X 135", "139 X 139", "143 X 143", "147 X 147", "151 X 151"}));
        this.aztecUserSizeCombo.setEnabled(false);
        this.aztecUserSizeCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.18
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.aztecUserSizeComboActionPerformed(actionEvent);
            }
        });
        this.aztecUserEccCombo.setModel(new DefaultComboBoxModel(new String[]{"10% + 3 words", "23% + 3 words", "36% + 3 words", "50% + 3 words"}));
        this.aztecUserEccCombo.setEnabled(false);
        this.aztecUserEccCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.19
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.aztecUserEccComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout4 = new GroupLayout(this.aztecPanel);
        this.aztecPanel.setLayout(groupLayout4);
        groupLayout4.setHorizontalGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout4.createSequentialGroup().addContainerGap().addGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.aztecAutoSize, -1, -1, 32767).addComponent(this.aztecUserSize, -1, -1, 32767).addComponent(this.aztecUserEcc, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.aztecUserEccCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2).addComponent(this.aztecUserSizeCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2)).addContainerGap()));
        groupLayout4.setVerticalGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout4.createSequentialGroup().addComponent(this.aztecAutoSize).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.aztecUserSize, -2, 23, -2).addComponent(this.aztecUserSizeCombo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.aztecUserEccCombo, -2, -1, -2).addComponent(this.aztecUserEcc)).addGap(1, 1, 1)));
        this.channelPanel.setBorder(BorderFactory.createTitledBorder("Channel Code"));
        this.channelNoOfChannels.setText("Number of Channels:");
        this.channelChannelsCombo.setModel(new DefaultComboBoxModel(new String[]{"Automatic", ExifInterface.GPS_MEASUREMENT_3D, "4", BuildConfig.PUBLISH_SETTINGS_VERSION, "6", "7", "8"}));
        this.channelChannelsCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.20
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.channelChannelsComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout5 = new GroupLayout(this.channelPanel);
        this.channelPanel.setLayout(groupLayout5);
        groupLayout5.setHorizontalGroup(groupLayout5.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout5.createSequentialGroup().addContainerGap().addComponent(this.channelNoOfChannels).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.channelChannelsCombo, -2, 350, -2).addContainerGap()));
        groupLayout5.setVerticalGroup(groupLayout5.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout5.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.channelNoOfChannels).addComponent(this.channelChannelsCombo, -2, -1, -2)));
        this.code39Panel.setBorder(BorderFactory.createTitledBorder("Code 39"));
        this.code39CheckLabel.setText("Check Digit Option:");
        this.code39CheckCombo.setModel(new DefaultComboBoxModel(new String[]{"No Check Digit", "Mod-43 Check Digit"}));
        this.code39CheckCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.21
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.code39CheckComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout6 = new GroupLayout(this.code39Panel);
        this.code39Panel.setLayout(groupLayout6);
        groupLayout6.setHorizontalGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout6.createSequentialGroup().addContainerGap().addComponent(this.code39CheckLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.code39CheckCombo, -2, 350, -2).addContainerGap()));
        groupLayout6.setVerticalGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.code39CheckLabel).addComponent(this.code39CheckCombo, -2, -1, -2)));
        this.codeOnePanel.setBorder(BorderFactory.createTitledBorder("Code One"));
        this.codeOneSizeLabel.setText("Symbol Size:");
        this.codeOneSizeCombo.setModel(new DefaultComboBoxModel(new String[]{"Automatic", "16 X 18 (Version A)", "22 X 22 (Version B)", "28 X 32 (Version C)", "40 X 42 (Version D)", "52 X 54 (Version E)", "70 X 76 (Version F)", "104 X 98 (Version G)", "148 X 134 (Version H)", "8X Height (Version S)", "16X Height (Version T)"}));
        this.codeOneSizeCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.22
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.codeOneSizeComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout7 = new GroupLayout(this.codeOnePanel);
        this.codeOnePanel.setLayout(groupLayout7);
        groupLayout7.setHorizontalGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout7.createSequentialGroup().addContainerGap().addComponent(this.codeOneSizeLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.codeOneSizeCombo, -2, 350, -2).addContainerGap()));
        groupLayout7.setVerticalGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.codeOneSizeLabel).addComponent(this.codeOneSizeCombo, -2, -1, -2)));
        this.databarPanel.setBorder(BorderFactory.createTitledBorder("GS1 DataBar Expanded Stacked"));
        this.databarColumnsLabel.setText("Number of Columns:");
        this.databarColumnsCombo.setModel(new DefaultComboBoxModel(new String[]{"Automatic", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", BuildConfig.PUBLISH_SETTINGS_VERSION, "6", "7", "8", "9"}));
        this.databarColumnsCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.23
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.databarColumnsComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout8 = new GroupLayout(this.databarPanel);
        this.databarPanel.setLayout(groupLayout8);
        groupLayout8.setHorizontalGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout8.createSequentialGroup().addContainerGap().addComponent(this.databarColumnsLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.databarColumnsCombo, -2, 350, -2).addContainerGap()));
        groupLayout8.setVerticalGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.databarColumnsLabel).addComponent(this.databarColumnsCombo, -2, -1, -2)));
        this.datamatrixPanel.setBorder(BorderFactory.createTitledBorder("Data Matrix"));
        this.dataMatrixSizeLabel.setText("Symbol Size:");
        this.dataMatrixSizeCombo.setModel(new DefaultComboBoxModel(new String[]{"Automatic", "10 X 10", "12 X 12", "14 X 14", "16 X 16", "18 X 18", "20 X 20", "22 X 22", "24 X 24", "26 X 26", "32 X 32", "36 X 36", "40 X 40", "44 X 44", "48 X 48", "52 X 52", "64 X 64", "72 X 72", "80 X 80", "88 X 88", "96 X 96", "104 X 104", "120 X 120", "132 X 132", "144 X 144", "8 X 18", "8 X 32", "12 X 26", "12 X 36", "16 X 36", "16 X 48"}));
        this.dataMatrixSizeCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.24
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.dataMatrixSizeComboActionPerformed(actionEvent);
            }
        });
        this.dataMatrixSquareOnlyCheck.setSelected(true);
        this.dataMatrixSquareOnlyCheck.setText("Supress Rectangular Symbols in Automatic Mode");
        this.dataMatrixSquareOnlyCheck.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.25
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.dataMatrixSquareOnlyCheckActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout9 = new GroupLayout(this.datamatrixPanel);
        this.datamatrixPanel.setLayout(groupLayout9);
        groupLayout9.setHorizontalGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout9.createSequentialGroup().addContainerGap().addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout9.createSequentialGroup().addComponent(this.dataMatrixSquareOnlyCheck).addGap(0, 0, 32767)).addGroup(groupLayout9.createSequentialGroup().addComponent(this.dataMatrixSizeLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.dataMatrixSizeCombo, -2, 350, -2))).addContainerGap()));
        groupLayout9.setVerticalGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout9.createSequentialGroup().addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.dataMatrixSizeLabel).addComponent(this.dataMatrixSizeCombo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.dataMatrixSquareOnlyCheck)));
        this.gridmatrixPanel.setBorder(BorderFactory.createTitledBorder("Grid Matrix"));
        this.gridmatrixButtonGroup.add(this.gridmatrixAutoSize);
        this.gridmatrixAutoSize.setSelected(true);
        this.gridmatrixAutoSize.setText("Automatic Resizing");
        this.gridmatrixAutoSize.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.26
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.gridmatrixAutoSizeActionPerformed(actionEvent);
            }
        });
        this.gridmatrixButtonGroup.add(this.gridmatrixUserSize);
        this.gridmatrixUserSize.setText("Adjust Size To:");
        this.gridmatrixUserSize.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.27
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.gridmatrixUserSizeActionPerformed(actionEvent);
            }
        });
        this.gridmatrixButtonGroup.add(this.gridmatrixUserEcc);
        this.gridmatrixUserEcc.setText("Add Minimum Error Correction:");
        this.gridmatrixUserEcc.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.28
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.gridmatrixUserEccActionPerformed(actionEvent);
            }
        });
        this.gridmatrixUserSizeCombo.setModel(new DefaultComboBoxModel(new String[]{"18 X 18 (Version 1)", "30 X 30 (Version 2)", "42 X 42 (Version 3)", "54 X 54 (Version 4)", "66 X 66 (Version 5)", "78 X 78 (Version 6)", "90 X 90 (Version 7)", "102 X 102 (Version 8)", "114 X 114 (Version 9)", "126 X 126 (Version 10)", "138 X 138 (Version 11)", "150 X 150 (Version 12)", "162 X 162 (Version 13)"}));
        this.gridmatrixUserSizeCombo.setEnabled(false);
        this.gridmatrixUserSizeCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.29
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.gridmatrixUserSizeComboActionPerformed(actionEvent);
            }
        });
        this.gridmatrixUserEccCombo.setModel(new DefaultComboBoxModel(new String[]{"Approx 10%", "Approx 20%", "Approx 30%", "Approx 40%", "Approx 50%"}));
        this.gridmatrixUserEccCombo.setEnabled(false);
        this.gridmatrixUserEccCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.30
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.gridmatrixUserEccComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout10 = new GroupLayout(this.gridmatrixPanel);
        this.gridmatrixPanel.setLayout(groupLayout10);
        groupLayout10.setHorizontalGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout10.createSequentialGroup().addContainerGap().addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.gridmatrixAutoSize, -1, -1, 32767).addComponent(this.gridmatrixUserSize, -1, -1, 32767).addComponent(this.gridmatrixUserEcc, -1, 248, 32767)).addGap(74, 74, 74).addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.gridmatrixUserSizeCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2).addComponent(this.gridmatrixUserEccCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2)).addContainerGap()));
        groupLayout10.setVerticalGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout10.createSequentialGroup().addComponent(this.gridmatrixAutoSize).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.gridmatrixUserSize).addComponent(this.gridmatrixUserSizeCombo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.gridmatrixUserEcc).addComponent(this.gridmatrixUserEccCombo, -2, -1, -2))));
        this.maxicodePanel.setBorder(BorderFactory.createTitledBorder("Maxicode"));
        this.maxiEncodeModeLabel.setText("Encoding Mode:");
        this.maxiPrimaryDataLabel.setText("Primary Data:");
        this.maxiPrimaryDataLabel.setEnabled(false);
        this.maxiEncodingModeCombo.setModel(new DefaultComboBoxModel(new String[]{"Structured Carrier Message (Mode 2)", "Structured Carrier Message (Mode 3)", "Standard Symbol (Mode 4)", "Full ECC Symbol (Mode 5)"}));
        this.maxiEncodingModeCombo.setSelectedIndex(2);
        this.maxiEncodingModeCombo.setToolTipText("");
        this.maxiEncodingModeCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.31
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.maxiEncodingModeComboActionPerformed(actionEvent);
            }
        });
        this.maxiPrimaryData.setText("Primary Data Here!");
        this.maxiPrimaryData.setEnabled(false);
        this.maxiPrimaryData.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.32
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.maxiPrimaryDataActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout11 = new GroupLayout(this.maxicodePanel);
        this.maxicodePanel.setLayout(groupLayout11);
        groupLayout11.setHorizontalGroup(groupLayout11.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout11.createSequentialGroup().addContainerGap().addGroup(groupLayout11.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.maxiEncodeModeLabel).addComponent(this.maxiPrimaryDataLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(groupLayout11.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.maxiEncodingModeCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2).addComponent(this.maxiPrimaryData, GroupLayout.Alignment.TRAILING, -2, 350, -2)).addContainerGap()));
        groupLayout11.setVerticalGroup(groupLayout11.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout11.createSequentialGroup().addGroup(groupLayout11.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.maxiEncodeModeLabel).addComponent(this.maxiEncodingModeCombo, -2, 20, -2)).addGap(9, 9, 9).addGroup(groupLayout11.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.maxiPrimaryData, -2, -1, -2).addComponent(this.maxiPrimaryDataLabel)).addGap(2, 2, 2)));
        this.microPdfPanel.setBorder(BorderFactory.createTitledBorder("Micro PDF417"));
        this.microPdfColumnsLabel.setText("Number of Data Columns:");
        this.microPdfColumnsCombo.setModel(new DefaultComboBoxModel(new String[]{"Automatic", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4"}));
        this.microPdfColumnsCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.33
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.microPdfColumnsComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout12 = new GroupLayout(this.microPdfPanel);
        this.microPdfPanel.setLayout(groupLayout12);
        groupLayout12.setHorizontalGroup(groupLayout12.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout12.createSequentialGroup().addContainerGap().addComponent(this.microPdfColumnsLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.microPdfColumnsCombo, -2, 350, -2).addContainerGap()));
        groupLayout12.setVerticalGroup(groupLayout12.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.microPdfColumnsCombo, GroupLayout.Alignment.TRAILING, -2, -1, -2).addComponent(this.microPdfColumnsLabel, GroupLayout.Alignment.TRAILING));
        this.microQrPanel.setBorder(BorderFactory.createTitledBorder("Micro QR Code"));
        this.microQrButtonGroup.add(this.microQrAutoSize);
        this.microQrAutoSize.setSelected(true);
        this.microQrAutoSize.setText("Automatic Resizing");
        this.microQrAutoSize.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.34
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.microQrAutoSizeActionPerformed(actionEvent);
            }
        });
        this.microQrButtonGroup.add(this.microQrUserSize);
        this.microQrUserSize.setText("Adjust Size To:");
        this.microQrUserSize.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.35
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.microQrUserSizeActionPerformed(actionEvent);
            }
        });
        this.microQrButtonGroup.add(this.microQrUserEcc);
        this.microQrUserEcc.setText("Add Minimum Error Correction:");
        this.microQrUserEcc.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.36
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.microQrUserEccActionPerformed(actionEvent);
            }
        });
        this.microQrUserSizeCombo.setModel(new DefaultComboBoxModel(new String[]{"11 X 11 (Version M1)", "13 X 13 (Version M2)", "15 X 15 (Version M3)", "17 X 17 (Version M4)"}));
        this.microQrUserSizeCombo.setEnabled(false);
        this.microQrUserSizeCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.37
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.microQrUserSizeComboActionPerformed(actionEvent);
            }
        });
        this.microQrUserEccCombo.setModel(new DefaultComboBoxModel(new String[]{"Approx 20% (Level L)", "Approx 37% (Level M)", "Approx 55% (Level Q)"}));
        this.microQrUserEccCombo.setEnabled(false);
        this.microQrUserEccCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.38
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.microQrUserEccComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout13 = new GroupLayout(this.microQrPanel);
        this.microQrPanel.setLayout(groupLayout13);
        groupLayout13.setHorizontalGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout13.createSequentialGroup().addContainerGap().addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.microQrAutoSize, -1, -1, 32767).addComponent(this.microQrUserSize, -1, -1, 32767).addComponent(this.microQrUserEcc, -1, -1, 32767)).addGap(14, 14, 14).addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.microQrUserSizeCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2).addComponent(this.microQrUserEccCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2)).addContainerGap()));
        groupLayout13.setVerticalGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout13.createSequentialGroup().addComponent(this.microQrAutoSize).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.microQrUserSize).addComponent(this.microQrUserSizeCombo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.microQrUserEcc).addComponent(this.microQrUserEccCombo, -2, -1, -2))));
        this.msiPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("MSI Plessey"), "MSI Plessey"));
        this.msiCheckDigitLabel.setText("Check Digit:");
        this.msiCheckDigitCombo.setModel(new DefaultComboBoxModel(new String[]{"None", "Mod-10", "Mod-10 & Mod-10", "Mod-11", "Mod-11 & Mod-10"}));
        this.msiCheckDigitCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.39
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.msiCheckDigitComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout14 = new GroupLayout(this.msiPanel);
        this.msiPanel.setLayout(groupLayout14);
        groupLayout14.setHorizontalGroup(groupLayout14.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout14.createSequentialGroup().addContainerGap().addComponent(this.msiCheckDigitLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.msiCheckDigitCombo, -2, 350, -2).addContainerGap()));
        groupLayout14.setVerticalGroup(groupLayout14.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout14.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.msiCheckDigitLabel).addComponent(this.msiCheckDigitCombo, -2, -1, -2)));
        this.pdfPanel.setBorder(BorderFactory.createTitledBorder("PDF417"));
        this.pdfDataColumnsLabel.setText("Number of Data Columns:");
        this.pdfEccLabel.setText("Error Correction Capacity:");
        this.pdfColumnsCombo.setModel(new DefaultComboBoxModel(new String[]{"Automatic", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", BuildConfig.PUBLISH_SETTINGS_VERSION, "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
        this.pdfColumnsCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.40
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.pdfColumnsComboActionPerformed(actionEvent);
            }
        });
        this.pdfEccCombo.setModel(new DefaultComboBoxModel(new String[]{"Automatic", "2 words", "4 words", "8 words", "16 words", "32 words", "64 words", "128 words", "256 words", "512 words"}));
        this.pdfEccCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.41
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.pdfEccComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout15 = new GroupLayout(this.pdfPanel);
        this.pdfPanel.setLayout(groupLayout15);
        groupLayout15.setHorizontalGroup(groupLayout15.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout15.createSequentialGroup().addContainerGap().addGroup(groupLayout15.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.pdfDataColumnsLabel, -1, -1, 32767).addComponent(this.pdfEccLabel, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(groupLayout15.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.pdfColumnsCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2).addComponent(this.pdfEccCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2)).addContainerGap()));
        groupLayout15.setVerticalGroup(groupLayout15.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout15.createSequentialGroup().addGroup(groupLayout15.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.pdfDataColumnsLabel).addComponent(this.pdfColumnsCombo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout15.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.pdfEccLabel).addComponent(this.pdfEccCombo, -2, -1, -2))));
        this.qrPanel.setBorder(BorderFactory.createTitledBorder("QR Code"));
        this.qrButtonGroup.add(this.qrAutoSize);
        this.qrAutoSize.setSelected(true);
        this.qrAutoSize.setText("Automatic Resizing");
        this.qrAutoSize.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.42
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.qrAutoSizeActionPerformed(actionEvent);
            }
        });
        this.qrButtonGroup.add(this.qrUserSize);
        this.qrUserSize.setText("Adjust Size To:");
        this.qrUserSize.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.43
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.qrUserSizeActionPerformed(actionEvent);
            }
        });
        this.qrButtonGroup.add(this.qrUserEcc);
        this.qrUserEcc.setText("Add Minimum Error Correction:");
        this.qrUserEcc.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.44
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.qrUserEccActionPerformed(actionEvent);
            }
        });
        this.qrUserSizeCombo.setModel(new DefaultComboBoxModel(new String[]{"21 X 21 (Version 1)", "25 X 25 (Version 2)", "29 X 29 (Version 3)", "33 X 33 (Version 4)", "37 X 37 (Version 5)", "41 X 41 (Version 6)", "45 X 45 (Version 7)", "49 X 49 (Version 8)", "53 X 53 (Version 9)", "57 X 57 (Version 10)", "61 X 61 (Version 11)", "65 X 65 (Version 12)", "69 X 69 (Version 13)", "73 X 73 (Version 14)", "77 X 77 (Version 15)", "81 X 81 (Version 16)", "85 X 85 (Version 17)", "89 X 89 (Version 18)", "93 X 93 (Version 19)", "97 X 97 (Version 20)", "101 X 101 (Version 21)", "105 X 105 (Version 22)", "109 X 109 (Version 23)", "113 X 113 (Version 24)", "117 X 117 (Version 25)", "121 X 121 (Version 26)", "125 X 125 (Version 27)", "129 X 129 (Version 28)", "133 X 133 (Version 29)", "137 X 137 (Version 30)", "141 X 141 (Version 31)", "145 X 145 (Version 32)", "149 X 149 (Version 33)", "153 X 153 (Version 34)", "157 X 157 (Version 35)", "161 X 161 (Version 36)", "165 X 165 (Version 37)", "169 X 169 (Version 38)", "173 X 173 (Version 39)", "177 X 177 (Version 40)"}));
        this.qrUserSizeCombo.setEnabled(false);
        this.qrUserSizeCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.45
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.qrUserSizeComboActionPerformed(actionEvent);
            }
        });
        this.qrUserEccCombo.setModel(new DefaultComboBoxModel(new String[]{"Approx 20% (Level L)", "Approx 37% (Level M)", "Approx 55% (Level Q)", "Approx 65% (Level H)"}));
        this.qrUserEccCombo.setEnabled(false);
        this.qrUserEccCombo.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.46
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.qrUserEccComboActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout16 = new GroupLayout(this.qrPanel);
        this.qrPanel.setLayout(groupLayout16);
        groupLayout16.setHorizontalGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout16.createSequentialGroup().addContainerGap().addGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.qrAutoSize, -1, -1, 32767).addComponent(this.qrUserSize, -1, -1, 32767).addComponent(this.qrUserEcc, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.qrUserSizeCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2).addComponent(this.qrUserEccCombo, GroupLayout.Alignment.TRAILING, -2, 350, -2)).addContainerGap()));
        groupLayout16.setVerticalGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout16.createSequentialGroup().addComponent(this.qrAutoSize).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.qrUserSize).addComponent(this.qrUserSizeCombo, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.qrUserEcc).addComponent(this.qrUserEccCombo, -2, -1, -2)).addContainerGap()));
        this.compositePanel.setBorder(BorderFactory.createTitledBorder("Composite Component"));
        this.compositeModeLabel.setText("Composite Component Mode:");
        this.compositeUserMode.setModel(new DefaultComboBoxModel(new String[]{"Automatic", "CC-A", "CC-B", "CC-C"}));
        this.compositeUserMode.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.47
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.compositeUserModeActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout17 = new GroupLayout(this.compositePanel);
        this.compositePanel.setLayout(groupLayout17);
        groupLayout17.setHorizontalGroup(groupLayout17.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout17.createSequentialGroup().addContainerGap().addComponent(this.compositeModeLabel, -2, 172, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.compositeUserMode, -2, 350, -2).addContainerGap()));
        groupLayout17.setVerticalGroup(groupLayout17.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout17.createSequentialGroup().addComponent(this.compositeModeLabel).addGap(0, 0, 32767)).addComponent(this.compositeUserMode, -1, 30, 32767));
        GroupLayout groupLayout18 = new GroupLayout(this.attributeScrollPanel);
        this.attributeScrollPanel.setLayout(groupLayout18);
        groupLayout18.setHorizontalGroup(groupLayout18.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.aztecPanel, -1, -1, 32767).addComponent(this.channelPanel, -1, -1, 32767).addComponent(this.code39Panel, -1, -1, 32767).addComponent(this.codeOnePanel, -1, -1, 32767).addComponent(this.datamatrixPanel, -1, -1, 32767).addComponent(this.maxicodePanel, -1, -1, 32767).addComponent(this.microPdfPanel, -1, -1, 32767).addComponent(this.microQrPanel, -1, -1, 32767).addComponent(this.msiPanel, -1, -1, 32767).addComponent(this.pdfPanel, -1, -1, 32767).addComponent(this.qrPanel, -1, -1, 32767).addComponent(this.compositePanel, -1, -1, 32767).addComponent(this.gridmatrixPanel, -1, -1, 32767).addComponent(this.databarPanel, -1, -1, 32767));
        groupLayout18.setVerticalGroup(groupLayout18.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout18.createSequentialGroup().addComponent(this.aztecPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.channelPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.code39Panel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.codeOnePanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.compositePanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.datamatrixPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.gridmatrixPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.databarPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.maxicodePanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.microPdfPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.microQrPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.msiPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.pdfPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.qrPanel, -2, -1, -2).addContainerGap(168, 32767)));
        this.attributeScrollPane.setViewportView(this.attributeScrollPanel);
        this.chkReaderInit.setText("Add reader initialisation");
        this.chkReaderInit.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.48
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.chkReaderInitActionPerformed(actionEvent);
            }
        });
        this.cmbHrtPosition.setModel(new DefaultComboBoxModel(new String[]{"Below symbol", "Above symbol", "Don't display"}));
        this.cmbHrtPosition.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.49
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.cmbHrtPositionActionPerformed(actionEvent);
            }
        });
        this.txtShowHrt.setText("Show human readable text:");
        this.lblXDimension.setText("X Dimension:");
        this.lblXDimensionPixels.setText("Pixels");
        this.lblBorderWidth.setText("Border Width:");
        this.lblWhitespaceWidth.setText("Whitespace Width:");
        this.txtXDimension.setText("4");
        this.txtXDimension.addFocusListener(new FocusAdapter() { // from class: uk.org.okapibarcode.gui.OkapiUI.50
            public void focusLost(FocusEvent focusEvent) {
                OkapiUI.this.txtXDimensionFocusLost(focusEvent);
            }
        });
        this.txtBorderWidth.setText(BuildConfig.PUBLISH_SETTINGS_VERSION);
        this.txtBorderWidth.addFocusListener(new FocusAdapter() { // from class: uk.org.okapibarcode.gui.OkapiUI.51
            public void focusLost(FocusEvent focusEvent) {
                OkapiUI.this.txtBorderWidthFocusLost(focusEvent);
            }
        });
        this.txtWhitespaceWidth.setText("0");
        this.txtWhitespaceWidth.addFocusListener(new FocusAdapter() { // from class: uk.org.okapibarcode.gui.OkapiUI.52
            public void focusLost(FocusEvent focusEvent) {
                OkapiUI.this.txtWhitespaceWidthFocusLost(focusEvent);
            }
        });
        GroupLayout groupLayout19 = new GroupLayout(this.attributePanel);
        this.attributePanel.setLayout(groupLayout19);
        groupLayout19.setHorizontalGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.attributeScrollPane, -1, 846, 32767).addGroup(GroupLayout.Alignment.TRAILING, groupLayout19.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.inkButton, -2, 130, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.paperButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.resetColourButton, -2, 130, -2).addGap(122, 122, 122)).addGroup(groupLayout19.createSequentialGroup().addContainerGap().addGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout19.createSequentialGroup().addComponent(this.encodeInfoArea, -1, -1, 32767).addContainerGap()).addGroup(groupLayout19.createSequentialGroup().addComponent(this.txtShowHrt, -1, -1, 32767).addGap(18, 18, 18).addComponent(this.cmbHrtPosition, -2, 160, -2).addGap(55, 55, 55).addComponent(this.chkReaderInit, -2, 230, -2).addGap(30, 30, 30)).addGroup(groupLayout19.createSequentialGroup().addComponent(this.lblXDimension).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.txtXDimension, -2, 58, -2).addGap(3, 3, 3).addComponent(this.lblXDimensionPixels).addGap(81, 81, 81).addComponent(this.lblBorderWidth).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.txtBorderWidth, -2, 62, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.lblWhitespaceWidth).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.txtWhitespaceWidth, -2, 57, -2).addGap(71, 71, 71)))));
        groupLayout19.setVerticalGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout19.createSequentialGroup().addContainerGap().addGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.inkButton).addComponent(this.paperButton).addComponent(this.resetColourButton)).addGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout19.createSequentialGroup().addGap(6, 6, 6).addGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.chkReaderInit).addComponent(this.txtShowHrt))).addGroup(groupLayout19.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cmbHrtPosition, -2, -1, -2))).addGap(9, 9, 9).addGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.lblWhitespaceWidth, -2, 15, -2).addGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lblXDimension).addComponent(this.lblXDimensionPixels).addComponent(this.lblBorderWidth).addComponent(this.txtXDimension, -2, -1, -2).addComponent(this.txtBorderWidth, -2, -1, -2).addComponent(this.txtWhitespaceWidth, -2, -1, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.attributeScrollPane, -2, 345, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, 32767).addComponent(this.encodeInfoArea, -2, 120, -2).addContainerGap()));
        this.mainTabs.addTab("Attributes", this.attributePanel);
        this.exitButton.setText("Exit");
        this.exitButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.53
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.exitButtonActionPerformed(actionEvent);
            }
        });
        this.saveButton.setText("Save");
        this.saveButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.54
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.saveButtonActionPerformed(actionEvent);
            }
        });
        this.aboutButton.setText("About");
        this.aboutButton.addActionListener(new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.55
            public void actionPerformed(ActionEvent actionEvent) {
                OkapiUI.this.aboutButtonActionPerformed(actionEvent);
            }
        });
        GroupLayout groupLayout20 = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout20);
        groupLayout20.setHorizontalGroup(groupLayout20.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout20.createSequentialGroup().addContainerGap().addComponent(this.symbolPane, -2, 209, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(groupLayout20.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout20.createSequentialGroup().addComponent(this.aboutButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.saveButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.exitButton)).addComponent(this.mainTabs)).addContainerGap()));
        groupLayout20.setVerticalGroup(groupLayout20.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout20.createSequentialGroup().addContainerGap().addGroup(groupLayout20.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.symbolPane).addGroup(groupLayout20.createSequentialGroup().addComponent(this.mainTabs).addGap(11, 11, 11).addGroup(groupLayout20.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.exitButton).addComponent(this.saveButton).addComponent(this.aboutButton)).addGap(5, 5, 5))).addContainerGap()));
        pack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void directoryButtonActionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Directory Select");
        jFileChooser.setFileSelectionMode(1);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        if (jFileChooser.showOpenDialog(this) == 0) {
            this.folderField.setText(jFileChooser.getSelectedFile().getAbsolutePath() + File.separator);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aboutButtonActionPerformed(ActionEvent actionEvent) {
        AboutOkapi aboutOkapi = new AboutOkapi();
        aboutOkapi.setLocationRelativeTo(this);
        aboutOkapi.setVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveButtonActionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(this) == 0) {
            File selectedFile = jFileChooser.getSelectedFile();
            SaveSymbol saveSymbol = new SaveSymbol();
            saveSymbol.removeAll();
            saveSymbol.setSize(saveSymbol.getPreferredSize());
            saveSymbol.setBorder(BorderFactory.createEmptyBorder());
            saveSymbol.setBackground(paperColour);
            try {
                new SaveImage().save(selectedFile, saveSymbol);
            } catch (IOException e) {
                System.out.println("Cannot write to file " + jFileChooser.getSelectedFile().toString() + ": " + e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitButtonActionPerformed(ActionEvent actionEvent) {
        dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadDataButtonActionPerformed(ActionEvent actionEvent) {
        MoreData moreData = new MoreData();
        moreData.setLocationRelativeTo(this);
        moreData.setVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetButtonActionPerformed(ActionEvent actionEvent) {
        this.sequenceArea.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createButtonActionPerformed(ActionEvent actionEvent) {
        try {
            int parseInt = Integer.parseInt(this.startField.getText());
            int parseInt2 = Integer.parseInt(this.stopField.getText());
            int parseInt3 = Integer.parseInt(this.incrementField.getText());
            if (parseInt3 <= 0) {
                System.out.println("Invalid increment value");
                return;
            }
            if (parseInt >= parseInt2) {
                System.out.println("Invalid sequence");
                return;
            }
            String str = "";
            while (parseInt <= parseInt2) {
                str = (str + applyFormat(Integer.toString(parseInt))) + '\n';
                parseInt += parseInt3;
            }
            this.sequenceArea.setText(str);
        } catch (NumberFormatException unused) {
            System.out.println("Invalid input value");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void batchFileButtonActionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(this) != 0) {
            return;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(jFileChooser.getSelectedFile()), "UTF8"));
            try {
                this.sequenceArea.setText("");
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        this.sequenceArea.setText(this.sequenceArea.getText() + readLine + '\n');
                    } else {
                        bufferedReader.close();
                        return;
                    }
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (UnsupportedEncodingException unused) {
            System.out.println("Encoding exception");
        } catch (IOException unused2) {
            System.out.println("Cannot read from file" + jFileChooser.getSelectedFile().toString());
        } catch (Exception unused3) {
            System.out.println("Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runBatchButtonActionPerformed(ActionEvent actionEvent) {
        String str;
        char c;
        String str2;
        char[] cArr;
        char c2;
        String str3 = "Starting batch process...\n";
        this.batchOutputArea.setText("Starting batch process...\n");
        int selectedIndex = this.outFormatCombo.getSelectedIndex();
        int i = 1;
        if (selectedIndex == 0) {
            str = ".png";
        } else if (selectedIndex == 1) {
            str = ".jpg";
        } else if (selectedIndex == 2) {
            str = ".gif";
        } else if (selectedIndex == 3) {
            str = ".bmp";
        } else if (selectedIndex == 4) {
            str = ".svg";
        } else {
            str = ".eps";
        }
        String str4 = str;
        char[] charArray = this.sequenceArea.getText().toCharArray();
        int length = charArray.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            c = '\n';
            if (i3 >= length) {
                break;
            }
            if (charArray[i3] == '\n') {
                i4++;
            }
            i3++;
        }
        String content = symbol.getContent();
        int length2 = Integer.toString(i4 + 1).length();
        int length3 = charArray.length;
        String str5 = "";
        String str6 = str5;
        while (i2 < length3) {
            char c3 = charArray[i2];
            if (c3 == c) {
                String str7 = "";
                for (int length4 = Integer.toString(i).length(); length4 < length2; length4++) {
                    str7 = str7 + "0";
                }
                String str8 = str7 + Integer.toString(i);
                if (str6.isEmpty()) {
                    str2 = str4;
                } else {
                    String str9 = this.outFilenameCombo.getSelectedIndex() == 0 ? this.prefixField.getText() + osFriendly(str6) + str4 : this.prefixField.getText() + str8 + str4;
                    dataInput = str6;
                    encodeData();
                    SaveSymbol saveSymbol = new SaveSymbol();
                    saveSymbol.removeAll();
                    saveSymbol.setSize(saveSymbol.getPreferredSize());
                    saveSymbol.setBorder(BorderFactory.createEmptyBorder());
                    saveSymbol.setBackground(paperColour);
                    try {
                        SaveImage saveImage = new SaveImage();
                        if (this.errorLabel.getText().isEmpty()) {
                            str2 = str4;
                            try {
                                saveImage.save(new File(this.folderField.getText(), str9), saveSymbol);
                            } catch (IOException e) {
                                e = e;
                                str3 = str3 + "I/O exception writing to " + str9 + " at line " + i + ": " + e.getMessage() + '\n';
                                cArr = charArray;
                                str5 = "Completed line " + i + " of " + i4 + " (" + ((int) ((i * 100.0d) / i4)) + "% done)";
                                this.batchOutputArea.setText(str3 + str5);
                                i++;
                                str6 = "";
                                c2 = '\n';
                                i2++;
                                charArray = cArr;
                                c = c2;
                                str4 = str2;
                            }
                        } else {
                            str2 = str4;
                            str3 = str3 + this.errorLabel.getText() + " at line " + i + '\n';
                        }
                    } catch (IOException e2) {
                        e = e2;
                        str2 = str4;
                    }
                }
                cArr = charArray;
                str5 = "Completed line " + i + " of " + i4 + " (" + ((int) ((i * 100.0d) / i4)) + "% done)";
                this.batchOutputArea.setText(str3 + str5);
                i++;
                str6 = "";
            } else {
                str2 = str4;
                cArr = charArray;
                if (c3 != '\r') {
                    c2 = '\n';
                    if (c3 != '\n') {
                        str6 = str6 + c3;
                    }
                    i2++;
                    charArray = cArr;
                    c = c2;
                    str4 = str2;
                }
            }
            c2 = '\n';
            i2++;
            charArray = cArr;
            c = c2;
            str4 = str2;
        }
        dataInput = content;
        encodeData();
        this.batchOutputArea.setText(str3 + (str5 + "\nFinished!"));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0058 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0046 A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String osFriendly(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r5 = ""
            r0 = 0
            r1 = r0
        L4:
            int r2 = r6.length()
            if (r1 >= r2) goto L5b
            char r2 = r6.charAt(r1)
            if (r2 <= 0) goto L16
            r3 = 32
            if (r2 >= r3) goto L16
            r3 = r0
            goto L17
        L16:
            r3 = 1
        L17:
            r4 = 126(0x7e, float:1.77E-43)
            if (r2 <= r4) goto L20
            r4 = 160(0xa0, float:2.24E-43)
            if (r2 >= r4) goto L20
            r3 = r0
        L20:
            r4 = 33
            if (r2 == r4) goto L46
            r4 = 34
            if (r2 == r4) goto L46
            r4 = 37
            if (r2 == r4) goto L46
            r4 = 58
            if (r2 == r4) goto L46
            r4 = 124(0x7c, float:1.74E-43)
            if (r2 == r4) goto L46
            r4 = 46
            if (r2 == r4) goto L46
            r4 = 47
            if (r2 == r4) goto L46
            switch(r2) {
                case 42: goto L46;
                case 43: goto L46;
                case 44: goto L46;
                default: goto L3f;
            }
        L3f:
            switch(r2) {
                case 60: goto L46;
                case 61: goto L46;
                case 62: goto L46;
                case 63: goto L46;
                case 64: goto L46;
                default: goto L42;
            }
        L42:
            switch(r2) {
                case 91: goto L46;
                case 92: goto L46;
                case 93: goto L46;
                default: goto L45;
            }
        L45:
            goto L47
        L46:
            r3 = r0
        L47:
            if (r3 == 0) goto L58
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            r3.append(r2)
            java.lang.String r5 = r3.toString()
        L58:
            int r1 = r1 + 1
            goto L4
        L5b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.gui.OkapiUI.osFriendly(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inkButtonActionPerformed(ActionEvent actionEvent) {
        final JColorChooser jColorChooser = new JColorChooser();
        JDialog createDialog = JColorChooser.createDialog(this, "Ink Colour", false, jColorChooser, new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.56
            public void actionPerformed(ActionEvent actionEvent2) {
                OkapiUI.setInk(jColorChooser.getColor());
            }
        }, (ActionListener) null);
        createDialog.setVisible(true);
        createDialog.addWindowListener(new WindowAdapter() { // from class: uk.org.okapibarcode.gui.OkapiUI.57
            public void windowClosing(WindowEvent windowEvent) {
                OkapiUI.setInk(jColorChooser.getColor());
                OkapiUI.this.dispose();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void paperButtonActionPerformed(ActionEvent actionEvent) {
        final JColorChooser jColorChooser = new JColorChooser();
        JDialog createDialog = JColorChooser.createDialog(this, "Paper Colour", false, jColorChooser, new ActionListener() { // from class: uk.org.okapibarcode.gui.OkapiUI.58
            public void actionPerformed(ActionEvent actionEvent2) {
                OkapiUI.setPaper(jColorChooser.getColor());
            }
        }, (ActionListener) null);
        createDialog.setVisible(true);
        createDialog.addWindowListener(new WindowAdapter() { // from class: uk.org.okapibarcode.gui.OkapiUI.59
            public void windowClosing(WindowEvent windowEvent) {
                OkapiUI.setPaper(jColorChooser.getColor());
                OkapiUI.this.dispose();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetColourButtonActionPerformed(ActionEvent actionEvent) {
        setInk(Color.BLACK);
        setPaper(Color.WHITE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aztecUserSizeActionPerformed(ActionEvent actionEvent) {
        this.aztecUserSizeCombo.setEnabled(true);
        this.aztecUserEccCombo.setEnabled(false);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aztecAutoSizeActionPerformed(ActionEvent actionEvent) {
        this.aztecUserSizeCombo.setEnabled(false);
        this.aztecUserEccCombo.setEnabled(false);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aztecUserEccActionPerformed(ActionEvent actionEvent) {
        this.aztecUserSizeCombo.setEnabled(false);
        this.aztecUserEccCombo.setEnabled(true);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void channelChannelsComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dataMatrixSquareOnlyCheckActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gridmatrixAutoSizeActionPerformed(ActionEvent actionEvent) {
        this.gridmatrixUserSizeCombo.setEnabled(false);
        this.gridmatrixUserEccCombo.setEnabled(false);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gridmatrixUserSizeActionPerformed(ActionEvent actionEvent) {
        this.gridmatrixUserSizeCombo.setEnabled(true);
        this.gridmatrixUserEccCombo.setEnabled(false);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gridmatrixUserEccActionPerformed(ActionEvent actionEvent) {
        this.gridmatrixUserSizeCombo.setEnabled(false);
        this.gridmatrixUserEccCombo.setEnabled(true);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void microQrAutoSizeActionPerformed(ActionEvent actionEvent) {
        this.microQrUserSizeCombo.setEnabled(false);
        this.microQrUserEccCombo.setEnabled(false);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void microQrUserSizeActionPerformed(ActionEvent actionEvent) {
        this.microQrUserSizeCombo.setEnabled(true);
        this.microQrUserEccCombo.setEnabled(false);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void microQrUserEccActionPerformed(ActionEvent actionEvent) {
        this.microQrUserSizeCombo.setEnabled(false);
        this.microQrUserEccCombo.setEnabled(true);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qrAutoSizeActionPerformed(ActionEvent actionEvent) {
        this.qrUserSizeCombo.setEnabled(false);
        this.qrUserEccCombo.setEnabled(false);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qrUserSizeComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qrUserSizeActionPerformed(ActionEvent actionEvent) {
        this.qrUserSizeCombo.setEnabled(true);
        this.qrUserEccCombo.setEnabled(false);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qrUserEccActionPerformed(ActionEvent actionEvent) {
        this.qrUserSizeCombo.setEnabled(false);
        this.qrUserEccCombo.setEnabled(true);
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gridmatrixUserSizeComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maxiEncodingModeComboActionPerformed(ActionEvent actionEvent) {
        if (this.maxiEncodingModeCombo.getSelectedIndex() == 0 || this.maxiEncodingModeCombo.getSelectedIndex() == 1) {
            this.maxiPrimaryData.setEnabled(true);
            this.maxiPrimaryDataLabel.setEnabled(true);
        } else {
            this.maxiPrimaryData.setEnabled(false);
            this.maxiPrimaryDataLabel.setEnabled(false);
        }
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCompositeButtonActionPerformed(ActionEvent actionEvent) {
        AddComposite addComposite = new AddComposite();
        addComposite.setLocationRelativeTo(this);
        addComposite.setVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aztecUserSizeComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aztecUserEccComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void code39CheckComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void codeOneSizeComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void databarColumnsComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dataMatrixSizeComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gridmatrixUserEccComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maxiPrimaryDataActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void microPdfColumnsComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void microQrUserSizeComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void microQrUserEccComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void msiCheckDigitComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdfColumnsComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdfEccComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qrUserEccComboActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void useGS1CheckActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compositeUserModeActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void useCompositeCheckActionPerformed(ActionEvent actionEvent) {
        if (this.useCompositeCheck.isSelected()) {
            this.compositeLabel.setEnabled(true);
            compositeInputField.setEnabled(true);
            this.addCompositeButton.setEnabled(true);
        } else {
            this.compositeLabel.setEnabled(false);
            compositeInputField.setEnabled(false);
            this.addCompositeButton.setEnabled(false);
        }
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cmbHrtPositionActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chkReaderInitActionPerformed(ActionEvent actionEvent) {
        encodeData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void txtBorderWidthFocusLost(FocusEvent focusEvent) {
        if (this.txtBorderWidth.getText().matches("[0-9]+")) {
            quietZoneHorizontal = Integer.parseInt(this.txtBorderWidth.getText());
            encodeData();
        } else {
            this.txtBorderWidth.setText(String.valueOf(quietZoneHorizontal));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void txtXDimensionFocusLost(FocusEvent focusEvent) {
        if (this.txtXDimension.getText().matches("[0-9]+")) {
            if (Integer.parseInt(this.txtXDimension.getText()) != 0) {
                moduleWidth = Integer.parseInt(this.txtXDimension.getText());
                encodeData();
                return;
            } else {
                this.txtXDimension.setText(String.valueOf(moduleWidth));
                return;
            }
        }
        this.txtXDimension.setText(String.valueOf(moduleWidth));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void txtWhitespaceWidthFocusLost(FocusEvent focusEvent) {
        if (this.txtWhitespaceWidth.getText().matches("[0-9]+")) {
            quietZoneVertical = Integer.parseInt(this.txtWhitespaceWidth.getText());
            encodeData();
        } else {
            this.txtWhitespaceWidth.setText(String.valueOf(quietZoneVertical));
        }
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
            java.lang.Class<uk.org.okapibarcode.gui.OkapiUI> r7 = uk.org.okapibarcode.gui.OkapiUI.class
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
            uk.org.okapibarcode.gui.OkapiUI$60 r7 = new uk.org.okapibarcode.gui.OkapiUI$60
            r7.<init>()
            java.awt.EventQueue.invokeLater(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.gui.OkapiUI.main(java.lang.String[]):void");
    }

    public static void setInk(Color color) {
        inkColour = color;
    }

    public static void setPaper(Color color) {
        paperColour = color;
    }

    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) this.symbolTree.getLastSelectedPathComponent();
        if (defaultMutableTreeNode != null) {
            Object userObject = defaultMutableTreeNode.getUserObject();
            if (defaultMutableTreeNode.isLeaf()) {
                this.selectedSymbol = (SymbolType) userObject;
                dataInput = dataInputField.getText();
                compositeInput = compositeInputField.getText();
                switch (AnonymousClass61.$SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[this.selectedSymbol.symbology.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        this.useGS1Check.setEnabled(true);
                        break;
                    default:
                        this.useGS1Check.setEnabled(false);
                        break;
                }
                int i = AnonymousClass61.$SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[this.selectedSymbol.symbology.ordinal()];
                if (i != 2) {
                    switch (i) {
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                            break;
                        default:
                            this.useCompositeCheck.setEnabled(false);
                            this.compositeLabel.setEnabled(false);
                            compositeInputField.setEnabled(false);
                            this.addCompositeButton.setEnabled(false);
                            break;
                    }
                    encodeData();
                }
                this.useCompositeCheck.setEnabled(true);
                if (this.useCompositeCheck.isSelected()) {
                    this.compositeLabel.setEnabled(true);
                    compositeInputField.setEnabled(true);
                    this.addCompositeButton.setEnabled(true);
                }
                encodeData();
            }
        }
    }

    /* renamed from: uk.org.okapibarcode.gui.OkapiUI$61, reason: invalid class name */
    static /* synthetic */ class AnonymousClass61 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding;

        static {
            int[] iArr = new int[SymbolType.Encoding.values().length];
            $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding = iArr;
            try {
                iArr[SymbolType.Encoding.AZTEC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE_128.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE16K.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE49.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE_ONE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DATAMATRIX.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.QR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.EAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.UPC_E.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DB14_STACKED_OMNIDIRECT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DB14_STACKED.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DB_LIMITED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DB14.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DB_EXPANDED.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.UPC_A.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DB_EXPANDED_STACKED.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.ITF14.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE_128_HIBC.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.NVE18.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODABAR.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE25_MATRIX.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE25_INDUSTRY.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE25_INTERLEAVED.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.MSI_PLESSEY.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE39.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE39_HIBC.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DOD_LOGMARS.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE_11.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE93.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.PZN.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE39_EXTENDED.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.TELEPEN.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.TELEPEN_NUMERIC.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.KOREA_POST.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE25_IATA.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE25_DATALOGIC.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DP_LEITCODE.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DP_IDENTCODE.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.USPS_POSTNET.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.BRAZIL_CEPNET.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.USPS_PLANET.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.RM4SCC.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.KIX_CODE.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.JAPAN_POST.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.AUSPOST.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.AUSPOST_REPLY.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.AUSPOST_REROUTE.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.AUSPOST_REDIRECT.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CHANNEL_CODE.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.PHARMA.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.PHARMA_TWOTRACK.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODE_32.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.PDF417.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.PDF417_HIBC.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.PDF417_TRUNCATED.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.PDF417_MICRO.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.PDF417_MICRO_HIBC.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.AZTEC_HIBC.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.AZTEC_RUNE.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.DATAMATRIX_HIBC.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.USPS_IMAIL.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.USPS_IMPB.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.QR_HIBC.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.QR_MICRO.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.GRIDMATRIX.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.MAXICODE.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODABLOCK_F.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[SymbolType.Encoding.CODABLOCK_HIBC.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
        }
    }

    public void encodeData() {
        DrawSymbol drawSymbol = new DrawSymbol();
        topPanel.removeAll();
        drawSymbol.removeAll();
        this.errorLabel.setText("");
        if (dataInput.isEmpty()) {
            this.errorLabel.setText("No input data");
            topPanel.add(this.errorLabel);
            topPanel.updateUI();
            return;
        }
        try {
            symbol = getNewSymbol();
            double width = topPanel.getWidth() / symbol.getWidth();
            double height = topPanel.getHeight() / symbol.getHeight();
            if (width < height) {
                factor = (int) width;
            } else {
                factor = (int) height;
            }
            drawSymbol.setBorder(BorderFactory.createEmptyBorder());
            drawSymbol.setBackground(paperColour);
            drawSymbol.setBounds((topPanel.getWidth() - drawSymbol.getPreferredSize().width) / 2, (topPanel.getHeight() - drawSymbol.getPreferredSize().height) / 2, drawSymbol.getPreferredSize().width, drawSymbol.getPreferredSize().height);
            topPanel.add(drawSymbol);
            this.encodeInfoArea.setText(symbol.getEncodeInfo());
            topPanel.updateUI();
            pack();
        } catch (OkapiException e) {
            this.errorLabel.setText(e.getMessage());
            topPanel.add(this.errorLabel);
            topPanel.updateUI();
        }
    }

    private String applyFormat(String str) {
        String text = this.formatField.getText();
        int length = str.length();
        int length2 = text.length();
        String str2 = "";
        String str3 = "";
        for (int i = length2; i > 0; i--) {
            char charAt = text.charAt(i - 1);
            if (charAt != '#') {
                if (charAt != '$') {
                    if (charAt != '*') {
                        str3 = str3 + charAt;
                    } else if (length > 0) {
                        str3 = str3 + str.charAt(length - 1);
                        length--;
                    } else {
                        str3 = str3 + '*';
                    }
                } else if (length > 0) {
                    str3 = str3 + str.charAt(length - 1);
                    length--;
                } else {
                    str3 = str3 + '0';
                }
            } else if (length > 0) {
                str3 = str3 + str.charAt(length - 1);
                length--;
            } else {
                str3 = str3 + ' ';
            }
        }
        while (length2 > 0) {
            str2 = str2 + str3.charAt(length2 - 1);
            length2--;
        }
        return str2;
    }

    private HumanReadableLocation getHrtLoc() {
        HumanReadableLocation humanReadableLocation = HumanReadableLocation.BOTTOM;
        int selectedIndex = this.cmbHrtPosition.getSelectedIndex();
        if (selectedIndex == 0) {
            return HumanReadableLocation.BOTTOM;
        }
        if (selectedIndex != 1) {
            return selectedIndex != 2 ? humanReadableLocation : HumanReadableLocation.NONE;
        }
        return HumanReadableLocation.TOP;
    }

    private void setUniversals(Symbol symbol2) {
        symbol2.setModuleWidth(moduleWidth);
        symbol2.setQuietZoneHorizontal(quietZoneHorizontal);
        symbol2.setQuietZoneVertical(quietZoneVertical);
    }

    private Symbol getNewSymbol() throws OkapiException {
        boolean isSelected = this.chkReaderInit.isSelected();
        HumanReadableLocation hrtLoc = getHrtLoc();
        if (this.selectedSymbol == null) {
            throw new OkapiException("No symbology selected");
        }
        if (this.useCompositeCheck.isEnabled() && this.useCompositeCheck.isSelected() && !compositeInput.isEmpty()) {
            Composite composite = new Composite();
            int i = AnonymousClass61.$SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[this.selectedSymbol.symbology.ordinal()];
            if (i != 2) {
                switch (i) {
                    case 8:
                        composite.setSymbology(Composite.LinearEncoding.EAN);
                        break;
                    case 9:
                        composite.setSymbology(Composite.LinearEncoding.UPCE);
                        break;
                    case 10:
                        composite.setSymbology(Composite.LinearEncoding.DATABAR_14_STACK_OMNI);
                        break;
                    case 11:
                        composite.setSymbology(Composite.LinearEncoding.DATABAR_14_STACK);
                        break;
                    case 12:
                        composite.setSymbology(Composite.LinearEncoding.DATABAR_LIMITED);
                        break;
                    case 13:
                        composite.setSymbology(Composite.LinearEncoding.DATABAR_14);
                        break;
                    case 14:
                        composite.setSymbology(Composite.LinearEncoding.DATABAR_EXPANDED);
                        break;
                    case 15:
                        composite.setSymbology(Composite.LinearEncoding.UPCA);
                        break;
                    case 16:
                        composite.setSymbology(Composite.LinearEncoding.DATABAR_EXPANDED_STACK);
                        break;
                }
            } else {
                composite.setSymbology(Composite.LinearEncoding.CODE_128);
            }
            composite.setLinearContent(dataInput);
            int selectedIndex = this.compositeUserMode.getSelectedIndex();
            if (selectedIndex == 1) {
                composite.setPreferredMode(Composite.CompositeMode.CC_A);
            } else if (selectedIndex == 2) {
                composite.setPreferredMode(Composite.CompositeMode.CC_B);
            } else if (selectedIndex == 3) {
                composite.setPreferredMode(Composite.CompositeMode.CC_C);
            }
            setUniversals(composite);
            composite.setContent(compositeInput);
            return composite;
        }
        switch (AnonymousClass61.$SwitchMap$uk$org$okapibarcode$gui$SymbolType$Encoding[this.selectedSymbol.symbology.ordinal()]) {
            case 1:
            case 58:
                AztecCode aztecCode = new AztecCode();
                if (this.useGS1Check.isSelected()) {
                    aztecCode.setDataType(Symbol.DataType.GS1);
                }
                if (this.selectedSymbol.symbology == SymbolType.Encoding.AZTEC_HIBC) {
                    aztecCode.setDataType(Symbol.DataType.HIBC);
                }
                aztecCode.setReaderInit(isSelected);
                if (this.aztecUserEcc.isSelected()) {
                    aztecCode.setPreferredEccLevel(this.aztecUserEccCombo.getSelectedIndex() + 1);
                }
                if (this.aztecUserSize.isSelected()) {
                    aztecCode.setPreferredSize(this.aztecUserSizeCombo.getSelectedIndex() + 1);
                }
                aztecCode.setContent(dataInput);
                setUniversals(aztecCode);
                return aztecCode;
            case 2:
            case 18:
                Code128 code128 = new Code128();
                code128.unsetCc();
                if (this.useGS1Check.isSelected()) {
                    code128.setDataType(Symbol.DataType.GS1);
                }
                if (this.selectedSymbol.symbology == SymbolType.Encoding.CODE_128_HIBC) {
                    code128.setDataType(Symbol.DataType.HIBC);
                }
                code128.setReaderInit(isSelected);
                code128.setHumanReadableLocation(hrtLoc);
                code128.setContent(dataInput);
                setUniversals(code128);
                return code128;
            case 3:
                Symbol code16k = new Code16k();
                if (this.useGS1Check.isSelected()) {
                    code16k.setDataType(Symbol.DataType.GS1);
                }
                code16k.setReaderInit(isSelected);
                code16k.setContent(dataInput);
                setUniversals(code16k);
                return code16k;
            case 4:
                Symbol code49 = new Code49();
                code49.setHumanReadableLocation(hrtLoc);
                code49.setContent(dataInput);
                setUniversals(code49);
                return code49;
            case 5:
                CodeOne codeOne = new CodeOne();
                if (this.useGS1Check.isSelected()) {
                    codeOne.setDataType(Symbol.DataType.GS1);
                }
                codeOne.setReaderInit(isSelected);
                switch (this.codeOneSizeCombo.getSelectedIndex()) {
                    case 0:
                        codeOne.setPreferredVersion(CodeOne.Version.NONE);
                        break;
                    case 1:
                        codeOne.setPreferredVersion(CodeOne.Version.A);
                        break;
                    case 2:
                        codeOne.setPreferredVersion(CodeOne.Version.B);
                        break;
                    case 3:
                        codeOne.setPreferredVersion(CodeOne.Version.C);
                        break;
                    case 4:
                        codeOne.setPreferredVersion(CodeOne.Version.D);
                        break;
                    case 5:
                        codeOne.setPreferredVersion(CodeOne.Version.E);
                        break;
                    case 6:
                        codeOne.setPreferredVersion(CodeOne.Version.F);
                        break;
                    case 7:
                        codeOne.setPreferredVersion(CodeOne.Version.G);
                        break;
                    case 8:
                        codeOne.setPreferredVersion(CodeOne.Version.H);
                        break;
                    case 9:
                        codeOne.setPreferredVersion(CodeOne.Version.S);
                        break;
                    case 10:
                        codeOne.setPreferredVersion(CodeOne.Version.T);
                        break;
                }
                codeOne.setContent(dataInput);
                setUniversals(codeOne);
                return codeOne;
            case 6:
            case 60:
                DataMatrix dataMatrix = new DataMatrix();
                if (this.useGS1Check.isSelected()) {
                    dataMatrix.setDataType(Symbol.DataType.GS1);
                }
                if (this.selectedSymbol.symbology == SymbolType.Encoding.DATAMATRIX_HIBC) {
                    dataMatrix.setDataType(Symbol.DataType.HIBC);
                }
                dataMatrix.setReaderInit(isSelected);
                dataMatrix.setPreferredSize(this.dataMatrixSizeCombo.getSelectedIndex());
                dataMatrix.setForceMode(this.dataMatrixSquareOnlyCheck.isSelected() ? DataMatrix.ForceMode.SQUARE : DataMatrix.ForceMode.NONE);
                dataMatrix.setContent(dataInput);
                setUniversals(dataMatrix);
                return dataMatrix;
            case 7:
            case 63:
                QrCode qrCode = new QrCode();
                if (this.useGS1Check.isSelected()) {
                    qrCode.setDataType(Symbol.DataType.GS1);
                }
                if (this.selectedSymbol.symbology == SymbolType.Encoding.QR_HIBC) {
                    qrCode.setDataType(Symbol.DataType.HIBC);
                }
                if (this.qrUserEcc.isSelected()) {
                    int selectedIndex2 = this.qrUserEccCombo.getSelectedIndex();
                    if (selectedIndex2 == 0) {
                        qrCode.setPreferredEccLevel(QrCode.EccLevel.L);
                    } else if (selectedIndex2 == 1) {
                        qrCode.setPreferredEccLevel(QrCode.EccLevel.M);
                    } else if (selectedIndex2 == 2) {
                        qrCode.setPreferredEccLevel(QrCode.EccLevel.Q);
                    } else if (selectedIndex2 == 3) {
                        qrCode.setPreferredEccLevel(QrCode.EccLevel.H);
                    }
                }
                if (this.qrUserSize.isSelected()) {
                    qrCode.setPreferredVersion(this.qrUserSizeCombo.getSelectedIndex() + 1);
                }
                qrCode.setReaderInit(isSelected);
                qrCode.setContent(dataInput);
                setUniversals(qrCode);
                return qrCode;
            case 8:
                Ean ean = new Ean();
                if (eanCalculateVersion() == 8) {
                    ean.setMode(Ean.Mode.EAN8);
                } else {
                    ean.setMode(Ean.Mode.EAN13);
                }
                ean.setContent(dataInput);
                setUniversals(ean);
                return ean;
            case 9:
                Upc upc = new Upc();
                upc.setMode(Upc.Mode.UPCE);
                upc.setContent(dataInput);
                setUniversals(upc);
                return upc;
            case 10:
                DataBar14 dataBar14 = new DataBar14();
                dataBar14.setMode(DataBar14.Mode.OMNI);
                dataBar14.setContent(dataInput);
                setUniversals(dataBar14);
                return dataBar14;
            case 11:
                DataBar14 dataBar142 = new DataBar14();
                dataBar142.setMode(DataBar14.Mode.STACKED);
                dataBar142.setContent(dataInput);
                setUniversals(dataBar142);
                return dataBar142;
            case 12:
                Symbol dataBarLimited = new DataBarLimited();
                dataBarLimited.setHumanReadableLocation(hrtLoc);
                dataBarLimited.setContent(dataInput);
                setUniversals(dataBarLimited);
                return dataBarLimited;
            case 13:
                DataBar14 dataBar143 = new DataBar14();
                dataBar143.setMode(DataBar14.Mode.LINEAR);
                dataBar143.setHumanReadableLocation(hrtLoc);
                dataBar143.setContent(dataInput);
                setUniversals(dataBar143);
                return dataBar143;
            case 14:
                DataBarExpanded dataBarExpanded = new DataBarExpanded();
                dataBarExpanded.setStacked(false);
                dataBarExpanded.setContent(dataInput);
                setUniversals(dataBarExpanded);
                return dataBarExpanded;
            case 15:
                Upc upc2 = new Upc();
                upc2.setMode(Upc.Mode.UPCA);
                upc2.setContent(dataInput);
                setUniversals(upc2);
                return upc2;
            case 16:
                DataBarExpanded dataBarExpanded2 = new DataBarExpanded();
                dataBarExpanded2.setPreferredColumns(this.databarColumnsCombo.getSelectedIndex());
                dataBarExpanded2.setStacked(true);
                dataBarExpanded2.setContent(dataInput);
                setUniversals(dataBarExpanded2);
                return dataBarExpanded2;
            case 17:
                Code2Of5 code2Of5 = new Code2Of5();
                code2Of5.setMode(Code2Of5.ToFMode.ITF14);
                code2Of5.setHumanReadableLocation(hrtLoc);
                code2Of5.setContent(dataInput);
                setUniversals(code2Of5);
                return code2Of5;
            case 19:
                Symbol nve18 = new Nve18();
                nve18.setHumanReadableLocation(hrtLoc);
                nve18.setContent(dataInput);
                setUniversals(nve18);
                return nve18;
            case 20:
                Symbol codabar = new Codabar();
                codabar.setHumanReadableLocation(hrtLoc);
                codabar.setContent(dataInput);
                setUniversals(codabar);
                return codabar;
            case 21:
                Code2Of5 code2Of52 = new Code2Of5();
                code2Of52.setMode(Code2Of5.ToFMode.MATRIX);
                code2Of52.setHumanReadableLocation(hrtLoc);
                code2Of52.setContent(dataInput);
                setUniversals(code2Of52);
                return code2Of52;
            case 22:
                Code2Of5 code2Of53 = new Code2Of5();
                code2Of53.setMode(Code2Of5.ToFMode.INDUSTRIAL);
                code2Of53.setHumanReadableLocation(hrtLoc);
                code2Of53.setContent(dataInput);
                setUniversals(code2Of53);
                return code2Of53;
            case 23:
                Code2Of5 code2Of54 = new Code2Of5();
                code2Of54.setMode(Code2Of5.ToFMode.INTERLEAVED);
                code2Of54.setHumanReadableLocation(hrtLoc);
                code2Of54.setContent(dataInput);
                setUniversals(code2Of54);
                return code2Of54;
            case 24:
                MsiPlessey msiPlessey = new MsiPlessey();
                int selectedIndex3 = this.msiCheckDigitCombo.getSelectedIndex();
                if (selectedIndex3 == 0) {
                    msiPlessey.setCheckDigit(MsiPlessey.CheckDigit.NONE);
                } else if (selectedIndex3 == 1) {
                    msiPlessey.setCheckDigit(MsiPlessey.CheckDigit.MOD10);
                } else if (selectedIndex3 == 2) {
                    msiPlessey.setCheckDigit(MsiPlessey.CheckDigit.MOD10_MOD10);
                } else if (selectedIndex3 == 3) {
                    msiPlessey.setCheckDigit(MsiPlessey.CheckDigit.MOD11);
                } else if (selectedIndex3 == 4) {
                    msiPlessey.setCheckDigit(MsiPlessey.CheckDigit.MOD11_MOD10);
                }
                msiPlessey.setHumanReadableLocation(hrtLoc);
                msiPlessey.setContent(dataInput);
                setUniversals(msiPlessey);
                return msiPlessey;
            case 25:
            case 26:
                Code3Of9 code3Of9 = new Code3Of9();
                if (this.selectedSymbol.symbology == SymbolType.Encoding.CODE39_HIBC) {
                    code3Of9.setDataType(Symbol.DataType.HIBC);
                }
                int selectedIndex4 = this.code39CheckCombo.getSelectedIndex();
                if (selectedIndex4 == 0) {
                    code3Of9.setCheckDigit(Code3Of9.CheckDigit.NONE);
                } else if (selectedIndex4 == 1) {
                    code3Of9.setCheckDigit(Code3Of9.CheckDigit.MOD43);
                }
                code3Of9.setHumanReadableLocation(hrtLoc);
                code3Of9.setContent(dataInput);
                setUniversals(code3Of9);
                return code3Of9;
            case 27:
                Symbol logmars = new Logmars();
                logmars.setHumanReadableLocation(hrtLoc);
                logmars.setContent(dataInput);
                setUniversals(logmars);
                return logmars;
            case 28:
                Symbol code11 = new Code11();
                code11.setHumanReadableLocation(hrtLoc);
                code11.setContent(dataInput);
                setUniversals(code11);
                return code11;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                Symbol code93 = new Code93();
                code93.setHumanReadableLocation(hrtLoc);
                code93.setContent(dataInput);
                setUniversals(code93);
                return code93;
            case 30:
                Symbol pharmazentralnummer = new Pharmazentralnummer();
                pharmazentralnummer.setHumanReadableLocation(hrtLoc);
                pharmazentralnummer.setContent(dataInput);
                setUniversals(pharmazentralnummer);
                return pharmazentralnummer;
            case 31:
                Code3Of9Extended code3Of9Extended = new Code3Of9Extended();
                int selectedIndex5 = this.code39CheckCombo.getSelectedIndex();
                if (selectedIndex5 == 0) {
                    code3Of9Extended.setCheckDigit(Code3Of9Extended.CheckDigit.NONE);
                } else if (selectedIndex5 == 1) {
                    code3Of9Extended.setCheckDigit(Code3Of9Extended.CheckDigit.MOD43);
                }
                code3Of9Extended.setHumanReadableLocation(hrtLoc);
                code3Of9Extended.setContent(dataInput);
                setUniversals(code3Of9Extended);
                return code3Of9Extended;
            case 32:
                Telepen telepen = new Telepen();
                telepen.setMode(Telepen.Mode.NORMAL);
                telepen.setHumanReadableLocation(hrtLoc);
                telepen.setContent(dataInput);
                setUniversals(telepen);
                return telepen;
            case 33:
                Telepen telepen2 = new Telepen();
                telepen2.setMode(Telepen.Mode.NUMERIC);
                telepen2.setHumanReadableLocation(hrtLoc);
                telepen2.setContent(dataInput);
                setUniversals(telepen2);
                return telepen2;
            case 34:
                Symbol koreaPost = new KoreaPost();
                koreaPost.setHumanReadableLocation(hrtLoc);
                koreaPost.setContent(dataInput);
                setUniversals(koreaPost);
                return koreaPost;
            case 35:
                Code2Of5 code2Of55 = new Code2Of5();
                code2Of55.setMode(Code2Of5.ToFMode.IATA);
                code2Of55.setHumanReadableLocation(hrtLoc);
                code2Of55.setContent(dataInput);
                setUniversals(code2Of55);
                return code2Of55;
            case 36:
                Code2Of5 code2Of56 = new Code2Of5();
                code2Of56.setMode(Code2Of5.ToFMode.DATA_LOGIC);
                code2Of56.setHumanReadableLocation(hrtLoc);
                code2Of56.setContent(dataInput);
                setUniversals(code2Of56);
                return code2Of56;
            case 37:
                Code2Of5 code2Of57 = new Code2Of5();
                code2Of57.setMode(Code2Of5.ToFMode.DP_LEITCODE);
                code2Of57.setHumanReadableLocation(hrtLoc);
                code2Of57.setContent(dataInput);
                setUniversals(code2Of57);
                return code2Of57;
            case 38:
                Code2Of5 code2Of58 = new Code2Of5();
                code2Of58.setMode(Code2Of5.ToFMode.DP_IDENTCODE);
                code2Of58.setHumanReadableLocation(hrtLoc);
                code2Of58.setContent(dataInput);
                setUniversals(code2Of58);
                return code2Of58;
            case 39:
            case 40:
                Postnet postnet = new Postnet();
                postnet.setMode(Postnet.Mode.POSTNET);
                postnet.setContent(dataInput);
                setUniversals(postnet);
                return postnet;
            case 41:
                Postnet postnet2 = new Postnet();
                postnet2.setMode(Postnet.Mode.PLANET);
                postnet2.setContent(dataInput);
                setUniversals(postnet2);
                return postnet2;
            case 42:
                Symbol royalMail4State = new RoyalMail4State();
                royalMail4State.setContent(dataInput);
                setUniversals(royalMail4State);
                return royalMail4State;
            case 43:
                Symbol kixCode = new KixCode();
                kixCode.setContent(dataInput);
                setUniversals(kixCode);
                return kixCode;
            case 44:
                Symbol japanPost = new JapanPost();
                japanPost.setContent(dataInput);
                setUniversals(japanPost);
                return japanPost;
            case 45:
                AustraliaPost australiaPost = new AustraliaPost();
                australiaPost.setMode(AustraliaPost.Mode.POST);
                australiaPost.setContent(dataInput);
                setUniversals(australiaPost);
                return australiaPost;
            case 46:
                AustraliaPost australiaPost2 = new AustraliaPost();
                australiaPost2.setMode(AustraliaPost.Mode.REPLY);
                australiaPost2.setContent(dataInput);
                setUniversals(australiaPost2);
                return australiaPost2;
            case 47:
                AustraliaPost australiaPost3 = new AustraliaPost();
                australiaPost3.setMode(AustraliaPost.Mode.ROUTE);
                australiaPost3.setContent(dataInput);
                setUniversals(australiaPost3);
                return australiaPost3;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                AustraliaPost australiaPost4 = new AustraliaPost();
                australiaPost4.setMode(AustraliaPost.Mode.REDIRECT);
                australiaPost4.setContent(dataInput);
                setUniversals(australiaPost4);
                return australiaPost4;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                ChannelCode channelCode = new ChannelCode();
                channelCode.setPreferredNumberOfChannels(this.channelChannelsCombo.getSelectedIndex() + 2);
                channelCode.setHumanReadableLocation(hrtLoc);
                channelCode.setContent(dataInput);
                setUniversals(channelCode);
                return channelCode;
            case 50:
                Symbol pharmacode = new Pharmacode();
                pharmacode.setHumanReadableLocation(hrtLoc);
                pharmacode.setContent(dataInput);
                setUniversals(pharmacode);
                return pharmacode;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                Symbol pharmacode2Track = new Pharmacode2Track();
                pharmacode2Track.setContent(dataInput);
                setUniversals(pharmacode2Track);
                return pharmacode2Track;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                Symbol code32 = new Code32();
                code32.setHumanReadableLocation(hrtLoc);
                code32.setContent(dataInput);
                setUniversals(code32);
                return code32;
            case 53:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
            case 55:
                Pdf417 pdf417 = new Pdf417();
                if (this.useGS1Check.isSelected()) {
                    pdf417.setDataType(Symbol.DataType.GS1);
                }
                if (this.selectedSymbol.symbology == SymbolType.Encoding.PDF417_HIBC) {
                    pdf417.setDataType(Symbol.DataType.HIBC);
                } else if (this.selectedSymbol.symbology == SymbolType.Encoding.PDF417_TRUNCATED) {
                    pdf417.setMode(Pdf417.Mode.TRUNCATED);
                }
                if (this.pdfEccCombo.getSelectedIndex() != 0) {
                    pdf417.setPreferredEccLevel(this.pdfEccCombo.getSelectedIndex() - 1);
                }
                if (this.pdfColumnsCombo.getSelectedIndex() != 0) {
                    pdf417.setDataColumns(this.pdfColumnsCombo.getSelectedIndex());
                }
                pdf417.setReaderInit(isSelected);
                pdf417.setContent(dataInput);
                setUniversals(pdf417);
                return pdf417;
            case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
            case 57:
                Pdf417 pdf4172 = new Pdf417();
                pdf4172.setMode(Pdf417.Mode.MICRO);
                if (this.useGS1Check.isSelected()) {
                    pdf4172.setDataType(Symbol.DataType.GS1);
                }
                if (this.selectedSymbol.symbology == SymbolType.Encoding.PDF417_MICRO_HIBC) {
                    pdf4172.setDataType(Symbol.DataType.HIBC);
                }
                pdf4172.setReaderInit(isSelected);
                if (this.microPdfColumnsCombo.getSelectedIndex() != 0) {
                    pdf4172.setDataColumns(this.microPdfColumnsCombo.getSelectedIndex());
                }
                pdf4172.setContent(dataInput);
                setUniversals(pdf4172);
                return pdf4172;
            case 59:
                Symbol aztecRune = new AztecRune();
                aztecRune.setContent(dataInput);
                setUniversals(aztecRune);
                return aztecRune;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                Symbol uspsOneCode = new UspsOneCode();
                uspsOneCode.setContent(dataInput);
                setUniversals(uspsOneCode);
                return uspsOneCode;
            case 62:
                Symbol uspsPackage = new UspsPackage();
                uspsPackage.setContent(dataInput);
                setUniversals(uspsPackage);
                return uspsPackage;
            case 64:
                MicroQrCode microQrCode = new MicroQrCode();
                if (this.microQrUserEcc.isSelected()) {
                    int selectedIndex6 = this.microQrUserEccCombo.getSelectedIndex();
                    if (selectedIndex6 == 0) {
                        microQrCode.setEccMode(MicroQrCode.EccMode.L);
                    } else if (selectedIndex6 == 1) {
                        microQrCode.setEccMode(MicroQrCode.EccMode.M);
                    } else if (selectedIndex6 == 2) {
                        microQrCode.setEccMode(MicroQrCode.EccMode.Q);
                    } else if (selectedIndex6 == 3) {
                        microQrCode.setEccMode(MicroQrCode.EccMode.H);
                    }
                }
                if (this.microQrUserSize.isSelected()) {
                    microQrCode.setPreferredVersion(this.microQrUserSizeCombo.getSelectedIndex() + 1);
                }
                microQrCode.setContent(dataInput);
                setUniversals(microQrCode);
                return microQrCode;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                GridMatrix gridMatrix = new GridMatrix();
                if (this.useGS1Check.isSelected()) {
                    gridMatrix.setDataType(Symbol.DataType.GS1);
                }
                gridMatrix.setReaderInit(isSelected);
                if (this.gridmatrixUserEcc.isSelected()) {
                    gridMatrix.setPreferredEccLevel(this.gridmatrixUserEccCombo.getSelectedIndex() + 1);
                }
                if (this.gridmatrixUserSize.isSelected()) {
                    gridMatrix.setPreferredVersion(this.gridmatrixUserSizeCombo.getSelectedIndex() + 1);
                }
                gridMatrix.setContent(dataInput);
                setUniversals(gridMatrix);
                return gridMatrix;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                MaxiCode maxiCode = new MaxiCode();
                maxiCode.setPrimary(this.maxiPrimaryData.getText());
                if (isSelected) {
                    maxiCode.setMode(6);
                } else {
                    maxiCode.setMode(this.maxiEncodingModeCombo.getSelectedIndex() + 2);
                }
                maxiCode.setContent(dataInput);
                setUniversals(maxiCode);
                return maxiCode;
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
            case 68:
                Symbol codablockF = new CodablockF();
                if (this.useGS1Check.isSelected()) {
                    codablockF.setDataType(Symbol.DataType.GS1);
                }
                if (this.selectedSymbol.symbology == SymbolType.Encoding.CODABLOCK_HIBC) {
                    codablockF.setDataType(Symbol.DataType.HIBC);
                }
                codablockF.setContent(dataInput);
                setUniversals(codablockF);
                return codablockF;
            default:
                throw new OkapiException("Symbology not recognised: " + this.selectedSymbol.guiLabel);
        }
    }

    private int eanCalculateVersion() {
        boolean z = true;
        int i = 0;
        for (int i2 = 0; i2 < dataInput.length(); i2++) {
            if (dataInput.charAt(i2) < '0' || dataInput.charAt(i2) > '9') {
                z = false;
            } else if (z) {
                i++;
            }
        }
        return i <= 7 ? 8 : 13;
    }

    private static void createNodes(DefaultMutableTreeNode defaultMutableTreeNode) {
        DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode("One-Dimensional");
        defaultMutableTreeNode.add(defaultMutableTreeNode2);
        defaultMutableTreeNode2.add(new DefaultMutableTreeNode(new SymbolType("Channel Code", SymbolType.Encoding.CHANNEL_CODE)));
        defaultMutableTreeNode2.add(new DefaultMutableTreeNode(new SymbolType("Codabar", SymbolType.Encoding.CODABAR)));
        defaultMutableTreeNode2.add(new DefaultMutableTreeNode(new SymbolType("Code 11", SymbolType.Encoding.CODE_11)));
        DefaultMutableTreeNode defaultMutableTreeNode3 = new DefaultMutableTreeNode("Code 2 of 5");
        defaultMutableTreeNode2.add(defaultMutableTreeNode3);
        defaultMutableTreeNode3.add(new DefaultMutableTreeNode(new SymbolType("Standard", SymbolType.Encoding.CODE25_MATRIX)));
        defaultMutableTreeNode3.add(new DefaultMutableTreeNode(new SymbolType("IATA", SymbolType.Encoding.CODE25_IATA)));
        defaultMutableTreeNode3.add(new DefaultMutableTreeNode(new SymbolType("Industrial", SymbolType.Encoding.CODE25_INDUSTRY)));
        defaultMutableTreeNode3.add(new DefaultMutableTreeNode(new SymbolType("Interleaved", SymbolType.Encoding.CODE25_INTERLEAVED)));
        defaultMutableTreeNode3.add(new DefaultMutableTreeNode(new SymbolType("Data Logic", SymbolType.Encoding.CODE25_DATALOGIC)));
        defaultMutableTreeNode3.add(new DefaultMutableTreeNode(new SymbolType("ITF-14", SymbolType.Encoding.ITF14)));
        DefaultMutableTreeNode defaultMutableTreeNode4 = new DefaultMutableTreeNode("Code 39");
        defaultMutableTreeNode2.add(defaultMutableTreeNode4);
        defaultMutableTreeNode4.add(new DefaultMutableTreeNode(new SymbolType("Standard", SymbolType.Encoding.CODE39)));
        defaultMutableTreeNode4.add(new DefaultMutableTreeNode(new SymbolType("Extended", SymbolType.Encoding.CODE39_EXTENDED)));
        defaultMutableTreeNode4.add(new DefaultMutableTreeNode(new SymbolType("Code 93", SymbolType.Encoding.CODE93)));
        defaultMutableTreeNode4.add(new DefaultMutableTreeNode(new SymbolType("LOGMARS", SymbolType.Encoding.DOD_LOGMARS)));
        DefaultMutableTreeNode defaultMutableTreeNode5 = new DefaultMutableTreeNode("Code 128");
        defaultMutableTreeNode2.add(defaultMutableTreeNode5);
        defaultMutableTreeNode5.add(new DefaultMutableTreeNode(new SymbolType("Code 128", SymbolType.Encoding.CODE_128)));
        defaultMutableTreeNode5.add(new DefaultMutableTreeNode(new SymbolType("NVE-18", SymbolType.Encoding.NVE18)));
        defaultMutableTreeNode2.add(new DefaultMutableTreeNode(new SymbolType("European Article Number", SymbolType.Encoding.EAN)));
        defaultMutableTreeNode2.add(new DefaultMutableTreeNode(new SymbolType("MSI Plessey", SymbolType.Encoding.MSI_PLESSEY)));
        DefaultMutableTreeNode defaultMutableTreeNode6 = new DefaultMutableTreeNode("Telepen");
        defaultMutableTreeNode2.add(defaultMutableTreeNode6);
        defaultMutableTreeNode6.add(new DefaultMutableTreeNode(new SymbolType("Alpha", SymbolType.Encoding.TELEPEN)));
        defaultMutableTreeNode6.add(new DefaultMutableTreeNode(new SymbolType("Numeric", SymbolType.Encoding.TELEPEN_NUMERIC)));
        DefaultMutableTreeNode defaultMutableTreeNode7 = new DefaultMutableTreeNode("Universal Product Code");
        defaultMutableTreeNode2.add(defaultMutableTreeNode7);
        defaultMutableTreeNode7.add(new DefaultMutableTreeNode(new SymbolType("Version A", SymbolType.Encoding.UPC_A)));
        defaultMutableTreeNode7.add(new DefaultMutableTreeNode(new SymbolType("Version E", SymbolType.Encoding.UPC_E)));
        DefaultMutableTreeNode defaultMutableTreeNode8 = new DefaultMutableTreeNode("Stacked");
        defaultMutableTreeNode.add(defaultMutableTreeNode8);
        defaultMutableTreeNode8.add(new DefaultMutableTreeNode(new SymbolType("Codablock-F", SymbolType.Encoding.CODABLOCK_F)));
        defaultMutableTreeNode8.add(new DefaultMutableTreeNode(new SymbolType("Code 16K", SymbolType.Encoding.CODE16K)));
        defaultMutableTreeNode8.add(new DefaultMutableTreeNode(new SymbolType("Code 49", SymbolType.Encoding.CODE49)));
        defaultMutableTreeNode8.add(new DefaultMutableTreeNode(new SymbolType("PDF417", SymbolType.Encoding.PDF417)));
        defaultMutableTreeNode8.add(new DefaultMutableTreeNode(new SymbolType("PDF417 Truncated", SymbolType.Encoding.PDF417_TRUNCATED)));
        defaultMutableTreeNode8.add(new DefaultMutableTreeNode(new SymbolType("Micro PDF417", SymbolType.Encoding.PDF417_MICRO)));
        DefaultMutableTreeNode defaultMutableTreeNode9 = new DefaultMutableTreeNode("Two-Dimensional");
        defaultMutableTreeNode.add(defaultMutableTreeNode9);
        defaultMutableTreeNode9.add(new DefaultMutableTreeNode(new SymbolType("Aztec Code", SymbolType.Encoding.AZTEC)));
        defaultMutableTreeNode9.add(new DefaultMutableTreeNode(new SymbolType("Aztec Runes", SymbolType.Encoding.AZTEC_RUNE)));
        defaultMutableTreeNode9.add(new DefaultMutableTreeNode(new SymbolType("Data Matrix", SymbolType.Encoding.DATAMATRIX)));
        defaultMutableTreeNode9.add(new DefaultMutableTreeNode(new SymbolType("Code One", SymbolType.Encoding.CODE_ONE)));
        defaultMutableTreeNode9.add(new DefaultMutableTreeNode(new SymbolType("Grid Matrix", SymbolType.Encoding.GRIDMATRIX)));
        defaultMutableTreeNode9.add(new DefaultMutableTreeNode(new SymbolType("Maxicode", SymbolType.Encoding.MAXICODE)));
        defaultMutableTreeNode9.add(new DefaultMutableTreeNode(new SymbolType("QR Code", SymbolType.Encoding.QR)));
        defaultMutableTreeNode9.add(new DefaultMutableTreeNode(new SymbolType("Micro QR Code", SymbolType.Encoding.QR_MICRO)));
        DefaultMutableTreeNode defaultMutableTreeNode10 = new DefaultMutableTreeNode("GS1 DataBar");
        defaultMutableTreeNode.add(defaultMutableTreeNode10);
        defaultMutableTreeNode10.add(new DefaultMutableTreeNode(new SymbolType("Omnidirectional", SymbolType.Encoding.DB14)));
        defaultMutableTreeNode10.add(new DefaultMutableTreeNode(new SymbolType("Stacked", SymbolType.Encoding.DB14_STACKED)));
        defaultMutableTreeNode10.add(new DefaultMutableTreeNode(new SymbolType("Stacked Omnidirectional", SymbolType.Encoding.DB14_STACKED_OMNIDIRECT)));
        defaultMutableTreeNode10.add(new DefaultMutableTreeNode(new SymbolType("Limited", SymbolType.Encoding.DB_LIMITED)));
        defaultMutableTreeNode10.add(new DefaultMutableTreeNode(new SymbolType("Expanded Omnidirectional", SymbolType.Encoding.DB_EXPANDED)));
        defaultMutableTreeNode10.add(new DefaultMutableTreeNode(new SymbolType("Expanded Stacked Omnidirectional", SymbolType.Encoding.DB_EXPANDED_STACKED)));
        DefaultMutableTreeNode defaultMutableTreeNode11 = new DefaultMutableTreeNode("Postal");
        defaultMutableTreeNode.add(defaultMutableTreeNode11);
        DefaultMutableTreeNode defaultMutableTreeNode12 = new DefaultMutableTreeNode("Australia Post");
        defaultMutableTreeNode11.add(defaultMutableTreeNode12);
        defaultMutableTreeNode12.add(new DefaultMutableTreeNode(new SymbolType("Customer", SymbolType.Encoding.AUSPOST)));
        defaultMutableTreeNode12.add(new DefaultMutableTreeNode(new SymbolType("Reply Paid", SymbolType.Encoding.AUSPOST_REPLY)));
        defaultMutableTreeNode12.add(new DefaultMutableTreeNode(new SymbolType("Routing", SymbolType.Encoding.AUSPOST_REROUTE)));
        defaultMutableTreeNode12.add(new DefaultMutableTreeNode(new SymbolType("Redirect", SymbolType.Encoding.AUSPOST_REDIRECT)));
        defaultMutableTreeNode11.add(new DefaultMutableTreeNode(new SymbolType("Brazilian CEPNet", SymbolType.Encoding.BRAZIL_CEPNET)));
        DefaultMutableTreeNode defaultMutableTreeNode13 = new DefaultMutableTreeNode("Deutsche Post");
        defaultMutableTreeNode11.add(defaultMutableTreeNode13);
        defaultMutableTreeNode13.add(new DefaultMutableTreeNode(new SymbolType("Leitcode", SymbolType.Encoding.DP_LEITCODE)));
        defaultMutableTreeNode13.add(new DefaultMutableTreeNode(new SymbolType("Identcode", SymbolType.Encoding.DP_IDENTCODE)));
        defaultMutableTreeNode11.add(new DefaultMutableTreeNode(new SymbolType("Dutch Post KIX", SymbolType.Encoding.KIX_CODE)));
        defaultMutableTreeNode11.add(new DefaultMutableTreeNode(new SymbolType("Japan Post", SymbolType.Encoding.JAPAN_POST)));
        defaultMutableTreeNode11.add(new DefaultMutableTreeNode(new SymbolType("Korea Post", SymbolType.Encoding.KOREA_POST)));
        defaultMutableTreeNode11.add(new DefaultMutableTreeNode(new SymbolType("Royal Mail", SymbolType.Encoding.RM4SCC)));
        DefaultMutableTreeNode defaultMutableTreeNode14 = new DefaultMutableTreeNode("USPS");
        defaultMutableTreeNode11.add(defaultMutableTreeNode14);
        defaultMutableTreeNode14.add(new DefaultMutableTreeNode(new SymbolType("Intelligent Mail", SymbolType.Encoding.USPS_IMAIL)));
        defaultMutableTreeNode14.add(new DefaultMutableTreeNode(new SymbolType("IM Package Barcode", SymbolType.Encoding.USPS_IMPB)));
        defaultMutableTreeNode14.add(new DefaultMutableTreeNode(new SymbolType("PostNet", SymbolType.Encoding.USPS_POSTNET)));
        defaultMutableTreeNode14.add(new DefaultMutableTreeNode(new SymbolType("PLANET", SymbolType.Encoding.USPS_PLANET)));
        DefaultMutableTreeNode defaultMutableTreeNode15 = new DefaultMutableTreeNode("Medical");
        defaultMutableTreeNode.add(defaultMutableTreeNode15);
        defaultMutableTreeNode15.add(new DefaultMutableTreeNode(new SymbolType("Code 32", SymbolType.Encoding.CODE_32)));
        DefaultMutableTreeNode defaultMutableTreeNode16 = new DefaultMutableTreeNode("HIBC");
        defaultMutableTreeNode15.add(defaultMutableTreeNode16);
        defaultMutableTreeNode16.add(new DefaultMutableTreeNode(new SymbolType("Aztec Code", SymbolType.Encoding.AZTEC_HIBC)));
        defaultMutableTreeNode16.add(new DefaultMutableTreeNode(new SymbolType("Codablock-F", SymbolType.Encoding.CODABLOCK_HIBC)));
        defaultMutableTreeNode16.add(new DefaultMutableTreeNode(new SymbolType("Code 39", SymbolType.Encoding.CODE39_HIBC)));
        defaultMutableTreeNode16.add(new DefaultMutableTreeNode(new SymbolType("Code 128", SymbolType.Encoding.CODE_128_HIBC)));
        defaultMutableTreeNode16.add(new DefaultMutableTreeNode(new SymbolType("Data Matrix", SymbolType.Encoding.DATAMATRIX_HIBC)));
        defaultMutableTreeNode16.add(new DefaultMutableTreeNode(new SymbolType("PDF417", SymbolType.Encoding.PDF417_HIBC)));
        defaultMutableTreeNode16.add(new DefaultMutableTreeNode(new SymbolType("Micro PDF417", SymbolType.Encoding.PDF417_MICRO_HIBC)));
        defaultMutableTreeNode16.add(new DefaultMutableTreeNode(new SymbolType("QR Code", SymbolType.Encoding.QR_HIBC)));
        DefaultMutableTreeNode defaultMutableTreeNode17 = new DefaultMutableTreeNode("Pharmacode");
        defaultMutableTreeNode15.add(defaultMutableTreeNode17);
        defaultMutableTreeNode17.add(new DefaultMutableTreeNode(new SymbolType("One Track", SymbolType.Encoding.PHARMA)));
        defaultMutableTreeNode17.add(new DefaultMutableTreeNode(new SymbolType("Two Track", SymbolType.Encoding.PHARMA_TWOTRACK)));
        defaultMutableTreeNode15.add(new DefaultMutableTreeNode(new SymbolType("PZN8", SymbolType.Encoding.PZN)));
    }
}
