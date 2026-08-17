package com.scandit.datacapture.barcode.internal.module.capture;

import com.scandit.datacapture.barcode.data.Checksum;
import com.scandit.datacapture.barcode.data.Symbology;
import com.snapchat.djinni.NativeObjectManager;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSymbologySettings {

    public static final class CppProxy extends NativeSymbologySettings {
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

        private native HashSet<Short> native_getActiveSymbolCounts(long j);

        private native EnumSet<Checksum> native_getChecksumBits(long j);

        private native HashSet<String> native_getEnabledExtensions(long j);

        private native String native_getOcrFallbackRegex(long j);

        private native Symbology native_getSymbology(long j);

        private native boolean native_isColorInvertedEnabled(long j);

        private native boolean native_isEnabled(long j);

        private native boolean native_isExtensionEnabled(long j, String str);

        private native void native_setActiveSymbolCounts(long j, HashSet<Short> hashSet);

        private native void native_setChecksumBits(long j, EnumSet<Checksum> enumSet);

        private native void native_setColorInvertedEnabled(long j, boolean z);

        private native void native_setEnabled(long j, boolean z);

        private native void native_setExtensionEnabled(long j, String str, boolean z);

        private native void native_setOcrFallbackRegex(long j, String str);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public HashSet<Short> getActiveSymbolCounts() {
            return native_getActiveSymbolCounts(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public EnumSet<Checksum> getChecksumBits() {
            return native_getChecksumBits(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public HashSet<String> getEnabledExtensions() {
            return native_getEnabledExtensions(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public String getOcrFallbackRegex() {
            return native_getOcrFallbackRegex(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public Symbology getSymbology() {
            return native_getSymbology(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public boolean isColorInvertedEnabled() {
            return native_isColorInvertedEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public boolean isExtensionEnabled(String str) {
            return native_isExtensionEnabled(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public void setActiveSymbolCounts(HashSet<Short> hashSet) {
            native_setActiveSymbolCounts(this.nativeRef, hashSet);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public void setChecksumBits(EnumSet<Checksum> enumSet) {
            native_setChecksumBits(this.nativeRef, enumSet);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public void setColorInvertedEnabled(boolean z) {
            native_setColorInvertedEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public void setExtensionEnabled(String str, boolean z) {
            native_setExtensionEnabled(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public void setOcrFallbackRegex(String str) {
            native_setOcrFallbackRegex(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract HashSet<Short> getActiveSymbolCounts();

    public abstract EnumSet<Checksum> getChecksumBits();

    public abstract HashSet<String> getEnabledExtensions();

    public abstract String getOcrFallbackRegex();

    public abstract Symbology getSymbology();

    public abstract boolean isColorInvertedEnabled();

    public abstract boolean isEnabled();

    public abstract boolean isExtensionEnabled(String str);

    public abstract void setActiveSymbolCounts(HashSet<Short> hashSet);

    public abstract void setChecksumBits(EnumSet<Checksum> enumSet);

    public abstract void setColorInvertedEnabled(boolean z);

    public abstract void setEnabled(boolean z);

    public abstract void setExtensionEnabled(String str, boolean z);

    public abstract void setOcrFallbackRegex(String str);

    public abstract String toJson();
}
