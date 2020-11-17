package javademo.entities;

import java.sql.Date;

public class Borrow {
    private String stuId;
    private String stuName;
    private String bookId;
    private String bookName;
    private Date borrowDate;
    private Date expectReturnDate;
    private Date realReturnDate;
    private String isExceeded;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getRealReturnDate() {
        return realReturnDate;
    }

    public void setRealReturnDate(Date realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    public String getIsExceeded() {
        return isExceeded;
    }

    public void setIsExceeded(String isExceeded) {
        this.isExceeded = isExceeded;
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
    @Override
    public String toString() {
        return "Borrow{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", borrowDate=" + borrowDate +
                ", expectReturnDate=" + expectReturnDate +
                ", realReturnDate=" + realReturnDate +
                ", isExceeded='" + isExceeded + '\'' +
                '}';
    }
}
