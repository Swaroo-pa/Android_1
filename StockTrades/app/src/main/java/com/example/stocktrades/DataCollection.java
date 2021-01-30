package com.example.stocktrades;

/* exc_name is the Stock exchange name
* sym_name is the Symbol name
* change_per is the Change in percentage
* change_price is the the price difference from ltp and close
* ltp is the price of the stock
*/
public class DataCollection {
    String exc_name;
    String sym_name;
    String change_per;
    String change_price;
    String ltp;

    public DataCollection(){}

    public DataCollection(String exc_name, String sym_name, String change_price, String change_per, String ltp){
        this.exc_name = exc_name;
        this.sym_name = sym_name;
        this.change_price = change_price;
        this.change_per = change_per;
        this.ltp = ltp;
    }

    public String getExc_name() {
        return exc_name;
    }

    public void setExc_name(String exc_name) {
        this.exc_name = exc_name;
    }

    public String getSym_name() {
        return sym_name;
    }

    public void setSym_name(String sym_name) {
        this.sym_name = sym_name;
    }

    public String getChange_per() {
        return change_per;
    }

    public void setChange_per(String change_per) {
        this.change_per = change_per;
    }

    public String getChange_price() {
        return change_price;
    }

    public void setChange_price(String change_price) {
        this.change_price = change_price;
    }

    public String getLtp() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }
}
