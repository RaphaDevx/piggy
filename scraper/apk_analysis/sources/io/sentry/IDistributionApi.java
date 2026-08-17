package io.sentry;

/* loaded from: classes2.dex */
public interface IDistributionApi {

    public interface UpdateCallback {
        void onResult(UpdateStatus updateStatus);
    }

    void checkForUpdate(UpdateCallback updateCallback);

    UpdateStatus checkForUpdateBlocking();

    void downloadUpdate(UpdateInfo updateInfo);
}
