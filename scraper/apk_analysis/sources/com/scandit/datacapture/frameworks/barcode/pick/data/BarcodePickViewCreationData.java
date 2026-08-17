package com.scandit.datacapture.frameworks.barcode.pick.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodePickViewCreationData.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0016\u0018\u0000 %2\u00020\u0001:\u0002%&Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014¨\u0006'"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData;", "", "modeJson", "", "viewJson", "barcodeProviderJson", "viewIsStarted", "", BarcodePickViewCreationData.VIEW_HAS_LISTENERS, BarcodePickViewCreationData.VIEW_HAS_UI_LISTENERS, "hasScanningListener", "highlightType", "Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData$HighlightType;", BarcodePickViewCreationData.HIGHLIGHT_STYLE_HAS_PROVIDER_KEY, "hasModeListener", "hasViewActionListener", BarcodePickViewCreationData.VIEW_ID_KEY, "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZLcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData$HighlightType;ZZZI)V", "getBarcodeProviderJson", "()Ljava/lang/String;", "getHasAsyncProvider", "()Z", "getHasModeListener", "getHasScanningListener", "getHasViewActionListener", "getHasViewListeners", "getHasViewUiListener", "getHighlightType", "()Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData$HighlightType;", "getModeJson", "getViewId", "()I", "setViewId", "(I)V", "getViewIsStarted", "getViewJson", "Companion", "HighlightType", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickViewCreationData {
    private static final String BARCODE_PROVIDER = "ProductProvider";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HIGHLIGHT_STYLE_HAS_PROVIDER_KEY = "hasAsyncProvider";
    private static final String HIGHLIGHT_STYLE_KEY = "highlightStyle";
    private static final String HIGHLIGHT_STYLE_TYPE_KEY = "type";
    private static final String MODE_HAS_LISTENERS_KEY = "hasListeners";
    private static final String MODE_KEY = "BarcodePick";
    private static final String VIEW_HAS_ACTION_LISTENERS_KEY = "hasActionListeners";
    private static final String VIEW_HAS_LISTENERS = "hasViewListeners";
    private static final String VIEW_HAS_SCANNING_LISTENERS = "hasScanningListeners";
    private static final String VIEW_HAS_STARTED = "isStarted";
    private static final String VIEW_HAS_UI_LISTENERS = "hasViewUiListener";
    private static final String VIEW_ID_KEY = "viewId";
    private static final String VIEW_KEY = "View";
    private static final String VIEW_SETTINGS_KEY = "viewSettings";
    private final String barcodeProviderJson;
    private final boolean hasAsyncProvider;
    private final boolean hasModeListener;
    private final boolean hasScanningListener;
    private final boolean hasViewActionListener;
    private final boolean hasViewListeners;
    private final boolean hasViewUiListener;
    private final HighlightType highlightType;
    private final String modeJson;
    private int viewId;
    private final boolean viewIsStarted;
    private final String viewJson;

    public BarcodePickViewCreationData(String modeJson, String viewJson, String barcodeProviderJson, boolean z, boolean z2, boolean z3, boolean z4, HighlightType highlightType, boolean z5, boolean z6, boolean z7, int i) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Intrinsics.checkNotNullParameter(barcodeProviderJson, "barcodeProviderJson");
        Intrinsics.checkNotNullParameter(highlightType, "highlightType");
        this.modeJson = modeJson;
        this.viewJson = viewJson;
        this.barcodeProviderJson = barcodeProviderJson;
        this.viewIsStarted = z;
        this.hasViewListeners = z2;
        this.hasViewUiListener = z3;
        this.hasScanningListener = z4;
        this.highlightType = highlightType;
        this.hasAsyncProvider = z5;
        this.hasModeListener = z6;
        this.hasViewActionListener = z7;
        this.viewId = i;
    }

    public /* synthetic */ BarcodePickViewCreationData(String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, HighlightType highlightType, boolean z5, boolean z6, boolean z7, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, z, z2, z3, z4, highlightType, z5, z6, z7, (i2 & 2048) != 0 ? -1 : i);
    }

    public final String getModeJson() {
        return this.modeJson;
    }

    public final String getViewJson() {
        return this.viewJson;
    }

    public final String getBarcodeProviderJson() {
        return this.barcodeProviderJson;
    }

    public final boolean getViewIsStarted() {
        return this.viewIsStarted;
    }

    public final boolean getHasViewListeners() {
        return this.hasViewListeners;
    }

    public final boolean getHasViewUiListener() {
        return this.hasViewUiListener;
    }

    public final boolean getHasScanningListener() {
        return this.hasScanningListener;
    }

    public final HighlightType getHighlightType() {
        return this.highlightType;
    }

    public final boolean getHasAsyncProvider() {
        return this.hasAsyncProvider;
    }

    public final boolean getHasModeListener() {
        return this.hasModeListener;
    }

    public final boolean getHasViewActionListener() {
        return this.hasViewActionListener;
    }

    public final int getViewId() {
        return this.viewId;
    }

    public final void setViewId(int i) {
        this.viewId = i;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: BarcodePickViewCreationData.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData$HighlightType;", "", "(Ljava/lang/String;I)V", "NONE", "DOT", "DOT_WITH_ICONS", "RECTANGULAR", "RECTANGULAR_WITH_ICONS", "CUSTOM_VIEW", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class HighlightType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ HighlightType[] $VALUES;
        public static final HighlightType NONE = new HighlightType("NONE", 0);
        public static final HighlightType DOT = new HighlightType("DOT", 1);
        public static final HighlightType DOT_WITH_ICONS = new HighlightType("DOT_WITH_ICONS", 2);
        public static final HighlightType RECTANGULAR = new HighlightType("RECTANGULAR", 3);
        public static final HighlightType RECTANGULAR_WITH_ICONS = new HighlightType("RECTANGULAR_WITH_ICONS", 4);
        public static final HighlightType CUSTOM_VIEW = new HighlightType("CUSTOM_VIEW", 5);

        private static final /* synthetic */ HighlightType[] $values() {
            return new HighlightType[]{NONE, DOT, DOT_WITH_ICONS, RECTANGULAR, RECTANGULAR_WITH_ICONS, CUSTOM_VIEW};
        }

        public static EnumEntries<HighlightType> getEntries() {
            return $ENTRIES;
        }

        public static HighlightType valueOf(String str) {
            return (HighlightType) Enum.valueOf(HighlightType.class, str);
        }

        public static HighlightType[] values() {
            return (HighlightType[]) $VALUES.clone();
        }

        private HighlightType(String str, int i) {
        }

        static {
            HighlightType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* compiled from: BarcodePickViewCreationData.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData$Companion;", "", "()V", "BARCODE_PROVIDER", "", "HIGHLIGHT_STYLE_HAS_PROVIDER_KEY", "HIGHLIGHT_STYLE_KEY", "HIGHLIGHT_STYLE_TYPE_KEY", "MODE_HAS_LISTENERS_KEY", "MODE_KEY", "VIEW_HAS_ACTION_LISTENERS_KEY", "VIEW_HAS_LISTENERS", "VIEW_HAS_SCANNING_LISTENERS", "VIEW_HAS_STARTED", "VIEW_HAS_UI_LISTENERS", "VIEW_ID_KEY", "VIEW_KEY", "VIEW_SETTINGS_KEY", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData;", "viewJson", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        public final BarcodePickViewCreationData fromJson(String viewJson) {
            HighlightType highlightType;
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            if (!jSONObject.has(BarcodePickViewCreationData.MODE_KEY) || !jSONObject.has(BarcodePickViewCreationData.VIEW_KEY)) {
                throw new IllegalArgumentException("Unable to create a BarcodePickView. The given json doesn't contain all the required fields. Required fields = [BarcodePick, View]");
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(BarcodePickViewCreationData.VIEW_KEY);
            int optInt = jSONObject2.optInt(BarcodePickViewCreationData.VIEW_ID_KEY, -1);
            boolean optBoolean = jSONObject2.optBoolean(BarcodePickViewCreationData.VIEW_HAS_STARTED, false);
            boolean optBoolean2 = jSONObject2.optBoolean(BarcodePickViewCreationData.VIEW_HAS_LISTENERS, false);
            boolean optBoolean3 = jSONObject2.optBoolean(BarcodePickViewCreationData.VIEW_HAS_UI_LISTENERS, false);
            boolean optBoolean4 = jSONObject2.optBoolean(BarcodePickViewCreationData.VIEW_HAS_SCANNING_LISTENERS, false);
            boolean optBoolean5 = jSONObject2.optBoolean(BarcodePickViewCreationData.VIEW_HAS_ACTION_LISTENERS_KEY, false);
            JSONObject jSONObject3 = jSONObject2.getJSONObject(BarcodePickViewCreationData.VIEW_SETTINGS_KEY).getJSONObject(BarcodePickViewCreationData.HIGHLIGHT_STYLE_KEY);
            String string = jSONObject3.getString("type");
            if (string != null) {
                switch (string.hashCode()) {
                    case -1581639242:
                        if (string.equals("customView")) {
                            highlightType = HighlightType.CUSTOM_VIEW;
                            break;
                        }
                        break;
                    case -467383426:
                        if (string.equals("rectangular")) {
                            highlightType = HighlightType.RECTANGULAR;
                            break;
                        }
                        break;
                    case -294602069:
                        if (string.equals("dotWithIcons")) {
                            highlightType = HighlightType.DOT_WITH_ICONS;
                            break;
                        }
                        break;
                    case -247390186:
                        if (string.equals("rectangularWithIcons")) {
                            highlightType = HighlightType.RECTANGULAR_WITH_ICONS;
                            break;
                        }
                        break;
                    case 99657:
                        if (string.equals("dot")) {
                            highlightType = HighlightType.DOT;
                            break;
                        }
                        break;
                }
                boolean optBoolean6 = jSONObject3.optBoolean(BarcodePickViewCreationData.HIGHLIGHT_STYLE_HAS_PROVIDER_KEY, false);
                JSONObject jSONObject4 = jSONObject.getJSONObject(BarcodePickViewCreationData.MODE_KEY);
                boolean optBoolean7 = jSONObject4.optBoolean(BarcodePickViewCreationData.MODE_HAS_LISTENERS_KEY, false);
                String jSONObject5 = jSONObject4.toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject5, "toString(...)");
                String jSONObject6 = jSONObject2.toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject6, "toString(...)");
                String string2 = jSONObject4.getString(BarcodePickViewCreationData.BARCODE_PROVIDER);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                return new BarcodePickViewCreationData(jSONObject5, jSONObject6, string2, optBoolean, optBoolean2, optBoolean3, optBoolean4, highlightType, optBoolean6, optBoolean7, optBoolean5, optInt);
            }
            highlightType = HighlightType.NONE;
            boolean optBoolean62 = jSONObject3.optBoolean(BarcodePickViewCreationData.HIGHLIGHT_STYLE_HAS_PROVIDER_KEY, false);
            JSONObject jSONObject42 = jSONObject.getJSONObject(BarcodePickViewCreationData.MODE_KEY);
            boolean optBoolean72 = jSONObject42.optBoolean(BarcodePickViewCreationData.MODE_HAS_LISTENERS_KEY, false);
            String jSONObject52 = jSONObject42.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject52, "toString(...)");
            String jSONObject62 = jSONObject2.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject62, "toString(...)");
            String string22 = jSONObject42.getString(BarcodePickViewCreationData.BARCODE_PROVIDER);
            Intrinsics.checkNotNullExpressionValue(string22, "getString(...)");
            return new BarcodePickViewCreationData(jSONObject52, jSONObject62, string22, optBoolean, optBoolean2, optBoolean3, optBoolean4, highlightType, optBoolean62, optBoolean72, optBoolean5, optInt);
        }
    }
}
