package io.sentry;

import io.sentry.protocol.ViewHierarchy;
import java.io.File;
import java.util.concurrent.Callable;
import javax.ws.rs.core.MediaType;

/* loaded from: classes2.dex */
public final class Attachment {
    private static final String DEFAULT_ATTACHMENT_TYPE = "event.attachment";
    private static final String VIEW_HIERARCHY_ATTACHMENT_TYPE = "event.view_hierarchy";
    private final boolean addToTransactions;
    private String attachmentType;
    private final Callable<byte[]> byteProvider;
    private byte[] bytes;
    private final String contentType;
    private final String filename;
    private String pathname;
    private final JsonSerializable serializable;

    public Attachment(byte[] bArr, String str) {
        this(bArr, str, (String) null);
    }

    public Attachment(byte[] bArr, String str, String str2) {
        this(bArr, str, str2, false);
    }

    public Attachment(byte[] bArr, String str, String str2, boolean z) {
        this(bArr, str, str2, DEFAULT_ATTACHMENT_TYPE, z);
    }

    public Attachment(byte[] bArr, String str, String str2, String str3, boolean z) {
        this.bytes = bArr;
        this.serializable = null;
        this.byteProvider = null;
        this.filename = str;
        this.contentType = str2;
        this.attachmentType = str3;
        this.addToTransactions = z;
    }

    public Attachment(JsonSerializable jsonSerializable, String str, String str2, String str3, boolean z) {
        this.bytes = null;
        this.serializable = jsonSerializable;
        this.byteProvider = null;
        this.filename = str;
        this.contentType = str2;
        this.attachmentType = str3;
        this.addToTransactions = z;
    }

    public Attachment(Callable<byte[]> callable, String str, String str2, String str3, boolean z) {
        this.bytes = null;
        this.serializable = null;
        this.byteProvider = callable;
        this.filename = str;
        this.contentType = str2;
        this.attachmentType = str3;
        this.addToTransactions = z;
    }

    public Attachment(String str) {
        this(str, new File(str).getName());
    }

    public Attachment(String str, String str2) {
        this(str, str2, (String) null);
    }

    public Attachment(String str, String str2, String str3) {
        this(str, str2, str3, DEFAULT_ATTACHMENT_TYPE, false);
    }

    public Attachment(String str, String str2, String str3, String str4, boolean z) {
        this.pathname = str;
        this.filename = str2;
        this.serializable = null;
        this.byteProvider = null;
        this.contentType = str3;
        this.attachmentType = str4;
        this.addToTransactions = z;
    }

    public Attachment(String str, String str2, String str3, boolean z) {
        this.attachmentType = DEFAULT_ATTACHMENT_TYPE;
        this.pathname = str;
        this.filename = str2;
        this.serializable = null;
        this.byteProvider = null;
        this.contentType = str3;
        this.addToTransactions = z;
    }

    public Attachment(String str, String str2, String str3, boolean z, String str4) {
        this.pathname = str;
        this.filename = str2;
        this.serializable = null;
        this.byteProvider = null;
        this.contentType = str3;
        this.addToTransactions = z;
        this.attachmentType = str4;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public JsonSerializable getSerializable() {
        return this.serializable;
    }

    public String getPathname() {
        return this.pathname;
    }

    public String getFilename() {
        return this.filename;
    }

    public String getContentType() {
        return this.contentType;
    }

    boolean isAddToTransactions() {
        return this.addToTransactions;
    }

    public String getAttachmentType() {
        return this.attachmentType;
    }

    public Callable<byte[]> getByteProvider() {
        return this.byteProvider;
    }

    public static Attachment fromScreenshot(byte[] bArr) {
        return new Attachment(bArr, "screenshot.png", "image/png", false);
    }

    public static Attachment fromByteProvider(Callable<byte[]> callable, String str, String str2, boolean z) {
        return new Attachment(callable, str, str2, DEFAULT_ATTACHMENT_TYPE, z);
    }

    public static Attachment fromViewHierarchy(ViewHierarchy viewHierarchy) {
        return new Attachment((JsonSerializable) viewHierarchy, "view-hierarchy.json", MediaType.APPLICATION_JSON, VIEW_HIERARCHY_ATTACHMENT_TYPE, false);
    }

    public static Attachment fromThreadDump(byte[] bArr) {
        return new Attachment(bArr, "thread-dump.txt", "text/plain", false);
    }
}
