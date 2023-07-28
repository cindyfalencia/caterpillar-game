package assignment2;

public class World {
    private Caterpillar caterpillar;
    private Position foodPos;
    private Region map;
    private ActionQueue actions;
    private TargetQueue foods;
    private GameState state;

    public World(TargetQueue a, ActionQueue b){
        this.foods = a;
        this.actions = b;
        this.map = new Region  (0, 0, 15, 15);
        this.caterpillar = new Caterpillar();
        this.foodPos = foods.dequeue();
        this.state = GameState.MOVE;
    }

    public void step() {
        if(actions.isEmpty()){
            state = GameState.NO_MORE_ACTION;
        }
        if(!(isRunning())){
            return;
        }

        Direction direction = actions.dequeue();
        Position newHead = new Position(caterpillar.getHead());

        switch(direction){
            case NORTH -> newHead.moveNorth();
            case EAST -> newHead.moveEast();
            case WEST -> newHead.moveWest();
            case SOUTH -> newHead.moveSouth();
        }

        if (!map.contains(newHead)) {
            state = GameState.WALL_COLLISION;
        } else if (caterpillar.selfCollision(newHead)) {
            state = GameState.SELF_COLLISION;
        } else if (newHead.equals(foodPos)) {
            caterpillar.eat(foodPos);
            if (foods.isEmpty()) {
                state = GameState.DONE;
            } else {
                foodPos = foods.dequeue();
                state = GameState.EAT;
            }
        } else {
            caterpillar.move(newHead);
            state = GameState.MOVE;
        }
    }
    public GameState getState() {
        return state;
    }
    public Caterpillar getCaterpillar() {
        return caterpillar;
    }

    public Position getFood() {
        return foodPos;
    }
    public boolean isRunning() {
        return state == GameState.MOVE || state == GameState.EAT;
    }
}
