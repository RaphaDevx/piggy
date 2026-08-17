package ch.coop.capacitor.persistentstorage;

import android.content.Context;
import io.sentry.Sentry;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/* loaded from: classes3.dex */
public class LargeFileStorage {
    private final Context context;

    public LargeFileStorage(Context context) {
        this.context = context;
    }

    public String writeFile(String key, String value) {
        try {
            FileOutputStream openFileOutput = this.context.openFileOutput(key, 0);
            try {
                openFileOutput.write(value.getBytes(StandardCharsets.UTF_8));
                if (openFileOutput != null) {
                    openFileOutput.close();
                }
                return key;
            } finally {
            }
        } catch (Exception e) {
            Sentry.captureException(e);
            return null;
        }
    }

    public String readFile(String key) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(this.context.openFileInput(key), StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    sb.append(readLine);
                    sb.append('\n');
                }
                bufferedReader.close();
                return sb.toString();
            } finally {
            }
        } catch (Exception e) {
            Sentry.captureException(e);
            return null;
        }
    }
}
