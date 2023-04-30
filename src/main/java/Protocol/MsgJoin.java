package Protocol;

public class MsgJoin extends MsgBase{
    private static final long serialVersionUID = 4L;
    public String username;
    public MsgJoin(String username) {
        this.username = username;
        this.protoName = "MsgJoin";
    }
}
