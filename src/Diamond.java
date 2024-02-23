public class Diamond extends Jewel{

    Diamond() {
        setJewelPoint(30);
        setGridLetter("D");
    }

    @Override
    void action(int row, int column) {
        direction1(row,column);
        direction9(row,column);
        direction3(row,column);
        direction7(row,column);
    }
}
