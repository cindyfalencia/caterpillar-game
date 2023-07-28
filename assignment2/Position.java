package assignment2;

public class Position {
    private int xCoordinate;
    private int yCoordinate;

    public Position(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public Position(Position p){
        this.xCoordinate = p.xCoordinate;
        this.yCoordinate = p.yCoordinate;
    }

    public void reset(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public void reset(Position p){
        this.xCoordinate = p.xCoordinate;
        this.yCoordinate = p.yCoordinate;
    }

    public static int getDistance(Position p1, Position p2) {
        int dx = Math.abs(p1.getX() - p2.getX());
        int dy = Math.abs(p1.getY() - p2.getY());
        return dx + dy;
    }

    public int getX() {
        return this.xCoordinate;
    }

    public int getY() {
        return this.yCoordinate;
    }

    public void moveWest() {
        this.xCoordinate--;
    }

    public void moveEast() {
        this.xCoordinate++;
    }

    public void moveNorth() {
        this.yCoordinate--;
    }

    public void moveSouth() {
        this.yCoordinate++;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position other = (Position) obj;
            return this.xCoordinate == other.xCoordinate && this.yCoordinate == other.yCoordinate;
        }
        return false;
    }
}

