package javax.ws.rs.client;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface ClientRequestFilter {
    void filter(ClientRequestContext clientRequestContext) throws IOException;
}
