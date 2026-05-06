package app;

/**
 * Represents a 2D vector with integer components. This class provides basic
 * vector operations such as addition, subtraction, multiplication, and
 * division, as well as utility methods for calculating the dot product, length,
 * and other properties of the vector.
 */
public class Vec2i {

    public int x;
    public int y;

    // -------------------------
    // Constructors
    // -------------------------

    /**
     * Create a new Vec2i with the given x and y components. If no components are
     * provided, the vector will be initialized to (0, 0).
     */
    public Vec2i() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Create a new Vec2i with the given x and y components.
     * 
     * @param x The x component of the vector.
     * @param y The y component of the vector.
     */
    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new Vec2i by copying the components of another Vec2i.
     * 
     * @param other The Vec2i to copy.
     */
    public Vec2i(Vec2i other) {
        this.x = other.x;
        this.y = other.y;
    }

    // -------------------------
    // Basic math (immutable style)
    // -------------------------

    /**
     * Add another Vec2i to this vector and return the result as a new Vec2i. This
     * method does not modify the original vector.
     * 
     * @param v The Vec2i to add to this vector.
     * @return A new Vec2i that is the sum of this vector and the given vector.
     */
    public Vec2i add(Vec2i v) {
        return new Vec2i(this.x + v.x, this.y + v.y);
    }

    /**
     * Subtract another Vec2i from this vector and return the result as a new Vec2i.
     * This method does not modify the original vector.
     * 
     * @param v The Vec2i to subtract from this vector.
     * @return A new Vec2i that is the difference of this vector and the given
     *         vector.
     */
    public Vec2i subtract(Vec2i v) {
        return new Vec2i(this.x - v.x, this.y - v.y);
    }

    /**
     * Multiply this vector by a scalar and return the result as a new Vec2i. This
     * method does not modify the original vector.
     * 
     * @param scalar The scalar to multiply this vector by.
     * @return A new Vec2i that is the product of this vector and the given scalar.
     */
    public Vec2i multiply(int scalar) {
        return new Vec2i(this.x * scalar, this.y * scalar);
    }

    /**
     * Divide this vector by a scalar and return the result as a new Vec2i. This
     * method
     * does not modify the original vector.
     * 
     * @param scalar The scalar to divide this vector by.
     * @return A new Vec2i that is the quotient of this vector and the given scalar.
     */
    public Vec2i divide(int scalar) {
        if (scalar == 0) {
            return new Vec2i(0, 0); // Avoid division by zero, return zero vector
        }
        return new Vec2i(this.x / scalar, this.y / scalar);
    }

    // -------------------------
    // In-place operations (mutable style)
    // -------------------------

    /**
     * Add another Vec2i to this vector in place. This method modifies the original
     * vector and returns it for chaining.
     * 
     * @param v The Vec2i to add to this vector.
     * @return This vector after adding the given vector to it.
     */
    public Vec2i addInPlace(Vec2i v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    /**
     * Subtract another Vec2i from this vector in place. This method modifies the
     * original vector and returns it for chaining.
     * 
     * @param v The Vec2i to subtract from this vector.
     * @return This vector after subtracting the given vector from it.
     */
    public Vec2i subtractInPlace(Vec2i v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Multiply this vector by a scalar in place. This method modifies the original
     * vector and returns it for chaining.
     * 
     * @param scalar The scalar to multiply this vector by.
     * @return This vector after multiplying it by the given scalar.
     */
    public Vec2i multiplyInPlace(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    /**
     * Divide this vector by a scalar in place. This method modifies the original
     * vector and returns it for chaining.
     * 
     * @param scalar The scalar to divide this vector by.
     * @return This vector after dividing it by the given scalar.
     */
    public Vec2i divideInPlace(int scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    // -------------------------
    // Utility methods
    // -------------------------

    /**
     * Calculate the dot product of this vector and another Vec2i.
     * 
     * @param v The Vec2i to calculate the dot product with.
     * @return The dot product of this vector and the given vector.
     */
    public int dot(Vec2i v) {
        return this.x * v.x + this.y * v.y;
    }

    /**
     * Calculate the Manhattan length of this vector, which is the sum of the
     * absolute values of its components.
     * 
     * @return The Manhattan length of this vector.
     */
    public int manhattanLength() {
        return Math.abs(x) + Math.abs(y);
    }

    /**
     * Calculate the Euclidean length of this vector, which is the square root of
     * the sum of the squares of its components.
     * 
     * @return The Euclidean length of this vector.
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Set the components of this vector to the given x and y values.
     * 
     * @param x The new x component of the vector.
     * @param y The new y component of the vector.
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set the components of this vector to the components of another Vec2i.
     * 
     * @param v The Vec2i to copy the components from.
     */
    public void set(Vec2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Create a copy of this vector and return it as a new Vec2i. This method does
     * not modify the original vector.
     * 
     * @return A new Vec2i with the same components as this vector.
     */
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