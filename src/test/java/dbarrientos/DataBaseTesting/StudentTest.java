package dbarrientos.DataBaseTesting;

import dbarrientos.data.StudentDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StudentTest {
	private static final StudentDao dbAccess = new StudentDao();

	@Test
	public void findAllStudents() throws ClassNotFoundException, SQLException {

		dbAccess.findAll();
	}

	@Test(dataProvider = "getData")
	public void insertStudent(HashMap<String, String> input) throws Exception {
		Boolean created = dbAccess.create(input);
		Assert.assertTrue(created);

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = new ArrayList();
		data.add(createStudentMap("1", "Michael Potter", "14", "Computer Science"));
		data.add(createStudentMap("2", "Daniel Luis", "18", "Computer Science"));
		data.add(createStudentMap("2", "Giorgio Giovanna", "20", "Computer Science"));
		data.add(createStudentMap("6", "Dio Brando", "15", "Economics"));
		Object[][] result = new Object[data.size()][];
		for (int i = 0; i < data.size(); i++) {
			result[i] = new Object[] { data.get(i) };
		}

		return result;
	}

	private static HashMap<String, String> createStudentMap(String advisorId, String studName, String totCred,String studDept) {
		HashMap<String, String> studentMap = new HashMap<String, String>();
		studentMap.put("advisor_id", advisorId);
		studentMap.put("stud_name", studName);
		studentMap.put("tot_cred", totCred);
		studentMap.put("stud_dept", studDept);
		return studentMap;
	}
}
