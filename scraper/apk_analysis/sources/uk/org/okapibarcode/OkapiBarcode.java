package uk.org.okapibarcode;

import com.beust.jcommander.JCommander;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import uk.org.okapibarcode.gui.OkapiUI;

/* loaded from: classes4.dex */
public class OkapiBarcode {
    public static void main(String[] strArr) {
        Settings settings = new Settings();
        new JCommander(settings).parse(strArr);
        if (!settings.isGuiSupressed()) {
            new OkapiUI().setVisible(true);
        } else if (commandLine(settings) != 0) {
            System.out.println("An error occurred");
        }
    }

    private static int commandLine(Settings settings) {
        String inputData = settings.getInputData();
        String inputFile = settings.getInputFile();
        if (settings.isDisplayTypes()) {
            System.out.print(" 1: Code 11           54: Brazilian CepNet         97: Micro QR Code\n 2: Standard 2of5     55: PDF417                   98: HIBC Code 128\n 3: Interleaved 2of5  56: PDF417 Trunc             99: HIBC Code 39\n 4: IATA 2of5         57: Maxicode                102: HIBC Data Matrix\n 6: Data Logic        58: QR Code                 104: HIBC QR Code\n 7: Industrial 2of5   60: Code 128-B              106: HIBC PDF417\n 8: Code 39           63: AP Standard Customer    108: HIBC MicroPDF417\n 9: Extended Code 39  66: AP Reply Paid           110: HIBC Codablock-F\n13: EAN               67: AP Routing              112: HIBC Aztec Code\n18: Codabar           68: AP Redirection          113: PZN-8\n20: Code 128          70: RM4SCC                  117: USPS IMpb\n21: Leitcode          71: Data Matrix             128: Aztec Runes\n22: Identcode         74: Codablock-F             129: Code 32\n23: Code 16k          75: NVE-18                  130: Comp EAN\n24: Code 49           76: Japanese Post           131: Comp GS1-128\n25: Code 93           77: Korea Post              132: Comp Databar-14\n29: Databar-14        79: Databar-14 Stack        133: Comp Databar Ltd\n30: Databar Limited   80: Databar-14 Stack Omni   134: Comp Databar Ext\n31: Databar Extended  81: Databar Extended Stack  135: Comp UPC-A\n32: Telepen Alpha     82: Planet                  136: Comp UPC-E\n34: UPC-A             84: MicroPDF                137: Comp Databar-14 Stack\n37: UPC-E             85: USPS Intelligent Mail   138: Comp Databar Stack Omni\n40: Postnet           87: Telepen Numeric         139: Comp Databar Ext Stack\n47: MSI Plessey       89: ITF-14                  140: Channel Code\n50: Logmars           90: KIX Code                141: Code One \n51: Pharma One-Track  92: Aztec Code              142: Grid Matrix\n53: Pharma Two-Track  93: Code 32\n");
        }
        if (inputData.isEmpty() && inputFile.isEmpty()) {
            System.out.println("error: No data received, no symbol generated");
            return 0;
        }
        if (inputFile.isEmpty()) {
            if (!settings.isDataBinaryMode()) {
                inputData = escapeCharProcess(inputData);
            }
            new MakeBarcode().process(settings, inputData, settings.getOutputFile());
        } else {
            processFile(settings);
        }
        return 0;
    }

    private static void processFile(Settings settings) {
        File file = new File(settings.getInputFile());
        if (!settings.isBatchMode()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    new MakeBarcode().process(settings, new String(bArr, "UTF-8"), settings.getOutputFile());
                    fileInputStream.close();
                    return;
                } finally {
                }
            } catch (IOException unused) {
                System.out.println("File Read Error");
                return;
            }
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            int i = 0;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        i++;
                        new MakeBarcode().process(settings, readLine, calcFileName(settings, i));
                    } else {
                        bufferedReader.close();
                        return;
                    }
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
        } catch (UnsupportedEncodingException unused2) {
            System.out.println("Encoding exception");
        } catch (IOException unused3) {
            System.out.println("File Read Error");
        }
    }

    private static String calcFileName(Settings settings, int i) {
        String outputFile;
        String num = Integer.toString(i);
        if (settings.getOutputFile().equals("out.png")) {
            outputFile = "~~~~~.png";
        } else {
            outputFile = settings.getOutputFile();
        }
        int i2 = 0;
        for (int i3 = 0; i3 < outputFile.length(); i3++) {
            char charAt = outputFile.charAt(i3);
            if (charAt == '#' || charAt == '~') {
                i2++;
            }
        }
        int length = i2 - num.length();
        if (length < 0) {
            System.out.println("Invalid output filename");
            return "out.png";
        }
        String str = "";
        int i4 = 0;
        for (int i5 = 0; i5 < outputFile.length(); i5++) {
            char charAt2 = outputFile.charAt(i5);
            if (charAt2 != '#') {
                if (charAt2 != '~') {
                    str = str + outputFile.charAt(i5);
                } else if (i4 >= length) {
                    str = str + num.charAt(i4 - length);
                } else {
                    str = str + '0';
                }
            } else if (i4 >= length) {
                str = str + num.charAt(i4 - length);
            } else {
                str = str + ' ';
            }
            i4++;
        }
        return str;
    }

    private static String escapeCharProcess(String str) {
        String str2 = "";
        int i = 0;
        do {
            if (str.charAt(i) == '\\') {
                if (i < str.length() - 1) {
                    char charAt = str.charAt(i + 1);
                    if (charAt == '0') {
                        str2 = str2 + 0;
                    } else if (charAt == 'E') {
                        str2 = str2 + 4;
                    } else if (charAt == 'G') {
                        str2 = str2 + 29;
                    } else if (charAt == 'R') {
                        str2 = str2 + 30;
                    } else if (charAt == 'n') {
                        str2 = str2 + 10;
                    } else if (charAt == 'r') {
                        str2 = str2 + 13;
                    } else if (charAt == 't') {
                        str2 = str2 + 9;
                    } else if (charAt == 'v') {
                        str2 = str2 + 11;
                    } else if (charAt == 'a') {
                        str2 = str2 + 7;
                    } else if (charAt == 'b') {
                        str2 = str2 + 8;
                    } else if (charAt == 'e') {
                        str2 = str2 + 27;
                    } else if (charAt == 'f') {
                        str2 = str2 + 12;
                    } else {
                        str2 = (str2 + '\\') + str.charAt(i);
                    }
                    i += 2;
                } else {
                    str2 = str2 + '\\';
                }
            } else {
                str2 = str2 + str.charAt(i);
            }
            i++;
        } while (i < str.length());
        return str2;
    }
}
