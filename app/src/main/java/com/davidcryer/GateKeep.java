package com.davidcryer;

public class GateKeep {

    public static <T extends Throwable> void that(final boolean conditionMet, final ThrowableProvider<T> throwableProvider) throws T {
        if (!conditionMet) {
            throw throwableProvider.throwable();
        }
    }

    public interface ThrowableProvider<T> {
        T throwable();
    }
}
