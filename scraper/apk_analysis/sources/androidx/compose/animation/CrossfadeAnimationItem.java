package androidx.compose.animation;

import androidx.compose.runtime.Composer;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Crossfade.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B \u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ\u0019\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007HÆ\u0003¢\u0006\u0002\u0010\nJ3\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\u0013\b\u0002\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001e\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Landroidx/compose/animation/CrossfadeAnimationItem;", ExifInterface.GPS_DIRECTION_TRUE, "", "key", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "getContent", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/animation/CrossfadeAnimationItem;", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "toString", "", "animation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final /* data */ class CrossfadeAnimationItem<T> {
    private final Function2<Composer, Integer, Unit> content;
    private final T key;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CrossfadeAnimationItem copy$default(CrossfadeAnimationItem crossfadeAnimationItem, Object obj, Function2 function2, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = crossfadeAnimationItem.key;
        }
        if ((i & 2) != 0) {
            function2 = crossfadeAnimationItem.content;
        }
        return crossfadeAnimationItem.copy(obj, function2);
    }

    public final T component1() {
        return this.key;
    }

    public final Function2<Composer, Integer, Unit> component2() {
        return this.content;
    }

    public final CrossfadeAnimationItem<T> copy(T key, Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return new CrossfadeAnimationItem<>(key, content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CrossfadeAnimationItem)) {
            return false;
        }
        CrossfadeAnimationItem crossfadeAnimationItem = (CrossfadeAnimationItem) other;
        return Intrinsics.areEqual(this.key, crossfadeAnimationItem.key) && Intrinsics.areEqual(this.content, crossfadeAnimationItem.content);
    }

    public int hashCode() {
        T t = this.key;
        return ((t == null ? 0 : t.hashCode()) * 31) + this.content.hashCode();
    }

    public String toString() {
        return "CrossfadeAnimationItem(key=" + this.key + ", content=" + this.content + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CrossfadeAnimationItem(T t, Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.key = t;
        this.content = content;
    }

    public final T getKey() {
        return this.key;
    }

    public final Function2<Composer, Integer, Unit> getContent() {
        return this.content;
    }
}
