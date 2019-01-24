package com.jih10157.Jihsk.VariableTriggers.varData;

import com.github.lyokofirelyte.VariableTriggers.VTVars;
import com.github.lyokofirelyte.VariableTriggers.VariableTriggers;
import com.jih10157.Jihsk.Main;
import org.bukkit.plugin.Plugin;

public class VTVar_2 implements VTVar {
    private Plugin vt;
    private VTVars vtVars;
    public VTVar_2(Plugin vt){
        this.vt = vt;
    }
    @Override
    public boolean check() {
        try{ com.github.lyokofirelyte.VariableTriggers.VariableTriggers.class.toString(); } catch (NoClassDefFoundError e) { return false; }
        this.vtVars = ((VariableTriggers)vt).vars;
        return true;
    }

    @Override
    public void setStringData(String objectname, String varname, String str) {
        this.vtVars.set(objectname+"."+varname, str);
    }

    @Override
    public void delStringData(String objectname, String varname) {
        this.vtVars.remove(objectname+"."+varname);
    }

    @Override
    public String getStringData(String objectname, String varname) {
        if (this.vtVars.containsKey(objectname+"."+varname)) {
            return this.vtVars.getStr(objectname+"."+varname);
        }
        return null;
    }

    @Override
    public void setIntData(String objectname, String varname, int i) {
        this.vtVars.set(objectname+"."+varname, i);
    }

    @Override
    public void delIntData(String objectname, String varname) {
        this.vtVars.remove(objectname+"."+varname);
    }

    @Override
    public void addIntData(String objectname, String varname, int i) {
        this.vtVars.set(objectname+"."+varname, getIntData(objectname, varname)+i);
    }

    @Override
    public void subIntData(String objectname, String varname, int i) {
        this.vtVars.set(objectname+"."+varname, getIntData(objectname, varname)-i);
    }

    @Override
    public int getIntData(String objectname, String varname) {
        if (this.vtVars.containsKey(objectname+"."+varname)) {
            return this.vtVars.getInt(objectname+"."+varname);
        }
        return 0;
    }

    @Override
    public void setBooleanData(String objectname, String varname, boolean b) {
        this.vtVars.set(objectname+"."+varname, b);
    }

    @Override
    public void delBooleanData(String objectname, String varname) {
        this.vtVars.remove(objectname+"."+varname);
    }

    @Override
    public boolean getBooleanData(String objectname, String varname) {
        if (this.vtVars.containsKey(objectname+"."+varname)) {
            return this.vtVars.getBool(objectname+"."+varname);
        }
        return false;
    }
}
