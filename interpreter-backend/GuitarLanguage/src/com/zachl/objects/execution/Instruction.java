package com.zachl.objects.execution;

public class Instruction {
    private interface Body{
        void execute(InstructionHead head);
    }
    private enum Header {
        Store("str"),
        Reference("ref"),
        Manipulate("man");
        String tag;
        Header(String tag){
            this.tag = tag;
        }
    }
    public Header header;
    public Body body;
    public int position;
    private String[] params;
    public Instruction(int position, String arg, String[] params){
       header = Header.valueOf(arg);
       this.position = position;
       this.params = params;
    }
    public void execute(InstructionHead head) {
        body.execute(head);
        switch(header) {
            case Store:
                body = headtemp -> {headtemp.goToNext();};
                break;
            case Reference:
                body = head1 -> {};
                break;
            default:
                body = headtemp -> {headtemp.goToNext();};
        }
    }
}
