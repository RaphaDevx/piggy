package ch.datatrans.payment;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.k4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0167k4 extends SSLSocket {
    public final SSLSocket a;

    public AbstractC0167k4(SSLSocket delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.a = delegate;
    }

    @Override // javax.net.ssl.SSLSocket
    public final void addHandshakeCompletedListener(HandshakeCompletedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a.addHandshakeCompletedListener(listener);
    }

    @Override // java.net.Socket
    public final void bind(SocketAddress localAddr) {
        Intrinsics.checkNotNullParameter(localAddr, "localAddr");
        this.a.bind(localAddr);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.a.close();
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress remoteAddr) {
        Intrinsics.checkNotNullParameter(remoteAddr, "remoteAddr");
        this.a.connect(remoteAddr);
    }

    public final boolean equals(Object obj) {
        return Intrinsics.areEqual(this.a, obj);
    }

    @Override // java.net.Socket
    public final SocketChannel getChannel() {
        SocketChannel channel = this.a.getChannel();
        Intrinsics.checkNotNullExpressionValue(channel, "getChannel(...)");
        return channel;
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getEnableSessionCreation() {
        return this.a.getEnableSessionCreation();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getEnabledCipherSuites() {
        String[] enabledCipherSuites = this.a.getEnabledCipherSuites();
        Intrinsics.checkNotNullExpressionValue(enabledCipherSuites, "getEnabledCipherSuites(...)");
        return enabledCipherSuites;
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getEnabledProtocols() {
        String[] enabledProtocols = this.a.getEnabledProtocols();
        Intrinsics.checkNotNullExpressionValue(enabledProtocols, "getEnabledProtocols(...)");
        return enabledProtocols;
    }

    @Override // java.net.Socket
    public final InetAddress getInetAddress() {
        InetAddress inetAddress = this.a.getInetAddress();
        Intrinsics.checkNotNullExpressionValue(inetAddress, "getInetAddress(...)");
        return inetAddress;
    }

    @Override // java.net.Socket
    public final InputStream getInputStream() {
        InputStream inputStream = this.a.getInputStream();
        Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
        return inputStream;
    }

    @Override // java.net.Socket
    public final boolean getKeepAlive() {
        return this.a.getKeepAlive();
    }

    @Override // java.net.Socket
    public final InetAddress getLocalAddress() {
        InetAddress localAddress = this.a.getLocalAddress();
        Intrinsics.checkNotNullExpressionValue(localAddress, "getLocalAddress(...)");
        return localAddress;
    }

    @Override // java.net.Socket
    public final int getLocalPort() {
        return this.a.getLocalPort();
    }

    @Override // java.net.Socket
    public final SocketAddress getLocalSocketAddress() {
        SocketAddress localSocketAddress = this.a.getLocalSocketAddress();
        Intrinsics.checkNotNullExpressionValue(localSocketAddress, "getLocalSocketAddress(...)");
        return localSocketAddress;
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getNeedClientAuth() {
        return this.a.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public final boolean getOOBInline() {
        return this.a.getOOBInline();
    }

    @Override // java.net.Socket
    public final OutputStream getOutputStream() {
        OutputStream outputStream = this.a.getOutputStream();
        Intrinsics.checkNotNullExpressionValue(outputStream, "getOutputStream(...)");
        return outputStream;
    }

    @Override // java.net.Socket
    public final int getPort() {
        return this.a.getPort();
    }

    @Override // java.net.Socket
    public final synchronized int getReceiveBufferSize() {
        return this.a.getReceiveBufferSize();
    }

    @Override // java.net.Socket
    public final SocketAddress getRemoteSocketAddress() {
        SocketAddress remoteSocketAddress = this.a.getRemoteSocketAddress();
        Intrinsics.checkNotNullExpressionValue(remoteSocketAddress, "getRemoteSocketAddress(...)");
        return remoteSocketAddress;
    }

    @Override // java.net.Socket
    public final boolean getReuseAddress() {
        return this.a.getReuseAddress();
    }

    @Override // java.net.Socket
    public final synchronized int getSendBufferSize() {
        return this.a.getSendBufferSize();
    }

    @Override // javax.net.ssl.SSLSocket
    public final SSLSession getSession() {
        SSLSession session = this.a.getSession();
        Intrinsics.checkNotNullExpressionValue(session, "getSession(...)");
        return session;
    }

    @Override // java.net.Socket
    public final int getSoLinger() {
        return this.a.getSoLinger();
    }

    @Override // java.net.Socket
    public final synchronized int getSoTimeout() {
        return this.a.getSoTimeout();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getSupportedCipherSuites() {
        String[] supportedCipherSuites = this.a.getSupportedCipherSuites();
        Intrinsics.checkNotNullExpressionValue(supportedCipherSuites, "getSupportedCipherSuites(...)");
        return supportedCipherSuites;
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getSupportedProtocols() {
        String[] supportedProtocols = this.a.getSupportedProtocols();
        Intrinsics.checkNotNullExpressionValue(supportedProtocols, "getSupportedProtocols(...)");
        return supportedProtocols;
    }

    @Override // java.net.Socket
    public final boolean getTcpNoDelay() {
        return this.a.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public final int getTrafficClass() {
        return this.a.getTrafficClass();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getUseClientMode() {
        return this.a.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getWantClientAuth() {
        return this.a.getWantClientAuth();
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.net.Socket
    public final boolean isBound() {
        return this.a.isBound();
    }

    @Override // java.net.Socket
    public final boolean isClosed() {
        return this.a.isClosed();
    }

    @Override // java.net.Socket
    public final boolean isConnected() {
        return this.a.isConnected();
    }

    @Override // java.net.Socket
    public final boolean isInputShutdown() {
        return this.a.isInputShutdown();
    }

    @Override // java.net.Socket
    public final boolean isOutputShutdown() {
        return this.a.isOutputShutdown();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void removeHandshakeCompletedListener(HandshakeCompletedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a.removeHandshakeCompletedListener(listener);
    }

    @Override // java.net.Socket
    public final void sendUrgentData(int i) {
        this.a.sendUrgentData(i);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnableSessionCreation(boolean z) {
        this.a.setEnableSessionCreation(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnabledCipherSuites(String[] suites) {
        Intrinsics.checkNotNullParameter(suites, "suites");
        this.a.setEnabledCipherSuites(suites);
    }

    @Override // java.net.Socket
    public final void setKeepAlive(boolean z) {
        this.a.setKeepAlive(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setNeedClientAuth(boolean z) {
        this.a.setNeedClientAuth(z);
    }

    @Override // java.net.Socket
    public final void setOOBInline(boolean z) {
        this.a.setOOBInline(z);
    }

    @Override // java.net.Socket
    public final void setPerformancePreferences(int i, int i2, int i3) {
        this.a.setPerformancePreferences(i, i2, i3);
    }

    @Override // java.net.Socket
    public final synchronized void setReceiveBufferSize(int i) {
        this.a.setReceiveBufferSize(i);
    }

    @Override // java.net.Socket
    public final void setReuseAddress(boolean z) {
        this.a.setReuseAddress(z);
    }

    @Override // java.net.Socket
    public final synchronized void setSendBufferSize(int i) {
        this.a.setSendBufferSize(i);
    }

    @Override // java.net.Socket
    public final void setSoLinger(boolean z, int i) {
        this.a.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public final synchronized void setSoTimeout(int i) {
        this.a.setSoTimeout(i);
    }

    @Override // java.net.Socket
    public final void setTcpNoDelay(boolean z) {
        this.a.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public final void setTrafficClass(int i) {
        this.a.setTrafficClass(i);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setUseClientMode(boolean z) {
        this.a.setUseClientMode(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setWantClientAuth(boolean z) {
        this.a.setWantClientAuth(z);
    }

    @Override // java.net.Socket
    public final void shutdownInput() {
        this.a.shutdownInput();
    }

    @Override // java.net.Socket
    public final void shutdownOutput() {
        this.a.shutdownOutput();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void startHandshake() {
        this.a.startHandshake();
    }

    @Override // javax.net.ssl.SSLSocket, java.net.Socket
    public final String toString() {
        String sSLSocket = this.a.toString();
        Intrinsics.checkNotNullExpressionValue(sSLSocket, "toString(...)");
        return sSLSocket;
    }

    public final void a(String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        try {
            SSLSocket sSLSocket = this.a;
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            sSLParameters.setServerNames(CollectionsKt.listOf(new SNIHostName(host)));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (Exception e) {
            throw new IllegalStateException("Could not enable SNI", e);
        }
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress remoteAddr, int i) {
        Intrinsics.checkNotNullParameter(remoteAddr, "remoteAddr");
        this.a.connect(remoteAddr, i);
    }
}
