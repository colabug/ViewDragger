package com.colabug.viewdragger;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Author: Corey Leigh Latislaw
 */
public class DraggableView extends View
{
    // Logging
    private static final String TAG = DraggableView.class.getSimpleName();

    // Context
    private Context context;

    // Image information
    private Bitmap image;
    private final Point  size          = new Point();
    private final Region region        = new Region();
    private final Point  startPosition = new Point();

    // Dragging state
    private boolean isDragging = false;


    public DraggableView( Context context )
    {
        super( context );
        this.context = context;
    }

    public final Bitmap getImage()
    {
        return image;
    }

    public final void setImage( Bitmap image )
    {
        this.image = image;
        setSize( image.getWidth(), image.getHeight() );
    }

    @Override
    protected void onDraw( Canvas canvas )
    {
        Log.d(TAG, "Drawing view.");
        Point position = getPosition();
        canvas.drawBitmap( image, position.x, position.y, new Paint() );
    }

    public final void setPosition( final Point position )
    {
        Log.d(TAG, "Setting position to (" + position.x + ", " + position.y + ")");
        region.set( position.x,
                    position.y,
                    position.x + size.x,
                    position.y + size.y );
    }

    public final Point getPosition()
    {
        return new Point( region.getBounds().left, region.getBounds().top );
    }

    public final void setSize( int width, int height )
    {
        size.x = width;
        size.y = height;

        Rect bounds = region.getBounds();
        region.set( bounds.left,
                    bounds.top,
                    bounds.left + width,
                    bounds.top + height );
    }

    public final Point getSize()
    {
        return size;
    }

    @Override
    public boolean onTouchEvent( MotionEvent event )
    {
        // Is the event inside of this view?
        if ( !region.contains( (int) event.getX(), (int) event.getY() ) && !isDragging )
        {
            Toast.makeText( context,
                            "Touch not inside the image region.",
                            Toast.LENGTH_SHORT ).show();
            return super.onTouchEvent( event );
        }

        // When pressed, record the original position
        if ( event.getAction() == MotionEvent.ACTION_DOWN )
        {
            Toast.makeText( context,
                            "Starting drag from (" + (int) event.getX() +
                            ", " + (int) event.getY() + ")",
                            Toast.LENGTH_SHORT ).show();

            // Start the drag
            isDragging = true;

            // Record the position
            startPosition.x = (int) event.getX();
            startPosition.y = (int) event.getY();
            bringToFront();

            return true;
        }
        // Draw it as it's moving across the screen
        else if ( event.getAction() == MotionEvent.ACTION_MOVE )
        {
            // Set the location as the start location for the next drag
            startPosition.x = (int) event.getX();
            startPosition.y = (int) event.getY();

            region.translate( startPosition.x, startPosition.y );
            setPosition( startPosition );
            invalidate();

            return true;
        }
        // When dropped, set new position and stop dragging
        else if ( event.getAction() == MotionEvent.ACTION_UP )
        {
            final String message = "Touch down at (" + (int) event.getX() + ", " + (int) event.getY() + ")";
            Toast.makeText( context, message, Toast.LENGTH_SHORT ).show();

            isDragging = false;
            setPosition( startPosition );
            invalidate();

            return true;
        }
        else
        {
            return super.onTouchEvent( event );
        }
    }
}
