//Write a program to implement first-cum-first-serve(fcfs) cpu scheduling algorithm.
import java.io.*;

class Process
{
	int pid;
	int arrival;
	int burst;
	int rburst;
	int flag;
	int ttime;
	int wtime;
	BufferedReader pr=new BufferedReader(new InputStreamReader(System.in));
	void input() throws IOException{
		System.out.println("Enter process number : ");
		pid=Integer.parseInt(pr.readLine());
		System.out.println("Enter arrival time for "+pid);
		arrival=Integer.parseInt(pr.readLine());
		System.out.println("Enter burst time for "+pid);
		burst=Integer.parseInt(pr.readLine());
		flag=0;
		rburst=burst;
		ttime=0;
		wtime=0;
	}
	void output(){
		System.out.println(pid + "	" + arrival + "	" + rburst + "	" + wtime + "	" + ttime);

	}
}

class fcfs
{
	public static void main(String ars[]) throws IOException{
		System.out.println("Enter number of process : ");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Process obj[] = new Process[n];
		for(int i=0;i<n;i++){
			obj[i] = new Process();
			obj[i].input();
		}
		//Portion to calculate turn around time and waiting time
		int sum=0;
		for(int i=0;i<n;i++){
			int min=99999;
			int p=99999;
			int flag=0;
			for(int j=0;j<n;j++){
				if(min>obj[j].arrival && obj[j].flag==0){
					min=obj[j].arrival;
					p=j;
					flag=1;
				}
			}
			if(flag==1){
			obj[p].wtime=sum-obj[p].arrival;
				if(obj[p].wtime<0 || sum==0)
					obj[p].wtime=0;
				obj[p].ttime=obj[p].wtime + obj[p].rburst;
				sum=sum+obj[p].rburst;
				obj[p].flag=1;
			}
		}

		System.out.println("Results of scheduling");
		System.out.println("=====================");
		System.out.println("pid	Arrival	Burst	Waiting	Turnaround");
		for(int i=0;i<n;i++){
			obj[i].output();
		}
		float avg_ttime=0,avg_wtime=0;
		for(int i=0;i<n;i++){
			avg_ttime+=obj[i].ttime;
			avg_wtime+=obj[i].wtime;
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
admin1@RKMA:~/Sem1/Programs/BG/Scheduling$ java fifo
Enter number of process : 
5
Enter process number : 
1
Enter arrival time for 1
0
Enter burst time for 1
2
Enter process number : 
2
Enter arrival time for 2
1
Enter burst time for 2
3
Enter process number : 
3
Enter arrival time for 3
2
Enter burst time for 3
5
Enter process number : 
4
Enter arrival time for 4
3
Enter burst time for 4
4
Enter process number : 
5
Enter arrival time for 5
4
Enter burst time for 5
6

Results of scheduling
=====================
pid	Arrival	Burst	Waiting	Turnaround
1	0	2	0	2
2	1	3	1	4
3	2	5	3	8
4	3	4	7	11
5	4	6	10	16
Average turn around time 8.2
Average wating time 4.2
*/
