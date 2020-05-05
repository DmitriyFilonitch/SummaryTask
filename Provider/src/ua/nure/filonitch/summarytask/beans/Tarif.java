package ua.nure.filonitch.summarytask.beans;

/**
 * @author D.Filonich
 *
 * TARIFF ENTITY
 *
 */
public class Tarif {

	private String code;
	private String name;
	private float price;
	private String description;
	private int service_id;
	private String service_name;
	private String service_description;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the service_id
	 */
	public int getService_id() {
		return service_id;
	}
	/**
	 * @param service_id the service_id to set
	 */
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	/**
	 * @return the service_name
	 */
	public String getService_name() {
		return service_name;
	}
	/**
	 * @param service_name the service_name to set
	 */
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	/**
	 * @return the service_description
	 */
	public String getService_description() {
		return service_description;
	}
	/**
	 * @param service_description the service_description to set
	 */
	public void setService_description(String service_description) {
		this.service_description = service_description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((service_description == null) ? 0 : service_description.hashCode());
		result = prime * result + service_id;
		result = prime * result + ((service_name == null) ? 0 : service_name.hashCode());
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
		Tarif other = (Tarif) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (service_description == null) {
			if (other.service_description != null)
				return false;
		} else if (!service_description.equals(other.service_description))
			return false;
		if (service_id != other.service_id)
			return false;
		if (service_name == null) {
			if (other.service_name != null)
				return false;
		} else if (!service_name.equals(other.service_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tarif [code=" + code + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", service_id=" + service_id + ", service_name=" + service_name + ", service_description="
				+ service_description + ", getCode()=" + getCode() + ", getName()=" + getName() + ", getPrice()="
				+ getPrice() + ", getDescription()=" + getDescription() + ", getService_id()=" + getService_id()
				+ ", getService_name()=" + getService_name() + ", getService_description()=" + getService_description()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
	public Tarif() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tarif(String code, String name, float price, String description, int service_id, String service_name,
			String service_description) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.description = description;
		this.service_id = service_id;
		this.service_name = service_name;
		this.service_description = service_description;
	}
	public Tarif(String code, String name, float price, String description, int service_id) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.description = description;
		this.service_id = service_id;
	}

}
