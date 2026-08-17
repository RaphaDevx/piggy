package com.salesforce.marketingcloud.sfmcsdk.components.logging;

import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.push.g;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u000b\b&\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011J\u0012\u0010\u0016\u001a\u00020\u00112\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019J\u001e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0016J*\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00112\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0016J\u001e\u0010 \u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0016J*\u0010 \u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00112\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0016J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u0011H\u0002J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0002J7\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00112\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u001f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0000¢\u0006\u0002\b'J\u001e\u0010(\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0016J*\u0010(\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00112\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006*"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/Logger;", "", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogListener;", "getListener", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogListener;", "setListener", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogListener;)V", "logLevel", "Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogLevel;", "getLogLevel", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogLevel;", "setLogLevel", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogLevel;)V", "redactedValues", "", "", "getRedactedValues", "()Ljava/util/List;", "setRedactedValues", "(Ljava/util/List;)V", "createTag", "tag", "clazz", "Lkotlin/reflect/KClass;", "d", "", "lazyMsg", "Lkotlin/Function0;", "throwable", "", "e", "formatMsg", NotificationCompat.CATEGORY_MESSAGE, "formatTag", "log", "lvl", g.g, "log$sfmcsdk_release", "w", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class Logger {
    private static final int MAX_TAG_LENGTH = 23;
    private static final String REDACTED_VALUE_REPLACEMENT_TEXT = "[REDACTED]";
    private LogListener listener;
    private List<String> redactedValues = CollectionsKt.emptyList();
    private LogLevel logLevel = LogLevel.ERROR;

    public List<String> getRedactedValues() {
        return this.redactedValues;
    }

    public void setRedactedValues(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.redactedValues = list;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        Intrinsics.checkNotNullParameter(logLevel, "<set-?>");
        this.logLevel = logLevel;
    }

    public LogListener getListener() {
        return this.listener;
    }

    public void setListener(LogListener logListener) {
        this.listener = logListener;
    }

    public final String createTag(KClass<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        String simpleName = JvmClassMappingKt.getJavaClass((KClass) clazz).getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        return createTag(simpleName);
    }

    public final String createTag(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return formatTag(tag);
    }

    public void d(String tag, Function0<String> lazyMsg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(lazyMsg, "lazyMsg");
        d(tag, null, lazyMsg);
    }

    public static /* synthetic */ void d$default(Logger logger, String str, Throwable th, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: d");
        }
        if ((i & 2) != 0) {
            th = null;
        }
        logger.d(str, th, function0);
    }

    public void d(String tag, Throwable throwable, Function0<String> lazyMsg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(lazyMsg, "lazyMsg");
        log$sfmcsdk_release(LogLevel.DEBUG, tag, throwable, lazyMsg);
    }

    public void w(String tag, Function0<String> lazyMsg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(lazyMsg, "lazyMsg");
        w(tag, null, lazyMsg);
    }

    public static /* synthetic */ void w$default(Logger logger, String str, Throwable th, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: w");
        }
        if ((i & 2) != 0) {
            th = null;
        }
        logger.w(str, th, function0);
    }

    public void w(String tag, Throwable throwable, Function0<String> lazyMsg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(lazyMsg, "lazyMsg");
        log$sfmcsdk_release(LogLevel.WARN, tag, throwable, lazyMsg);
    }

    public void e(String tag, Function0<String> lazyMsg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(lazyMsg, "lazyMsg");
        e(tag, null, lazyMsg);
    }

    public static /* synthetic */ void e$default(Logger logger, String str, Throwable th, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: e");
        }
        if ((i & 2) != 0) {
            th = null;
        }
        logger.e(str, th, function0);
    }

    public void e(String tag, Throwable throwable, Function0<String> lazyMsg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(lazyMsg, "lazyMsg");
        log$sfmcsdk_release(LogLevel.ERROR, tag, throwable, lazyMsg);
    }

    public static /* synthetic */ void log$sfmcsdk_release$default(Logger logger, LogLevel logLevel, String str, Throwable th, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
        if ((i & 4) != 0) {
            th = null;
        }
        logger.log$sfmcsdk_release(logLevel, str, th, function0);
    }

    public final void log$sfmcsdk_release(LogLevel lvl, String tag, Throwable t, Function0<String> lazyMsg) {
        Intrinsics.checkNotNullParameter(lvl, "lvl");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(lazyMsg, "lazyMsg");
        LogListener listener = getListener();
        if (listener == null || lvl.compareTo(getLogLevel()) < 0) {
            return;
        }
        try {
            listener.out(lvl, formatTag(tag), formatMsg(lazyMsg.invoke()), t);
        } catch (Exception e) {
            Log.e("~$Logger", "Exception was thrown by " + listener.getClass().getName(), e);
        }
    }

    private final String formatTag(String tag) {
        return tag.length() <= 23 ? tag : tag.subSequence(0, 23).toString();
    }

    private final String formatMsg(String msg) {
        if (TextUtils.getTrimmedLength(msg) == 0) {
            return "FORMATTED LOG MESSAGE WAS EMPTY";
        }
        for (String str : getRedactedValues()) {
            if (!Intrinsics.areEqual(REDACTED_VALUE_REPLACEMENT_TEXT, str)) {
                msg = StringsKt.replace(msg, str, REDACTED_VALUE_REPLACEMENT_TEXT, true);
            }
        }
        return msg;
    }
}
