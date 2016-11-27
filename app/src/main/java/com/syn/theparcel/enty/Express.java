package com.syn.theparcel.enty;
/**
 * Created by 孙亚楠 on 2016/11/21.
 */

public class Express {
    private String name,logo;    //快递公司名称
    private int id;
    private String number;

    public Express() {

    }

    public Express(String string_edittext_id, String string_editext_name) {
        this.name=string_editext_name;
        this.number=string_edittext_id;
    }

    public int getId(){
        return  id;
    }
    public String getExpressNumber(){
        return  number;
    }
    public Express(int id, String name, String number){
        this.id=id;
        this.number=number;
        this.name=name;
    }

    public String getExpressName() {
        return name;
    }

    public void setExpressName(String simpleName) {
        this.name = simpleName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


}
