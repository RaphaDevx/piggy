package com.scandit.datacapture.core.internal.sdk.source;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class NativeBitmapConfig {
    public static final NativeBitmapConfig ARGB_8888;
    private static final /* synthetic */ NativeBitmapConfig[] a;

    static {
        NativeBitmapConfig nativeBitmapConfig = new NativeBitmapConfig();
        ARGB_8888 = nativeBitmapConfig;
        a = new NativeBitmapConfig[]{nativeBitmapConfig};
    }

    private NativeBitmapConfig() {
    }

    public static NativeBitmapConfig valueOf(String str) {
        return (NativeBitmapConfig) Enum.valueOf(NativeBitmapConfig.class, str);
    }

    public static NativeBitmapConfig[] values() {
        return (NativeBitmapConfig[]) a.clone();
    }
}
