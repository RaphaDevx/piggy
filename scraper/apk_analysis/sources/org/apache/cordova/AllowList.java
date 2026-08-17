package org.apache.cordova;

import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class AllowList {
    public static final String TAG = "CordovaAllowList";
    private ArrayList<URLPattern> allowList = new ArrayList<>();

    private static class URLPattern {
        public Pattern host;
        public Pattern path;
        public Integer port;
        public Pattern scheme;

        private String regexFromPattern(String pattern, boolean allowWildcards) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pattern.length(); i++) {
                char charAt = pattern.charAt(i);
                if (charAt == '*' && allowWildcards) {
                    sb.append(".");
                } else if ("\\.[]{}()^$?+|".indexOf(charAt) > -1) {
                    sb.append('\\');
                }
                sb.append(charAt);
            }
            return sb.toString();
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0070 A[Catch: NumberFormatException -> 0x0088, TryCatch #0 {NumberFormatException -> 0x0088, blocks: (B:27:0x000c, B:30:0x0013, B:4:0x0020, B:6:0x0026, B:8:0x0058, B:11:0x005f, B:13:0x0070, B:16:0x0079, B:19:0x0085, B:21:0x006c, B:22:0x0029, B:24:0x0031, B:25:0x004c, B:3:0x001e), top: B:26:0x000c }] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0029 A[Catch: NumberFormatException -> 0x0088, TryCatch #0 {NumberFormatException -> 0x0088, blocks: (B:27:0x000c, B:30:0x0013, B:4:0x0020, B:6:0x0026, B:8:0x0058, B:11:0x005f, B:13:0x0070, B:16:0x0079, B:19:0x0085, B:21:0x006c, B:22:0x0029, B:24:0x0031, B:25:0x004c, B:3:0x001e), top: B:26:0x000c }] */
        /* JADX WARN: Removed duplicated region for block: B:6:0x0026 A[Catch: NumberFormatException -> 0x0088, TryCatch #0 {NumberFormatException -> 0x0088, blocks: (B:27:0x000c, B:30:0x0013, B:4:0x0020, B:6:0x0026, B:8:0x0058, B:11:0x005f, B:13:0x0070, B:16:0x0079, B:19:0x0085, B:21:0x006c, B:22:0x0029, B:24:0x0031, B:25:0x004c, B:3:0x001e), top: B:26:0x000c }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public URLPattern(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) throws java.net.MalformedURLException {
            /*
                r6 = this;
                java.lang.String r0 = "([a-z0-9.-]*\\.)?"
                r6.<init>()
                r1 = 0
                java.lang.String r2 = "*"
                r3 = 2
                r4 = 0
                if (r7 == 0) goto L1e
                boolean r5 = r2.equals(r7)     // Catch: java.lang.NumberFormatException -> L88
                if (r5 == 0) goto L13
                goto L1e
            L13:
                java.lang.String r7 = r6.regexFromPattern(r7, r1)     // Catch: java.lang.NumberFormatException -> L88
                java.util.regex.Pattern r7 = java.util.regex.Pattern.compile(r7, r3)     // Catch: java.lang.NumberFormatException -> L88
                r6.scheme = r7     // Catch: java.lang.NumberFormatException -> L88
                goto L20
            L1e:
                r6.scheme = r4     // Catch: java.lang.NumberFormatException -> L88
            L20:
                boolean r7 = r2.equals(r8)     // Catch: java.lang.NumberFormatException -> L88
                if (r7 == 0) goto L29
                r6.host = r4     // Catch: java.lang.NumberFormatException -> L88
                goto L56
            L29:
                java.lang.String r7 = "*."
                boolean r7 = r8.startsWith(r7)     // Catch: java.lang.NumberFormatException -> L88
                if (r7 == 0) goto L4c
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> L88
                r7.<init>(r0)     // Catch: java.lang.NumberFormatException -> L88
                java.lang.String r8 = r8.substring(r3)     // Catch: java.lang.NumberFormatException -> L88
                java.lang.String r8 = r6.regexFromPattern(r8, r1)     // Catch: java.lang.NumberFormatException -> L88
                r7.append(r8)     // Catch: java.lang.NumberFormatException -> L88
                java.lang.String r7 = r7.toString()     // Catch: java.lang.NumberFormatException -> L88
                java.util.regex.Pattern r7 = java.util.regex.Pattern.compile(r7, r3)     // Catch: java.lang.NumberFormatException -> L88
                r6.host = r7     // Catch: java.lang.NumberFormatException -> L88
                goto L56
            L4c:
                java.lang.String r7 = r6.regexFromPattern(r8, r1)     // Catch: java.lang.NumberFormatException -> L88
                java.util.regex.Pattern r7 = java.util.regex.Pattern.compile(r7, r3)     // Catch: java.lang.NumberFormatException -> L88
                r6.host = r7     // Catch: java.lang.NumberFormatException -> L88
            L56:
                if (r9 == 0) goto L6c
                boolean r7 = r2.equals(r9)     // Catch: java.lang.NumberFormatException -> L88
                if (r7 == 0) goto L5f
                goto L6c
            L5f:
                r7 = 10
                int r7 = java.lang.Integer.parseInt(r9, r7)     // Catch: java.lang.NumberFormatException -> L88
                java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.NumberFormatException -> L88
                r6.port = r7     // Catch: java.lang.NumberFormatException -> L88
                goto L6e
            L6c:
                r6.port = r4     // Catch: java.lang.NumberFormatException -> L88
            L6e:
                if (r10 == 0) goto L85
                java.lang.String r7 = "/*"
                boolean r7 = r7.equals(r10)     // Catch: java.lang.NumberFormatException -> L88
                if (r7 == 0) goto L79
                goto L85
            L79:
                r7 = 1
                java.lang.String r7 = r6.regexFromPattern(r10, r7)     // Catch: java.lang.NumberFormatException -> L88
                java.util.regex.Pattern r7 = java.util.regex.Pattern.compile(r7)     // Catch: java.lang.NumberFormatException -> L88
                r6.path = r7     // Catch: java.lang.NumberFormatException -> L88
                goto L87
            L85:
                r6.path = r4     // Catch: java.lang.NumberFormatException -> L88
            L87:
                return
            L88:
                java.net.MalformedURLException r6 = new java.net.MalformedURLException
                java.lang.String r7 = "Port must be a number"
                r6.<init>(r7)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.AllowList.URLPattern.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
        }

        public boolean matches(Uri uri) {
            try {
                Pattern pattern = this.scheme;
                if (pattern != null && !pattern.matcher(uri.getScheme()).matches()) {
                    return false;
                }
                Pattern pattern2 = this.host;
                if (pattern2 != null && !pattern2.matcher(uri.getHost()).matches()) {
                    return false;
                }
                Integer num = this.port;
                if (num != null && !num.equals(Integer.valueOf(uri.getPort()))) {
                    return false;
                }
                Pattern pattern3 = this.path;
                if (pattern3 != null) {
                    if (!pattern3.matcher(uri.getPath()).matches()) {
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                LOG.d(AllowList.TAG, e.toString());
                return false;
            }
        }
    }

    public void addAllowListEntry(String origin, boolean subdomains) {
        String str = "*";
        if (this.allowList != null) {
            try {
                if (origin.compareTo("*") == 0) {
                    LOG.d(TAG, "Unlimited access to network resources");
                    this.allowList = null;
                    return;
                }
                Matcher matcher = Pattern.compile("^((\\*|[A-Za-z-]+):(//)?)?(\\*|((\\*\\.)?[^*/:]+))?(:(\\d+))?(/.*)?").matcher(origin);
                if (matcher.matches()) {
                    String group = matcher.group(2);
                    String group2 = matcher.group(4);
                    if ((!"file".equals(group) && !FirebaseAnalytics.Param.CONTENT.equals(group)) || group2 != null) {
                        str = group2;
                    }
                    String group3 = matcher.group(8);
                    String group4 = matcher.group(9);
                    if (group == null) {
                        this.allowList.add(new URLPattern("http", str, group3, group4));
                        this.allowList.add(new URLPattern("https", str, group3, group4));
                    } else {
                        this.allowList.add(new URLPattern(group, str, group3, group4));
                    }
                }
            } catch (Exception unused) {
                LOG.d(TAG, "Failed to add origin %s", origin);
            }
        }
    }

    public boolean isUrlAllowListed(String uri) {
        if (this.allowList == null) {
            return true;
        }
        Uri parse = Uri.parse(uri);
        Iterator<URLPattern> it = this.allowList.iterator();
        while (it.hasNext()) {
            if (it.next().matches(parse)) {
                return true;
            }
        }
        return false;
    }
}
