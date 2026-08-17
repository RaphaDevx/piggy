package com.scandit.datacapture.barcode.internal.module.batch.ui.overlay;

import android.view.ViewGroup;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.Augmentation;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.TrackedObjectAugmenter;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.Transformation;
import com.scandit.datacapture.core.internal.sdk.utils.ObjectOverlayUtilsKt;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e extends TrackedObjectAugmenter {
    public boolean j;
    public final d k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(TrackedObjectAugmenter.Host host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.j = true;
        Transformation.INSTANCE.getID();
        this.k = new d(this);
    }

    public static void a(e eVar) {
        if (eVar.j) {
            return;
        }
        Iterator it = eVar.getAugmentations().values().iterator();
        while (it.hasNext()) {
            eVar.a((Augmentation) it.next());
        }
        eVar.getHost().postDelayed(30L, eVar.k);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.TrackedObjectAugmenter
    public final void correctViewPositions(Transformation transformation) {
        Intrinsics.checkNotNullParameter(transformation, "transformation");
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.TrackedObjectAugmenter
    public final String getTrackingId(Object obj) {
        TrackedBarcode trackedBarcode = (TrackedBarcode) obj;
        Intrinsics.checkNotNullParameter(trackedBarcode, "<this>");
        return String.valueOf(trackedBarcode.getIdentifier());
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.TrackedObjectAugmenter
    public final void onAugmentationAdded(Augmentation augmentation) {
        Intrinsics.checkNotNullParameter(augmentation, "augmentation");
        Transformation.INSTANCE.getID();
        ViewGroup containerView = augmentation.getContainerView();
        if (containerView != null) {
            containerView.addOnLayoutChangeListener(new c(new b(this, augmentation)));
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.TrackedObjectAugmenter
    public final void onAugmentationRemoved(Augmentation augmentation) {
        Intrinsics.checkNotNullParameter(augmentation, "augmentation");
        Transformation.INSTANCE.getID();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.TrackedObjectAugmenter
    public final void onAugmentationUpdated(Augmentation augmentation) {
        Intrinsics.checkNotNullParameter(augmentation, "augmentation");
        Transformation.INSTANCE.getID();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.TrackedObjectAugmenter
    public final void onHostAttachedToWindow() {
        this.j = false;
        getHost().postDelayed(30L, this.k);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.TrackedObjectAugmenter
    public final void onHostDetachedFromWindow() {
        this.j = true;
    }

    public final void a(Augmentation augmentation) {
        ViewGroup containerView = augmentation.getContainerView();
        if (containerView == null) {
            return;
        }
        Point anchorPositionIgnoringLicense = ((TrackedBarcode) augmentation.getTrackedObject()).getA().getAnchorPositionIgnoringLicense(augmentation.getAnchor());
        TrackedObjectAugmenter.Host host = getHost();
        Intrinsics.checkNotNull(anchorPositionIgnoringLicense);
        Point calculateViewTopLeft = getObjectOverlayUtils().calculateViewTopLeft(host.mapFramePointToView(anchorPositionIgnoringLicense), augmentation.getAndroidx.constraintlayout.core.motion.utils.TypedValues.CycleType.S_WAVE_OFFSET java.lang.String(), ObjectOverlayUtilsKt.getSize(containerView));
        containerView.setX(calculateViewTopLeft.getX());
        containerView.setY(calculateViewTopLeft.getY());
    }
}
