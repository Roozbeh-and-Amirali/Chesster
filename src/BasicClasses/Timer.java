package BasicClasses;

import java.io.Serializable;

public class Timer implements Serializable {
   private Long initTime;
    public Timer(){
    }

    public void start(){
        initTime=System.nanoTime();
    }

    public Time getTime(){
        Long nowsTimeInMilis=System.nanoTime();
        int sec=(int) ((nowsTimeInMilis-this.initTime)/1e9);


        Time nowsTime=new Time(sec);
        return nowsTime;
    }


}
