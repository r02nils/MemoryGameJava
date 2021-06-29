public class Karte {
    private int number;
    private boolean isTurned;
    private int value;
    private String color;
    private String img;

    public void setColor() {

    }

    public void setImg() {

    }

    public boolean isMatched(){
        return true;
    }

    public void setJokerCard(){
        this.value = 2;
    }

    public int getCardValue(){
        return this.value;
    }

}
