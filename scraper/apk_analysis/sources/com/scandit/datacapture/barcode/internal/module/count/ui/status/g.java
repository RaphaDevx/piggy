package com.scandit.datacapture.barcode.internal.module.count.ui.status;

import android.widget.ImageView;
import com.scandit.datacapture.barcode.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function0 {
    public final /* synthetic */ m a;
    public final /* synthetic */ b b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(m mVar, b bVar) {
        super(0);
        this.a = mVar;
        this.b = bVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        int i;
        ImageView a = this.a.a();
        b bVar = this.b;
        if (Intrinsics.areEqual(bVar, d.a)) {
            i = R.drawable.sc_ic_status_none;
        } else if (Intrinsics.areEqual(bVar, c.a)) {
            i = R.drawable.sc_ic_status_not_available;
        } else {
            if (!(bVar instanceof e)) {
                throw new NoWhenBranchMatchedException();
            }
            switch (f.a[((e) this.b).a.ordinal()]) {
                case 1:
                    i = R.drawable.sc_ic_status_none;
                    break;
                case 2:
                    i = R.drawable.sc_ic_status_not_available;
                    break;
                case 3:
                    i = R.drawable.sc_ic_status_expired;
                    break;
                case 4:
                    i = R.drawable.sc_ic_status_fragile;
                    break;
                case 5:
                    i = R.drawable.sc_ic_status_quality_check;
                    break;
                case 6:
                    i = R.drawable.sc_ic_status_low_stock;
                    break;
                case 7:
                    i = R.drawable.sc_ic_status_wrong;
                    break;
                case 8:
                    i = R.drawable.sc_ic_status_expiring_soon;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        a.setImageResource(i);
        return Unit.INSTANCE;
    }
}
