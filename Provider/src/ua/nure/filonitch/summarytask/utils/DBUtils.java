package ua.nure.filonitch.summarytask.utils;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.filonitch.summarytask.beans.Services;
import ua.nure.filonitch.summarytask.beans.Tarif;
import ua.nure.filonitch.summarytask.beans.UserAccount;
import ua.nure.filonitch.summarytask.beans.UserTarif;

/**
 * @author D.Filonich
 *
 * METHODS FOR EXECUTING AND PROCCESSING SQL REQUESTS
 *
 */
public class DBUtils {
	/*
	 * SQL REQUESTS
	 */
	private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT USER_ID, USER_NAME, FULLNAME , PASSWORD, balance , GENDER,BLOCK_STATUS, ROLE_ID, NAME FROM USER_ACCOUNT INNER JOIN ROLES ON USER_ACCOUNT.ROLE_ID=ROLES.ID WHERE USER_NAME = ? AND PASSWORD= ?";
	private static final String SELECT_USER_BY_USERNAME = "SELECT A.USER_ID, A.USER_NAME, A.PASSWORD,A.balance, A.BLOCK_STATUS,  A.GENDER FROM USER_ACCOUNT A  WHERE A.USER_NAME = ?";
	private static final String SELECT_ALL_FROM_TARIF_AND_SERVICES = "SELECT * FROM TARIF INNER JOIN SERVICES ON TARIF.SERVICE_ID=SERVICES.SERVICE_ID";
	private static final String SELECT_ALL_USERS = "SELECT * FROM USER_ACCOUNT INNER JOIN ROLES ON USER_ACCOUNT.ROLE_ID = ROLES.ID";
	private static final String SELECT_ALL_SERVICES = "SELECT * FROM SERVICES";
	private static final String SELECT_ALL_USERS_TARIF = "SELECT * FROM USERS_TARIF";
	private static final String SELECT_TARIF_BY_CODE = "SELECT * FROM TARIF A WHERE A.CODE=?";
	private static final String SELECT_USER_FOR_EDITING = "SELECT USER_ID, USER_NAME, FULLNAME , PASSWORD, GENDER,BALANCE,BLOCK_STATUS, ROLE_ID, NAME FROM USER_ACCOUNT INNER JOIN ROLES ON USER_ACCOUNT.ROLE_ID=ROLES.ID WHERE USER_ID=?";
	private static final String SELECT_SERVICE_BY_ID = "SELECT SERVICE_ID, SERVICE_NAME, SERVICE_DESCRIPTION FROM SERVICES WHERE SERVICE_ID=?";
	private static final String UPDATE_TARIF_BY_CODE = "UPDATE TARIF SET NAME =?, PRICE=?, DESCRIPTION=?, SERVICE_ID=? WHERE CODE=? ";
	private static final String INSERT_INTO_TARIF = "INSERT INTO TARIF(CODE, NAME,PRICE, DESCRIPTION, SERVICE_ID) VALUES (?,?,?,?,?)";
	private static final String DELETE_TARIF_BY_CODE = "DELETE FROM TARIF WHERE CODE= ?";
	private static final String UPDATE_USER = "UPDATE USER_ACCOUNT  SET USER_NAME=?, FULLNAME=?, GENDER=?, BALANCE=?,BLOCK_STATUS=?  WHERE USER_ID=? ";
	private static final String UPDATE_SERVICES = "UPDATE SERVICES  SET SERVICE_NAME=?, SERVICE_DESCRIPTION=?  WHERE SERVICE_ID=? ";
	private static final String INSERT_INTO_USERS_ACCOUNT = "INSERT INTO USER_ACCOUNT( USER_NAME, FULLNAME, GENDER, PASSWORD,BALANCE, ROLE_ID) VALUES (?,?,?,?,?,?)";
	private static final String INSERT_INTO_SERVICES = "INSERT INTO SERVICES (SERVICE_ID, SERVICE_NAME, SERVICE_DESCRIPTION) VALUES (?,?,?)";
	private static final String DELETE_FROM_SERVICES = "DELETE FROM SERVICES WHERE SERVICE_ID= ?";
	private static final String DELETE_FROM_USER_ACCOUNT = "DELETE FROM USER_ACCOUNT WHERE USER_ID= ?";
	private static final String SELECT_USER_TARIF = "SELECT * FROM TARIF INNER JOIN USERS_TARIF  ON TARIF.CODE=USERS_TARIF.CODE  WHERE ID_USER =? ";
	private static final String SELECT_USERS_TARIFS = "SELECT * FROM SERVICES INNER JOIN TARIF INNER JOIN USERS_TARIF ON TARIF.CODE = USERS_TARIF.CODE AND TARIF.SERVICE_ID = SERVICES.SERVICE_ID WHERE ID_USER =?";
	private static final String SELECT_USERS_SERVICES = "SELECT * FROM SERVICES INNER JOIN TARIF INNER JOIN USERS_TARIF ON TARIF.CODE = USERS_TARIF.CODE AND TARIF.SERVICE_ID = SERVICES.SERVICE_ID WHERE ID_USER =?";
	private static final String SELECT_USER_SERVICES = "SELECT * FROM SERVICES INNER JOIN TARIF INNER JOIN USERS_TARIF ON TARIF.CODE = USERS_TARIF.CODE AND TARIF.SERVICE_ID = SERVICES.SERVICE_ID WHERE ID_USER =? ";
	private static final String SELECT_ALL_OF_SERVICES = "SELECT * FROM SERVICES";
	private static final String SELECT_USER_TARIF_FOR_EDIT = "SELECT * FROM USERS_TARIF WHERE ID_USER=? AND CODE=?";
	private static final String UPDATE_USER_TARIF = "UPDATE USERS_TARIF  SET CODE=?, PAYMENT_STATUS=?  WHERE ID_USER=? AND CODE=? ";
	private static final String INSERT_INTO_USERS_TARIF = "INSERT INTO USERS_TARIF (ID_USER, CODE) VALUES (?,?)";
	private static final String DISABLE_SAFE_MODE = "SET SQL_SAFE_UPDATES=0";
	private static final String DELETE_USER_TARIF = "DELETE FROM USERS_TARIF WHERE ID_USER= ? AND CODE=?";
	private static final String ENABLE_SAFE_MODE = "SET SQL_SAFE_UPDATES=1";
	private static final String SELECT_TARIF_BY_SERVICE_ID = "SELECT * FROM TARIF where service_id=?";
	private static final String INSERT_INTO_USERS_TARIFS = "INSERT INTO USERS_TARIF (ID_USER, CODE, PAYMENT_STATUS) VALUES (?,?,2)";
	private static final String DELETE_FROM_USERS_TARIF_BY_USER_ID_AND_TARIF_CODE = "DELETE FROM USERS_TARIF WHERE ID_USER= ? AND CODE= ?";
	private static final String SELECT_PRICE_TO_PAY = "SELECT SUM(price) AS total FROM SERVICES INNER JOIN TARIF INNER JOIN USERS_TARIF ON TARIF.CODE = USERS_TARIF.CODE AND TARIF.SERVICE_ID = SERVICES.SERVICE_ID WHERE ID_USER =? AND PAYMENT_STATUS=2";
	private static final String UPDATE_USER_BALANCE = "UPDATE USER_ACCOUNT  SET BALANCE=?  WHERE USER_ID=? ";
	private static final String GET_PAYMENT_STATUS = "SELECT PAYMENT_STATUS FROM USERS_TARIF WHERE ID_USER=?";
	private static final String UPDATE_PAYMENT_STATUS = " UPDATE USERS_TARIF SET PAYMENT_STATUS=1 WHERE ID_USER=?";
	private static final String UPDATE_PAYMENT_STATUS_RESET = " UPDATE USERS_TARIF SET PAYMENT_STATUS=2 WHERE ID_USER=? AND CODE=?";
	private static final String GET_BALANCE = "SELECT BALANCE FROM USER_ACCOUNT WHERE USER_ID=?";
	private static final String UPDATE_BALANCE = "UPDATE USER_ACCOUNT SET BALANCE=? WHERE USER_ID=?";
	private static final String SET_BLOCK_STATUS = "UPDATE USER_ACCOUNT SET BLOCK_STATUS=? WHERE USER_ID=?";;

