package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;

@Entity
@Table(name = "template")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdTemplate;

    @ManyToOne
    @JoinColumn(name="IdTemplateType", nullable=false)
    private TemplateType IdTemplateType;

    private String Template;

    public Template() {
    }


    public Template(TemplateType idTemplateType, String template) {
        IdTemplateType = idTemplateType;
        Template = template;
    }

    public Integer getIdTemplate() {
        return IdTemplate;
    }

    public void setIdTemplate(Integer idTemplate) {
        IdTemplate = idTemplate;
    }

    public TemplateType getIdTemplateType() {
        return IdTemplateType;
    }

    public void setIdTemplateType(TemplateType idTemplateType) {
        IdTemplateType = idTemplateType;
    }

    public String getTemplate() {
        return Template;
    }

    public void setTemplate(String template) {
        Template = template;
    }
}
