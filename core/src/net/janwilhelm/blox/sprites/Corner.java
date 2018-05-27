package net.janwilhelm.blox.sprites;

public enum Corner {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT;

    public static Corner[] top() {
        return new Corner[] {
                TOP_LEFT,
                TOP_RIGHT
        };
    }

    public static Corner[] bottom() {
        return new Corner[] {
                BOTTOM_LEFT,
                BOTTOM_RIGHT
        };
    }

    public static Corner[] left() {
        return new Corner[] {
                BOTTOM_LEFT,
                TOP_LEFT
        };
    }

    public static Corner[] right() {
        return new Corner[] {
                BOTTOM_RIGHT,
                TOP_RIGHT
        };
    }
}
