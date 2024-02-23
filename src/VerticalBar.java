public class VerticalBar extends MathJewel{

    VerticalBar(){
        setGridLetter("|");
    }

    @Override
    void action(int row, int column) {
        direction2(row, column);
        direction8(row,column);
    }
}
