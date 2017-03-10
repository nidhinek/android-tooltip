package com.tooltip.nidhin.android_tooltip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tooltip.nidhin.android_tooltip.util.TooltipWindow;

public class MainActivity extends AppCompatActivity {
    private TooltipWindow tipWindow;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClick = (Button) findViewById(R.id.button);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tipWindow = new TooltipWindow(MainActivity.this, TooltipWindow.DRAW_TOP, "Hello  -Top");
                tipWindow.showToolTip(btnClick, TooltipWindow.DRAW_ARROW_DEFAULT_CENTER, true);
                tipWindow = new TooltipWindow(MainActivity.this, TooltipWindow.DRAW_RIGHT, "Right");
                tipWindow.showToolTip(btnClick, TooltipWindow.DRAW_ARROW_DEFAULT_CENTER, true);
                tipWindow = new TooltipWindow(MainActivity.this, TooltipWindow.DRAW_LEFT, "Left");
                tipWindow.showToolTip(btnClick, TooltipWindow.DRAW_ARROW_DEFAULT_CENTER, true);
                tipWindow = new TooltipWindow(MainActivity.this, TooltipWindow.DRAW_BOTTOM, "Top Right Arrow");
                tipWindow.showToolTip(btnClick, TooltipWindow.DRAW_ARROW_TOP_RIGHT, true);

            }
        });
    }

    public void showCoachMark() {

        tipWindow = new TooltipWindow(MainActivity.this, TooltipWindow.DRAW_BOTTOM, "Click here to view th magic");
        tipWindow.showToolTip(btnClick, TooltipWindow.DRAW_ARROW_DEFAULT_CENTER, false);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            showCoachMark();
    }
}
