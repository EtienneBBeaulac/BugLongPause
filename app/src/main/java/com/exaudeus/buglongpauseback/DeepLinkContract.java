package com.exaudeus.buglongpauseback;

import android.content.UriMatcher;

public final class DeepLinkContract {

    public static final int TREE = 740;
    public static final String IS_DEEP_LINK = "DeepLinkContract.IS_DEEP_LINK";

    private static final UriMatcher deepLinkUrls = new UriMatcher(UriMatcher.NO_MATCH);

    private static final String baseHostname = "www.familysearch.org";

    static {
        addDeepLinkUris(baseHostname, deepLinkUrls);
    }

    private static void addDeepLinkUris(String hostname, @SuppressWarnings("SameParameterValue") UriMatcher matcher) {
        String tree = "/tree";
        matcher.addURI(hostname, tree, TREE);
    }

    public static UriMatcher getDeepLinkUrls() {
        return deepLinkUrls;
    }
}
