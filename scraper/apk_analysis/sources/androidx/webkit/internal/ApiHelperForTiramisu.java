package androidx.webkit.internal;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

/* loaded from: classes3.dex */
public class ApiHelperForTiramisu {
    private ApiHelperForTiramisu() {
    }

    static PackageManager.ComponentInfoFlags of(long j) {
        PackageManager.ComponentInfoFlags of;
        of = PackageManager.ComponentInfoFlags.of(j);
        return of;
    }

    static ServiceInfo getServiceInfo(PackageManager packageManager, ComponentName componentName, PackageManager.ComponentInfoFlags componentInfoFlags) throws PackageManager.NameNotFoundException {
        ServiceInfo serviceInfo;
        serviceInfo = packageManager.getServiceInfo(componentName, componentInfoFlags);
        return serviceInfo;
    }
}
