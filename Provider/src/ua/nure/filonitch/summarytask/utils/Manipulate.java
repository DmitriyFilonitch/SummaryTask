package ua.nure.filonitch.summarytask.utils;

import java.io.FileWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ua.nure.filonitch.summarytask.beans.Services;
import ua.nure.filonitch.summarytask.beans.Tarif;
import ua.nure.filonitch.summarytask.beans.UserAccount;
import ua.nure.filonitch.summarytask.beans.UserTarif;

/**
 * @author D.Filonich
 *
 * DATA MANIPULATE CLASS
 *
 */
public class Manipulate {

	public Manipulate() {
	}

	/*
	 * SORTING SERVICES
	 */
	public static List<Services> getServicesSortedByNameAZ(List<Services> services) {
		Collections.sort(services, new Comparator<Services>() {
			@Override
			public int compare(Services service2, Services service1) {
				return service2.getService_name().compareTo(service1.getService_name());
			}
		});
		return services;
	}

	public static List<Services> getServicesSortedByNameZA(List<Services> services) {

		Collections.sort(services, new Comparator<Services>() {
			@Override
			public int compare(Services service2, Services service1) {
				return service1.getService_name().compareTo(service2.getService_name());
			}
		});
		return services;
	}

	
/*
 * SORTING USERS
 */
	public static List<UserAccount> getUserSortedByIDHighToLow(List<UserAccount> users) {
		Collections.sort(users, new Comparator<UserAccount>() {
			@Override
			public int compare(UserAccount user2, UserAccount user1) {
				return user1.getUser_id() - (user2.getUser_id());
			}
		});
		return users;

	}

	public static List<UserAccount> getUserSortedByIDLowToHigh(List<UserAccount> users) {
		Collections.sort(users, new Comparator<UserAccount>() {
			@Override
			public int compare(UserAccount user1, UserAccount user2) {
				return user1.getUser_id() - (user2.getUser_id());
			}
		});
		return users;

	}

	public static List<UserAccount> getUsersSortedByUserNameAZ(List<UserAccount> users) {
		Collections.sort(users, new Comparator<UserAccount>() {
			@Override
			public int compare(UserAccount user2, UserAccount user1) {
				return user2.getUserName().compareTo(user1.getUserName());
			}
		});
		return users;
	}

