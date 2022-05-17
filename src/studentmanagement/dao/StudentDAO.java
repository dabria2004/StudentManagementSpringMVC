package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import studentmanagement.dto.StudentRequestDTO;
import studentmanagement.dto.StudentResponseDTO;

@Service("studentDao")

public class StudentDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
		System.out.println("Got Connection...");
	}
	public int insertData(StudentRequestDTO dto) {
		int result=0;
		String sql="insert into student(student_id,student_name,class_name,register_date,status) values(?,?,?,?,?)";	
		try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, dto.getStudent_id());
				ps.setString(2, dto.getStudent_name());
				ps.setString(3, dto.getClass_name());
				ps.setString(4, dto.getDate());
				ps.setString(5, dto.getStatus());
				result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error in inserthing student data.");
		}
		return result;
	}
	
	public int updateData(StudentRequestDTO dto) {
		String sql = "update student set student_name=?, class_name=?, register_date=?, status=? where student_id=?";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStudent_name());
			ps.setString(2, dto.getClass_name());
			ps.setString(3, dto.getDate());
			ps.setString(4, dto.getStatus());
			ps.setString(5, dto.getStudent_id());

			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Database error in updating student data.");
		}
		return i;
	}
	
	public ArrayList<StudentResponseDTO> selectAll() {
		ArrayList<StudentResponseDTO> list = new ArrayList<>();
		String sql = "select * from student";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO res = new StudentResponseDTO();
				res.setStudent_id(rs.getString("student_id"));
				res.setStudent_id(rs.getString("student_name"));
				res.setClass_name(rs.getString("class_name"));
				res.setDate(rs.getDate("register_date").toLocalDate());
				res.setStatus(rs.getString("status"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println("Database error in SDAO selectAll.");
		}
		return list;
	}
	
	public ArrayList<StudentResponseDTO> searchById(String id) {
		ArrayList<StudentResponseDTO> list = new ArrayList<>();
		String sql = "select * from student where student_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO res = new StudentResponseDTO();
				res.setStudent_id(rs.getString("student_id"));
				res.setStudent_name(rs.getString("student_name"));
				res.setClass_name(rs.getString("class_name"));
				res.setDate(rs.getDate("register_date").toLocalDate());
				res.setStatus(rs.getString("status"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public ArrayList<StudentResponseDTO> searchByName(String student_name) {
		ArrayList<StudentResponseDTO> list = new ArrayList<>();
		String sql = "select * from student where student_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO res = new StudentResponseDTO();
				res.setStudent_id(rs.getString("student_id"));
				res.setStudent_name(rs.getString("student_name"));
				res.setClass_name(rs.getString("class_name"));
				res.setDate(rs.getDate("register_date").toLocalDate());
				res.setStatus(rs.getString("status"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public ArrayList<StudentResponseDTO> searchByClass(String class_name) {
		ArrayList<StudentResponseDTO> list = new ArrayList<>();
		String sql = "select * from student where class_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, class_name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO res = new StudentResponseDTO();
				res.setStudent_id(rs.getString("student_id"));
				res.setStudent_name(rs.getString("student_name"));
				res.setClass_name(rs.getString("class_name"));
				res.setDate(rs.getDate("register_date").toLocalDate());
				res.setStatus(rs.getString("status"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public int deleteData(StudentRequestDTO dto) {
		int result=0;
		String sql="delete from student where student_id=?";	
		try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,dto.getStudent_id());
				ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error in deleting");
		}
		return result;
	}
	
	public StudentResponseDTO selectById(String id) {
		String sql = "select * from student where student_id=?";
		StudentResponseDTO res = new StudentResponseDTO();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setStudent_id(rs.getString("student_id"));
				res.setStudent_name(rs.getString("student_name"));
				res.setClass_name(rs.getString("class_name"));
				res.setDate(rs.getDate("register_date").toLocalDate());
				res.setStatus(rs.getString("status"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
	public boolean checkId(String id) {
		String sql = "select * from student where student_id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
