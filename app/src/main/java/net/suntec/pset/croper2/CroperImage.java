package net.suntec.pset.croper2;

import android.graphics.Bitmap;

/**
 * Created by wangzhanfei on 16-10-21.
 */

public class CroperImage {

        public static Bitmap getBp() {
            return bp;
        }

        public static void setBp(Bitmap p) {
            bp = p;
        }


        private static Bitmap bp;
}
