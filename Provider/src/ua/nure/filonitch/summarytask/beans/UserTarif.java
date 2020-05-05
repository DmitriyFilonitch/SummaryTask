package ua.nure.filonitch.summarytask.beans;

/**
 * @author D.Filonich
 *
 *         USER TARIF ENTITY
 *
 */
public class UserTarif {
	private int id_user;
	private String code;
	private int payment_status;

	/**
	 * @return the id_user
	 */
	public int getId_user() {
		return id_user;
	}

	/**
	 * @param id_user the id_user to set
	 */
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the payment_status
	 */
	public int getPayment_status() {
		return payment_status;
	}

	/**
	 * @param payment_status the payment_status to set
	 */
	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id_user;
		result = prime * result + payment_status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTarif other = (UserTarif) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id_user != other.id_user)
			return false;
		if (payment_status != other.payment_status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserTarif [id_user=" + id_user + ", code=" + code + ", payment_status=" + payment_status
				+ ", getId_user()=" + getId_user() + ", getCode()=" + getCode() + ", getPayment_status()="
				+ getPayment_status() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}

	public UserTarif() {
		super();
		// TODO Auto-generated constructor stub
	}

}