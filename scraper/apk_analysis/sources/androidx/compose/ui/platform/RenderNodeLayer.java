package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.ExperimentalComposeUiApi;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tealium.library.DataSources;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RenderNodeLayer.android.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0001[B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\u0010\nJ\b\u0010+\u001a\u00020\u0007H\u0016J\u0010\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u0006H\u0016J\b\u0010.\u001a\u00020\u0007H\u0016J\u001d\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u000201H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00103J\u0018\u00104\u001a\u00020\u00072\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0010H\u0016J%\u00108\u001a\u0002012\u0006\u00109\u001a\u0002012\u0006\u00107\u001a\u00020\u0010H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b:\u0010;J\u001d\u0010<\u001a\u00020\u00072\u0006\u00100\u001a\u00020=H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b>\u0010?J\u001d\u0010@\u001a\u00020\u00072\u0006\u0010A\u001a\u00020BH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u0010?J\b\u0010D\u001a\u00020\u0007H\u0002J\b\u0010E\u001a\u00020\u0007H\u0016J\u008d\u0001\u0010F\u001a\u00020\u00072\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020H2\u0006\u0010K\u001a\u00020H2\u0006\u0010L\u001a\u00020H2\u0006\u0010M\u001a\u00020H2\u0006\u0010N\u001a\u00020H2\u0006\u0010O\u001a\u00020H2\u0006\u0010P\u001a\u00020H2\u0006\u0010Q\u001a\u00020H2\u0006\u0010(\u001a\u00020)2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u00102\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020XH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bY\u0010ZR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u00198VX\u0097\u0004¢\u0006\f\u0012\u0004\b#\u0010$\u001a\u0004\b%\u0010\u001bR\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010(\u001a\u00020)X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010*\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\\"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeLayer;", "Landroidx/compose/ui/node/OwnedLayer;", "ownerView", "Landroidx/compose/ui/platform/AndroidComposeView;", "drawBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Canvas;", "", "invalidateParentLayer", "Lkotlin/Function0;", "(Landroidx/compose/ui/platform/AndroidComposeView;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "getDrawBlock", "()Lkotlin/jvm/functions/Function1;", "drawnWithZ", "", "getInvalidateParentLayer", "()Lkotlin/jvm/functions/Function0;", "isDestroyed", "value", "isDirty", "setDirty", "(Z)V", "layerId", "", "getLayerId", "()J", "matrixCache", "Landroidx/compose/ui/platform/RenderNodeMatrixCache;", "outlineResolver", "Landroidx/compose/ui/platform/OutlineResolver;", "getOwnerView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "ownerViewId", "getOwnerViewId$annotations", "()V", "getOwnerViewId", "renderNode", "Landroidx/compose/ui/platform/DeviceRenderNode;", "transformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "J", "destroy", "drawLayer", "canvas", "invalidate", "isInLayer", "position", "Landroidx/compose/ui/geometry/Offset;", "isInLayer-k-4lQ0M", "(J)Z", "mapBounds", "rect", "Landroidx/compose/ui/geometry/MutableRect;", "inverse", "mapOffset", "point", "mapOffset-8S9VItk", "(JZ)J", "move", "Landroidx/compose/ui/unit/IntOffset;", "move--gyyYBs", "(J)V", "resize", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/unit/IntSize;", "resize-ozmzZPI", "triggerRepaint", "updateDisplayList", "updateLayerProperties", "scaleX", "", "scaleY", "alpha", "translationX", "translationY", "shadowElevation", "rotationX", "rotationY", "rotationZ", "cameraDistance", "shape", "Landroidx/compose/ui/graphics/Shape;", "clip", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "updateLayerProperties-dRfWZ4U", "(FFFFFFFFFFJLandroidx/compose/ui/graphics/Shape;ZLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)V", "UniqueDrawingIdApi29", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class RenderNodeLayer implements OwnedLayer {
    private final CanvasHolder canvasHolder;
    private final Function1<Canvas, Unit> drawBlock;
    private boolean drawnWithZ;
    private final Function0<Unit> invalidateParentLayer;
    private boolean isDestroyed;
    private boolean isDirty;
    private final RenderNodeMatrixCache matrixCache;
    private final OutlineResolver outlineResolver;
    private final AndroidComposeView ownerView;
    private final DeviceRenderNode renderNode;
    private long transformOrigin;

    @ExperimentalComposeUiApi
    public static /* synthetic */ void getOwnerViewId$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RenderNodeLayer(AndroidComposeView ownerView, Function1<? super Canvas, Unit> drawBlock, Function0<Unit> invalidateParentLayer) {
        Intrinsics.checkNotNullParameter(ownerView, "ownerView");
        Intrinsics.checkNotNullParameter(drawBlock, "drawBlock");
        Intrinsics.checkNotNullParameter(invalidateParentLayer, "invalidateParentLayer");
        this.ownerView = ownerView;
        this.drawBlock = drawBlock;
        this.invalidateParentLayer = invalidateParentLayer;
        this.outlineResolver = new OutlineResolver(ownerView.getDensity());
        this.matrixCache = new RenderNodeMatrixCache();
        this.canvasHolder = new CanvasHolder();
        this.transformOrigin = TransformOrigin.INSTANCE.m1559getCenterSzJe1aQ();
        RenderNodeApi29 renderNodeApi29 = new RenderNodeApi29(ownerView);
        renderNodeApi29.setHasOverlappingRendering(true);
        Unit unit = Unit.INSTANCE;
        this.renderNode = renderNodeApi29;
    }

    public final AndroidComposeView getOwnerView() {
        return this.ownerView;
    }

    public final Function1<Canvas, Unit> getDrawBlock() {
        return this.drawBlock;
    }

    public final Function0<Unit> getInvalidateParentLayer() {
        return this.invalidateParentLayer;
    }

    private final void setDirty(boolean z) {
        if (z != this.isDirty) {
            this.isDirty = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    @Override // androidx.compose.ui.layout.GraphicLayerInfo
    public long getLayerId() {
        return this.renderNode.getUniqueId();
    }

    @Override // androidx.compose.ui.layout.GraphicLayerInfo
    public long getOwnerViewId() {
        return UniqueDrawingIdApi29.INSTANCE.getUniqueDrawingId(this.ownerView);
    }

    /* compiled from: RenderNodeLayer.android.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeLayer$UniqueDrawingIdApi29;", "", "()V", "Companion", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class UniqueDrawingIdApi29 {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JvmStatic
        public static final long getUniqueDrawingId(View view) {
            return INSTANCE.getUniqueDrawingId(view);
        }

        /* compiled from: RenderNodeLayer.android.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeLayer$UniqueDrawingIdApi29$Companion;", "", "()V", "getUniqueDrawingId", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Landroid/view/View;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final long getUniqueDrawingId(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                return view.getUniqueDrawingId();
            }
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: updateLayerProperties-dRfWZ4U */
    public void mo2630updateLayerPropertiesdRfWZ4U(float scaleX, float scaleY, float alpha, float translationX, float translationY, float shadowElevation, float rotationX, float rotationY, float rotationZ, float cameraDistance, long transformOrigin, Shape shape, boolean clip, LayoutDirection layoutDirection, Density density) {
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(density, "density");
        this.transformOrigin = transformOrigin;
        boolean z = this.renderNode.getClipToOutline() && this.outlineResolver.getClipPath() != null;
        this.renderNode.setScaleX(scaleX);
        this.renderNode.setScaleY(scaleY);
        this.renderNode.setAlpha(alpha);
        this.renderNode.setTranslationX(translationX);
        this.renderNode.setTranslationY(translationY);
        this.renderNode.setElevation(shadowElevation);
        this.renderNode.setRotationZ(rotationZ);
        this.renderNode.setRotationX(rotationX);
        this.renderNode.setRotationY(rotationY);
        this.renderNode.setCameraDistance(cameraDistance);
        this.renderNode.setPivotX(TransformOrigin.m1554getPivotFractionXimpl(transformOrigin) * this.renderNode.getWidth());
        this.renderNode.setPivotY(TransformOrigin.m1555getPivotFractionYimpl(transformOrigin) * this.renderNode.getHeight());
        this.renderNode.setClipToOutline(clip && shape != RectangleShapeKt.getRectangleShape());
        this.renderNode.setClipToBounds(clip && shape == RectangleShapeKt.getRectangleShape());
        boolean update = this.outlineResolver.update(shape, this.renderNode.getAlpha(), this.renderNode.getClipToOutline(), this.renderNode.getElevation(), layoutDirection, density);
        this.renderNode.setOutline(this.outlineResolver.getOutline());
        boolean z2 = this.renderNode.getClipToOutline() && this.outlineResolver.getClipPath() != null;
        if (z != z2 || (z2 && update)) {
            invalidate();
        } else {
            triggerRepaint();
        }
        if (!this.drawnWithZ && this.renderNode.getElevation() > 0.0f) {
            this.invalidateParentLayer.invoke();
        }
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public boolean mo2626isInLayerk4lQ0M(long position) {
        float m1025getXimpl = Offset.m1025getXimpl(position);
        float m1026getYimpl = Offset.m1026getYimpl(position);
        if (this.renderNode.getClipToBounds()) {
            return 0.0f <= m1025getXimpl && m1025getXimpl < ((float) this.renderNode.getWidth()) && 0.0f <= m1026getYimpl && m1026getYimpl < ((float) this.renderNode.getHeight());
        }
        if (this.renderNode.getClipToOutline()) {
            return this.outlineResolver.m2649isInOutlinek4lQ0M(position);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public void mo2629resizeozmzZPI(long size) {
        int m3127getWidthimpl = IntSize.m3127getWidthimpl(size);
        int m3126getHeightimpl = IntSize.m3126getHeightimpl(size);
        float f = m3127getWidthimpl;
        this.renderNode.setPivotX(TransformOrigin.m1554getPivotFractionXimpl(this.transformOrigin) * f);
        float f2 = m3126getHeightimpl;
        this.renderNode.setPivotY(TransformOrigin.m1555getPivotFractionYimpl(this.transformOrigin) * f2);
        DeviceRenderNode deviceRenderNode = this.renderNode;
        if (deviceRenderNode.setPosition(deviceRenderNode.getLeft(), this.renderNode.getTop(), this.renderNode.getLeft() + m3127getWidthimpl, this.renderNode.getTop() + m3126getHeightimpl)) {
            this.outlineResolver.m2650updateuvyYCjk(SizeKt.Size(f, f2));
            this.renderNode.setOutline(this.outlineResolver.getOutline());
            invalidate();
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public void mo2628movegyyYBs(long position) {
        int left = this.renderNode.getLeft();
        int top = this.renderNode.getTop();
        int m3085getXimpl = IntOffset.m3085getXimpl(position);
        int m3086getYimpl = IntOffset.m3086getYimpl(position);
        if (left == m3085getXimpl && top == m3086getYimpl) {
            return;
        }
        this.renderNode.offsetLeftAndRight(m3085getXimpl - left);
        this.renderNode.offsetTopAndBottom(m3086getYimpl - top);
        triggerRepaint();
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void invalidate() {
        if (this.isDirty || this.isDestroyed) {
            return;
        }
        this.ownerView.invalidate();
        setDirty(true);
    }

    private final void triggerRepaint() {
        WrapperRenderNodeLayerHelperMethods.INSTANCE.onDescendantInvalidated(this.ownerView);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void drawLayer(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        android.graphics.Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (nativeCanvas.isHardwareAccelerated()) {
            updateDisplayList();
            boolean z = this.renderNode.getElevation() > 0.0f;
            this.drawnWithZ = z;
            if (z) {
                canvas.enableZ();
            }
            this.renderNode.drawInto(nativeCanvas);
            if (this.drawnWithZ) {
                canvas.disableZ();
                return;
            }
            return;
        }
        this.drawBlock.invoke(canvas);
        setDirty(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateDisplayList() {
        if (this.isDirty || !this.renderNode.getHasDisplayList()) {
            setDirty(false);
            this.renderNode.record(this.canvasHolder, this.renderNode.getClipToOutline() ? this.outlineResolver.getClipPath() : null, this.drawBlock);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void destroy() {
        this.isDestroyed = true;
        setDirty(false);
        this.ownerView.requestClearInvalidObservations();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: mapOffset-8S9VItk */
    public long mo2627mapOffset8S9VItk(long point, boolean inverse) {
        if (inverse) {
            return Matrix.m1424mapMKHz9U(this.matrixCache.m2651getInverseMatrixGrdbGEg(this.renderNode), point);
        }
        return Matrix.m1424mapMKHz9U(this.matrixCache.m2652getMatrixGrdbGEg(this.renderNode), point);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void mapBounds(MutableRect rect, boolean inverse) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        if (inverse) {
            Matrix.m1426mapimpl(this.matrixCache.m2651getInverseMatrixGrdbGEg(this.renderNode), rect);
        } else {
            Matrix.m1426mapimpl(this.matrixCache.m2652getMatrixGrdbGEg(this.renderNode), rect);
        }
    }
}
