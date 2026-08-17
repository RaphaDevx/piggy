package com.scandit.datacapture.frameworks.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraPositionUtilsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: CameraDefaults.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB5\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "cameraSettingsDefaults", "", "", "", CameraDefaults.FIELD_DEFAULT_POSITION, CameraDefaults.FIELD_AVAILABLE_POSITIONS, "", "Lcom/scandit/datacapture/core/source/CameraPosition;", "(Ljava/util/Map;Ljava/lang/String;Ljava/util/List;)V", "toMap", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CameraDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_AVAILABLE_POSITIONS = "availablePositions";
    private static final String FIELD_CAMERA_SETTINGS_DEFAULTS = "Settings";
    private static final String FIELD_DEFAULT_POSITION = "defaultPosition";
    private final List<CameraPosition> availablePositions;
    private final Map<String, Object> cameraSettingsDefaults;
    private final String defaultPosition;

    public /* synthetic */ CameraDefaults(Map map, String str, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, str, list);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CameraDefaults(Map<String, ? extends Object> map, String str, List<? extends CameraPosition> list) {
        this.cameraSettingsDefaults = map;
        this.defaultPosition = str;
        this.availablePositions = list;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        Pair[] pairArr = new Pair[3];
        pairArr[0] = TuplesKt.to(FIELD_CAMERA_SETTINGS_DEFAULTS, this.cameraSettingsDefaults);
        pairArr[1] = TuplesKt.to(FIELD_DEFAULT_POSITION, this.defaultPosition);
        List<CameraPosition> list = this.availablePositions;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(CameraPositionUtilsKt.toJson((CameraPosition) it.next()));
        }
        pairArr[2] = TuplesKt.to(FIELD_AVAILABLE_POSITIONS, arrayList);
        return MapsKt.mapOf(pairArr);
    }

    /* compiled from: CameraDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraDefaults$Companion;", "", "()V", "FIELD_AVAILABLE_POSITIONS", "", "FIELD_CAMERA_SETTINGS_DEFAULTS", "FIELD_DEFAULT_POSITION", "get", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            CameraPosition position;
            CameraPosition[] cameraPositionArr = new CameraPosition[2];
            DefaultConstructorMarker defaultConstructorMarker = null;
            Camera camera$default = Camera.Companion.getCamera$default(Camera.INSTANCE, CameraPosition.USER_FACING, null, 2, null);
            cameraPositionArr[0] = camera$default != null ? camera$default.getPosition() : null;
            Camera camera$default2 = Camera.Companion.getCamera$default(Camera.INSTANCE, CameraPosition.WORLD_FACING, null, 2, null);
            cameraPositionArr[1] = camera$default2 != null ? camera$default2.getPosition() : null;
            List listOfNotNull = CollectionsKt.listOfNotNull((Object[]) cameraPositionArr);
            Map<String, Object> map = CameraSettingsDefaults.INSTANCE.get();
            Camera defaultCamera$default = Camera.Companion.getDefaultCamera$default(Camera.INSTANCE, null, 1, null);
            return new CameraDefaults(map, (defaultCamera$default == null || (position = defaultCamera$default.getPosition()) == null) ? null : CameraPositionUtilsKt.toJson(position), listOfNotNull, defaultConstructorMarker).toMap();
        }
    }
}
