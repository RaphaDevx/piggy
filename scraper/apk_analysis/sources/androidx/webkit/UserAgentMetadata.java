package androidx.webkit;

import androidx.webkit.internal.WebViewFeatureInternal;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public final class UserAgentMetadata {
    public static final int BITNESS_DEFAULT = 0;
    private final String mArchitecture;
    private int mBitness;
    private final List<BrandVersion> mBrandVersionList;
    private final List<String> mFormFactors;
    private final String mFullVersion;
    private boolean mMobile;
    private final String mModel;
    private final String mPlatform;
    private final String mPlatformVersion;
    private boolean mWow64;
    public static final String FORM_FACTOR_DESKTOP = "Desktop";
    public static final String FORM_FACTOR_AUTOMOTIVE = "Automotive";
    public static final String FORM_FACTOR_MOBILE = "Mobile";
    public static final String FORM_FACTOR_TABLET = "Tablet";
    public static final String FORM_FACTOR_XR = "XR";
    public static final String FORM_FACTOR_EINK = "EInk";
    public static final String FORM_FACTOR_WATCH = "Watch";
    private static final Set<String> VALID_FORM_FACTORS = Collections.unmodifiableSet(new HashSet(Arrays.asList(FORM_FACTOR_DESKTOP, FORM_FACTOR_AUTOMOTIVE, FORM_FACTOR_MOBILE, FORM_FACTOR_TABLET, FORM_FACTOR_XR, FORM_FACTOR_EINK, FORM_FACTOR_WATCH)));

    @Retention(RetentionPolicy.SOURCE)
    public @interface FormFactors {
    }

    private UserAgentMetadata(List<BrandVersion> list, String str, String str2, String str3, String str4, String str5, boolean z, int i, boolean z2, List<String> list2) {
        this.mBrandVersionList = list;
        this.mFullVersion = str;
        this.mPlatform = str2;
        this.mPlatformVersion = str3;
        this.mArchitecture = str4;
        this.mModel = str5;
        this.mMobile = z;
        this.mBitness = i;
        this.mWow64 = z2;
        this.mFormFactors = list2;
    }

    public List<BrandVersion> getBrandVersionList() {
        return this.mBrandVersionList;
    }

    public String getFullVersion() {
        return this.mFullVersion;
    }

    public String getPlatform() {
        return this.mPlatform;
    }

    public String getPlatformVersion() {
        return this.mPlatformVersion;
    }

    public String getArchitecture() {
        return this.mArchitecture;
    }

    public String getModel() {
        return this.mModel;
    }

    public boolean isMobile() {
        return this.mMobile;
    }

    public int getBitness() {
        return this.mBitness;
    }

    public boolean isWow64() {
        return this.mWow64;
    }

    public List<String> getFormFactors() {
        return this.mFormFactors;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserAgentMetadata)) {
            return false;
        }
        UserAgentMetadata userAgentMetadata = (UserAgentMetadata) obj;
        return this.mMobile == userAgentMetadata.mMobile && this.mBitness == userAgentMetadata.mBitness && this.mWow64 == userAgentMetadata.mWow64 && Objects.equals(this.mBrandVersionList, userAgentMetadata.mBrandVersionList) && Objects.equals(this.mFullVersion, userAgentMetadata.mFullVersion) && Objects.equals(this.mPlatform, userAgentMetadata.mPlatform) && Objects.equals(this.mPlatformVersion, userAgentMetadata.mPlatformVersion) && Objects.equals(this.mArchitecture, userAgentMetadata.mArchitecture) && Objects.equals(this.mModel, userAgentMetadata.mModel) && Objects.equals(this.mFormFactors, userAgentMetadata.mFormFactors);
    }

    public int hashCode() {
        return Objects.hash(this.mBrandVersionList, this.mFullVersion, this.mPlatform, this.mPlatformVersion, this.mArchitecture, this.mModel, Boolean.valueOf(this.mMobile), Integer.valueOf(this.mBitness), Boolean.valueOf(this.mWow64), this.mFormFactors);
    }

    public static final class BrandVersion {
        private final String mBrand;
        private final String mFullVersion;
        private final String mMajorVersion;

        private BrandVersion(String str, String str2, String str3) {
            this.mBrand = str;
            this.mMajorVersion = str2;
            this.mFullVersion = str3;
        }

        public String getBrand() {
            return this.mBrand;
        }

        public String getMajorVersion() {
            return this.mMajorVersion;
        }

        public String getFullVersion() {
            return this.mFullVersion;
        }

        public String toString() {
            return this.mBrand + FirebaseConstants.SEPARATOR + this.mMajorVersion + FirebaseConstants.SEPARATOR + this.mFullVersion;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BrandVersion)) {
                return false;
            }
            BrandVersion brandVersion = (BrandVersion) obj;
            return Objects.equals(this.mBrand, brandVersion.mBrand) && Objects.equals(this.mMajorVersion, brandVersion.mMajorVersion) && Objects.equals(this.mFullVersion, brandVersion.mFullVersion);
        }

        public int hashCode() {
            return Objects.hash(this.mBrand, this.mMajorVersion, this.mFullVersion);
        }

        public static final class Builder {
            private String mBrand;
            private String mFullVersion;
            private String mMajorVersion;

            public Builder() {
            }

            public Builder(BrandVersion brandVersion) {
                this.mBrand = brandVersion.getBrand();
                this.mMajorVersion = brandVersion.getMajorVersion();
                this.mFullVersion = brandVersion.getFullVersion();
            }

            public BrandVersion build() {
                String str;
                String str2;
                String str3 = this.mBrand;
                if (str3 == null || str3.trim().isEmpty() || (str = this.mMajorVersion) == null || str.trim().isEmpty() || (str2 = this.mFullVersion) == null || str2.trim().isEmpty()) {
                    throw new IllegalStateException("Brand name, major version and full version should not be null or blank.");
                }
                return new BrandVersion(this.mBrand, this.mMajorVersion, this.mFullVersion);
            }

            public Builder setBrand(String str) {
                if (str.trim().isEmpty()) {
                    throw new IllegalArgumentException("Brand should not be blank.");
                }
                this.mBrand = str;
                return this;
            }

            public Builder setMajorVersion(String str) {
                if (str.trim().isEmpty()) {
                    throw new IllegalArgumentException("MajorVersion should not be blank.");
                }
                this.mMajorVersion = str;
                return this;
            }

            public Builder setFullVersion(String str) {
                if (str.trim().isEmpty()) {
                    throw new IllegalArgumentException("FullVersion should not be blank.");
                }
                this.mFullVersion = str;
                return this;
            }
        }
    }

    public static final class Builder {
        private String mArchitecture;
        private int mBitness;
        private List<BrandVersion> mBrandVersionList;
        private List<String> mFormFactors;
        private String mFullVersion;
        private boolean mMobile;
        private String mModel;
        private String mPlatform;
        private String mPlatformVersion;
        private boolean mWow64;

        public Builder() {
            this.mBrandVersionList = new ArrayList();
            this.mMobile = true;
            this.mBitness = 0;
            this.mWow64 = false;
            this.mFormFactors = new ArrayList();
        }

        public Builder(UserAgentMetadata userAgentMetadata) {
            this.mBrandVersionList = new ArrayList();
            this.mMobile = true;
            this.mBitness = 0;
            this.mWow64 = false;
            this.mFormFactors = new ArrayList();
            this.mBrandVersionList = userAgentMetadata.getBrandVersionList();
            this.mFullVersion = userAgentMetadata.getFullVersion();
            this.mPlatform = userAgentMetadata.getPlatform();
            this.mPlatformVersion = userAgentMetadata.getPlatformVersion();
            this.mArchitecture = userAgentMetadata.getArchitecture();
            this.mModel = userAgentMetadata.getModel();
            this.mMobile = userAgentMetadata.isMobile();
            this.mBitness = userAgentMetadata.getBitness();
            this.mWow64 = userAgentMetadata.isWow64();
            this.mFormFactors = userAgentMetadata.getFormFactors();
        }

        public UserAgentMetadata build() {
            return new UserAgentMetadata(this.mBrandVersionList, this.mFullVersion, this.mPlatform, this.mPlatformVersion, this.mArchitecture, this.mModel, this.mMobile, this.mBitness, this.mWow64, this.mFormFactors);
        }

        public Builder setBrandVersionList(List<BrandVersion> list) {
            this.mBrandVersionList = list;
            return this;
        }

        public Builder setFullVersion(String str) {
            if (str == null) {
                this.mFullVersion = null;
                return this;
            }
            if (str.trim().isEmpty()) {
                throw new IllegalArgumentException("Full version should not be blank.");
            }
            this.mFullVersion = str;
            return this;
        }

        public Builder setPlatform(String str) {
            if (str == null) {
                this.mPlatform = null;
                return this;
            }
            if (str.trim().isEmpty()) {
                throw new IllegalArgumentException("Platform should not be blank.");
            }
            this.mPlatform = str;
            return this;
        }

        public Builder setPlatformVersion(String str) {
            this.mPlatformVersion = str;
            return this;
        }

        public Builder setArchitecture(String str) {
            this.mArchitecture = str;
            return this;
        }

        public Builder setModel(String str) {
            this.mModel = str;
            return this;
        }

        public Builder setMobile(boolean z) {
            this.mMobile = z;
            return this;
        }

        public Builder setBitness(int i) {
            this.mBitness = i;
            return this;
        }

        public Builder setWow64(boolean z) {
            this.mWow64 = z;
            return this;
        }

        public Builder setFormFactors(List<String> list) {
            if (!WebViewFeatureInternal.USER_AGENT_METADATA_FORM_FACTORS.isSupportedByWebView()) {
                throw WebViewFeatureInternal.getUnsupportedOperationException();
            }
            for (String str : list) {
                if (!UserAgentMetadata.VALID_FORM_FACTORS.contains(str)) {
                    throw new IllegalArgumentException("Invalid form factor: " + str);
                }
            }
            this.mFormFactors = list;
            return this;
        }
    }
}
