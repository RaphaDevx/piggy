package javax.ws.rs.container;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface ContainerRequestFilter {
    void filter(ContainerRequestContext containerRequestContext) throws IOException;
}
