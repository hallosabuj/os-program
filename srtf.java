//Write a program to implement shortest-remaining-time-first(srtf) cpu scheduling algorithm.
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

class srtf
{
	public static void main(String[] ars) throws IOException{
		int time=0,status=0;
		BufferedReader ip=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number of process ");
		int n=Integer.parseInt(ip.readLine());
		process ob[] = new process[n];
		for(int i=0;i<n;i++){
			ob[i]=new process();
			ob[i].getdetails();
		}
		while(status==0){
			int flag=0;
			//Code for scheduling				
			for(int i=0;i<n;i++){
				if(ob[i].arrival<=time && ob[i].status==0){
					int p=i;
					int min1=ob[i].rburst;
					flag=1;
					for(int j=i+1;j<n;j++){
						if(ob[j].rburst<min1 && ob[j].status==0 && ob[j].arrival<=time){
							p=j;
							min1=ob[j].rburst;
						}
					}
					ob[p].rburst=ob[p].rburst-1;
					time=time+1;
					if(ob[p].rburst==0){
						ob[p].status=1;
						ob[p].ttime=time-ob[p].arrival;
						ob[p].wtime=ob[p].ttime-ob[p].burst;
					}
					break;
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
		for(int i=0;i<n;i++)
		{
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
Output
======
admin1@RKMA:~/Sem1/Programs/BG/Scheduling$ java srtf
Enter number of process 
5
Enter process id 
1
Enter arrival time for process 1
0
Enter burst time for process 1
8
Enter process id 
2
Enter arrival time for process 2
1
Enter burst time for process 2
1
Enter process id 
3
Enter arrival time for process 3
2
Enter burst time for process 3
3
Enter process id 
4
Enter arrival time for process 4
3
Enter burst time for process 4
2
Enter process id 
5
Enter arrival time for process 5
4
Enter burst time for process 5
6

Results of scheduling
=====================
pid	Arrival	Burst	Waiting	Turnaround
1	0	8	12	20
2	1	1	0	1
3	2	3	0	3
4	3	2	2	4
5	4	6	3	9
Average turn around time 7.4
Average wating time 3.4
*/
