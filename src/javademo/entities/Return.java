package javademo.entities;


import java.sql.Date;

public class Return {
    private String stuId;
    private String bookId;
    private String stuName;
    private String bookName;
    private Date borrowDate;
    private Date expectReturnDate;
    private Date realReturnDate;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getRealReturnDate() {
        return realReturnDate;
    }

    public void setRealReturnDate(Date realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpectReturnDate() {
        return expectReturnDate;
    }

    public void setExpectReturnDate(Date expectReturnDate) {
        this.expectReturnDate = expectReturnDate;
    }
}
