package com.aizhong.singleIns;

/**
 * 饿汉式单例
 */
public class HungrySingleIns {
    private static HungrySingleIns hungrySingleIns = new HungrySingleIns();
    private HungrySingleIns(){};

    public static HungrySingleIns getInstance(){
        return hungrySingleIns;
    }
}
class HungrySingleIns02 {
    private static HungrySingleIns02 hungrySingleIns02 = new HungrySingleIns02();
    private HungrySingleIns02(){
        if(hungrySingleIns02 != null){
            throw new RuntimeException();
        }
    };
    public static HungrySingleIns02 getInstance(){
        return hungrySingleIns02;
    }
}
