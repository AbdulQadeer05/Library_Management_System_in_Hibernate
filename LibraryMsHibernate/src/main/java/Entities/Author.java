package Entities;

import javax.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private int authorId;
    @Column
    private String authorName;
    @Column
    private String bookName;
    @Column
    private int authorContact;

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public int getAuthorId() {
        return authorId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookName() {
        return bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorContact(int authorContact) {
        this.authorContact = authorContact;
    }
    public int getAuthorContact() {
        return authorContact;
    }
}
