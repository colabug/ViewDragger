package com.colabug.viewdragger;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Set view
        setContentView( R.layout.main);

        // Create an action bar
        ActionBar bar   = getActionBar();
        bar.setDisplayOptions( ActionBar.DISPLAY_USE_LOGO |
                               ActionBar.DISPLAY_SHOW_TITLE );
        bar.setDisplayShowHomeEnabled( true );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        CanvasFragment canvas = getCanvasFragment();
        switch ( item.getItemId() )
        {
            // TODO: Undo last action
            case R.id.undo:
                return true;

            case R.id.add:
                canvas.addAnIcon();
                return true;

            case R.id.clear:
                canvas.clearScreen();
                return true;

            default:
                return super.onOptionsItemSelected( item );
        }
    }

    private CanvasFragment getCanvasFragment()
    {
        return (CanvasFragment) getFragmentManager().findFragmentById( R.id.canvas_fragment );
    }
}
