package ch.coop.auth;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import com.getcapacitor.JSObject;
import io.sentry.Sentry;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class FontDefinition {
    private static final String KEY_NAME = "name";
    private static final String KEY_SIZE = "size";
    private final String name;
    private final String path;
    private final float size;

    public FontDefinition(String str, float f) {
        this.name = str;
        this.path = "fonts/" + str + ".ttf";
        this.size = f;
    }

    public static FontDefinition fromJSObject(JSObject jSObject) {
        float f;
        if (jSObject != null) {
            String string = jSObject.getString("name");
            try {
                f = new Double(jSObject.getDouble("size")).floatValue();
            } catch (JSONException e) {
                Sentry.captureException(e);
                f = -1.0f;
            }
            if (string != null && f >= 0.0f) {
                return new FontDefinition(string, f);
            }
        }
        return null;
    }

    public static FontDefinition fromJSONObject(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                return new FontDefinition(jSONObject.getString("name"), new Double(jSONObject.getDouble("size")).floatValue());
            } catch (JSONException e) {
                Sentry.captureException(e);
            }
        }
        return null;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.name);
            jSONObject.put("size", this.size);
        } catch (JSONException e) {
            Sentry.captureException(e);
        }
        return jSONObject;
    }

    public void apply(Context context, TextView textView) {
        if (this.path != null) {
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), this.path));
        }
        float f = this.size;
        if (f >= 0.0f) {
            textView.setTextSize(f);
        }
    }
}
