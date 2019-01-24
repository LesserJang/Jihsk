package com.jih10157.Jihsk.VariableTriggers.varData;

public interface VTVar {
    boolean check();
    void setStringData(String objectname, String varname, String str);
    void delStringData(String objectname, String varname);
    String getStringData(String objectname, String varname);
    void setIntData(String objectname, String varname, int i);
    void delIntData(String objectname, String varname);
    void addIntData(String objectname, String varname, int i);
    void subIntData(String objectname, String varname, int i);
    int getIntData(String objectname, String varname);
    void setBooleanData(String objectname, String varname, boolean b);
    void delBooleanData(String objectname, String varname);
    boolean getBooleanData(String objectname, String varname);
}
