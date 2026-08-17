package javax.ws.rs.ext;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;

/* loaded from: classes4.dex */
public interface ReaderInterceptor {
    Object aroundReadFrom(ReaderInterceptorContext readerInterceptorContext) throws IOException, WebApplicationException;
}
