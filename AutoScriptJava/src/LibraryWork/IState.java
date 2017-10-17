package LibraryWork;

public interface IState {
	
	void run();
	
	boolean checkStop(String variable);
	
	void stop();
	
}
