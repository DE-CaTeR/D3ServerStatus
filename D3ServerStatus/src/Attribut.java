
public class Attribut implements Cloneable{
	public String name;
	public String value;
	
	public  Attribut(String tName, String tValue){
		name = tName;
		value = tValue;
	}
	
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("This should not occur since we implement Cloneable");
        }
    }
	
}
