package ir.saha.domain.enumeration;

import java.util.Arrays;

/**
 * The VaziatGozaresh enumeration.
 */
public enum VaziatGozaresh {
    AVALIE("اولیه"), MODIR("مدیر"), MOAVENAT("معاونت"), HEYAT_RAESE_SAZMAN("هیت رییشه سازمان"), HEYATRAESE_AJA_NAHAE("نهایی"), EBLAGH_GOZARESH_BEYEGAN_HESABRESI_SHAVANDE("ابلاغ گزارش به یگان");

    private String value;
    VaziatGozaresh(String  value) {
        this.value=value;
    }

   public static String getByEnum(VaziatGozaresh gozaresh){
       return Arrays.stream(VaziatGozaresh.values()).filter(v->v.equals(gozaresh)).findFirst().get().getValue();
   }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
