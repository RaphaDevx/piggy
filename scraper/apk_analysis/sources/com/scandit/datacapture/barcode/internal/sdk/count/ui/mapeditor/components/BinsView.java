package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.extensions.PointExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.tealium.library.DataSources;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 &2\u00020\u0001:\u0007&'()*+,B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0015H\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0017¢\u0006\u0004\b\u001c\u0010\u001dR$\u0010%\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006-"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView;", "Landroid/widget/FrameLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Grid;", "grid", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Settings;", "settings", "", "initialize", "(Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Grid;Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Settings;)V", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "(II)V", "Landroid/graphics/Canvas;", "canvas", "onDraw", "(Landroid/graphics/Canvas;)V", "Landroid/view/MotionEvent;", "event", "", "onTouchEvent", "(Landroid/view/MotionEvent;)Z", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$EditListener;", "p", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$EditListener;", "getOnEditListener", "()Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$EditListener;", "setOnEditListener", "(Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$EditListener;)V", "onEditListener", "Companion", "EditListener", "com/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/a", "Grid", "Item", "Settings", "TouchedElement", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BinsView extends FrameLayout {
    public static final float BIN_LABEL_Z_INDEX_DP = 5.0f;
    public static final int DEFAULT_SUB_BACKGROUND_COLOR = -13204481;
    public static final int DEFAULT_TOTE_BACKGROUND_COLOR = -15710303;
    public static final int FONT_SIZE_MINI_SP = 10;
    public static final int FONT_SIZE_SP = 12;
    public static final float GRABBED_VIEW_ALPHA = 0.5f;
    public static final float GRABBED_VIEW_ELEVATION_DP = 4.0f;
    public static final int LABEL_HEIGHT_DP = 20;
    public static final int LABEL_HORIZONTAL_PADDING_DP = 8;
    public static final int LABEL_VERTICAL_PADDING_DP = 2;
    public static final int LABEL_WIDTH_DP = 80;
    public static final String POSITION_TOKEN = "{position}";
    public static final float SUB_RADIUS_DP = 12.0f;
    public static final float SUB_SIZE_DP = 66.0f;
    public static final float SUB_X_DP = 16.0f;
    public static final float SUB_Y_DP = 30.0f;
    public static final int TOTE_HEIGHT_DP = 120;
    public static final float TOTE_RADIUS_DP = 16.0f;
    public static final int TOTE_SPACING_DP = 20;
    public static final int TOTE_WIDTH_DP = 150;
    public static final int UI_ELEMENT_ON_DARK_COLOR = -1972243;
    public static final int UI_ELEMENT_ON_LIGHT_COLOR = -15134680;
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final float f;
    private final float g;
    private final float h;
    private final RectF i;
    private final RectF j;
    private final BinDrawable k;
    private List l;
    private final SparseArray m;
    private final SparseArray n;
    private Grid o;

    /* renamed from: p, reason: from kotlin metadata */
    private EditListener onEditListener;
    private TouchedElement q;
    private Integer r;
    private float s;
    private float t;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$EditListener;", "", "onSubSwapped", "", "fromIndex", "", "toIndex", "onToteSwapped", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface EditListener {
        void onSubSwapped(int fromIndex, int toIndex);

        void onToteSwapped(int fromIndex, int toIndex);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u000f\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R\u001f\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Grid;", "", "", "rows", "columns", "", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Item;", "items", "<init>", "(IILjava/util/List;)V", FirebaseAnalytics.Param.INDEX, "itemAtIndex", "(I)Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Item;", "fromIndex", "toIndex", "", "swapTote", "(II)V", "swapSub", "a", "I", "getRows", "()I", "b", "getColumns", "d", "Ljava/util/List;", "getItems", "()Ljava/util/List;", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Grid {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* renamed from: a, reason: from kotlin metadata */
        private final int rows;

        /* renamed from: b, reason: from kotlin metadata */
        private final int columns;
        private final List c;

        /* renamed from: d, reason: from kotlin metadata */
        private final List items;

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\r\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Grid$Companion;", "", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Grid;", "emptyGrid", "()Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Grid;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
        public static final class Companion {
            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }

            public final Grid emptyGrid() {
                return new Grid(0, 0, CollectionsKt.emptyList());
            }
        }

        public Grid(int i, int i2, List<Item> items) {
            Intrinsics.checkNotNullParameter(items, "items");
            this.rows = i;
            this.columns = i2;
            List mutableList = CollectionsKt.toMutableList((Collection) items);
            this.c = mutableList;
            this.items = mutableList;
        }

        public final int getColumns() {
            return this.columns;
        }

        public final List<Item> getItems() {
            return this.items;
        }

        public final int getRows() {
            return this.rows;
        }

        public final Item itemAtIndex(int index) {
            return (Item) this.items.get(index);
        }

        public final void swapSub(int fromIndex, int toIndex) {
            Item item;
            Item item2 = (Item) this.c.get(fromIndex);
            if (item2 == null || (item = (Item) this.c.get(toIndex)) == null) {
                return;
            }
            this.c.set(fromIndex, new Item(item2.getToteText(), item.getSubText()));
            this.c.set(toIndex, new Item(item.getToteText(), item2.getSubText()));
        }

        public final void swapTote(int fromIndex, int toIndex) {
            Item item = (Item) this.c.get(fromIndex);
            List list = this.c;
            list.set(fromIndex, list.get(toIndex));
            this.c.set(toIndex, item);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Item;", "", "", "toteText", "subText", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "a", "Ljava/lang/String;", "getToteText", "()Ljava/lang/String;", "b", "getSubText", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Item {

        /* renamed from: a, reason: from kotlin metadata */
        private final String toteText;

        /* renamed from: b, reason: from kotlin metadata */
        private final String subText;

        public Item(String toteText, String str) {
            Intrinsics.checkNotNullParameter(toteText, "toteText");
            this.toteText = toteText;
            this.subText = str;
        }

        public final String getSubText() {
            return this.subText;
        }

        public final String getToteText() {
            return this.toteText;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$Settings;", "", "", "toteTextFormat", "", "toteColor", "subColor", "<init>", "(Ljava/lang/String;II)V", "a", "Ljava/lang/String;", "getToteTextFormat", "()Ljava/lang/String;", "b", "I", "getToteColor", "()I", "c", "getSubColor", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Settings {

        /* renamed from: a, reason: from kotlin metadata */
        private final String toteTextFormat;

        /* renamed from: b, reason: from kotlin metadata */
        private final int toteColor;

        /* renamed from: c, reason: from kotlin metadata */
        private final int subColor;

        public Settings(String toteTextFormat, int i, int i2) {
            Intrinsics.checkNotNullParameter(toteTextFormat, "toteTextFormat");
            this.toteTextFormat = toteTextFormat;
            this.toteColor = i;
            this.subColor = i2;
        }

        public final int getSubColor() {
            return this.subColor;
        }

        public final int getToteColor() {
            return this.toteColor;
        }

        public final String getToteTextFormat() {
            return this.toteTextFormat;
        }
    }

    public interface TouchedElement {

        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$TouchedElement$Nothing;", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$TouchedElement;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
        public static final class Nothing implements TouchedElement {
            public static final Nothing INSTANCE = new Nothing();

            private Nothing() {
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$TouchedElement$SubHandle;", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$TouchedElement;", "", FirebaseAnalytics.Param.INDEX, "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/SubView;", "subView", "<init>", "(ILcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/SubView;)V", "a", "I", "getIndex", "()I", "b", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/SubView;", "getSubView", "()Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/SubView;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
        public static final class SubHandle implements TouchedElement {

            /* renamed from: a, reason: from kotlin metadata */
            private final int index;

            /* renamed from: b, reason: from kotlin metadata */
            private final SubView subView;

            public SubHandle(int i, SubView subView) {
                Intrinsics.checkNotNullParameter(subView, "subView");
                this.index = i;
                this.subView = subView;
            }

            public final int getIndex() {
                return this.index;
            }

            public final SubView getSubView() {
                return this.subView;
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$TouchedElement$ToteHandle;", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinsView$TouchedElement;", "", FirebaseAnalytics.Param.INDEX, "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/ToteView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "<init>", "(ILcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/ToteView;)V", "a", "I", "getIndex", "()I", "b", "Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/ToteView;", "getView", "()Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/ToteView;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
        public static final class ToteHandle implements TouchedElement {

            /* renamed from: a, reason: from kotlin metadata */
            private final int index;

            /* renamed from: b, reason: from kotlin metadata */
            private final ToteView view;

            public ToteHandle(int i, ToteView view) {
                Intrinsics.checkNotNullParameter(view, "view");
                this.index = i;
                this.view = view;
            }

            public final int getIndex() {
                return this.index;
            }

            public final ToteView getView() {
                return this.view;
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BinsView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void a(final SubView subView, int i, final Function0 function0) {
        int columns = i % this.o.getColumns();
        int columns2 = i / this.o.getColumns();
        float x = subView.getX();
        float y = subView.getY();
        int i2 = this.a;
        int i3 = this.c;
        float f = ((i2 + i3) * columns) + this.f;
        float f2 = ((this.b + i3) * columns2) + this.g;
        final a aVar = new a(x, f);
        final a aVar2 = new a(y, f2);
        final a aVar3 = new a(subView.getElevation(), 0.0f);
        final a aVar4 = new a(subView.getAlpha(), 1.0f);
        float distance = PointExtensionsKt.distance(new Point(x, y), new Point(f, f2));
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        long coerceIn = RangesKt.coerceIn((long) PixelExtensionsKt.dpFromPx(distance, context), 100L, 250L);
        Object tag = subView.getTag();
        ValueAnimator valueAnimator = tag instanceof ValueAnimator ? (ValueAnimator) tag : null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(coerceIn);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                BinsView.a(ofFloat, subView, aVar, aVar2, aVar3, aVar4, valueAnimator2);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView$animateSubViewToPosition$3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                animation.removeAllListeners();
                ValueAnimator valueAnimator2 = ofFloat;
                Intrinsics.checkNotNull(valueAnimator2, "null cannot be cast to non-null type android.animation.ValueAnimator");
                valueAnimator2.removeAllUpdateListeners();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                function0.invoke();
                subView.setTag(null);
                animation.removeAllListeners();
                ValueAnimator valueAnimator2 = ofFloat;
                Intrinsics.checkNotNull(valueAnimator2, "null cannot be cast to non-null type android.animation.ValueAnimator");
                valueAnimator2.removeAllUpdateListeners();
            }
        });
        subView.setTag(ofFloat);
        ofFloat.start();
    }

    public static final void access$displayToteSubs(BinsView binsView) {
        com.scandit.datacapture.barcode.internal.module.extensions.c.a(binsView.n, d.a);
        com.scandit.datacapture.barcode.internal.module.extensions.c.a(binsView.m, e.a);
    }

    public static final void access$swapSubIndex(BinsView binsView, int i, int i2) {
        ToteView toteView = (ToteView) binsView.m.get(i);
        ToteView toteView2 = (ToteView) binsView.m.get(i2);
        String subBarcode = toteView.getSubBarcode();
        toteView.setSubBarcode(toteView2.getSubBarcode());
        toteView2.setSubBarcode(subBarcode);
        SubView subView = (SubView) binsView.n.get(i);
        SubView subView2 = (SubView) binsView.n.get(i2);
        binsView.n.put(i2, subView);
        binsView.n.put(i, subView2);
        binsView.o.swapSub(i, i2);
        EditListener editListener = binsView.onEditListener;
        if (editListener != null) {
            editListener.onSubSwapped(i, i2);
        }
    }

    public static final void access$swapToteIndex(BinsView binsView, int i, int i2) {
        ToteView toteView = (ToteView) binsView.m.get(i);
        ToteView toteView2 = (ToteView) binsView.m.get(i2);
        binsView.m.put(i2, toteView);
        binsView.m.put(i, toteView2);
        SubView subView = (SubView) binsView.n.get(i);
        SubView subView2 = (SubView) binsView.n.get(i2);
        binsView.n.put(i2, subView);
        binsView.n.put(i, subView2);
        binsView.o.swapTote(i, i2);
        EditListener editListener = binsView.onEditListener;
        if (editListener != null) {
            editListener.onToteSwapped(i, i2);
        }
    }

    public final EditListener getOnEditListener() {
        return this.onEditListener;
    }

    public final void initialize(Grid grid, Settings settings) {
        String subText;
        Intrinsics.checkNotNullParameter(grid, "grid");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.o = grid;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            removeView((TextView) it.next());
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int pxFromDp = PixelExtensionsKt.pxFromDp(80, context);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        int pxFromDp2 = PixelExtensionsKt.pxFromDp(20, context2);
        int i = (this.a - pxFromDp) / 2;
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        float pxFromDp3 = PixelExtensionsKt.pxFromDp(5.0f, context3);
        int i2 = 0;
        IntRange until = RangesKt.until(0, a());
        int i3 = 10;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it2 = until.iterator();
        while (it2.hasNext()) {
            int nextInt = ((IntIterator) it2).nextInt();
            int columns = nextInt % this.o.getColumns();
            int columns2 = nextInt / this.o.getColumns();
            TextView textView = new TextView(getContext());
            textView.setGravity(17);
            int i4 = this.e;
            textView.setPadding(i4, i2, i4, i2);
            textView.setAutoSizeTextTypeUniformWithConfiguration(i3, 12, 1, 2);
            i2 = 0;
            textView.setTypeface(Typeface.create(null, 600, false));
            textView.setSingleLine();
            String toteTextFormat = settings.getToteTextFormat();
            String valueOf = String.valueOf(nextInt + 1);
            Iterator<Integer> it3 = it2;
            textView.setText(StringsKt.contains$default((CharSequence) toteTextFormat, (CharSequence) POSITION_TOKEN, false, 2, (Object) null) ? StringsKt.trim((CharSequence) StringsKt.replace$default(toteTextFormat, POSITION_TOKEN, valueOf, false, 4, (Object) null)).toString() : StringsKt.trim((CharSequence) toteTextFormat).toString() + ' ' + valueOf);
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(pxFromDp, pxFromDp2);
            textView.setX(((this.a + this.c) * columns) + i);
            textView.setY((this.b + this.c) * columns2);
            textView.setZ(pxFromDp3);
            addView(textView, layoutParams);
            arrayList.add(textView);
            it2 = it3;
            i3 = 10;
        }
        this.l = arrayList;
        com.scandit.datacapture.barcode.internal.module.extensions.c.a(this.m, new h(this));
        com.scandit.datacapture.barcode.internal.module.extensions.c.a(this.n, new i(this));
        this.m.clear();
        this.n.clear();
        int a = a();
        for (int i5 = i2; i5 < a; i5++) {
            int columns3 = i5 % this.o.getColumns();
            int columns4 = i5 / this.o.getColumns();
            Item itemAtIndex = this.o.itemAtIndex(i5);
            if (itemAtIndex != null) {
                SparseArray sparseArray = this.m;
                Context context4 = getContext();
                Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
                ToteView toteView = new ToteView(context4, null, 0, 6, null);
                toteView.setToteColor(settings.getToteColor());
                toteView.setSubColor(settings.getSubColor());
                toteView.setMainBarcode(itemAtIndex.getToteText());
                toteView.setSubBarcode(itemAtIndex.getSubText());
                ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(this.a, this.b);
                toteView.setX((this.a + this.c) * columns3);
                toteView.setY((this.b + this.c) * columns4);
                addView(toteView, layoutParams2);
                Unit unit = Unit.INSTANCE;
                sparseArray.put(i5, toteView);
            }
        }
        int a2 = a();
        while (i2 < a2) {
            Item itemAtIndex2 = this.o.itemAtIndex(i2);
            if (itemAtIndex2 != null && (subText = itemAtIndex2.getSubText()) != null && subText.length() != 0) {
                SparseArray sparseArray2 = this.n;
                Context context5 = getContext();
                Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
                SubView subView = new SubView(context5, null, 0, 6, null);
                subView.setColor(settings.getSubColor());
                subView.setText(itemAtIndex2.getSubText());
                subView.setAlpha(0.0f);
                int i6 = this.d;
                addView(subView, new FrameLayout.LayoutParams(i6, i6));
                Unit unit2 = Unit.INSTANCE;
                sparseArray2.put(i2, subView);
            }
            i2++;
        }
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int a = a();
        for (int i = 0; i < a; i++) {
            int columns = i % this.o.getColumns();
            int columns2 = i / this.o.getColumns();
            canvas.save();
            int i2 = this.a;
            int i3 = this.c;
            canvas.translate(columns * (i2 + i3), columns2 * (this.b + i3));
            this.k.draw(canvas);
            canvas.restore();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(((this.o.getColumns() - 1) * this.c) + (this.o.getColumns() * this.a), ((this.o.getRows() - 1) * this.c) + (this.o.getRows() * this.b));
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x029e, code lost:
    
        if ((r0 instanceof com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView.TouchedElement.Nothing) != false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0013, code lost:
    
        if (r0 != 3) goto L111;
     */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0202  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instructions count: 687
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void setOnEditListener(EditListener editListener) {
        this.onEditListener = editListener;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BinsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ BinsView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BinsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        int pxFromDp = PixelExtensionsKt.pxFromDp(150, context);
        this.a = pxFromDp;
        int pxFromDp2 = PixelExtensionsKt.pxFromDp(120, context);
        this.b = pxFromDp2;
        this.c = PixelExtensionsKt.pxFromDp(20, context);
        int pxFromDp3 = (int) PixelExtensionsKt.pxFromDp(66.0f, context);
        this.d = pxFromDp3;
        this.e = PixelExtensionsKt.pxFromDp(8, context);
        float pxFromDp4 = PixelExtensionsKt.pxFromDp(16.0f, context);
        this.f = pxFromDp4;
        float pxFromDp5 = PixelExtensionsKt.pxFromDp(30.0f, context);
        this.g = pxFromDp5;
        this.h = PixelExtensionsKt.pxFromDp(4.0f, context);
        this.i = new RectF(0.0f, 0.0f, pxFromDp, pxFromDp2);
        float f = pxFromDp3;
        this.j = new RectF(pxFromDp4, pxFromDp5, pxFromDp4 + f, f + pxFromDp5);
        BinDrawable binDrawable = new BinDrawable(context);
        binDrawable.setBounds(0, 0, pxFromDp, pxFromDp2);
        this.k = binDrawable;
        this.l = CollectionsKt.emptyList();
        this.m = new SparseArray();
        this.n = new SparseArray();
        this.o = Grid.INSTANCE.emptyGrid();
        setWillNotDraw(false);
        setClipChildren(false);
        setClipToPadding(false);
        this.q = TouchedElement.Nothing.INSTANCE;
    }

    private final void a(final ToteView toteView, int i, final Function0 function0) {
        int columns = i % this.o.getColumns();
        int columns2 = i / this.o.getColumns();
        float x = toteView.getX();
        float y = toteView.getY();
        int i2 = this.a;
        int i3 = this.c;
        float f = (i2 + i3) * columns;
        float f2 = (this.b + i3) * columns2;
        final a aVar = new a(x, f);
        final a aVar2 = new a(y, f2);
        final a aVar3 = new a(toteView.getElevation(), 0.0f);
        final a aVar4 = new a(toteView.getAlpha(), 1.0f);
        float distance = PointExtensionsKt.distance(new Point(x, y), new Point(f, f2));
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        long coerceIn = RangesKt.coerceIn((long) PixelExtensionsKt.dpFromPx(distance, context), 100L, 250L);
        Object tag = toteView.getTag();
        ValueAnimator valueAnimator = tag instanceof ValueAnimator ? (ValueAnimator) tag : null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(coerceIn);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                BinsView.a(ofFloat, toteView, aVar, aVar2, aVar3, aVar4, valueAnimator2);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView$animateToteViewToPosition$3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                animation.removeAllListeners();
                ValueAnimator valueAnimator2 = ofFloat;
                Intrinsics.checkNotNull(valueAnimator2, "null cannot be cast to non-null type android.animation.ValueAnimator");
                valueAnimator2.removeAllUpdateListeners();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                function0.invoke();
                toteView.setTag(null);
                animation.removeAllListeners();
                ValueAnimator valueAnimator2 = ofFloat;
                Intrinsics.checkNotNull(valueAnimator2, "null cannot be cast to non-null type android.animation.ValueAnimator");
                valueAnimator2.removeAllUpdateListeners();
            }
        });
        toteView.setTag(ofFloat);
        ofFloat.start();
    }

    private final int a() {
        return this.o.getColumns() * this.o.getRows();
    }

    private final Integer a(FrameLayout frameLayout) {
        int width = (frameLayout.getWidth() / 2) + ((int) frameLayout.getX());
        int height = (frameLayout.getHeight() / 2) + ((int) frameLayout.getY());
        int i = this.c;
        int i2 = i / 2;
        int i3 = (width + i2) / (this.a + i);
        int i4 = (i2 + height) / (this.b + i);
        if (i3 < 0 || i3 >= this.o.getColumns() || i4 < 0 || i4 >= this.o.getRows()) {
            return null;
        }
        return Integer.valueOf((this.o.getColumns() * i4) + i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(ValueAnimator valueAnimator, ToteView view, a xTransition, a yTransition, a elevationTransition, a alphaTransition, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(xTransition, "$xTransition");
        Intrinsics.checkNotNullParameter(yTransition, "$yTransition");
        Intrinsics.checkNotNullParameter(elevationTransition, "$elevationTransition");
        Intrinsics.checkNotNullParameter(alphaTransition, "$alphaTransition");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        view.setX(xTransition.a(floatValue));
        view.setY(yTransition.a(floatValue));
        view.setElevation(elevationTransition.a(floatValue));
        view.setAlpha(alphaTransition.a(floatValue));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(ValueAnimator valueAnimator, SubView view, a xTransition, a yTransition, a elevationTransition, a alphaTransition, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(xTransition, "$xTransition");
        Intrinsics.checkNotNullParameter(yTransition, "$yTransition");
        Intrinsics.checkNotNullParameter(elevationTransition, "$elevationTransition");
        Intrinsics.checkNotNullParameter(alphaTransition, "$alphaTransition");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        view.setX(xTransition.a(floatValue));
        view.setY(yTransition.a(floatValue));
        view.setElevation(elevationTransition.a(floatValue));
        view.setAlpha(alphaTransition.a(floatValue));
    }
}
