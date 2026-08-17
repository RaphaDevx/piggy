package com.salesforce.marketingcloud.push.buttons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.media.q;
import com.salesforce.marketingcloud.push.buttons.a;
import com.salesforce.marketingcloud.push.data.Style;
import com.salesforce.marketingcloud.push.k;
import com.salesforce.marketingcloud.push.style.a;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements k<a> {
    private final Context a;
    private final com.salesforce.marketingcloud.push.b b;

    public c(Context context, com.salesforce.marketingcloud.push.b richButtonIntentProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(richButtonIntentProvider, "richButtonIntentProvider");
        this.a = context;
        this.b = richButtonIntentProvider;
    }

    @Override // com.salesforce.marketingcloud.push.k
    public RemoteViews a(RemoteViews remoteViews, a template) {
        CharSequence n;
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(template, "template");
        if (template.k().isEmpty()) {
            throw new IllegalArgumentException("Rich Buttons template must have at least one item");
        }
        a.b bVar = new a.b(this.a);
        remoteViews.setViewVisibility(R.id.mcsdk_push_custom_buttons, 0);
        remoteViews.setViewVisibility(R.id.mcsdk_push_button_list, 0);
        int i = 0;
        for (Object obj : template.k()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            a.c cVar = (a.c) obj;
            int identifier = this.a.getResources().getIdentifier("mcsdk_btn_item_" + i, "id", this.a.getPackageName());
            remoteViews.setViewVisibility(identifier, 0);
            com.salesforce.marketingcloud.push.b bVar2 = this.b;
            List<com.salesforce.marketingcloud.push.data.a> i3 = cVar.i();
            Unit unit = null;
            com.salesforce.marketingcloud.push.data.a[] aVarArr = i3 != null ? (com.salesforce.marketingcloud.push.data.a[]) i3.toArray(new com.salesforce.marketingcloud.push.data.a[0]) : null;
            String d = cVar.d();
            com.salesforce.marketingcloud.push.data.c p = cVar.p();
            remoteViews.setOnClickPendingIntent(identifier, bVar2.a(aVarArr, com.salesforce.marketingcloud.analytics.stats.b.o, d, p != null ? p.n() : null));
            com.salesforce.marketingcloud.push.data.c p2 = cVar.p();
            if (p2 != null) {
                int identifier2 = this.a.getResources().getIdentifier("mcsdk_btn_title_" + i, "id", this.a.getPackageName());
                remoteViews.setViewVisibility(identifier2, 0);
                Style.b a = ((com.salesforce.marketingcloud.push.data.c) com.salesforce.marketingcloud.push.style.a.a(bVar, p2, null, 2, null)).a();
                if (a == null || (n = a.o()) == null) {
                    n = p2.n();
                }
                remoteViews.setTextViewText(identifier2, n);
            }
            int identifier3 = this.a.getResources().getIdentifier("mcsdk_btn_img_" + i, "id", this.a.getPackageName());
            String o = cVar.o();
            if (o != null) {
                remoteViews.setViewVisibility(identifier3, 0);
                Bitmap decodeResource = BitmapFactory.decodeResource(this.a.getResources(), q.a.a(this.a, o));
                if (decodeResource != null) {
                    remoteViews.setImageViewBitmap(identifier3, decodeResource);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    remoteViews.setViewVisibility(identifier3, 8);
                }
            }
            i = i2;
        }
        return remoteViews;
    }
}
