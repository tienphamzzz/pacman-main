package game.ghostStrategies;

import game.Game;
import game.GameplayPanel;

//Chiến lược cụ thể của Blinky (con ma đỏ)
public class BlinkyStrategy implements IGhostStrategy{
    //Blinky nhắm thẳng vào vị trí của Pacman
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        position[0] = Game.getPacman().getxPos();
        position[1] = Game.getPacman().getyPos();
        return position;
    }

    //Trong khi tạm dừng, Blinky nhắm mục tiêu vào ô ở trên cùng bên phải
    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = GameplayPanel.width;
        position[1] = 0;
        return position;
    }
}
