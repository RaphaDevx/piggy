package uk.org.okapibarcode;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.material.MenuKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.coop.auth.AuthBrowserView;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import kotlin.io.encoding.Base64;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import net.openid.appauth.CodeVerifierUtil;
import okhttp3.internal.ws.WebSocketProtocol;
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
import uk.org.okapibarcode.graphics.Color;
import uk.org.okapibarcode.output.Java2DRenderer;
import uk.org.okapibarcode.output.PostScriptRenderer;
import uk.org.okapibarcode.output.SvgRenderer;

/* loaded from: classes4.dex */
public class MakeBarcode {
    public void process(Settings settings, String str, String str2) {
        Symbol symbol;
        String str3;
        char c;
        int symbolType = settings.getSymbolType();
        HumanReadableLocation hrtPosition = settings.getHrtPosition();
        Color foregroundColour = settings.getForegroundColour();
        Color backgroundColour = settings.getBackgroundColour();
        if (settings.isReverseColour()) {
            foregroundColour = Color.WHITE;
            backgroundColour = Color.BLACK;
        }
        Color color = foregroundColour;
        Color color2 = backgroundColour;
        try {
            switch (symbolType) {
                case 1:
                    Symbol code11 = new Code11();
                    code11.setHumanReadableLocation(hrtPosition);
                    code11.setContent(str);
                    symbol = code11;
                    break;
                case 2:
                    Code2Of5 code2Of5 = new Code2Of5();
                    code2Of5.setMode(Code2Of5.ToFMode.MATRIX);
                    code2Of5.setHumanReadableLocation(hrtPosition);
                    code2Of5.setContent(str);
                    symbol = code2Of5;
                    break;
                case 3:
                    Code2Of5 code2Of52 = new Code2Of5();
                    code2Of52.setMode(Code2Of5.ToFMode.INTERLEAVED);
                    code2Of52.setHumanReadableLocation(hrtPosition);
                    code2Of52.setContent(str);
                    symbol = code2Of52;
                    break;
                case 4:
                    Code2Of5 code2Of53 = new Code2Of5();
                    code2Of53.setMode(Code2Of5.ToFMode.IATA);
                    code2Of53.setHumanReadableLocation(hrtPosition);
                    code2Of53.setContent(str);
                    symbol = code2Of53;
                    break;
                case 5:
                case 17:
                case 19:
                case 26:
                case 27:
                case 28:
                case 33:
                case 46:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                case 62:
                case 69:
                case 72:
                case 73:
                case 78:
                case ModuleDescriptor.MODULE_VERSION /* 86 */:
                case 88:
                case 91:
                case 94:
                case 95:
                case CodeVerifierUtil.MAX_CODE_VERIFIER_ENTROPY /* 96 */:
                case 114:
                case 115:
                case 116:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case WebSocketProtocol.PAYLOAD_SHORT /* 126 */:
                case 127:
                case 129:
                default:
                    System.out.println("Invaid barcode type");
                    return;
                case 6:
                    Code2Of5 code2Of54 = new Code2Of5();
                    code2Of54.setMode(Code2Of5.ToFMode.DATA_LOGIC);
                    code2Of54.setHumanReadableLocation(hrtPosition);
                    code2Of54.setContent(str);
                    symbol = code2Of54;
                    break;
                case 7:
                    Code2Of5 code2Of55 = new Code2Of5();
                    code2Of55.setMode(Code2Of5.ToFMode.INDUSTRIAL);
                    code2Of55.setHumanReadableLocation(hrtPosition);
                    code2Of55.setContent(str);
                    symbol = code2Of55;
                    break;
                case 8:
                case 99:
                case 101:
                    Symbol code3Of9 = new Code3Of9();
                    if (symbolType == 99 || symbolType == 101) {
                        code3Of9.setDataType(Symbol.DataType.HIBC);
                    }
                    code3Of9.setHumanReadableLocation(hrtPosition);
                    code3Of9.setContent(str);
                    symbol = code3Of9;
                    break;
                case 9:
                    Symbol code3Of9Extended = new Code3Of9Extended();
                    code3Of9Extended.setHumanReadableLocation(hrtPosition);
                    code3Of9Extended.setContent(str);
                    symbol = code3Of9Extended;
                    break;
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    Ean ean = new Ean();
                    if (eanCalculateVersion(str) == 8) {
                        ean.setMode(Ean.Mode.EAN8);
                    } else {
                        ean.setMode(Ean.Mode.EAN13);
                    }
                    ean.setContent(str);
                    symbol = ean;
                    break;
                case 16:
                case 20:
                case 59:
                case 60:
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 98:
                case 100:
                    Code128 code128 = new Code128();
                    code128.unsetCc();
                    if (settings.isDataGs1Mode() || symbolType == 16) {
                        code128.setDataType(Symbol.DataType.GS1);
                    }
                    if (symbolType == 98 || symbolType == 100) {
                        code128.setDataType(Symbol.DataType.HIBC);
                    }
                    if (symbolType == 60) {
                        code128.setSuppressModeC(true);
                    }
                    code128.setReaderInit(settings.isReaderInit());
                    code128.setHumanReadableLocation(hrtPosition);
                    code128.setContent(str);
                    symbol = code128;
                    break;
                case 18:
                    Symbol codabar = new Codabar();
                    codabar.setHumanReadableLocation(hrtPosition);
                    codabar.setContent(str);
                    symbol = codabar;
                    break;
                case 21:
                    Code2Of5 code2Of56 = new Code2Of5();
                    code2Of56.setMode(Code2Of5.ToFMode.DP_LEITCODE);
                    code2Of56.setHumanReadableLocation(hrtPosition);
                    code2Of56.setContent(str);
                    symbol = code2Of56;
                    break;
                case 22:
                    Code2Of5 code2Of57 = new Code2Of5();
                    code2Of57.setMode(Code2Of5.ToFMode.DP_IDENTCODE);
                    code2Of57.setHumanReadableLocation(hrtPosition);
                    code2Of57.setContent(str);
                    symbol = code2Of57;
                    break;
                case 23:
                    Symbol code16k = new Code16k();
                    if (settings.isDataGs1Mode()) {
                        code16k.setDataType(Symbol.DataType.GS1);
                    }
                    code16k.setReaderInit(settings.isReaderInit());
                    code16k.setContent(str);
                    symbol = code16k;
                    break;
                case 24:
                    Symbol code49 = new Code49();
                    code49.setHumanReadableLocation(hrtPosition);
                    code49.setContent(str);
                    symbol = code49;
                    break;
                case 25:
                    Symbol code93 = new Code93();
                    code93.setHumanReadableLocation(hrtPosition);
                    code93.setContent(str);
                    symbol = code93;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    DataBar14 dataBar14 = new DataBar14();
                    dataBar14.setMode(DataBar14.Mode.LINEAR);
                    dataBar14.setHumanReadableLocation(hrtPosition);
                    dataBar14.setContent(str);
                    symbol = dataBar14;
                    break;
                case 30:
                    Symbol dataBarLimited = new DataBarLimited();
                    dataBarLimited.setHumanReadableLocation(hrtPosition);
                    dataBarLimited.setContent(str);
                    symbol = dataBarLimited;
                    break;
                case 31:
                    DataBarExpanded dataBarExpanded = new DataBarExpanded();
                    dataBarExpanded.setStacked(false);
                    dataBarExpanded.setHumanReadableLocation(hrtPosition);
                    dataBarExpanded.setContent(str);
                    symbol = dataBarExpanded;
                    break;
                case 32:
                    Telepen telepen = new Telepen();
                    telepen.setMode(Telepen.Mode.NORMAL);
                    telepen.setHumanReadableLocation(hrtPosition);
                    telepen.setContent(str);
                    symbol = telepen;
                    break;
                case 34:
                case 35:
                case 36:
                    Upc upc = new Upc();
                    upc.setMode(Upc.Mode.UPCA);
                    upc.setContent(str);
                    symbol = upc;
                    break;
                case 37:
                case 38:
                case 39:
                    Upc upc2 = new Upc();
                    upc2.setMode(Upc.Mode.UPCE);
                    upc2.setContent(str);
                    symbol = upc2;
                    break;
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    Postnet postnet = new Postnet();
                    postnet.setMode(Postnet.Mode.POSTNET);
                    postnet.setContent(str);
                    symbol = postnet;
                    break;
                case 47:
                    Symbol msiPlessey = new MsiPlessey();
                    msiPlessey.setHumanReadableLocation(hrtPosition);
                    msiPlessey.setContent(str);
                    symbol = msiPlessey;
                    break;
                case 50:
                    Symbol logmars = new Logmars();
                    logmars.setHumanReadableLocation(hrtPosition);
                    logmars.setContent(str);
                    symbol = logmars;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    Symbol pharmacode = new Pharmacode();
                    pharmacode.setContent(str);
                    symbol = pharmacode;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case 113:
                    Symbol pharmazentralnummer = new Pharmazentralnummer();
                    pharmazentralnummer.setContent(str);
                    symbol = pharmazentralnummer;
                    break;
                case 53:
                    Symbol pharmacode2Track = new Pharmacode2Track();
                    pharmacode2Track.setContent(str);
                    symbol = pharmacode2Track;
                    break;
                case 55:
                case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                case b.l /* 106 */:
                case b.m /* 107 */:
                    Pdf417 pdf417 = new Pdf417();
                    if (settings.isDataGs1Mode()) {
                        pdf417.setDataType(Symbol.DataType.GS1);
                    }
                    if (symbolType != 106 && symbolType != 107) {
                        if (symbolType == 56) {
                            pdf417.setMode(Pdf417.Mode.TRUNCATED);
                        }
                        pdf417.setPreferredEccLevel(settings.getSymbolECC() - 1);
                        pdf417.setDataColumns(settings.getSymbolColumns());
                        pdf417.setReaderInit(settings.isReaderInit());
                        pdf417.setContent(str);
                        symbol = pdf417;
                        break;
                    }
                    pdf417.setDataType(Symbol.DataType.HIBC);
                    pdf417.setPreferredEccLevel(settings.getSymbolECC() - 1);
                    pdf417.setDataColumns(settings.getSymbolColumns());
                    pdf417.setReaderInit(settings.isReaderInit());
                    pdf417.setContent(str);
                    symbol = pdf417;
                    break;
                case 57:
                    MaxiCode maxiCode = new MaxiCode();
                    maxiCode.setPrimary(settings.getPrimaryData());
                    maxiCode.setMode(settings.getEncodeMode());
                    maxiCode.setContent(str);
                    symbol = maxiCode;
                    break;
                case 58:
                case 104:
                case 105:
                    QrCode qrCode = new QrCode();
                    if (settings.isDataGs1Mode()) {
                        qrCode.setDataType(Symbol.DataType.GS1);
                    }
                    if (symbolType == 104 || symbolType == 105) {
                        qrCode.setDataType(Symbol.DataType.HIBC);
                    }
                    int symbolECC = settings.getSymbolECC();
                    if (symbolECC == 0) {
                        qrCode.setPreferredEccLevel(QrCode.EccLevel.L);
                    } else if (symbolECC == 1) {
                        qrCode.setPreferredEccLevel(QrCode.EccLevel.M);
                    } else if (symbolECC == 2) {
                        qrCode.setPreferredEccLevel(QrCode.EccLevel.Q);
                    } else if (symbolECC == 3) {
                        qrCode.setPreferredEccLevel(QrCode.EccLevel.H);
                    }
                    qrCode.setPreferredVersion(settings.getSymbolVersion());
                    qrCode.setReaderInit(settings.isReaderInit());
                    qrCode.setContent(str);
                    symbol = qrCode;
                    break;
                case 63:
                case 64:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    AustraliaPost australiaPost = new AustraliaPost();
                    australiaPost.setMode(AustraliaPost.Mode.POST);
                    australiaPost.setContent(str);
                    symbol = australiaPost;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    AustraliaPost australiaPost2 = new AustraliaPost();
                    australiaPost2.setMode(AustraliaPost.Mode.REPLY);
                    australiaPost2.setContent(str);
                    symbol = australiaPost2;
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    AustraliaPost australiaPost3 = new AustraliaPost();
                    australiaPost3.setMode(AustraliaPost.Mode.ROUTE);
                    australiaPost3.setContent(str);
                    symbol = australiaPost3;
                    break;
                case 68:
                    AustraliaPost australiaPost4 = new AustraliaPost();
                    australiaPost4.setMode(AustraliaPost.Mode.REDIRECT);
                    australiaPost4.setContent(str);
                    symbol = australiaPost4;
                    break;
                case 70:
                    Symbol royalMail4State = new RoyalMail4State();
                    royalMail4State.setContent(str);
                    symbol = royalMail4State;
                    break;
                case 71:
                case 102:
                case b.i /* 103 */:
                    DataMatrix dataMatrix = new DataMatrix();
                    if (settings.isDataGs1Mode()) {
                        dataMatrix.setDataType(Symbol.DataType.GS1);
                    }
                    if (symbolType == 102 || symbolType == 103) {
                        dataMatrix.setDataType(Symbol.DataType.HIBC);
                    }
                    dataMatrix.setReaderInit(settings.isReaderInit());
                    dataMatrix.setPreferredSize(settings.getSymbolVersion());
                    dataMatrix.setForceMode(settings.isMakeSquare() ? DataMatrix.ForceMode.SQUARE : DataMatrix.ForceMode.NONE);
                    dataMatrix.setContent(str);
                    symbol = dataMatrix;
                    break;
                case 74:
                case b.n /* 110 */:
                case b.o /* 111 */:
                    Symbol codablockF = new CodablockF();
                    if (settings.isDataGs1Mode()) {
                        codablockF.setDataType(Symbol.DataType.GS1);
                    }
                    if (symbolType == 110 || symbolType == 111) {
                        codablockF.setDataType(Symbol.DataType.HIBC);
                    }
                    codablockF.setContent(str);
                    symbol = codablockF;
                    break;
                case MenuKt.OutTransitionDuration /* 75 */:
                    Symbol nve18 = new Nve18();
                    nve18.setHumanReadableLocation(hrtPosition);
                    nve18.setContent(str);
                    symbol = nve18;
                    break;
                case Base64.mimeLineLength /* 76 */:
                    Symbol japanPost = new JapanPost();
                    japanPost.setContent(str);
                    symbol = japanPost;
                    break;
                case 77:
                    Symbol koreaPost = new KoreaPost();
                    koreaPost.setHumanReadableLocation(hrtPosition);
                    koreaPost.setContent(str);
                    symbol = koreaPost;
                    break;
                case 79:
                    DataBar14 dataBar142 = new DataBar14();
                    dataBar142.setMode(DataBar14.Mode.STACKED);
                    dataBar142.setContent(str);
                    symbol = dataBar142;
                    break;
                case BinsView.LABEL_WIDTH_DP /* 80 */:
                    DataBar14 dataBar143 = new DataBar14();
                    dataBar143.setMode(DataBar14.Mode.OMNI);
                    dataBar143.setContent(str);
                    symbol = dataBar143;
                    break;
                case 81:
                    DataBarExpanded dataBarExpanded2 = new DataBarExpanded();
                    dataBarExpanded2.setPreferredColumns(settings.getSymbolColumns());
                    dataBarExpanded2.setStacked(true);
                    dataBarExpanded2.setContent(str);
                    symbol = dataBarExpanded2;
                    break;
                case 82:
                case 83:
                    Postnet postnet2 = new Postnet();
                    postnet2.setMode(Postnet.Mode.PLANET);
                    postnet2.setContent(str);
                    symbol = postnet2;
                    break;
                case 84:
                case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR /* 108 */:
                case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY /* 109 */:
                    Pdf417 pdf4172 = new Pdf417();
                    pdf4172.setMode(Pdf417.Mode.MICRO);
                    if (settings.isDataGs1Mode()) {
                        pdf4172.setDataType(Symbol.DataType.GS1);
                    }
                    if (symbolType == 108 || symbolType == 109) {
                        pdf4172.setDataType(Symbol.DataType.HIBC);
                    }
                    pdf4172.setReaderInit(settings.isReaderInit());
                    pdf4172.setDataColumns(settings.getSymbolColumns());
                    pdf4172.setContent(str);
                    symbol = pdf4172;
                    break;
                case 85:
                    Symbol uspsOneCode = new UspsOneCode();
                    uspsOneCode.setContent(str);
                    symbol = uspsOneCode;
                    break;
                case 87:
                    Telepen telepen2 = new Telepen();
                    telepen2.setMode(Telepen.Mode.NUMERIC);
                    telepen2.setHumanReadableLocation(hrtPosition);
                    telepen2.setContent(str);
                    symbol = telepen2;
                    break;
                case 89:
                    Code2Of5 code2Of58 = new Code2Of5();
                    code2Of58.setMode(Code2Of5.ToFMode.ITF14);
                    code2Of58.setHumanReadableLocation(hrtPosition);
                    code2Of58.setContent(str);
                    symbol = code2Of58;
                    break;
                case 90:
                    Symbol kixCode = new KixCode();
                    kixCode.setContent(str);
                    symbol = kixCode;
                    break;
                case 92:
                case b.p /* 112 */:
                    AztecCode aztecCode = new AztecCode();
                    if (settings.isDataGs1Mode()) {
                        aztecCode.setDataType(Symbol.DataType.GS1);
                    }
                    if (symbolType == 112) {
                        aztecCode.setDataType(Symbol.DataType.HIBC);
                    }
                    aztecCode.setReaderInit(settings.isReaderInit());
                    aztecCode.setPreferredEccLevel(settings.getSymbolECC());
                    aztecCode.setPreferredSize(settings.getSymbolVersion());
                    aztecCode.setContent(str);
                    symbol = aztecCode;
                    break;
                case 93:
                    Symbol code32 = new Code32();
                    code32.setHumanReadableLocation(hrtPosition);
                    code32.setContent(str);
                    symbol = code32;
                    break;
                case 97:
                    MicroQrCode microQrCode = new MicroQrCode();
                    int symbolECC2 = settings.getSymbolECC();
                    if (symbolECC2 == 0) {
                        microQrCode.setEccMode(MicroQrCode.EccMode.L);
                    } else if (symbolECC2 == 1) {
                        microQrCode.setEccMode(MicroQrCode.EccMode.M);
                    } else if (symbolECC2 == 2) {
                        microQrCode.setEccMode(MicroQrCode.EccMode.Q);
                    } else if (symbolECC2 == 3) {
                        microQrCode.setEccMode(MicroQrCode.EccMode.H);
                    }
                    microQrCode.setPreferredVersion(settings.getSymbolVersion());
                    microQrCode.setContent(str);
                    symbol = microQrCode;
                    break;
                case 117:
                    Symbol uspsPackage = new UspsPackage();
                    uspsPackage.setContent(str);
                    symbol = uspsPackage;
                    break;
                case 128:
                    Symbol aztecRune = new AztecRune();
                    aztecRune.setContent(str);
                    symbol = aztecRune;
                    break;
                case 130:
                    Composite composite = new Composite();
                    composite.setSymbology(Composite.LinearEncoding.EAN);
                    composite.setLinearContent(settings.getPrimaryData());
                    composite.setContent(str);
                    symbol = composite;
                    break;
                case 131:
                    Composite composite2 = new Composite();
                    composite2.setSymbology(Composite.LinearEncoding.CODE_128);
                    composite2.setLinearContent(settings.getPrimaryData());
                    composite2.setContent(str);
                    symbol = composite2;
                    break;
                case 132:
                    Composite composite3 = new Composite();
                    composite3.setSymbology(Composite.LinearEncoding.DATABAR_14);
                    composite3.setLinearContent(settings.getPrimaryData());
                    composite3.setContent(str);
                    symbol = composite3;
                    break;
                case 133:
                    Composite composite4 = new Composite();
                    composite4.setSymbology(Composite.LinearEncoding.DATABAR_LIMITED);
                    composite4.setLinearContent(settings.getPrimaryData());
                    composite4.setContent(str);
                    symbol = composite4;
                    break;
                case 134:
                    Composite composite5 = new Composite();
                    composite5.setSymbology(Composite.LinearEncoding.DATABAR_EXPANDED);
                    composite5.setLinearContent(settings.getPrimaryData());
                    composite5.setContent(str);
                    symbol = composite5;
                    break;
                case 135:
                    Composite composite6 = new Composite();
                    composite6.setSymbology(Composite.LinearEncoding.UPCA);
                    composite6.setLinearContent(settings.getPrimaryData());
                    composite6.setContent(str);
                    symbol = composite6;
                    break;
                case 136:
                    Composite composite7 = new Composite();
                    composite7.setSymbology(Composite.LinearEncoding.UPCE);
                    composite7.setLinearContent(settings.getPrimaryData());
                    composite7.setContent(str);
                    symbol = composite7;
                    break;
                case 137:
                    Composite composite8 = new Composite();
                    composite8.setSymbology(Composite.LinearEncoding.DATABAR_14_STACK);
                    composite8.setLinearContent(settings.getPrimaryData());
                    composite8.setContent(str);
                    symbol = composite8;
                    break;
                case 138:
                    Composite composite9 = new Composite();
                    composite9.setSymbology(Composite.LinearEncoding.DATABAR_14_STACK_OMNI);
                    composite9.setLinearContent(settings.getPrimaryData());
                    composite9.setContent(str);
                    symbol = composite9;
                    break;
                case 139:
                    Composite composite10 = new Composite();
                    composite10.setSymbology(Composite.LinearEncoding.DATABAR_EXPANDED_STACK);
                    composite10.setLinearContent(settings.getPrimaryData());
                    composite10.setContent(str);
                    symbol = composite10;
                    break;
                case 140:
                    ChannelCode channelCode = new ChannelCode();
                    channelCode.setPreferredNumberOfChannels(settings.getSymbolColumns());
                    channelCode.setHumanReadableLocation(hrtPosition);
                    channelCode.setContent(str);
                    symbol = channelCode;
                    break;
                case 141:
                    CodeOne codeOne = new CodeOne();
                    if (settings.isDataGs1Mode()) {
                        codeOne.setDataType(Symbol.DataType.GS1);
                    }
                    codeOne.setReaderInit(settings.isReaderInit());
                    switch (settings.getSymbolVersion()) {
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
                    codeOne.setContent(str);
                    symbol = codeOne;
                    break;
                case 142:
                    GridMatrix gridMatrix = new GridMatrix();
                    if (settings.isDataGs1Mode()) {
                        gridMatrix.setDataType(Symbol.DataType.GS1);
                    }
                    gridMatrix.setReaderInit(settings.isReaderInit());
                    gridMatrix.setPreferredEccLevel(settings.getSymbolECC());
                    gridMatrix.setPreferredVersion(settings.getSymbolVersion());
                    gridMatrix.setContent(str);
                    symbol = gridMatrix;
                    break;
            }
            File file = new File(str2);
            try {
                try {
                    int lastIndexOf = file.getName().lastIndexOf(46);
                    if (lastIndexOf <= 0) {
                        str3 = "";
                    } else {
                        str3 = file.getName().substring(lastIndexOf + 1);
                    }
                    switch (str3.hashCode()) {
                        case 97669:
                            if (str3.equals("bmp")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 100648:
                            if (str3.equals("eps")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case 102340:
                            if (str3.equals("gif")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 105441:
                            if (str3.equals("jpg")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 111145:
                            if (str3.equals("png")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 114276:
                            if (str3.equals("svg")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    if (c != 0 && c != 1 && c != 2 && c != 3) {
                        if (c == 4) {
                            new SvgRenderer(new FileOutputStream(file), 1.0d, color2, color, true).render(symbol);
                            return;
                        } else if (c == 5) {
                            new PostScriptRenderer(new FileOutputStream(file), 1.0d, color2, color).render(symbol);
                            return;
                        } else {
                            System.out.println("Unsupported output format");
                            return;
                        }
                    }
                    BufferedImage bufferedImage = new BufferedImage(symbol.getWidth(), symbol.getHeight(), 1);
                    Graphics2D createGraphics = bufferedImage.createGraphics();
                    createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    Java2DRenderer java2DRenderer = new Java2DRenderer(createGraphics, 1.0d, color2, color);
                    System.out.printf("MakeBarcode\n", new Object[0]);
                    java2DRenderer.render(symbol);
                    try {
                        ImageIO.write(bufferedImage, str3, file);
                    } catch (IOException unused) {
                        System.out.printf("Error outputting to file\n", new Object[0]);
                    }
                } catch (FileNotFoundException unused2) {
                    System.out.printf("File Not Found\n", new Object[0]);
                }
            } catch (IOException unused3) {
                System.out.printf("Write Error\n", new Object[0]);
            }
        } catch (OkapiException e) {
            System.out.printf("Encoding error: %s\n", e.getMessage());
        }
    }

    private int eanCalculateVersion(String str) {
        boolean z = true;
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) < '0' || str.charAt(i2) > '9') {
                z = false;
            } else if (z) {
                i++;
            }
        }
        return i <= 7 ? 8 : 13;
    }
}
