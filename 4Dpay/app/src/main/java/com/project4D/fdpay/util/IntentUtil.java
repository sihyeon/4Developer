package com.project4D.fdpay.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 * @version 2015-08-09.
 */
public class IntentUtil {
    public static void pullActivity(Context from, Class<?> to, Bundle... extra) {
        Intent i = new Intent(from, to);
        i.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        i.putExtras(extra[0]);
        from.startActivity(i);
    }

    public static void pullBroadCastActivity(Context from, String action, Bundle... extra) {
        Intent i = new Intent(action);
        i.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        i.putExtras(extra[0]);
        from.sendBroadcast(i);
    }
}
