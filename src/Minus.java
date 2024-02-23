public class Minus extends MathJewel {

    Minus(){
        setGridLetter("-");
    }

    @Override
    void action(int row, int column) {
        direction4(row, column);
        direction6(row, column);
    }
}
