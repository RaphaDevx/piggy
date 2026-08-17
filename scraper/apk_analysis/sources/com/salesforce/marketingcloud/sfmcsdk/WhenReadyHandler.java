package com.salesforce.marketingcloud.sfmcsdk;

import android.os.Handler;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WhenReadyHandler.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/WhenReadyHandler;", "Landroid/os/Handler;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkReadyListener;", "(Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkReadyListener;)V", "getListener", "()Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkReadyListener;", "deliverSdk", "", "sdk", "Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdk;", "execute", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class WhenReadyHandler extends Handler {
    private final SFMCSdkReadyListener listener;

    public final SFMCSdkReadyListener getListener() {
        return this.listener;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public WhenReadyHandler(com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener r2) {
        /*
            r1 = this;
            java.lang.String r0 = "listener"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            android.os.Looper r0 = android.os.Looper.myLooper()
            if (r0 != 0) goto Lf
            android.os.Looper r0 = android.os.Looper.getMainLooper()
        Lf:
            r1.<init>(r0)
            r1.listener = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.WhenReadyHandler.<init>(com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deliverSdk$lambda$0(WhenReadyHandler this$0, SFMCSdk sdk) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sdk, "$sdk");
        this$0.execute(sdk, this$0.listener);
    }

    public final void deliverSdk(final SFMCSdk sdk) {
        Intrinsics.checkNotNullParameter(sdk, "sdk");
        post(new Runnable() { // from class: com.salesforce.marketingcloud.sfmcsdk.WhenReadyHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WhenReadyHandler.deliverSdk$lambda$0(WhenReadyHandler.this, sdk);
            }
        });
    }

    private final void execute(SFMCSdk sdk, final SFMCSdkReadyListener listener) {
        try {
            listener.ready(sdk);
        } catch (Exception e) {
            SFMCSdkLogger.INSTANCE.e("~$WhenReadyHandler", e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.WhenReadyHandler$execute$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Error in " + SFMCSdkReadyListener.this.getClass().getName();
                }
            });
        }
    }
}
