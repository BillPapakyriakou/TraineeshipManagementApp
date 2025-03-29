package traineeship_app.mappers;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import traineeship_app.domainmodel.Committee;


@Repository
public interface CommitteeMapper extends JpaRepository<Committee, Long>{
    Committee findByUsername(String username);
}

