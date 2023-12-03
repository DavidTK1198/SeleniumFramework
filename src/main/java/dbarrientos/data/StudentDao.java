package dbarrientos.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao extends DataBase{
	public StudentDao(){
		super();
	}
	private static final String CreateStudent = "insert into student (advisor_id, stud_name, tot_cred, stud_dept) values(?,?,?,?)";
	private static final String findByName= "select * from student where stud_name=?";
	private static final String findAll ="select * from student";

	public Boolean create(Map<String, String> student) throws Exception {
		connect();
		PreparedStatement stm = prepareStatement(CreateStudent);
		stm.setInt(1, Integer.parseInt(student.get("advisor_id")));
		stm.setString(2, student.get("stud_name"));
		stm.setInt(3, Integer.parseInt(student.get("tot_cred")));
		stm.setString(4, student.get("stud_dept"));
		int count = executeUpdate(stm);
		if (count == 0) {
			disconnect();
			throw new Exception("The student already exist");
		}
		disconnect();
		return true;
	}

	public List<Map<String, String>> findAll() throws ClassNotFoundException, SQLException {
		connect();
		List<Map<String, String>> r = new ArrayList<>();
		try {
			PreparedStatement stm = prepareStatement(findAll);
			ResultSet rs = executeQuery(stm);
			while (rs.next()) {
				r.add(from(rs));
			}
		} catch (SQLException ex) {
			disconnect();
		}
		disconnect();
		return r;
	}

	public Map<String, String> findByName(String name) throws Exception {
		connect();
		PreparedStatement stm = prepareStatement(findByName);
		stm.setString(1, name);
		ResultSet rs = executeQuery(stm);
		if (rs.next()) {
			return from(rs);
		} else {
			disconnect();
			return null;
		}
	}

	public Map<String, String> from(ResultSet rs) {
		try {
			Map<String, String> r = new HashMap<String, String>();
			r.put("advisor", Integer.toString(rs.getInt("advisor_id")));
			r.put("name",rs.getString("stud_name"));
			r.put("credits",Integer.toString(rs.getInt("tot_cred")));
			r.put("deparment",rs.getString("stud_dept"));

			return r;
		} catch (SQLException ex) {
			return null;
		}
	}
}
