package javax.ws.rs.core;

import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class Form {
    private final MultivaluedMap<String, String> parameters;

    public Form() {
        this(new AbstractMultivaluedMap<String, String>(new LinkedHashMap()) { // from class: javax.ws.rs.core.Form.1
        });
    }

    public Form(String str, String str2) {
        this();
        this.parameters.add(str, str2);
    }

    public Form(MultivaluedMap<String, String> multivaluedMap) {
        this.parameters = multivaluedMap;
    }

    public Form param(String str, String str2) {
        this.parameters.add(str, str2);
        return this;
    }

    public MultivaluedMap<String, String> asMap() {
        return this.parameters;
    }
}
