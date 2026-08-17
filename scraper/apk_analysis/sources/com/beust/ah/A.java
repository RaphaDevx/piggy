package com.beust.ah;

import java.util.HashSet;

/* loaded from: classes3.dex */
public class A {
    public static void main(String[] strArr) {
        HashSet hashSet = new HashSet();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 40000; i++) {
            hashSet.add(new Pair(Integer.valueOf(i), Integer.valueOf(i)));
        }
        System.out.printf("Total time to add %d entries: %d ms", 40000, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }
}
