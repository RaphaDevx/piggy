package javax.ws.rs.ext;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

/* loaded from: classes4.dex */
public interface ReaderInterceptorContext extends InterceptorContext {
    MultivaluedMap<String, String> getHeaders();

    InputStream getInputStream();

    Object proceed() throws IOException, WebApplicationException;

    void setInputStream(InputStream inputStream);
}
