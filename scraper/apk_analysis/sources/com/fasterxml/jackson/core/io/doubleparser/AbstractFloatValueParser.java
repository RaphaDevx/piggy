package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes3.dex */
abstract class AbstractFloatValueParser {
    static final byte[] CHAR_TO_HEX_MAP = new byte[128];
    static final byte DECIMAL_POINT_CLASS = -4;
    static final int MAX_EXPONENT_NUMBER = 1024;
    static final long MINIMAL_NINETEEN_DIGIT_INTEGER = 1000000000000000000L;
    static final byte OTHER_CLASS = -1;
    static final long PARSE_ERROR = -1;

    AbstractFloatValueParser() {
    }

    static {
        char c = 0;
        while (true) {
            byte[] bArr = CHAR_TO_HEX_MAP;
            if (c >= bArr.length) {
                break;
            }
            bArr[c] = -1;
            c = (char) (c + 1);
        }
        for (char c2 = '0'; c2 <= '9'; c2 = (char) (c2 + 1)) {
            CHAR_TO_HEX_MAP[c2] = (byte) (c2 - '0');
        }
        for (char c3 = 'A'; c3 <= 'F'; c3 = (char) (c3 + 1)) {
            CHAR_TO_HEX_MAP[c3] = (byte) (c3 - '7');
        }
        for (char c4 = 'a'; c4 <= 'f'; c4 = (char) (c4 + 1)) {
            CHAR_TO_HEX_MAP[c4] = (byte) (c4 - 'W');
        }
        for (char c5 = '.'; c5 <= '.'; c5 = (char) (c5 + 1)) {
            CHAR_TO_HEX_MAP[c5] = DECIMAL_POINT_CLASS;
        }
    }
}
