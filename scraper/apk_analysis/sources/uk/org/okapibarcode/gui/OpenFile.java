package uk.org.okapibarcode.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public class OpenFile {
    public static String ReadFile(File file, boolean z) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            String str = "";
            if (file.isFile() && file.canRead()) {
                int i = 0;
                if (z) {
                    while (i < file.length()) {
                        str = str + ((char) fileInputStream.read());
                        i++;
                    }
                } else if (file.length() < 3000) {
                    while (i < file.length()) {
                        str = str + ((char) fileInputStream.read());
                        i++;
                    }
                } else {
                    System.out.println("Input file too big");
                }
            } else {
                System.out.println("I/O Error");
            }
            fileInputStream.close();
            return str;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
