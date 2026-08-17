package ch.coop.capacitor.persistentstorage;

import android.text.TextUtils;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import org.json.JSONException;

@CapacitorPlugin(name = "PersistentStorage")
/* loaded from: classes3.dex */
public class PersistentStoragePlugin extends Plugin {
    public PersistentStorage _storage;

    @Override // com.getcapacitor.Plugin
    public void load() {
        this._storage = new PersistentStorage(getContext().getApplicationContext());
    }

    @PluginMethod
    public void get(PluginCall call) {
        String string = call.getString("key");
        if (string == null) {
            call.reject("Must provide key");
            return;
        }
        try {
            Object obj = this._storage.get(string);
            JSObject jSObject = new JSObject();
            if (obj == null) {
                obj = JSObject.NULL;
            }
            jSObject.put("value", obj);
            call.resolve(jSObject);
        } catch (Exception e) {
            e.printStackTrace();
            call.reject("[CoopchCapacitorPersistentStorage] could not get value for key: " + string);
        }
    }

    @PluginMethod
    public void set(PluginCall call) {
        String string = call.getString("key");
        if (TextUtils.isEmpty(string)) {
            call.reject("Must provide key");
            return;
        }
        try {
            this._storage.set(string, call.getString("value"), call.getBoolean("encrypted", false), call.getBoolean("largeFileStorageAndroid", false));
            call.resolve();
        } catch (Exception e) {
            e.printStackTrace();
            call.reject("[CoopchCapacitorPersistentStorage] could not set value for key: " + string);
        }
    }

    @PluginMethod
    public void remove(PluginCall call) {
        String string = call.getString("key");
        if (string == null) {
            call.reject("Must provide key");
            return;
        }
        try {
            this._storage.remove(string);
            call.resolve();
        } catch (Exception e) {
            e.printStackTrace();
            call.reject("[CoopchCapacitorPersistentStorage] could not remove value for key: " + string);
        }
    }

    @PluginMethod
    public void keys(PluginCall call) {
        try {
            String[] strArr = (String[]) this._storage.keys().toArray(new String[0]);
            JSObject jSObject = new JSObject();
            try {
                jSObject.put("keys", (Object) new JSArray(strArr));
                call.resolve(jSObject);
            } catch (JSONException e) {
                call.reject("Unable to serialize response.", e);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            call.reject("[CoopchCapacitorPersistentStorage] could not retrieve keys");
        }
    }

    @PluginMethod
    public void clear(PluginCall call) {
        try {
            this._storage.clear();
            call.resolve();
        } catch (Exception e) {
            e.printStackTrace();
            call.reject("[CoopchCapacitorPersistentStorage] could not clear storage.");
        }
    }
}
