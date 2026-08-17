package com.scandit.datacapture.frameworks.barcode;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.barcode.data.defaults.BarcodeDefaults;
import com.scandit.datacapture.frameworks.core.FrameworkModule;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeModule.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/BarcodeModule;", "Lcom/scandit/datacapture/frameworks/core/FrameworkModule;", "()V", "contextRef", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "getDefaults", "", "", "", "onCreate", "", "context", "onDestroy", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BarcodeModule implements FrameworkModule {
    private WeakReference<Context> contextRef = new WeakReference<>(null);

    @Override // com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onCreate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.contextRef = new WeakReference<>(context);
    }

    @Override // com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onDestroy() {
        this.contextRef = new WeakReference<>(null);
    }

    public final Map<String, Object> getDefaults() {
        return BarcodeDefaults.INSTANCE.get();
    }
}
