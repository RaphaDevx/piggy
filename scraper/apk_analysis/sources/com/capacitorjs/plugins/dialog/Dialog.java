package com.capacitorjs.plugins.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import com.capacitorjs.plugins.dialog.Dialog;

/* loaded from: classes3.dex */
public class Dialog {

    public interface OnCancelListener {
        void onCancel();
    }

    public interface OnResultListener {
        void onResult(boolean z, boolean z2, String str);
    }

    public static void alert(Context context, String str, OnResultListener onResultListener) {
        alert(context, str, null, null, onResultListener);
    }

    public static void alert(final Context context, final String str, final String str2, String str3, final OnResultListener onResultListener) {
        if (str3 == null) {
            str3 = "OK";
        }
        final String str4 = str3;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Dialog.lambda$alert$2(context, str2, str, str4, onResultListener);
            }
        });
    }

    static /* synthetic */ void lambda$alert$2(Context context, String str, String str2, String str3, final OnResultListener onResultListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (str != null) {
            builder.setTitle(str);
        }
        builder.setMessage(str2).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Dialog.lambda$alert$0(Dialog.OnResultListener.this, dialogInterface, i);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                Dialog.lambda$alert$1(Dialog.OnResultListener.this, dialogInterface);
            }
        });
        builder.create().show();
    }

    static /* synthetic */ void lambda$alert$0(OnResultListener onResultListener, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        onResultListener.onResult(true, false, null);
    }

    static /* synthetic */ void lambda$alert$1(OnResultListener onResultListener, DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        onResultListener.onResult(false, true, null);
    }

    public static void confirm(Context context, String str, OnResultListener onResultListener) {
        confirm(context, str, null, null, null, onResultListener);
    }

    public static void confirm(final Context context, final String str, final String str2, String str3, String str4, final OnResultListener onResultListener) {
        if (str3 == null) {
            str3 = "OK";
        }
        final String str5 = str3;
        if (str4 == null) {
            str4 = "Cancel";
        }
        final String str6 = str4;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                Dialog.lambda$confirm$6(context, str2, str, str5, onResultListener, str6);
            }
        });
    }

    static /* synthetic */ void lambda$confirm$6(Context context, String str, String str2, String str3, final OnResultListener onResultListener, String str4) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (str != null) {
            builder.setTitle(str);
        }
        builder.setMessage(str2).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Dialog.lambda$confirm$3(Dialog.OnResultListener.this, dialogInterface, i);
            }
        }).setNegativeButton(str4, new DialogInterface.OnClickListener() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Dialog.lambda$confirm$4(Dialog.OnResultListener.this, dialogInterface, i);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                Dialog.lambda$confirm$5(Dialog.OnResultListener.this, dialogInterface);
            }
        });
        builder.create().show();
    }

    static /* synthetic */ void lambda$confirm$3(OnResultListener onResultListener, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        onResultListener.onResult(true, false, null);
    }

    static /* synthetic */ void lambda$confirm$4(OnResultListener onResultListener, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        onResultListener.onResult(false, false, null);
    }

    static /* synthetic */ void lambda$confirm$5(OnResultListener onResultListener, DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        onResultListener.onResult(false, true, null);
    }

    public static void prompt(Context context, String str, OnResultListener onResultListener) {
        prompt(context, str, null, null, null, null, null, onResultListener);
    }

    public static void prompt(final Context context, final String str, final String str2, String str3, String str4, String str5, String str6, final OnResultListener onResultListener) {
        final String str7 = str3 == null ? "OK" : str3;
        final String str8 = str4 == null ? "Cancel" : str4;
        final String str9 = str5 == null ? "" : str5;
        final String str10 = str6 == null ? "" : str6;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Dialog.lambda$prompt$10(context, str9, str10, str2, str, str7, onResultListener, str8);
            }
        });
    }

    static /* synthetic */ void lambda$prompt$10(Context context, String str, String str2, String str3, String str4, String str5, final OnResultListener onResultListener, String str6) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText editText = new EditText(context);
        editText.setHint(str);
        editText.setText(str2);
        if (str3 != null) {
            builder.setTitle(str3);
        }
        builder.setMessage(str4).setView(editText).setPositiveButton(str5, new DialogInterface.OnClickListener() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Dialog.lambda$prompt$7(editText, onResultListener, dialogInterface, i);
            }
        }).setNegativeButton(str6, new DialogInterface.OnClickListener() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Dialog.lambda$prompt$8(Dialog.OnResultListener.this, dialogInterface, i);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.capacitorjs.plugins.dialog.Dialog$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                Dialog.lambda$prompt$9(Dialog.OnResultListener.this, dialogInterface);
            }
        });
        builder.create().show();
    }

    static /* synthetic */ void lambda$prompt$7(EditText editText, OnResultListener onResultListener, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        onResultListener.onResult(true, false, editText.getText().toString().trim());
    }

    static /* synthetic */ void lambda$prompt$8(OnResultListener onResultListener, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        onResultListener.onResult(false, true, null);
    }

    static /* synthetic */ void lambda$prompt$9(OnResultListener onResultListener, DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        onResultListener.onResult(false, true, null);
    }
}
