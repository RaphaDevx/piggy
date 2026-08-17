package com.scandit.datacapture.frameworks.core.frames.configuration;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: FramesHandlingConfiguration.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/frames/configuration/FramesHandlingConfiguration;", "", "isFileSystemCacheEnabled", "", "imageQuality", "", "autoRotateImages", "(ZIZ)V", "getAutoRotateImages", "()Z", "getImageQuality", "()I", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FramesHandlingConfiguration {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final boolean DEFAULT_AUTO_ROTATE_ENABLED = false;
    private static final int DEFAULT_IMAGE_QUALITY = 100;
    private static final boolean DEFAULT_IS_FILE_SYSTEM_CACHE_ENABLED = false;
    private final boolean autoRotateImages;
    private final int imageQuality;
    private final boolean isFileSystemCacheEnabled;

    @JvmStatic
    public static final FramesHandlingConfiguration create(String str) {
        return INSTANCE.create(str);
    }

    @JvmStatic
    public static final FramesHandlingConfiguration getDefaultConfig() {
        return INSTANCE.getDefaultConfig();
    }

    public FramesHandlingConfiguration(boolean z, int i, boolean z2) {
        this.isFileSystemCacheEnabled = z;
        this.imageQuality = i;
        this.autoRotateImages = z2;
    }

    /* renamed from: isFileSystemCacheEnabled, reason: from getter */
    public final boolean getIsFileSystemCacheEnabled() {
        return this.isFileSystemCacheEnabled;
    }

    public final int getImageQuality() {
        return this.imageQuality;
    }

    public final boolean getAutoRotateImages() {
        return this.autoRotateImages;
    }

    /* compiled from: FramesHandlingConfiguration.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\b\u0010\f\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/frames/configuration/FramesHandlingConfiguration$Companion;", "", "()V", "DEFAULT_AUTO_ROTATE_ENABLED", "", "DEFAULT_IMAGE_QUALITY", "", "DEFAULT_IS_FILE_SYSTEM_CACHE_ENABLED", "create", "Lcom/scandit/datacapture/frameworks/core/frames/configuration/FramesHandlingConfiguration;", "contextCreationJson", "", "getDefaultConfig", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FramesHandlingConfiguration create(String contextCreationJson) {
            Intrinsics.checkNotNullParameter(contextCreationJson, "contextCreationJson");
            JSONObject optJSONObject = new JSONObject(contextCreationJson).optJSONObject("settings");
            JSONObject jSONObject = (optJSONObject == null || !optJSONObject.has("frameDataSettings")) ? null : optJSONObject.getJSONObject("frameDataSettings");
            if (jSONObject != null) {
                return new FramesHandlingConfiguration(jSONObject.optBoolean("sc_frame_isFileSystemCacheEnabled", false), jSONObject.optInt("sc_frame_imageQuality", 100), jSONObject.optBoolean("sc_frame_autoRotate", false));
            }
            return getDefaultConfig();
        }

        @JvmStatic
        public final FramesHandlingConfiguration getDefaultConfig() {
            return new FramesHandlingConfiguration(false, 100, false);
        }
    }
}
