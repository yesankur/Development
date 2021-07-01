package em.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import em.app.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

}
