public abstract class Jewel {
    private int jewelPoint;
    private String gridLetter;
    private static int gridScore;
    private boolean checkW = false;
    abstract void action(int row, int column); //every Jewel's action is different, so it is an abstract method

    /*direction methods adjust row and column numbers to find triplets in given directions,
    if given coordinate is instance of Wildcard or MathematicalSymbols,searching algorithm is different than other jewels,so they are distinguished.
    if given coordinate is instanceof Wildcard,boolean checkW is used to check that triplets don't consist of MathematicalSymbols since it's not acceptable
    after finding triplets(whenever match=2) direction methods call horizontal/vertical/diagonal mover functions to set triplets to null value.
    */

    public void direction1(int row, int column) {
        int match = 0;
        for(int i =-2;i<0;i++) {
            int new_row = row +i;
            int new_column = column +i;
            if (row-2 >= 0 && row-1 >=0 && column-2 >= 0 && column-1>=0) { //to get rid of IndexOutOfBounds Exception
                if(MakeGrid.grid.get(new_row).get(new_column)!=null && MakeGrid.grid.get(row).get(column)!=null){
                    if (MakeGrid.grid.get(row).get(column) instanceof Wildcard) {
                         checkW = true;
                         if(MakeGrid.grid.get(new_row).get(new_column) instanceof Wildcard) {
                             match=2; //if two of them in triples is W, sets match directly to 2
                         }
                         else if (MakeGrid.grid.get(new_row).get(new_column).getClass().equals(MakeGrid.grid.get(new_row+1).get(new_column+1).getClass())) {
                             match = 2;
                         }
                    }

                    else if (MakeGrid.grid.get(row).get(column) instanceof MathJewel) {
                         if (MakeGrid.grid.get(new_row).get(new_column) instanceof MathJewel) {
                             match++;
                         }
                    }

                    else {
                         if (MakeGrid.grid.get(new_row).get(new_column).getClass().equals(MakeGrid.grid.get(row).get(column).getClass())) {
                             match++;
                         }
                    }
                }
            }
        }
        if (match == 2) {
            diagonalMover(1, row, column);
            checkW=false;
        }
    }


    public void direction2(int row, int column) {
        int match = 0;
        for(int i =-2;i<0;i++) {
            int new_row = row +i;
            if (row-2 >= 0 && row-1>=0) {
                if(MakeGrid.grid.get(new_row).get(column)!=null && MakeGrid.grid.get(row).get(column)!=null){
                    if (MakeGrid.grid.get(row).get(column) instanceof Wildcard) {
                        checkW = true;
                        if(MakeGrid.grid.get(new_row).get(column) instanceof Wildcard) {
                            match=2;
                        }
                        else if (MakeGrid.grid.get(new_row).get(column).getClass().equals(MakeGrid.grid.get(new_row+1).get(column).getClass())) {
                            match = 2;
                        }
                    }

                    else if (MakeGrid.grid.get(row).get(column) instanceof MathJewel) {
                        if (MakeGrid.grid.get(new_row).get(column) instanceof MathJewel) {
                            match++;
                        }
                    }

                    else {
                        if (MakeGrid.grid.get(new_row).get(column).getClass().equals(MakeGrid.grid.get(row).get(column).getClass())) {
                            match++;
                        }
                    }
                }
            }
        }
        if (match == 2) {
            verticalMover(2, row, column);
            checkW=false;
        }
    }


