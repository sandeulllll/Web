package coursework1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SJF {

    static int Time_plus(int x, int y){
        int newMinutes = (x % 100 + y) % 60;
        int newHours = x / 100 + (x % 100 + y) / 60;
        return newHours * 100 + newMinutes;
    }


    static void SJF(List<Job> jobs){
        int currentTime = 0;
        List<Job> jobs1 = new ArrayList<>();

        while (!jobs.isEmpty()){
            Job shortestJob = null;
            int shortestBurstTime = Integer.MAX_VALUE;

            for(Job job :jobs){
                if (job.arrivalTime <= currentTime && job.burstTime < shortestBurstTime){
                    shortestJob = job;
                    shortestBurstTime = job.burstTime;
                }
            }

            if (shortestJob != null){
                jobs.remove(shortestJob); // 从原列表中移除最短作业
                shortestJob.startTime = currentTime;
                shortestJob.finishTime = Time_plus(currentTime,shortestJob.burstTime);
                currentTime = shortestJob.finishTime;
                jobs1.add(shortestJob); // 将最短作业添加到新列表中
            }else {
                currentTime++;
            }
        }
        for (Job job : jobs1){
            System.out.println(job.id + "\t" + job.startTime + "\t" + job.finishTime);
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
        SJF(jobs);
    }
}
