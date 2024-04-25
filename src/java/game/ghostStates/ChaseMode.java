package game.ghostStates;

import game.entities.ghosts.Ghost;

//Lớp mô tả cụ thể trạng thái của một con ma đuổi theo Pacman
public class ChaseMode extends GhostState{
    public ChaseMode(Ghost ghost) {
        super(ghost);
    }

    //Chuyển đổi khi ăn SuperPacGum
    @Override
    public void superPacGumEaten() {
        ghost.switchFrightenedMode();
    }

    //Chuyển đổi khi bộ đếm thời gian trạng thái hiện tại kết thúc (nó xen kẽ giữa ChaseMode và ScatterMode)
    @Override
    public void timerModeOver() {
        ghost.switchScatterMode();
    }

    //Ở trạng thái này, vị trí mục tiêu phụ thuộc vào chiến lược của hồn ma
    @Override
    public int[] getTargetPosition() {
        return ghost.getStrategy().getChaseTargetPosition();
    }
}
