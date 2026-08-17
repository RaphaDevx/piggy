package uk.org.okapibarcode.util;

import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import org.apache.commons.lang3.CharUtils;
import uk.org.okapibarcode.backend.OkapiException;

/* loaded from: classes4.dex */
public final class Strings {
    private static boolean isHex(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    private Strings() {
    }

    public static String escape(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 10);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == 0) {
                sb.append("\\0");
            } else if (charAt == 4) {
                sb.append("\\E");
            } else if (charAt == 27) {
                sb.append("\\e");
            } else if (charAt == '\\') {
                sb.append("\\\\");
            } else if (charAt == 29) {
                sb.append("\\G");
            } else if (charAt != 30) {
                switch (charAt) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case '\b':
                        sb.append("\\b");
                        break;
                    case '\t':
                        sb.append("\\t");
                        break;
                    case '\n':
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case '\f':
                        sb.append("\\f");
                        break;
                    case '\r':
                        sb.append("\\r");
                        break;
                    default:
                        if (charAt >= ' ' && charAt <= '~') {
                            sb.append(charAt);
                            break;
                        } else {
                            String format = String.format("%02X", Integer.valueOf(String.valueOf(charAt).getBytes(StandardCharsets.ISO_8859_1)[0] & UByte.MAX_VALUE));
                            sb.append("\\x");
                            sb.append(format);
                            break;
                        }
                        break;
                }
            } else {
                sb.append("\\R");
            }
        }
        return sb.toString();
    }

    public static String unescape(String str, boolean z) {
        int i;
        StringBuilder sb = new StringBuilder(str.length());
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt != '\\') {
                sb.append(charAt);
            } else {
                int i3 = i2 + 1;
                if (i3 >= str.length()) {
                    throw new OkapiException("Error processing escape sequences: expected escape character, found end of string");
                }
                char charAt2 = str.charAt(i3);
                if (charAt2 == '0') {
                    sb.append((char) 0);
                } else if (charAt2 == 'E') {
                    sb.append((char) 4);
                } else if (charAt2 == 'G') {
                    sb.append((char) 29);
                } else if (charAt2 == 'R') {
                    sb.append((char) 30);
                } else if (charAt2 == '\\') {
                    sb.append('\\');
                } else if (charAt2 == 'n') {
                    sb.append('\n');
                } else if (charAt2 != 'r') {
                    if (charAt2 == 'x') {
                        i = i2 + 3;
                        if (i >= str.length()) {
                            throw new OkapiException("Error processing escape sequences: expected hex sequence, found end of string");
                        }
                        char charAt3 = str.charAt(i2 + 2);
                        char charAt4 = str.charAt(i);
                        if (isHex(charAt3) && isHex(charAt4)) {
                            sb.append(new String(new byte[]{(byte) Integer.parseInt("" + charAt3 + charAt4, 16)}, StandardCharsets.ISO_8859_1));
                        } else {
                            throw new OkapiException("Error processing escape sequences: expected hex sequence, found '" + charAt3 + charAt4 + "'");
                        }
                    } else if (charAt2 == 'a') {
                        sb.append((char) 7);
                    } else if (charAt2 == 'b') {
                        sb.append('\b');
                    } else if (charAt2 == 'e') {
                        sb.append((char) 27);
                    } else if (charAt2 != 'f') {
                        switch (charAt2) {
                            case 't':
                                sb.append('\t');
                                break;
                            case 'u':
                                i = i2 + 5;
                                if (i >= str.length()) {
                                    throw new OkapiException("Error processing escape sequences: expected unicode hex sequence, found end of string");
                                }
                                char charAt5 = str.charAt(i2 + 2);
                                char charAt6 = str.charAt(i2 + 3);
                                char charAt7 = str.charAt(i2 + 4);
                                char charAt8 = str.charAt(i);
                                if (isHex(charAt5) && isHex(charAt6) && isHex(charAt7) && isHex(charAt8)) {
                                    sb.append(new String(new byte[]{(byte) Integer.parseInt("" + charAt5 + charAt6, 16), (byte) Integer.parseInt("" + charAt7 + charAt8, 16)}, StandardCharsets.UTF_16BE));
                                    break;
                                } else {
                                    throw new OkapiException("Error processing escape sequences: expected unicode hex sequence, found '" + charAt5 + charAt6 + charAt7 + charAt8 + "'");
                                }
                                break;
                            case 'v':
                                sb.append((char) 11);
                                break;
                            default:
                                if (z) {
                                    sb.append(charAt);
                                    break;
                                } else {
                                    throw new OkapiException("Error processing escape sequences: expected valid escape character, found '" + charAt2 + "'");
                                }
                        }
                    } else {
                        sb.append('\f');
                    }
                    i2 = i;
                } else {
                    sb.append(CharUtils.CR);
                }
                i2 = i3;
            }
            i2++;
        }
        return sb.toString();
    }

    public static void binaryAppend(StringBuilder sb, int i, int i2) {
        int i3 = 1 << (i2 - 1);
        for (int i4 = 0; i4 < i2; i4++) {
            if (((i3 >> i4) & i) == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }
        }
    }

    public static String toPrintableAscii(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= ' ' && charAt <= '~') {
                sb.append(charAt);
            } else {
                sb.append("\\u");
                sb.append(String.format("%04x", Integer.valueOf(charAt)));
            }
        }
        return sb.toString();
    }
}
