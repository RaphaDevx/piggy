package ch.coop.apidia.appGateway.loyalty.model;

import java.util.Map;
import java.util.Objects;
import javax.ws.rs.core.GenericType;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public abstract class AbstractOpenApiSchema {
    private Object instance;
    private Boolean isNullable;
    private final String schemaType;

    public abstract Map<String, GenericType> getSchemas();

    public AbstractOpenApiSchema(String str, Boolean bool) {
        this.schemaType = str;
        this.isNullable = bool;
    }

    public Object getActualInstance() {
        return this.instance;
    }

    public void setActualInstance(Object obj) {
        this.instance = obj;
    }

    public Object getActualInstanceRecursively() {
        return getActualInstanceRecursively(this);
    }

    private Object getActualInstanceRecursively(AbstractOpenApiSchema abstractOpenApiSchema) {
        if (abstractOpenApiSchema.getActualInstance() == null) {
            return null;
        }
        if (abstractOpenApiSchema.getActualInstance() instanceof AbstractOpenApiSchema) {
            return getActualInstanceRecursively((AbstractOpenApiSchema) abstractOpenApiSchema.getActualInstance());
        }
        return abstractOpenApiSchema.getActualInstance();
    }

    public String getSchemaType() {
        return this.schemaType;
    }

    public String toString() {
        return "class " + getClass() + " {\n    instance: " + toIndentedString(this.instance) + "\n    isNullable: " + toIndentedString(this.isNullable) + "\n    schemaType: " + toIndentedString(this.schemaType) + "\n}";
    }

    private String toIndentedString(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.toString().replace(StringUtils.LF, "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractOpenApiSchema abstractOpenApiSchema = (AbstractOpenApiSchema) obj;
        return Objects.equals(this.instance, abstractOpenApiSchema.instance) && Objects.equals(this.isNullable, abstractOpenApiSchema.isNullable) && Objects.equals(this.schemaType, abstractOpenApiSchema.schemaType);
    }

    public int hashCode() {
        return Objects.hash(this.instance, this.isNullable, this.schemaType);
    }

    public Boolean isNullable() {
        if (Boolean.TRUE.equals(this.isNullable)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
