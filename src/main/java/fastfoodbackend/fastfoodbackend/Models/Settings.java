package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;
import java.sql.Time;
@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdSettings;
    private Integer CompanyId;
    private String FirstShiftStart;
    private String SecondShiftStart;
    private Integer LimitPiece;
    private Integer LimitDays;

    public Settings(){}

    public Settings(Integer companyId, String firstShiftStart, String secondShiftStart, Integer limitPiece, Integer limitDays) {
        CompanyId = companyId;
        FirstShiftStart = firstShiftStart;
        SecondShiftStart = secondShiftStart;
        LimitPiece = limitPiece;
        LimitDays = limitDays;
    }

    public Integer getIdSettings() {
        return IdSettings;
    }

    public void setIdSettings(Integer idSettings) {
        IdSettings = idSettings;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }

    public String getFirstShiftStart() {
        return FirstShiftStart;
    }

    public void setFirstShiftStart(String firstShiftStart) {
        FirstShiftStart = firstShiftStart;
    }

    public String getSecondShiftStart() {
        return SecondShiftStart;
    }

    public void setSecondShiftStart(String secondShiftStart) {
        SecondShiftStart = secondShiftStart;
    }

    public Integer getLimitPiece() {
        return LimitPiece;
    }

    public void setLimitPiece(Integer limitPiece) {
        LimitPiece = limitPiece;
    }

    public Integer getLimitDays() {
        return LimitDays;
    }

    public void setLimitDays(Integer limitDays) {
        LimitDays = limitDays;
    }
}

