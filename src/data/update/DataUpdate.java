package data.update;

import data.python.PythonEnviroment.PythonInterpreterFactory;
import org.python.util.PythonInterpreter;

/**
 * Created by chenghao on 15/6/14.
 */
public class DataUpdate {
    public static boolean flag = false;

    public static void update(){
    	System.out.println("123---------------");
        if (flag == false){
            System.out.println("----------");
            new Thread(){
                public void run(){
                    PythonInterpreterFactory.getPythonInterpreter().execfile("python/refresh_live.py");
                }
            }.start();
            new Thread(){
                public void run(){
                    PythonInterpreterFactory.getPythonInterpreter().execfile("python/refresh_next_game.py");
                }
            }.start();
        }
        flag = true;
    }

    public static void main(String[] args){
        update();
    }
}
