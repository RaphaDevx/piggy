package org.openapitools.jackson.nullable;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.Module;

/* loaded from: classes4.dex */
public class JsonNullableModule extends Module {
    private final String NAME = "JsonNullableModule";

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int hashCode() {
        return -378616747;
    }

    @Override // com.fasterxml.jackson.databind.Module
    public void setupModule(Module.SetupContext setupContext) {
        setupContext.addSerializers(new JsonNullableSerializers());
        setupContext.addDeserializers(new JsonNullableDeserializers());
        setupContext.addTypeModifier(new JsonNullableTypeModifier());
        setupContext.addBeanSerializerModifier(new JsonNullableBeanSerializerModifier());
    }

    @Override // com.fasterxml.jackson.databind.Module, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.databind.Module
    public String getModuleName() {
        return "JsonNullableModule";
    }
}
