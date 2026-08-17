package ch.coop.capacitor.accessibility;

import android.os.Build;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Accessibility")
/* loaded from: classes3.dex */
public class AccessibilityPlugin extends Plugin {
    static final String TAG = "AccessibilityPlugin";

    private boolean isOnOffSwitchLabelsEnabledInternal() {
        return false;
    }

    private boolean isReduceTransparencyEnabledInternal() {
        return false;
    }

    @PluginMethod
    public void settings(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("isOnOffSwitchLabelsEnabled", isOnOffSwitchLabelsEnabledInternal());
        jSObject.put("isReduceTransparencyEnabled", isReduceTransparencyEnabledInternal());
        jSObject.put("isDarkerSystemColorsEnabled", isDarkerSystemColorsEnabledInternal());
        jSObject.put("isBoldTextEnabled", isBoldTextEnabledInternal());
        jSObject.put("hasIncreasedTextSize", hasIncreasedTextSizeInternal());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void isOnOffSwitchLabelsEnabled(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", isOnOffSwitchLabelsEnabledInternal());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void isReduceTransparencyEnabled(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", isReduceTransparencyEnabledInternal());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void isDarkerSystemColorsEnabled(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", isDarkerSystemColorsEnabledInternal());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void isBoldTextEnabled(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", isBoldTextEnabledInternal());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void hasIncreasedTextSize(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", hasIncreasedTextSizeInternal());
        pluginCall.resolve(jSObject);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isDarkerSystemColorsEnabledInternal() {
        /*
            r4 = this;
            android.content.Context r4 = r4.getContext()
            if (r4 == 0) goto L4b
            java.lang.String r0 = "accessibility"
            java.lang.Object r4 = r4.getSystemService(r0)
            android.view.accessibility.AccessibilityManager r4 = (android.view.accessibility.AccessibilityManager) r4
            java.lang.String r0 = "AccessibilityPlugin"
            r1 = 0
            if (r4 == 0) goto L23
            java.lang.Class r2 = r4.getClass()     // Catch: java.lang.NoSuchMethodException -> L1e
            java.lang.String r3 = "isHighTextContrastEnabled"
            java.lang.reflect.Method r2 = r2.getMethod(r3, r1)     // Catch: java.lang.NoSuchMethodException -> L1e
            goto L24
        L1e:
            java.lang.String r2 = "isHighTextContrastEnabled not found in AccessibilityManager"
            android.util.Log.i(r0, r2)
        L23:
            r2 = r1
        L24:
            if (r2 == 0) goto L4b
            java.lang.Object r4 = r2.invoke(r4, r1)     // Catch: java.lang.Exception -> L35
            boolean r1 = r4 instanceof java.lang.Boolean     // Catch: java.lang.Exception -> L35
            if (r1 == 0) goto L4b
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch: java.lang.Exception -> L35
            boolean r4 = r4.booleanValue()     // Catch: java.lang.Exception -> L35
            return r4
        L35:
            r4 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "isHighTextContrastEnabled invoked with an exception"
            r1.<init>(r2)
            java.lang.String r4 = r4.getMessage()
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            android.util.Log.i(r0, r4)
        L4b:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.coop.capacitor.accessibility.AccessibilityPlugin.isDarkerSystemColorsEnabledInternal():boolean");
    }

    private boolean hasIncreasedTextSizeInternal() {
        return ((double) getContext().getResources().getConfiguration().fontScale) > 1.0d;
    }

    private boolean isBoldTextEnabledInternal() {
        int i;
        if (Build.VERSION.SDK_INT < 31) {
            return false;
        }
        i = getContext().getResources().getConfiguration().fontWeightAdjustment;
        return i > 0;
    }
}
