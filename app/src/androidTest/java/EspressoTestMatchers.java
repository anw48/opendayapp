import android.view.View;

import org.hamcrest.Matcher;



/**
 * Created by Andrew on 4/1/2016.
 */
public class EspressoTestMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }

}

