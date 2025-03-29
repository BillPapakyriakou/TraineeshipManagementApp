package traineeship_app.domainmodel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Committee extends User{

    @Column(nullable = false)
    private String committeeMemberName;


    public Committee(String committeeMemberName){
        this.committeeMemberName = committeeMemberName;
    }

    public Committee(Long id, String username, String password, Role role, String committeeMemberName){
        super(id, username, password, role);
        this.committeeMemberName = committeeMemberName;
    }

    public Committee(){

    }


    public String getCommitteeMemberName(){
        return committeeMemberName;
    }

    public void setCommitteeMemberName(String committeeMemberName){
        this.committeeMemberName = committeeMemberName;
    }
}
