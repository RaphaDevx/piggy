package io.gsonfire;

import com.google.gson.JsonElement;

/* loaded from: classes2.dex */
public interface TypeSelector<T> {
    Class<? extends T> getClassForElement(JsonElement jsonElement);
}
