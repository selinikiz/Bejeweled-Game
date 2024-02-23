public class Slash extends MathJewel {

    Slash(){
        setGridLetter("/");
    }

    @Override
    void action(int row, int column) {
        direction3(row, column);
        direction7(row, column);
    }
}
