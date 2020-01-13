    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJOs;

/**
 *
 * @author Vinod
 */
public class User {

    String name, enrollment, email, password;
    int semester;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String enrollment, String email, String password, int semester) {
        this.name = name;
        this.enrollment = enrollment;
        this.email = email;
        this.password = password;
        this.semester = semester;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
    
    

}
