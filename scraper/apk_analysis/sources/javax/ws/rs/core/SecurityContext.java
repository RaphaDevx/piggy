package javax.ws.rs.core;

import java.security.Principal;

/* loaded from: classes4.dex */
public interface SecurityContext {
    public static final String BASIC_AUTH = "BASIC";
    public static final String CLIENT_CERT_AUTH = "CLIENT_CERT";
    public static final String DIGEST_AUTH = "DIGEST";
    public static final String FORM_AUTH = "FORM";

    String getAuthenticationScheme();

    Principal getUserPrincipal();

    boolean isSecure();

    boolean isUserInRole(String str);
}
