package javax.ws.rs.ext;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;

/* loaded from: classes4.dex */
public interface WriterInterceptor {
    void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException, WebApplicationException;
}
