package fr.univartois.ili.sadoc.sadocweb.utils;

public abstract class Crypt {

	public static boolean trueFalseID(String tmp) {
		if(tmp.length()!=12) return false;
		String falseid = tmp.substring(0, 9);
		String product = tmp.substring(10, 12);

		long id = Long.parseLong(falseid);
		int prod = Integer.parseInt(product);
		int calcprod = calculateProd(id);
		return calcprod == prod;

	}

	public static long findRealID(String tmp) {
		return Long.parseLong(tmp.substring(0, 9));
	}

	public static String createFalseID(long ID) {
		String temp = String.valueOf(ID);

		if (temp.length() > 9) {
			temp = temp.substring(0, 9);
			ID = Long.parseLong(temp);

		} else {
			StringBuffer sbid = new StringBuffer();
			int add = 9 - temp.length();
			while (add > 0) {
				sbid.append("0");
				add--;
			}
			temp = sbid.toString() + temp;

		}
		return temp + createProd(ID);
	}

	public static int calculateProd(long ID) {
		if(ID==0) return 0;
		int prod = 1;
		
		while (ID > 0) {
			prod *= ID % 10;
			ID = ID / 10;
		}
		prod %= 1000;
		return prod;
		
	}

	public static String createProd(long ID) {
		int prod = calculateProd(ID);
		String tmpprod = String.valueOf(prod);

		int addprod = 3 - tmpprod.length();
		StringBuffer sbprod = new StringBuffer();
		while (addprod > 0) {
			sbprod.append("0");
			addprod--;
		}
		String product = sbprod.toString() + tmpprod;
		return product;
	}
	
}
