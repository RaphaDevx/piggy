package org.openapitools.jackson.nullable;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public class JsonNullable<T> implements Serializable {
    private static final JsonNullable<?> UNDEFINED = new JsonNullable<>(null, false);
    private static final long serialVersionUID = 1;
    private final boolean isPresent;
    private final T value;

    private JsonNullable(T t, boolean z) {
        this.value = t;
        this.isPresent = z;
    }

    public static <T> JsonNullable<T> undefined() {
        return (JsonNullable<T>) UNDEFINED;
    }

    public static <T> JsonNullable<T> of(T t) {
        return new JsonNullable<>(t, true);
    }

    public T get() {
        if (!this.isPresent) {
            throw new NoSuchElementException("Value is undefined");
        }
        return this.value;
    }

    public T orElse(T t) {
        return this.isPresent ? this.value : t;
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (this.isPresent) {
            consumer.accept(this.value);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JsonNullable)) {
            return false;
        }
        JsonNullable jsonNullable = (JsonNullable) obj;
        return Objects.equals(this.value, jsonNullable.value) && this.isPresent == jsonNullable.isPresent;
    }

    public int hashCode() {
        return Objects.hash(this.value, Boolean.valueOf(this.isPresent));
    }

    public String toString() {
        return this.isPresent ? String.format("JsonNullable[%s]", this.value) : "JsonNullable.undefined";
    }
}
