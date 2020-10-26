package co.jamong.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.jamong.board.vo.MemberVO;

public class MemberDao extends DAO {
	private PreparedStatement psmt; // sql 명령문 실행
	private ResultSet rs; // select 후 결과 셋 받음
	private MemberVO vo;

	// final 상수들은 대부분 대문자로 해줘야한다. -> ctrl + shift + x 하면 대문자로 바뀜
	// 외부에서 다른 누군가 쿼리를 수정하지 못하도록 상수로 선언.
	private final String SELECT_ALL = "SELECT * FROM MEMBER ORDER BY 1";
	private final String SELECT = "SELECT * FROM MEMBER WHERE ID= ? AND PASSWORD=?";
	private final String INSERT = "INSERT INTO MEMBER(ID, NAME, PASSWORD, ADDRESS, TEL) VALUES(?,?,?,?,?)";
	private final String UPDATE = "UPDATE MEMBER SET NAME=?, PASSWORD=?, ADDRESS=?, TEL=? WHERE ID=?";
	private final String DELETE = "DELETE FROM MEMBER WHERE ID=?";

	// 여러행의 데이터를 다 가져오겠따.
	public List<MemberVO> selectAll() { // 멤버 리스트 전체를 가져오는 메소드
		List<MemberVO> list = new ArrayList<MemberVO>();

		try {
			// 상위 DAO가 conn을 가지고 있어서 별도로 선언 안해줘도 된다.
			// conn 객체에 내가 처리할 sql을 담아 DBMS에게 보낸다.
			psmt = conn.prepareStatement(SELECT_ALL); // DBMS에게 sql 전달
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id")); // db 칼럼명이랑 같아야함
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setAuthor(rs.getString("author"));
				vo.setEnterdate(rs.getDate("enterdate"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	public MemberVO select(MemberVO vo) { // 한 행을 검색할 때
		try {
			// conn : 드라이브 매니저를 통해 생성된것. sql을 보낸다.
			psmt = conn.prepareStatement(SELECT);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());

			rs = psmt.executeQuery();
			// 한 행만 들어오니까 if문
			if (rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setAuthor(rs.getString("author"));
				vo.setEnterdate(rs.getDate("enterdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return vo;
	}

	public int insert(MemberVO vo) { // 입력
		int n = 0;
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getTel());
			psmt.setString(5, vo.getId());

			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	public int update(MemberVO vo) { // 수정
		int n = 0;

		try {
			psmt = conn.prepareStatement(UPDATE);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getTel());
			psmt.setString(5, vo.getId());
			
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	public int delete(MemberVO vo) { // 삭제
		int n = 0;
		try {
			psmt = conn.prepareStatement(DELETE);
			psmt.setString(1, vo.getId());
			
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	// 모든 동작 후 연결 끊어주기
	private void close() {
		try {
			// 열어준거의 반대방향으로 닫아준다.
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
