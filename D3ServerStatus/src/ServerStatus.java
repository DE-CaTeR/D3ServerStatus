import java.util.ArrayList;


public class ServerStatus {
	
	public String realm;
	public String name;
	public String status;
	public int statusID;
	public boolean changed;
	
	
	public ServerStatus(String tRealm, String tName, String tStatus){
		realm = tRealm;
		name = tName;
		status = tStatus;
		changed = true;
		setStatus();
		
	}
	
	private int setStatus(){
		if(status.equals("Offline")){
			statusID = 0;
			return statusID;
		}
		if(status.equals("Available")){
			statusID = 1;
			return statusID;
		}
		if(status.equals("Maintenance")){
			statusID = 2;
			return statusID;
		}
		statusID = 3;
		return statusID;
		
	}
	public String toString(){
		return realm + "//" + name + "//" + status + "//" + statusID + "//" + changed;
		
	}
	
	public boolean change(ServerStatus s){
		if(this.name.equals(s.name)){
			if(this.statusID == s.statusID){
				changed = false;
			} else {
				changed = true;
			}
		} else {
			changed = true;
		}
		return changed;
	}
	
	static public ArrayList<ServerStatus> compare(ArrayList<ServerStatus> sl1, ArrayList<ServerStatus> sl2){
		System.out.println("vergleiche...");
		ArrayList<ServerStatus> sList = new ArrayList<ServerStatus>();
		if(sl1 == null) return sl2;
		if(sl1.size() != sl2.size()) return sl2;

		int i = 0;
		while (i < sl1.size()){
			// neu(sl2) mit alt(sl1) vergleichen
			if(sl2.get(i).change(sl1.get(i))){
				sList.add(sl2.get(i));
			}
			i++;
		}
		
		return sList;
	}
}
