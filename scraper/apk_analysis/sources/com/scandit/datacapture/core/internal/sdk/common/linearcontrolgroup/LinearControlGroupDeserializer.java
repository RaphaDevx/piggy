package com.scandit.datacapture.core.internal.sdk.common.linearcontrolgroup;

import android.content.Context;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.internal.sdk.common.camera.CameraSwitchControlDeserializer;
import com.scandit.datacapture.core.internal.sdk.common.torch.TorchSwitchControlDeserializer;
import com.scandit.datacapture.core.internal.sdk.common.zoom.ZoomSwitchControlDeserializer;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.control.LinearControlGroup;
import com.scandit.datacapture.core.ui.control.LinearControlGroupBuilder;
import com.scandit.datacapture.core.ui.control.LinearControlGroupOrientation;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/common/linearcontrolgroup/LinearControlGroupDeserializer;", "", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/core/json/JsonValue;", "json", "Lcom/scandit/datacapture/core/ui/control/LinearControlGroup;", "fromJson", "(Landroid/content/Context;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/core/ui/control/LinearControlGroup;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class LinearControlGroupDeserializer {
    public static final LinearControlGroupDeserializer INSTANCE = new LinearControlGroupDeserializer();

    private LinearControlGroupDeserializer() {
    }

    @JvmStatic
    public static final LinearControlGroup fromJson(Context context, JsonValue json) {
        Object fromJson;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(json, "json");
        LinearControlGroupOrientation fromString = LinearControlGroupOrientationDeserializer.fromString(json.getByKeyAsString("orientation", "vertical"));
        FloatWithUnit byKeyAsFloatWithUnit = json.getByKeyAsFloatWithUnit("spacing", new FloatWithUnit(0.0f, MeasureUnit.PIXEL));
        ArrayList arrayList = new ArrayList();
        JsonValue byKeyAsArray = json.getByKeyAsArray("childControls", null);
        if (byKeyAsArray != null) {
            int size = (int) byKeyAsArray.getSize();
            for (int i = 0; i < size; i++) {
                JsonValue requireByIndex = byKeyAsArray.requireByIndex(i);
                LinearControlGroupDeserializer linearControlGroupDeserializer = INSTANCE;
                a aVar = new a(arrayList);
                linearControlGroupDeserializer.getClass();
                String requireByKeyAsString = requireByIndex.requireByKeyAsString("type");
                if (Intrinsics.areEqual(requireByKeyAsString, "linearGroup")) {
                    throw new IllegalStateException("Nested LinearControlGroups are not allowed".toString());
                }
                int hashCode = requireByKeyAsString.hashCode();
                if (hashCode != -1367751899) {
                    if (hashCode != 3744723) {
                        if (hashCode == 110547964 && requireByKeyAsString.equals("torch")) {
                            fromJson = TorchSwitchControlDeserializer.fromJson(context, requireByIndex);
                            aVar.invoke(fromJson);
                        }
                        throw new IllegalStateException((requireByIndex.getAbsolutePath() + ".type is required to be one of [torch, camera, zoom, linearGroup]").toString());
                    }
                    if (!requireByKeyAsString.equals("zoom")) {
                        throw new IllegalStateException((requireByIndex.getAbsolutePath() + ".type is required to be one of [torch, camera, zoom, linearGroup]").toString());
                    }
                    fromJson = ZoomSwitchControlDeserializer.fromJson(context, requireByIndex);
                    aVar.invoke(fromJson);
                } else {
                    if (!requireByKeyAsString.equals("camera")) {
                        throw new IllegalStateException((requireByIndex.getAbsolutePath() + ".type is required to be one of [torch, camera, zoom, linearGroup]").toString());
                    }
                    fromJson = CameraSwitchControlDeserializer.fromJson(context, requireByIndex);
                    aVar.invoke(fromJson);
                }
            }
        }
        return new LinearControlGroupBuilder(context).setOrientation(fromString).setSpacing(byKeyAsFloatWithUnit).setControls(arrayList).build();
    }
}
