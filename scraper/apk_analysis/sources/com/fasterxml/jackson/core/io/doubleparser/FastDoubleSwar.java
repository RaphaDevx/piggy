package com.fasterxml.jackson.core.io.doubleparser;

import com.salesforce.marketingcloud.config.a;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes3.dex */
class FastDoubleSwar {
    public static int tryToParseEightDigitsUtf16(long j, long j2) {
        long j3 = j - 13511005043687472L;
        long j4 = j2 - 13511005043687472L;
        if ((((j + 19703549022044230L) | j3 | (j2 + 19703549022044230L) | j4) & (-35747867511423104L)) != 0) {
            return -1;
        }
        return ((int) ((j4 * 281475406208040961L) >>> 48)) + (((int) ((j3 * 281475406208040961L) >>> 48)) * a.n);
    }

    public static int tryToParseEightDigitsUtf8(long j) {
        long j2 = j - 3472328296227680304L;
        if ((((j + 5063812098665367110L) | j2) & (-9187201950435737472L)) != 0) {
            return -1;
        }
        long j3 = j2 * 2561;
        return (int) (((((j3 >>> 8) & 1095216660735L) * 4294967296000100L) + (((j3 >>> 24) & 1095216660735L) * 42949672960001L)) >>> 32);
    }

    public static long tryToParseEightHexDigitsUtf8(long j) {
        long j2 = j - 3472328296227680304L;
        long j3 = (j - (-5063812098665367110L)) & (-9187201950435737472L);
        if (j3 != ((j - (-2242545357980376863L)) & (-9187201950435737472L) & ((9187201950435737471L ^ j2) + 3978709506094217015L))) {
            return -1L;
        }
        long j4 = (j3 >>> 7) * 255;
        long j5 = ((~j4) & j2) | (j2 - (j4 & 2821266740684990247L));
        long j6 = (j5 | (j5 >>> 4)) & 71777214294589695L;
        long j7 = j6 | (j6 >>> 8);
        return (j7 & WebSocketProtocol.PAYLOAD_SHORT_MAX) | ((j7 >>> 16) & 4294901760L);
    }

    public static long tryToParseFourHexDigitsUtf16(long j) {
        long j2 = j - 13511005043687472L;
        long j3 = (j - (-9207186978729525190L)) & (-9223231297218904064L);
        if (j3 != ((j - (-9196209287131529119L)) & (-9223231297218904064L) & ((9223231297218904063L ^ j2) + 15481359945891895L))) {
            return -1L;
        }
        long j4 = (j3 >>> 15) * WebSocketProtocol.PAYLOAD_SHORT_MAX;
        long j5 = ((~j4) & j2) | (j2 - (j4 & 10977691597996071L));
        long j6 = j5 | (j5 >>> 12);
        return (j6 | (j6 >>> 24)) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    FastDoubleSwar() {
    }

    public static int tryToParseEightDigitsUtf16(char[] cArr, int i) {
        return tryToParseEightDigitsUtf16(cArr[i] | (cArr[i + 1] << 16) | (cArr[i + 2] << 32) | (cArr[i + 3] << 48), (cArr[i + 7] << 48) | cArr[i + 4] | (cArr[i + 5] << 16) | (cArr[i + 6] << 32));
    }

    public static int tryToParseEightDigitsUtf8(byte[] bArr, int i) {
        return tryToParseEightDigitsUtf8(readLongFromByteArrayLittleEndian(bArr, i));
    }

    public static long tryToParseEightHexDigitsUtf16(char[] cArr, int i) {
        return tryToParseEightHexDigitsUtf16((cArr[i] << 48) | (cArr[i + 1] << 32) | (cArr[i + 2] << 16) | cArr[i + 3], cArr[i + 7] | (cArr[i + 4] << 48) | (cArr[i + 5] << 32) | (cArr[i + 6] << 16));
    }

    public static long tryToParseEightHexDigitsUtf16(long j, long j2) {
        return (tryToParseFourHexDigitsUtf16(j) << 16) | tryToParseFourHexDigitsUtf16(j2);
    }

    public static long tryToParseEightHexDigitsUtf8(byte[] bArr, int i) {
        return tryToParseEightHexDigitsUtf8(readLongFromByteArrayBigEndian(bArr, i));
    }

    public static long readLongFromByteArrayLittleEndian(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 7] & 255) << 56) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
    }

    public static long readLongFromByteArrayBigEndian(byte[] bArr, int i) {
        return (bArr[i + 7] & 255) | ((bArr[i] & 255) << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
    }
}
