package com.scandit.capacitor.datacapture.core.errors;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ActionError.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u000b2\u00060\u0001j\u0002`\u0002:\u0001\u000bB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/errors/ActionError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorCode", "", "errorMessage", "", "<init>", "(ILjava/lang/String;)V", "serializeContent", "Lorg/json/JSONObject;", "Companion", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class ActionError extends Exception {
    private static final String KEY_CODE = "Code";
    private static final String KEY_MESSAGE = "Message";
    private final int errorCode;
    private final String errorMessage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActionError(int i, String errorMessage) {
        super(errorMessage);
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        this.errorCode = i;
        this.errorMessage = errorMessage;
    }

    public final JSONObject serializeContent() {
        return new JSONObject(MapsKt.mapOf(TuplesKt.to(KEY_CODE, Integer.valueOf(this.errorCode)), TuplesKt.to(KEY_MESSAGE, this.errorMessage)));
    }
}
