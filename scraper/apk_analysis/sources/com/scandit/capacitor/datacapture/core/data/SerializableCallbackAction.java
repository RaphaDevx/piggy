package com.scandit.capacitor.datacapture.core.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: SerializableCallbackAction.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \f2\u00020\u0001:\u0001\fB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/data/SerializableCallbackAction;", "Lcom/scandit/capacitor/datacapture/core/data/SerializableData;", "callbackName", "", SerializableCallbackAction.FIELD_ARGUMENT, "Lorg/json/JSONObject;", "finishCallbackId", SerializableCallbackAction.FIELD_SHOULD_NOTIFY_WHEN_FINISHED, "", "<init>", "(Ljava/lang/String;Lorg/json/JSONObject;Ljava/lang/String;Z)V", "toJson", "Companion", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SerializableCallbackAction implements SerializableData {
    private static final String FIELD_ARGUMENT = "argument";
    public static final String FIELD_FINISH_CALLBACK_ID = "finishCallbackID";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_SHOULD_NOTIFY_WHEN_FINISHED = "shouldNotifyWhenFinished";
    private final JSONObject argument;
    private final String callbackName;
    private final String finishCallbackId;
    private final boolean shouldNotifyWhenFinished;

    public SerializableCallbackAction(String callbackName, JSONObject argument, String str, boolean z) {
        Intrinsics.checkNotNullParameter(callbackName, "callbackName");
        Intrinsics.checkNotNullParameter(argument, "argument");
        this.callbackName = callbackName;
        this.argument = argument;
        this.finishCallbackId = str;
        this.shouldNotifyWhenFinished = z;
    }

    public /* synthetic */ SerializableCallbackAction(String str, JSONObject jSONObject, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, jSONObject, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? false : z);
    }

    @Override // com.scandit.capacitor.datacapture.core.data.SerializableData
    public JSONObject toJson() {
        return new JSONObject(MapsKt.mapOf(TuplesKt.to("name", this.callbackName), TuplesKt.to(FIELD_ARGUMENT, this.argument), TuplesKt.to(FIELD_FINISH_CALLBACK_ID, this.finishCallbackId), TuplesKt.to(FIELD_SHOULD_NOTIFY_WHEN_FINISHED, Boolean.valueOf(this.shouldNotifyWhenFinished))));
    }
}
