package javax.ws.rs.client;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Locale;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Variant;

/* loaded from: classes3.dex */
public final class Entity<T> {
    private static final Annotation[] EMPTY_ANNOTATIONS = new Annotation[0];
    private final Annotation[] annotations;
    private final T entity;
    private final Variant variant;

    public static <T> Entity<T> entity(T t, MediaType mediaType) {
        return new Entity<>(t, mediaType);
    }

    public static <T> Entity<T> entity(T t, MediaType mediaType, Annotation[] annotationArr) {
        return new Entity<>(t, mediaType, annotationArr);
    }

    public static <T> Entity<T> entity(T t, String str) {
        return new Entity<>(t, MediaType.valueOf(str));
    }

    public static <T> Entity<T> entity(T t, Variant variant) {
        return new Entity<>(t, variant);
    }

    public static <T> Entity<T> entity(T t, Variant variant, Annotation[] annotationArr) {
        return new Entity<>(t, variant, annotationArr);
    }

    public static <T> Entity<T> text(T t) {
        return new Entity<>(t, MediaType.TEXT_PLAIN_TYPE);
    }

    public static <T> Entity<T> xml(T t) {
        return new Entity<>(t, MediaType.APPLICATION_XML_TYPE);
    }

    public static <T> Entity<T> json(T t) {
        return new Entity<>(t, MediaType.APPLICATION_JSON_TYPE);
    }

    public static <T> Entity<T> html(T t) {
        return new Entity<>(t, MediaType.TEXT_HTML_TYPE);
    }

    public static <T> Entity<T> xhtml(T t) {
        return new Entity<>(t, MediaType.APPLICATION_XHTML_XML_TYPE);
    }

    public static Entity<Form> form(Form form) {
        return new Entity<>(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
    }

    public static Entity<Form> form(MultivaluedMap<String, String> multivaluedMap) {
        return new Entity<>(new Form(multivaluedMap), MediaType.APPLICATION_FORM_URLENCODED_TYPE);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    private Entity(T t, MediaType mediaType) {
        this(t, new Variant(mediaType, (Locale) null, (String) null), (Annotation[]) null);
    }

    private Entity(T t, Variant variant) {
        this(t, variant, (Annotation[]) null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    private Entity(T t, MediaType mediaType, Annotation[] annotationArr) {
        this(t, new Variant(mediaType, (Locale) null, (String) null), annotationArr);
    }

    private Entity(T t, Variant variant, Annotation[] annotationArr) {
        this.entity = t;
        this.variant = variant;
        this.annotations = annotationArr == null ? EMPTY_ANNOTATIONS : annotationArr;
    }

    public Variant getVariant() {
        return this.variant;
    }

    public MediaType getMediaType() {
        return this.variant.getMediaType();
    }

    public String getEncoding() {
        return this.variant.getEncoding();
    }

    public Locale getLanguage() {
        return this.variant.getLanguage();
    }

    public T getEntity() {
        return this.entity;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) obj;
        if (!Arrays.equals(this.annotations, entity.annotations)) {
            return false;
        }
        T t = this.entity;
        if (t == null ? entity.entity != null : !t.equals(entity.entity)) {
            return false;
        }
        Variant variant = this.variant;
        return variant == null ? entity.variant == null : variant.equals(entity.variant);
    }

    public int hashCode() {
        T t = this.entity;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        Variant variant = this.variant;
        return ((hashCode + (variant != null ? variant.hashCode() : 0)) * 31) + Arrays.hashCode(this.annotations);
    }

    public String toString() {
        return "Entity{entity=" + this.entity + ", variant=" + this.variant + ", annotations=" + Arrays.toString(this.annotations) + '}';
    }
}
