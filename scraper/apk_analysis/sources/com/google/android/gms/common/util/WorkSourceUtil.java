package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
/* loaded from: classes3.dex */
public class WorkSourceUtil {
    private static final int zza = Process.myUid();
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;
    private static final Method zze;
    private static final Method zzf;
    private static final Method zzg;
    private static final Method zzh;
    private static final Method zzi;
    private static Boolean zzj;

    /* JADX WARN: Can't wrap try/catch for region: R(26:0|1|(2:2|3)|4|(22:55|56|7|8|9|10|11|12|13|(13:47|48|16|(10:42|43|19|(7:37|38|22|(7:28|29|30|31|32|25|26)|24|25|26)|21|22|(0)|24|25|26)|18|19|(0)|21|22|(0)|24|25|26)|15|16|(0)|18|19|(0)|21|22|(0)|24|25|26)|6|7|8|9|10|11|12|13|(0)|15|16|(0)|18|19|(0)|21|22|(0)|24|25|26) */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x004c, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x003a, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            java.lang.String r0 = "add"
            int r1 = android.os.Process.myUid()
            com.google.android.gms.common.util.WorkSourceUtil.zza = r1
            r1 = 0
            java.lang.Class<android.os.WorkSource> r2 = android.os.WorkSource.class
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L16
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch: java.lang.Exception -> L16
            java.lang.reflect.Method r2 = r2.getMethod(r0, r3)     // Catch: java.lang.Exception -> L16
            goto L17
        L16:
            r2 = r1
        L17:
            com.google.android.gms.common.util.WorkSourceUtil.zzb = r2
            boolean r2 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR2()
            if (r2 == 0) goto L2e
            java.lang.Class<android.os.WorkSource> r2 = android.os.WorkSource.class
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L2e
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            java.lang.Class[] r3 = new java.lang.Class[]{r3, r4}     // Catch: java.lang.Exception -> L2e
            java.lang.reflect.Method r0 = r2.getMethod(r0, r3)     // Catch: java.lang.Exception -> L2e
            goto L2f
        L2e:
            r0 = r1
        L2f:
            com.google.android.gms.common.util.WorkSourceUtil.zzc = r0
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r2 = "size"
            java.lang.reflect.Method r0 = r0.getMethod(r2, r1)     // Catch: java.lang.Exception -> L3a
            goto L3b
        L3a:
            r0 = r1
        L3b:
            com.google.android.gms.common.util.WorkSourceUtil.zzd = r0
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r2 = "get"
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L4c
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch: java.lang.Exception -> L4c
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch: java.lang.Exception -> L4c
            goto L4d
        L4c:
            r0 = r1
        L4d:
            com.google.android.gms.common.util.WorkSourceUtil.zze = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR2()
            if (r0 == 0) goto L64
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r2 = "getName"
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L64
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch: java.lang.Exception -> L64
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch: java.lang.Exception -> L64
            goto L65
        L64:
            r0 = r1
        L65:
            com.google.android.gms.common.util.WorkSourceUtil.zzf = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            java.lang.String r2 = "WorkSourceUtil"
            if (r0 == 0) goto L7e
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r3 = "createWorkChain"
            java.lang.reflect.Method r0 = r0.getMethod(r3, r1)     // Catch: java.lang.Exception -> L78
            goto L7f
        L78:
            r0 = move-exception
            java.lang.String r3 = "Missing WorkChain API createWorkChain"
            android.util.Log.w(r2, r3, r0)
        L7e:
            r0 = r1
        L7f:
            com.google.android.gms.common.util.WorkSourceUtil.zzg = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            if (r0 == 0) goto La2
            java.lang.String r0 = "android.os.WorkSource$WorkChain"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Exception -> L9c
            java.lang.String r3 = "addNode"
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L9c
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            java.lang.Class[] r4 = new java.lang.Class[]{r4, r5}     // Catch: java.lang.Exception -> L9c
            java.lang.reflect.Method r0 = r0.getMethod(r3, r4)     // Catch: java.lang.Exception -> L9c
            goto La3
        L9c:
            r0 = move-exception
            java.lang.String r3 = "Missing WorkChain class"
            android.util.Log.w(r2, r3, r0)
        La2:
            r0 = r1
        La3:
            com.google.android.gms.common.util.WorkSourceUtil.zzh = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            if (r0 == 0) goto Lb8
            java.lang.Class<android.os.WorkSource> r0 = android.os.WorkSource.class
            java.lang.String r2 = "isEmpty"
            java.lang.reflect.Method r0 = r0.getMethod(r2, r1)     // Catch: java.lang.Exception -> Lb8
            r2 = 1
            r0.setAccessible(r2)     // Catch: java.lang.Exception -> Lb9
            goto Lb9
        Lb8:
            r0 = r1
        Lb9:
            com.google.android.gms.common.util.WorkSourceUtil.zzi = r0
            com.google.android.gms.common.util.WorkSourceUtil.zzj = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.WorkSourceUtil.<clinit>():void");
    }

    private WorkSourceUtil() {
    }

    public static void add(WorkSource workSource, int i, String str) {
        Method method = zzc;
        if (method != null) {
            if (str == null) {
                str = "";
            }
            try {
                method.invoke(workSource, Integer.valueOf(i), str);
                return;
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
                return;
            }
        }
        Method method2 = zzb;
        if (method2 != null) {
            try {
                method2.invoke(workSource, Integer.valueOf(i));
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        }
    }

    public static WorkSource fromPackage(Context context, String str) {
        if (context != null && context.getPackageManager() != null && str != null) {
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
                    return null;
                }
                int i = applicationInfo.uid;
                WorkSource workSource = new WorkSource();
                add(workSource, i, str);
                return workSource;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
            }
        }
        return null;
    }

    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) {
        Method method;
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int i = -1;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
            } else {
                i = applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
        }
        if (i < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        Method method2 = zzg;
        if (method2 == null || (method = zzh) == null) {
            add(workSource, i, str);
        } else {
            try {
                Object invoke = method2.invoke(workSource, null);
                int i2 = zza;
                if (i != i2) {
                    method.invoke(invoke, Integer.valueOf(i), str);
                }
                method.invoke(invoke, Integer.valueOf(i2), str2);
            } catch (Exception e) {
                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e);
            }
        }
        return workSource;
    }

    public static int get(WorkSource workSource, int i) {
        Method method = zze;
        if (method == null) {
            return 0;
        }
        try {
            Object invoke = method.invoke(workSource, Integer.valueOf(i));
            Preconditions.checkNotNull(invoke);
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            return 0;
        }
    }

    public static String getName(WorkSource workSource, int i) {
        Method method = zzf;
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke(workSource, Integer.valueOf(i));
        } catch (Exception e) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            return null;
        }
    }

    public static List<String> getNames(WorkSource workSource) {
        ArrayList arrayList = new ArrayList();
        int size = workSource == null ? 0 : size(workSource);
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                String name = getName(workSource, i);
                if (!Strings.isEmptyOrWhitespace(name)) {
                    Preconditions.checkNotNull(name);
                    arrayList.add(name);
                }
            }
        }
        return arrayList;
    }

    public static synchronized boolean hasWorkSourcePermission(Context context) {
        synchronized (WorkSourceUtil.class) {
            Boolean bool = zzj;
            if (bool != null) {
                return bool.booleanValue();
            }
            if (context == null) {
                return false;
            }
            boolean z = ContextCompat.checkSelfPermission(context, "android.permission.UPDATE_DEVICE_STATS") == 0;
            Boolean valueOf = Boolean.valueOf(z);
            zzj = valueOf;
            valueOf.getClass();
            return z;
        }
    }

    public static boolean isEmpty(WorkSource workSource) {
        Method method = zzi;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, null);
                Preconditions.checkNotNull(invoke);
                return ((Boolean) invoke).booleanValue();
            } catch (Exception e) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e);
            }
        }
        return size(workSource) == 0;
    }

    public static int size(WorkSource workSource) {
        Method method = zzd;
        if (method == null) {
            return 0;
        }
        try {
            Object invoke = method.invoke(workSource, null);
            Preconditions.checkNotNull(invoke);
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            return 0;
        }
    }
}
