package com.scandit.datacapture.frameworks.core.locator;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: ServiceLocator.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\u0004\u0018\u00018\u00002\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\u0004H&J\u0017\u0010\f\u001a\u0004\u0018\u00018\u00002\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\n¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/locator/ServiceLocator;", ExifInterface.GPS_DIRECTION_TRUE, "", "register", "", "module", "(Ljava/lang/Object;)V", "remove", "clazzName", "", "(Ljava/lang/String;)Ljava/lang/Object;", "removeAll", "resolve", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface ServiceLocator<T> {
    void register(T module);

    T remove(String clazzName);

    void removeAll();

    T resolve(String clazzName);
}
