package io.gsonfire.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface ExposeMethodResult {

    public enum ConflictResolutionStrategy {
        OVERWRITE,
        SKIP
    }

    ConflictResolutionStrategy conflictResolution() default ConflictResolutionStrategy.OVERWRITE;

    String value();
}
