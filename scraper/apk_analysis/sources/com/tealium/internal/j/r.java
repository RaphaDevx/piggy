package com.tealium.internal.j;

import com.tealium.internal.listeners.RemoveRemoteCommandListener;
import com.tealium.remotecommands.RemoteCommand;

/* compiled from: RemoveRemoteCommandMessenger.java */
/* loaded from: classes2.dex */
public final class r extends n<RemoveRemoteCommandListener> {
    private final RemoteCommand b;

    public r(RemoteCommand remoteCommand) {
        super(RemoveRemoteCommandListener.class);
        this.b = remoteCommand;
        if (remoteCommand == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(RemoveRemoteCommandListener removeRemoteCommandListener) {
        removeRemoteCommandListener.onRemoveRemoteCommand(this.b);
    }
}
