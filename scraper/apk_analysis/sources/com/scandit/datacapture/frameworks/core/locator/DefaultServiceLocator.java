package com.scandit.datacapture.frameworks.core.locator;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.FrameworkModule;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultServiceLocator.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\tH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\u0006H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/locator/DefaultServiceLocator;", "Lcom/scandit/datacapture/frameworks/core/locator/ServiceLocator;", "Lcom/scandit/datacapture/frameworks/core/FrameworkModule;", "()V", "services", "", "", "", "register", "", "module", "remove", "clazzName", "removeAll", "resolve", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultServiceLocator implements ServiceLocator<FrameworkModule> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DefaultServiceLocator instance = new DefaultServiceLocator();
    private final Map<String, Object> services = new LinkedHashMap();

    @JvmStatic
    public static final DefaultServiceLocator getInstance() {
        return INSTANCE.getInstance();
    }

    @Override // com.scandit.datacapture.frameworks.core.locator.ServiceLocator
    public synchronized void register(FrameworkModule module) {
        Intrinsics.checkNotNullParameter(module, "module");
        Map<String, Object> map = this.services;
        String name = module.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        map.put(name, module);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.scandit.datacapture.frameworks.core.locator.ServiceLocator
    public synchronized FrameworkModule resolve(String clazzName) {
        Object obj;
        Intrinsics.checkNotNullParameter(clazzName, "clazzName");
        obj = this.services.get(clazzName);
        return obj instanceof FrameworkModule ? (FrameworkModule) obj : null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.scandit.datacapture.frameworks.core.locator.ServiceLocator
    public synchronized FrameworkModule remove(String clazzName) {
        Object remove;
        Intrinsics.checkNotNullParameter(clazzName, "clazzName");
        remove = this.services.remove(clazzName);
        return remove instanceof FrameworkModule ? (FrameworkModule) remove : null;
    }

    @Override // com.scandit.datacapture.frameworks.core.locator.ServiceLocator
    public synchronized void removeAll() {
        this.services.clear();
    }

    /* compiled from: DefaultServiceLocator.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/locator/DefaultServiceLocator$Companion;", "", "()V", "instance", "Lcom/scandit/datacapture/frameworks/core/locator/DefaultServiceLocator;", "getInstance", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DefaultServiceLocator getInstance() {
            return DefaultServiceLocator.instance;
        }
    }
}
