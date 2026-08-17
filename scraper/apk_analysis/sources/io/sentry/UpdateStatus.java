package io.sentry;

/* loaded from: classes2.dex */
public abstract class UpdateStatus {

    public static final class UpToDate extends UpdateStatus {
        private static final UpToDate INSTANCE = new UpToDate();

        private UpToDate() {
        }

        public static UpToDate getInstance() {
            return INSTANCE;
        }
    }

    public static final class NewRelease extends UpdateStatus {
        private final UpdateInfo info;

        public NewRelease(UpdateInfo updateInfo) {
            this.info = updateInfo;
        }

        public UpdateInfo getInfo() {
            return this.info;
        }
    }

    public static final class UpdateError extends UpdateStatus {
        private final String message;

        public UpdateError(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static final class NoNetwork extends UpdateStatus {
        private final String message;

        public NoNetwork(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