    public void direction3(int row, int column) {
        int match = 0;
        for (int i=-2,j=2;i<0 && j>0;i++,j--) {
            int new_row = row +i;
            int new_column = column +j;
            if (row-2 >= 0 && row-1>=0 && column+2 < MakeGrid.grid.get(0).size() && column+1 < MakeGrid.grid.get(0).size()){
                if(MakeGrid.grid.get(new_row).get(new_column)!=null && MakeGrid.grid.get(row).get(column)!=null) {
                    if (MakeGrid.grid.get(row).get(column) instanceof Wildcard) {
                        checkW = true;
                        if(MakeGrid.grid.get(new_row).get(new_column) instanceof Wildcard) {
                            match=2;
                        }
                        else if (MakeGrid.grid.get(new_row).get(new_column).getClass().equals(MakeGrid.grid.get(new_row+1).get(new_column-1).getClass())) {
                            match = 2;
                        }
                    }

                    else if (MakeGrid.grid.get(row).get(column) instanceof MathJewel) {
                        if (MakeGrid.grid.get(new_row).get(new_column) instanceof MathJewel) {
                            match++;
                        }
                    }

                    else {
                        if (MakeGrid.grid.get(new_row).get(new_column).getClass().equals(MakeGrid.grid.get(row).get(column).getClass())) {
                            match++;
                        }
                    }
                }
            }
        }
        if (match == 2) {
            diagonalMover(3, row, column);
            checkW=false;
        }
    }


    public  void direction4(int row, int column) {
        int match = 0;
        for (int i = -2; i < 0; i++) {
            int new_column = column + i;
            if (column-2 >= 0 && column-1>=0) {
                if(MakeGrid.grid.get(row).get(new_column)!=null && MakeGrid.grid.get(row).get(column)!=null){
                    if (MakeGrid.grid.get(row).get(column) instanceof Wildcard) {
                        checkW = true;
                        if(MakeGrid.grid.get(row).get(new_column)instanceof Wildcard) {
                            match=2;
                        }
                        else if (MakeGrid.grid.get(row).get(new_column).getClass().equals(MakeGrid.grid.get(row).get(new_column+1).getClass())) {
                            match = 2;
                        }
                    }

                    else if (MakeGrid.grid.get(row).get(column) instanceof MathJewel) {
                        if (MakeGrid.grid.get(row).get(new_column) instanceof MathJewel) {
                            match++;
                        }
                    }

                    else {
                        if (MakeGrid.grid.get(row).get(new_column).getClass().equals(MakeGrid.grid.get(row).get(column).getClass())) {
                            match++;
                        }
                    }
                }
            }
        }
        if (match == 2) {
            horizontalMover(4, row, column);
            checkW =false;
        }
    }


    public void direction6(int row, int column) {
        int match = 0;
        for (int i = 2; i > 0; i--) {
            int new_column = column + i;
            if (column +2 < MakeGrid.grid.get(0).size() && column+1<MakeGrid.grid.get(0).size()){
                if(MakeGrid.grid.get(row).get(new_column)!=null && MakeGrid.grid.get(row).get(column)!=null) {
                    if (MakeGrid.grid.get(row).get(column) instanceof Wildcard) {
                        checkW = true;
                        if(MakeGrid.grid.get(row).get(new_column) instanceof Wildcard) {
                            match=2;
                        }
                        else if (MakeGrid.grid.get(row).get(new_column).getClass().equals(MakeGrid.grid.get(row).get(new_column-1).getClass())) {
                            match = 2;
                        }
                    }

                    else if (MakeGrid.grid.get(row).get(column) instanceof MathJewel) {
                        if (MakeGrid.grid.get(row).get(new_column) instanceof MathJewel) {
                            match++;
                        }
                    }

                    else {
                        if (MakeGrid.grid.get(row).get(new_column).getClass().equals(MakeGrid.grid.get(row).get(column).getClass())) {
                            match++;
                        }
                    }
                }
            }
        }
        if (match == 2) {
            horizontalMover(6, row, column);
            checkW =false;
        }
    }


    public void direction7(int row, int column) {
        int match = 0;
        for (int i=2,j=-2;i>0 && j<0;i--,j++) {
            int new_row = row +i;
            int new_column = column +j;
            if (row+2 < MakeGrid.grid.size() && row+1<MakeGrid.grid.size() && column-2 >= 0 && column-1 >=0){
                if(MakeGrid.grid.get(new_row).get(new_column)!=null && MakeGrid.grid.get(row).get(column)!=null) {
                    if (MakeGrid.grid.get(row).get(column) instanceof Wildcard) {
                        checkW = true;
                        if(MakeGrid.grid.get(new_row).get(new_column)instanceof Wildcard) {
                            match=2;
                        }
                        else if (MakeGrid.grid.get(new_row).get(new_column).getClass().equals(MakeGrid.grid.get(new_row-1).get(new_column+1).getClass())) {
                            match = 2;
                        }
                    }

                    else if (MakeGrid.grid.get(row).get(column) instanceof MathJewel) {
                        if (MakeGrid.grid.get(new_row).get(new_column) instanceof MathJewel) {
                            match++;
                        }
                    }

                    else {
                        if (MakeGrid.grid.get(new_row).get(new_column).getClass().equals(MakeGrid.grid.get(row).get(column).getClass())) {
                            match++;
                        }
                    }
                }
            }
        }
        if (match == 2) {
            diagonalMover(7, row, column);
            checkW =false;
        }
    }


