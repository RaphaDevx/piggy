package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.barcode.spark.serialization.SparkScanScanningModeSerializer;
import com.scandit.datacapture.core.source.CameraPositionSerializer;
import com.scandit.datacapture.core.source.TorchStateSerializer;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0428g implements SparkScanAnalyticsManager {
    public final SparkScanStateManager a;
    public com.scandit.datacapture.barcode.internal.module.spark.internal.c b;
    public final com.scandit.datacapture.barcode.internal.module.spark.internal.g c;
    public final com.scandit.datacapture.barcode.internal.module.spark.internal.g d;
    public final com.scandit.datacapture.barcode.internal.module.spark.internal.g e;
    public final com.scandit.datacapture.barcode.internal.module.spark.internal.g f;
    public final com.scandit.datacapture.barcode.internal.module.spark.internal.g g;
    public final com.scandit.datacapture.barcode.internal.module.spark.internal.g h;

    public C0428g(SparkScanStateManager stateManager) {
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        this.a = stateManager;
        this.b = new com.scandit.datacapture.barcode.internal.module.spark.internal.c();
        this.c = new com.scandit.datacapture.barcode.internal.module.spark.internal.g("torch_state", TorchStateSerializer.toJson(stateManager.x()));
        this.d = new com.scandit.datacapture.barcode.internal.module.spark.internal.g("sound_enabled", String.valueOf(stateManager.v()));
        this.e = new com.scandit.datacapture.barcode.internal.module.spark.internal.g("haptic_enabled", String.valueOf(stateManager.e()));
        this.f = new com.scandit.datacapture.barcode.internal.module.spark.internal.g("session_stop_reason", null);
        this.g = new com.scandit.datacapture.barcode.internal.module.spark.internal.g("scanning_mode", SparkScanScanningModeSerializer.toJson(stateManager.s(), true));
        this.h = new com.scandit.datacapture.barcode.internal.module.spark.internal.g("camera_position", CameraPositionSerializer.toJson(stateManager.a()));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanAnalyticsManager
    public final void a() {
        synchronized (this) {
            com.scandit.datacapture.barcode.internal.module.spark.internal.c cVar = new com.scandit.datacapture.barcode.internal.module.spark.internal.c();
            this.b = cVar;
            com.scandit.datacapture.barcode.internal.module.spark.internal.d[] newDisposables = {this.a.f().a((com.scandit.datacapture.barcode.internal.module.spark.internal.x) new C0426e(this.e)), this.a.w().a((com.scandit.datacapture.barcode.internal.module.spark.internal.x) new C0427f(this.d)), this.a.y().a((com.scandit.datacapture.barcode.internal.module.spark.internal.x) new C0423b(this.c)), this.a.u().a((com.scandit.datacapture.barcode.internal.module.spark.internal.x) new C0424c(this.g)), this.a.b().a((com.scandit.datacapture.barcode.internal.module.spark.internal.x) new C0425d(this.h))};
            Intrinsics.checkNotNullParameter(newDisposables, "newDisposables");
            cVar.a.addAll(ArraysKt.filterNotNull(newDisposables));
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanAnalyticsManager
    public final void a(EnumC0422a stopReason) {
        Intrinsics.checkNotNullParameter(stopReason, "stopReason");
        synchronized (this) {
            com.scandit.datacapture.barcode.internal.module.spark.internal.g gVar = this.f;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", "session_stop_reason");
            jSONObject.put("value", stopReason.a);
            gVar.a(jSONObject);
            Unit unit = Unit.INSTANCE;
        }
        this.b.a();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanAnalyticsManager
    public final com.scandit.datacapture.barcode.internal.module.spark.internal.g a(String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case -1773044719:
                    if (str.equals("sound_enabled")) {
                        return this.d;
                    }
                    break;
                case -1404158567:
                    if (str.equals("haptic_enabled")) {
                        return this.e;
                    }
                    break;
                case -1137308018:
                    if (str.equals("torch_state")) {
                        return this.c;
                    }
                    break;
                case -77151997:
                    if (str.equals("camera_position")) {
                        return this.h;
                    }
                    break;
                case 1101455473:
                    if (str.equals("scanning_mode")) {
                        return this.g;
                    }
                    break;
                case 1563988216:
                    if (str.equals("session_stop_reason")) {
                        return this.f;
                    }
                    break;
            }
        }
        return null;
    }
}
