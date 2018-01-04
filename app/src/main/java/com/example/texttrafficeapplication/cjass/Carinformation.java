package com.example.texttrafficeapplication.cjass;

public class Carinformation {
    public int ID;
    public String Name;
    public String Money;
    public String Time;
    public String Shusi;

    public Carinformation(String name, String money, String time, String shuzi) {
        this.Name = name;
        this.Money = money;
        this.Time = time;
        this.Shusi = shuzi;
    }

    public String showInfo() {
        String text = "";
        text += "ID:" + ID + "   ";
        text += "充值人:" + Name + "   ";
        text += "充值金额:" + Money + "   ";
        text += "充值时间:" + Time + "   ";
        text += "车牌号:" + Shusi + ";\n";
        return text;
    }
}
