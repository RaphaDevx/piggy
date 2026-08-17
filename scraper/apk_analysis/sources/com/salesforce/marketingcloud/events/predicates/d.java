package com.salesforce.marketingcloud.events.predicates;

import com.salesforce.marketingcloud.events.g;
import java.util.Locale;

/* loaded from: classes2.dex */
public class d extends h<Integer> {

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

    public d(Object obj, g.a aVar, Object obj2) {
        super(obj, aVar, obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.salesforce.marketingcloud.events.predicates.h
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            try {
                return Integer.valueOf(Integer.parseInt((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.salesforce.marketingcloud.events.predicates.h
    public boolean a(Integer num, g.a aVar, Integer num2) throws UnsupportedOperationException {
        if (num == null || num2 == null) {
            return false;
        }
        switch (a.a[aVar.ordinal()]) {
            case 1:
                return num.equals(num2);
            case 2:
                return !num.equals(num2);
            case 3:
                if (num.intValue() >= num2.intValue()) {
                    return false;
                }
                break;
            case 4:
                if (num.intValue() <= num2.intValue()) {
                    return false;
                }
                break;
            case 5:
                if (num.intValue() > num2.intValue()) {
                    return false;
                }
                break;
            case 6:
                if (num.intValue() < num2.intValue()) {
                    return false;
                }
                break;
            default:
                throw new UnsupportedOperationException(String.format(Locale.ENGLISH, "Operator %s not supported for Integer data types.", aVar));
        }
        return true;
    }
}
