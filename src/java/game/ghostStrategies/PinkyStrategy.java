package game.ghostStrategies;

import game.Game;
import game.utils.Utils;


//Chiến lược cụ thể của Pinky (con ma hồng)
public class PinkyStrategy implements IGhostStrategy {
    // Pinky nhắm vào hai ô vuông trước mặt Pacman
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        int[] pacmanFacingPosition = Utils.getPointDistanceDirection(Game.getPacman().getxPos(), Game.getPacman().getyPos(), 64, Utils.directionConverter(Game.getPacman().getDirection()));
        position[0] = pacmanFacingPosition[0];
        position[1] = pacmanFacingPosition[1];
        return position;
    }

    //Đầu game, Pinky nhắm vào ô ở trên cùng bên trái
    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = 0;
        position[1] = 0;
        return position;
    }
}
