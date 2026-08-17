package javax.ws.rs.ext;

import java.io.IOException;
import java.io.OutputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

/* loaded from: classes4.dex */
public interface WriterInterceptorContext extends InterceptorContext {
    Object getEntity();

    MultivaluedMap<String, Object> getHeaders();

    OutputStream getOutputStream();

    void proceed() throws IOException, WebApplicationException;

    void setEntity(Object obj);

    void setOutputStream(OutputStream outputStream);
}
