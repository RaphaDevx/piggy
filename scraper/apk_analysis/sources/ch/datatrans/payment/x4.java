package ch.datatrans.payment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewGroupKt;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* loaded from: classes3.dex */
public abstract class x4 {
    public static final void a(TextView textView, InterfaceC0235ud interfaceC0235ud) {
        String str;
        Intrinsics.checkNotNullParameter(textView, "<this>");
        if (interfaceC0235ud != null) {
            Context context = textView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            str = interfaceC0235ud.a(context);
        } else {
            str = null;
        }
        textView.setText(str);
    }

    public static final void a(final View view, long j) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.postDelayed(new Runnable() { // from class: ch.datatrans.payment.x4$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                x4.a(view);
            }
        }, j);
    }

    public static final void a(View view) {
        view.sendAccessibilityEvent(8);
    }

    public static final void a(Map map, String key, Object obj) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (obj != null) {
            map.put(key, obj);
        }
    }

    public static final void a(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() > 0) {
                Iterator<View> it = ViewGroupKt.getChildren(viewGroup).iterator();
                while (it.hasNext()) {
                    a(it.next(), z);
                }
            }
        }
    }

    public static final String a(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        BigInteger bigInteger = new BigInteger(1, bArr);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%0" + (bArr.length << 1) + 'x', Arrays.copyOf(new Object[]{bigInteger}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public static final byte[] a(byte[] bArr, byte[] other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        ArrayList arrayList = new ArrayList(bArr.length);
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            if (i2 % 2 != 0) {
                b = other[i2];
            }
            arrayList.add(Byte.valueOf(b));
            i++;
            i2 = i3;
        }
        return CollectionsKt.toByteArray(arrayList);
    }
}
