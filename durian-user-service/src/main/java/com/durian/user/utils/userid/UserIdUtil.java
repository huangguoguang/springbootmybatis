package com.durian.user.utils.userid;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2017/7/6.
 */
public class UserIdUtil {
    private static int YEAR_PARAM = 4320;

    public static void main(String[] args) {
        //生成的用户id格式为:（yyyy+4320）+两位随机数+MMddHH+sSSS+3位随机数

        long[] arr = new long[100000];// 创建2个数组
        long[] arr2= new long[100000];
        for (int i = 0; i <100000; i++) {
            long a = Long.valueOf(getRandomId());// 生成随机数a
            Arrays.sort(arr2);//排序后才能用binarySearch()方法
            long result = Arrays.binarySearch(arr2, a);// 查看数组arr2中是否有a
            if (result < 0) {// 没有a就把a加入数组
                arr[i] = a;
                arr2[i] = a;
            } else {
                // 有a就把i加1，使i值不变
                System.out.println("第"+i+"次重复，"+"重复的数字为:" + a);
                i++;

            }
        }
        System.out.println("运行结束！");
    }

    public static String getRandomId() {
        StringBuffer sb = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = sdf.format(new Date());
        int year = Integer.parseInt(time.substring(0,4))+YEAR_PARAM;
        Random random = new Random();
        int num1 = random.nextInt(88)+11;
        int num2 = random.nextInt(898)+101;
        sb.append(year+""+num1+time.substring(4,9)+time.substring(13)+num2);
        return sb.toString();

    }
}
