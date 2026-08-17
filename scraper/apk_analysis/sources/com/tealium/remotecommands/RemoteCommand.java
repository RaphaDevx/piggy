package com.tealium.remotecommands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class RemoteCommand {
    private final String mCommandId;
    private RemoteCommandContext mContext;
    private final String mDescription;
    private final String mVersion;

    public interface ResponseHandler {
        void onHandle(Response response);
    }

    protected abstract void onInvoke(Response response) throws Exception;

    public RemoteCommand(String str, String str2) {
        this(str, str2, null);
    }

    public RemoteCommand(String str, String str2, String str3) {
        if (str == null || !isCommandNameValid(str)) {
            throw new IllegalArgumentException("Invalid remote command name.");
        }
        this.mCommandId = str.toLowerCase(Locale.ROOT);
        this.mDescription = str2 == null ? "" : str2;
        this.mVersion = str3;
    }

    public RemoteCommandContext getContext() {
        return this.mContext;
    }

    public void setContext(RemoteCommandContext remoteCommandContext) {
        this.mContext = remoteCommandContext;
    }

    public final String getCommandName() {
        return this.mCommandId;
    }

    public final String getDescription() {
        return this.mDescription;
    }

    public final String getVersion() {
        return this.mVersion;
    }

    public final void invoke(RemoteCommandRequest remoteCommandRequest) {
        if (remoteCommandRequest == null) {
            throw new IllegalArgumentException("request must not be null.");
        }
        try {
            onInvoke(remoteCommandRequest.getResponse());
        } catch (Throwable th) {
            remoteCommandRequest.getResponse().setStatus(555).setBody(Response.stringify(th)).send();
        }
    }

    static boolean isCommandNameValid(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return Pattern.matches("^[\\w-]*$", str);
    }

    public static class Response {
        public static final int STATUS_BAD_REQUEST = 400;
        public static final int STATUS_EXCEPTION_THROWN = 555;
        public static final int STATUS_NOT_FOUND = 404;
        public static final int STATUS_OK = 200;
        private String mBody;
        private final String mCommandId;
        private final ResponseHandler mHandler;
        private final String mId;
        private final JSONObject mRequestPayload;
        private boolean mSent;
        private int mStatus;

        public Response(ResponseHandler responseHandler, String str, String str2, JSONObject jSONObject) {
            if (str == null) {
                throw new IllegalArgumentException("mCommandId must not be null.");
            }
            this.mHandler = responseHandler;
            this.mCommandId = str;
            this.mId = str2;
            this.mRequestPayload = jSONObject == null ? new JSONObject() : jSONObject;
            this.mStatus = 200;
            this.mBody = null;
            this.mSent = false;
        }

        public final String getCommandId() {
            return this.mCommandId;
        }

        public final String getId() {
            return this.mId;
        }

        public final boolean isSent() {
            return this.mSent;
        }

        public final JSONObject getRequestPayload() {
            return this.mRequestPayload;
        }

        public final int getStatus() {
            return this.mStatus;
        }

        public final Response setStatus(int i) {
            if (this.mSent) {
                throw new IllegalStateException("Response already sent.");
            }
            this.mStatus = i;
            return this;
        }

        public final String getBody() {
            return this.mBody;
        }

        public final Response setBody(String str) {
            if (this.mSent) {
                throw new IllegalStateException("Response already sent.");
            }
            this.mBody = str;
            return this;
        }

        public void send() {
            if (this.mSent) {
                throw new IllegalStateException("Response already sent.");
            }
            this.mSent = true;
            ResponseHandler responseHandler = this.mHandler;
            if (responseHandler != null) {
                responseHandler.onHandle(this);
            }
        }

        public static String stringify(Throwable th) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.toString();
        }
    }
}
