package myHashMap;

public class MyHashMap {
	
	private String[] key = new String[0];
	private String[] value = new String[0];
	
	//HashMap ler Key:Value 
	//HashMap lerde .put ekleme yapıyor. Onun metodunu yaziyoruz
	public void put(String key, String value) {
		String[] tempKey = new String[(this.key.length)+1];
		String[] tempValue = new String[(this.value.length)+1];
		for(int i=0 ; i<this.key.length ; i++) {
			tempKey[i] = this.key[i];
			tempValue[i] = this.value[i];
		}
		tempKey[this.key.length] = key;
		tempValue[this.value.length] = value;
		
		this.key = tempKey;
		this.value = tempValue;
	}
	
	// HashMap .clear temizleme islemidir
	public void clear() {
		// Referance
		this.key = new String[0];
		this.value = new String[0];
	}
	
	public String[] getAllcountryCapitals() {
		String[] array = new String[this.key.length];
		for (int i = 0; i < this.key.length; i++) {

			array[i] = "Key = "+this.key[i] + " =>  Value = " + this.value[i];
		}
		return array;
	}
	
	

}
