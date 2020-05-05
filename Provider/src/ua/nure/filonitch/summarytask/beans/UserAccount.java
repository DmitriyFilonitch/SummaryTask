package ua.nure.filonitch.summarytask.beans;

/**
 * @author D.Filonich
 *
 * USER ENTITY
 *
 */
public class UserAccount {

	public static String GENDER_MALE = "M";
	public static String GENDER_FEMALE = "F";

	private int user_id;
	private String userName;
	private String fullname;
	private String gender;
	private String password;
	private float balance;
	private boolean block_status;
	private int role_id;
	private String nameRole;

	/**
	 * @return the gENDER_MALE
	 */
	public static String getGENDER_MALE() {
		return GENDER_MALE;
	}

	/**
	 * @param gENDER_MALE the gENDER_MALE to set
	 */
	public static void setGENDER_MALE(String gENDER_MALE) {
		GENDER_MALE = gENDER_MALE;
	}

	/**
	 * @return the gENDER_FEMALE
	 */
	public static String getGENDER_FEMALE() {
		return GENDER_FEMALE;
	}

	/**
	 * @param gENDER_FEMALE the gENDER_FEMALE to set
	 */
	public static void setGENDER_FEMALE(String gENDER_FEMALE) {
		GENDER_FEMALE = gENDER_FEMALE;
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * @return the block_status
	 */
	public boolean isBlock_status() {
		return block_status;
	}

	/**
	 * @param block_status the block_status to set
	 */
	public void setBlock_status(boolean block_status) {
		this.block_status = block_status;
	}

	/**
	 * @return the role_id
	 */
	public int getRole_id() {
		return role_id;
	}

	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	/**
	 * @return the nameRole
	 */
	public String getNameRole() {
		return nameRole;
	}

	/**
	 * @param nameRole the nameRole to set
	 */
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + (block_status ? 1231 : 1237);
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((nameRole == null) ? 0 : nameRole.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + role_id;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + user_id;
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
		UserAccount other = (UserAccount) obj;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (block_status != other.block_status)
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (nameRole == null) {
			if (other.nameRole != null)
				return false;
		} else if (!nameRole.equals(other.nameRole))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role_id != other.role_id)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserAccount [user_id=" + user_id + ", userName=" + userName + ", fullname=" + fullname + ", gender="
				+ gender + ", password=" + password + ", balance=" + balance + ", block_status=" + block_status
				+ ", role_id=" + role_id + ", nameRole=" + nameRole + ", getUser_id()=" + getUser_id()
				+ ", getUserName()=" + getUserName() + ", getFullname()=" + getFullname() + ", getGender()="
				+ getGender() + ", getPassword()=" + getPassword() + ", getBalance()=" + getBalance()
				+ ", isBlock_status()=" + isBlock_status() + ", getRole_id()=" + getRole_id() + ", getNameRole()="
				+ getNameRole() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}

	public UserAccount(int user_id, String userName, String fullname, String gender, String password, float balance,
			boolean block_status, int role_id, String nameRole) {
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.fullname = fullname;
		this.gender = gender;
		this.password = password;
		this.balance = balance;
		this.block_status = block_status;
		this.role_id = role_id;
		this.nameRole = nameRole;
	}

	public UserAccount(int user_id, String userName, String fullname, String gender, String password, float balance,
			int role_id, String nameRole) {
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.fullname = fullname;
		this.gender = gender;
		this.password = password;
		this.balance = balance;
		this.role_id = role_id;
		this.nameRole = nameRole;
	}

	public UserAccount(int user_id, String userName, String fullname, String gender, String password, float balance,
			boolean block_status, String nameRole) {

		this.user_id = user_id;
		this.userName = userName;
		this.fullname = fullname;
		this.gender = gender;
		this.password = password;
		this.balance = balance;
		this.block_status = block_status;
		this.nameRole = nameRole;

	}

	public void setRole_id(String role_id2) {
		this.role_id = role_id;
	}

	public UserAccount() {
		super();
	}

}