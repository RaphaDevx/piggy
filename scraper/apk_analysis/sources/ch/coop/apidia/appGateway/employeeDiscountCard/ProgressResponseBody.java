package ch.coop.apidia.appGateway.employeeDiscountCard;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes3.dex */
public class ProgressResponseBody extends ResponseBody {
    private BufferedSource bufferedSource;
    private final ApiCallback callback;
    private final ResponseBody responseBody;

    public ProgressResponseBody(ResponseBody responseBody, ApiCallback apiCallback) {
        this.responseBody = responseBody;
        this.callback = apiCallback;
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentType */
    public MediaType get$contentType() {
        return this.responseBody.get$contentType();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentLength */
    public long getContentLength() {
        return this.responseBody.getContentLength();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: source */
    public BufferedSource getSource() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(source(this.responseBody.getSource()));
        }
        return this.bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.ProgressResponseBody.1
            long totalBytesRead = 0;

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                long read = super.read(buffer, j);
                this.totalBytesRead += read != -1 ? read : 0L;
                ProgressResponseBody.this.callback.onDownloadProgress(this.totalBytesRead, ProgressResponseBody.this.responseBody.getContentLength(), read == -1);
                return read;
            }
        };
    }
}
