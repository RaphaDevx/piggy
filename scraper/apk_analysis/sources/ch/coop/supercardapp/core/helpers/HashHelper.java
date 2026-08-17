package ch.coop.supercardapp.core.helpers;

import io.sentry.Sentry;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class HashHelper {
    public static byte[] sha256(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            Sentry.captureException(e);
            return null;
        }
    }

    public static String sha256Hex(String str) {
        byte[] sha256 = sha256(str);
        if (sha256 != null) {
            return bin2hex(sha256);
        }
        return null;
    }

    private static String bin2hex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(String.format("%02x", Integer.valueOf(b & UByte.MAX_VALUE)));
        }
        return sb.toString();
    }
}
