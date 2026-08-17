package com.beust.jcommander.converters;

import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class CommaParameterSplitter implements IParameterSplitter {
    @Override // com.beust.jcommander.converters.IParameterSplitter
    public List<String> split(String str) {
        return Arrays.asList(str.split(FirebaseConstants.SEPARATOR));
    }
}
