package unipi.library.classes;
public class User {
   // Pedia
   private int id;
   private String firstName;
   private String lastName;
   private String birthDate;
   private String phone;
   private String email;
  
   public User(int id, String firstName, String lastName, String birthDate, String phone, String email) {  //Constructor
       this.id = id;
       this.firstName = firstName;
       this.lastName = lastName;
       this.birthDate = birthDate;
       this.phone = phone;
       this.email = email;
   }
   
   // --- Getters & Setters ---
   public int getId() {
       return id;
   }
   public void setId(int id) {
       this.id = id;
   }
   public String getFirstName() {
       return firstName;
   }
   public void setFirstName(String firstName) {
       this.firstName = firstName;
   }
   public String getLastName() {
       return lastName;
   }
   public void setLastName(String lastName) {
       this.lastName = lastName;
   }
   public String getBirthDate() {
       return birthDate;
   }
   public void setBirthDate(String birthDate) {
       this.birthDate = birthDate;
   }
   public String getPhone() {
       return phone;
   }
   public void setPhone(String phone) {
       this.phone = phone;
   }
   public String getEmail() {
       return email;
   }
   public void setEmail(String email) {
       this.email = email;
   }
   
   public String getFullName() { //methodos pou ftaixnei to plhres onomateponymo tou xrhsth
       return firstName + " " + lastName;
   }
   // --- toString ---
   @Override
   public String toString() {
       return "User{" +
               "id=" + id +
               ", fullName=" + getFullName() +
               ", birthDate='" + birthDate + '\'' +
               ", phone='" + phone + '\'' +
               ", email='" + email + '\'' +
               '}';
   }
}



