package com.jih10157.Jihsk.VariableTriggers.varData;

import com.wizardscraft.VariableTriggers.VariableTriggers;
import com.wizardscraft.dataclass.VarData;
import org.bukkit.plugin.Plugin;

public class VTVar_1 implements VTVar {
    private Plugin vt;
    private VarData varData;
    public VTVar_1(Plugin vt){
        this.vt = vt;
    }

    @Override
    public boolean check() {
        try { com.wizardscraft.VariableTriggers.VariableTriggers.class.toString(); } catch (NoClassDefFoundError e) { return false; }
        this.varData = ((VariableTriggers)vt).varData;
        return true;
    }

    @Override
    public void setStringData(String objectname, String varname, String str) {
        this.varData.setObjectStringData(objectname, varname, str);
    }

    @Override
    public void delStringData(String objectname, String varname) {
        this.varData.delObjectStringData(objectname, varname);
    }

    @Override
    public String getStringData(String objectname, String varname) {
        return this.varData.getObjectStringData(objectname, varname);
    }

    @Override
    public void setIntData(String objectname, String varname, int i) {
        this.varData.setObjectIntData(objectname, varname, i);
    }

    @Override
    public void delIntData(String objectname, String varname) {
        this.varData.delObjectIntData(objectname, varname);
    }

    @Override
    public void addIntData(String objectname, String varname, int i) {
        this.varData.addObjectIntData(objectname, varname, i);
    }

    @Override
    public void subIntData(String objectname, String varname, int i) {
        this.varData.subObjectIntData(objectname, varname, i);
    }

    @Override
    public int getIntData(String objectname, String varname) {
        return varData.getObjectIntData(objectname, varname);
    }

    @Override
    public void setBooleanData(String objectname, String varname, boolean b) {
        this.varData.setObjectBooleanData(objectname, varname, b);
    }

    @Override
    public void delBooleanData(String objectname, String varname) {
        this.varData.delObjectBooleanData(objectname, varname);
    }

    @Override
    public boolean getBooleanData(String objectname, String varname) {
        return this.varData.getObjectBooleanData(objectname, varname);
    }
}
