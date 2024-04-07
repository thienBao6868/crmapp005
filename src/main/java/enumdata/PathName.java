package enumdata;

public enum PathName {

	ADDUSER("/add-user"),
	USERS("/users");
	
	private final String name;
	
	private PathName(String name) {
		this.name = name;
	};
	
	public String getName() {
		return name;
	}
	
	

}
