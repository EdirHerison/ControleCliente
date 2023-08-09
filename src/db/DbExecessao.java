package db;

public class DbExecessao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DbExecessao (String msgm) {
		super (msgm);
	}
}
