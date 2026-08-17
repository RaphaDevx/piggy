package com.byteowls.capacitor.filesharer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

@CapacitorPlugin(name = "FileSharer")
/* loaded from: classes3.dex */
public class FileSharerPlugin extends Plugin {
    private static final String CAP_FILESHARER_TEMP = "capfilesharer";
    private static final String ERR_FILE_CACHING_FAILED = "ERR_FILE_CACHING_FAILED";
    private static final String ERR_LOCAL_FILE_NOT_FOUND = "ERR_LOCAL_FILE_NOT_FOUND";
    private static final String ERR_PARAM_DATA_INVALID = "ERR_PARAM_DATA_INVALID";
    private static final String ERR_PARAM_NO_CONTENT_TYPE = "ERR_PARAM_NO_CONTENT_TYPE";
    private static final String ERR_PARAM_NO_DATA = "ERR_PARAM_NO_DATA";
    private static final String ERR_PARAM_NO_FILENAME = "ERR_PARAM_NO_FILENAME";
    private static final String PARAM_ANDROID_CHOOSER = "android.titlechooser";
    private static final String PARAM_BASE64_DATA = "base64Data";
    private static final String PARAM_CONTENT_TYPE = "contentType";
    private static final String PARAM_FILENAME = "filename";
    private static final String PARAM_LOCAL_PATH = "path";
    private static final String USER_CANCELLED = "USER_CANCELLED";
    private String callbackId;

    @PluginMethod
    public void share(PluginCall pluginCall) {
        String str = getContext().getPackageName() + ".filesharer.fileprovider";
        this.callbackId = pluginCall.getCallbackId();
        JSObject data = pluginCall.getData();
        String paramString = ConfigUtils.getParamString(data, "filename");
        if (paramString == null || paramString.length() == 0) {
            pluginCall.reject(ERR_PARAM_NO_FILENAME);
            return;
        }
        String paramString2 = ConfigUtils.getParamString(data, PARAM_CONTENT_TYPE);
        if (paramString2 == null || paramString2.length() == 0) {
            pluginCall.reject(ERR_PARAM_NO_CONTENT_TYPE);
            return;
        }
        String paramString3 = ConfigUtils.getParamString(data, PARAM_BASE64_DATA);
        if (paramString3 == null || paramString3.length() == 0) {
            String paramString4 = ConfigUtils.getParamString(data, "path");
            if (paramString4 != null && paramString4.length() > 0) {
                String str2 = paramString4.split("_capacitor_file_")[1];
                if (str2 != null) {
                    paramString4 = str2;
                }
                try {
                    paramString3 = readFileAsBase64EncodedData(new FileInputStream(paramString4));
                } catch (IOException e) {
                    Log.e(getLogTag(), e.getMessage());
                    pluginCall.reject(ERR_LOCAL_FILE_NOT_FOUND);
                    return;
                }
            } else {
                pluginCall.reject(ERR_PARAM_NO_DATA);
                return;
            }
        }
        String paramString5 = ConfigUtils.getParamString(data, PARAM_ANDROID_CHOOSER);
        this.bridge.saveCall(pluginCall);
        File file = new File(getCacheDir(), paramString);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(Base64.decode(paramString3, 0));
                fileOutputStream.flush();
                fileOutputStream.close();
                if (file.exists()) {
                    Uri uriForFile = FileSharerProvider.getUriForFile(getContext(), str, file);
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.STREAM", uriForFile);
                    intent.setTypeAndNormalize(paramString2);
                    intent.addFlags(1);
                    intent.addFlags(524288);
                    intent.addFlags(268435456);
                    ArrayList arrayList = new ArrayList();
                    PackageManager packageManager = getContext().getPackageManager();
                    for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
                        String str3 = resolveInfo.activityInfo.packageName;
                        Intent intent2 = new Intent();
                        intent2.setComponent(new ComponentName(str3, resolveInfo.activityInfo.name));
                        intent2.setPackage(str3);
                        intent2.setAction("android.intent.action.SEND");
                        intent2.putExtra("android.intent.extra.STREAM", uriForFile);
                        intent2.setTypeAndNormalize(paramString2);
                        arrayList.add(new LabeledIntent(intent2, str3, resolveInfo.loadLabel(packageManager), resolveInfo.getIconResource()));
                    }
                    Intent createChooser = Intent.createChooser(intent, paramString5);
                    createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new LabeledIntent[0]));
                    startActivityForResult(pluginCall, createChooser, "callbackComplete");
                    return;
                }
                Log.e(getLogTag(), "Cached file does not exist!");
                pluginCall.reject(ERR_FILE_CACHING_FAILED);
            } finally {
            }
        } catch (IOException e2) {
            Log.e(getLogTag(), e2.getMessage());
            pluginCall.reject(ERR_FILE_CACHING_FAILED);
        } catch (IllegalArgumentException e3) {
            Log.e(getLogTag(), e3.getMessage());
            pluginCall.reject(ERR_PARAM_DATA_INVALID);
        }
    }

    public String readFileAsBase64EncodedData(InputStream inputStream) throws IOException {
        FileInputStream fileInputStream = (FileInputStream) inputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                fileInputStream.close();
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            }
        }
    }

    private File getCacheDir() {
        File file = new File(getContext().getFilesDir(), CAP_FILESHARER_TEMP);
        if (!file.exists()) {
            file.mkdirs();
        } else if (file.listFiles() != null) {
            for (File file2 : (File[]) Objects.requireNonNull(file.listFiles())) {
                file2.delete();
            }
        }
        return file;
    }

    @ActivityCallback
    private void callbackComplete(PluginCall pluginCall, ActivityResult activityResult) {
        if (activityResult.getResultCode() == 0) {
            pluginCall.reject(USER_CANCELLED);
        } else {
            this.bridge.getSavedCall(this.callbackId).resolve();
        }
    }
}
