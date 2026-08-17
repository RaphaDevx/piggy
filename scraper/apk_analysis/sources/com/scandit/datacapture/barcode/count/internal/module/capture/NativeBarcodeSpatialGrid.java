package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.count.capture.Coordinate2d;
import com.scandit.djinni.ResultOrError;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeSpatialGrid {

    public static final class CppProxy extends NativeBarcodeSpatialGrid {
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

        private native ArrayList<NativeBarcodeSpatialGridElement> native_column(long j, int i);

        private native int native_columns(long j);

        private native ArrayList<Coordinate2d> native_coordinatesForElement(long j, NativeBarcodeSpatialGridElement nativeBarcodeSpatialGridElement);

        private native NativeBarcodeSpatialGridElement native_elementAt(long j, int i, int i2);

        private native ArrayList<NativeBarcodeSpatialGridElement> native_row(long j, int i);

        private native int native_rows(long j);

        private native ResultOrError<Boolean, String> native_swapElementsAtCoordinates(long j, int i, int i2, int i3, int i4);

        private native ResultOrError<Boolean, String> native_swapSubsAtCoordinates(long j, int i, int i2, int i3, int i4);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public ArrayList<NativeBarcodeSpatialGridElement> column(int i) {
            return native_column(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public int columns() {
            return native_columns(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public ArrayList<Coordinate2d> coordinatesForElement(NativeBarcodeSpatialGridElement nativeBarcodeSpatialGridElement) {
            return native_coordinatesForElement(this.nativeRef, nativeBarcodeSpatialGridElement);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public NativeBarcodeSpatialGridElement elementAt(int i, int i2) {
            return native_elementAt(this.nativeRef, i, i2);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public ArrayList<NativeBarcodeSpatialGridElement> row(int i) {
            return native_row(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public int rows() {
            return native_rows(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public ResultOrError<Boolean, String> swapElementsAtCoordinates(int i, int i2, int i3, int i4) {
            return native_swapElementsAtCoordinates(this.nativeRef, i, i2, i3, i4);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public ResultOrError<Boolean, String> swapSubsAtCoordinates(int i, int i2, int i3, int i4) {
            return native_swapSubsAtCoordinates(this.nativeRef, i, i2, i3, i4);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract ArrayList<NativeBarcodeSpatialGridElement> column(int i);

    public abstract int columns();

    public abstract ArrayList<Coordinate2d> coordinatesForElement(NativeBarcodeSpatialGridElement nativeBarcodeSpatialGridElement);

    public abstract NativeBarcodeSpatialGridElement elementAt(int i, int i2);

    public abstract ArrayList<NativeBarcodeSpatialGridElement> row(int i);

    public abstract int rows();

    public abstract ResultOrError<Boolean, String> swapElementsAtCoordinates(int i, int i2, int i3, int i4);

    public abstract ResultOrError<Boolean, String> swapSubsAtCoordinates(int i, int i2, int i3, int i4);

    public abstract String toJson();
}
