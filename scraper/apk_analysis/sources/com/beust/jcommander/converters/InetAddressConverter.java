package com.beust.jcommander.converters;

import com.beust.jcommander.IStringConverter;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes3.dex */
public class InetAddressConverter implements IStringConverter<InetAddress> {
    @Override // com.beust.jcommander.IStringConverter
    public InetAddress convert(String str) {
        try {
            return InetAddress.getByName(str);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(str, e);
        }
    }
}
