package com.scandit.capacitor.datacapture.core.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CapacitorResult.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J$\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/utils/CapacitorResult;", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", NotificationCompat.CATEGORY_CALL, "Lcom/getcapacitor/PluginCall;", "<init>", "(Lcom/getcapacitor/PluginCall;)V", "success", "", "result", "", "error", "errorCode", "", "errorMessage", "errorDetails", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CapacitorResult implements FrameworksResult {
    private final PluginCall call;

    public CapacitorResult(PluginCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.call = call;
    }

    @Override // com.scandit.datacapture.frameworks.core.result.FrameworksResult
    public void success(Object result) {
        String obj;
        if (result == null) {
            this.call.resolve();
            return;
        }
        if (result instanceof Map) {
            obj = new JSONObject((Map) result).toString();
        } else {
            obj = result.toString();
        }
        Intrinsics.checkNotNull(obj);
        JSObject jSObject = new JSObject();
        jSObject.put("data", obj);
        this.call.resolve(jSObject);
    }

    @Override // com.scandit.datacapture.frameworks.core.result.FrameworksResult
    public void error(String errorCode, String errorMessage, Object errorDetails) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        this.call.reject(errorMessage, errorMessage);
    }
}
