package items_p;

import com.tilegame.Handler;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int hight, int damage){
        super(handler, x, y, width, hight, damage);
    }
}
