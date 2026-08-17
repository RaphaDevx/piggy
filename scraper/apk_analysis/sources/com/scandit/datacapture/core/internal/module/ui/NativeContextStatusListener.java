package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.internal.sdk.common.NativeContextStatus;
import com.scandit.datacapture.core.internal.sdk.common.NativeError;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class NativeContextStatusListener {
    public abstract void onStatusChanged(NativeContextStatus nativeContextStatus);

    public abstract void onWarningsChanged(ArrayList<NativeError> arrayList);
}
