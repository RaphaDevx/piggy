package com.salesforce.marketingcloud.sfmcsdk.modules;

import android.os.Handler;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModuleInterface.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleReadyHandler;", "Landroid/os/Handler;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleReadyListener;", "(Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleReadyListener;)V", "getListener", "()Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleReadyListener;", "deliverModule", "", "module", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleInterface;", "execute", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ModuleReadyHandler extends Handler {
    private final ModuleReadyListener listener;

    public final ModuleReadyListener getListener() {
        return this.listener;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ModuleReadyHandler(com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener r2) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyHandler.<init>(com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deliverModule$lambda$0(ModuleReadyHandler this$0, ModuleInterface module) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(module, "$module");
        this$0.execute(module, this$0.listener);
    }

    public final void deliverModule(final ModuleInterface module) {
        Intrinsics.checkNotNullParameter(module, "module");
        post(new Runnable() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ModuleReadyHandler.deliverModule$lambda$0(ModuleReadyHandler.this, module);
            }
        });
    }

    private final void execute(final ModuleInterface module, final ModuleReadyListener listener) {
        try {
            listener.ready(module);
        } catch (Exception e) {
            SFMCSdkLogger.INSTANCE.e("~$ModuleReadyHandler", e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyHandler$execute$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Error delivering module " + ModuleInterface.this + " to " + listener;
                }
            });
        }
    }
}
