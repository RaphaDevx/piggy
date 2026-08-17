package com.getcapacitor;

import java.util.ArrayList;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class UriMatcher {
    private static final int EXACT = 0;
    private static final int MASK = 3;
    static final Pattern PATH_SPLIT_PATTERN = Pattern.compile("/");
    private static final int REST = 2;
    private static final int TEXT = 1;
    private ArrayList<UriMatcher> mChildren;
    private Object mCode;
    private String mText;
    private int mWhich;

    public UriMatcher(Object obj) {
        this.mCode = obj;
        this.mWhich = -1;
        this.mChildren = new ArrayList<>();
        this.mText = null;
    }

    private UriMatcher() {
        this.mCode = null;
        this.mWhich = -1;
        this.mChildren = new ArrayList<>();
        this.mText = null;
    }

    public void addURI(String str, String str2, String str3, Object obj) {
        String[] strArr;
        String str4;
        String str5 = str3;
        if (obj == null) {
            throw new IllegalArgumentException("Code can't be null");
        }
        if (str5 != null) {
            if (!str3.isEmpty() && str5.charAt(0) == '/') {
                str5 = str5.substring(1);
            }
            strArr = PATH_SPLIT_PATTERN.split(str5);
        } else {
            strArr = null;
        }
        int length = strArr != null ? strArr.length : 0;
        UriMatcher uriMatcher = this;
        int i = -2;
        while (i < length) {
            if (i == -2) {
                str4 = str;
            } else {
                str4 = i == -1 ? str2 : strArr[i];
            }
            ArrayList<UriMatcher> arrayList = uriMatcher.mChildren;
            int size = arrayList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                UriMatcher uriMatcher2 = arrayList.get(i2);
                if (str4.equals(uriMatcher2.mText)) {
                    uriMatcher = uriMatcher2;
                    break;
                }
                i2++;
            }
            if (i2 == size) {
                UriMatcher uriMatcher3 = new UriMatcher();
                if (i == -1 && str4.contains("*")) {
                    uriMatcher3.mWhich = 3;
                } else if (str4.equals("**")) {
                    uriMatcher3.mWhich = 2;
                } else if (str4.equals("*")) {
                    uriMatcher3.mWhich = 1;
                } else {
                    uriMatcher3.mWhich = 0;
                }
                uriMatcher3.mText = str4;
                uriMatcher.mChildren.add(uriMatcher3);
                uriMatcher = uriMatcher3;
            }
            i++;
        }
        uriMatcher.mCode = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
    
        if (com.getcapacitor.util.HostMask.Parser.parse(r9.mText).matches(r4) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0064, code lost:
    
        if (r9.mText.equals(r4) != false) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006a A[LOOP:1: B:15:0x0038->B:27:0x006a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006d A[EDGE_INSN: B:28:0x006d->B:29:0x006d BREAK  A[LOOP:1: B:15:0x0038->B:27:0x006a], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object match(android.net.Uri r13) {
        /*
            r12 = this;
            java.util.List r0 = r13.getPathSegments()
            int r1 = r0.size()
            if (r1 != 0) goto L13
            java.lang.String r2 = r13.getAuthority()
            if (r2 != 0) goto L13
            java.lang.Object r12 = r12.mCode
            return r12
        L13:
            r2 = -2
            r3 = r2
        L15:
            if (r3 >= r1) goto L74
            if (r3 != r2) goto L1e
            java.lang.String r4 = r13.getScheme()
            goto L2c
        L1e:
            r4 = -1
            if (r3 != r4) goto L26
            java.lang.String r4 = r13.getAuthority()
            goto L2c
        L26:
            java.lang.Object r4 = r0.get(r3)
            java.lang.String r4 = (java.lang.String) r4
        L2c:
            java.util.ArrayList<com.getcapacitor.UriMatcher> r5 = r12.mChildren
            if (r5 != 0) goto L31
            goto L74
        L31:
            int r12 = r5.size()
            r6 = 0
            r7 = 0
            r8 = r6
        L38:
            if (r7 >= r12) goto L6d
            java.lang.Object r9 = r5.get(r7)
            com.getcapacitor.UriMatcher r9 = (com.getcapacitor.UriMatcher) r9
            int r10 = r9.mWhich
            if (r10 == 0) goto L5e
            r11 = 1
            if (r10 == r11) goto L66
            r11 = 2
            if (r10 == r11) goto L5b
            r11 = 3
            if (r10 == r11) goto L4e
            goto L67
        L4e:
            java.lang.String r10 = r9.mText
            com.getcapacitor.util.HostMask r10 = com.getcapacitor.util.HostMask.Parser.parse(r10)
            boolean r10 = r10.matches(r4)
            if (r10 == 0) goto L67
            goto L66
        L5b:
            java.lang.Object r12 = r9.mCode
            return r12
        L5e:
            java.lang.String r10 = r9.mText
            boolean r10 = r10.equals(r4)
            if (r10 == 0) goto L67
        L66:
            r8 = r9
        L67:
            if (r8 == 0) goto L6a
            goto L6d
        L6a:
            int r7 = r7 + 1
            goto L38
        L6d:
            r12 = r8
            if (r12 != 0) goto L71
            return r6
        L71:
            int r3 = r3 + 1
            goto L15
        L74:
            java.lang.Object r12 = r12.mCode
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getcapacitor.UriMatcher.match(android.net.Uri):java.lang.Object");
    }
}
