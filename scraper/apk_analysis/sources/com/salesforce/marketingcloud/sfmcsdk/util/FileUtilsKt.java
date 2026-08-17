package com.salesforce.marketingcloud.sfmcsdk.util;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.http.RequestKt;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: FileUtils.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u001a\u0010\t\u001a\u0004\u0018\u00010\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a \u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0012H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"SDK_PACKAGE_PREFIX", "", "getFilenameForModuleInstallation", "filename", "moduleApplicationId", "registrationId", "getFilenamePrefixForModule", "getFilenamePrefixForModuleInstallation", "getFilenamePrefixForSFMCSdk", "retrieveModuleKey", "context", "Landroid/content/Context;", "storeModuleKey", "", "moduleName", "keyValue", "wipeModuleFiles", "readAll", "Ljava/io/InputStream;", "sfmcsdk_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FileUtilsKt {
    public static final String SDK_PACKAGE_PREFIX = "com.salesforce.marketingcloud";

    public static final String getFilenamePrefixForSFMCSdk(String filename) {
        Intrinsics.checkNotNullParameter(filename, "filename");
        return "com.salesforce.marketingcloud_sfmcsdk_" + filename;
    }

    public static final String getFilenamePrefixForModule(String moduleApplicationId) {
        Intrinsics.checkNotNullParameter(moduleApplicationId, "moduleApplicationId");
        return "com.salesforce.marketingcloud_" + moduleApplicationId;
    }

    public static final String getFilenamePrefixForModuleInstallation(String moduleApplicationId, String registrationId) {
        Intrinsics.checkNotNullParameter(moduleApplicationId, "moduleApplicationId");
        Intrinsics.checkNotNullParameter(registrationId, "registrationId");
        return getFilenamePrefixForModule(moduleApplicationId) + "_" + registrationId;
    }

    public static final String getFilenameForModuleInstallation(String filename, String moduleApplicationId, String registrationId) {
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(moduleApplicationId, "moduleApplicationId");
        Intrinsics.checkNotNullParameter(registrationId, "registrationId");
        return getFilenamePrefixForModuleInstallation(moduleApplicationId, registrationId) + "_" + filename;
    }

    public static final void storeModuleKey(Context context, String moduleName, String keyValue) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(keyValue, "keyValue");
        try {
            FilesKt.writeText$default(new File(context.getNoBackupFilesDir(), moduleName), keyValue, null, 2, null);
        } catch (Exception unused) {
            SFMCSdkLogger.INSTANCE.e("~$SFMCSdkStorage", new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt$storeModuleKey$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Failed to write module key to file";
                }
            });
        }
    }

    public static final String retrieveModuleKey(Context context, String moduleApplicationId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleApplicationId, "moduleApplicationId");
        try {
            File file = new File(context.getNoBackupFilesDir(), moduleApplicationId);
            if (file.exists()) {
                String readText$default = FilesKt.readText$default(file, null, 1, null);
                if (!StringsKt.isBlank(readText$default)) {
                    return readText$default;
                }
            }
        } catch (Exception unused) {
            SFMCSdkLogger.INSTANCE.e("~$SFMCSdkStorage", new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt$retrieveModuleKey$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Caught exception trying to retrieve module key from file";
                }
            });
        }
        return null;
    }

    public static final void wipeModuleFiles(Context context, final String moduleApplicationId) {
        File[] listFiles;
        File[] listFiles2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleApplicationId, "moduleApplicationId");
        try {
            File parentFile = context.getDatabasePath(moduleApplicationId).getParentFile();
            if (parentFile != null && parentFile.isDirectory() && (listFiles2 = parentFile.listFiles(new FilenameFilter() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt$$ExternalSyntheticLambda0
                @Override // java.io.FilenameFilter
                public final boolean accept(File file, String str) {
                    boolean wipeModuleFiles$lambda$0;
                    wipeModuleFiles$lambda$0 = FileUtilsKt.wipeModuleFiles$lambda$0(moduleApplicationId, file, str);
                    return wipeModuleFiles$lambda$0;
                }
            })) != null) {
                for (final File file : listFiles2) {
                    SFMCSdkLogger.INSTANCE.w("~$SFMCSdkStorage", new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt$wipeModuleFiles$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Deleting Database File: " + file.getName();
                        }
                    });
                    file.delete();
                }
            }
        } catch (Exception unused) {
            SFMCSdkLogger.INSTANCE.w("~$SFMCSdkStorage", new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt$wipeModuleFiles$3
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Caught exception trying to delete database file";
                }
            });
        }
        try {
            File file2 = new File(context.getFilesDir().getParent() + "/shared_prefs");
            if (!file2.isDirectory() || (listFiles = file2.listFiles(new FilenameFilter() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt$$ExternalSyntheticLambda1
                @Override // java.io.FilenameFilter
                public final boolean accept(File file3, String str) {
                    boolean wipeModuleFiles$lambda$2;
                    wipeModuleFiles$lambda$2 = FileUtilsKt.wipeModuleFiles$lambda$2(moduleApplicationId, file3, str);
                    return wipeModuleFiles$lambda$2;
                }
            })) == null) {
                return;
            }
            for (final File file3 : listFiles) {
                SFMCSdkLogger.INSTANCE.w("~$SFMCSdkStorage", new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt$wipeModuleFiles$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Deleting SharedPreferences File: " + file3.getName();
                    }
                });
                file3.delete();
            }
        } catch (Exception unused2) {
            SFMCSdkLogger.INSTANCE.w("~$SFMCSdkStorage", new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt$wipeModuleFiles$6
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Caught exception trying to delete SharedPreferences file";
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean wipeModuleFiles$lambda$0(String moduleApplicationId, File file, String str) {
        Intrinsics.checkNotNullParameter(moduleApplicationId, "$moduleApplicationId");
        Intrinsics.checkNotNull(str);
        return StringsKt.startsWith$default(str, getFilenamePrefixForModule(moduleApplicationId), false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean wipeModuleFiles$lambda$2(String moduleApplicationId, File file, String str) {
        Intrinsics.checkNotNullParameter(moduleApplicationId, "$moduleApplicationId");
        Intrinsics.checkNotNull(str);
        return StringsKt.startsWith$default(str, getFilenamePrefixForModule(moduleApplicationId), false, 2, (Object) null);
    }

    public static final String readAll(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, RequestKt.getUTF_8()));
        try {
            BufferedReader bufferedReader2 = bufferedReader;
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                sb.append(readLine);
                sb.append('\n');
            }
            String sb2 = sb.toString();
            CloseableKt.closeFinally(bufferedReader, null);
            return sb2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(bufferedReader, th);
                throw th2;
            }
        }
    }
}
