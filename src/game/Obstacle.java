package game;

import city.cs.engine.*;

public class Obstacle extends StaticBody {
    public Obstacle(World world, Shape shape) {
        super(world, shape);
        setFillColor(java.awt.Color.RED);
    }
}
