package ua.nure.filonitch.summarytask.beans;

/**
 * @author D.Filonich
 *
 * SERVICES ENTITY
 *
 */
public class Services {

	private int service_id;
	private String service_name;
	private String service_description;

	public Services(int service_id, String service_name, String service_description) {
		this.service_id = service_id;
		this.service_name = service_name;
		this.service_description = service_description;
	}

	public Services() {
	
	}

	@Override
	public String toString() {
		return "Services [service_id=" + service_id + ", service_name=" + service_name + ", service_description="
				+ service_description + ", hashCode()=" + hashCode() + ", getService_id()=" + getService_id()
				+ ", getService_name()=" + getService_name() + ", getService_description()=" + getService_description()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Services other = (Services) obj;
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
}