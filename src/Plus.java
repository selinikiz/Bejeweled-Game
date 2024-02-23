public class Plus extends MathJewel{

    Plus(){
        setGridLetter("+");
    }

    @Override
    void action(int row, int column) {
        direction4(row, column);
        direction6(row, column);
        direction2(row, column);
        direction8(row, column);
    }
}
