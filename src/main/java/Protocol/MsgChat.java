package Protocol;

import Main.Main;

public class MsgChat extends MsgBase{
    private static final long serialVersionUID = 6L;
    public String username;
    public String content;
    public MsgChat(String content) {
        username = Main.username;
        this.content = content;
        this.protoName = "MsgChat";
    }
}
