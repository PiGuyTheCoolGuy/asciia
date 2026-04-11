package app;

public class Vec2i {

    public int x;
    public int y;

    // -------------------------
    // Constructors
    // -------------------------

    public Vec2i() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i(Vec2i other) {
        this.x = other.x;
        this.y = other.y;
    }

    // -------------------------
    // Basic math (immutable style)
    // -------------------------

    public Vec2i add(Vec2i v) {
        return new Vec2i(this.x + v.x, this.y + v.y);
    }

    public Vec2i subtract(Vec2i v) {
        return new Vec2i(this.x - v.x, this.y - v.y);
    }

    public Vec2i multiply(int scalar) {
        return new Vec2i(this.x * scalar, this.y * scalar);
    }

    public Vec2i divide(int scalar) {
        return new Vec2i(this.x / scalar, this.y / scalar);
    }

    // -------------------------
    // In-place operations (mutable style)
    // -------------------------

    public Vec2i addInPlace(Vec2i v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public Vec2i subtractInPlace(Vec2i v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vec2i multiplyInPlace(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vec2i divideInPlace(int scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    // -------------------------
    // Utility methods
    // -------------------------

    public int dot(Vec2i v) {
        return this.x * v.x + this.y * v.y;
    }

    public int manhattanLength() {
        return Math.abs(x) + Math.abs(y);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vec2i copy() {
        return new Vec2i(this.x, this.y);
    }

    // -------------------------
    // Object overrides
    // -------------------------

    @Override
    public String toString() {
        return "Vec2i(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Vec2i))
            return false;
        Vec2i v = (Vec2i) obj;
        return this.x == v.x && this.y == v.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}