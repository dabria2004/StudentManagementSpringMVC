package studentmanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import studentmanagement.dto.UserRequestDTO;
import studentmanagement.dto.UserResponseDTO;

@Service("userDao")

public class UserDAO {
		public static Connection con=null;
		static {
			con=MyConnection.getConnection();
			System.out.println("Got Connection...");
		}
		public int insertData(UserRequestDTO dto) {
			int result=0;
			String sql="insert into user(user_id,user_name,user_password) values(?,?,?)";	
			try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1,dto.getUser_id());
					ps.setString(2,dto.getUser_name());
					ps.setString(3,dto.getUser_password());
					result=ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Database error in inserthing");
			}
			return result;
		}
		
		public int updateData(UserRequestDTO dto) {
			int result=0;
			String sql="update user set user_name=?,user_password=? where user_id=?";	
			try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1,dto.getUser_name());
					ps.setString(2,dto.getUser_password());
					ps.setString(3,dto.getUser_id());
					result=ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Database error in updating");
			}
			return result;
		}
		
		public int deleteData(UserRequestDTO dto) {
			int result=0;
			String sql="delete from user where user_id=?";	
			try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1,dto.getUser_id());
					ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Database error in deleting");
			}
			return result;
		}
		
		public UserResponseDTO selectOneByid(String id) {
			String sql="select * from user where user_id=?";
			UserResponseDTO res=new UserResponseDTO();
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					
					res.setUser_id(rs.getString("user_id"));
					res.setUser_name(rs.getString("user_name"));
					res.setUser_password(rs.getString("user_password"));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return res;		
		}
		
		public UserResponseDTO selectOneByname(String name) {
			String sql="select * from user where user_name=?";
			UserResponseDTO res=new UserResponseDTO();
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,name);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					
					res.setUser_id(rs.getString("user_id"));
					res.setUser_name(rs.getString("user_name"));
					res.setUser_password(rs.getString("user_password"));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return res;		
		}
		
		public ArrayList<UserResponseDTO> selectAll(){
			ArrayList<UserResponseDTO> list=new ArrayList();
			String sql="select * from user";		
			try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserResponseDTO res=new UserResponseDTO();
				res.setUser_id(rs.getString("user_id"));
				res.setUser_name(rs.getString("user_name"));
				res.setUser_password(rs.getString("user_password"));
				list.add(res);
	}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		
		public UserResponseDTO selectOne(UserRequestDTO dto) {
			String sql = "select * from user where user_id=?";
			UserResponseDTO res = new UserResponseDTO();
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, dto.getUser_id());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					res.setUser_id(rs.getString("user_id"));
					res.setUser_name(rs.getString("user_name"));
					res.setUser_password(rs.getString("user_password"));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return res;
		}
		
		public boolean check(String user_id, String user_password) {

			String sql = "select * from user where user_id=? && user_password=?";
			try {
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, user_id);
				st.setString(2, user_password);
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			return false;
		}
		public UserResponseDTO selectOneById(String id) {
			UserResponseDTO res=new UserResponseDTO();
			String sql="select * from user where user_id=?";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					
					res.setUser_id(rs.getString("user_id"));
					res.setUser_name(rs.getString("user_name"));
					res.setUser_password(rs.getString("user_password"));
					
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return res;		
		}
		
		public boolean checkId(String id) {
			String sql = "select * from user where user_id=?";
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
