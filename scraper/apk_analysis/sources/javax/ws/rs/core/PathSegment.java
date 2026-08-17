package javax.ws.rs.core;

/* loaded from: classes4.dex */
public interface PathSegment {
    MultivaluedMap<String, String> getMatrixParameters();

    String getPath();
}
