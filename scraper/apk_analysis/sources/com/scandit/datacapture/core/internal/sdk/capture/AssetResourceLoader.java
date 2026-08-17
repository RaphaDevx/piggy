package com.scandit.datacapture.core.internal.sdk.capture;

import android.content.res.AssetManager;
import com.scandit.datacapture.core.internal.module.utils.i;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\n\u001a\u00020\t2\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/capture/AssetResourceLoader;", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeResourceLoader;", "Landroid/content/res/AssetManager;", "assets", "<init>", "(Landroid/content/res/AssetManager;)V", "", "Lcom/scandit/datacapture/core/internal/sdk/capture/ResourceId;", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "load", "(Ljava/lang/String;)[B", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class AssetResourceLoader extends NativeResourceLoader {
    private final AssetManager a;

    public AssetResourceLoader(AssetManager assets) {
        Intrinsics.checkNotNullParameter(assets, "assets");
        this.a = assets;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeResourceLoader
    public byte[] load(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        try {
            InputStream open = this.a.open(identifier);
            try {
                Intrinsics.checkNotNull(open);
                byte[] readBytes = ByteStreamsKt.readBytes(open);
                CloseableKt.closeFinally(open, null);
                return readBytes;
            } finally {
            }
        } catch (Exception unused) {
            i.a("Failed to load assets `" + identifier + "`.");
            return new byte[0];
        }
    }
}
