package org.cnss.Classes;

public class Analysis extends Document {
    private static String laboratory;
    private static String description;

    public Analysis(String code, int payedAmount, int reimbursementRate) {
        super(code, payedAmount, reimbursementRate);
    }

    public static String getLaboratory() {
        return laboratory;
    }

    public static void setLaboratory(String laboratory) {
        Analysis.laboratory = laboratory;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Analysis.description = description;
    }


}
