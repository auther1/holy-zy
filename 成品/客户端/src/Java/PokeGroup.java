package Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class PokeGroup implements Comparable<PokeGroup>{
    public ArrayList<String> pokegroup =new ArrayList<String>();
    public String type;
    public int value;
    public String basicType;
    //type=1,   3,4,5...13,14,15,16,17
    //type=2,   3,4,5...13,14,15
    //type=3,   3,4,5...13,14,15
    //type=火箭   40
    //type=炸弹   23,24,25...33,34,35
    //type=3+1  3,4,5...13,14,15
    //type=3+2  3,4,5...13,14,15
    //type=4+1  3,4,5...13,14,15
    //type=4+2  3,4,5...13,14,15(两张牌可以不一样)
    //type=顺子5*1    7,8,9,10,11,12,13,14
    //type=顺子6*1    8,9,10,11,12,13,14
    //type=顺子7*1    9,10,11,12,13,14
    //type=顺子8*1    10,11,12,13,14
    //type=顺子9*1    11,12,13,14
    //type=顺子10*1   12,13,14
    //type=顺子11*1   13,14
    //type=顺子12*1   14
    //type=连对3*2    5,6,7,8,9,10,11,12,13,14
    //type=连对4*2    6,7,8,9,10,11,12,13,14
    //type=连对5*2    7,8,9,10,11,12,13,14
    //type=连对6*2    8,9,10,11,12,13,14
    //type=连对7*2    9,10,11,12,13,14
    //type=连对8*2    10,11,12,13,14
    //type=连对9*2    11,12,13,14
    //type=连对10*2   12,13,14
    //type=飞机2*3    4,5,6,7,8,9,10,11,12,13,14
    //type=飞机3*3    5,6,7,8,9,10,11,12,13,14
    //type=飞机3*3    6,7,8,9,10,11,12,13,14
    //type=飞机4*3    7,8,9,10,11,12,13,14
    //type=飞机5*3    8,9,10,11,12,13,14
    //type=飞机6*3    9,10,11,12,13,14
    //type=飞机2*3+1    4,5,6,7,8,9,10,11,12,13,14
    //type=飞机3*3+1    5,6,7,8,9,10,11,12,13,14
    //type=飞机3*3+1    6,7,8,9,10,11,12,13,14
    //type=飞机4*3+1    7,8,9,10,11,12,13,14
    //type=飞机5*3+1    8,9,10,11,12,13,14
    //type=飞机2*3+2    4,5,6,7,8,9,10,11,12,13,14
    //type=飞机3*3+2    5,6,7,8,9,10,11,12,13,14
    //type=飞机3*3+2    6,7,8,9,10,11,12,13,14
    //type=飞机4*3+2    7,8,9,10,11,12,13,14


    public PokeGroup(){
        type="不出";
        value=0;
    }
    public String getpokegroup(){
        return basicType+"#"+String.join(" ", pokegroup);
    }

    public int getCountNumber(){
        return pokegroup.size();
    }

    //获取单张牌面价值（字符串）
    protected static int getValue(String str){
        char s=str.charAt(1);
        if(s>='3'&&s<='9'){
            return Integer.parseInt(String.valueOf(s));
        }
        else if(str.equals("_大王")){
            return 17;
        }
        else if(str.equals("_小王")){
            return 16;
        }
        else{
            switch(s){
                case 'J':return 11;
                case 'Q':return 12;
                case 'K':return 13;
                case 'A':return 14;
                case '2':return 15;
                case '1':return 10;
                default:return 0;
            }
        }

    }
    //获取但张牌面价值（下标）
    private int getValue(int i){
        return getValue(pokegroup.get(i));
    }

    //添加单张牌
    public void add(String poke){
        pokegroup.add(poke);
    }

    //添加多张牌
    public void addPro(String pokes){
        if(pokes.equals(" ")){
        }
        else{pokegroup.addAll(Arrays.asList(pokes.split(" ")));}
    }

    //删除牌
    public void remove(String poke){
        pokegroup.removeIf(pokeDelete->pokeDelete.equals(poke));
    }

    //清空牌
    public void removeAll(){
        pokegroup.clear();value=0;type="不出";
    }

    //获取类型
    public String getType(){
        return type;
    }

    //基本大小排序
    private void pokeSortBasic(){
        // 按照 getValue() 返回的值排序
        Collections.sort(this.pokegroup, new ValueComparator());
    }

    //计数
    protected int getCount(String s){
        int count = 0;
        for (String str : pokegroup) {
            if (getValue(str) == getValue(s)) {
                count++;
            }
        }
        return count;
    }

    //格式排序
    private void pokeSortFinal(){
        // 按照 getCount() 返回的值排序
        Collections.sort(this.pokegroup, new CountComparator(this));
    }

    //最终排序
    public void pokeSort(){
        pokeSortBasic();
        pokeSortFinal();
    }

    //顺子
    private boolean isShunzi(int size){
        if(getValue(0)>14){
            return false;
        }
        for(int i=0;i<size-1;i++){
            if(getValue(i)!=getValue(i+1)+1) {
                return false;
            }
        }
        return true;
    }

    //连对
    private boolean isLiandui(int s){
        if(getValue(0)>14){
            return false;
        }
        for(int i=0;i<s;i++){
            if(getValue(i*2)!=getValue(i*2+1)){
                return false;
            }
        }
        for(int i=0;i<s-1;i++){
            if(getValue(i*2)!=getValue(i*2+2)+1){
                return false;
            }
        }
        return true;
    }

    //飞机*3+0
    private boolean isFeiji0(int s){
        if(getValue(0)>14){
            return false;
        }
        for(int i=0;i<s;i++){
            if(!(getValue(i*3)==getValue(i*3+1)&&getValue(i*3)==getValue(i*3+2))){
                return false;
            }
        }
        for(int i=0;i<s-1;i++){
            if(getValue(i*3)!=getValue(i*3+3)+1){
                return false;
            }
        }
        return true;
    }

    //飞机*3+1
    private boolean isFeiji1(int s){
        if(getValue(0)>14){
            return false;
        }
        for(int i=0;i<s;i++){
            if(!(getValue(i*3)==getValue(i*3+1)&&getValue(i*3)==getValue(i*3+2))){
                return false;
            }
        }
        for(int i=0;i<s-1;i++){
            if(getValue(i*3)!=getValue(i*3+3)+1){
                return false;
            }
        }
        for(int i=0;i<s-1;i++){
            if(getValue(i+3*s)==getValue(i+3*s+1)){
                return false;
            }
        }
        return true;
    }

    //飞机*3+2
    private boolean isFeiji2(int s){
        if(getValue(0)>14){
            return false;
        }
        for(int i=0;i<s;i++){
            if(!(getValue(i*3)==getValue(i*3+1)&&getValue(i*3)==getValue(i*3+2))){
                return false;
            }
        }
        for(int i=0;i<s-1;i++){
            if(getValue(i*3)!=getValue(i*3+3)+1){
                return false;
            }
        }

        for(int i=0;i<s;i++){
            if(getValue(i*2+3*s)!=getValue(i*2+3*s+1)){
                return false;
            }
        }
        for(int i=0;i<s-1;i++){
            if(getValue(i*2+3*s)==getValue(i*2+3*s+2)){
                return false;
            }
        }
        return true;
    }

    //是否合法
    public boolean isLegal(){
        pokeSort();
        int size=pokegroup.size();
        switch(size){
            case 0:{
                type="不出";
                value=0;
                basicType="不出";
                return true;

            }
            case 1:{
                type="1";
                value=getValue(0);
                basicType="1";

                return true;
            }

            case 2:{
                if(getValue(0)<=15&&getValue(0)==getValue(1)){
                    type="2";
                    value=getValue(0);
                    basicType="2";
                    return true;
                }
                else if(pokegroup.get(0).equals("_大王")&&pokegroup.get(1).equals("_小王")){
                    type="火箭";
                    basicType="火箭";
                    value=40;
                    return true;
                }
                else{
                    return false;
                }
            }

            case 3:{
                if((getValue(0)<=15)&&(getValue(0)==getValue(1))&&(getValue(0)==getValue(2))){
                    type="3";
                    basicType="3";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 4:{
                if((getValue(0)<=15)&&(getValue(0)==getValue(1))&&(getValue(0)==getValue(2))&&(getValue(0)==getValue(3))){
                    type="炸弹";
                    basicType="炸弹";
                    value=20+getValue(0);
                    return true;
                }
                else if((getValue(0)<=15)&&(getValue(0)==getValue(1))&&(getValue(0)==getValue(2))){
                    type="3+1";
                    basicType="3+1";
                    value=getValue(0);
                    return true;
                }
                else {
                    return false;
                }
            }

            case 5:{
                if((getValue(0)<=15)&&(getValue(0)==getValue(1))&&(getValue(0)==getValue(2))&&(getValue(3)==getValue(4))){
                    type="3+2";
                    basicType="3+2";
                    value=getValue(0);
                    return true;
                }
                else if((getValue(0)<=15)&&(getValue(0)==getValue(1))&&(getValue(0)==getValue(2))&&(getValue(0)==getValue(3))){
                    type="4+1";
                    basicType="4+1";
                    value=getValue(0);
                    return true;
                }
                else if(isShunzi(5)){
                    type="顺子5*1";
                    basicType="顺子";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 6:{
                if((getValue(0)<=15)&&(getValue(0)==getValue(1))&&(getValue(0)==getValue(2))&&(getValue(0)==getValue(3))){
                    type="4+2";
                    basicType="4+2";
                    value=getValue(0);
                    return true;
                }
                else if(isShunzi(6)){
                    type="顺子6*1";
                    basicType="顺子";
                    value=getValue(0);
                    return true;
                }
                else if(isLiandui(size/2)){
                    type="连对3*2";
                    basicType="连对";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji0(size/3)){
                    type="飞机2*3+0";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 7:{
                if(isShunzi(7)){
                    type="顺子7*1";
                    basicType="顺子";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 8:{
                if(isShunzi(8)){
                    type="顺子8*1";
                    basicType="顺子";
                    value=getValue(0);
                    return true;
                }
                else if(isLiandui(4)){
                    type="连对4*2";
                    basicType="连对";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji1(2)){
                    type="飞机2*3+1";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 9:{
                if(isShunzi(9)){
                    type="顺子9*1";
                    basicType="顺子";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji0(3)){
                    type="飞机3*3+0";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 10:{
                if(isShunzi(10)){
                    type="顺子10*1";
                    basicType="顺子";
                    value=getValue(0);
                    return true;
                }
                else if (isLiandui(5)) {
                    type="连对5*2";
                    basicType="连对";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji2(2)){
                    type="飞机2*3+2";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }

            }

            case 11:{
                if(isShunzi(11)){
                    type="顺子11*1";
                    basicType="顺子";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 12:{
                if(isShunzi(12)){
                    type="顺子12*1";
                    basicType="顺子";
                    value=getValue(0);
                    return true;
                }
                else if(isLiandui(6)){
                    type="连对6*2";
                    basicType="连对";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji0(3)){
                    type="飞机4*3+0";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji1(3)){
                    type="飞机3*3+1";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 13:{
                return false;
            }

            case 14:{
                if(isLiandui(7)){
                    type="连对7*2";
                    basicType="连对";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 15:{
                if(isFeiji0(5)){
                    type="飞机5*3+0";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji2(3)){
                    type="飞机3*3+2";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 16:{
                if(isLiandui(8)){
                    type="连对8*2";
                    basicType="连对";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji1(4)){
                    type="飞机4*3+1";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 17:{
                return false;
            }

            case 18:{
                if(isLiandui(9)){
                    type="连对9*2";
                    basicType="连对";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji0(6)){
                    type="飞机6*3+0";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            case 19:{
                    return false;
            }

            case 20:{
                if(isLiandui(10)){
                    type="连对10*2";
                    basicType="连对";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji1(5)){
                    type="飞机5*3+1";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else if(isFeiji2(4)){
                    type="飞机4*3+2";
                    basicType="飞机";
                    value=getValue(0);
                    return true;
                }
                else{
                    return false;
                }
            }

            default: {
                type="不合法";
                return false;
            }
        }






    }

    public int compareTo(PokeGroup p){

        p.isLegal();
        if(this.isLegal()){
            if(this.type.equals(p.type)){
                if(this.value>p.value){
                    return 1;
                }
                else return -1;
            }
            else if(this.type.equals("火箭")||this.type.equals("炸弹")){
                if(this.value>p.value){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else if(p.type.equals("不出")){
                return -1;
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
    }

    public void show(){
        // 打印 ArrayList
        for (String str : pokegroup) {
            System.out.print(str+" ");
        }
        if(isLegal()){
        System.out.println(type);}
        else{
            System.out.println("非法");
        }

    }
}
class ValueComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        int value1 = PokeGroup.getValue(str1);
        int value2 = PokeGroup.getValue(str2);
        return Integer.compare(value2, value1);
    }
}
class CountComparator implements Comparator<String> {
    private PokeGroup pokegroup;
    public CountComparator(PokeGroup pokegroup){
        this.pokegroup=pokegroup;
    }
    @Override
    public int compare(String str1, String str2) {
        int count1 =pokegroup.getCount(str1);
        int count2 =pokegroup.getCount(str2);
        return Integer.compare(count2, count1);
    }
}
