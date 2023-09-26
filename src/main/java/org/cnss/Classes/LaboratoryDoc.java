package org.cnss.Classes;

enum Category {
    MEDICAMENT, MALADIE, ESTHETIQUE;
}

enum Description {
    RADIO, ANALYSE, SCANNER;
}

public class LaboratoryDoc extends Document {
    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private Description description;
    private String laboratory;
    private Category category;

    public LaboratoryDoc(String code, int payedAmount, int reimbursementRate, Description description, String laboratory, Category category) {
        super(code, payedAmount, reimbursementRate);
        this.description = description;
        this.laboratory = laboratory;
        this.category = category;
    }
}
