import entity.Student;
import service.StudentService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EditStudentsView implements Serializable {
    private String name;
    private String email;
    private String contactNumber;
    private String program;
    private String dob;

    @EJB
    private StudentService studentService;
    private transient Student studentToUpdate;

    public void populateView(long studentId) {
        studentToUpdate = studentService.findById(studentId);
        this.setName(studentToUpdate.getName());
        this.setEmail(studentToUpdate.getEmail());
        this.setContactNumber(studentToUpdate.getContactNumber());
        this.setProgram(studentToUpdate.getProgram());
        this.setDob(studentToUpdate.getDoB());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String  contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public Student getStudentToUpdate() {
        return studentToUpdate;
    }

    public void setStudentToUpdate(Student studentToUpdate) {
        this.studentToUpdate = studentToUpdate;
    }
    public String save(){
        Student createdStudent = new Student (name, email, contactNumber, program, dob);
        if (studentToUpdate != null){
            createdStudent.setId (studentToUpdate.getId());
            studentService.update(createdStudent);}
            else {
                studentService.create (createdStudent);
        }
        nullifyFields();
        return "/editStudent.xhtml?faces-redirect=true";
        }
        private void nullifyFields (){
        studentToUpdate = null;
        this.setName(null);
        this.setEmail(null);
        this.setContactNumber(null);
        this.setProgram(null);
        this.setDob(null);

        }
    }

