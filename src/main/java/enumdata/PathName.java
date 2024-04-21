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
	EDITGROUPWORK("/groupwork-edit"),
	ADDTASK("/task-add"),
	TASK("/task"),
	EDITTASK("/task-edit"),
	TASKDETAIL("/task-detail"),
	PROFILE("/profile"),
	EDITPROFILE("/profile-edit"),
	LOGIN("/login"),
	LOGOUT("/logout")
	
	;
	
	private final String name;
	
	private PathName(String name) {
		this.name = name;
	};
	
	public String getName() {
		return name;
	}
	
	

}
