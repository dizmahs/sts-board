package com.project.board.common;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.reflect.Array;

public class Box extends Hashtable{

	protected String name = null;
	/**
	 *
	 */
	public Box(String name) {
		super();
		this.name = name;
	}
	
	
	
	
	/**
	 * 
	 * @param target com.lgeds.jdf.servlet.Box
	 */
	public Object clone() {
	
		Box newbox = new Box(name);
	
		Hashtable src = (Hashtable)this;
		Hashtable target = (Hashtable)newbox;
		
		Enumeration e = src.keys();
		while(e.hasMoreElements()) {
			String key = (String) e.nextElement();
			Object value =  src.get(key);
			target.put(key,value);
		}
		return newbox;
	}
	
	
	
	
	
	/**
	 * request �� key ���� ������ box �� ��ƿ� Entity Class FieldName �����ϸ�
	 * Entity Class �� ��� �ش� <br>
	 * *.jsp ������ ���� ����
	 * <xmp> 
	 *
	 *  Box box = HttpUtility.getBox(request);
	 *  BoardEntity board = new BoardEntity();
	 *  box.copyToEntity(board);
	 *
	 * </xmp>
	 * @param entity java.lang.Object
	 */
	public void copyToEntity(Object entity) {
		if ( entity == null )
			throw new NullPointerException("trying to copy from box to null entity class");
	
		Class c = entity.getClass();
		java.lang.reflect.Field[] field = c.getFields();
		for (int i=0 ; i<field.length; i++) {
			try {
				String fieldtype = field[i].getType().getName();
				String fieldname = field[i].getName();
				
				if ( containsKey( fieldname ) ) {
					if ( fieldtype.equals("java.lang.String")) {
						field[i].set(entity, getString(fieldname));
					}
					else if ( fieldtype.equals("int")) {
						field[i].setInt(entity, getInt(fieldname));
					}
					else if ( fieldtype.equals("double")) {
						field[i].setDouble(entity, getDouble(fieldname));
					}
					else if ( fieldtype.equals("long")) {
						field[i].setLong(entity, getLong(fieldname));
					}
					else if ( fieldtype.equals("float")) {
						field[i].set(entity, new Float(getDouble(fieldname)));
					}
					else if ( fieldtype.equals("boolean")) {
						field[i].setBoolean(entity, getBoolean(fieldname));
					}
				}	// end of if 
			}	// end of try 
			catch(Exception e){
				System.out.println("copyToEntity ����"); //Debug.warn.println(this, e.getMessage());
			}
		}
	}
	
	
	
	
	
	
	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public String get(String key) {
		return getString(key);
	}
	
	
	
	
	
	
	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public boolean getBoolean(String key) {
		String value = getString(key);
		boolean isTrue = false;
		try {
			isTrue = (new Boolean(value)).booleanValue();
		}
		catch(Exception e){ System.out.println("getBoolean ����"); }
		return isTrue;
	}
	
	
	
	
	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public double getDouble(String key) {
		String value = removeComma(getString(key));
		if ( value.equals("") ) return 0;
		double num = 0;
		try {
			num = Double.valueOf(value).doubleValue();
		}
		catch(Exception e) {
			num = 0;
		}
		return num;
	}

	
	
	
	
	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public double getFloat(String key) {
		return (float)getDouble(key);
	}
	
	
	
	
	
	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public int getInt(String key) {
		double value = getDouble(key);
		return (int)value;
	}
	
	
	
	
	
	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public long getLong(String key) {
		String value = removeComma(getString(key));
		if ( value.equals("") ) return 0L;
		
		long lvalue = 0L;
		try {
			lvalue = Long.valueOf(value).longValue();
		}
		catch(Exception e) {
			lvalue = 0L;
		}
		
		return lvalue;
	}
	
	
	
	
	
	
	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public String getString(String key) {
		String value = null;
		try {
			Object o = (Object)super.get(key);
			Class c = o.getClass();
			if ( o == null ) value = "";
			else if( c.isArray() ) {
				int length = Array.getLength(o);
				if ( length == 0 ) value = "";
				else {
					Object item = Array.get(o, 0);
					if ( item == null ) value = "";
					else value = item.toString();
				}
			}			
			else 	value = o.toString();
		}
		catch(Exception e) {
			value = "";
		}
		//return value;
		return strBlockSpecialTags(value);
	}
	
	
	
	
	
	
	
	
	/**
	 * check box �� ���� ���� name�� ���� ���� value���� String�� Vector�� �Ѱ��ش�.
	 * @return Vector
	 * @param key java.lang.String
	 */
	public Vector getVector(String key) {
		Vector vector = new Vector();
		try {
			Object o = (Object)super.get(key);
			Class c = o.getClass();
			if ( o != null ) {
				if( c.isArray() ) {
					int length = Array.getLength(o);
					if ( length != 0 ) {
						for(int i=0; i<length;i++) {	
							Object tiem = Array.get(o, i);
							if (tiem == null ) vector.addElement("");
							else vector.addElement(tiem.toString());
						}
					}
				}			
				else 
					vector.addElement(o.toString());
			}
		}
		catch(Exception e) { System.out.println("getVector ����"); return vector; }
		return vector;
	}
	
	
	
	
	
