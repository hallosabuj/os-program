//Write a program to implement round-robin cpu scheduling algorithm.
import java.io.*;

class process
{
	int pid;
	int arrival;
	int burst;
	int rburst;
	int status;
	int priority;
	int wtime;
	int ttime;
	int tq;
	BufferedReader pr=new BufferedReader(new InputStreamReader(System.in));
	
	void getdetails() throws IOException{
		System.out.println("Enter process id ");
		pid = Integer.parseInt(pr.readLine());
		System.out.println("Enter arrival time for process "+pid);
		arrival = Integer.parseInt(pr.readLine());
		System.out.println("Enter burst time for process "+pid);
		burst = Integer.parseInt(pr.readLine());
		rburst=burst;
		wtime=0;
		ttime=0;
		status=0;
	}
	void display(){
		System.out.println(pid+"	"+arrival+"	"+burst+"	"+wtime+"	"+ttime);
	}
}

class rr
{
	public static void main(String[] ars) throws IOException{
		int time=0,status=0;
		BufferedReader ip=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number of process : ");
		int n=Integer.parseInt(ip.readLine());
		System.out.println("Enter time quantam :");
		int tq=Integer.parseInt(ip.readLine());
		process ob[] = new process[n];
		for(int i=0;i<n;i++){
			ob[i]=new process();
			ob[i].getdetails();
		}
		while(status==0){
			//Code for scheduling
			int flag=0;
			for(int i=0;i<n;i++){
				if(ob[i].status==0 && ob[i].arrival<=time){
					flag=1;
					if(ob[i].rburst<=tq){//Process is completed
						time=time+ob[i].rburst;
						ob[i].rburst=0;
						ob[i].ttime=time-ob[i].arrival;
						ob[i].wtime=ob[i].ttime-ob[i].burst;
						ob[i].status=1;
					}
					else{
						time=time+tq;
						ob[i].rburst=ob[i].rburst-tq;
					}
				}
			}
			if(flag==0)
				time=time+1;

			status=1;
			for(int i=0;i<n;i++){
				if(ob[i].status==0){
					status=0;
				}
			}
		}
		//Displaying details
		System.out.println("Results of scheduling");
		System.out.println("=====================");
		System.out.println("pid	Arrival	Burst	Waiting	Turnaround");
		for(int i=0;i<n;i++){
			ob[i].display();
		}
		float avg_ttime=0,avg_wtime=0;
		for(int i=0;i<n;i++){
			avg_ttime+=ob[i].ttime;
			avg_wtime+=ob[i].wtime;
		}
		avg_ttime=avg_ttime/n;
		avg_wtime=avg_wtime/n;
		System.out.println("Average turn around time "+avg_ttime);
		System.out.println("Average wating time "+avg_wtime);
	}
}

/*
admin1@RKMA:~/Sem1/Programs/BG/Scheduling$ java rr
Enter number of process : 
4
Enter time quantam :
5
Enter process id 
1
Enter arrival time for process 1
2
Enter burst time for process 1
21
Enter process id 
2
Enter arrival time for process 2
0
Enter burst time for process 2
3
Enter process id 
3
Enter arrival time for process 3
7
Enter burst time for process 3
6
Enter process id 
4
Enter arrival time for process 4
1
Enter burst time for process 4
2

Results of scheduling
=====================
pid	Arrival	Burst	Waiting	Turnaround
1	2	21	9	30
2	0	3	0	3
3	7	6	8	14
4	1	2	2	4
Average turn around time 12.75
Average wating time 4.75
*/
