package com.salesforce.marketingcloud.events.predicates;

import com.salesforce.marketingcloud.events.g;
import java.util.Locale;
import org.apache.commons.lang3.BooleanUtils;

/* loaded from: classes2.dex */
public class b extends h<Boolean> {

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
        }
    }

    public b(Object obj, g.a aVar, Object obj2) {
        super(obj, aVar, obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.salesforce.marketingcloud.events.predicates.h
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Boolean a(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if ("true".equalsIgnoreCase(str)) {
                return Boolean.TRUE;
            }
            if (BooleanUtils.FALSE.equalsIgnoreCase(str)) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.salesforce.marketingcloud.events.predicates.h
    public boolean a(Boolean bool, g.a aVar, Boolean bool2) throws UnsupportedOperationException {
        if (bool == null || bool2 == null) {
            return false;
        }
        int i = a.a[aVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new UnsupportedOperationException(String.format(Locale.ENGLISH, "Operator %s not supported for Boolean data types.", aVar));
            }
            if (bool == bool2) {
                return false;
            }
        } else if (bool != bool2) {
            return false;
        }
        return true;
    }
}
