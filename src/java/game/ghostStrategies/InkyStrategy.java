package game.ghostStrategies;

import game.Game;
import game.GameplayPanel;
import game.entities.ghosts.Ghost;
import game.utils.Utils;

//Chiến lược cụ thể của Inky (con ma xanh)
public class InkyStrategy implements IGhostStrategy{
    private Ghost otherGhost;
    public InkyStrategy(Ghost ghost) {
        this.otherGhost = ghost;
    }

    //Inky sử dụng vị trí của Blinky để nhắm mục tiêu vào Pacman: chúng ta lấy một vectơ giữa vị trí của Blinky
    // và một hình vuông phía trước Pacman và thêm vectơ này vào vị trí một hình vuông phía trước Pacman để lấy được
    // mục tiêu của Inky
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        int[] pacmanFacingPosition = Utils.getPointDistanceDirection(Game.getPacman().getxPos(), Game.getPacman().getyPos(), 32d, Utils.directionConverter(Game.getPacman().getDirection()));
        double distanceOtherGhost = Utils.getDistance(pacmanFacingPosition[0], pacmanFacingPosition[1], otherGhost.getxPos(), otherGhost.getyPos());
        double directionOtherGhost = Utils.getDirection(otherGhost.getxPos(), otherGhost.getyPos(), pacmanFacingPosition[0], pacmanFacingPosition[1]);
        int[] blinkyVectorPosition = Utils.getPointDistanceDirection(pacmanFacingPosition[0], pacmanFacingPosition[1], distanceOtherGhost, directionOtherGhost);
        position[0] = blinkyVectorPosition[0];
        position[1] = blinkyVectorPosition[1];
        return position;
    }

    //Đầu game, Inky nhắm mục tiêu vào ô phía dưới bên phải
    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = GameplayPanel.width;
        position[1] = GameplayPanel.height;
        return position;
    }
}
