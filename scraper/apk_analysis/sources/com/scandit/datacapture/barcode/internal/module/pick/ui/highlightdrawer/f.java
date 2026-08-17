package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer;

import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal;
import com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession;
import com.scandit.datacapture.barcode.internal.module.pick.capture.v;
import com.scandit.datacapture.barcode.internal.module.pick.capture.w;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.o;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.DataCaptureViewSizeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class f implements BarcodePickListener, DataCaptureViewSizeListener {
    public final BarcodePick a;
    public final Function1 b;
    public final Function1 c;
    public final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k d;
    public final BarcodePickDrawer e;

    public f(BarcodePick mode, o runOnMain, com.scandit.datacapture.barcode.pick.ui.m onDrawerDataUpdatedWithStatusIconsShown, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k elementsRequestor, BarcodePickDrawer highlightDrawer) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(runOnMain, "runOnMain");
        Intrinsics.checkNotNullParameter(onDrawerDataUpdatedWithStatusIconsShown, "onDrawerDataUpdatedWithStatusIconsShown");
        Intrinsics.checkNotNullParameter(elementsRequestor, "elementsRequestor");
        Intrinsics.checkNotNullParameter(highlightDrawer, "highlightDrawer");
        this.a = mode;
        this.b = runOnMain;
        this.c = onDrawerDataUpdatedWithStatusIconsShown;
        this.d = elementsRequestor;
        this.e = highlightDrawer;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void a(BarcodePickInternal mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void b(BarcodePickInternal mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewSizeListener
    public final void onSizeChanged(DataCaptureView view, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(view, "view");
        NativeBarcodePickSession session = this.a._impl$scandit_barcode_capture().getSession();
        if (session == null) {
            return;
        }
        a(new v(new b(session)));
    }

    public final void a(v vVar) {
        HashSet<String> toPickObjects = ((w) vVar.b.getValue()).a.getToPickObjects();
        Intrinsics.checkNotNull(toPickObjects);
        Set set = CollectionsKt.toSet(toPickObjects);
        HashSet<String> pickedObjects = ((w) vVar.b.getValue()).a.getPickedObjects();
        Intrinsics.checkNotNull(pickedObjects);
        Set set2 = CollectionsKt.toSet(pickedObjects);
        HashSet<String> undefinedObjects = ((w) vVar.b.getValue()).a.getUndefinedObjects();
        Intrinsics.checkNotNull(undefinedObjects);
        Set set3 = CollectionsKt.toSet(undefinedObjects);
        ArrayList<NativeTrackedObject> addedTrackedObjects = ((w) vVar.b.getValue()).a.getAddedTrackedObjects();
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(addedTrackedObjects);
        List<TrackedObject> convertNativeTrackedObjectList = barcodeNativeTypeFactory.convertNativeTrackedObjectList(addedTrackedObjects);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(convertNativeTrackedObjectList, 10)), 16));
        for (Object obj : convertNativeTrackedObjectList) {
            linkedHashMap.put(obj, a(((TrackedObject) obj).getData(), set2, set, set3));
        }
        ArrayList<NativeTrackedObject> updatedTrackedObjects = ((w) vVar.b.getValue()).a.getUpdatedTrackedObjects();
        BarcodeNativeTypeFactory barcodeNativeTypeFactory2 = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(updatedTrackedObjects);
        List<TrackedObject> convertNativeTrackedObjectList2 = barcodeNativeTypeFactory2.convertNativeTrackedObjectList(updatedTrackedObjects);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(convertNativeTrackedObjectList2, 10)), 16));
        for (Object obj2 : convertNativeTrackedObjectList2) {
            linkedHashMap2.put(obj2, a(((TrackedObject) obj2).getData(), set2, set, set3));
        }
        ArrayList<Integer> removedTrackedObjects = ((w) vVar.b.getValue()).a.getRemovedTrackedObjects();
        Intrinsics.checkNotNull(removedTrackedObjects);
        List list = CollectionsKt.toList(removedTrackedObjects);
        HashMap<Integer, NativeTrackedObject> filteredTrackedObjects = ((w) vVar.b.getValue()).a.getFilteredTrackedObjects();
        BarcodeNativeTypeFactory barcodeNativeTypeFactory3 = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(filteredTrackedObjects);
        List list2 = CollectionsKt.toList(barcodeNativeTypeFactory3.convertTrackedObjectNativeMap(filteredTrackedObjects).values());
        if (linkedHashMap.isEmpty() && linkedHashMap2.isEmpty() && list.isEmpty() && list2.isEmpty()) {
            return;
        }
        this.b.invoke(new e(linkedHashMap, linkedHashMap2, list, this, list2));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void b(BarcodePickInternal mode, v session, FrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        a(session);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void a(BarcodePickInternal mode, v session, FrameData frameData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        a(session);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void a(BarcodePickInternal mode, Map requestedData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(requestedData, "requestedData");
        v c = mode.c();
        for (Map.Entry entry : requestedData.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k kVar = this.d;
            HashSet<String> pickedObjects = ((w) c.b.getValue()).a.getPickedObjects();
            Intrinsics.checkNotNull(pickedObjects);
            HashSet<String> toPickObjects = ((w) c.b.getValue()).a.getToPickObjects();
            Intrinsics.checkNotNull(toPickObjects);
            HashSet<String> undefinedObjects = ((w) c.b.getValue()).a.getUndefinedObjects();
            Intrinsics.checkNotNull(undefinedObjects);
            kVar.a(str, str2, a(str, pickedObjects, toPickObjects, undefinedObjects), new c(this, c));
        }
        a(c);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void a(BarcodePickInternal mode, String id, boolean z) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(id, "id");
        this.d.a(id);
        NativeBarcodePickSession session = this.a._impl$scandit_barcode_capture().getSession();
        if (session == null) {
            return;
        }
        a(new v(new d(session)));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void a(TrackedObject trackedObject) {
        this.d.b(trackedObject != null ? trackedObject.getData() : null);
        NativeBarcodePickSession session = this.a._impl$scandit_barcode_capture().getSession();
        if (session == null) {
            return;
        }
        a(new v(new d(session)));
    }

    public static BarcodePickState a(String str, Set set, Set set2, Set set3) {
        if (str == null) {
            return BarcodePickState.UNKNOWN;
        }
        return set2.contains(str) ? BarcodePickState.TO_PICK : set.contains(str) ? BarcodePickState.PICKED : set3.contains(str) ? BarcodePickState.UNKNOWN : BarcodePickState.IGNORE;
    }
}