	public static List<UserAccount> getUsersSortedByUserNameZA(List<UserAccount> users) {
		Collections.sort(users, new Comparator<UserAccount>() {
			@Override
			public int compare(UserAccount user2, UserAccount user1) {

				return user1.getUserName().compareTo(user2.getUserName());
			}
		});
		return users;
	}
/*
 * SORTING TARIFFS
 */
	public static List<Tarif> getTarifSortedByPriceHtL(List<Tarif> tarifs) {
		Collections.sort(tarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif tariff2, Tarif tariff1) {
				return Float.compare(tariff1.getPrice(), (tariff2.getPrice()));
			}
		});
		return tarifs;

	}

	public static List<Tarif> getTarifSortedByPriceLtH(List<Tarif> tarifs) {
		Collections.sort(tarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif tariff2, Tarif tariff1) {
				return Float.compare(tariff2.getPrice(), (tariff1.getPrice()));
			}
		});
		return tarifs;

	}

	public static List<Tarif> getTarifSortedByNameAZ(List<Tarif> tarifs) {
		Collections.sort(tarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif t2, Tarif t1) {
				return t2.getName().compareTo(t1.getName());
			}
		});
		return tarifs;
	}

	public static List<Tarif> getTarifSortedByNameZA(List<Tarif> tarifs) {

		Collections.sort(tarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif t2, Tarif t1) {
				return t1.getName().compareTo(t2.getName());
			}
		});
		return tarifs;
	}

	public static List<Tarif> getTarifSortedByCodeAZ(List<Tarif> tarifs) {
		Collections.sort(tarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif tariff2, Tarif tariff1) {
				return tariff2.getCode().compareTo(tariff1.getCode());
			}
		});
		return tarifs;
	}

	public static List<Tarif> getTarifSortedByCodeZA(List<Tarif> tarifs) {
		Collections.sort(tarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif tariff2, Tarif tariff1) {
				return tariff1.getCode().compareTo(tariff2.getCode());
			}
		});
		return tarifs;
	}

	public static List<Tarif> getTarifSortedByServiceIDHtL(List<Tarif> tarifs) {
		Collections.sort(tarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif tariff2, Tarif tariff1) {
				return tariff1.getService_id() - (tariff2.getService_id());
			}
		});
		return tarifs;

	}

	public static List<Tarif> getTarifSortedByServiceIDLtH(List<Tarif> tarifs) {
		Collections.sort(tarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif tariff1, Tarif tariff2) {
				return tariff1.getService_id() - (tariff2.getService_id());
			}
		});
		return tarifs;

	}
	/*
	 * SORTING USERS TARIFS
	 */
	
	public static List<UserTarif> getUsersTarifsSortedByIDHighToLow(List<UserTarif> users_tarifs) {
		Collections.sort(users_tarifs, new Comparator<UserTarif>() {
			@Override
			public int compare(UserTarif user_tarif2, UserTarif user_tarif1) {

				return user_tarif1.getId_user() - (user_tarif2.getId_user());
			}
		});
		return users_tarifs;

	}

	public static List<UserTarif> getUsersTarifsSortedByIDLowToHigh(List<UserTarif> users_tarifs) {
		Collections.sort(users_tarifs, new Comparator<UserTarif>() {
			@Override
			public int compare(UserTarif user_tarif1, UserTarif user_tarif2) {

				return user_tarif1.getId_user() - (user_tarif2.getId_user());
			}
		});
		return users_tarifs;

	}

	public static List<UserTarif> getUsersTarifSortedByCodeAZ(List<UserTarif> users_tarifs) {
		Collections.sort(users_tarifs, new Comparator<UserTarif>() {
			@Override
			public int compare(UserTarif tariff2, UserTarif tariff1) {

				return tariff2.getCode().compareTo(tariff1.getCode());
			}
		});
		return users_tarifs;
	}

	public static List<UserTarif> getUsersTarifSortedByCodeZA(List<UserTarif> users_tarifs) {
		Collections.sort(users_tarifs, new Comparator<UserTarif>() {
			@Override
			public int compare(UserTarif tariff2, UserTarif tariff1) {

				return tariff1.getCode().compareTo(tariff2.getCode());
			}
		});
		return users_tarifs;
	}

/*
 * WRITING USER FILE
 */
	public static void writeFile(String nameOfFile, String data) {

		try (FileWriter writer = new FileWriter("C:/Users/dimon/Downloads/" + nameOfFile + ".txt", false)) {
			writer.write(data);
			writer.flush();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static String createFileForUser(UserAccount user, List<Tarif> serviceList, float total) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer contract = new StringBuffer("");
		String status = user.isBlock_status() ? "BLOCKED" : "UNBLOCKED";
		String active_status = user.isActive_status() ? "Активна" : "Неактивна";
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date date = new Date();
		int i = 1;
		for (Tarif serv : serviceList) {
			contract.append(i).append(". ").append(serv.getService_name()).append("\n\tСтоимость:")
					.append(serv.getPrice()).append(" грн\n" + "\tОписание: ").append(serv.getDescription())
					.append("\n\n\t");
			i++;
		}
		i = 1;
		String payment = null;
		if (total > 0) {
			payment = "ЗАДОЛЖЕННОСТЬ : " + total + " грн!";
		} else {
			payment = "ЗАДОЛЖЕННОСТЕЙ НЕТ!";
		}

		sb2.append("\n\n\t" + "СТАТУС ОПЛАТЫ : ").append(payment)
				.append("\n\tДАТА ЗАГРУЗКИ : " + dateFormat.format(date));
		sb.append("Договор №").append(user.getUser_id()).append(" на предоставление услуг\n" + "Пользователю ")
				.append(user.getFullname()).append("\nЛогин - ").append(user.getUserName()).append("\nСтатус - ")
				.append(status).append("\nУровень учетной записи - ").append(active_status).append("\nБалланс = ").append(user.getBalance()).append("\nПодключенные услуги:\n\t")
				.append(contract).append(sb2);

		return sb.toString();
	}

}