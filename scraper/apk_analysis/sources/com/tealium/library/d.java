package com.tealium.library;

import com.tealium.collect.listeners.VisitorProfileUpdateListener;
import com.tealium.collect.visitor.VisitorProfile;
import com.tealium.internal.data.PublishSettings;
import com.tealium.internal.g;
import com.tealium.internal.listeners.PublishSettingsUpdateListener;
import com.tealium.library.Tealium;
import java.io.File;
import org.json.JSONException;

/* compiled from: FileManager.java */
/* loaded from: classes2.dex */
final class d implements VisitorProfileUpdateListener, PublishSettingsUpdateListener {
    private final com.tealium.internal.d a;
    private final File b;

    /* compiled from: FileManager.java */
    class a implements Runnable {
        final /* synthetic */ VisitorProfile a;

        a(VisitorProfile visitorProfile) {
            this.a = visitorProfile;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.a(this.a);
        }
    }

    d(Tealium.Config config, com.tealium.internal.d dVar) {
        this.b = config.getTealiumDir();
        this.a = dVar;
    }

    static PublishSettings b(File file) {
        try {
            return PublishSettings.from(g.a.a(new File(file, "mobile_publish_settings.json")));
        } catch (PublishSettings.DisabledLibraryException e) {
            throw new RuntimeException(e);
        }
    }

    static VisitorProfile c(File file) {
        File file2 = new File(file, "visitor_profile.json");
        if (!file2.exists()) {
            return null;
        }
        try {
            return VisitorProfile.fromJSON(g.a.a(file2));
        } catch (JSONException unused) {
            a(file2);
            return null;
        }
    }

    @Override // com.tealium.internal.listeners.PublishSettingsUpdateListener
    public void onPublishSettingsUpdate(PublishSettings publishSettings) {
        if (publishSettings.getSource() != null) {
            g.a.a(new File(this.b, "mobile_publish_settings.json"), publishSettings.getSource());
        }
    }

    @Override // com.tealium.collect.listeners.VisitorProfileUpdateListener
    public void onVisitorProfileUpdated(VisitorProfile visitorProfile, VisitorProfile visitorProfile2) {
        if (visitorProfile2 == null || visitorProfile2.getSource() == null) {
            return;
        }
        this.a.c(new a(visitorProfile2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(VisitorProfile visitorProfile) {
        String source = visitorProfile.getSource();
        if (source == null) {
            throw new IllegalArgumentException();
        }
        g.a.a(new File(this.b, "visitor_profile.json"), source);
    }

    private static void a(File file) {
        g.a.a(file, "");
        File file2 = new File(file.getParentFile(), System.nanoTime() + ".old");
        file.renameTo(file2);
        file.delete();
        file2.delete();
    }
}
