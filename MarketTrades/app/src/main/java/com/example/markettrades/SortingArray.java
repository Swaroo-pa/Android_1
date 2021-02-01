package com.example.markettrades;

import java.util.ArrayList;

public class SortingArray {

    ArrayList<DataCollection> array;

    public  SortingArray(ArrayList<DataCollection> array){
        this.array = array;
    };

    // sorting array in increasing order of change_price of the product
    public ArrayList<DataCollection> sorting_array_by_change(){

        int n = array.size();

        for(int i=0; i<n-1; i++){

            for(int j=0; j<n-i-1; j++){

                Double item_1 = Double.parseDouble(array.get(j).getChange_price());
                Double item_2 = Double.parseDouble(array.get(j+1).getChange_price());

                if(item_1 > item_2){

                       DataCollection temp = new DataCollection();
                       temp = array.get(j);
                       array.set(j,array.get(j+1));
                       array.set(j+1,temp);
                }
            }
        }
   //  System.out.print(array);
    return array;
    }

    // sorting array in increasing order of ltp of the product
    public ArrayList<DataCollection> sorting_array_by_price(){
        int n = array.size();

        for(int i=0; i<n-1; i++){

            for(int j=0; j<n-i-1; j++){

                Double item_1 = Double.parseDouble(array.get(j).getLtp());
                Double item_2 = Double.parseDouble(array.get(j+1).getLtp());

                if(item_1 > item_2){

                    DataCollection temp = array.get(j);
                    array.set(j,array.get(j+1));
                    array.set(j+1,temp);
                }
            }
        }
        return array;
    }
}
