package com.scandit.datacapture.core.internal.sdk.source;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;
import com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraSettings;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J$\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0016\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\r\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/source/CameraFactory;", "", "getCamera", "Lcom/scandit/datacapture/core/source/Camera;", "position", "Lcom/scandit/datacapture/core/source/CameraPosition;", "settings", "Lcom/scandit/datacapture/core/source/CameraSettings;", "getCameraDelegate", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraDelegate;", "api", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraApi;", "getDefaultCamera", "getNativeCameraApi", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface CameraFactory {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static /* synthetic */ Camera getCamera$default(CameraFactory cameraFactory, CameraPosition cameraPosition, CameraSettings cameraSettings, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCamera");
            }
            if ((i & 2) != 0) {
                cameraSettings = null;
            }
            return cameraFactory.getCamera(cameraPosition, cameraSettings);
        }

        public static /* synthetic */ Camera getDefaultCamera$default(CameraFactory cameraFactory, CameraSettings cameraSettings, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getDefaultCamera");
            }
            if ((i & 1) != 0) {
                cameraSettings = null;
            }
            return cameraFactory.getDefaultCamera(cameraSettings);
        }
    }

    Camera getCamera(CameraPosition position, CameraSettings settings);

    NativeCameraDelegate getCameraDelegate(CameraPosition position, NativeCameraApi api, CameraSettings settings);

    Camera getDefaultCamera(CameraSettings settings);

    NativeCameraApi getNativeCameraApi(CameraSettings settings);
}
