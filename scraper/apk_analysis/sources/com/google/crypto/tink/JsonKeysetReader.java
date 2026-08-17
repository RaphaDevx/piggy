package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class JsonKeysetReader implements KeysetReader {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final InputStream inputStream;
    private final JSONObject json;
    private boolean urlSafeBase64;

    private JsonKeysetReader(InputStream inputStream) {
        this.urlSafeBase64 = false;
        this.inputStream = inputStream;
        this.json = null;
    }

    private JsonKeysetReader(JSONObject jSONObject) {
        this.urlSafeBase64 = false;
        this.json = jSONObject;
        this.inputStream = null;
    }

    public static KeysetReader withInputStream(InputStream inputStream) throws IOException {
        return new JsonKeysetReader(inputStream);
    }

    public static JsonKeysetReader withJsonObject(JSONObject jSONObject) {
        return new JsonKeysetReader(jSONObject);
    }

    public static JsonKeysetReader withString(String str) {
        return new JsonKeysetReader(new ByteArrayInputStream(str.getBytes(UTF_8)));
    }

    public static JsonKeysetReader withBytes(byte[] bArr) {
        return new JsonKeysetReader(new ByteArrayInputStream(bArr));
    }

    public static JsonKeysetReader withFile(File file) throws IOException {
        return new JsonKeysetReader(new FileInputStream(file));
    }

    public static JsonKeysetReader withPath(String str) throws IOException {
        return withFile(new File(str));
    }

    public static JsonKeysetReader withPath(Path path) throws IOException {
        return withFile(path.toFile());
    }

    public JsonKeysetReader withUrlSafeBase64() {
        this.urlSafeBase64 = true;
        return this;
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        try {
            JSONObject jSONObject = this.json;
            if (jSONObject != null) {
                return keysetFromJson(jSONObject);
            }
            return keysetFromJson(new JSONObject(new String(Util.readAll(this.inputStream), UTF_8)));
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            JSONObject jSONObject = this.json;
            if (jSONObject != null) {
                return encryptedKeysetFromJson(jSONObject);
            }
            return encryptedKeysetFromJson(new JSONObject(new String(Util.readAll(this.inputStream), UTF_8)));
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }

    private Keyset keysetFromJson(JSONObject jSONObject) throws JSONException {
        validateKeyset(jSONObject);
        Keyset.Builder newBuilder = Keyset.newBuilder();
        if (jSONObject.has("primaryKeyId")) {
            newBuilder.setPrimaryKeyId(jSONObject.getInt("primaryKeyId"));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("key");
        for (int i = 0; i < jSONArray.length(); i++) {
            newBuilder.addKey(keyFromJson(jSONArray.getJSONObject(i)));
        }
        return newBuilder.build();
    }

    private EncryptedKeyset encryptedKeysetFromJson(JSONObject jSONObject) throws JSONException {
        byte[] decode;
        validateEncryptedKeyset(jSONObject);
        if (this.urlSafeBase64) {
            decode = Base64.urlSafeDecode(jSONObject.getString("encryptedKeyset"));
        } else {
            decode = Base64.decode(jSONObject.getString("encryptedKeyset"));
        }
        return EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(decode)).setKeysetInfo(keysetInfoFromJson(jSONObject.getJSONObject("keysetInfo"))).build();
    }

    private Keyset.Key keyFromJson(JSONObject jSONObject) throws JSONException {
        validateKey(jSONObject);
        return Keyset.Key.newBuilder().setStatus(getStatus(jSONObject.getString("status"))).setKeyId(jSONObject.getInt("keyId")).setOutputPrefixType(getOutputPrefixType(jSONObject.getString("outputPrefixType"))).setKeyData(keyDataFromJson(jSONObject.getJSONObject("keyData"))).build();
    }

    private KeysetInfo keysetInfoFromJson(JSONObject jSONObject) throws JSONException {
        KeysetInfo.Builder newBuilder = KeysetInfo.newBuilder();
        if (jSONObject.has("primaryKeyId")) {
            newBuilder.setPrimaryKeyId(jSONObject.getInt("primaryKeyId"));
        }
        if (jSONObject.has("keyInfo")) {
            JSONArray jSONArray = jSONObject.getJSONArray("keyInfo");
            for (int i = 0; i < jSONArray.length(); i++) {
                newBuilder.addKeyInfo(keyInfoFromJson(jSONArray.getJSONObject(i)));
            }
        }
        return newBuilder.build();
    }

    private KeysetInfo.KeyInfo keyInfoFromJson(JSONObject jSONObject) throws JSONException {
        return KeysetInfo.KeyInfo.newBuilder().setStatus(getStatus(jSONObject.getString("status"))).setKeyId(jSONObject.getInt("keyId")).setOutputPrefixType(getOutputPrefixType(jSONObject.getString("outputPrefixType"))).setTypeUrl(jSONObject.getString("typeUrl")).build();
    }

    private KeyData keyDataFromJson(JSONObject jSONObject) throws JSONException {
        byte[] decode;
        validateKeyData(jSONObject);
        if (this.urlSafeBase64) {
            decode = Base64.urlSafeDecode(jSONObject.getString("value"));
        } else {
            decode = Base64.decode(jSONObject.getString("value"));
        }
        return KeyData.newBuilder().setTypeUrl(jSONObject.getString("typeUrl")).setValue(ByteString.copyFrom(decode)).setKeyMaterialType(getKeyMaterialType(jSONObject.getString("keyMaterialType"))).build();
    }

    private KeyStatusType getStatus(String str) throws JSONException {
        if (str.equals("ENABLED")) {
            return KeyStatusType.ENABLED;
        }
        if (str.equals("DISABLED")) {
            return KeyStatusType.DISABLED;
        }
        throw new JSONException("unknown status: " + str);
    }

    private OutputPrefixType getOutputPrefixType(String str) throws JSONException {
        if (str.equals("TINK")) {
            return OutputPrefixType.TINK;
        }
        if (str.equals("RAW")) {
            return OutputPrefixType.RAW;
        }
        if (str.equals("LEGACY")) {
            return OutputPrefixType.LEGACY;
        }
        if (str.equals("CRUNCHY")) {
            return OutputPrefixType.CRUNCHY;
        }
        throw new JSONException("unknown output prefix type: " + str);
    }

    private KeyData.KeyMaterialType getKeyMaterialType(String str) throws JSONException {
        if (str.equals("SYMMETRIC")) {
            return KeyData.KeyMaterialType.SYMMETRIC;
        }
        if (str.equals("ASYMMETRIC_PRIVATE")) {
            return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
        }
        if (str.equals("ASYMMETRIC_PUBLIC")) {
            return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
        }
        if (str.equals("REMOTE")) {
            return KeyData.KeyMaterialType.REMOTE;
        }
        throw new JSONException("unknown key material type: " + str);
    }

    private void validateKeyset(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("key") || jSONObject.getJSONArray("key").length() == 0) {
            throw new JSONException("invalid keyset");
        }
    }

    private void validateEncryptedKeyset(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("encryptedKeyset")) {
            throw new JSONException("invalid encrypted keyset");
        }
    }

    private void validateKey(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("keyData") || !jSONObject.has("status") || !jSONObject.has("keyId") || !jSONObject.has("outputPrefixType")) {
            throw new JSONException("invalid key");
        }
    }

    private void validateKeyData(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("typeUrl") || !jSONObject.has("value") || !jSONObject.has("keyMaterialType")) {
            throw new JSONException("invalid keyData");
        }
    }
}
