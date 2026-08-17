package com.scandit.datacapture.core.json;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.MarginsWithUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b \u0010!J\u001f\u0010\"\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\nH\u0016¢\u0006\u0004\b$\u0010\fJ\u0017\u0010%\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b%\u0010&J\u001f\u0010'\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0016¢\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020\u0016H\u0016¢\u0006\u0004\b)\u0010\u0018J\u0017\u0010*\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b*\u0010\u001aJ\u001f\u0010+\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0016H\u0016¢\u0006\u0004\b+\u0010\u001cJ#\u0010,\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0004\b,\u0010-J\u000f\u0010/\u001a\u00020.H\u0016¢\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020.2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b1\u00102J\u001f\u00103\u001a\u00020.2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020.H\u0016¢\u0006\u0004\b3\u00104J\u000f\u00106\u001a\u000205H\u0016¢\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u0002052\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b8\u00109J\u001f\u0010:\u001a\u0002052\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u000205H\u0016¢\u0006\u0004\b:\u0010;J\u000f\u0010=\u001a\u00020<H\u0016¢\u0006\u0004\b=\u0010>J\u0017\u0010?\u001a\u00020<2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b?\u0010@J\u001f\u0010A\u001a\u00020<2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020<H\u0016¢\u0006\u0004\bA\u0010BJ\u000f\u0010D\u001a\u00020CH\u0016¢\u0006\u0004\bD\u0010EJ\u0017\u0010F\u001a\u00020C2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\bF\u0010GJ\u001f\u0010H\u001a\u00020C2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020CH\u0016¢\u0006\u0004\bH\u0010IJ\u000f\u0010K\u001a\u00020JH\u0016¢\u0006\u0004\bK\u0010LJ\u0017\u0010M\u001a\u00020J2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\bM\u0010NJ#\u0010O\u001a\u0004\u0018\u00010J2\u0006\u0010\u0010\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010JH\u0016¢\u0006\u0004\bO\u0010PJ\u0017\u0010R\u001a\u00020J2\u0006\u0010Q\u001a\u00020\u0016H\u0016¢\u0006\u0004\bR\u0010SJ\u000f\u0010T\u001a\u00020JH\u0016¢\u0006\u0004\bT\u0010LJ\u0017\u0010U\u001a\u00020J2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\bU\u0010NJ#\u0010V\u001a\u0004\u0018\u00010J2\u0006\u0010\u0010\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010JH\u0016¢\u0006\u0004\bV\u0010PJ\u0018\u0010W\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\nH\u0096\u0002¢\u0006\u0004\bW\u0010\u0012J\u0017\u0010X\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\bX\u0010\u0012J\u0017\u0010Y\u001a\u00020J2\u0006\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\bY\u0010NJ\u001d\u0010]\u001a\u00020\\2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\n0ZH\u0016¢\u0006\u0004\b]\u0010^J\u0015\u0010_\u001a\b\u0012\u0004\u0012\u00020\n0ZH\u0016¢\u0006\u0004\b_\u0010`R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\ba\u0010b\u001a\u0004\bc\u0010dR\u0014\u0010h\u001a\u00020e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0014\u0010j\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bi\u0010\fR\u0014\u0010l\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bk\u0010\u000f¨\u0006m"}, d2 = {"Lcom/scandit/datacapture/core/json/JsonValueProxyAdapter;", "Lcom/scandit/datacapture/core/json/JsonValueProxy;", "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "_NativeJsonValue", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "", "jsonString", "()Ljava/lang/String;", "", "asBoolean", "()Z", "key", "requireByKeyAsBoolean", "(Ljava/lang/String;)Z", "defaultValue", "getByKeyAsBoolean", "(Ljava/lang/String;Z)Z", "", "asInt", "()I", "requireByKeyAsInt", "(Ljava/lang/String;)I", "getByKeyAsInt", "(Ljava/lang/String;I)I", "", "asFloat", "()F", "requireByKeyAsFloat", "(Ljava/lang/String;)F", "getByKeyAsFloat", "(Ljava/lang/String;F)F", "asString", "requireByKeyAsString", "(Ljava/lang/String;)Ljava/lang/String;", "getByKeyAsString", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "asColor", "requireByKeyAsColor", "getByKeyAsColor", "getByKeyAsNullableColor", "(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;", "Lcom/scandit/datacapture/core/ui/style/Brush;", "asBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "requireByKeyAsBrush", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/ui/style/Brush;", "getByKeyAsBrush", "(Ljava/lang/String;Lcom/scandit/datacapture/core/ui/style/Brush;)Lcom/scandit/datacapture/core/ui/style/Brush;", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "asFloatWithUnit", "()Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "requireByKeyAsFloatWithUnit", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "getByKeyAsFloatWithUnit", "(Ljava/lang/String;Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;)Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "asPointWithUnit", "()Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "requireByKeyAsPointWithUnit", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "getByKeyAsPointWithUnit", "(Ljava/lang/String;Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;)Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "asMarginsWithUnit", "()Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "requireByKeyAsMarginsWithUnit", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "getByKeyAsMarginsWithUnit", "(Ljava/lang/String;Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;)Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "Lcom/scandit/datacapture/core/json/JsonValue;", "asArray", "()Lcom/scandit/datacapture/core/json/JsonValue;", "requireByKeyAsArray", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/json/JsonValue;", "getByKeyAsArray", "(Ljava/lang/String;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/core/json/JsonValue;", FirebaseAnalytics.Param.INDEX, "requireByIndex", "(I)Lcom/scandit/datacapture/core/json/JsonValue;", "asObject", "requireByKeyAsObject", "getByKeyAsObject", "contains", "containsNull", "requireByKey", "Ljava/util/ArrayList;", "keys", "", "_removeKeys", "(Ljava/util/ArrayList;)V", "_getWarnings", "()Ljava/util/ArrayList;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "getSize", "()J", RRWebVideoEvent.JsonKeys.SIZE, "getAbsolutePath", "absolutePath", "getUsed", "used", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class JsonValueProxyAdapter implements JsonValueProxy {
    private final NativeJsonValue a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public JsonValueProxyAdapter(NativeJsonValue _NativeJsonValue, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeJsonValue, "_NativeJsonValue");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeJsonValue;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public ArrayList<String> _getWarnings() {
        ArrayList<String> warnings = this.a.getWarnings();
        Intrinsics.checkNotNull(warnings);
        return warnings;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    /* renamed from: _impl, reason: from getter */
    public NativeJsonValue getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public void _removeKeys(ArrayList<String> keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        this.a.removeKeys(keys);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public JsonValue asArray() {
        NativeJsonValue asArray = this.a.asArray();
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeJsonValue.class);
        Intrinsics.checkNotNull(asArray);
        return (JsonValue) proxyCache.getOrPut(orCreateKotlinClass, null, asArray, new a(asArray));
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public boolean asBoolean() {
        return this.a.asBool();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public Brush asBrush() {
        NativeBrush asBrush = this.a.asBrush();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(asBrush);
        return coreNativeTypeFactory.convert(asBrush);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public int asColor() {
        NativeColor asColor = this.a.asColor();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(asColor);
        return coreNativeTypeFactory.convert(asColor);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public float asFloat() {
        return this.a.asFloat();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public FloatWithUnit asFloatWithUnit() {
        FloatWithUnit asFloatWithUnit = this.a.asFloatWithUnit();
        Intrinsics.checkNotNull(asFloatWithUnit);
        return asFloatWithUnit;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public int asInt() {
        return this.a.asInt();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public MarginsWithUnit asMarginsWithUnit() {
        MarginsWithUnit asMarginsWithUnit = this.a.asMarginsWithUnit();
        Intrinsics.checkNotNull(asMarginsWithUnit);
        return asMarginsWithUnit;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public JsonValue asObject() {
        NativeJsonValue asObject = this.a.asObject();
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeJsonValue.class);
        Intrinsics.checkNotNull(asObject);
        return (JsonValue) proxyCache.getOrPut(orCreateKotlinClass, null, asObject, new b(asObject));
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public PointWithUnit asPointWithUnit() {
        PointWithUnit asPointWithUnit = this.a.asPointWithUnit();
        Intrinsics.checkNotNull(asPointWithUnit);
        return asPointWithUnit;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public String asString() {
        String asString = this.a.asString();
        Intrinsics.checkNotNull(asString);
        return asString;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public boolean contains(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.contains(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public boolean containsNull(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.containsNull(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public String getAbsolutePath() {
        String absolutePath = this.a.getAbsolutePath();
        Intrinsics.checkNotNull(absolutePath);
        return absolutePath;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public JsonValue getByKeyAsArray(String key, JsonValue defaultValue) {
        NativeJsonValue nativeJsonValue;
        Intrinsics.checkNotNullParameter(key, "key");
        if (defaultValue != null) {
            nativeJsonValue = defaultValue.getA();
            this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, nativeJsonValue, defaultValue);
        } else {
            nativeJsonValue = null;
        }
        NativeJsonValue arrayForKeyOrDefault = this.a.getArrayForKeyOrDefault(key, nativeJsonValue);
        if (arrayForKeyOrDefault != null) {
            return (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, arrayForKeyOrDefault, new c(arrayForKeyOrDefault));
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public boolean getByKeyAsBoolean(String key, boolean defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getBoolForKeyOrDefault(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public Brush getByKeyAsBrush(String key, Brush defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        NativeBrush brushForKeyOrDefault = this.a.getBrushForKeyOrDefault(key, coreNativeTypeFactory.convert(defaultValue));
        Intrinsics.checkNotNull(brushForKeyOrDefault);
        return coreNativeTypeFactory.convert(brushForKeyOrDefault);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public int getByKeyAsColor(String key, int defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        NativeColor colorForKeyOrDefault = this.a.getColorForKeyOrDefault(key, coreNativeTypeFactory.convert(defaultValue));
        Intrinsics.checkNotNull(colorForKeyOrDefault);
        return coreNativeTypeFactory.convert(colorForKeyOrDefault);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public float getByKeyAsFloat(String key, float defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getFloatForKeyOrDefault(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public FloatWithUnit getByKeyAsFloatWithUnit(String key, FloatWithUnit defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        FloatWithUnit floatWithUnitForKeyOrDefault = this.a.getFloatWithUnitForKeyOrDefault(key, defaultValue);
        Intrinsics.checkNotNull(floatWithUnitForKeyOrDefault);
        return floatWithUnitForKeyOrDefault;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public int getByKeyAsInt(String key, int defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getIntForKeyOrDefault(key, defaultValue);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public MarginsWithUnit getByKeyAsMarginsWithUnit(String key, MarginsWithUnit defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        MarginsWithUnit marginsWithUnitForKeyOrDefault = this.a.getMarginsWithUnitForKeyOrDefault(key, defaultValue);
        Intrinsics.checkNotNull(marginsWithUnitForKeyOrDefault);
        return marginsWithUnitForKeyOrDefault;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public Integer getByKeyAsNullableColor(String key, Integer defaultValue) {
        NativeColor nativeColor;
        Intrinsics.checkNotNullParameter(key, "key");
        if (defaultValue != null) {
            nativeColor = CoreNativeTypeFactory.INSTANCE.convert(defaultValue.intValue());
        } else {
            nativeColor = null;
        }
        NativeColor optionalColorForKeyOrDefault = this.a.getOptionalColorForKeyOrDefault(key, nativeColor);
        if (optionalColorForKeyOrDefault != null) {
            return Integer.valueOf(CoreNativeTypeFactory.INSTANCE.convert(optionalColorForKeyOrDefault));
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public JsonValue getByKeyAsObject(String key, JsonValue defaultValue) {
        NativeJsonValue nativeJsonValue;
        Intrinsics.checkNotNullParameter(key, "key");
        if (defaultValue != null) {
            nativeJsonValue = defaultValue.getA();
            this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, nativeJsonValue, defaultValue);
        } else {
            nativeJsonValue = null;
        }
        NativeJsonValue objectForKeyOrDefault = this.a.getObjectForKeyOrDefault(key, nativeJsonValue);
        if (objectForKeyOrDefault != null) {
            return (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, objectForKeyOrDefault, new d(objectForKeyOrDefault));
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public PointWithUnit getByKeyAsPointWithUnit(String key, PointWithUnit defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        PointWithUnit pointWithUnitForKeyOrDefault = this.a.getPointWithUnitForKeyOrDefault(key, defaultValue);
        Intrinsics.checkNotNull(pointWithUnitForKeyOrDefault);
        return pointWithUnitForKeyOrDefault;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public String getByKeyAsString(String key, String defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        String stringForKeyOrDefault = this.a.getStringForKeyOrDefault(key, defaultValue);
        Intrinsics.checkNotNull(stringForKeyOrDefault);
        return stringForKeyOrDefault;
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public long getSize() {
        return this.a.size();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public boolean getUsed() {
        return this.a.isUsed();
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public String jsonString() {
        String nativeJsonValue = this.a.toString();
        Intrinsics.checkNotNullExpressionValue(nativeJsonValue, "toString(...)");
        return nativeJsonValue;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public JsonValue requireByIndex(int index) {
        NativeJsonValue forIndex = this.a.getForIndex(index);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeJsonValue.class);
        Intrinsics.checkNotNull(forIndex);
        return (JsonValue) proxyCache.getOrPut(orCreateKotlinClass, null, forIndex, new e(forIndex));
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public JsonValue requireByKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        NativeJsonValue forKey = this.a.getForKey(key);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeJsonValue.class);
        Intrinsics.checkNotNull(forKey);
        return (JsonValue) proxyCache.getOrPut(orCreateKotlinClass, null, forKey, new f(forKey));
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public JsonValue requireByKeyAsArray(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        NativeJsonValue arrayForKey = this.a.getArrayForKey(key);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeJsonValue.class);
        Intrinsics.checkNotNull(arrayForKey);
        return (JsonValue) proxyCache.getOrPut(orCreateKotlinClass, null, arrayForKey, new g(arrayForKey));
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public boolean requireByKeyAsBoolean(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getBoolForKey(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public Brush requireByKeyAsBrush(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        NativeBrush brushForKey = this.a.getBrushForKey(key);
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(brushForKey);
        return coreNativeTypeFactory.convert(brushForKey);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public int requireByKeyAsColor(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        NativeColor colorForKey = this.a.getColorForKey(key);
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(colorForKey);
        return coreNativeTypeFactory.convert(colorForKey);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public float requireByKeyAsFloat(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getFloatForKey(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public FloatWithUnit requireByKeyAsFloatWithUnit(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        FloatWithUnit floatWithUnitForKey = this.a.getFloatWithUnitForKey(key);
        Intrinsics.checkNotNull(floatWithUnitForKey);
        return floatWithUnitForKey;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public int requireByKeyAsInt(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.getIntForKey(key);
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public MarginsWithUnit requireByKeyAsMarginsWithUnit(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        MarginsWithUnit marginsWithUnitForKey = this.a.getMarginsWithUnitForKey(key);
        Intrinsics.checkNotNull(marginsWithUnitForKey);
        return marginsWithUnitForKey;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public JsonValue requireByKeyAsObject(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        NativeJsonValue objectForKey = this.a.getObjectForKey(key);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeJsonValue.class);
        Intrinsics.checkNotNull(objectForKey);
        return (JsonValue) proxyCache.getOrPut(orCreateKotlinClass, null, objectForKey, new h(objectForKey));
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public PointWithUnit requireByKeyAsPointWithUnit(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        PointWithUnit pointWithUnitForKey = this.a.getPointWithUnitForKey(key);
        Intrinsics.checkNotNull(pointWithUnitForKey);
        return pointWithUnitForKey;
    }

    @Override // com.scandit.datacapture.core.json.JsonValueProxy
    public String requireByKeyAsString(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        String stringForKey = this.a.getStringForKey(key);
        Intrinsics.checkNotNull(stringForKey);
        return stringForKey;
    }

    public /* synthetic */ JsonValueProxyAdapter(NativeJsonValue nativeJsonValue, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeJsonValue, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
