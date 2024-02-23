public class Wildcard extends Jewel{

    Wildcard() {
        setJewelPoint(10);
        setGridLetter("W");
    }

    @Override
    void action(int row, int column) {
        direction2(row,column);
        direction8(row,column);
        direction4(row,column);
        direction6(row,column);
        direction1(row,column);
        direction9(row,column);
        direction3(row,column);
        direction7(row,column);
    }
}
