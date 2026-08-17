package com.tealium.internal.k;

import com.tealium.internal.d;
import com.tealium.internal.g;
import com.tealium.internal.j.m;
import com.tealium.library.R;
import com.tealium.library.Tealium;
import com.tealium.remotecommands.RemoteCommand;
import com.tealium.remotecommands.RemoteCommandContext;
import com.tealium.remotecommands.RemoteCommandRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TagBridge.java */
/* loaded from: classes2.dex */
public final class c {
    private final Map<String, RemoteCommand> a;
    private final d b;
    private final com.tealium.internal.c c;
    private final RemoteCommandContext d;

    /* compiled from: TagBridge.java */
    class a implements RemoteCommandContext {
        final /* synthetic */ Tealium a;

        a(c cVar, Tealium tealium) {
            this.a = tealium;
        }

        @Override // com.tealium.remotecommands.RemoteCommandContext
        public void track(String str, Map<String, ?> map) {
            this.a.trackEvent(str, map);
        }

        @Override // com.tealium.remotecommands.RemoteCommandContext
        public void track(String str, String str2, Map<String, ?> map) {
            this.a.trackEventType(str, str2, map);
        }
    }

    /* compiled from: TagBridge.java */
    class b implements RemoteCommand.ResponseHandler {
        final /* synthetic */ d a;

        b(d dVar) {
            this.a = dVar;
        }

        @Override // com.tealium.remotecommands.RemoteCommand.ResponseHandler
        public void onHandle(RemoteCommand.Response response) {
            if (response.getId() != null) {
                this.a.a(new m(response.getBody() == null ? String.format(Locale.ROOT, "try {\tutag.mobile.remote_api.response[\"%s\"][\"%s\"](%d);} catch(err) {\tconsole.error(err);};", response.getCommandId(), response.getId(), Integer.valueOf(response.getStatus())) : String.format(Locale.ROOT, "try {\tutag.mobile.remote_api.response[\"%s\"][\"%s\"](%d, %s);} catch(err) {\tconsole.error(err);};", response.getCommandId(), response.getId(), Integer.valueOf(response.getStatus()), JSONObject.quote(response.getBody()))));
            }
        }
    }

    public c(Tealium tealium, Tealium.Config config, d dVar) {
        if (config == null) {
            throw new IllegalArgumentException("config must not be null.");
        }
        this.b = dVar;
        if (dVar == null) {
            throw new IllegalArgumentException();
        }
        HashMap hashMap = new HashMap(1);
        this.a = hashMap;
        hashMap.put("_config", new com.tealium.internal.k.a(dVar));
        this.c = config.getLogger();
        this.d = a(tealium);
    }

    private RemoteCommandContext a(Tealium tealium) {
        return new a(this, tealium);
    }

    public static boolean b(String str) {
        return str.matches("^tealium://_config");
    }

    private RemoteCommand c(String str) {
        com.tealium.internal.k.b bVar = "_http".equals(str) ? new com.tealium.internal.k.b() : null;
        if (bVar != null) {
            this.a.put(bVar.getCommandName(), bVar);
        }
        return bVar;
    }

    public void a(String str) {
        try {
            a(new RemoteCommandRequest(a(this.b), str));
        } catch (Throwable th) {
            this.c.a(th);
        }
    }

    public final void b(RemoteCommand remoteCommand) {
        if (!g.c()) {
            throw new UnsupportedOperationException("Remote commands must be removed in the main thread.");
        }
        if (remoteCommand == null) {
            throw new IllegalArgumentException("remoteCommand must not be null.");
        }
        this.a.remove(remoteCommand.getCommandName());
    }

    private void a(RemoteCommandRequest remoteCommandRequest) throws JSONException, UnsupportedEncodingException {
        if (g.c()) {
            RemoteCommand remoteCommand = this.a.get(remoteCommandRequest.getCommandId());
            if (remoteCommand == null && (remoteCommand = c(remoteCommandRequest.getCommandId())) == null) {
                if (this.c.e()) {
                    this.c.e(R.string.tagbridge_no_command_found, remoteCommandRequest.getCommandId());
                }
                remoteCommandRequest.getResponse().setStatus(404).setBody(String.format(Locale.ROOT, "No remote command found with id \"%s\"", remoteCommandRequest.getCommandId())).send();
                return;
            } else {
                this.c.d(R.string.tagbridge_detected_command, remoteCommandRequest.getCommandId(), remoteCommandRequest.getResponse().getRequestPayload());
                remoteCommand.invoke(remoteCommandRequest);
                return;
            }
        }
        throw new UnsupportedOperationException("processRequest must be called in the main thread");
    }

    public final void a(RemoteCommand remoteCommand) {
        if (!g.c()) {
            throw new UnsupportedOperationException("Remote commands must be added in the main thread.");
        }
        if (remoteCommand != null) {
            if (remoteCommand.getContext() == null) {
                remoteCommand.setContext(this.d);
            }
            this.a.put(remoteCommand.getCommandName(), remoteCommand);
            return;
        }
        throw new IllegalArgumentException("remoteCommand must not be null.");
    }

    private static RemoteCommand.ResponseHandler a(d dVar) {
        return new b(dVar);
    }
}
