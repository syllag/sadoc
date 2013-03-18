package fr.univartois.ili.sadoc.metier.commun.utils;

import java.util.ArrayList;
import java.util.List;

public class StringSplitter {

	public static String[] split(String string, char delim){
		List<String> res=new ArrayList<String>();

		int i=0;
		
		char[] decoupChar=string.toCharArray();
		StringBuilder tmp=new StringBuilder();
		
		while(i<decoupChar.length){
			if(decoupChar[i]!=delim)
				tmp.append(decoupChar[i]);
			else{
				res.add(tmp.toString());
				tmp=new StringBuilder();
			}
			i++;
		}
		
		res.add(tmp.toString());
		
		return res.toArray(new String[res.size()]);
	}
}
