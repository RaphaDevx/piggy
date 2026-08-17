package com.scandit.datacapture.core.internal.sdk.extensions;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Looper;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.core.R;
import com.tealium.library.DataSources;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u001a\u0012\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0007\u001a\n\u0010\u001a\u001a\u00020\u0014*\u00020\u001b\u001a\n\u0010\u001c\u001a\u00020\u0014*\u00020\u0002\u001a<\u0010\u001d\u001a\u00020\u0014\"\b\b\u0000\u0010\u001e*\u00020\u0002*\u0002H\u001e2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u0011H\u001e¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\u0010$\u001a\u0014\u0010%\u001a\u00020\u0014*\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0016\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"*\u0010\b\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00078Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\"*\u0010\f\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00078Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000b\"\u0016\u0010\u000e\u001a\u00020\u0007*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\t\"\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006("}, d2 = {ViewHierarchyNode.JsonKeys.CHILDREN, "", "Landroid/view/View;", "Landroid/view/ViewGroup;", "getChildren", "(Landroid/view/ViewGroup;)Ljava/lang/Iterable;", "value", "", "isGone", "(Landroid/view/View;)Z", "setGone", "(Landroid/view/View;Z)V", "isInvisible", "setInvisible", "isVisible", "orientation", "", "getOrientation", "(Landroid/view/View;)I", "addRippleForeground", "", "mask", "Landroid/graphics/drawable/Drawable;", "getCorrectedViewSize", "Landroid/util/Size;", "isPortrait", "removeAllRules", "Landroid/widget/RelativeLayout$LayoutParams;", "removeFromSuperview", "runOnMainThread", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "setRippleWrappedDrawable", "Landroid/widget/ImageView;", FirebaseAnalytics.Param.CONTENT, "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ViewExtensionsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(Function1 block, View this_runOnMainThread) {
        Intrinsics.checkNotNullParameter(block, "$block");
        Intrinsics.checkNotNullParameter(this_runOnMainThread, "$this_runOnMainThread");
        block.invoke(this_runOnMainThread);
    }

    public static final void addRippleForeground(View view, Drawable drawable) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ColorStateList valueOf = ColorStateList.valueOf(view.getContext().getColor(R.color.sc_default_ripple));
        if (drawable == null) {
            drawable = view.getBackground();
        }
        view.setForeground(new RippleDrawable(valueOf, null, drawable));
    }

    public static /* synthetic */ void addRippleForeground$default(View view, Drawable drawable, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable = null;
        }
        addRippleForeground(view, drawable);
    }

    public static final Iterable<View> getChildren(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        return new ViewExtensionsKt$special$$inlined$Iterable$1(viewGroup);
    }

    public static final Size getCorrectedViewSize(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return z ? new Size(Math.min(view.getWidth(), view.getHeight()), Math.max(view.getWidth(), view.getHeight())) : new Size(Math.max(view.getWidth(), view.getHeight()), Math.min(view.getWidth(), view.getHeight()));
    }

    public static final int getOrientation(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return ContextExtensionsKt.getOrientation(context);
    }

    public static final void removeAllRules(RelativeLayout.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(layoutParams, "<this>");
        int length = layoutParams.getRules().length;
        for (int i = 0; i < length; i++) {
            layoutParams.removeRule(i);
        }
    }

    public static final void removeFromSuperview(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
    }

    public static final <T extends View> void runOnMainThread(final T t, final Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            block.invoke(t);
        } else {
            t.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ViewExtensionsKt.a(Function1.this, t);
                }
            });
        }
    }

    public static final void setRippleWrappedDrawable(ImageView imageView, Drawable drawable) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        if (drawable == null) {
            imageView.setImageDrawable(null);
        } else {
            imageView.setImageDrawable(new RippleDrawable(ColorStateList.valueOf(imageView.getContext().getColor(R.color.sc_default_ripple)), drawable, null));
        }
    }

    public static final boolean isVisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 0;
    }

    public static final boolean isInvisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 4;
    }

    public static final void setInvisible(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 4 : 0);
    }

    public static final boolean isGone(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 8;
    }

    public static final void setGone(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 8 : 0);
    }
}
