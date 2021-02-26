class philosopher{
	//value of ststus (0,thinking) (1,hungry) (2,eating)
	int status;
	int philo_num;
	philosopher obj[];
	int n;
	philosopher(int philo_num,philosopher obj[],int n){
		this.philo_num=philo_num;
		this.obj=obj;
		this.n=n;
		status=0;
	}
	void putdown(){
		status=0;
		System.out.println(philo_num+" is Thinking");
		test((philo_num-1)%n);
		test((philo_num+1)%n);
	}
	void pickup(){
		status=1;
		System.out.println(philo_num+" is hungry");
		test(philo_num);
	}
	void test(int i){
		if(status==1&&obj[(i+1)%n].status!=2&&obj[(i-1)%n].status!=2){
			status=2;
			System.out.println(i+" is eating");
		}
	}
}

class empty_class{}

class create_thread{
	public static void main(String[] ars){
	philosopher obj[]=new philosopher[5];
	for(int i=0;i<5;i++)
		obj[i]=new philosopher(i,obj,5);
	//Creating thread
	empty_class sync=new empty_class();
	final int i;
	for(i=0;i<5;i++){
		Thread th=new Thread(){
			public void run(){
				while(true){
					synchronized(sync){obj[i].pickup();}
					try{Thread.sleep(1000);}catch(Exception e){}
					synchronized(sync){obj[i].putdown();}
				}
			}
		};
		th.start();
	}
	}
}