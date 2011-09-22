package com.colabug.viewdragger;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MyActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Set view
        setContentView( R.layout.main);

        // Create new layout parameters
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.mainLayout);

        // Create draggable views
        DraggableView draggableView = new DraggableView(this);
        draggableView.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
        DraggableView draggableView2 = new DraggableView(this);
        draggableView2.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.icon));

        // Add them to view
        frameLayout.addView(draggableView);
        frameLayout.addView(draggableView2);
    }
}
