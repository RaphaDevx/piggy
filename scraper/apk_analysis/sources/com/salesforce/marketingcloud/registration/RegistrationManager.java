package com.salesforce.marketingcloud.registration;

import com.salesforce.marketingcloud.g;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public interface RegistrationManager {
    public static final String a = g.a("RegistrationManager");
    public static final String b = "Android";
    public static final int c = 128;

    public interface Editor {
        Editor addTag(String str);

        Editor addTags(Iterable<String> iterable);

        Editor addTags(String... strArr);

        Editor clearAttribute(String str);

        Editor clearAttributes(Iterable<String> iterable);

        Editor clearAttributes(String... strArr);

        Editor clearTags();

        boolean commit();

        Editor removeTag(String str);

        Editor removeTags(Iterable<String> iterable);

        Editor removeTags(String... strArr);

        Editor setAttribute(String str, String str2);

        Editor setContactKey(String str);

        Editor setSignedString(String str);
    }

    public interface RegistrationEventListener {
        void onRegistrationReceived(Registration registration);
    }

    Editor edit();

    Map<String, String> getAttributes();

    String getContactKey();

    String getDeviceId();

    String getSignedString();

    String getSystemToken();

    Set<String> getTags();

    void registerForRegistrationEvents(RegistrationEventListener registrationEventListener);

    void unregisterForRegistrationEvents(RegistrationEventListener registrationEventListener);
}
