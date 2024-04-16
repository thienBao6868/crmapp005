package enumdata;

public enum PathName {

	ADDUSER("/add-user"),
	USERS("/users"),
	DETAILUSER("/user-details"),
	EDITUSER("/user-edit"),
	ROLES("/role-table"),
	ADDROLES("/role-add"),
	EDITROLES("/role-edit"),
	GROUPWORK("/groupwork"),
	ADDGROUPWORK("/groupwork-add"),
	DETAILSGROUPWORK("/groupwork-details"),
	ADDTASK("/task-add"),
	TASK("/task"),
	PROFILE("/profile"),
	EDITPROFILE("/profile-edit")
	
	;
	
	private final String name;
	
	private PathName(String name) {
		this.name = name;
	};
	
	public String getName() {
		return name;
	}
	
	

}
