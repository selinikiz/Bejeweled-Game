public class Square extends Jewel{

    Square() {
        setJewelPoint(15);
        setGridLetter("S");
    }

    @Override
    void action(int row, int column) {
        direction4(row,column);
        direction6(row,column);
    }
}
