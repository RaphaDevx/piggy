package com.scandit.datacapture.frameworks.core.method;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: FrameworksMethodCall.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001d\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0002\u0010\fJ\u0013\u0010\u0002\u001a\u0002H\n\"\u0004\b\u0000\u0010\nH&¢\u0006\u0002\u0010\u0004J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/method/FrameworksMethodCall;", "", "arguments", "getArguments", "()Ljava/lang/Object;", "method", "", "getMethod", "()Ljava/lang/String;", "argument", ExifInterface.GPS_DIRECTION_TRUE, "key", "(Ljava/lang/String;)Ljava/lang/Object;", "hasArgument", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface FrameworksMethodCall {
    <T> T argument(String key);

    <T> T arguments();

    Object getArguments();

    String getMethod();

    boolean hasArgument(String key);
}
