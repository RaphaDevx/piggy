package io.sentry.android.replay.util;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.SentryOptions;
import io.sentry.android.replay.viewhierarchy.ComposeViewHierarchyNode;
import io.sentry.android.replay.viewhierarchy.ViewHierarchyNode;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Views.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0016\u0010\n\u001a\u00020\u0006*\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0000\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0007H\u0000\u001a\u000e\u0010\u0015\u001a\u00020\u0014*\u0004\u0018\u00010\u0016H\u0001\u001a\u001a\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0018*\u00020\u0007H\u0000\u001a\u0016\u0010\u0019\u001a\u00020\u0006*\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0016\u0010\u001a\u001a\u00020\u0006*\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0000\u001a\f\u0010\u001b\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\u001c\u0010\u001c\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006!"}, d2 = {"totalPaddingTopSafe", "", "Landroid/widget/TextView;", "getTotalPaddingTopSafe", "(Landroid/widget/TextView;)I", "addOnDrawListenerSafe", "", "Landroid/view/View;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/ViewTreeObserver$OnDrawListener;", "addOnPreDrawListenerSafe", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "getVisibleRects", "", "Landroid/graphics/Rect;", "Lio/sentry/android/replay/util/TextLayout;", "globalRect", "paddingLeft", "paddingTop", "hasSize", "", "isMaskable", "Landroid/graphics/drawable/Drawable;", "isVisibleToUser", "Lkotlin/Pair;", "removeOnDrawListenerSafe", "removeOnPreDrawListenerSafe", "toOpaque", "traverse", "parentNode", "Lio/sentry/android/replay/viewhierarchy/ViewHierarchyNode;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "sentry-android-replay_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ViewsKt {
    public static final int toOpaque(int i) {
        return i | ViewCompat.MEASURED_STATE_MASK;
    }

    public static final void traverse(View view, ViewHierarchyNode parentNode, SentryOptions options) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(parentNode, "parentNode");
        Intrinsics.checkNotNullParameter(options, "options");
        if ((view instanceof ViewGroup) && !ComposeViewHierarchyNode.INSTANCE.fromView(view, parentNode, options)) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(viewGroup.getChildCount());
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt != null) {
                    ViewHierarchyNode fromView = ViewHierarchyNode.INSTANCE.fromView(childAt, parentNode, viewGroup.indexOfChild(childAt), options);
                    arrayList.add(fromView);
                    traverse(childAt, fromView, options);
                }
            }
            parentNode.setChildren(arrayList);
        }
    }

    public static final Pair<Boolean, Rect> isVisibleToUser(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (view.isAttachedToWindow()) {
            if (view.getWindowVisibility() != 0) {
                return TuplesKt.to(false, null);
            }
            Object obj = view;
            while (obj instanceof View) {
                float transitionAlpha = ((View) obj).getTransitionAlpha();
                View view2 = (View) obj;
                if (view2.getAlpha() <= 0.0f || transitionAlpha <= 0.0f || view2.getVisibility() != 0) {
                    return TuplesKt.to(false, null);
                }
                obj = view2.getParent();
            }
            Rect rect = new Rect();
            return TuplesKt.to(Boolean.valueOf(view.getGlobalVisibleRect(rect, new Point())), rect);
        }
        return TuplesKt.to(false, null);
    }

    public static final boolean isMaskable(Drawable drawable) {
        if (drawable instanceof InsetDrawable ? true : drawable instanceof ColorDrawable ? true : drawable instanceof VectorDrawable ? true : drawable instanceof GradientDrawable) {
            return false;
        }
        if (!(drawable instanceof BitmapDrawable)) {
            return true;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap == null) {
            return false;
        }
        return !bitmap.isRecycled() && bitmap.getHeight() > 10 && bitmap.getWidth() > 10;
    }

    public static final List<Rect> getVisibleRects(TextLayout textLayout, Rect globalRect, int i, int i2) {
        Intrinsics.checkNotNullParameter(globalRect, "globalRect");
        if (textLayout == null) {
            return CollectionsKt.listOf(globalRect);
        }
        ArrayList arrayList = new ArrayList();
        int lineCount = textLayout.getLineCount();
        for (int i3 = 0; i3 < lineCount; i3++) {
            int primaryHorizontal = (int) textLayout.getPrimaryHorizontal(i3, textLayout.getLineStart(i3));
            int ellipsisCount = textLayout.getEllipsisCount(i3);
            int lineVisibleEnd = textLayout.getLineVisibleEnd(i3);
            int primaryHorizontal2 = (int) textLayout.getPrimaryHorizontal(i3, (lineVisibleEnd - ellipsisCount) + (ellipsisCount > 0 ? 1 : 0));
            if (primaryHorizontal2 == 0 && lineVisibleEnd > 0) {
                primaryHorizontal2 = ((int) textLayout.getPrimaryHorizontal(i3, lineVisibleEnd - 1)) + 1;
            }
            int lineTop = textLayout.getLineTop(i3);
            int lineBottom = textLayout.getLineBottom(i3);
            Rect rect = new Rect();
            rect.left = globalRect.left + i + primaryHorizontal;
            rect.right = rect.left + (primaryHorizontal2 - primaryHorizontal);
            rect.top = globalRect.top + i2 + lineTop;
            rect.bottom = rect.top + (lineBottom - lineTop);
            arrayList.add(rect);
        }
        return arrayList;
    }

    public static final int getTotalPaddingTopSafe(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        try {
            return textView.getTotalPaddingTop();
        } catch (NullPointerException unused) {
            return textView.getExtendedPaddingTop();
        }
    }

    public static final void addOnDrawListenerSafe(View view, ViewTreeObserver.OnDrawListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (view == null || view.getViewTreeObserver() == null || !view.getViewTreeObserver().isAlive()) {
            return;
        }
        try {
            view.getViewTreeObserver().addOnDrawListener(listener);
        } catch (IllegalStateException unused) {
        }
    }

    public static final void removeOnDrawListenerSafe(View view, ViewTreeObserver.OnDrawListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (view == null || view.getViewTreeObserver() == null || !view.getViewTreeObserver().isAlive()) {
            return;
        }
        try {
            view.getViewTreeObserver().removeOnDrawListener(listener);
        } catch (IllegalStateException unused) {
        }
    }

    public static final void addOnPreDrawListenerSafe(View view, ViewTreeObserver.OnPreDrawListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (view == null || view.getViewTreeObserver() == null || !view.getViewTreeObserver().isAlive()) {
            return;
        }
        try {
            view.getViewTreeObserver().addOnPreDrawListener(listener);
        } catch (IllegalStateException unused) {
        }
    }

    public static final void removeOnPreDrawListenerSafe(View view, ViewTreeObserver.OnPreDrawListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (view == null || view.getViewTreeObserver() == null || !view.getViewTreeObserver().isAlive()) {
            return;
        }
        try {
            view.getViewTreeObserver().removeOnPreDrawListener(listener);
        } catch (IllegalStateException unused) {
        }
    }

    public static final boolean hasSize(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getWidth() > 0 && view.getHeight() > 0;
    }
}
