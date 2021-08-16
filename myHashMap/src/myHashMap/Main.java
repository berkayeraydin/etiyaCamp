package myHashMap;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		
		MyHashMap countryCapital = new MyHashMap();
		
		countryCapital.put("Hollanda","Amsterdam");
		countryCapital.put("Ingiltere","Londra");
		
		//Anahtar Kelime YAZ DEGERI AL
		//HashMap<String,Integer> eraydinAilesi = new HashMap<String,Integer>();
		//HashMap hm = new HashMap();
		// Key:Value
		//eraydinAilesi.put("Berkay", 23);
		//eraydinAilesi.put("Sena", 20);
		//eraydinAilesi.remove("Berkay");
		//System.out.println(countryCapital.get("Berkay"));
		// KUMEYI ekrana yazdir
		//System.out.println(eraydinAilesi);
		
		for (String countryCapitals : countryCapital.getAllcountryCapitals()) {
			System.out.println(countryCapitals);
		}
		
		//ANAHTAR ve -> DEGERINE ULASMA
		//for(String i:eraydinAilesi.keySet()) {		
				//System.out.println("Anahtar (isim) = " +i+" => Deger (yas) = "+eraydinAilesi.get(i));
		//}
		/*
		Set s = eraydinAilesi.entrySet();
		Iterator i = s.iterator();
		while(i.hasNext()) {
			Map.Entry item = (Map.Entry) i.next();
			System.out.println("Anahtari = "+item.getKey()+" => Degeri = "+item.getValue());
		}
		System.out.println(s);
		*/

	}

}
