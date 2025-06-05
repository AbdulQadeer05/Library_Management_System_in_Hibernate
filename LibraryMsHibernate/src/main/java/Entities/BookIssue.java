package Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BookIssue {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String user;
        private String book;
        @Column
      private LocalDate issue_date;
        @Column
        private LocalDate return_date;

        public void setId(int id) {
                this.id = id;
        }
        public int getId() {
                return id;
        }

        public void setBook(String book) {
                this.book = book;
        }
        public String getBook() {
                return book;
        }

        public void setUser(String user) {
                this.user = user;
        }

        public String getUser() {
                return user;
        }

        public void setIssue_date(LocalDate issue_date) {
                this.issue_date = issue_date;
        }
        public LocalDate getIssue_date() {
                return issue_date;
        }

        public void setReturn_date(LocalDate return_date) {
                this.return_date = return_date;
        }
        public LocalDate getReturn_date() {
                return return_date;
        }


}


