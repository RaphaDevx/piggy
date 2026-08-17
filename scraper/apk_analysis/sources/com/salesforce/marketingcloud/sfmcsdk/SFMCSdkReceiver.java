package com.salesforce.marketingcloud.sfmcsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorType;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: SFMCSdkReceiver.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SFMCSdkReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BehaviorType fromString;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action == null) {
            action = "";
        }
        String str = action;
        if (StringsKt.isBlank(str)) {
            return;
        }
        final String replaceFirst$default = StringsKt.replaceFirst$default(str, context.getApplicationContext().getPackageName() + new Regex("."), "", false, 4, (Object) null);
        SFMCSdkLogger.INSTANCE.d("~$SFMCSdkReceiver", new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReceiver$onReceive$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "onReceive with action: " + replaceFirst$default;
            }
        });
        if (!Intrinsics.areEqual(replaceFirst$default, "android.intent.action.MY_PACKAGE_REPLACED") || (fromString = BehaviorType.INSTANCE.fromString(replaceFirst$default)) == null) {
            return;
        }
        SFMCSdkJobIntentService.INSTANCE.enqueueSystemBehavior(context, fromString, intent.getExtras());
    }
}
