package com.fpoly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fpoly.entity.History;
import com.fpoly.utils.JpaUtils;

public class DAO_History {
	public void create(History history) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// Bắt đầu transaction
			tran.begin();
			// Lưu History vào CSDL
			em.persist(history);
			// Chấp nhận kết quả thao tác
			tran.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Hủy thao tác khi có exception
			tran.rollback();
			System.out.println("Lỗi thêm dữ liệu");
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(History history) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạc transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// bắt đầu transaction
			tran.begin();
			// update History vào CSDL
			em.merge(history);
			// Chấp nhận kết quả thao tác
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Hủy thao tác khi có exception
			tran.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void deleteAll() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();
		// Tạo câu truy vấn
		String jqpl = "Delete from History";
		// Tạo đối tượng truy vấn
		try {
			tran.begin();
			em.createQuery(jqpl).executeUpdate();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}

	public void delete(String id) throws Exception {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// Bắt đầu transaction
			tran.begin();
			// update History vào CSDL
			History History = em.find(History.class, id);
			// Nếu tìm thấy thì xóa, không thì không tồn tại
			if (History != null) {
				em.remove(History);
			} else {
				throw new Exception("This History does not exist!");
			}
			// Chấp nhận kết quả thao tác
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Hủy thao tác khi có exception
			tran.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public List<History> findAll() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Lấy câu lệnh findAll có sẵn lúc tạo lớp History
		String sql = "SELECT History FROM History History";
		TypedQuery<History> query = em.createQuery(sql, History.class);
		// Trả về kết quả truy vấn

		return query.getResultList();
	}

	public History getHistoryByAuthorIDAndVideoID(int authorID, int videoID) {
		try {
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = JpaUtils.getEntityManager();
			// Lấy câu lệnh findAll có sẵn lúc tạo lớp History
			String sql = "FROM History h WHERE h.author.authorID = :authorID AND h.video.videoID = :videoID";
			TypedQuery<History> query = em.createQuery(sql, History.class);
			query.setParameter("authorID", authorID);
			query.setParameter("videoID", videoID);
			// Trả về kết quả truy vấn

			return query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Lỗi Login");
		}
		return null;
	}

	public List<History> getTop5() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<History> query = em.createQuery("SELECT History FROM History History ORDER BY RAND()", History.class)
				.setMaxResults(5);

		// Trả về kết quả
		return query.getResultList();
	}

	public History findOne(String id, String password) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM History o WHERE o.id = :id AND o.password = :password";
		// tạo đối tượng truy vấn
		TypedQuery<History> query = em.createQuery(jqpl, History.class);

		query.setParameter("id", id);
		query.setParameter("password", password);
		// Trả về kết quả
		return query.getSingleResult();
	}

	public History findByID(int id) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM History o WHERE o.id = :id";

		// Tạo đối tượng truy vấn
		TypedQuery<History> query = em.createQuery(jqpl, History.class);

		query.setParameter("id", id);

		// Trả về kết quả truy vấn
		return query.getSingleResult();
	}

	public List<History> findByRole(boolean role) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "SELECT o from History o WHERE o.admin = :role";
		// Tạo đối tượng truy vấn
		TypedQuery<History> query = em.createQuery(jqpl, History.class);
		query.setParameter("role", role);
		// Trả về kết quả truy vấn
		return query.getResultList();
	}
	
	public List<History> getAllHistoryByAuthorID(int authorID) {
		try {
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = JpaUtils.getEntityManager();
			String jqpl = "SELECT o from History o WHERE o.author.authorID = :authorID";
			// Tạo đối tượng truy vấn
			TypedQuery<History> query = em.createQuery(jqpl, History.class);
			query.setParameter("authorID", authorID);
			// Trả về kết quả truy vấn
			return query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public List<History> findByKeyWord(String keyword) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM History o WHERE o.fullname like :keyword";

		// Tạo đối tượng truy vấn
		TypedQuery<History> query = em.createQuery(jqpl, History.class);
		query.setParameter("keyword", "%" + keyword + "%");
		// Trả về kết quả truy vấn
		return query.getResultList();
	}
}
