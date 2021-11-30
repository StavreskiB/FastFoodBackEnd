package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "templatetype")
public class TemplateType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdTemplateType;
    private String Template;

    public TemplateType() {
    }


    public TemplateType(String template) {
        Template = template;
    }

    public Integer getIdTemplateType() {
        return IdTemplateType;
    }

    public void setIdTemplateType(Integer idTemplateType) {
        IdTemplateType = idTemplateType;
    }

    public String getTemplate() {
        return Template;
    }

    public void setTemplate(String template) {
        Template = template;
    }
}

