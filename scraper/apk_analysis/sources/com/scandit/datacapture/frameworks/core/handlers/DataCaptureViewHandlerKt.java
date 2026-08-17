package com.scandit.datacapture.frameworks.core.handlers;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataCaptureViewHandler.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"findFirstOfType", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;", "(Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;)Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "scandit-datacapture-frameworks-core_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DataCaptureViewHandlerKt {
    public static final /* synthetic */ <T extends DataCaptureOverlay> T findFirstOfType(DataCaptureViewHandler dataCaptureViewHandler) {
        Intrinsics.checkNotNullParameter(dataCaptureViewHandler, "<this>");
        FrameworksDataCaptureView topmostDataCaptureView = dataCaptureViewHandler.getTopmostDataCaptureView();
        Object obj = null;
        if (topmostDataCaptureView == null) {
            return null;
        }
        Iterator<T> it = topmostDataCaptureView.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (((DataCaptureOverlay) next) instanceof DataCaptureOverlay) {
                obj = next;
                break;
            }
        }
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) obj;
    }
}
