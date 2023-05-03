package com.fpoly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fpoly.entity.Account;
import com.fpoly.entity.Account;
import com.fpoly.utils.JpaUtils;

public class DAO_Account {
	public void create(Account account) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// Bắt đầu transaction
			tran.begin();
			// Lưu Account vào CSDL
			em.persist(account);
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

	public void update(Account account) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạc transaction
		EntityTransaction tran = em.getTransaction();

		try {
			// bắt đầu transaction
			tran.begin();
			// update Account vào CSDL
			em.merge(account);
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
		String jqpl = "Delete from Account";
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
			// update Account vào CSDL
			Account account = em.find(Account.class, id);
			// Nếu tìm thấy thì xóa, không thì không tồn tại
			if (account != null) {
				em.remove(account);
			} else {
				throw new Exception("This Account does not exist!");
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

	public Account login(String userName, String password) {
		try {
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = JpaUtils.getEntityManager();
			// Lấy câu lệnh findAll có sẵn lúc tạo lớp Account
			String sql = "SELECT a FROM Account a WHERE a.userName = :userName AND a.password = :password";
			TypedQuery<Account> query = em.createQuery(sql, Account.class);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			// Trả về kết quả truy vấn

			return query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Lỗi Login");
		}
		return null;
	}

	public List<Account> findAll() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Lấy câu lệnh findAll có sẵn lúc tạo lớp Account
		String sql = "SELECT account FROM Account account";
		TypedQuery<Account> query = em.createQuery(sql, Account.class);
		// Trả về kết quả truy vấn

		return query.getResultList();
	}

	public List<Account> getTop5() {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Account> query = em.createQuery("SELECT account FROM Account account ORDER BY RAND()", Account.class)
				.setMaxResults(5);

		// Trả về kết quả
		return query.getResultList();
	}

	public Account findOne(String id, String password) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Account o WHERE o.id = :id AND o.password = :password";
		// tạo đối tượng truy vấn
		TypedQuery<Account> query = em.createQuery(jqpl, Account.class);

		query.setParameter("id", id);
		query.setParameter("password", password);
		// Trả về kết quả
		return query.getSingleResult();
	}

	public Account getByUser(String userName) {

		try {
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = JpaUtils.getEntityManager();
			// Tạo câu truy vấn
			String jqpl = "SELECT o FROM Account o WHERE o.userName = :userName";
			// tạo đối tượng truy vấn
			TypedQuery<Account> query = em.createQuery(jqpl, Account.class);

			query.setParameter("userName", userName);
			// Trả về kết quả
			return query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Lỗi Login");
		}
		return null;

	}

	public Account findByID(int id) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Account o WHERE o.id = :id";

		// Tạo đối tượng truy vấn
		TypedQuery<Account> query = em.createQuery(jqpl, Account.class);

		query.setParameter("id", id);

		// Trả về kết quả truy vấn
		return query.getSingleResult();
	}

	public List<Account> findByRole(boolean role) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "SELECT o from Account o WHERE o.admin = :role";
		// Tạo đối tượng truy vấn
		TypedQuery<Account> query = em.createQuery(jqpl, Account.class);
		query.setParameter("role", role);
		// Trả về kết quả truy vấn
		return query.getResultList();
	}

	public List<Account> findByKeyWord(String keyword) {
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = JpaUtils.getEntityManager();
		// Tạo câu truy vấn
		String jqpl = "SELECT o FROM Account o WHERE o.fullname like :keyword";

		// Tạo đối tượng truy vấn
		TypedQuery<Account> query = em.createQuery(jqpl, Account.class);
		query.setParameter("keyword", "%" + keyword + "%");
		// Trả về kết quả truy vấn
		return query.getResultList();
	}
}
