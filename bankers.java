import java.io.*;
class process
{
	int pid;
	int max[];
	int allocated[];
	int need[];
	int no_of_res;
	int status;
	process(int n,int x)
	{
		pid=x;
		max=new int[n];
		allocated=new int[n];
		need=new int[n];
		no_of_res=n;
	}
	
	void input() throws IOException
	{
		BufferedReader ip=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("For "+pid+"th process:");
		System.out.println("======================");
		for(int i=0;i<no_of_res;i++)
		{
			System.out.println("Enter maximum number of "+(i+1)+"th resource required");
			max[i]=Integer.parseInt(ip.readLine());
		}
		status=0;
	}
	void alloc() throws IOException
	{
		BufferedReader ip=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("For "+pid+"th process:");
		System.out.println("======================");
		for(int i=0;i<no_of_res;i++)
		{
			System.out.println("Enter number of allocated "+(i+1)+"th resource ");
			allocated[i]=Integer.parseInt(ip.readLine());
			need[i]=max[i]-allocated[i];
		}
	}

}



class bankers
{
	public static void main(String[] ars)throws IOException
	{
		int count=0,flag;
		BufferedReader ip=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number of types of resource : ");
		int n1=Integer.parseInt(ip.readLine());
		int res[]=new int[n1];
		System.out.println("Enter number of process : ");
		int n2=Integer.parseInt(ip.readLine());
		System.out.println("Enter available number of resources::");
		System.out.println("=====================================");
		for(int i=0;i<n1;i++)
		{
			System.out.println((i+1)+"th resource :");
			res[i]=Integer.parseInt(ip.readLine());
		}
			
		process ob[]=new process[n2];
		System.out.println("Enter maximum need matrix");
		for(int i=0;i<n2;i++)
		{
			ob[i]=new process(n1,i+1);
			ob[i].input();
		}
		System.out.println("Now entering allocated matrix");
		for(int i=0;i<n2;i++)
		{
			ob[i].alloc();
		}
		//=========================================================
		System.out.println("Printing Max matrix");
		for(int i=0;i<n2;i++)
		{
			for(int j=0;j<n1;j++)
				System.out.print(ob[i].max[j]+"	");
			System.out.println("");
		}
		System.out.println("Printing Allocated matrix");
		for(int i=0;i<n2;i++)
		{
			for(int j=0;j<n1;j++)
				System.out.print(ob[i].allocated[j]+"	");
			System.out.println("");
		}
		System.out.println("Printing Need matrix");
		for(int i=0;i<n2;i++)
		{
			for(int j=0;j<n1;j++)
				System.out.print(ob[i].need[j]+"	");
			System.out.println("");
		}
		//==========================================================
		System.out.println("Algorithm started");
		System.out.println("=================");
		while(count!=n2)
		{
			int count1=count;
			System.out.println("count1 "+count1+" count "+count);
			for(int i=0;i<n2;i++)
			{
				flag=0;
				if(ob[i].status==0)
				{
					for(int j=0;j<n1;j++)
					{
						if(res[j]<ob[i].need[j])
						{	
							flag=1;
							break;
						}
					}
					if(flag==0)
					{
						System.out.println((i+1)+" process completed");
						count++;
						for(int j=0;j<n1;j++)
							res[j]=res[j]+ob[i].allocated[j];
						ob[i].status=1;
					}
				}
			}
			System.out.println("count1 "+count1+" count "+count);
			if(count1==count)
			{
				System.out.println("inside if count1 "+count1+" count "+count);
				System.out.println("Unsafe state");
				break;
			}
		}
	}
}


/*
Output
======
admin1@RKMA:~/Sem1/Programs/BG$ java bankers
Enter number of types of resource : 
3
Enter number of process : 
4
Enter available number of resources::
=====================================
1th resource :
1
2th resource :
1
3th resource :
1
Enter maximum need matrix
For 1th process:======================
Enter maximum number of 1th resource required
3
Enter maximum number of 2th resource required
4
Enter maximum number of 3th resource required
3
For 2th process:
======================
Enter maximum number of 1th resource required
2
Enter maximum number of 2th resource required
2
Enter maximum number of 3th resource required
2
For 3th process:
======================
Enter maximum number of 1th resource required
1
Enter maximum number of 2th resource required
1
Enter maximum number of 3th resource required
1
For 4th process:
======================
Enter maximum number of 1th resource required
2
Enter maximum number of 2th resource required
2
Enter maximum number of 3th resource required
2
Now entering allocated matrix
For 1th process:
======================
Enter number of allocated 1th resource 
1
Enter number of allocated 2th resource 
2
Enter number of allocated 3th resource 
1
For 2th process:
======================
Enter number of allocated 1th resource 
1
Enter number of allocated 2th resource 
0
Enter number of allocated 3th resource 
1
For 3th process:
======================
Enter number of allocated 1th resource 
0
Enter number of allocated 2th resource 
1
Enter number of allocated 3th resource 
1
For 4th process:
======================
Enter number of allocated 1th resource 
1
Enter number of allocated 2th resource 
0
Enter number of allocated 3th resource 
1
Printing Max matrix
3	4	3	
2	2	2	
1	1	1	
2	2	2	
Printing Allocated matrix
1	2	1	
1	0	1	
0	1	1	
1	0	1	
Printing Need matrix
2	2	2	
1	2	1	
1	0	0	
1	2	1
	
Algorithm started
=================
3 process completed
4 process completed
1 process completed
2 process completed
*/	
