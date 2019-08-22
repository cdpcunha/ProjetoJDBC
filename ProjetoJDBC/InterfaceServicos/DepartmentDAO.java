package InterfaceServicos;

import java.util.List;

import ObjetosEntidades.Department;

public interface DepartmentDAO {
	void insert(Department obj);
	void deleteByID(Department obj);
	Department findByID(int id);
	List<Department> findAll();
	void updateDepartment(Integer id);
	}