    public void direction8(int row, int column) {
        int match = 0;
        for(int i =2;i>0;i--) {
            int new_row= row +i;
            if (row+2 < MakeGrid.grid.size() && row+1<MakeGrid.grid.size()){
                if(MakeGrid.grid.get(new_row).get(column)!=null && MakeGrid.grid.get(row).get(column)!=null) {
                    if (MakeGrid.grid.get(row).get(column) instanceof Wildcard) {
                        checkW = true;
                        if(MakeGrid.grid.get(new_row).get(column)instanceof Wildcard) {
                            match=2;
                        }
                        else if (MakeGrid.grid.get(new_row).get(column).getClass().equals(MakeGrid.grid.get(new_row-1).get(column).getClass())) {
                            match = 2;
                        }
                    }

                    else if (MakeGrid.grid.get(row).get(column) instanceof MathJewel) {
                        if (MakeGrid.grid.get(new_row).get(column) instanceof MathJewel) {
                            match++;
                        }
                    }

                    else {
                        if (MakeGrid.grid.get(new_row).get(column).getClass().equals(MakeGrid.grid.get(row).get(column).getClass())) {
                            match++;
                        }
                    }
                }
            }
        }
        if (match == 2) {
            verticalMover(8, row, column);
            checkW=false;
        }
    }


    public void direction9(int row, int column) {
        int match = 0;
        for(int i =2;i>0;i--) {
            int new_row = row +i;
            int new_column = column +i;
            if (row+2 < MakeGrid.grid.size() && row+1 < MakeGrid.grid.size() && column+2 < MakeGrid.grid.get(0).size() && column+1 < MakeGrid.grid.get(0).size()){
                 if(MakeGrid.grid.get(new_row).get(new_column)!=null && MakeGrid.grid.get(row).get(column)!=null){
                     if (MakeGrid.grid.get(row).get(column)instanceof Wildcard) {
                         checkW = true;
                         if(MakeGrid.grid.get(new_row).get(new_column) instanceof Wildcard) {
                             match=2;
                         }
                         else if (MakeGrid.grid.get(new_row).get(new_column).getClass().equals(MakeGrid.grid.get(new_row-1).get(new_column+-1).getClass())) {
                             match = 2;
                         }
                     }

                     else if (MakeGrid.grid.get(row).get(column) instanceof MathJewel) {
                         if (MakeGrid.grid.get(new_row).get(new_column) instanceof MathJewel) {
                             match++;
                         }
                     }

                     else {
                         if (MakeGrid.grid.get(new_row).get(new_column).getClass().equals(MakeGrid.grid.get(row).get(column).getClass())) {
                             match++;
                         }
                     }
                }
            }
        }
        if (match == 2) {
            diagonalMover(9, row, column);
            checkW =false;
        }
    }


    /* mover methods is used to set triplets to null.
    Whenever checkW is true(that means triplet is coming from chosen Wildcard) it checks values in triplet to eliminate the ones with MathematicalSymbols
     */
    public void verticalMover(int directiontype, int row, int column) {
        if (directiontype == 2) {
            if((!(MakeGrid.grid.get(row-2).get(column) instanceof MathJewel) && !(MakeGrid.grid.get(row-1).get(column) instanceof MathJewel))||(!checkW)) {
                for (int i = (row - 2); i <= row; i++) {
                    setGridScore((MakeGrid.grid.get(i).get(column).getJewelPoint()));
                    MakeGrid.grid.get(i).set(column, null);
                }
            }
        }

        else if (directiontype == 8) {
            if((!(MakeGrid.grid.get(row+2).get(column) instanceof MathJewel) && !(MakeGrid.grid.get(row+1).get(column) instanceof MathJewel))||(!checkW)) {
                for (int i = (row + 2); i >= row; i--) {
                    setGridScore((MakeGrid.grid.get(i).get(column).getJewelPoint()));
                    MakeGrid.grid.get(i).set(column, null);
                }
            }
        }
    }


