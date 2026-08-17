package javax.ws.rs.core;

import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Response;

/* loaded from: classes4.dex */
public interface Request {
    Response.ResponseBuilder evaluatePreconditions();

    Response.ResponseBuilder evaluatePreconditions(Date date);

    Response.ResponseBuilder evaluatePreconditions(Date date, EntityTag entityTag);

    Response.ResponseBuilder evaluatePreconditions(EntityTag entityTag);

    String getMethod();

    Variant selectVariant(List<Variant> list);
}
