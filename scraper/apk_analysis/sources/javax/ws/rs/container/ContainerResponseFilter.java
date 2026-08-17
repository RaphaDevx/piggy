package javax.ws.rs.container;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface ContainerResponseFilter {
    void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException;
}
