package game.ghostStates;

import game.entities.ghosts.Ghost;
import game.utils.Utils;
import game.utils.WallCollisionDetector;

//Classe abstrate pour décrire les différents états de fantômes
public abstract class GhostState {
    protected Ghost ghost;

    public GhostState(Ghost ghost) {
        this.ghost = ghost;
    }

    //Những chuyển đổi khác nhau có thể xảy ra từ trạng thái này sang trạng thái khác
    public void superPacGumEaten() {}
    public void timerModeOver() {}
    public void timerFrightenedModeOver() {}
    public void eaten() {}
    public void outsideHouse() {}
    public void insideHouse() {}

    public int[] getTargetPosition(){
        return new int[2];
    } //trả về điểm mà con ma sẽ nhắm tới

    //Phương pháp tính hướng tiếp theo con ma sẽ đi
    public void computeNextDir() {
        int new_xSpd = 0;
        int new_ySpd = 0;

        if (!ghost.onTheGrid()) return; //Con ma phải ở trên một “hình vuông” của khu vui chơi
        if (!ghost.onGameplayWindow()) return;  //Con ma chắc chắn đang ở trong khu vui chơi

        double minDist = Double.MAX_VALUE; //Khoảng cách tối thiểu hiện tại giữa bóng ma và mục tiêu theo hướng tiếp theo của nó

        //Nếu con ma hiện đang đi về bên trái và không có bức tường nào ở bên trái...
        if (ghost.getxSpd() <= 0 && !WallCollisionDetector.checkWallCollision(ghost, -ghost.getSpd(), 0)) {
            //Chúng tôi xem xét khoảng cách giữa vị trí được nhắm mục tiêu và vị trí tiềm năng của bóng ma nếu vị trí sau đi sang trái
            double distance = Utils.getDistance(ghost.getxPos() - ghost.getSpd(), ghost.getyPos(), getTargetPosition()[0], getTargetPosition()[1]);

            //Nếu khoảng cách này nhỏ hơn khoảng cách tối thiểu hiện tại thì ta nói bóng ma đang đi về bên trái và chúng ta cập nhật khoảng cách tối thiểu đó
            if (distance < minDist) {
                new_xSpd = -ghost.getSpd();
                new_ySpd = 0;
                minDist = distance;
            }
        }

        //Kiểm tra điều tương tự ở bên phải
        if (ghost.getxSpd() >= 0 && !WallCollisionDetector.checkWallCollision(ghost, ghost.getSpd(), 0)) {
            double distance = Utils.getDistance(ghost.getxPos() + ghost.getSpd(), ghost.getyPos(),  getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = ghost.getSpd();
                new_ySpd = 0;
                minDist = distance;
            }
        }

        //Kiểm tra điều tương tự trở lên
        if (ghost.getySpd() <= 0 && !WallCollisionDetector.checkWallCollision(ghost, 0, -ghost.getSpd())) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() - ghost.getSpd(), getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = -ghost.getSpd();
                minDist = distance;
            }
        }

        //Điều tương tự đang được thử nghiệm
        if (ghost.getySpd() >= 0 && !WallCollisionDetector.checkWallCollision(ghost, 0, ghost.getSpd())) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() + ghost.getSpd(), getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = ghost.getSpd();
                minDist = distance;
            }
        }

        if (new_xSpd == 0 && new_ySpd == 0) return;

        //Khi tất cả các trường hợp đã được kiểm tra, chúng tôi thay đổi hướng của bóng ma (trong trường hợp hướng này
        // được xác định bởi tốc độ ngang và tốc độ dọc, chúng tôi vẫn kiểm tra để nó không thể đi theo đường chéo)
        if (Math.abs(new_xSpd) != Math.abs(new_ySpd)) {
            ghost.setxSpd(new_xSpd);
            ghost.setySpd(new_ySpd);
        } else {
            if (new_xSpd != 0) {
                ghost.setxSpd(0);
                ghost.setxSpd(new_ySpd);
            }else{
                ghost.setxSpd(new_xSpd);
                ghost.setySpd(0);
            }
        }
    }
}
