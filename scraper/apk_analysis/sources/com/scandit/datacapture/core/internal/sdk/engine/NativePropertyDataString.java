package com.scandit.datacapture.core.internal.sdk.engine;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class NativePropertyDataString {
    final String defaultValue;
    final ArrayList<String> supportedValues;

    public NativePropertyDataString(String str, ArrayList<String> arrayList) {
        this.defaultValue = str;
        this.supportedValues = arrayList;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public ArrayList<String> getSupportedValues() {
        return this.supportedValues;
    }

    public String toString() {
        return "NativePropertyDataString{defaultValue=" + this.defaultValue + ",supportedValues=" + this.supportedValues + "}";
    }
}
