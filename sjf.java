//Write a program to implement shortest-job-first(sjf) cpu scheduling algorithm.
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
		rburst=burst;
		flag=0;
		ttime=0;
		wtime=0;
	}
	void output(){
		System.out.println(pid + "	" + arrival + "	" + rburst + "	" + wtime + "	" + ttime);
	}
}

class sjf
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
			int min1=99999;
			int min2=99999;
			int p=n;
			for(int j=0;j<n;j++){
				if(obj[j].arrival<=sum && obj[j].flag==0 && obj[j].rburst<min1){
					min1=obj[j].rburst;
					p=j;
				}
			}
			//System.out.println("==="+p +"	" + sum);
			if(p==n){
				for(int j=0;j<n;j++){
					if(obj[j].flag==0 && obj[i].arrival < min2){
						p=j;
						min2=obj[j].arrival;
					}
				}
			}
			System.out.println(p +"	");
			obj[p].wtime=sum-obj[p].arrival;
			if((sum-obj[p].arrival)<0 || sum==0)
				obj[p].wtime=0;
			obj[p].ttime=obj[p].wtime + obj[p].rburst;
			sum=sum+obj[p].rburst;
			obj[p].flag=1;
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
admin1@RKMA:~/Sem1/Programs/BG/Scheduling$ java sjf
Enter number of process : 
5
Enter process number : 
1
Enter arrival time for 1
0
Enter burst time for 1
8
Enter process number : 
2
Enter arrival time for 2
1
Enter burst time for 2
1
Enter process number : 
3
Enter arrival time for 3
2
Enter burst time for 3
3
Enter process number : 
4
Enter arrival time for 4
3
Enter burst time for 4
2
Enter process number : 
5
Enter arrival time for 5
4
Enter burst time for 5
6
0	
1	
3	
2	
4

Results of scheduling
=====================
pid	Arrival	Burst	Waiting	Turnaround	
1	0	8	0	8
2	1	1	7	8
3	2	3	9	12
4	3	2	6	8
5	4	6	10	16
Average turn around time 10.4
Average wating time 6.4
*/
