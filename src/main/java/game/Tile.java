package game;

public class Tile {
    private String type; // نوع زمین (خاکی، آب، جنگل و ...)
    private boolean isBuildable; // آیا قابل ساخت است؟

    public Tile(String type, boolean isBuildable) {
        this.type = type;
        this.isBuildable = isBuildable;
    }

    public String getType() {
        return type;
    }

    public boolean isBuildable() {
        return isBuildable;
    }
}
