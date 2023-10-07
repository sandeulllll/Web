package coursework1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HRRN {
    static int Time_plus(int x, int y){
        int newMinutes = (x % 100 + y) % 60;
        int newHours = x / 100 + (x % 100 + y) / 60;
        return newHours * 100 + newMinutes;
    }

    static void HRRN(List<Job> jobs){
        int currentTime = 0;
        List<Job> jobs2 = new ArrayList<>(); // 新建一个用于存放已选择的作业的列表

        while (!jobs.isEmpty()){
            double maxResponseRatio = -1;
            Job selectedJob = null;

            for (Job job : jobs){
                if (job.arrivalTime <= currentTime){
                    double responseRatio = (currentTime - job.arrivalTime + job.burstTime) / (double)job.burstTime;
                    if (responseRatio > maxResponseRatio){
                        maxResponseRatio = responseRatio;
                        selectedJob = job;
                    }
                }
            }

            if (selectedJob != null){
                jobs.remove(selectedJob); // 从原列表中移除最高响应比的作业
                selectedJob.startTime = currentTime;
                selectedJob.finishTime = Time_plus(currentTime, selectedJob.burstTime);
                currentTime = selectedJob.finishTime;
                jobs2.add(selectedJob); // 将最高响应比的作业添加到新列表中
            } else {
                currentTime++;
            }
        }

        // 输出jobs2列表中的作业信息
        for (Job job : jobs2){
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

        HRRN(jobs);
    }
}
