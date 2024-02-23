public class Triangle extends Jewel{

    Triangle() {
        setJewelPoint(15);
        setGridLetter("T");
    }

    @Override
    void action(int row, int column) {
        direction2(row,column);
        direction8(row,column);
    }
}
