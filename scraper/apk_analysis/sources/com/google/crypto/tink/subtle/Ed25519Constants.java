package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Ed25519;
import java.lang.reflect.Array;
import java.math.BigInteger;

/* loaded from: classes2.dex */
final class Ed25519Constants {
    static final Ed25519.CachedXYT[] B2;
    static final Ed25519.CachedXYT[][] B_TABLE;
    static final long[] D;
    static final long[] D2;
    private static final BigInteger D2_BI;
    private static final BigInteger D_BI;
    private static final BigInteger P_BI;
    static final long[] SQRTM1;
    private static final BigInteger SQRTM1_BI;

    Ed25519Constants() {
    }

    static {
        BigInteger subtract = BigInteger.valueOf(2L).pow(255).subtract(BigInteger.valueOf(19L));
        P_BI = subtract;
        BigInteger mod = BigInteger.valueOf(-121665L).multiply(BigInteger.valueOf(121666L).modInverse(subtract)).mod(subtract);
        D_BI = mod;
        BigInteger mod2 = BigInteger.valueOf(2L).multiply(mod).mod(subtract);
        D2_BI = mod2;
        BigInteger modPow = BigInteger.valueOf(2L).modPow(subtract.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4L)), subtract);
        SQRTM1_BI = modPow;
        Point point = new Point();
        point.y = BigInteger.valueOf(4L).multiply(BigInteger.valueOf(5L).modInverse(subtract)).mod(subtract);
        point.x = recoverX(point.y);
        D = Field25519.expand(toLittleEndian(mod));
        D2 = Field25519.expand(toLittleEndian(mod2));
        SQRTM1 = Field25519.expand(toLittleEndian(modPow));
        B_TABLE = (Ed25519.CachedXYT[][]) Array.newInstance((Class<?>) Ed25519.CachedXYT.class, 32, 8);
        Point point2 = point;
        for (int i = 0; i < 32; i++) {
            Point point3 = point2;
            for (int i2 = 0; i2 < 8; i2++) {
                B_TABLE[i][i2] = getCachedXYT(point3);
                point3 = edwards(point3, point2);
            }
            for (int i3 = 0; i3 < 8; i3++) {
                point2 = edwards(point2, point2);
            }
        }
        Point edwards = edwards(point, point);
        B2 = new Ed25519.CachedXYT[8];
        for (int i4 = 0; i4 < 8; i4++) {
            B2[i4] = getCachedXYT(point);
            point = edwards(point, edwards);
        }
    }

    private static class Point {
        private BigInteger x;
        private BigInteger y;

        private Point() {
        }
    }

    private static BigInteger recoverX(BigInteger bigInteger) {
        BigInteger subtract = bigInteger.pow(2).subtract(BigInteger.ONE);
        BigInteger add = D_BI.multiply(bigInteger.pow(2)).add(BigInteger.ONE);
        BigInteger bigInteger2 = P_BI;
        BigInteger multiply = subtract.multiply(add.modInverse(bigInteger2));
        BigInteger modPow = multiply.modPow(bigInteger2.add(BigInteger.valueOf(3L)).divide(BigInteger.valueOf(8L)), bigInteger2);
        if (!modPow.pow(2).subtract(multiply).mod(bigInteger2).equals(BigInteger.ZERO)) {
            modPow = modPow.multiply(SQRTM1_BI).mod(bigInteger2);
        }
        return modPow.testBit(0) ? bigInteger2.subtract(modPow) : modPow;
    }

    private static Point edwards(Point point, Point point2) {
        Point point3 = new Point();
        BigInteger multiply = D_BI.multiply(point.x.multiply(point2.x).multiply(point.y).multiply(point2.y));
        BigInteger bigInteger = P_BI;
        BigInteger mod = multiply.mod(bigInteger);
        point3.x = point.x.multiply(point2.y).add(point2.x.multiply(point.y)).multiply(BigInteger.ONE.add(mod).modInverse(bigInteger)).mod(bigInteger);
        point3.y = point.y.multiply(point2.y).add(point.x.multiply(point2.x)).multiply(BigInteger.ONE.subtract(mod).modInverse(bigInteger)).mod(bigInteger);
        return point3;
    }

    private static byte[] toLittleEndian(BigInteger bigInteger) {
        byte[] bArr = new byte[32];
        byte[] byteArray = bigInteger.toByteArray();
        System.arraycopy(byteArray, 0, bArr, 32 - byteArray.length, byteArray.length);
        for (int i = 0; i < 16; i++) {
            byte b = bArr[i];
            int i2 = 31 - i;
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }
        return bArr;
    }

    private static Ed25519.CachedXYT getCachedXYT(Point point) {
        BigInteger add = point.y.add(point.x);
        BigInteger bigInteger = P_BI;
        return new Ed25519.CachedXYT(Field25519.expand(toLittleEndian(add.mod(bigInteger))), Field25519.expand(toLittleEndian(point.y.subtract(point.x).mod(bigInteger))), Field25519.expand(toLittleEndian(D2_BI.multiply(point.x).multiply(point.y).mod(bigInteger))));
    }
}
