package com.capacitorjs.plugins.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

/* loaded from: classes3.dex */
public class Preferences {
    private SharedPreferences preferences;

    /* JADX INFO: Access modifiers changed from: private */
    interface PreferencesOperation {
        void execute(SharedPreferences.Editor editor);
    }

    Preferences(Context context, PreferencesConfiguration preferencesConfiguration) {
        this.preferences = context.getSharedPreferences(preferencesConfiguration.group, 0);
    }

    public String get(String str) {
        return this.preferences.getString(str, null);
    }

    public void set(final String str, final String str2) {
        executeOperation(new PreferencesOperation() { // from class: com.capacitorjs.plugins.preferences.Preferences$$ExternalSyntheticLambda0
            @Override // com.capacitorjs.plugins.preferences.Preferences.PreferencesOperation
            public final void execute(SharedPreferences.Editor editor) {
                editor.putString(str, str2);
            }
        });
    }

    public void remove(final String str) {
        executeOperation(new PreferencesOperation() { // from class: com.capacitorjs.plugins.preferences.Preferences$$ExternalSyntheticLambda2
            @Override // com.capacitorjs.plugins.preferences.Preferences.PreferencesOperation
            public final void execute(SharedPreferences.Editor editor) {
                editor.remove(str);
            }
        });
    }

    public Set<String> keys() {
        return this.preferences.getAll().keySet();
    }

    public void clear() {
        executeOperation(new PreferencesOperation() { // from class: com.capacitorjs.plugins.preferences.Preferences$$ExternalSyntheticLambda1
            @Override // com.capacitorjs.plugins.preferences.Preferences.PreferencesOperation
            public final void execute(SharedPreferences.Editor editor) {
                editor.clear();
            }
        });
    }

    private void executeOperation(PreferencesOperation preferencesOperation) {
        SharedPreferences.Editor edit = this.preferences.edit();
        preferencesOperation.execute(edit);
        edit.apply();
    }
}
