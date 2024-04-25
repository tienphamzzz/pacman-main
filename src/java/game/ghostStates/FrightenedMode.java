package game.ghostStates;

import game.entities.ghosts.Ghost;
import game.utils.Utils;
import jdk.jshell.execution.Util;

//Classe pour décrire l'état concret d'un fantôme effrayé (après que Pacman ait mangé une SuperPacGum)
public class FrightenedMode extends GhostState{
    public FrightenedMode(Ghost ghost) {
        super(ghost);
    }

    //Transition lorsque le fantôme est mangé
    @Override
    public void eaten() {
        ghost.switchEatenMode();
    }

    //Transition lorsque le timer d'état effrayé est terminé
    @Override
    public void timerFrightenedModeOver() {
        ghost.switchChaseModeOrScatterMode();
    }

    // Ở trạng thái này, vị trí mục tiêu là một hình vuông ngẫu nhiên xung quanh con ma
    @Override
    public int[] getTargetPosition(){
        int[] position = new int[2];

        boolean randomAxis = Utils.randomBool();
        position[0] = ghost.getxPos() + (randomAxis ? Utils.randomInt(-1,1) * 32 : 0);
        position[1] = ghost.getyPos() + (!randomAxis ? Utils.randomInt(-1,1) * 32 : 0);
        return position;
    }
}
