package com.tealium.internal.j;

import com.tealium.internal.data.PublishSettings;
import com.tealium.internal.listeners.PublishSettingsUpdateListener;

/* compiled from: PublishSettingsUpdateMessenger.java */
/* loaded from: classes2.dex */
public class q extends n<PublishSettingsUpdateListener> {
    private final PublishSettings b;

    public q(PublishSettings publishSettings) {
        super(PublishSettingsUpdateListener.class);
        this.b = publishSettings;
        if (publishSettings == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(PublishSettingsUpdateListener publishSettingsUpdateListener) {
        publishSettingsUpdateListener.onPublishSettingsUpdate(this.b);
    }
}
