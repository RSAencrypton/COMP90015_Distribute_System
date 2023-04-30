package Protocol;

public class MsgJoinRes extends MsgBase{
    private static final long serialVersionUID = 5L;
    public Boolean res;
    public MsgJoinRes(Boolean res) {
        this.res = res;
        this.protoName = "MsgJoinRes";
    }
}
