package com.tealium.remotecommands;

import com.tealium.remotecommands.RemoteCommand;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class RemoteCommandRequest {
    private static final String ARG = "?request=";
    private static final String CONFIG_RESPONSE_ID = "response_id";
    private static final String METAKEY_CONFIG = "config";
    private static final String METAKEY_PAYLOAD = "payload";
    private static final Pattern PROTOCOL_PREFIX = Pattern.compile("^tealium://.+", 2);
    public static final String REMOTE_COMMAND_REQUEST_PREFIX = "tealium://";
    private final String mCommandId;
    private final JSONObject mPayload;
    private final RemoteCommand.Response mRemoteCommandResponse;

    public RemoteCommandRequest(RemoteCommand.ResponseHandler responseHandler, String str) throws JSONException {
        JSONObject jSONObject;
        if (!isRequest(str)) {
            throw new IllegalArgumentException("Given url must not be null.");
        }
        int indexOf = str.indexOf(ARG);
        if (indexOf == -1) {
            this.mCommandId = str.substring(10).toLowerCase(Locale.ROOT);
            jSONObject = null;
        } else {
            this.mCommandId = str.substring(10, indexOf).toLowerCase(Locale.ROOT);
            try {
                String decode = URLDecoder.decode(str.substring(indexOf + 9), "UTF-8");
                jSONObject = decode.length() == 0 ? new JSONObject() : new JSONObject(decode);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        if (!RemoteCommand.isCommandNameValid(this.mCommandId)) {
            throw new IllegalArgumentException("The command id provided by the request is not a valid command id.");
        }
        if (jSONObject == null) {
            this.mPayload = null;
        } else {
            JSONObject optJSONObject = jSONObject.optJSONObject("config");
            r3 = optJSONObject != null ? optJSONObject.optString(CONFIG_RESPONSE_ID, null) : null;
            this.mPayload = jSONObject.optJSONObject("payload");
        }
        this.mRemoteCommandResponse = createResponse(responseHandler, this.mCommandId, r3, this.mPayload);
    }

    public RemoteCommandRequest(String str, JSONObject jSONObject) {
        this.mCommandId = str.toLowerCase(Locale.ROOT);
        this.mPayload = jSONObject != null ? jSONObject : new JSONObject();
        this.mRemoteCommandResponse = new RemoteCommand.Response(null, str, null, jSONObject);
    }

    public String getCommandId() {
        return this.mCommandId;
    }

    public RemoteCommand.Response getResponse() {
        return this.mRemoteCommandResponse;
    }

    public JSONObject getPayload() {
        return this.mPayload;
    }

    static RemoteCommand.Response createResponse(RemoteCommand.ResponseHandler responseHandler, String str, String str2, JSONObject jSONObject) {
        return new RemoteCommand.Response(responseHandler, str, str2, jSONObject);
    }

    public static boolean isRequest(String str) {
        if (str == null) {
            return false;
        }
        return PROTOCOL_PREFIX.matcher(str).matches();
    }
}
