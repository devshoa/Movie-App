package com.fpoly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.fpoly.entity.Video;
import com.fpoly.utils.JpaUtils;

public class DAO_Video {
	public void create(Video Video) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// Bắt đầu transaction
			tran.begin();
			// Lưu Video vào CSDL
			em.persist(Video);
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

	public List<Video> searchByName(String nameVideo) {
		try {
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = JpaUtils.getEntityManager();
			// Lấy câu lệnh findAll có sẵn lúc tạo lớp Video
			String sql = "SELECT v FROM Video v WHERE v.nameVideo LIKE :nameVideo";
			TypedQuery<Video> query = em.createQuery(sql, Video.class);
			query.setParameter("nameVideo", "%" + nameVideo + "%");
			// Trả về kết quả truy vấn

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void update(Video Video) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạc transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// bắt đầu transaction
			tran.begin();
			// update Video vào CSDL
			em.merge(Video);
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

	public void updateViewsByID(Video Video) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạc transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// bắt đầu transaction
			tran.begin();
			// update Video vào CSDL
			em.merge(Video);
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

	public Video getByID(int id) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Video o WHERE o.videoID = :videoID";

		// Tạo đối tượng truy vấn
		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);

		query.setParameter("videoID", id);

		// Trả về kết quả truy vấn
		return query.getSingleResult();
	}

	public void deleteAll() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();
		// Tạo câu truy vấn
		String jqpl = "Delete from Video";
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
			// update Video vào CSDL
			Video Video = em.find(Video.class, id);
			// Nếu tìm thấy thì xóa, không thì không tồn tại
			if (Video != null) {
				em.remove(Video);
			} else {
				throw new Exception("This Video does not exist!");
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

	// đếm số lượng Account trong database
	public int countAccounts() { 
	    EntityManager entityManager = JpaUtils.getEntityManager(); 
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder(); 
	    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class); 
	    countQuery.select(criteriaBuilder.count(countQuery.from(Video.class))); 
	    Long count = entityManager.createQuery(countQuery).getSingleResult(); 
	    entityManager.close(); 
	    return count.intValue(); 
	}


	public List<Video> pagingAccount(int index) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Lấy câu lệnh findAll có sẵn lúc tạo lớp Video 
		String sql = "SELECT a FROM Video a ORDER BY a.videoID DESC";
		TypedQuery<Video> query = em.createQuery(sql, Video.class);
		query.setFirstResult(index);
		query.setMaxResults(3);
		// Trả về kết quả truy vấn

		return query.getResultList();
	}

	public List<Video> findAll() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Lấy câu lệnh findAll có sẵn lúc tạo lớp Video
		String sql = "SELECT v FROM Video v ORDER BY NEWID()";
		TypedQuery<Video> query = em.createQuery(sql, Video.class);
		// Trả về kết quả truy vấn

		return query.getResultList();
	}
	
	public List<Video> getAllOderViews() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Lấy câu lệnh findAll có sẵn lúc tạo lớp Video
		String sql = "SELECT v FROM Video v ORDER BY v.views DESC";
		TypedQuery<Video> query = em.createQuery(sql, Video.class);
		// Trả về kết quả truy vấn

		return query.getResultList();
	}

	public List<Video> findAllID() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Lấy câu lệnh findAll có sẵn lúc tạo lớp Video
		String sql = "SELECT v FROM Video v ORDER BY v.id DESC";
		TypedQuery<Video> query = em.createQuery(sql, Video.class);
		// Trả về kết quả truy vấn

		return query.getResultList();
	}
	
	public List<Video> getTop5() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v ORDER BY NEWID()", Video.class)
				.setMaxResults(5);

		// Trả về kết quả
		return query.getResultList();
	}

	public Video findOne(String id, String password) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Video o WHERE o.id = :id AND o.password = :password";
		// tạo đối tượng truy vấn
		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);

		query.setParameter("id", id);
		query.setParameter("password", password);
		// Trả về kết quả
		return query.getSingleResult();
	}

	public List<Video> findByRole(boolean role) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "SELECT o from Video o WHERE o.admin = :role";
		// Tạo đối tượng truy vấn
		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);
		query.setParameter("role", role);
		// Trả về kết quả truy vấn
		return query.getResultList();
	}

	public List<Video> findByKeyWord(String keyword) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Video o WHERE o.fullname like :keyword";

		// Tạo đối tượng truy vấn
		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);
		query.setParameter("keyword", "%" + keyword + "%");
		// Trả về kết quả truy vấn
		return query.getResultList();
	}

	public List<Video> getVideoByCategoryID(int categoryID) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT v FROM Video v WHERE v.category.categoryID = :categoryID ORDER BY NEWID()";

		// Tạo đối tượng truy vấn
		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);
		query.setParameter("categoryID", categoryID);
		// Trả về kết quả truy vấn
		return query.getResultList();
	}

	public List<Video> getVideoByAuthorID(int authorID) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT v FROM Video v WHERE v.account.authorID = :authorID ORDER BY NEWID()";

		// Tạo đối tượng truy vấn
		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);
		query.setParameter("authorID", authorID);
		// Trả về kết quả truy vấn
		return query.getResultList();
	}

}
