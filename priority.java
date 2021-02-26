//Write a program to implement priority cpu scheduling algorithm.
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
		System.out.println("Enter priority for process "+pid);
		priority = Integer.parseInt(pr.readLine());
		rburst=burst;
		wtime=0;
		ttime=0;
		status=0;
	}
	void display(){
		System.out.println(pid+"	"+arrival+"	"+burst+"	"+priority+"		"+wtime+"	"+ttime);
	}
}

class priority
{
	public static void main(String[] ars) throws IOException{
		int time=0,status=0;
		BufferedReader ip=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number of process : ");
		int n=Integer.parseInt(ip.readLine());
		process ob[] = new process[n];
		for(int i=0;i<n;i++){
			ob[i]=new process();
			ob[i].getdetails();
		}
		while(status==0){
			//Code for scheduling
			int flag=0;
			for(int i=0;i<n;i++){
				if(ob[i].arrival<=time && ob[i].status==0){
					int min=ob[i].priority;
					int p=i;
					for(int j=i+1;j<n;j++){
						if(ob[j].priority<min && ob[j].status==0 && ob[j].arrival<=time){
							min=ob[j].priority;
							p=j;
						}
					}
					time=time+ob[p].rburst;
					ob[p].rburst=0;
					ob[p].ttime=time-ob[p].arrival;
					ob[p].wtime=ob[p].ttime-ob[p].burst;
					ob[p].status=1;
					flag=1;
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
Output
======
admin1@RKMA:~/Sem1/Programs/BG/Scheduling$ java priority
Enter number of process : 
5
Enter process id 
1
Enter arrival time for process 1
0
Enter burst time for process 1
9
Enter priority for process 1
5
Enter process id 
2
Enter arrival time for process 2
1
Enter burst time for process 2
4
Enter priority for process 2
3
Enter process id 
3
Enter arrival time for process 3
2
Enter burst time for process 3
5
Enter priority for process 3
1
Enter process id 
4
Enter arrival time for process 4
3
Enter burst time for process 4
7
Enter priority for process 4
2
Enter process id 
5
Enter arrival time for process 5
4
Enter burst time for process 5
3
Enter priority for process 5
4

Results of scheduling
=====================
pid	Arrival	Burst	Waiting	Turnaround
1	0	9	5		0	9
2	1	4	3		20	24
3	2	5	1		7	12
4	3	7	2		11	18
5	4	3	4		21	24
Average turn around time 17.4
Average wating time 11.8
*/
