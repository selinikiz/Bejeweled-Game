public class Backslash extends MathJewel {

    Backslash(){
        setGridLetter("\\");
    }

    @Override
    void action(int row, int column) {
        direction1(row, column);
        direction9(row, column);
    }
}
