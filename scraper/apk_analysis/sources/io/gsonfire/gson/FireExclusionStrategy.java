package io.gsonfire.gson;

import io.gsonfire.postprocessors.methodinvoker.MappedMethod;

/* loaded from: classes2.dex */
public interface FireExclusionStrategy {
    boolean shouldSkipMethod(MappedMethod mappedMethod);
}
