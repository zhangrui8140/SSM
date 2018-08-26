package business.table;

import org.apache.ibatis.annotations.orm.*;

public class HelloWorldTable {

    /**
     * ZKPZXXB.LGRPCD  法人代码.
     * @mbggenerated
     */
    @StringColumn(name="LGRPCD",comments="法人代码",length=4,nullable=true,defaultValue="'9999'")
    private String lgrpcd;

    public String getLgrpcd() {
        return lgrpcd;
    }

    public void setLgrpcd(String lgrpcd) {
        this.lgrpcd = lgrpcd == null ? "" : lgrpcd.trim();
    }


    @StringColumn(name="ISCHNL",comments="发卡渠道",length=2,nullable=true)
    private String ischnl;

    public String getIschnl() {
        return ischnl;
    }

    public void setIschnl(String ischnl) {
        this.ischnl = ischnl == null ? "" : ischnl.trim();
    }

    public HelloWorldTable() {
        super();
        lgrpcd = "9999";
        ischnl = "";
    }
    public void trim() {
        lgrpcd = lgrpcd == null ? "" : lgrpcd.trim();
        ischnl = ischnl == null ? "" : ischnl.trim();
    }

}
