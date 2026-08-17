package com.tealium.internal.j;

import com.tealium.internal.listeners.AddRemoteCommandListener;
import com.tealium.remotecommands.RemoteCommand;

/* compiled from: AddRemoteCommandMessenger.java */
/* loaded from: classes2.dex */
public final class c extends n<AddRemoteCommandListener> {
    private final RemoteCommand b;

    public c(RemoteCommand remoteCommand) {
        super(AddRemoteCommandListener.class);
        this.b = remoteCommand;
        if (remoteCommand == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(AddRemoteCommandListener addRemoteCommandListener) {
        addRemoteCommandListener.onAddRemoteCommand(this.b);
    }
}
