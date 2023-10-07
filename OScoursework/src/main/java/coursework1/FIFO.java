package coursework1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*class Job{
    int id;
    int arrivalTime;
    int burstTime;
    int startTime;
    int finishTime;
}*/

public class FIFO {

    /*static int Time_plus(int x,int y){
        if(y < 60){
            x += y;
        }else {
            x = (x/100 + y/60)*100 + y%60;
        }
        return x;
    }*/
    static int Time_plus(int x, int y){
        int newMinutes = (x % 100 + y) % 60;
        int newHours = x / 100 + (x % 100 + y) / 60;
        return newHours * 100 + newMinutes;
    }


    static void FIFO(List<Job> jobs){
        int currentTime = 0;
        for (Job job : jobs){
            if (currentTime < job.arrivalTime){
                currentTime = job.arrivalTime;
            }
            job.startTime = currentTime;
            job.finishTime = Time_plus(currentTime,job.burstTime);
            currentTime = job.finishTime;
        }
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>(
                Arrays.asList(
                        new Job(){{
                            id = 1;
                            arrivalTime = 1000;
                            burstTime = 60;
                        }},
                        new Job(){{
                            id = 2;
                            arrivalTime = 1010;
                            burstTime = 60;
                        }},
                        new Job(){{
                            id = 3;
                            arrivalTime = 1025;
                            burstTime = 15;
                        }}
                )
        );
        FIFO(jobs);
        for (Job job : jobs){
            System.out.println(job.id + "\t" + job.startTime + "\t" + job.finishTime);
        }
    }

}
