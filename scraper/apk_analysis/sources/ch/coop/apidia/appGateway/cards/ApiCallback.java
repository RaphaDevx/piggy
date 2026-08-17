package ch.coop.apidia.appGateway.cards;

import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface ApiCallback<T> {
    void onDownloadProgress(long j, long j2, boolean z);

    void onFailure(ApiException apiException, int i, Map<String, List<String>> map);

    void onSuccess(T t, int i, Map<String, List<String>> map);

    void onUploadProgress(long j, long j2, boolean z);
}
