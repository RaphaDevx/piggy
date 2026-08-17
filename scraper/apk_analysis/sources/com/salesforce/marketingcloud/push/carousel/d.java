package com.salesforce.marketingcloud.push.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.u;
import com.salesforce.marketingcloud.push.carousel.a;
import com.salesforce.marketingcloud.push.data.Style;
import com.salesforce.marketingcloud.push.style.a;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class d implements com.salesforce.marketingcloud.push.k<com.salesforce.marketingcloud.push.carousel.a> {
    private final com.salesforce.marketingcloud.push.carousel.b a;
    private final Context b;
    private final com.salesforce.marketingcloud.media.o c;
    private final String d;
    private final a.b e;

    static final class a extends Lambda implements Function0<String> {
        final /* synthetic */ Ref.IntRef b;
        final /* synthetic */ Ref.IntRef c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Ref.IntRef intRef, Ref.IntRef intRef2) {
            super(0);
            this.b = intRef;
            this.c = intRef2;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "safe dimension::imageHeight " + this.b.element + " , imageWidth " + this.c.element;
        }
    }

    static final class b extends Lambda implements Function0<String> {
        final /* synthetic */ Ref.IntRef b;
        final /* synthetic */ Ref.IntRef c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Ref.IntRef intRef, Ref.IntRef intRef2) {
            super(0);
            this.b = intRef;
            this.c = intRef2;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "ratio adjusted safe dimension::imageHeight " + this.b.element + " , imageWidth " + this.c.element;
        }
    }

    static final class c extends Lambda implements Function0<String> {
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(int i, int i2, int i3, int i4) {
            super(0);
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return " getCarouselImageHeight captionsOnly" + this.b + " subCaptionOnly" + this.c + " captionWithSubCaption" + this.d + " noCaptionSubCaption " + this.e + StringUtils.SPACE;
        }
    }

    /* renamed from: com.salesforce.marketingcloud.push.carousel.d$d, reason: collision with other inner class name */
    static final class C0078d extends Lambda implements Function0<String> {
        final /* synthetic */ Ref.IntRef b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0078d(Ref.IntRef intRef) {
            super(0);
            this.b = intRef;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return " captionWithSubCaption enter:" + this.b.element;
        }
    }

    static final class e extends Lambda implements Function0<String> {
        final /* synthetic */ Ref.IntRef b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Ref.IntRef intRef) {
            super(0);
            this.b = intRef;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "noCaptionSubCaption :" + this.b.element;
        }
    }

    static final class f extends Lambda implements Function0<String> {
        final /* synthetic */ Ref.IntRef b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(Ref.IntRef intRef) {
            super(0);
            this.b = intRef;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "captionsOnly :" + this.b.element;
        }
    }

    static final class g extends Lambda implements Function0<String> {
        final /* synthetic */ Ref.IntRef b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(Ref.IntRef intRef) {
            super(0);
            this.b = intRef;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "subCaptionOnly :" + this.b.element;
        }
    }

    static final class h extends Lambda implements Function0<String> {
        final /* synthetic */ int b;
        final /* synthetic */ int c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(int i, int i2) {
            super(0);
            this.b = i;
            this.c = i2;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "captionsOnly :" + this.b + " and subCaptionOnly " + this.c;
        }
    }

    static final class i extends Lambda implements Function0<String> {
        final /* synthetic */ DisplayMetrics b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(DisplayMetrics displayMetrics) {
            super(0);
            this.b = displayMetrics;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "displayMetrics " + this.b;
        }
    }

    static final class j extends Lambda implements Function0<String> {
        final /* synthetic */ DisplayMetrics b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(DisplayMetrics displayMetrics) {
            super(0);
            this.b = displayMetrics;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "displayMetrics DPI " + this.b.densityDpi;
        }
    }

    static final class k extends Lambda implements Function0<String> {
        final /* synthetic */ Ref.IntRef b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(Ref.IntRef intRef) {
            super(0);
            this.b = intRef;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "API > 31 decorationWidth " + this.b.element;
        }
    }

    static final class l extends Lambda implements Function0<String> {
        final /* synthetic */ Ref.IntRef b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(Ref.IntRef intRef) {
            super(0);
            this.b = intRef;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "API < 31 decorationWidth " + this.b.element;
        }
    }

    static final class m extends Lambda implements Function0<String> {
        final /* synthetic */ Bitmap b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        m(Bitmap bitmap) {
            super(0);
            this.b = bitmap;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "getScaledBitmapFromDrawable default bitmap W:" + this.b.getWidth() + "  H:" + this.b.getHeight();
        }
    }

    static final class n extends Lambda implements Function0<String> {
        final /* synthetic */ Bitmap b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        n(Bitmap bitmap) {
            super(0);
            this.b = bitmap;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "getScaledBitmapFromDrawable scaled bitmap W:" + this.b.getWidth() + "  H:" + this.b.getHeight();
        }
    }

    public static final class o implements com.salesforce.marketingcloud.media.f {
        final /* synthetic */ a.C0076a b;

        static final class a extends Lambda implements Function0<String> {
            final /* synthetic */ a.C0076a b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(a.C0076a c0076a) {
                super(0);
                this.b = c0076a;
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return "Preloading of the image failed. " + this.b.p().o();
            }
        }

        o(a.C0076a c0076a) {
            this.b = c0076a;
        }

        @Override // com.salesforce.marketingcloud.media.f
        public void a() {
        }

        @Override // com.salesforce.marketingcloud.media.f
        public void a(Exception exc) {
            com.salesforce.marketingcloud.g.a.a(d.this.d, exc, new a(this.b));
        }
    }

    static final class p extends Lambda implements Function0<String> {
        final /* synthetic */ a.C0076a b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        p(a.C0076a c0076a) {
            super(0);
            this.b = c0076a;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to load image from URL: " + this.b.p().o();
        }
    }

    static final class q extends Lambda implements Function0<String> {
        public static final q b = new q();

        q() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Not applying alignment on subTitle due to Android Version <= 11";
        }
    }

    static final class r extends Lambda implements Function0<String> {
        public static final r b = new r();

        r() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Not applying alignment on title due to Android Version <= 11";
        }
    }

    public d(com.salesforce.marketingcloud.push.carousel.b intentProvider, Context context, com.salesforce.marketingcloud.media.o oVar) {
        Intrinsics.checkNotNullParameter(intentProvider, "intentProvider");
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = intentProvider;
        this.b = context;
        this.c = oVar;
        this.d = com.salesforce.marketingcloud.g.a("CarouselRenderer");
        this.e = new a.b(context);
    }

    @Override // com.salesforce.marketingcloud.push.k
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public RemoteViews a(RemoteViews remoteViews, com.salesforce.marketingcloud.push.carousel.a template) {
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(template, "template");
        if (template.l().isEmpty() || template.m() < 0 || template.m() >= template.l().size()) {
            throw new IllegalArgumentException("Carousel template must have at least one item");
        }
        a.C0076a c0076a = template.l().get(template.m());
        remoteViews.setViewVisibility(R.id.mcsdk_push_carousel, 0);
        c(remoteViews, c0076a);
        b(remoteViews, c0076a);
        b(remoteViews, c0076a, template);
        d(remoteViews, template);
        c(remoteViews, template);
        a(remoteViews, c0076a);
        return remoteViews;
    }

    public final void c(RemoteViews remoteViews, a.C0076a item) {
        CharSequence n2;
        Style.Alignment e2;
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.r() == null) {
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_title, 4);
            return;
        }
        remoteViews.setViewVisibility(R.id.mcsdk_carousel_title, 0);
        int i2 = R.id.mcsdk_carousel_title;
        Style.b a2 = this.e.a(item.r(), Style.FontStyle.B).a();
        if (a2 == null || (n2 = a2.o()) == null) {
            n2 = item.r().n();
        }
        remoteViews.setTextViewText(i2, n2);
        if (Build.VERSION.SDK_INT < 31) {
            Style.b a3 = item.r().a();
            if ((a3 != null ? a3.e() : null) != null) {
                com.salesforce.marketingcloud.g.a.e(this.d, (Throwable) null, r.b);
                return;
            }
            return;
        }
        Style.b a4 = item.r().a();
        if (a4 == null || (e2 = a4.e()) == null) {
            return;
        }
        remoteViews.setInt(R.id.mcsdk_carousel_title, "setGravity", e2.toGravity());
    }

    public final void d(RemoteViews remoteViews, com.salesforce.marketingcloud.push.carousel.a template) {
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(template, "template");
        remoteViews.removeAllViews(R.id.mcsdk_dot_container);
        int size = template.l().size();
        int i2 = 0;
        while (i2 < size) {
            RemoteViews remoteViews2 = new RemoteViews(this.b.getPackageName(), R.layout.mcsdk_dot_view);
            remoteViews2.setImageViewResource(R.id.mcsdk_dot_image, i2 == template.m() ? R.drawable.mcsdk_dot_selected : R.drawable.mcsdk_dot_unselected);
            remoteViews.addView(R.id.mcsdk_dot_container, remoteViews2);
            i2++;
        }
    }

    private final void a(a.C0076a c0076a, RemoteViews remoteViews) {
        Unit unit;
        remoteViews.setViewVisibility(R.id.mcsdk_carousel_image, 8);
        com.salesforce.marketingcloud.push.data.c n2 = c0076a.p().n();
        if (n2 != null) {
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_alt_text, 0);
            remoteViews.setTextViewText(R.id.mcsdk_carousel_alt_text, n2.n());
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_placeholder, 8);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_placeholder, 0);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_alt_text, 8);
        }
    }

    public final void b(RemoteViews remoteViews, a.C0076a item) {
        CharSequence n2;
        Style.Alignment e2;
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.q() != null) {
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_subtitle, 0);
            int i2 = R.id.mcsdk_carousel_subtitle;
            Style.b a2 = ((com.salesforce.marketingcloud.push.data.c) com.salesforce.marketingcloud.push.style.a.a(this.e, item.q(), null, 2, null)).a();
            if (a2 == null || (n2 = a2.o()) == null) {
                n2 = item.q().n();
            }
            remoteViews.setTextViewText(i2, n2);
            if (Build.VERSION.SDK_INT >= 31) {
                Style.b a3 = item.q().a();
                if (a3 == null || (e2 = a3.e()) == null) {
                    return;
                }
                remoteViews.setInt(R.id.mcsdk_carousel_subtitle, "setGravity", e2.toGravity());
                return;
            }
            Style.b a4 = item.q().a();
            if ((a4 != null ? a4.e() : null) != null) {
                com.salesforce.marketingcloud.g.a.e(this.d, (Throwable) null, q.b);
                return;
            }
            return;
        }
        remoteViews.setViewVisibility(R.id.mcsdk_carousel_subtitle, 4);
    }

    public final void c(RemoteViews remoteViews, com.salesforce.marketingcloud.push.carousel.a template) {
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(template, "template");
        remoteViews.setOnClickPendingIntent(R.id.mcsdk_carousel_previous, this.a.a(com.salesforce.marketingcloud.push.carousel.b.m, template));
        remoteViews.setOnClickPendingIntent(R.id.mcsdk_carousel_next, this.a.a(com.salesforce.marketingcloud.push.carousel.b.l, template));
        remoteViews.setOnClickPendingIntent(R.id.mcsdk_carousel_next_unselected, null);
        remoteViews.setOnClickPendingIntent(R.id.mcsdk_carousel_previous_unselected, null);
        int m2 = template.m();
        if (m2 == 0) {
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_previous, 4);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_previous_unselected, 0);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_next, 0);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_next_unselected, 8);
            return;
        }
        if (m2 == template.l().size() - 1) {
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_previous, 0);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_previous_unselected, 8);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_next, 4);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_next_unselected, 0);
            return;
        }
        remoteViews.setViewVisibility(R.id.mcsdk_carousel_previous, 0);
        remoteViews.setViewVisibility(R.id.mcsdk_carousel_previous_unselected, 8);
        remoteViews.setViewVisibility(R.id.mcsdk_carousel_next, 0);
        remoteViews.setViewVisibility(R.id.mcsdk_carousel_next_unselected, 8);
    }

    public final Bitmap a(Drawable background, int i2, int i3) {
        Intrinsics.checkNotNullParameter(background, "background");
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        background.setBounds(0, 0, i2, i3);
        background.draw(canvas);
        com.salesforce.marketingcloud.g gVar = com.salesforce.marketingcloud.g.a;
        com.salesforce.marketingcloud.g.a(gVar, this.d, null, new m(createBitmap), 2, null);
        Bitmap a2 = com.salesforce.marketingcloud.media.q.a.a(createBitmap, i3, i2);
        com.salesforce.marketingcloud.g.a(gVar, this.d, null, new n(a2), 2, null);
        return a2;
    }

    public final void a(RemoteViews remoteViews, a.C0076a item) {
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(item, "item");
        List<com.salesforce.marketingcloud.push.data.a> i2 = item.i();
        if (i2 != null) {
            remoteViews.setOnClickPendingIntent(R.id.mcsdk_push_carousel, this.a.a((com.salesforce.marketingcloud.push.data.a[]) i2.toArray(new com.salesforce.marketingcloud.push.data.a[0]), com.salesforce.marketingcloud.analytics.stats.b.n, item.d(), null));
        }
    }

    public final void b(RemoteViews remoteViews, a.C0076a item, com.salesforce.marketingcloud.push.carousel.a template) {
        Bitmap bitmap;
        u b2;
        u a2;
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(template, "template");
        Pair<Integer, Integer> a3 = a(remoteViews, item, template);
        int intValue = a3.component1().intValue();
        int intValue2 = a3.component2().intValue();
        Drawable drawable = this.b.getResources().getDrawable(R.drawable.mcsdk_carousel_bg, this.b.getTheme());
        Style a4 = item.p().a();
        Bitmap bitmap2 = null;
        if ((a4 != null ? a4.h() : null) != null) {
            drawable.setColorFilter(new BlendModeColorFilter(Color.parseColor(item.p().a().h()), BlendMode.SRC_ATOP));
            Intrinsics.checkNotNull(drawable);
            bitmap = a(drawable, intValue2, intValue);
            remoteViews.setImageViewBitmap(R.id.mcsdk_carousel_bg, bitmap);
        } else {
            Style a5 = template.a();
            if ((a5 != null ? a5.h() : null) != null) {
                drawable.setColorFilter(new BlendModeColorFilter(Color.parseColor(template.a().h()), BlendMode.SRC_ATOP));
                Intrinsics.checkNotNull(drawable);
                bitmap = a(drawable, intValue2, intValue);
                remoteViews.setImageViewBitmap(R.id.mcsdk_carousel_bg, bitmap);
            } else {
                bitmap = null;
            }
        }
        if (bitmap == null) {
            Intrinsics.checkNotNull(drawable);
            bitmap = a(drawable, intValue2, intValue);
            remoteViews.setImageViewBitmap(R.id.mcsdk_carousel_bg, bitmap);
        }
        try {
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_image, 0);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_placeholder, 8);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_alt_text, 8);
            com.salesforce.marketingcloud.media.o oVar = this.c;
            if (oVar != null) {
                bitmap2 = oVar.a(item.p().o() + StringUtils.LF);
            }
            if (bitmap2 == null) {
                a(item, remoteViews);
                com.salesforce.marketingcloud.media.o oVar2 = this.c;
                if (oVar2 == null || (b2 = oVar2.b(item.p().o())) == null || (a2 = b2.a(o.c.HIGH)) == null) {
                    return;
                }
                a2.a(new o(item));
                return;
            }
            Bitmap a6 = com.salesforce.marketingcloud.media.q.a.a(bitmap2, intValue, intValue2);
            if (a(bitmap, a6)) {
                remoteViews.setImageViewBitmap(R.id.mcsdk_carousel_image, com.salesforce.marketingcloud.push.i.a.a(a6, this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_radius)));
            } else {
                remoteViews.setImageViewBitmap(R.id.mcsdk_carousel_image, a6);
            }
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.a.b(this.d, e2, new p(item));
            a(item, remoteViews);
        }
    }

    public final boolean a(Bitmap backgroundImage, Bitmap foregroundImage) {
        Intrinsics.checkNotNullParameter(backgroundImage, "backgroundImage");
        Intrinsics.checkNotNullParameter(foregroundImage, "foregroundImage");
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_radius);
        if (foregroundImage.getWidth() > backgroundImage.getWidth() || foregroundImage.getHeight() > backgroundImage.getHeight()) {
            return true;
        }
        return Math.sqrt((double) ((foregroundImage.getWidth() * foregroundImage.getWidth()) + (foregroundImage.getHeight() * foregroundImage.getHeight()))) > Math.sqrt((double) ((backgroundImage.getWidth() * backgroundImage.getWidth()) + (backgroundImage.getHeight() * backgroundImage.getHeight()))) - (((double) (dimensionPixelSize * 2)) * Math.sqrt(2.0d));
    }

    public final Pair<Integer, Integer> a(RemoteViews remoteViews, a.C0076a item, com.salesforce.marketingcloud.push.carousel.a template) {
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(template, "template");
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = a();
        Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = a2(remoteViews, template);
        com.salesforce.marketingcloud.g gVar = com.salesforce.marketingcloud.g.a;
        com.salesforce.marketingcloud.g.a(gVar, this.d, null, new a(intRef2, intRef), 2, null);
        int i2 = intRef.element / 2;
        int i3 = intRef2.element;
        if (i2 < i3) {
            intRef2.element = i2;
            intRef.element = i2 * 2;
        } else {
            intRef.element = i3 * 2;
        }
        com.salesforce.marketingcloud.g.a(gVar, this.d, null, new b(intRef2, intRef), 2, null);
        return new Pair<>(Integer.valueOf(intRef2.element), Integer.valueOf(intRef.element));
    }

    /* renamed from: a, reason: avoid collision after fix types in other method */
    public final int a2(RemoteViews remoteViews, com.salesforce.marketingcloud.push.carousel.a template) {
        int i2;
        int i3;
        int i4;
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        Intrinsics.checkNotNullParameter(template, "template");
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_height);
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = dimensionPixelSize;
        List<a.C0076a> l2 = template.l();
        boolean z = l2 instanceof Collection;
        int i5 = 0;
        if (z && l2.isEmpty()) {
            i2 = 0;
        } else {
            i2 = 0;
            for (a.C0076a c0076a : l2) {
                if (c0076a.r() != null && c0076a.q() == null && (i2 = i2 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (z && l2.isEmpty()) {
            i3 = 0;
        } else {
            i3 = 0;
            for (a.C0076a c0076a2 : l2) {
                if (c0076a2.r() == null && c0076a2.q() != null && (i3 = i3 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (z && l2.isEmpty()) {
            i4 = 0;
        } else {
            i4 = 0;
            for (a.C0076a c0076a3 : l2) {
                if (c0076a3.r() != null && c0076a3.q() != null && (i4 = i4 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (!z || !l2.isEmpty()) {
            for (a.C0076a c0076a4 : l2) {
                if (c0076a4.r() == null && c0076a4.q() == null && (i5 = i5 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        com.salesforce.marketingcloud.g gVar = com.salesforce.marketingcloud.g.a;
        com.salesforce.marketingcloud.g.a(gVar, this.d, null, new c(i2, i3, i4, i5), 2, null);
        if (i4 > 0) {
            com.salesforce.marketingcloud.g.a(gVar, this.d, null, new C0078d(intRef), 2, null);
        } else if (i5 == l2.size()) {
            intRef.element += this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_height_addendum) * 2;
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_title, 8);
            remoteViews.setViewVisibility(R.id.mcsdk_carousel_subtitle, 8);
            com.salesforce.marketingcloud.g.a(gVar, this.d, null, new e(intRef), 2, null);
        } else {
            int i6 = i2 + i5;
            if (i6 == l2.size()) {
                intRef.element += this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_height_addendum);
                remoteViews.setViewVisibility(R.id.mcsdk_carousel_subtitle, 8);
                com.salesforce.marketingcloud.g.a(gVar, this.d, null, new f(intRef), 2, null);
            } else if (i5 + i3 == l2.size()) {
                intRef.element += this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_carousel_image_height_addendum);
                remoteViews.setViewVisibility(R.id.mcsdk_carousel_title, 8);
                com.salesforce.marketingcloud.g.a(gVar, this.d, null, new g(intRef), 2, null);
            } else if (i6 + i3 == l2.size()) {
                com.salesforce.marketingcloud.g.a(gVar, this.d, null, new h(i2, i3), 2, null);
            }
        }
        return intRef.element;
    }

    private final int a() {
        Ref.IntRef intRef = new Ref.IntRef();
        DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
        com.salesforce.marketingcloud.g gVar = com.salesforce.marketingcloud.g.a;
        com.salesforce.marketingcloud.g.a(gVar, this.d, null, new i(displayMetrics), 2, null);
        com.salesforce.marketingcloud.g.a(gVar, this.d, null, new j(displayMetrics), 2, null);
        if (Build.VERSION.SDK_INT >= 31) {
            intRef.element = (this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_android_notification_padding) * 2) + this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_android_notification_margin) + this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_android_notification_pillar_margin);
            com.salesforce.marketingcloud.g.a(gVar, this.d, null, new k(intRef), 2, null);
        } else {
            intRef.element = this.b.getResources().getDimensionPixelSize(R.dimen.mcsdk_push_android_notification_padding) * 2;
            com.salesforce.marketingcloud.g.a(gVar, this.d, null, new l(intRef), 2, null);
        }
        int i2 = displayMetrics.widthPixels - intRef.element;
        return i2 - (i2 % 2);
    }
}
