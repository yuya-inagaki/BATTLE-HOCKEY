package common;

// 双方のプレイヤー位置
public class GameInfo {
	public int p1y;
	public int p2y;

public static GameInfo initialGameInfo(){
    GameInfo res = new GameInfo();
    res.p1y = 250;
    res.p2y = 250;
    return res;
};
}
