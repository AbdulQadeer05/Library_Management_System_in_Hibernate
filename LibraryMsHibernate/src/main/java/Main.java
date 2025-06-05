import Entities.Author;
import Entities.Book;
import Entities.User;
import Entities.Publish;
import Entities.BookIssue;
import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String arg[]) {
        int oo = 11;
        do {

            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");

            SessionFactory sessionFactory = cfg.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Scanner scanner = new Scanner(System.in);

            System.out.print("1. User \n2. Login \n3. Book \n4. Issue Book  \n5. Author \n6. Publisher \nEnter Selection :");
            int sel = scanner.nextInt();
            //_________________________User Start_________________________
            if (sel == 1) {
                System.out.print("1. User Add \n2. User Update \n3. User Get \n4. User Delete \nEnter Selection :");
                int user = scanner.nextInt();

                if (user == 1) {

                    System.out.print("Enter your Name :");
                    String name = scanner.next();
                    System.out.print("Enter your Email :");
                    String email = scanner.next();
                    System.out.print("Enter your password :");
                    String password = scanner.next();

                    User user2 = new User();
                    user2.setName(name);
                    user2.setEmail(email);
                    user2.setPassword(password);

                    try {
                        session.save(user2);
                        transaction.commit();
                        System.out.print("User Added Successfully");
                    } catch (Exception e) {
                        transaction.rollback();
                        System.out.print("User Not Added Successfully");
                    }
                }
                if (user == 2) {

                    try {

                        System.out.print("Enter the name of the user you want to update:");
                        String oldName = scanner.next();

                        System.out.print("Enter the new name:");
                        String newName = scanner.next();

                        String hql = "UPDATE User SET name = :newName WHERE name = :oldName";

                        session.createQuery(hql)
                                .setParameter("newName", newName)
                                .setParameter("oldName", oldName)
                                .executeUpdate();
                        transaction.commit();
                        System.out.print
                                ("Record Update Successfully");

                    } catch (Exception e) {
                        System.out.print
                                ("Error");
                        e.printStackTrace();
                        transaction.rollback();
                    }
                }
                if (user == 3) {
                    try {
                        System.out.print("Enter the Id you want to display the record :1" +
                                "");
                        int na = scanner.nextInt();
                        User user3 = session.get(User.class, na);
                        System.out.println(user3.getId());
                        System.out.println(user3.getName());
                        System.out.println(user3.getEmail());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (user == 4) {
                    try {
                        System.out.print("Enter the name to delete the record");
                        String name = scanner.next();

                        String hql = "Delete From User where name = :name";
                        session.createQuery(hql)
                                .setParameter("name", name)
                                .executeUpdate();
                        transaction.commit();
                        System.out.print("Record Delete Successfully");

                    } catch (Exception e) {
                        System.out.print
                                ("Error");
                        e.printStackTrace();
                        transaction.rollback();
                    }
                }


            }
            //__________________User End___________________
            //________________Login Start_________________
            if (sel == 2) {
                User user = new User();

                System.out.print("Enter your name");
                String name = scanner.next();

                System.out.print("Enter Password");
                String password = scanner.next();

                try {
                    String hql = "FROM User WHERE name = :name AND password = :password";
                    User user1 = (User) session.createQuery(hql)
                            .setParameter("name", name)
                            .setParameter("password", password)
                            .uniqueResult();
                    if (user1 != null) {
                        System.out.print("Welcome");
                    } else {
                        System.out.print("Invalid credentials");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            //___________Login End_______________
            //_____________Book Start____________________

            if (sel == 3) {
                Book book = new Book();
                System.out.print("1. Add Book \n2. Update book \n3. Get All Book's \n4. Delete Book \nEnter Selection :");
                int bk = scanner.nextInt();

                if (bk == 1) {
                    System.out.print("Enter the book Name :");
                    String bookName = scanner.next();

                    System.out.print("Enter the Author");
                    String author = scanner.next();

                    try {
                        book.setBookName(bookName);
                        book.setAuthor(author);
                        session.save(book);
                        transaction.commit();
                        System.out.print("Record added Successfully");
                    } catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                    }
                }
                if (bk == 2) {
                    System.out.print("Enter the Name of Book which you want to Update :");
                    String oldName = scanner.next();
                    System.out.print("Enter the new Name");
                    String newName = scanner.next();

                    try {
                        String hql = "Update Book set bookName= :newName where bookName= :oldName";
                        session.createQuery(hql)
                                .setParameter("newName", newName)
                                .setParameter("oldName", oldName)
                                .executeUpdate();
                        transaction.commit();
                        System.out.print("Record Update Successfully");

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.print("Error");
                        transaction.rollback();
                    }

                }
                if (bk == 3) {
                    System.out.print("Enter the Book Id :");
                    int bookId = scanner.nextInt();

                    Book b = session.get(Book.class, bookId);
                    System.out.println(b.getBookId());
                    System.out.println(b.getBookName());
                    System.out.println(b.getAuthor());

                }
                if (bk == 4) {
                    try {
                        System.out.print("Enter the Book Name to Delete the record :");
                        String bookName = scanner.next();

                        String hql = "Delete from Book where bookName= :bookName ";
                        session.createQuery(hql)
                                .setParameter("bookName", bookName)
                                .executeUpdate();
                        transaction.commit();
                        System.out.print("Record Delete Successfully");
                    } catch (Exception e) {
                        System.out.print("Error");
                        e.printStackTrace();
                    }
                }

            }
            //_______________Author Start_____________
            if (sel == 5) {
                Author author = new Author();
                System.out.print("1. Add Author \n2. Update Author \n3. Delete Author \nEnter Selection");
                int authr = scanner.nextInt();

                if (authr == 1) {
                    System.out.print("Enter the Book Name :");
                    String bookName = scanner.next();
                    System.out.print("Enter the author Name :");
                    String authorName = scanner.next();
                    System.out.print("Enter the Contact :");
                    int contact = scanner.nextInt();

                    try {
                        author.setBookName(bookName);
                        author.setAuthorName(authorName);
                        author.setAuthorContact(contact);

                        session.save(author);
                        transaction.commit();
                        System.out.print("Record Added Successfully");

                    } catch (Exception e) {
                        System.out.print("Error");
                        e.printStackTrace();

                    }
                }
                if (authr == 2) {
                    System.out.print("Enter the Author Name which you want to Update :");
                    String oldName = scanner.next();
                    System.out.print("Enter the New Name :");
                    String newName = scanner.next();
                    try {

                        String hql = "Update Author set authorName = :newName where authorName = :oldName ";
                        session.createQuery(hql)
                                .setParameter("newName", newName)
                                .setParameter("oldName", oldName)
                                .executeUpdate();
                        transaction.commit();
                        System.out.print("Record Update Successfully");
                    } catch (Exception e) {
                        System.out.print("Error");
                        e.printStackTrace();
                    }

                }
                if (authr == 3) {
                    System.out.print("Enter the Author Name to Delete the Record :");
                    String name = scanner.next();

                    try {
                        String hql = "Delete From Author where authorName = : name ";
                        session.createQuery(hql)
                                .setParameter("name", name)
                                .executeUpdate();
                        transaction.commit();
                        System.out.print
                                ("Record Delete Successfully");

                    } catch (Exception e) {
                        System.out.print
                                ("Error");
                        e.printStackTrace();
                    }
                }

            }
            //___________________Publish__________________
            if (sel == 6) {
                Publish x = new Publish();
                System.out.print
                        ("1. Add Publisher \n2. Update Publisher \n3. Delete Publisher \nEnter Selection :");
                int pub = scanner.nextInt();

                if (pub == 1) {
                    System.out.print("Enter the Publisher Name :");
                    String name = scanner.next();
                    System.out.print("Enter the Address :");
                    String address = scanner.next();
                    System.out.print("Enter the Phone No :");
                    int no = scanner.nextInt();

                    try {
                        x.setName(name);
                        x.setAddress(address);
                        x.setPhone(no);

                        session.save(x);
                        transaction.commit();
                        System.out.print
                                ("Record Addedd Successfully");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.print
                                ("Error");
                    }
                }
                if (pub == 2) {
                    System.out.print("Enter the Name which you want to Update :");
                    String oldName = scanner.next();
                    System.out.print("Enter the New Name :");
                    String newName = scanner.next();
                    try {

                        String hql = "Update Publish set name= :newName where name= : oldName ";
                        session.createQuery(hql)
                                .setParameter("oldName", oldName)
                                .setParameter("newName", newName)
                                .executeUpdate();
                        transaction.commit();
                        System.out.print("REcord Update Successfully");

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.print("Error");
                    }

                }
                if (pub == 3) {
                    System.out.print("Enter the Name to Delete the Record  :");
                    String name = scanner.next();
                    try {
                        String hql = "Delete from Publish where name= :name";
                        session.createQuery(hql)
                                .setParameter("name", name)
                                .executeUpdate();
                        transaction.commit();
                        System.out.print("Record Delete Successfully ");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.print("Error");
                    }
                }

            }

            //______________________Book Issue___________________
            if (sel == 4) {
                BookIssue issue = new BookIssue();

                System.out.print("Enter the Book Id :");
                int bookId = scanner.nextInt();
                System.out.print("Enter the User Id :");
                int userId = scanner.nextInt();
                System.out.print("Enter the Issue Date :");
                String inputDate = scanner.next();
                LocalDate issueDate = LocalDate.parse(inputDate);
                System.out.print("Enter the Return Date :");
                String rDAte = scanner.next();
                LocalDate returDate = LocalDate.parse(rDAte);

                try{

                    User user= session.get(User.class,userId);
                    Book book= session.get(Book.class,bookId);


                    issue.setUser(user.getName());
                    issue.setBook(book.getBookName());

                    issue.setIssue_date(issueDate);
                    issue.setReturn_date(returDate);

                    session.save(issue);
                    transaction.commit();
                    System.out.println("Record Added Successfully");

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Error");
                }

                }

            }while (oo == 11);


            }

        }
