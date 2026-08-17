package ch.datatrans.payment;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import java.net.URLEncoder;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class wd {
    public final Context a;
    public final String b;

    public wd(Context context) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
            str = String.valueOf(applicationInfo.targetSdkVersion);
        } catch (PackageManager.NameNotFoundException unused) {
            str = "";
        }
        this.b = str;
    }

    public static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append('=');
        String encode = URLEncoder.encode(str2, "UTF-8");
        Intrinsics.checkNotNullExpressionValue(encode, "encode(...)");
        sb.append(encode);
        return sb.toString();
    }

    public final Map b() {
        Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("uppModuleName", "android-sdk"), TuplesKt.to("uppModuleVersion", "3.9.2"), TuplesKt.to("uppLibDeviceManufacturer", Build.MANUFACTURER), TuplesKt.to("uppLibDeviceModel", Build.MODEL), TuplesKt.to("uppLibOSVersion", Build.VERSION.RELEASE), TuplesKt.to("uppLibOSVersionNumeric", String.valueOf(Build.VERSION.SDK_INT)), TuplesKt.to("uppLibDeploymentTarget", this.b));
        R9 preferencesRepository = new R9(this.a);
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        Intrinsics.checkNotNullParameter("DTPL_INSTALLATION_ID", "key");
        String string = preferencesRepository.a.getString("DTPL_INSTALLATION_ID", null);
        if (string != null) {
            mutableMapOf.put("installationId", string);
        }
        return mutableMapOf;
    }

    public final String c() {
        R9 preferencesRepository = new R9(this.a);
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        Intrinsics.checkNotNullParameter("DTPL_INSTALLATION_ID", "key");
        String str = null;
        if (preferencesRepository.a.getString("DTPL_INSTALLATION_ID", null) != null) {
            R9 preferencesRepository2 = new R9(this.a);
            Intrinsics.checkNotNullParameter(preferencesRepository2, "preferencesRepository");
            Intrinsics.checkNotNullParameter("DTPL_INSTALLATION_ID", "key");
            String string = preferencesRepository2.a.getString("DTPL_INSTALLATION_ID", null);
            Intrinsics.checkNotNull(string);
            str = a("installationId", string);
        }
        String a = a("moduleName", "android-sdk");
        String a2 = a("moduleVersion", "3.9.2");
        String MANUFACTURER = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
        String a3 = a("deviceManufacturer", MANUFACTURER);
        String MODEL = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
        String a4 = a(LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_DEVICE_MODEL, MODEL);
        String RELEASE = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
        return CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) new String[]{a, a2, a3, a4, a(LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_OS_VERSION, RELEASE), a("osVersionNumeric", String.valueOf(Build.VERSION.SDK_INT)), str}), ";", null, null, 0, null, null, 62, null);
    }
}