	private static DBUtils instance;

	public static synchronized DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils();
		}
		return instance;
	}

	/*
	 * METHODS
	 */
	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			int user_id = rs.getInt("user_id");
			String fullname = rs.getString("Fullname");
			String gender = rs.getString("Gender");
			float balance = rs.getFloat("balance");
			boolean block_status = rs.getBoolean("block_status");
			int role_id1 = rs.getInt("Role_id");
			String nameRole = rs.getString("Name");
			UserAccount user = new UserAccount(user_id, userName, fullname, gender, null, balance, block_status,
					role_id1, nameRole);
			user.setUser_id(user_id);
			user.setUserName(userName);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setGender(gender);
			user.setRole_id(role_id1);
			user.setNameRole(nameRole);
			return user;
		}
		return null;
	}

	public static UserAccount findUser(Connection conn, String userName) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_USER_BY_USERNAME);
		pstm.setString(1, userName);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			int user_id = rs.getInt("user_id");
			String password = rs.getString("Password");
			String gender = rs.getString("Gender");
			float balance = rs.getFloat("balance");
			boolean block_status = rs.getBoolean("block_status");
			// int role_id = rs.getInt("role_id");
			UserAccount user = new UserAccount(user_id, userName, null, gender, null, balance, block_status, null);
			user.setUser_id(user_id);
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			return user;
		}
		return null;
	}

	public static List<Tarif> queryProduct(Connection conn) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_FROM_TARIF_AND_SERVICES);

		ResultSet rs = pstm.executeQuery();
		List<Tarif> list = new ArrayList<Tarif>();
		while (rs.next()) {
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			String description = rs.getString("description");
			int service_id = rs.getInt("service_id");

			Tarif tarif = new Tarif();
			tarif.setCode(code);
			tarif.setName(name);
			tarif.setPrice(price);
			tarif.setDescription(description);
			tarif.setService_id(service_id);

			list.add(tarif);
		}
		return list;
	}

	public static List<UserAccount> queryUsers(Connection conn) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_USERS);

		ResultSet rs = pstm.executeQuery();
		List<UserAccount> list = new ArrayList<UserAccount>();
		while (rs.next()) {
			int user_id = rs.getInt("user_id");
			String userName = rs.getString("User_name");
			String fullname = rs.getString("Fullname");
			String gender = rs.getString("gender");
			float balance = rs.getFloat("balance");
			boolean block_status = rs.getBoolean("block_status");
			int role_id = rs.getInt("role_id");
			String nameRole = rs.getString("name");
			UserAccount u = new UserAccount(user_id, userName, fullname, gender, null, balance, block_status, role_id,
					nameRole);
			u.setUser_id(user_id);
			u.setUserName(userName);
			u.setFullname(fullname);
			u.setGender(gender);
			u.setNameRole(nameRole);
			list.add(u);
		}
		return list;
	}

	public static List<Services> queryServices(Connection conn) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_SERVICES);

		ResultSet rs = pstm.executeQuery();
		List<Services> list = new ArrayList<Services>();

		while (rs.next()) {
			int service_id = rs.getInt("service_id");
			String service_name = rs.getString("service_name");
			String service_description = rs.getString("service_description");

			Services services = new Services(service_id, service_name, service_description);
			services.setService_id(service_id);
			services.setService_name(service_name);
			services.setService_description(service_description);
			list.add(services);

		}
		return list;
	}

	public static List<UserTarif> queryUserTarif(Connection conn) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_USERS_TARIF);

		ResultSet rs = pstm.executeQuery();
		List<UserTarif> list = new ArrayList<UserTarif>();
		while (rs.next()) {
			int id_user = rs.getInt("id_user");
			String code = rs.getString("code");
			int payment_status = rs.getInt("payment_status");

			UserTarif usertarif = new UserTarif(id_user, code, payment_status);
			usertarif.setId_user(id_user);
			usertarif.setCode(code);
			usertarif.setPayment_status(payment_status);
			list.add(usertarif);
		}
		return list;
	}

	public static Tarif findTarif(Connection conn, String code) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_TARIF_BY_CODE);
		pstm.setString(1, code);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			String description = rs.getString("description");
			int service_id = rs.getInt("service_id");
			Tarif tarif = new Tarif(code, name, price, description, service_id);
			return tarif;
		}
		return null;
	}

	public static UserAccount findForEditUser(Connection conn, int user_id) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_USER_FOR_EDITING);
		pstm.setInt(1, user_id);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			// int user_id = rs.getInt("user_id");
			String userName = rs.getString("User_name");
			String fullname = rs.getString("Fullname");
			String gender = rs.getString("gender");
			float balance = rs.getFloat("balance");
			boolean block_status = rs.getBoolean("block_status");
			int role_id = rs.getInt("role_id");
			String nameRole = rs.getString("name");
			UserAccount user = new UserAccount(user_id, userName, fullname, gender, null, balance, block_status,
					role_id, nameRole);
			return user;
		}
		return null;
	}

	public static Services findService(Connection conn, int service_id) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_SERVICE_BY_ID);
		pstm.setInt(1, service_id);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String name = rs.getString("service_name");
			String description = rs.getString("service_description");
			Services service = new Services(service_id, name, description);
			return service;
		}
		return null;
	}

	public static void updateTarif(Connection conn, Tarif tarif) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(UPDATE_TARIF_BY_CODE);

		pstm.setString(1, tarif.getName());
		pstm.setFloat(2, tarif.getPrice());
		pstm.setString(3, tarif.getDescription());
		// pstm.setString(4, tarif.getCode());
		pstm.setInt(4, tarif.getService_id());
		pstm.setString(5, tarif.getCode());

		pstm.executeUpdate();
	}

	public static void insertTarif(Connection conn, Tarif tarif) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(INSERT_INTO_TARIF);

		pstm.setString(1, tarif.getCode());
		pstm.setString(2, tarif.getName());
		pstm.setFloat(3, tarif.getPrice());
		pstm.setString(4, tarif.getDescription());
		pstm.setInt(5, tarif.getService_id());

		pstm.executeUpdate();
	}

	public static void deleteTarif(Connection conn, String code) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(DELETE_TARIF_BY_CODE);

		pstm.setString(1, code);

		pstm.executeUpdate();
	}

	public static void updateUser(Connection conn, UserAccount user) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(UPDATE_USER);

		pstm.setString(1, user.getUserName());
		pstm.setString(2, user.getFullname());
		pstm.setString(3, user.getGender());
		pstm.setFloat(4, user.getBalance());
		pstm.setBoolean(5, user.isBlock_status());
		;
		pstm.setInt(6, user.getUser_id());
		pstm.executeUpdate();
	}

	public static void updateService(Connection conn, Services service) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(UPDATE_SERVICES);

		pstm.setString(1, service.getService_name());
		pstm.setString(2, service.getService_description());
		pstm.setInt(3, service.getService_id());
		pstm.executeUpdate();
	}

	public static void insertUser(Connection conn, UserAccount user) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(INSERT_INTO_USERS_ACCOUNT);

		pstm.setString(1, user.getUserName());
		pstm.setString(2, user.getFullname());
		pstm.setString(3, user.getGender());
		pstm.setString(4, user.getPassword());
		pstm.setFloat(5, user.getBalance());
		pstm.setInt(6, user.getRole_id());

		pstm.executeUpdate();
	}

	public static void insertService(Connection conn, Services service) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(INSERT_INTO_SERVICES);

		pstm.setInt(1, service.getService_id());
		pstm.setString(2, service.getService_name());
		pstm.setString(3, service.getService_description());

		pstm.executeUpdate();
	}

	public static void deleteService(Connection conn, int service_id) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(DELETE_FROM_SERVICES);
		pstm.setInt(1, service_id);
		pstm.executeUpdate();

	}

	public static void deleteUser(Connection conn, int user_id) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(DELETE_FROM_USER_ACCOUNT);
		pstm.setInt(1, user_id);
		pstm.executeUpdate();

	}

	public static Tarif findUserTarif(Connection conn, int user_id) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_USER_TARIF);
		pstm.setInt(1, user_id);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {

			String code = rs.getString("Code");
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			String description = rs.getString("description");
			int service_id = rs.getInt("service_id");
			Tarif tarif = new Tarif(code, name, price, description, service_id);
			return tarif;
		}
		return null;
	}

	public static List<Tarif> usersTarifs(Connection conn, int id_user) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_USERS_TARIFS);
		pstm.setInt(1, id_user);

		ResultSet rs = pstm.executeQuery();
		List<Tarif> list = new ArrayList<Tarif>();
		while (rs.next()) {
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			String description = rs.getString("description");
			int service_id = rs.getInt("service_id");

			String service_name = rs.getString("service_name");
			String service_description = rs.getString("service_description");

			Tarif tarif = new Tarif(code, name, price, description, service_id, service_name, service_description);
			tarif.setCode(code);
			tarif.setName(name);
			tarif.setPrice(price);
			tarif.setDescription(description);
			tarif.setService_id(service_id);
			tarif.setService_name(service_name);
			tarif.setService_description(service_description);

			list.add(tarif);
		}
		return list;
	}

	public static List<Services> usersServices(Connection conn, int user_id) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_USERS_SERVICES);
		pstm.setInt(1, user_id);
		ResultSet rs = pstm.executeQuery();
		List<Services> list = new ArrayList<Services>();
		while (rs.next()) {
			int service_id = rs.getInt("service_id");
			String service_name = rs.getString("service_name");
			String service_description = rs.getString("service_description");

			Services services = new Services(service_id, service_name, service_description);
			services.setService_id(service_id);
			services.setService_name(service_name);
			services.setService_description(service_description);
			list.add(services);
		}
		return list;
	}

	public static Services findUserService(Connection conn, int user_id) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_USER_SERVICES);
		pstm.setInt(1, user_id);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {

			int service_id = rs.getInt("service_id");
			String service_name = rs.getString("service_name");
			String service_description = rs.getString("service_description");
			Services service = new Services(service_id, service_name, service_description);
			return service;
		}
		return null;
	}

	public static Services allServices(Connection conn) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_OF_SERVICES);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {

			int service_id = rs.getInt("service_id");
			String service_name = rs.getString("service_name");
			String service_description = rs.getString("service_description");
			Services service = new Services(service_id, service_name, service_description);
			return service;
		}
		return null;
	}

	public static UserTarif editUserTarif(Connection conn, int id_user, String code) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_USER_TARIF_FOR_EDIT);
		pstm.setInt(1, id_user);
		pstm.setString(2, code);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			int payment_status = rs.getInt("payment_status");
			UserTarif userTarif = new UserTarif(id_user, code, payment_status);
			return userTarif;
		}
		return null;
	}

	public static void updateUserTarif(Connection conn, UserTarif userTarif, String code) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(UPDATE_USER_TARIF);

		pstm.setString(1, userTarif.getCode());
		pstm.setInt(2, userTarif.getPayment_status());
		pstm.setInt(3, userTarif.getId_user());
		pstm.setString(4, code);
		pstm.executeUpdate();
	}

	public static void insertUserTarif(Connection conn, UserTarif userTarif) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(INSERT_INTO_USERS_TARIF);

		pstm.setInt(1, userTarif.getId_user());
		pstm.setString(2, userTarif.getCode());

		pstm.executeUpdate();

	}

	public static void deleteUserTarif(Connection conn, int id_user, String code) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(DISABLE_SAFE_MODE);
		pstm.executeUpdate();
		PreparedStatement pstm1 = conn.prepareStatement(DELETE_USER_TARIF);
		pstm1.setInt(1, id_user);
		pstm1.setString(2, code);
		pstm1.executeUpdate();
		PreparedStatement pstm2 = conn.prepareStatement(ENABLE_SAFE_MODE);
		pstm2.executeUpdate();

	}

	public static List<Tarif> getTarif(Connection conn, int service_id) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SELECT_TARIF_BY_SERVICE_ID);
		pstm.setInt(1, service_id);

		ResultSet rs = pstm.executeQuery();
		List<Tarif> list = new ArrayList<Tarif>();
		while (rs.next()) {
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			String description = rs.getString("description");

			Tarif tarif = new Tarif();
			tarif.setCode(code);
			tarif.setName(name);
			tarif.setPrice(price);
			tarif.setDescription(description);
			tarif.setService_id(service_id);

			list.add(tarif);
		}
		return list;

	}

	public static void addUserTarif(Connection conn, UserTarif userTarif, int user_id, String code)
			throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(INSERT_INTO_USERS_TARIFS);
		pstm.setInt(1, user_id);

		pstm.setString(2, userTarif.getCode());

		pstm.executeUpdate();
	}

	public static void stopTarifByUser(Connection conn, int id_user, String code) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(DISABLE_SAFE_MODE);
		pstm.executeUpdate();
		PreparedStatement pstm1 = conn.prepareStatement(DELETE_FROM_USERS_TARIF_BY_USER_ID_AND_TARIF_CODE);
		pstm1.setInt(1, id_user);
		pstm1.setString(2, code);
		pstm1.executeUpdate();
		PreparedStatement pstm2 = conn.prepareStatement(ENABLE_SAFE_MODE);
		pstm2.executeUpdate();

	}

	public static float getFullPrice(Connection conn, int id_user) throws SQLException {
		float total = 0;
		PreparedStatement pstm = conn.prepareStatement(SELECT_PRICE_TO_PAY);
		pstm.setInt(1, id_user);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			float sum = rs.getFloat("total");
			total = sum;
		}

		return total;
	}

	public static void payTarif(Connection conn, UserAccount user) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(UPDATE_USER_BALANCE);
		pstm.setFloat(1, user.getBalance());
		pstm.setInt(2, user.getUser_id());
		pstm.executeUpdate();
	}

	public static int getPaymentStatus(Connection conn, int id_user) throws SQLException {
		int status = 0;
		PreparedStatement pstm = conn.prepareStatement(GET_PAYMENT_STATUS);
		pstm.setInt(1, id_user);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			int res = rs.getInt("payment_status");
			status = res;
		}

		return status;
	}

	public static void setPaymentStatus(Connection conn, int id_user) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(UPDATE_PAYMENT_STATUS);

		pstm.setInt(1, id_user);
		pstm.executeUpdate();
	}

	public static void reSetPaymentStatus(Connection conn, int id_user) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(UPDATE_PAYMENT_STATUS_RESET);
		Tarif tarif = new Tarif();
		pstm.setInt(1, id_user);
		pstm.setString(2, tarif.getCode());
		pstm.executeUpdate();
	}

	public static int getBalance(Connection conn, int id_user) throws SQLException {
		int balance = 0;
		PreparedStatement pstm = conn.prepareStatement(GET_BALANCE);
		pstm.setInt(1, id_user);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			int res = rs.getInt("balance");
			balance = res;
		}

		return balance;
	}

	public static void setBalance(Connection conn, UserAccount user, float newbalance) throws SQLException {
		PreparedStatement pstm = conn.prepareStatement(UPDATE_BALANCE);

		pstm.setFloat(1, newbalance);
		pstm.setInt(2, user.getUser_id());
		pstm.executeUpdate();

	}

	public static void setBlockStatus(Connection conn, UserAccount user) throws SQLException {

		PreparedStatement pstm = conn.prepareStatement(SET_BLOCK_STATUS);

		pstm.setBoolean(1, user.isBlock_status());
		pstm.setInt(2, user.getUser_id());
		pstm.executeUpdate();
	}

}