package lib.android.paypal.com.magnessdk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public class b {
    private static final int d = 1024;
    private boolean a = false;
    private boolean b = false;
    private File c;

    public b() {
        a();
        this.c = android.os.Environment.getExternalStorageDirectory();
    }

    private void a() {
        String externalStorageState = android.os.Environment.getExternalStorageState();
        externalStorageState.hashCode();
        if (externalStorageState.equals("mounted")) {
            this.b = true;
            this.a = true;
        } else if (externalStorageState.equals("mounted_ro")) {
            this.a = true;
            this.b = false;
        } else {
            this.b = false;
            this.a = false;
        }
    }

    public void a(String str, byte[] bArr) throws IOException {
        if (!this.a || !this.b) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            if (this.c.mkdirs() || this.c.isDirectory()) {
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(this.c, str));
                try {
                    fileOutputStream2.write(bArr);
                    fileOutputStream = fileOutputStream2;
                } catch (Throwable th) {
                    fileOutputStream = fileOutputStream2;
                    th = th;
                    o.a(getClass(), fileOutputStream);
                    throw th;
                }
            }
            o.a(getClass(), fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public boolean a(String str) {
        return new File(this.c, str).delete();
    }

    public String b(String str) throws IOException {
        FileInputStream fileInputStream;
        byte[] bArr = new byte[1024];
        String str2 = null;
        FileInputStream fileInputStream2 = null;
        if (this.b) {
            try {
                fileInputStream = new FileInputStream(new File(this.c, str));
            } catch (Throwable th) {
                th = th;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                for (int read = fileInputStream.read(bArr, 0, 1024); read != -1; read = fileInputStream.read(bArr, 0, 1024)) {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                o.a(getClass(), fileInputStream);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                o.a(getClass(), fileInputStream2);
                throw th;
            }
        }
        return str2;
    }

    public void c(String str) {
        this.c = new File(str);
    }
}
