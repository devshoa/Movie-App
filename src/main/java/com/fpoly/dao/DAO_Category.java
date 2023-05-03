package com.fpoly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fpoly.entity.Category;
import com.fpoly.utils.JpaUtils;

public class DAO_Category {
	public void create(Category category) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// Bắt đầu transaction
			tran.begin();
			// Lưu Category vào CSDL
			em.persist(category);
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

	public void update(Category category) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạc transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// bắt đầu transaction
			tran.begin();
			// update Category vào CSDL
			em.merge(category);
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
		String jqpl = "Delete from Category";
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
			// update Category vào CSDL
			Category category = em.find(Category.class, id);
			// Nếu tìm thấy thì xóa, không thì không tồn tại
			if (category != null) {
				em.remove(category);
			} else {
				throw new Exception("This Category does not exist!");
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

	public List<Category> findAll() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Lấy câu lệnh findAll có sẵn lúc tạo lớp Category
		String sql = "SELECT Category FROM Category Category";
		TypedQuery<Category> query = em.createQuery(sql, Category.class);
		// Trả về kết quả truy vấn

		return query.getResultList();
	}

	public List<Category> getTop5() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Category> query = em
				.createQuery("SELECT Category FROM Category Category ORDER BY RAND()", Category.class)
				.setMaxResults(5);

		// Trả về kết quả
		return query.getResultList();
	}

	public Category findOne(String id, String password) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Category o WHERE o.id = :id AND o.password = :password";
		// tạo đối tượng truy vấn
		TypedQuery<Category> query = em.createQuery(jqpl, Category.class);

		query.setParameter("id", id);
		query.setParameter("password", password);
		// Trả về kết quả
		return query.getSingleResult();
	}

	public Category findByID(int id) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Category o WHERE o.id = :id";

		// Tạo đối tượng truy vấn
		TypedQuery<Category> query = em.createQuery(jqpl, Category.class);

		query.setParameter("id", id);

		// Trả về kết quả truy vấn
		return query.getSingleResult();
	}

	public List<Category> findByRole(boolean role) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "SELECT o from Category o WHERE o.admin = :role";
		// Tạo đối tượng truy vấn
		TypedQuery<Category> query = em.createQuery(jqpl, Category.class);
		query.setParameter("role", role);
		// Trả về kết quả truy vấn
		return query.getResultList();
	}

	public List<Category> findByKeyWord(String keyword) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Category o WHERE o.fullname like :keyword";

		// Tạo đối tượng truy vấn
		TypedQuery<Category> query = em.createQuery(jqpl, Category.class);
		query.setParameter("keyword", "%" + keyword + "%");
		// Trả về kết quả truy vấn
		return query.getResultList();
	}
}
