# android-tooltip
Create tooltip to show information about widget like button.Many apps required coach mark to display at the screen to guide users on its first launch.


You create this type of coachmarks by just copying "TooltipWindow.java" to your project .Call from any place you need to display by the below code.

    tipWindow = new TooltipWindow(MainActivity.this, TooltipWindow.DRAW_BOTTOM, "Click here to view th magic");
    tipWindow.showToolTip(btnClick, TooltipWindow.DRAW_ARROW_DEFAULT_CENTER, false);


![Alt text](/screenshots/Screenshot_2009-01-02-13-10-19.png?raw=true "Optional Title")

![Alt text](/screenshots/Screenshot_2009-01-02-13-10-22.png?raw=true "Optional Title")


