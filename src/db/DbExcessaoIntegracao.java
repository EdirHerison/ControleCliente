package db;

public class DbExcessaoIntegracao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DbExcessaoIntegracao (String msg) {
		super (msg);
	}
}
