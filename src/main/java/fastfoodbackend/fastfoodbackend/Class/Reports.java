package fastfoodbackend.fastfoodbackend.Class;

public class Reports {

    private String Employee;
    private String Date;
    private String Shift;
    private String Price;

    public Reports(){};

    public Reports(String employee, String date, String shift, String price) {
        Employee = employee;
        Date = date;
        Shift = shift;
        Price = price;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getShift() {
        return Shift;
    }

    public void setShift(String shift) {
        Shift = shift;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
