package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PrepareCard {

    //准备牌
    //运用静态代码块，随着类的加载而加载，而且只执行一次
    //牌库
    static ArrayList<String>list=new ArrayList<>();
    static HashMap<String,Integer> hm=new HashMap<>();

    static
    {
        String[] color = {"?","?","?","?"};
        String[] number = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        for (int i = 0; i < color.length; i++) {
            //i依此表示每一种花色
            for (int j = 0; j < number.length; j++) {
                //j表示每一个数字
                list.add(color[i]+number[j]);//每一张牌由花色和数字组成，一共4*13=52张牌

            }
        }
        list.add(" 小王");
        list.add(" 大王");
        //指定牌的价值
        //牌上的数字到Map集合中判断是否存在
        //存在，获取价值
        //不存在，本身的数字就是价值
        hm.put("J",11);
        hm.put("Q",12);
        hm.put("K",13);
        hm.put("A",14);
        hm.put("2",15);
        hm.put("小王",16);
        hm.put("大王",17);


    }
    public PrepareCard()
    {
        //洗牌
        Collections.shuffle(list);
        //发牌
        //准备四个集合
        ArrayList<String> lord=new ArrayList<>();
        ArrayList<String> player1=new ArrayList<>();
        ArrayList<String> player2=new ArrayList<>();
        ArrayList<String> player3=new ArrayList<>();

        //遍历牌库得到每一张牌
        for (int i = 0; i < list.size(); i++) {
            //i为索引
            String poker=list.get(i);
            if(i<=2)
            {
                lord.add(poker);
                continue;
            }
            //给三个玩家轮流发牌
            if(i%3==0)
            {
                player1.add(poker);
            }
            else if (i%3==1)
            {
                player2.add(poker);
            }
            else
            {
                player3.add(poker);
            }
        }
        //排序
        order(lord);
        order(player1);
        order(player2);
        order(player3);
        //看牌
        lookPoker("底牌",lord);
        lookPoker("玩家1",player1);
        lookPoker("玩家2",player2);
        lookPoker("玩家3",player3);

    }
    public void lookPoker(String name,ArrayList<String> list)
    {
        System.out.print(name+": ");
        for (String poker : list) {
            System.out.print(poker+" ");
        }
        System.out.println();
    }

    //利用牌的价值进行排序
    //参数：集合
    public void order(ArrayList<String> list)
    {
        Collections.sort(list, new Comparator<String>() {
            //Array.sort(插入排序+二分查找）
            @Override
            public int compare(String o1, String o2) {
                //o1:表示当前要插入到有序序列的牌
                //o2:表示已经在有序序列中存在的牌

                //负数：o1小 插入到后面
                //正数：o1大 插入到后面
                //0：o1的数字跟o2的数字是一样的需要按花色再次排序

                //1.计算o1的花色和价值
                String color1=o1.substring(0,1);
                int value1=getValue(o1);

                //2.计算o2的花色和价值
                String color2=o1.substring(0,1);
                int value2=getValue(o2);

                //3.比较o1和o2的价值
                int i=value1-value2;

                return i==0?color1.compareTo(color2):i;
            }
        });

    }
    //计算牌的价值
    //参数：牌
    //返回值：价值
    public int getValue(String poker)
    {
        String number = poker.substring(1);
        //拿着数字到map集合中判断是否存在
        //存在，获取价值
        //不存在，类型转换
        if(hm.containsKey(number))
        {
            return hm.get(number);
        }
        else {
            return Integer.parseInt(number);
        }
    }

}
