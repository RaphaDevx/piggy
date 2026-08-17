package ch.coop.apidia.appGateway.cards;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/* loaded from: classes3.dex */
public class ProgressRequestBody extends RequestBody {
    private final ApiCallback callback;
    private final RequestBody requestBody;

    public ProgressRequestBody(RequestBody requestBody, ApiCallback apiCallback) {
        this.requestBody = requestBody;
        this.callback = apiCallback;
    }

    @Override // okhttp3.RequestBody
    /* renamed from: contentType */
    public MediaType getContentType() {
        return this.requestBody.getContentType();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        return this.requestBody.contentLength();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        BufferedSink buffer = Okio.buffer(sink(bufferedSink));
        this.requestBody.writeTo(buffer);
        buffer.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) { // from class: ch.coop.apidia.appGateway.cards.ProgressRequestBody.1
            long bytesWritten = 0;
            long contentLength = 0;

            @Override // okio.ForwardingSink, okio.Sink
            public void write(Buffer buffer, long j) throws IOException {
                super.write(buffer, j);
                if (this.contentLength == 0) {
                    this.contentLength = ProgressRequestBody.this.contentLength();
                }
                this.bytesWritten += j;
                ApiCallback apiCallback = ProgressRequestBody.this.callback;
                long j2 = this.bytesWritten;
                long j3 = this.contentLength;
                apiCallback.onUploadProgress(j2, j3, j2 == j3);
            }
        };
    }
}
