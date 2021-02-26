import java.io.*;

class buffer{
	int data[];
	int n;
	int front,rear;
	buffer(int n){
		front=rear=-1;
		this.n=n;
		data=new int[this.n];
	}
	synchronized void insert(int element){
		if(front==-1){
			front=rear=0;
			data[rear]=element;
			System.out.println("One item produced.");
		}else if((rear+1)%n==front){
			System.out.println("Buffer is full.");
		}else{
			data[(rear+1)%n]=element;
			rear=(rear+1)%n;
			System.out.println("One item produced.");
		}
	}
	synchronized void delete(){
		if(front==-1){
			System.out.println("Empty buffer.");
		}else{
			if(front==rear){
				if(front!=-1)
					System.out.println("Item consumed.");
				front=rear=-1;
			}
			else{
				front=(front+1)%n;
				System.out.println("Item consumed");
			}
		}
	}
}

class producer_consumer{
	public static void main(String ars[])throws IOException{
		BufferedReader pr=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter number of elements :");
		int n=Integer.parseInt(pr.readLine());
		buffer ob=new buffer(n);
		Thread th1=new Thread(){
			public void run(){
				while(true){
					int x=(int)(Math.random()*10+1);
					ob.insert(x);
					try{Thread.sleep(1000);}catch(InterruptedException e){}
				}
			}
		};
		Thread th2=new Thread(){
			public void run(){
				while(true){
					ob.delete();
					try{Thread.sleep(4000);}catch(InterruptedException e){}
				}
			}
		};
		th1.start();
		th2.start();
	}
}

/*
Output when consumer is slower than the producer(Producer will encounter full buffer)
================================================
F:\sem1\Programs\BG>java producer_consumer
Enter number of elements :5
Empty buffer.
One item produced.
One item produced.
One item produced.
One item produced.
Item consumed
One item produced.
One item produced.
Buffer is full.
Buffer is full.
Item consumed
One item produced.
Buffer is full.
Buffer is full.

When the producer is slower than consumer(Consumer will encounter empty buffer)
=========================================
F:\sem1\Programs\BG>java producer_consumer
Enter number of elements :5
Empty buffer.
One item produced.
Item consumed.
Empty buffer.
Empty buffer.
Empty buffer.
One item produced.
Item consumed.
Empty buffer.
Empty buffer.
Empty buffer.
One item produced.
*/