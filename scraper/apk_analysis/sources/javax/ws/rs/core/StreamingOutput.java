package javax.ws.rs.core;

import java.io.IOException;
import java.io.OutputStream;
import javax.ws.rs.WebApplicationException;

/* loaded from: classes4.dex */
public interface StreamingOutput {
    void write(OutputStream outputStream) throws IOException, WebApplicationException;
}
