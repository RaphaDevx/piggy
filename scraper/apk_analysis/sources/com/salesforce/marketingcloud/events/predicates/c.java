package com.salesforce.marketingcloud.events.predicates;

import com.salesforce.marketingcloud.events.g;

/* loaded from: classes2.dex */
public class c extends h<Double> {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.a.values().length];
            a = iArr;
            try {
                iArr[g.a.b.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.a.c.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.a.d.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[g.a.e.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[g.a.f.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[g.a.g.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public c(Object obj, g.a aVar, Object obj2) {
        super(obj, aVar, obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.salesforce.marketingcloud.events.predicates.h
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Double a(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0047 A[ORIG_RETURN, RETURN] */
    @Override // com.salesforce.marketingcloud.events.predicates.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.lang.Double r5, com.salesforce.marketingcloud.events.g.a r6, java.lang.Double r7) throws java.lang.UnsupportedOperationException {
        /*
            r4 = this;
            r4 = 0
            if (r5 == 0) goto L48
            if (r7 == 0) goto L48
            double r0 = r5.doubleValue()
            double r2 = r7.doubleValue()
            int[] r5 = com.salesforce.marketingcloud.events.predicates.c.a.a
            int r7 = r6.ordinal()
            r5 = r5[r7]
            switch(r5) {
                case 1: goto L43;
                case 2: goto L3e;
                case 3: goto L39;
                case 4: goto L34;
                case 5: goto L2f;
                case 6: goto L2a;
                default: goto L18;
            }
        L18:
            java.lang.UnsupportedOperationException r4 = new java.lang.UnsupportedOperationException
            java.util.Locale r5 = java.util.Locale.ENGLISH
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r7 = "Operator %s not supported for Double data types."
            java.lang.String r5 = java.lang.String.format(r5, r7, r6)
            r4.<init>(r5)
            throw r4
        L2a:
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 < 0) goto L48
            goto L47
        L2f:
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 > 0) goto L48
            goto L47
        L34:
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 <= 0) goto L48
            goto L47
        L39:
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 >= 0) goto L48
            goto L47
        L3e:
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L48
            goto L47
        L43:
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 != 0) goto L48
        L47:
            r4 = 1
        L48:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.predicates.c.a(java.lang.Double, com.salesforce.marketingcloud.events.g$a, java.lang.Double):boolean");
    }
}
