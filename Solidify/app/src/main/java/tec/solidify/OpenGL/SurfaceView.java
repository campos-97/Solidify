package tec.solidify.OpenGL;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import tec.solidify.R;

/**
 * Created by josea on 10/19/2017.
 */

public class SurfaceView extends GLSurfaceView implements ErrorHandler
{
    private tec.solidify.OpenGL.Renderer renderer;

    // Offsets for touch events
    private float previousX;
    private float previousY;

    private float density;

    public SurfaceView(Context context)
    {
        super(context);
    }

    public SurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void handleError(final ErrorType errorType, final String cause) {
        // Queue on UI thread.
        post(new Runnable() {
            @Override
            public void run() {
                final String text;

                switch (errorType) {
                    case BUFFER_CREATION_ERROR:
                        text = String
                                .format(getContext().getResources().getString(
                                        R.string.solidify_could_not_create_vbo), cause);
                        break;
                    default:
                        text = String.format(
                                getContext().getResources().getString(
                                        R.string.solidify_error_unknown), cause);
                }

                Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event != null)
        {
            float x = event.getX();
            float y = event.getY();

            if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                if (renderer != null)
                {
                    float deltaX = (x - previousX) / density / 2f;
                    float deltaY = (y - previousY) / density / 2f;

                    renderer.deltaX += deltaX;
                    renderer.deltaY += deltaY;
                }
            }

            previousX = x;
            previousY = y;

            return true;
        }
        else
        {
            return super.onTouchEvent(event);
        }
    }

    // Hides superclass method.
    public void setRenderer(tec.solidify.OpenGL.Renderer renderer, float density)
    {
        this.renderer = renderer;
        this.density = density;
        super.setRenderer(renderer);
    }
}