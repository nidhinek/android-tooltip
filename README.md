# android-tooltip
Create tooltip to show information about widget like button.Many apps required coach mark to display at the screen to guide users on its first launch.

You create this type of coachmarks by just copying "TooltipWindow.java" to your project .Call from any place you need to display by the below code.

    tipWindow = new TooltipWindow(MainActivity.this, TooltipWindow.DRAW_BOTTOM, "Click here to view th magic");
    tipWindow.showToolTip(btnClick, TooltipWindow.DRAW_ARROW_DEFAULT_CENTER, false);


![Alt text](/screenshots/Screenshot_2009-01-02-13-10-19.png?raw=true "Optional Title")

![Alt text](/screenshots/Screenshot_2009-01-02-13-10-22.png?raw=true "Optional Title")

In this project tooltip and its arrow created by using xml only (No images used).
Arrow created using rotating a rectangle based on the pivot point.Below xml used for creating right arrow

        <rotate
            android:fromDegrees="-45"
            android:pivotX="-50%"
            android:pivotY="80%"
            android:toDegrees="45">
            <shape android:shape="rectangle">
                <solid android:color="@color/coachbubble"></solid>

                <stroke
                    android:width="2dp"
                    android:color="@color/coachbubble" />
            </shape>
        </rotate>

   Right Arrow image

![Alt text](/screenshots/arrow.png?raw=true "Right Arrow")


