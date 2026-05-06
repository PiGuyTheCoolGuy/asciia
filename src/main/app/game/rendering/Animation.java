package app.game.rendering;

/**
 * Represents an animation consisting of multiple frames. Each frame is a
 * <code>Texture</code>.
 */
public class Animation {
    private Texture[] frames;
    private int currentFrame;

    /**
     * Creates a new animation with the given frames.
     * 
     * @param frames
     */
    public Animation(Texture[] frames) {
        this.frames = frames;
        currentFrame = 0;
    }

    /**
     * Returns the current frame of the animation.
     * 
     * @return The current frame.
     */
    public Texture getCurrentFrame() {
        return frames[currentFrame];
    }

    /**
     * Advances the animation to the next frame.
     */
    public void nextFrame() {
        currentFrame = (currentFrame + 1) % frames.length;
    }

    /**
     * Returns the index of the current frame.
     * 
     * @return The index of the current frame.
     */
    public int getCurrentFrameIndex() {
        return currentFrame;
    }

    /**
     * Returns the total number of frames in the animation.
     * 
     * @return The total number of frames.
     */
    public int getFrameCount() {
        return frames.length;
    }

    /**
     * Sets the current frame of the animation.
     * 
     * @param index The index of the frame to set as current.
     */
    public void setCurrentFrame(int index) {
        currentFrame = index % frames.length;
    }

}
