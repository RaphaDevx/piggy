package com.tealium.internal.k;

import android.text.TextUtils;
import com.tealium.internal.d;
import com.tealium.internal.j.u;
import com.tealium.remotecommands.RemoteCommand;

/* compiled from: ConfigRemoteCommand.java */
/* loaded from: classes2.dex */
final class a extends RemoteCommand {
    private final d a;

    a(d dVar) {
        super("_config", "Java callback for mobile.html information.");
        this.a = dVar;
        if (dVar == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.remotecommands.RemoteCommand
    protected void onInvoke(RemoteCommand.Response response) throws Exception {
        String optString = response.getRequestPayload().optString("trace_id", null);
        if (!TextUtils.isEmpty(optString)) {
            this.a.b(new u(optString, true));
        }
        response.send();
    }
}
