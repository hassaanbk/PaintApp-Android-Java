package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView reusableImageView;

    private int startX = 1;
    private int startY = 1;
    private int endX = 25;
    private int endY = 25;


    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        //paint = setColour(paint);
        paint.setStrokeWidth(25);

        bitmap = Bitmap.createBitmap(
                (int) getWindowManager().getDefaultDisplay().getWidth(),
                (int) getWindowManager().getDefaultDisplay().getHeight(),
                Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.LTGRAY);

        reusableImageView = (ImageView)findViewById(R.id.ImageViewForDrawing);

        reusableImageView.setImageBitmap(bitmap);
        reusableImageView.setVisibility(View.VISIBLE);

        Spinner dropdown = findViewById(R.id.spinner);
        int[] thickness = new int[]{20,30,40,50};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
    }

    private void setColour(Paint paint)
    {
        RadioGroup colors = findViewById(R.id.colors);
        int colorId = colors.getCheckedRadioButtonId();
        RadioButton color = (RadioButton) findViewById(colorId);
        String colorText = (String) color.getText();

        if(colorText == "Red")
            paint.setColor(Color.RED);
        else if(colorText == "Green")
            paint.setColor(Color.GREEN);
        else if(colorText == "Blue")
            paint.setColor(Color.BLUE);
        else
            paint.setColor(Color.BLACK);
        //return paint;
    }



    public void clearCanvas(View v)
    {
        canvas.drawColor(Color.CYAN);
        startX = 1;
        startY = 1;
        endX = 300;
        endY = 300;

    }
    public void startDrawing(View v)
    {
        setColour(paint);
        canvas.drawPoint(1,1,paint);


    }
    public void drawLine(Canvas canvas)
    {
        canvas.drawLine(startX, startY, endX, endY, paint);
        startX=endX;
        startY=endY;

    }

    public void up(View view)
    {
        reusableImageView.setFocusable(true);
        reusableImageView.requestFocus();
        endY=endY-5;
        drawLine(canvas);

        reusableImageView.invalidate();
    }
    public void down(View view)
    {
        reusableImageView.setFocusable(true);
        reusableImageView.requestFocus();
        endY=endY+5;
        drawLine(canvas);

        reusableImageView.invalidate();
    }
    public void left(View view)
    {
        reusableImageView.setFocusable(true);
        reusableImageView.requestFocus();
        endX=endX-5;
        drawLine(canvas);
        reusableImageView.invalidate();
    }
    public void right(View view)
    {
        reusableImageView.setFocusable(true);
        reusableImageView.requestFocus();
        endX=endX+5;
        drawLine(canvas);
        reusableImageView.invalidate();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_DOWN:

                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endY=endY+5;
                drawLine(canvas);

                reusableImageView.invalidate();
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endX=endX+5;
                drawLine(canvas);
                reusableImageView.invalidate();

                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endX=endX-5;
                drawLine(canvas);
                reusableImageView.invalidate();

                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endY=endY-5;
                drawLine(canvas);

                reusableImageView.invalidate();
                return true;
        }
        return false;
    }
}