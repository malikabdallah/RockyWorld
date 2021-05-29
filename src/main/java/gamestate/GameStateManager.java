package gamestate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GameStateManager {

    private List<GameState>gamestates;
    private int currentState;

    public static final int MENU_STATE=0;
    public static final int LEVEL1STATE=1;

    public GameStateManager() {
        gamestates=new ArrayList<GameState>();
        currentState=MENU_STATE;
        gamestates.add(new MenuState(this));
    }

    public void setState(int state){
        currentState=state;
        gamestates.get(state).init();

    }

    public void update(){
        gamestates.get(currentState).update();
    }
    public void draw(Graphics2D g){

        gamestates.get(currentState).draw(g);
    }

    public void keyPressed(int k){
        gamestates.get(currentState).keyPressed(k);;

    }

    public void keyReleased(int k){
        gamestates.get(currentState).keyReleased(k);;

    }
}
