package in.co.rays.proj3.dto;


/**
 * Role JavaBean encapsulates Role attributes
 * @author shubham sharma
 *
 */
public class RoleDTO extends BaseDTO {

	/**
	 * Predefined Role constants
	 */

	public static final int ADMIN = 1;

	public static final long STUDENT = 2;

	public static final int COLLEGE = 3;

	public static final int KIOSK = 4;

	public static final int FACULTY = 5;

	/**
	 * Name of Role
	 */
	private String name;

	/**
	 * Description of Role
	 */
	private String description;

	/**
	 * accessor
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @Override public String getKey()
	 */

	public String getKey() {

		return id + "";
	}

	/**
	 * @Override public String getValue()
	 */

	public String getValue() {

		return name;
	}

}