	/**
	 * @param key java.lang.String
	 * @param value java.lang.String
	 */
	public void put(String key, String value) {
		super.put(key, value);
	}

	
	
	
	/**
	 * remove "," in string.
	 * @return java.lang.String
	 * @param s java.lang.String
	 */
	private static String removeComma(String s) {
		if ( s == null ) return null;
		if ( s.indexOf(",") != -1 ) {
			StringBuffer buf = new StringBuffer();
			for(int i=0;i<s.length();i++){
				char c = s.charAt(i);
				if ( c != ',') buf.append(c);
			}
			return buf.toString();
		}
		return s;
	}
	
	
	
	
	
	
	/**
	 * Box instance ���� ���� ��� 
	 *
	 * @return java.lang.String
	 */
	public synchronized String toString() {
		int max = size() - 1;
		StringBuffer buf = new StringBuffer();
		Enumeration keys = keys();
		Enumeration objects = elements();
		buf.append("{");
	
		for (int i = 0; i <= max; i++) {
			String key = keys.nextElement().toString();
			String value = null;
			Object o = objects.nextElement();
			if ( o == null ) value = "";
			else {
				Class  c = o.getClass();
				if( c.isArray() ) {
					int length = Array.getLength(o);
					if ( length == 0 ) 	value = "";
					else if ( length == 1 ) {
						Object item = Array.get(o, 0);
						if ( item == null ) value = "";
						else value = item.toString();
					}
					else {
						StringBuffer valueBuf = new StringBuffer();
						valueBuf.append("[");
						for ( int j=0;j<length;j++) {
							Object item = Array.get(o, j);
							if ( item != null ) valueBuf.append(item.toString());
							if ( j<length-1) valueBuf.append(",");
						}
						valueBuf.append("]");
						value = valueBuf.toString();
					}
				}
				else
					value = o.toString();
			}
		    buf.append(key + "=" + value);
		    if (i < max) buf.append(", ");
		}
		buf.append("}");
	
		return "Box["+name+"]=" + buf.toString();
	
	}
	
//	---------------------------------------------------------------------------------------
    public static String strReplace(String str, String map[][], boolean caseSensitive)
    {
        if(str == null || map == null)
            return str;
        String original = str;
        if(!caseSensitive)
            str = str.toUpperCase();
        StringBuffer sb = new StringBuffer();
        int nextCmpPoint = 0;
        do
        {
            int matchIndex = -1;
            int fastestMatchPoint = str.length();
            String from;
            for(int i = 0; i < map[0].length; i++)
            {
                from = map[0][i];
                //System.out.println("from[0]["+ i + "]:" + from  );
                if(!caseSensitive) from = from.toUpperCase();
                int matchPoint = str.indexOf(from, nextCmpPoint);
                if(matchPoint > -1 && matchPoint <= fastestMatchPoint)
                {
                    fastestMatchPoint = matchPoint;
                    matchIndex = i;
                }
            }

            sb.append(original.substring(nextCmpPoint, fastestMatchPoint));
            if(matchIndex < 0)
                break;
            from = map[0][matchIndex];
            String to = map[1][matchIndex];
            sb.append(to);
            nextCmpPoint = fastestMatchPoint + from.length();
        } while(nextCmpPoint < str.length());
        return sb.toString();
    }



    public static String strReplace(String str, String map[][])
    {
        return strReplace(str, map, false);
    }

    public static String strReplace(String str, String from, String to)
    {
        return strReplace(str, new String[][] {
            new String[] {
                from, to
            }
        });
    }

    public static String strReplaceIgnoreCase(String str, String[][] from )
    {
        return strReplace(str, from, false );
    }

	public static String strBlockSpecialTags(String source)
	{
    	String [][] oldString = { {"<html", "</html",   "<meta",  "<link",  "<head", "</head",   "<body",  "</body",  "<form",  "</form",  "<script",  "</script",  "<style",  "</style",  "script:",  "cookie", "document.",  "<",   ">"    ,"("    ,")"    ,"#"    ,"&"     },
                                  {"<hHTML", "</hHTML", "<hMETA", "<hLink", "<hHEAD", "</hHEAD", "<hBODY", "</hBODY", "<hFORM", "</hFORM", "<hSCRIPT", "</hSCRIPT", "<hSTYLE", "</hSTYLE", "script :", "cook!e", "document .", "&lt;","&gt;" ,"&#40;" ,"&#41;" ,"&#35;" ,"&#38;"        }   };
	    //return strReplaceIgnoreCase(source, oldString );
	    return Utility.fromKorean(strReplaceIgnoreCase(Utility.toKorean(source), oldString ));
	}
	public static String XmlSpecialTags(String source)
	{
    	String [][] oldString = { { "<",   ">"    ,"&"     },
                                  { "&lt;","&gt;" ,"&#38;"        }   };
	    //return strReplaceIgnoreCase(source, oldString );
	    return Utility.fromKorean(strReplaceIgnoreCase(Utility.toKorean(source), oldString ));
	}

	public static String strBST(String source)
	{
    	String [][] oldString = { {"<html", "</html",   "<meta",  "<link",  "<head", "</head",   "<body",  "</body",  "<form",  "</form",  "<script",  "</script",  "<style",  "</style",  "script:",  "cookie", "document.",  "<",   ">"    ,"("    ,")"    ,"#"    ,"&"     },
                                  {"<hHTML", "</hHTML", "<hMETA", "<hLink", "<hHEAD", "</hHEAD", "<hBODY", "</hBODY", "<hFORM", "</hFORM", "<hSCRIPT", "</hSCRIPT", "<hSTYLE", "</hSTYLE", "script :", "cook!e", "document .", "&lt;","&gt;" ,"&#40;" ,"&#41;" ,"&#35;" ,"&#38;"        }   };
	    return strReplaceIgnoreCase(source, oldString );
	}
	
//---------------------------------------------------------------------------------------
	

}
