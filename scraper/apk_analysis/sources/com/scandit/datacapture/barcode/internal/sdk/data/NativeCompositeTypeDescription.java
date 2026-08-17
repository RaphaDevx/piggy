package com.scandit.datacapture.barcode.internal.sdk.data;

import com.scandit.datacapture.barcode.data.CompositeType;
import com.scandit.datacapture.barcode.data.Symbology;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeCompositeTypeDescription {

    public static final class CppProxy extends NativeCompositeTypeDescription {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean a = new AtomicBoolean(false);
        private final long nativeRef;

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);

        private native HashSet<Symbology> native_getSymbologies(long j);

        private native EnumSet<CompositeType> native_getTypesBits(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeCompositeTypeDescription
        public HashSet<Symbology> getSymbologies() {
            return native_getSymbologies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeCompositeTypeDescription
        public EnumSet<CompositeType> getTypesBits() {
            return native_getTypesBits(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeCompositeTypeDescription
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native ArrayList<NativeCompositeTypeDescription> all();

    public static native NativeCompositeTypeDescription create(EnumSet<CompositeType> enumSet);

    public abstract HashSet<Symbology> getSymbologies();

    public abstract EnumSet<CompositeType> getTypesBits();

    public abstract String toJson();
}
