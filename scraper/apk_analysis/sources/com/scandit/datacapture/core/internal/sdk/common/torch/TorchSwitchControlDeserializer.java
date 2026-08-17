package com.scandit.datacapture.core.internal.sdk.common.torch;

import android.content.Context;
import android.graphics.Bitmap;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.R;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.JsonExtensionsKt;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.control.TorchSwitchControl;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/common/torch/TorchSwitchControlDeserializer;", "", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/core/json/JsonValue;", "json", "Lcom/scandit/datacapture/core/ui/control/TorchSwitchControl;", "fromJson", "(Landroid/content/Context;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/core/ui/control/TorchSwitchControl;", "control", "", "updateFromJson", "(Lcom/scandit/datacapture/core/ui/control/TorchSwitchControl;Lcom/scandit/datacapture/core/json/JsonValue;)V", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class TorchSwitchControlDeserializer {
    public static final TorchSwitchControlDeserializer INSTANCE = new TorchSwitchControlDeserializer();

    private TorchSwitchControlDeserializer() {
    }

    @JvmStatic
    public static final TorchSwitchControl fromJson(Context context, JsonValue json) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(json, "json");
        TorchSwitchControl torchSwitchControl = new TorchSwitchControl(context);
        updateFromJson(torchSwitchControl, json);
        return torchSwitchControl;
    }

    @JvmStatic
    public static final void updateFromJson(TorchSwitchControl control, JsonValue json) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmap3;
        Bitmap bitmap4;
        Intrinsics.checkNotNullParameter(control, "control");
        Intrinsics.checkNotNullParameter(json, "json");
        JsonValue byKeyAsObject = json.getByKeyAsObject(BarcodePickDeserializer.FIELD_ICON, null);
        if (byKeyAsObject != null) {
            JsonValue byKeyAsObject2 = byKeyAsObject.getByKeyAsObject("on", null);
            if (byKeyAsObject2 != null) {
                bitmap4 = JsonExtensionsKt.bitmapByKey(byKeyAsObject2, "default");
                bitmap2 = JsonExtensionsKt.bitmapByKey(byKeyAsObject2, "pressed");
            } else {
                bitmap2 = null;
                bitmap4 = null;
            }
            JsonValue byKeyAsObject3 = byKeyAsObject.getByKeyAsObject("off", null);
            if (byKeyAsObject3 != null) {
                bitmap3 = JsonExtensionsKt.bitmapByKey(byKeyAsObject3, "default");
                bitmap = JsonExtensionsKt.bitmapByKey(byKeyAsObject3, "pressed");
            } else {
                bitmap = null;
                bitmap3 = null;
            }
        } else {
            bitmap = null;
            bitmap2 = null;
            bitmap3 = null;
            bitmap4 = null;
        }
        String byKeyAsNullableString$scandit_capture_core = json.getByKeyAsNullableString$scandit_capture_core("contentDescriptionWhenOff", null);
        if (byKeyAsNullableString$scandit_capture_core != null) {
            control.setContentDescriptionWhenOff(byKeyAsNullableString$scandit_capture_core);
        }
        String byKeyAsNullableString$scandit_capture_core2 = json.getByKeyAsNullableString$scandit_capture_core("contentDescriptionWhenOn", null);
        if (byKeyAsNullableString$scandit_capture_core2 != null) {
            control.setContentDescriptionWhenOn(byKeyAsNullableString$scandit_capture_core2);
        }
        int i = R.drawable.sc_ic_torch_on;
        int i2 = R.drawable.sc_ic_torch_on_pressed;
        int i3 = R.drawable.sc_ic_torch_off;
        int i4 = R.drawable.sc_ic_torch_off_pressed;
        if (bitmap4 == null) {
            bitmap4 = BitmapExtensionsKt.bitmapFromResource(i);
        }
        control.setTorchOnImage(bitmap4);
        if (bitmap2 == null) {
            bitmap2 = BitmapExtensionsKt.bitmapFromResource(i2);
        }
        control.setTorchOnPressedImage(bitmap2);
        if (bitmap3 == null) {
            bitmap3 = BitmapExtensionsKt.bitmapFromResource(i3);
        }
        control.setTorchOffImage(bitmap3);
        if (bitmap == null) {
            bitmap = BitmapExtensionsKt.bitmapFromResource(i4);
        }
        control.setTorchOffPressedImage(bitmap);
    }
}
