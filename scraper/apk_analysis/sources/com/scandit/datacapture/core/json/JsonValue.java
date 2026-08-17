package com.scandit.datacapture.core.json;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.MarginsWithUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b$\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0019\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0014j\b\u0012\u0004\u0012\u00020\u0003`\u0015H\u0097\u0001J\t\u0010\u0016\u001a\u00020\u0006H\u0097\u0001J!\u0010\u0017\u001a\u00020\u00182\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0014j\b\u0012\u0004\u0012\u00020\u0003`\u0015H\u0097\u0001J\t\u0010\u001a\u001a\u00020\u0000H\u0097\u0001J\t\u0010\u001b\u001a\u00020\u0010H\u0097\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u0097\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u0097\u0001J\t\u0010 \u001a\u00020!H\u0097\u0001J\t\u0010\"\u001a\u00020#H\u0097\u0001J\t\u0010$\u001a\u00020\u001fH\u0097\u0001J\t\u0010%\u001a\u00020&H\u0097\u0001J\t\u0010'\u001a\u00020\u0000H\u0097\u0001J\t\u0010(\u001a\u00020)H\u0097\u0001J\t\u0010*\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u0003H\u0097\u0003J\u0011\u0010-\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u001d\u0010.\u001a\u0004\u0018\u00010\u00002\u0006\u0010,\u001a\u00020\u00032\b\u0010/\u001a\u0004\u0018\u00010\u0000H\u0097\u0001J\u0019\u00100\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u0010H\u0097\u0001J\u0019\u00101\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u001dH\u0097\u0001J\u0019\u00102\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u001fH\u0097\u0001J\u0019\u00103\u001a\u00020!2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020!H\u0097\u0001J\u0019\u00104\u001a\u00020#2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020#H\u0097\u0001J\u0019\u00105\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u001fH\u0097\u0001J\u0019\u00106\u001a\u00020&2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020&H\u0097\u0001J\"\u00107\u001a\u0004\u0018\u00010\u001f2\u0006\u0010,\u001a\u00020\u00032\b\u0010/\u001a\u0004\u0018\u00010\u001fH\u0097\u0001¢\u0006\u0002\u00108J!\u00109\u001a\u0004\u0018\u00010\u00032\u0006\u0010,\u001a\u00020\u00032\b\u0010/\u001a\u0004\u0018\u00010\u0003H\u0000¢\u0006\u0002\b:J\u001d\u0010;\u001a\u0004\u0018\u00010\u00002\u0006\u0010,\u001a\u00020\u00032\b\u0010/\u001a\u0004\u0018\u00010\u0000H\u0097\u0001J\u0019\u0010<\u001a\u00020)2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020)H\u0097\u0001J\u0019\u0010=\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u0003H\u0097\u0001J\t\u0010>\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u001fH\u0097\u0001J\u0011\u0010A\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010B\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010C\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010D\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010E\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010F\u001a\u00020!2\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010G\u001a\u00020#2\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010H\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010I\u001a\u00020&2\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010J\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010K\u001a\u00020)2\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010L\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0003H\u0097\u0001R\u0014\u0010\b\u001a\u00020\u00038WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006M"}, d2 = {"Lcom/scandit/datacapture/core/json/JsonValue;", "Lcom/scandit/datacapture/core/json/JsonValueProxy;", "jsonData", "", "(Ljava/lang/String;)V", "impl", "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "(Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "absolutePath", "getAbsolutePath", "()Ljava/lang/String;", RRWebVideoEvent.JsonKeys.SIZE, "", "getSize", "()J", "used", "", "getUsed", "()Z", "_getWarnings", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "_impl", "_removeKeys", "", "keys", "asArray", "asBoolean", "asBrush", "Lcom/scandit/datacapture/core/ui/style/Brush;", "asColor", "", "asFloat", "", "asFloatWithUnit", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "asInt", "asMarginsWithUnit", "Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "asObject", "asPointWithUnit", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "asString", "contains", "key", "containsNull", "getByKeyAsArray", "defaultValue", "getByKeyAsBoolean", "getByKeyAsBrush", "getByKeyAsColor", "getByKeyAsFloat", "getByKeyAsFloatWithUnit", "getByKeyAsInt", "getByKeyAsMarginsWithUnit", "getByKeyAsNullableColor", "(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;", "getByKeyAsNullableString", "getByKeyAsNullableString$scandit_capture_core", "getByKeyAsObject", "getByKeyAsPointWithUnit", "getByKeyAsString", "jsonString", "requireByIndex", FirebaseAnalytics.Param.INDEX, "requireByKey", "requireByKeyAsArray", "requireByKeyAsBoolean", "requireByKeyAsBrush", "requireByKeyAsColor", "requireByKeyAsFloat", "requireByKeyAsFloatWithUnit", "requireByKeyAsInt", "requireByKeyAsMarginsWithUnit", "requireByKeyAsObject", "requireByKeyAsPointWithUnit", "requireByKeyAsString", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@Mockable
/* loaded from: classes2.dex */
public final class JsonValue implements JsonValueProxy {
    private final /* synthetic */ JsonValueProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public JsonValue(NativeJsonValue impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new JsonValueProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getWarnings")
    public ArrayList<String> _getWarnings() {
        return this.a._getWarnings();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeJsonValue getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "removeKeys")
    public void _removeKeys(ArrayList<String> keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        this.a._removeKeys(keys);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public JsonValue asArray() {
        return this.a.asArray();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "asBool")
    public boolean asBoolean() {
        return this.a.asBoolean();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public Brush asBrush() {
        return this.a.asBrush();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public int asColor() {
        return this.a.asColor();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public float asFloat() {
        return this.a.asFloat();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public FloatWithUnit asFloatWithUnit() {
        return this.a.asFloatWithUnit();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public int asInt() {
        return this.a.asInt();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public MarginsWithUnit asMarginsWithUnit() {
        return this.a.asMarginsWithUnit();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public JsonValue asObject() {
        return this.a.asObject();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public PointWithUnit asPointWithUnit() {
        return this.a.asPointWithUnit();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public String asString() {
        return this.a.asString();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public boolean contains(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.contains(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction
    public boolean containsNull(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.containsNull(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(property = "absolutePath")
    public String getAbsolutePath() {
        return this.a.getAbsolutePath();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getArrayForKeyOrDefault")
    public JsonValue getByKeyAsArray(String key, JsonValue defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getByKeyAsArray(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getBoolForKeyOrDefault")
    public boolean getByKeyAsBoolean(String key, boolean defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getByKeyAsBoolean(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getBrushForKeyOrDefault")
    public Brush getByKeyAsBrush(String key, Brush defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return this.a.getByKeyAsBrush(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getColorForKeyOrDefault")
    public int getByKeyAsColor(String key, int defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getByKeyAsColor(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getFloatForKeyOrDefault")
    public float getByKeyAsFloat(String key, float defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getByKeyAsFloat(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getFloatWithUnitForKeyOrDefault")
    public FloatWithUnit getByKeyAsFloatWithUnit(String key, FloatWithUnit defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return this.a.getByKeyAsFloatWithUnit(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getIntForKeyOrDefault")
    public int getByKeyAsInt(String key, int defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getByKeyAsInt(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getMarginsWithUnitForKeyOrDefault")
    public MarginsWithUnit getByKeyAsMarginsWithUnit(String key, MarginsWithUnit defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return this.a.getByKeyAsMarginsWithUnit(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getOptionalColorForKeyOrDefault")
    public Integer getByKeyAsNullableColor(String key, Integer defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getByKeyAsNullableColor(key, defaultValue);
    }

    public final String getByKeyAsNullableString$scandit_capture_core(String key, String defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getA().getOptionalStringForKeyOrDefault(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getObjectForKeyOrDefault")
    public JsonValue getByKeyAsObject(String key, JsonValue defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getByKeyAsObject(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getPointWithUnitForKeyOrDefault")
    public PointWithUnit getByKeyAsPointWithUnit(String key, PointWithUnit defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return this.a.getByKeyAsPointWithUnit(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getStringForKeyOrDefault")
    public String getByKeyAsString(String key, String defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return this.a.getByKeyAsString(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = RRWebVideoEvent.JsonKeys.SIZE, property = RRWebVideoEvent.JsonKeys.SIZE)
    public long getSize() {
        return this.a.getSize();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "isUsed", property = "used")
    public boolean getUsed() {
        return this.a.getUsed();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "toString")
    public String jsonString() {
        return this.a.jsonString();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getForIndex")
    public JsonValue requireByIndex(int index) {
        return this.a.requireByIndex(index);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getForKey")
    public JsonValue requireByKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKey(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getArrayForKey")
    public JsonValue requireByKeyAsArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsArray(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getBoolForKey")
    public boolean requireByKeyAsBoolean(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsBoolean(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getBrushForKey")
    public Brush requireByKeyAsBrush(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsBrush(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getColorForKey")
    public int requireByKeyAsColor(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsColor(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getFloatForKey")
    public float requireByKeyAsFloat(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsFloat(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getFloatWithUnitForKey")
    public FloatWithUnit requireByKeyAsFloatWithUnit(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsFloatWithUnit(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getIntForKey")
    public int requireByKeyAsInt(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsInt(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getMarginsWithUnitForKey")
    public MarginsWithUnit requireByKeyAsMarginsWithUnit(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsMarginsWithUnit(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getObjectForKey")
    public JsonValue requireByKeyAsObject(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsObject(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getPointWithUnitForKey")
    public PointWithUnit requireByKeyAsPointWithUnit(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsPointWithUnit(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    @ProxyFunction(nativeName = "getStringForKey")
    public String requireByKeyAsString(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.requireByKeyAsString(key);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public JsonValue(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "jsonData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue r2 = com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue.fromString(r2)
            java.lang.String r0 = "fromString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.json.JsonValue.<init>(java.lang.String):void");
    }
}
