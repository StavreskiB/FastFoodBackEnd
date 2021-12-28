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
    private Integer LimitGr;

    public Settings(){}

    public Settings(Integer companyId, String firstShiftStart, String secondShiftStart, Integer limitPiece, Integer limitGr) {
        CompanyId = companyId;
        FirstShiftStart = firstShiftStart;
        SecondShiftStart = secondShiftStart;
        LimitPiece = limitPiece;
        LimitGr = limitGr;
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

    public Integer getLimitGr() {
        return LimitGr;
    }

    public void setLimitGr(Integer limitGr) {
        LimitGr = limitGr;
    }
}

