package io.sentry;

import io.sentry.IDistributionApi;
import io.sentry.UpdateStatus;

/* loaded from: classes2.dex */
public final class NoOpDistributionApi implements IDistributionApi {
    private static final NoOpDistributionApi instance = new NoOpDistributionApi();

    @Override // io.sentry.IDistributionApi
    public void checkForUpdate(IDistributionApi.UpdateCallback updateCallback) {
    }

    @Override // io.sentry.IDistributionApi
    public void downloadUpdate(UpdateInfo updateInfo) {
    }

    private NoOpDistributionApi() {
    }

    public static NoOpDistributionApi getInstance() {
        return instance;
    }

    @Override // io.sentry.IDistributionApi
    public UpdateStatus checkForUpdateBlocking() {
        return UpdateStatus.UpToDate.getInstance();
    }
}
