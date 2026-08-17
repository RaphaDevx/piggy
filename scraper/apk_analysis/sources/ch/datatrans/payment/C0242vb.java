package ch.datatrans.payment;

import java.net.InetAddress;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.vb, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0242vb extends SSLSocketFactory {
    public final SSLSocketFactory a;

    public C0242vb(TrustManager[] tm) {
        Intrinsics.checkNotNullParameter(tm, "tm");
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, tm, new SecureRandom());
        SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
        Intrinsics.checkNotNullExpressionValue(socketFactory, "getSocketFactory(...)");
        this.a = socketFactory;
    }

    public static Socket a(Socket socket, String str) {
        if (!(socket instanceof SSLSocket)) {
            return socket;
        }
        C0234ub c0234ub = new C0234ub((SSLSocket) socket);
        if (str != null) {
            c0234ub.a(str);
        }
        return c0234ub;
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() {
        Socket createSocket = this.a.createSocket();
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return a(createSocket, null);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        String[] defaultCipherSuites = this.a.getDefaultCipherSuites();
        Intrinsics.checkNotNullExpressionValue(defaultCipherSuites, "getDefaultCipherSuites(...)");
        return defaultCipherSuites;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        String[] supportedCipherSuites = this.a.getSupportedCipherSuites();
        Intrinsics.checkNotNullExpressionValue(supportedCipherSuites, "getSupportedCipherSuites(...)");
        return supportedCipherSuites;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String host, int i, boolean z) {
        Intrinsics.checkNotNullParameter(socket, "socket");
        Intrinsics.checkNotNullParameter(host, "host");
        Socket createSocket = this.a.createSocket(socket, host, i, z);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return a(createSocket, host);
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String host, int i) {
        Intrinsics.checkNotNullParameter(host, "host");
        Socket createSocket = this.a.createSocket(host, i);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return a(createSocket, host);
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String host, int i, InetAddress inetAddress, int i2) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(inetAddress, "inetAddress");
        Socket createSocket = this.a.createSocket(host, i, inetAddress, i2);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return a(createSocket, host);
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i) {
        Intrinsics.checkNotNullParameter(inetAddress, "inetAddress");
        Socket createSocket = this.a.createSocket(inetAddress, i);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return a(createSocket, inetAddress.getHostName());
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i2) {
        Intrinsics.checkNotNullParameter(inetAddress, "inetAddress");
        Intrinsics.checkNotNullParameter(inetAddress1, "inetAddress1");
        Socket createSocket = this.a.createSocket(inetAddress, i, inetAddress1, i2);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return a(createSocket, inetAddress.getHostName());
    }
}
