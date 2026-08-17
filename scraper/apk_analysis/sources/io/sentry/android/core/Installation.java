package io.sentry.android.core;

import android.content.Context;
import io.sentry.ISentryLifecycleToken;
import io.sentry.SentryUUID;
import io.sentry.util.AutoClosableReentrantLock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
final class Installation {
    static final String INSTALLATION = "INSTALLATION";
    static String deviceId;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    protected static final AutoClosableReentrantLock staticLock = new AutoClosableReentrantLock();

    private Installation() {
    }

    public static String id(Context context) throws RuntimeException {
        ISentryLifecycleToken acquire = staticLock.acquire();
        try {
            if (deviceId == null) {
                File file = new File(context.getFilesDir(), INSTALLATION);
                try {
                    if (!file.exists()) {
                        String writeInstallationFile = writeInstallationFile(file);
                        deviceId = writeInstallationFile;
                        if (acquire != null) {
                            acquire.close();
                        }
                        return writeInstallationFile;
                    }
                    deviceId = readInstallationFile(file);
                } catch (Throwable th) {
                    throw new RuntimeException(th);
                }
            }
            String str = deviceId;
            if (acquire != null) {
                acquire.close();
            }
            return str;
        } catch (Throwable th2) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
            }
            throw th2;
        }
    }

    static String readInstallationFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            byte[] bArr = new byte[(int) randomAccessFile.length()];
            randomAccessFile.readFully(bArr);
            String str = new String(bArr, UTF_8);
            randomAccessFile.close();
            return str;
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    static String writeInstallationFile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            String generateSentryId = SentryUUID.generateSentryId();
            fileOutputStream.write(generateSentryId.getBytes(UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
            return generateSentryId;
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
