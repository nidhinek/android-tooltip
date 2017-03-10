package com.tooltip.nidhin.android_tooltip.util;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tooltip.nidhin.android_tooltip.R;

public class TooltipWindow {

    public static final int DRAW_LEFT = 1;
    public static final int DRAW_RIGHT = 2;
    public static final int DRAW_TOP = 3;
    public static final int DRAW_BOTTOM = 4;
    public static final int DRAW_ARROW_TOP_RIGHT = 2;
    public static final int DRAW_ARROW_DEFAULT_CENTER = 1;
    private static final int MSG_DISMISS_TOOLTIP = 100;
    View contentView;
    TextView mInfoText;
    ImageView mImageArrow;
    private Context ctx;
    private PopupWindow tipWindow;
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_DISMISS_TOOLTIP:
                    if (tipWindow != null && tipWindow.isShowing())
                        tipWindow.dismiss();
                    break;
            }
        }

        ;
    };
    private LayoutInflater inflater;
    private int position = 4;


    public TooltipWindow(Context ctx, int position, String text) {
        this.ctx = ctx;
        this.position = position;
        tipWindow = new PopupWindow(ctx);

        inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layout = 0;
        switch (position) {
            case DRAW_BOTTOM:
                layout = R.layout.tooltip_bottom_layout;
                break;

            case DRAW_TOP:
                layout = R.layout.tooltip_top_layout;
                break;
            case DRAW_LEFT:
                layout = R.layout.tooltip_left_layout;
                break;
            case DRAW_RIGHT:
                layout = R.layout.tooltip_right_layout;
                break;
        }
        contentView = inflater.inflate(layout, null);
        mInfoText = (TextView) contentView.findViewById(R.id.tooltip_text);
        mImageArrow = (ImageView) contentView.findViewById(R.id.tooltip_nav_up);
        mInfoText.setText(text);

    }

    public void showToolTip(View anchor, int arroPosition, boolean autoDismiss) {
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, ctx.getResources().getDisplayMetrics());
        switch (arroPosition) {
            case DRAW_ARROW_TOP_RIGHT:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(height, height);
                layoutParams.gravity = Gravity.RIGHT;
                layoutParams.setMargins(0, 0, 10, 0);

                mImageArrow.setLayoutParams(layoutParams);
                break;


        }
        tipWindow.setHeight(LayoutParams.WRAP_CONTENT);
        tipWindow.setWidth(LayoutParams.WRAP_CONTENT);
        tipWindow.setOutsideTouchable(true);
        tipWindow.setTouchable(false);
        tipWindow.setFocusable(false);
        tipWindow.setBackgroundDrawable(new BitmapDrawable());

        tipWindow.setContentView(contentView);

        int screen_pos[] = new int[2];
        // Get location of anchor view on screen
        anchor.getLocationOnScreen(screen_pos);

        // Get rect for anchor view
        Rect anchor_rect = new Rect(screen_pos[0], screen_pos[1], screen_pos[0]
                + anchor.getWidth(), screen_pos[1] + anchor.getHeight());

        // Call view measure to calculate how big your view should be.
        contentView.measure(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        int contentViewHeight = contentView.getMeasuredHeight();
        int contentViewWidth = contentView.getMeasuredWidth();

// In this case , i dont need much calculation for x and y position of
// tooltip
// For cases if anchor is near screen border, you need to take care of
// direction as well
// to show left, right, above or below of anchor view
        int position_x = 0, position_y = 0;
        // int position_x = anchor_rect.centerX() - (contentViewWidth - contentViewHeight / 2);
        //int position_y = anchor_rect.bottom - (anchor_rect.height());
        switch (position) {
            case DRAW_BOTTOM:
                position_x = anchor_rect.centerX() - (contentViewWidth - contentViewWidth / 2);
                position_y = anchor_rect.bottom - (anchor_rect.height() / 2) + 10;
                break;
            case DRAW_TOP:
                position_x = anchor_rect.centerX() - (contentViewWidth - contentViewWidth / 2);
                position_y = anchor_rect.top - (anchor_rect.height());
                break;
            case DRAW_LEFT:
                DRAW_RIGHT:
                position_x = anchor_rect.left - (contentViewWidth) - 30;
                position_y = anchor_rect.top;
                break;
            case DRAW_RIGHT:
                position_x = anchor_rect.right;
                position_y = anchor_rect.top;
                break;
        }
        tipWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, position_x,
                position_y);

        if (autoDismiss) {
// send message to handler to dismiss tipWindow after X milliseconds
            handler.sendEmptyMessageDelayed(MSG_DISMISS_TOOLTIP, 4000);
        }


    }

    public boolean isTooltipShown() {
        if (tipWindow != null && tipWindow.isShowing())
            return true;
        return false;
    }

    public void dismissTooltip() {
        if (tipWindow != null && tipWindow.isShowing())
            tipWindow.dismiss();
    }

    public PopupWindow getView() {
        return tipWindow;
    }

}