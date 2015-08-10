package com.project4D.fdpay.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2015-08-09.
 */
public class IntentUtil {
    public static void pullActivity(Context from, Class<?> to, Bundle... extra) {
        Intent i = new Intent(from, to);
        i.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        i.putExtras(extra[0]);
        from.startActivity(i);
    }
}
