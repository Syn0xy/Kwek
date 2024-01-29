package model.input;

public enum KeyCode {
    A("a", 65),
    B("b", 66),
    C("c", 67),
    D("d", 68),
    E("e", 69),
    F("f", 70),
    G("g", 71),
    H("h", 72),
    I("i", 73),
    J("j", 74),
    K("k", 75),
    L("l", 76),
    M("m", 77),
    N("n", 78),
    O("o", 79),
    P("p", 80),
    Q("q", 81),
    R("r", 82),
    S("s", 83),
    T("t", 84),
    U("u", 85),
    V("v", 86),
    W("w", 87),
    X("x", 88),
    Y("y", 89),
    Z("z", 90),
    ALPHA_0("0", 48),
    ALPHA_1("1", 49),
    ALPHA_2("2", 50),
    ALPHA_3("3", 51),
    ALPHA_4("4", 52),
    ALPHA_5("5", 53),
    ALPHA_6("6", 54),
    ALPHA_7("7", 55),
    ALPHA_8("8", 56),
    ALPHA_9("9", 57),
    UP_ARROW("up", 265),
    DOWN_ARROW("down", 264),
    RIGHT_ARROW("right", 262),
    LEFT_ARROW("left", 263),
    NUMPAD_0("numpad-0", 320),
    NUMPAD_1("numpad-1", 321),
    NUMPAD_2("numpad-2", 322),
    NUMPAD_3("numpad-3", 323),
    NUMPAD_4("numpad-4", 324),
    NUMPAD_5("numpad-5", 325),
    NUMPAD_6("numpad-6", 326),
    NUMPAD_7("numpad-7", 327),
    NUMPAD_8("numpad-8", 328),
    NUMPAD_9("numpad-9", 329),
    F1("f1", 290),
    F2("f2", 291),
    F3("f3", 292),
    F4("f4", 293),
    F5("f5", 294),
    F6("f6", 295),
    F7("f7", 296),
    F8("f8", 297),
    F9("f9", 298),
    F10("f10", 299),
    F11("f11", 300),
    F12("f12", 301),
    CAPS_LOCK("caps lock", 280),
    CTRL("ctrl", 341),
    ALT("alt", 342),
    SHIFT("shift", 340),
    SPACE("space", 32),
    ENTER("enter", 257),
    ESCAPE("escape", 256),
    MOUSE_1("mouse1", 1),
    MOUSE_2("mouse2", 2),
    MOUSE_3("mouse3", 3),
    MOUSE_4("mouse4", 4),
    MOUSE_5("mouse5", 5);
    
    private String name;
    
    private int key;
    
    private KeyCode(String name, int key){
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }
    
    public static KeyCode getKey(int key){
        for(KeyCode k : values()){
            if(k.key == key){
                return k;
            }
        }
        return null;
    }

}
