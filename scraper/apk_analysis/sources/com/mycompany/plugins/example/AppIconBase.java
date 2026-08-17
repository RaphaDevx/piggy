package com.mycompany.plugins.example;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.getcapacitor.JSArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class AppIconBase {
    private String activeIconName;
    private Activity activity;
    private String classPackageName;
    private Context context;
    private List<String> disableIconNames = new ArrayList();
    private String packageName;
    PackageManager pm;

    public AppIconBase(Activity activity, Context context) {
        this.activeIconName = "";
        this.activity = activity;
        this.context = context;
        this.packageName = context.getPackageName();
        String name = activity.getClass().getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf != -1) {
            this.classPackageName = name.substring(0, lastIndexOf);
        } else {
            this.classPackageName = "";
        }
        this.pm = context.getApplicationContext().getPackageManager();
        this.activeIconName = "";
    }

    public Boolean isSupported() {
        return true;
    }

    public String getName() {
        ComponentName component = this.pm.getLaunchIntentForPackage(this.context.getPackageName()).getComponent();
        int componentEnabledSetting = this.pm.getComponentEnabledSetting(component);
        if (componentEnabledSetting != 1 && componentEnabledSetting != 0) {
            return null;
        }
        String shortClassName = component.getShortClassName();
        if (Objects.equals(shortClassName, ".MainActivity")) {
            return null;
        }
        return shortClassName.substring(1);
    }

    public void change(String str, JSArray jSArray) {
        try {
            List<String> list = jSArray.toList();
            this.pm.setComponentEnabledSetting(new ComponentName(this.packageName, this.classPackageName + "." + str), 1, 1);
            for (String str2 : list) {
                Log.i("AppIconBase", this.packageName + "." + str2);
                this.pm.setComponentEnabledSetting(new ComponentName(this.packageName, this.classPackageName + "." + str2), 2, 1);
            }
            this.pm.setComponentEnabledSetting(new ComponentName(this.packageName, this.classPackageName + ".MainActivity"), 2, 1);
        } catch (JSONException unused) {
        }
    }

    public void reset(JSArray jSArray) {
        try {
            List<String> list = jSArray.toList();
            this.pm.setComponentEnabledSetting(new ComponentName(this.packageName, this.classPackageName + ".MainActivity"), 1, 1);
            for (String str : list) {
                Log.i("AppIconBaseReset", this.packageName + "." + str);
                Log.i("AppIconBaseReset", this.classPackageName + "." + str);
                this.pm.setComponentEnabledSetting(new ComponentName(this.packageName, this.classPackageName + "." + str), 2, 1);
            }
        } catch (JSONException unused) {
        }
    }
}
