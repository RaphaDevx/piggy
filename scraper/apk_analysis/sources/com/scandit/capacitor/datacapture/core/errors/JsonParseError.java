package com.scandit.capacitor.datacapture.core.errors;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: JsonParseError.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/errors/JsonParseError;", "Lcom/scandit/capacitor/datacapture/core/errors/ActionError;", "error", "", "<init>", "(Ljava/lang/String;)V", "Companion", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class JsonParseError extends ActionError {
    private static final int ERROR_CODE = 10001;

    public JsonParseError(String str) {
        super(ERROR_CODE, "Invalid or no JSON passed for command: " + (str == null ? "No additional info" : str));
    }
}
