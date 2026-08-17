package com.capacitorjs.plugins.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.getcapacitor.Logger;

/* loaded from: classes3.dex */
public class Clipboard {
    private static final String TAG = "Clipboard";
    private ClipboardManager clipboard;
    private Context context;

    public Clipboard(Context context) {
        this.context = context;
        this.clipboard = (ClipboardManager) context.getSystemService("clipboard");
    }

    public ClipboardWriteResponse write(String str, String str2) {
        ClipboardManager clipboardManager;
        ClipData newPlainText = ClipData.newPlainText(str, str2);
        if (newPlainText != null && (clipboardManager = this.clipboard) != null) {
            try {
                clipboardManager.setPrimaryClip(newPlainText);
                return new ClipboardWriteResponse(true);
            } catch (Exception e) {
                Logger.error(TAG, e);
                return new ClipboardWriteResponse(false, "Writing to the clipboard failed");
            }
        }
        if (this.clipboard == null) {
            return new ClipboardWriteResponse(false, "Problem getting a reference to the system clipboard");
        }
        return new ClipboardWriteResponse(false, "Problem formatting data");
    }

    public ClipboardData read() {
        ClipboardManager clipboardManager = this.clipboard;
        CharSequence charSequence = null;
        if (clipboardManager == null) {
            return null;
        }
        String str = "text/plain";
        if (clipboardManager.hasPrimaryClip()) {
            if (this.clipboard.getPrimaryClipDescription().hasMimeType("text/plain")) {
                Logger.debug(TAG, "Got plaintxt");
                charSequence = this.clipboard.getPrimaryClip().getItemAt(0).getText();
            } else {
                Logger.debug(TAG, "Not plaintext!");
                charSequence = this.clipboard.getPrimaryClip().getItemAt(0).coerceToText(this.context).toString();
            }
        }
        ClipboardData clipboardData = new ClipboardData();
        if (charSequence != null) {
            clipboardData.setValue(charSequence.toString());
        }
        if (charSequence != null && charSequence.toString().startsWith("data:")) {
            str = charSequence.toString().split(";")[0].split(":")[1];
        }
        clipboardData.setType(str);
        return clipboardData;
    }
}
