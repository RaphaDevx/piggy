package com.getcapacitor.plugin.util;

import javax.ws.rs.core.MediaType;

/* loaded from: classes3.dex */
enum MimeType {
    APPLICATION_JSON(MediaType.APPLICATION_JSON),
    APPLICATION_VND_API_JSON("application/vnd.api+json"),
    TEXT_HTML(MediaType.TEXT_HTML);

    private final String value;

    MimeType(String str) {
        this.value = str;
    }

    String getValue() {
        return this.value;
    }
}
