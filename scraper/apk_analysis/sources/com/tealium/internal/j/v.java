package com.tealium.internal.j;

import com.tealium.internal.data.UserConsentPreferences;
import com.tealium.internal.listeners.UserConsentPreferencesUpdateListener;

/* compiled from: UserConsentPreferencesUpdateMessenger.java */
/* loaded from: classes2.dex */
public class v extends n<UserConsentPreferencesUpdateListener> {
    private final UserConsentPreferences b;

    public v(UserConsentPreferences userConsentPreferences) {
        super(UserConsentPreferencesUpdateListener.class);
        if (userConsentPreferences == null) {
            throw new IllegalArgumentException();
        }
        this.b = userConsentPreferences;
    }

    @Override // com.tealium.internal.j.n
    public void a(UserConsentPreferencesUpdateListener userConsentPreferencesUpdateListener) {
        userConsentPreferencesUpdateListener.onUserConsentPreferencesUpdate(this.b);
    }
}
