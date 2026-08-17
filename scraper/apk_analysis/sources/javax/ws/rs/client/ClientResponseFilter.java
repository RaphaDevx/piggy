package javax.ws.rs.client;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface ClientResponseFilter {
    void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException;
}
