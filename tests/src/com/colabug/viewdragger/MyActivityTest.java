package com.colabug.viewdragger;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.colabug.viewdragger.MyActivityTest \
 * com.colabug.viewdragger.tests/android.test.InstrumentationTestRunner
 */
public class MyActivityTest extends ActivityInstrumentationTestCase2<MyActivity> {

    public MyActivityTest() {
        super("com.colabug.viewdragger", MyActivity.class);
    }

    public void test() {
        TextView textView = (TextView) getActivity().findViewById(R.id.textView);
        assertEquals("Hello World!", textView.getText());
    }
}
