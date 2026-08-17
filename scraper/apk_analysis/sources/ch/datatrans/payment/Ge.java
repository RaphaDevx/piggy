package ch.datatrans.payment;

import android.text.Html;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTransaction;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public final class Ge {
    public final StringBuilder a = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
    public int b;

    public static void a(Ge ge, String name, String text, int i) {
        boolean z = (i & 4) != 0;
        ge.getClass();
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(text, "text");
        ge.a(name, null, text, z, null);
    }

    public final void a(int i) {
        Intrinsics.checkNotNullParameter(CardsGiftcardTransaction.SERIALIZED_NAME_AMOUNT, "name");
        a(this, CardsGiftcardTransaction.SERIALIZED_NAME_AMOUNT, null, String.valueOf(i), null, 24);
    }

    public static /* synthetic */ void a(Ge ge, String str, Map map, String str2, Function1 function1, int i) {
        Map map2 = (i & 2) != 0 ? null : map;
        if ((i & 4) != 0) {
            str2 = "";
        }
        ge.a(str, map2, str2, true, (i & 16) != 0 ? null : function1);
    }

    public final void a(String name, Map map, String text, boolean z, Function1 function1) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(text, "text");
        StringBuilder sb = this.a;
        sb.append(StringsKt.repeat("\t", this.b));
        sb.append("<" + name);
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                StringBuilder sb2 = new StringBuilder(StringUtils.SPACE);
                sb2.append(str);
                sb2.append("=\"");
                String escapeHtml = Html.escapeHtml(value.toString());
                Intrinsics.checkNotNullExpressionValue(escapeHtml, "escapeHtml(...)");
                sb2.append(escapeHtml);
                sb2.append('\"');
                sb.append(sb2.toString());
            }
        }
        sb.append(">");
        if (z) {
            text = Html.escapeHtml(text);
            Intrinsics.checkNotNullExpressionValue(text, "escapeHtml(...)");
        }
        sb.append(text);
        if (function1 != null) {
            sb.append('\n');
            this.b++;
            function1.invoke(this);
            int i = this.b - 1;
            this.b = i;
            sb.append(StringsKt.repeat("\t", i));
        }
        sb.append("</" + name + Typography.greater);
        sb.append('\n');
    }
}
