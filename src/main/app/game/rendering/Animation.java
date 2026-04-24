package app.game.rendering;

public class Animation {
    private Texture[] frames;
    private int currentFrame;

    public Animation(Texture[] frames) {
        this.frames = frames;
        currentFrame = 0;
    }

    public Animation(String filePath) { // XXX
    }

    public Texture getCurrentFrame() {
        return frames[currentFrame];
    }

    public void nextFrame() {
        currentFrame = (currentFrame + 1) % frames.length;
    }

}
