package com.fpoly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fpoly.entity.FavoriteVideo;
import com.fpoly.utils.JpaUtils;

public class DAO_Favorite {
	public void create(FavoriteVideo FavoriteVideo) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// Bắt đầu transaction
			tran.begin();
			// Lưu FavoriteVideo vào CSDL
			em.persist(FavoriteVideo);
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

	public void update(FavoriteVideo FavoriteVideo) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạc transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// bắt đầu transaction
			tran.begin();
			// update FavoriteVideo vào CSDL
			em.merge(FavoriteVideo);
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
		String jqpl = "Delete from FavoriteVideo";
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

	public void delete(int id) throws Exception {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// Bắt đầu transaction
			tran.begin();
			// update FavoriteVideo vào CSDL
			FavoriteVideo favoriteVideo = em.find(FavoriteVideo.class, id);
			// Nếu tìm thấy thì xóa, không thì không tồn tại
			if (favoriteVideo != null) {
				em.remove(favoriteVideo);
			} else {
				throw new Exception("This FavoriteVideo does not exist!");
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

	public FavoriteVideo login(String userName, String password) {
		try {
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = JpaUtils.getEntityManager();
			// Lấy câu lệnh findAll có sẵn lúc tạo lớp FavoriteVideo
			String sql = "SELECT a FROM FavoriteVideo a WHERE a.userName = :userName AND a.password = :password";
			TypedQuery<FavoriteVideo> query = em.createQuery(sql, FavoriteVideo.class);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			// Trả về kết quả truy vấn

			return query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Lỗi Login");
		}
		return null;
	}

	public List<FavoriteVideo> findAll() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Lấy câu lệnh findAll có sẵn lúc tạo lớp FavoriteVideo
		String sql = "SELECT FavoriteVideo FROM FavoriteVideo FavoriteVideo";
		TypedQuery<FavoriteVideo> query = em.createQuery(sql, FavoriteVideo.class);
		// Trả về kết quả truy vấn

		return query.getResultList();
	}

	public List<FavoriteVideo> getTop5() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<FavoriteVideo> query = em
				.createQuery("SELECT FavoriteVideo FROM FavoriteVideo FavoriteVideo ORDER BY RAND()",
						FavoriteVideo.class)
				.setMaxResults(5);

		// Trả về kết quả
		return query.getResultList();
	}

	public FavoriteVideo findOne(String id, String password) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM FavoriteVideo o WHERE o.id = :id AND o.password = :password";
		// tạo đối tượng truy vấn
		TypedQuery<FavoriteVideo> query = em.createQuery(jqpl, FavoriteVideo.class);

		query.setParameter("id", id);
		query.setParameter("password", password);
		// Trả về kết quả
		return query.getSingleResult();
	}

	public FavoriteVideo getByVIDAndAuID(int videoID, int authorID) {

		try {
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = JpaUtils.getEntityManager();
			// Tạo câu truy vấn
			String jqpl = "SELECT o FROM FavoriteVideo o WHERE o.video.videoID = :videoID AND o.account.authorID = :authorID";
			// tạo đối tượng truy vấn
			TypedQuery<FavoriteVideo> query = em.createQuery(jqpl, FavoriteVideo.class);

			query.setParameter("videoID", videoID);
			query.setParameter("authorID", authorID);
			// Trả về kết quả
			return query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Lỗi Login");
		}
		return null;

	}

	public FavoriteVideo findByID(int id) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM FavoriteVideo o WHERE o.id = :id";

		// Tạo đối tượng truy vấn
		TypedQuery<FavoriteVideo> query = em.createQuery(jqpl, FavoriteVideo.class);

		query.setParameter("id", id);

		// Trả về kết quả truy vấn
		return query.getSingleResult();
	}

	public List<FavoriteVideo> getAllVideo(int authorID) {
		try {
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = JpaUtils.getEntityManager();
			// Tạo câu truy vấn
			String jqpl = "SELECT o FROM FavoriteVideo o WHERE o.account.authorID = :authorID";

			// Tạo đối tượng truy vấn
			TypedQuery<FavoriteVideo> query = em.createQuery(jqpl, FavoriteVideo.class);

			query.setParameter("authorID", authorID);

			// Trả về kết quả truy vấn
			return query.getResultList();
		} catch (Exception e) {
		}
		return null;

	}

	public List<FavoriteVideo> findByRole(boolean role) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "SELECT o from FavoriteVideo o WHERE o.admin = :role";
		// Tạo đối tượng truy vấn
		TypedQuery<FavoriteVideo> query = em.createQuery(jqpl, FavoriteVideo.class);
		query.setParameter("role", role);
		// Trả về kết quả truy vấn
		return query.getResultList();
	}

	public List<FavoriteVideo> findByKeyWord(String keyword) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM FavoriteVideo o WHERE o.fullname like :keyword";

		// Tạo đối tượng truy vấn
		TypedQuery<FavoriteVideo> query = em.createQuery(jqpl, FavoriteVideo.class);
		query.setParameter("keyword", "%" + keyword + "%");
		// Trả về kết quả truy vấn
		return query.getResultList();
	}
}
