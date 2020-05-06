package ua.nure.filonitch.summarytask.beans;

public class DolgList {
	private String tarif_name;
	private int countOfDolgs;

	/**
	 * @return the tarif_name
	 */
	public String getTarif_name() {
		return tarif_name;
	}

	/**
	 * @param tarif_name the tarif_name to set
	 */
	public void setTarif_name(String tarif_name) {
		this.tarif_name = tarif_name;
	}

	/**
	 * @return the countOfDolgs
	 */
	public int getCountOfDolgs() {
		return countOfDolgs;
	}

	/**
	 * @param countOfDolgs the countOfDolgs to set
	 */
	public void setCountOfDolgs(int countOfDolgs) {
		this.countOfDolgs = countOfDolgs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countOfDolgs;
		result = prime * result + ((tarif_name == null) ? 0 : tarif_name.hashCode());
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
		DolgList other = (DolgList) obj;
		if (countOfDolgs != other.countOfDolgs)
			return false;
		if (tarif_name == null) {
			if (other.tarif_name != null)
				return false;
		} else if (!tarif_name.equals(other.tarif_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DolgList [tarif_name=" + tarif_name + ", countOfDolgs=" + countOfDolgs + ", getTarif_name()="
				+ getTarif_name() + ", getCountOfDolgs()=" + getCountOfDolgs() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
}
