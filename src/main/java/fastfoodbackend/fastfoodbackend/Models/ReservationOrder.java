package fastfoodbackend.fastfoodbackend.Models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "reservationorder")
public class ReservationOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdReservationType;
    private String Name;
    private Date Reminder;

    public ReservationOrder() {
    }

    public ReservationOrder(Integer idReservationType, String name, Date reminder) {
        IdReservationType = idReservationType;
        Name = name;
        Reminder = reminder;
    }

    public Integer getIdReservationType() {
        return IdReservationType;
    }

    public void setIdReservationType(Integer idReservationType) {
        IdReservationType = idReservationType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getReminder() {
        return Reminder;
    }

    public void setReminder(Date reminder) {
        Reminder = reminder;
    }
}
