package javax.ws.rs.ext;

import java.lang.Throwable;
import javax.ws.rs.core.Response;

/* loaded from: classes4.dex */
public interface ExceptionMapper<E extends Throwable> {
    Response toResponse(E e);
}