    public void horizontalMover(int directiontype,int row, int column) {
        if (directiontype == 4) {
            if((!(MakeGrid.grid.get(row).get(column-2) instanceof MathJewel) && !(MakeGrid.grid.get(row).get(column-1) instanceof MathJewel)) || (!checkW)) {
                for (int i = (column - 2); i <= column; i++) {
                    setGridScore((MakeGrid.grid.get(row).get(i).getJewelPoint()));
                    MakeGrid.grid.get(row).set(i, null);
                }
            }
        }

        else if (directiontype == 6) {
            if((!(MakeGrid.grid.get(row).get(column+2) instanceof MathJewel) && !(MakeGrid.grid.get(row).get(column+1) instanceof MathJewel))||(!checkW)) {
                for (int i = (column + 2); i >= column; i--) {
                    setGridScore((MakeGrid.grid.get(row).get(i).getJewelPoint()));
                    MakeGrid.grid.get(row).set(i, null);
                }
            }
        }
    }


    public void diagonalMover(int directiontype,int row, int column) {
        if (directiontype == 1) {
            if((!(MakeGrid.grid.get(row-2).get(column-2) instanceof MathJewel) && !(MakeGrid.grid.get(row-1).get(column-1) instanceof MathJewel))||(!checkW)) {
                for (int i = row - 2, j = column - 2; i <= row && j <= column; i++, j++) {
                    setGridScore((MakeGrid.grid.get(i).get(j).getJewelPoint()));
                    MakeGrid.grid.get(i).set(j, null);
                }
            }
        }

        else if(directiontype ==3) {
            if((!(MakeGrid.grid.get(row-2).get(column+2) instanceof MathJewel) && !(MakeGrid.grid.get(row-1).get(column+1) instanceof MathJewel))||(!checkW)) {
                for (int i = row - 2, j = column + 2; i <= row && j >= column; i++, j--) {
                    setGridScore((MakeGrid.grid.get(i).get(j).getJewelPoint()));
                    MakeGrid.grid.get(i).set(j, null);
                }
            }
        }

        else if(directiontype ==7) {
            if((!(MakeGrid.grid.get(row+2).get(column-2) instanceof MathJewel) && !(MakeGrid.grid.get(row+1).get(column-1) instanceof MathJewel))||(!checkW)) {
                for (int i = row + 2, j = column - 2; i >= row && j <= column; i--, j++) {
                    setGridScore((MakeGrid.grid.get(i).get(j).getJewelPoint()));
                    MakeGrid.grid.get(i).set(j, null);
                }
            }
        }

        else if(directiontype ==9) {
            if((!(MakeGrid.grid.get(row+2).get(column+2) instanceof MathJewel) && !(MakeGrid.grid.get(row+1).get(column+1) instanceof MathJewel))||(!checkW)) {
                for (int i = row + 2, j = column + 2; i >= row && j >= column; i--, j--) {
                    setGridScore((MakeGrid.grid.get(i).get(j).getJewelPoint()));
                    MakeGrid.grid.get(i).set(j, null);
                }
            }
        }
    }


    public void setGridLetter(String gridLetter) {
        this.gridLetter = gridLetter;
    }

    public String getGridLetter() {
        return gridLetter;
    }

    public void setJewelPoint(int jewelPoint) {
        this.jewelPoint = jewelPoint;
    }

    public int getJewelPoint() {
        return jewelPoint;
    }

    public static void setGridScore(int gridScore) {
        Jewel.gridScore += gridScore;
    }

    public static int getGridScore() {
        return gridScore;
    }

}
