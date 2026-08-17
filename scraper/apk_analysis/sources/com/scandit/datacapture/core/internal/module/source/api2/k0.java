package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2InfoProvider;
import com.scandit.datacapture.core.logger.CameraInfoProviderEvent;
import com.scandit.datacapture.core.logger.SdcLogger;
import java.util.ArrayList;
import kotlin.ExceptionsKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class k0 implements CameraApi2InfoProvider {
    public final CameraManager a;

    public k0(CameraManager cameraManager) {
        Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
        this.a = cameraManager;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2InfoProvider
    public final Iterable cameraInfos() {
        String str;
        Object[] objArr = null;
        try {
            String[] cameraIdList = this.a.getCameraIdList();
            if (cameraIdList.length == 0) {
                SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new CameraInfoProviderEvent(2, 0, "No exception thrown, 0 cameras available", null, 8, null));
                return CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList(cameraIdList.length);
            for (String str2 : cameraIdList) {
                CameraCharacteristics cameraCharacteristics = this.a.getCameraCharacteristics(str2);
                Intrinsics.checkNotNullExpressionValue(cameraCharacteristics, "getCameraCharacteristics(...)");
                arrayList.add(new j0(str2, cameraCharacteristics));
            }
            return arrayList;
        } catch (CameraAccessException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
            int length = 0 != 0 ? objArr.length : -1;
            String stackTraceToString = ExceptionsKt.stackTraceToString(e);
            StringBuilder sb = new StringBuilder("Camera id list: ");
            if (0 == 0 || (str = ArraysKt.joinToString$default((Object[]) null, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null)) == null) {
                str = "null list";
            }
            sb.append(str);
            sb.append(';');
            SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new CameraInfoProviderEvent(2, length, stackTraceToString, sb.toString()));
            return CollectionsKt.emptyList();
        }
    }
}
