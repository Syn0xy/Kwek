package model.util;

public class MutableNumber<E> {

    protected E value;

    protected MutableNumber(E value){
        this.value = value;
    }
    
    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

}
