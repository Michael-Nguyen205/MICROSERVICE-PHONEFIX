package spring.boot.commonservice.ENUM;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Templates {
    DISCOUNT_TEMPLATE("discountTemplate",""),
    INFO_FIX_PHONE_TEMPLATE("InfoFixPhoneTemplate","EmailInforFixPhoneService"),
    SUCCESS_FIX_PHONE_TEMPLATE("successFixPhoneTemplate","");


    private  String templateName;
    private  String serviceName;

//    templates(String templateName) {
//        this.templateName = templateName;
//    }


    public static Templates fromTemplateNameToGetService(String templateName) {
        for (Templates template : Templates.values()) {
            if (template.getTemplateName().equals(templateName)) {
                return template;
            }
        }
        throw new IllegalArgumentException("Unknown template: " + templateName);
    }



//    public String getTemplateName() {
//        return templateName;
//    }
}
