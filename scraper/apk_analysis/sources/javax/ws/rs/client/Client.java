package javax.ws.rs.client;

import java.net.URI;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;

/* loaded from: classes3.dex */
public interface Client extends Configurable<Client> {
    void close();

    HostnameVerifier getHostnameVerifier();

    SSLContext getSslContext();

    Invocation.Builder invocation(Link link);

    WebTarget target(String str);

    WebTarget target(URI uri);

    WebTarget target(Link link);

    WebTarget target(UriBuilder uriBuilder);
}
