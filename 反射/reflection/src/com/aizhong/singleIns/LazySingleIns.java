package com.aizhong.singleIns;

/**
 * 懒汉式单例，暂不考虑线程安全问题
 */
public class LazySingleIns {

    private static LazySingleIns lazySingleIns;
    private LazySingleIns(){}
    public static LazySingleIns getInstance(){
        if(lazySingleIns == null){
            lazySingleIns = new LazySingleIns();
        }
        return lazySingleIns;
    }
}
