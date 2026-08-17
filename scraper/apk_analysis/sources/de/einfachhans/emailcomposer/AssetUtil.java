package de.einfachhans.emailcomposer;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* loaded from: classes2.dex */
final class AssetUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ATTACHMENT_FOLDER = "/email_composer";
    private final Context ctx;

    AssetUtil(Context context) {
        this.ctx = context;
    }

    static void cleanupAttachmentFolder(Context context) {
        try {
            File file = new File(context.getExternalCacheDir() + ATTACHMENT_FOLDER);
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            }
        } catch (Exception unused) {
        }
    }

    Uri parse(String str, String str2, String str3) {
        str2.hashCode();
        switch (str2) {
            case "base64":
                return getUriForBase64Content(str, str3);
            case "resource":
                return getUriForResourcePath(str);
            case "asset":
                return getUriForAssetPath(str);
            case "absolute":
                return getUriForAbsolutePath(str);
            default:
                throw new RuntimeException("Unknown Attachment Type");
        }
    }

    private Uri getUriForAbsolutePath(String str) {
        File file = new File(str);
        if (!file.exists()) {
            throw new RuntimeException("File does not exist at absolute path");
        }
        return getUriForFile(this.ctx, file);
    }

    private Uri getUriForAssetPath(String str) {
        String str2 = "public/assets" + str;
        String substring = str2.substring(str2.lastIndexOf(47) + 1);
        File externalCacheDir = this.ctx.getExternalCacheDir();
        if (externalCacheDir == null) {
            return Uri.EMPTY;
        }
        String str3 = externalCacheDir.toString() + ATTACHMENT_FOLDER;
        File file = new File(str3, substring);
        new File(str3).mkdir();
        try {
            copyStream(this.ctx.getAssets().open(str2), new FileOutputStream(file));
            return getUriForFile(this.ctx, file);
        } catch (Exception unused) {
            throw new RuntimeException("File does not exist at assets");
        }
    }

    private Uri getUriForResourcePath(String str) {
        String substring = str.substring(str.lastIndexOf(47) + 1);
        String substring2 = substring.substring(0, substring.lastIndexOf(46));
        String substring3 = str.substring(str.lastIndexOf(46));
        File externalCacheDir = this.ctx.getExternalCacheDir();
        int resId = getResId(str);
        if (externalCacheDir == null) {
            return Uri.EMPTY;
        }
        if (resId == 0) {
            throw new RuntimeException("File does not exist at resources");
        }
        String str2 = externalCacheDir.toString() + ATTACHMENT_FOLDER;
        File file = new File(str2, substring2 + substring3);
        new File(str2).mkdir();
        try {
            copyStream(this.ctx.getResources().openRawResource(resId), new FileOutputStream(file));
            return getUriForFile(this.ctx, file);
        } catch (Exception unused) {
            throw new RuntimeException("File does not exist at resources");
        }
    }

    private Uri getUriForBase64Content(String str, String str2) {
        File externalCacheDir = this.ctx.getExternalCacheDir();
        if (externalCacheDir == null) {
            return Uri.EMPTY;
        }
        String str3 = externalCacheDir.toString() + ATTACHMENT_FOLDER;
        File file = new File(str3, str2);
        new File(str3).mkdir();
        try {
            copyStream(new ByteArrayInputStream(Base64.decode(str, 0)), new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getUriForFile(this.ctx, file);
    }

    private Uri getUriForFile(Context context, File file) {
        try {
            return Provider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        } catch (Exception unused) {
            throw new RuntimeException("Failed to get uri for file");
        }
    }

    private void copyStream(InputStream inputStream, FileOutputStream fileOutputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private int getResId(String str) {
        String str2;
        Resources resources = this.ctx.getResources();
        String packageName = this.ctx.getPackageName();
        if (str.contains("/")) {
            str2 = str.substring(0, str.lastIndexOf(47));
            str = str.substring(str.lastIndexOf(47) + 1);
        } else {
            str2 = "drawable";
        }
        String substring = str.substring(0, str.lastIndexOf(46));
        int identifier = resources.getIdentifier(substring, str2, packageName);
        if (identifier == 0) {
            identifier = resources.getIdentifier(substring, "mipmap", packageName);
        }
        return identifier == 0 ? resources.getIdentifier(substring, "drawable", packageName) : identifier;
    }
}
