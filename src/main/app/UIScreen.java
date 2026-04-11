package app;

public class UIScreen extends Screen {

    public UIScreen(String title, double x, double y, double width, double height) {
        super(title, x, y, width, height);
    }

    public UIScreen(String title, double width, double height) {
        super(title, width, height);
    }

    public UIScreen(String title) {
        super(title);
    }

}
