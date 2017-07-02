package br.com.randserver.exception;


@SuppressWarnings("serial")
public class ServerException extends Exception {

	/**
	 * Construtor da classe DaoException.java
	 * @param msg - {@link String}
	 */
	public ServerException(String msg) {
		super(msg);
	}
	
	/**
	 * Construtor da classe DaoException.java
	 * @param msg - {@link String}
	 * @param ex - {@link Exception}
	 */
	public ServerException(String msg, Exception ex) {
		super(msg, ex);
	}
}