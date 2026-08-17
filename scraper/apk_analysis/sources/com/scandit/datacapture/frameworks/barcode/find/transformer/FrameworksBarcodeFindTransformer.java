package com.scandit.datacapture.frameworks.barcode.find.transformer;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindTransformer;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeFindTransformer.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u0010\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000bJ\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/transformer/FrameworksBarcodeFindTransformer;", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindTransformer;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "viewId", "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", ExtentionsKt.ENABLED_KEY, "Ljava/util/concurrent/atomic/AtomicBoolean;", "onTransformBarcodeData", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "", "cancel", "", "disable", "enable", "submitResult", "result", "transformBarcodeData", "data", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeFindTransformer implements BarcodeFindTransformer {
    public static final String ON_TRANSFORM_BARCODE_DATA = "BarcodeFindTransformer.transformBarcodeData";
    private final Emitter emitter;
    private final AtomicBoolean enabled;
    private final EventForResult<String> onTransformBarcodeData;
    private final int viewId;

    public FrameworksBarcodeFindTransformer(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
        this.onTransformBarcodeData = new EventForResult<>(ON_TRANSFORM_BARCODE_DATA, null, null, 6, null);
        this.enabled = new AtomicBoolean(false);
    }

    public final void enable() {
        this.enabled.set(true);
    }

    public final void disable() {
        this.enabled.set(false);
        cancel();
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindTransformer
    public String transformBarcodeData(String data) {
        return !this.enabled.get() ? data : this.onTransformBarcodeData.emit(this.emitter, MapsKt.mutableMapOf(TuplesKt.to("data", data), TuplesKt.to("viewId", Integer.valueOf(this.viewId))), null, -1L);
    }

    public final void submitResult(String result) {
        this.onTransformBarcodeData.submitResult(result);
    }

    public final void cancel() {
        this.onTransformBarcodeData.cancel();
    }
}
