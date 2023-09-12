package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PrepareCard {

    //׼����
    //���þ�̬����飬������ļ��ض����أ�����ִֻ��һ��
    //�ƿ�
    static ArrayList<String>list=new ArrayList<>();
    static HashMap<String,Integer> hm=new HashMap<>();

    static
    {
        String[] color = {"?","?","?","?"};
        String[] number = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        for (int i = 0; i < color.length; i++) {
            //i���˱�ʾÿһ�ֻ�ɫ
            for (int j = 0; j < number.length; j++) {
                //j��ʾÿһ������
                list.add(color[i]+number[j]);//ÿһ�����ɻ�ɫ��������ɣ�һ��4*13=52����

            }
        }
        list.add(" С��");
        list.add(" ����");
        //ָ���Ƶļ�ֵ
        //���ϵ����ֵ�Map�������ж��Ƿ����
        //���ڣ���ȡ��ֵ
        //�����ڣ���������־��Ǽ�ֵ
        hm.put("J",11);
        hm.put("Q",12);
        hm.put("K",13);
        hm.put("A",14);
        hm.put("2",15);
        hm.put("С��",16);
        hm.put("����",17);


    }
    public PrepareCard()
    {
        //ϴ��
        Collections.shuffle(list);
        //����
        //׼���ĸ�����
        ArrayList<String> lord=new ArrayList<>();
        ArrayList<String> player1=new ArrayList<>();
        ArrayList<String> player2=new ArrayList<>();
        ArrayList<String> player3=new ArrayList<>();

        //�����ƿ�õ�ÿһ����
        for (int i = 0; i < list.size(); i++) {
            //iΪ����
            String poker=list.get(i);
            if(i<=2)
            {
                lord.add(poker);
                continue;
            }
            //�����������������
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
        //����
        order(lord);
        order(player1);
        order(player2);
        order(player3);
        //����
        lookPoker("����",lord);
        lookPoker("���1",player1);
        lookPoker("���2",player2);
        lookPoker("���3",player3);

    }
    public void lookPoker(String name,ArrayList<String> list)
    {
        System.out.print(name+": ");
        for (String poker : list) {
            System.out.print(poker+" ");
        }
        System.out.println();
    }

    //�����Ƶļ�ֵ��������
    //����������
    public void order(ArrayList<String> list)
    {
        Collections.sort(list, new Comparator<String>() {
            //Array.sort(��������+���ֲ��ң�
            @Override
            public int compare(String o1, String o2) {
                //o1:��ʾ��ǰҪ���뵽�������е���
                //o2:��ʾ�Ѿ������������д��ڵ���

                //������o1С ���뵽����
                //������o1�� ���뵽����
                //0��o1�����ָ�o2��������һ������Ҫ����ɫ�ٴ�����

                //1.����o1�Ļ�ɫ�ͼ�ֵ
                String color1=o1.substring(0,1);
                int value1=getValue(o1);

                //2.����o2�Ļ�ɫ�ͼ�ֵ
                String color2=o1.substring(0,1);
                int value2=getValue(o2);

                //3.�Ƚ�o1��o2�ļ�ֵ
                int i=value1-value2;

                return i==0?color1.compareTo(color2):i;
            }
        });

    }
    //�����Ƶļ�ֵ
    //��������
    //����ֵ����ֵ
    public int getValue(String poker)
    {
        String number = poker.substring(1);
        //�������ֵ�map�������ж��Ƿ����
        //���ڣ���ȡ��ֵ
        //�����ڣ�����ת��
        if(hm.containsKey(number))
        {
            return hm.get(number);
        }
        else {
            return Integer.parseInt(number);
        }
    }

}
